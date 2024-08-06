/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View;
import java.util.*;
import DAO.chiTietHoaDonDAO;
import DAO.mauSacDAO;
import DAO.danhMucDAO;
import DAO.thuongHieuDAO;
import DAO.sizeDAO;
import DAO.thongkeDAO;
import org.jfree.data.general.DefaultPieDataset;
import entity.chitiethoadon;
import entity.mausac;
import entity.danhmuc;
import entity.thuonghieu;
import entity.size;
import javax.swing.table.DefaultTableModel;
import Helper.PieChart;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
/**
 *
 * @author letnp
 */
public class ThongKe1 extends javax.swing.JDialog {

    /**
     * Creates new form ThongKe1
     */
    
    DefaultTableModel model;
    DefaultTableModel model2;
    List<chitiethoadon> cthoadons;
    List<danhmuc> danhmucs;
    List<thuonghieu> thuonghieus;
    List<mausac> mausacs;
    List<size> sizes;
    List<Object[]> sanphams;
    List<Object[]> ctdoanhthus;
    List<Integer> years;
    public ThongKe1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
//        showPieChartDM();
        years = new thongkeDAO().getYearFromHD();
        sanphams = new thongkeDAO().getAllSP_DM(1, years.getFirst());
        ctdoanhthus = new thongkeDAO().getAllCTDT();
        getYear();
        getMonth();
        reNewCbbLocDate();
        reNewDateChooser();
        resetComboBox();
        resetTable();
        resetTable2();
        refresh();
    }
    void reNewCbbLocDate(){
        cbbLocDate.removeAllItems();
        cbbLocDate.addItem("Lọc theo ngày");
        cbbLocDate.addItem("Lọc theo tháng");
        cbbLocDate.addItem("Lọc theo năm");
    }
    void reNewDateChooser(){
        // biến dcsto này ở đâu?
        thongkeDAO tkd = new thongkeDAO();
        dcsTo.setDate(tkd.getMaxHDDate());
        dcsFrom.setDate(tkd.getMinHDDate());
    }
    String pathSaveFile(){
        String pathName = "";
        JFileChooser folderPath = new JFileChooser();
        folderPath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        folderPath.setDialogTitle("Select a Folder");

        int x = folderPath.showDialog(null, "Save");
        if (x == JFileChooser.APPROVE_OPTION){
            pathName = folderPath.getSelectedFile().toString();
        }
        if (!pathName.toLowerCase().endsWith(".xlsx")) {
            pathName += ".xlsx";
        }
        return  pathName;
    }
    void getYear(){
        cbbYear.removeAllItems();
        for(Integer x : years){
            cbbYear.addItem("Năm "+x.toString());
        }
        cbbYear.setSelectedIndex(0);
    }
    void getMonth(){
        cbbMonths.removeAllItems();
        for(int i = 1; i < 13; i++){
            cbbMonths.addItem("Tháng " + i);
        }
        cbbMonths.setSelectedIndex(0);
    }
    void xuatFileExcel(JTable tbl, String pathname){
    try {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("danhsach");
        XSSFRow row = null;
        Cell cell = null;

        row = sheet.createRow(0); 
        int columnCount = tbl.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            cell = row.createCell(i, CellType.STRING);
            cell.setCellValue(tbl.getColumnName(i));
        }

        int rowCount = tbl.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < columnCount; j++) {
                cell = row.createCell(j, CellType.STRING);
                Object value = tbl.getValueAt(i, j);
                if (value != null) {
                    cell.setCellValue(value.toString());
                }
            }
        }
        File f = new File(pathname);
        try {
            FileOutputStream fos = new FileOutputStream(f);
            workbook.write(fos);
            fos.close();
            workbook.close(); 
            JOptionPane.showMessageDialog(rootPane, "Đã xuất file Excel thành công! ");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Lỗi "+ e);
        }
    } catch (Exception e) {
        e.printStackTrace(); 
    }
        
    }
    void showPieChartDM(){
            DefaultPieDataset pieDataSet;
    JFreeChart pieChart;
    PiePlot piePlot;
    ChartPanel chartPanel;
    String title;
        pieDataSet = new DefaultPieDataset();
        pieChart = ChartFactory.createPieChart("gdgdfg", pieDataSet,true,true,false);
        piePlot = (PiePlot) pieChart.getPlot();
        chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(600, 500)); // Set preferred size
        pnlPieChartDM.removeAll();
        pnlPieChartDM.setLayout(new BorderLayout());
        pnlPieChartDM.add(chartPanel, BorderLayout.CENTER);
        pnlPieChartDM.validate();
        pnlPieChartDM.repaint();
    }
    void refresh(){
        Object[] dtHomNay = new thongkeDAO().getHomNayDT();
        Object[] dtThang = new thongkeDAO().getThangNayDT();
        Object[] dtNam = new thongkeDAO().getNamNayDT();
        Object[] dtcn = new thongkeDAO().getNgayDTCaoNhat();
        lblDTHomNay.setText(dtHomNay[0].toString() + " VNĐ");
        lblDonTCHomNay.setText(dtHomNay[1].toString() );
        lblDonBHHomNay.setText(dtHomNay[2].toString() );
        
        lblThangNayDT.setText(dtThang[0].toString() + " VNĐ");
        lblDonTCThang.setText(dtThang[1].toString() );
        lblDonBHThang.setText(dtThang[2].toString() );
        
        lblNamNayDT.setText(dtNam[0].toString() + " VNĐ");
        lblDonTCNam.setText(dtNam[1].toString() );
        lblDonBHNam.setText(dtNam[2].toString() );
        
        lblDTCN.setText(dtcn[0].toString() + " VNĐ");
        lblDTCNDonTC.setText(dtcn[1].toString()) ;
        lblDTCNDonBH.setText(dtcn[2].toString() );
    }
    void resetComboBox(){
        cbbParent.removeAllItems();
        cbbParent.addItem("Danh mục");
        cbbParent.addItem("Thương hiệu");
        cbbParent.setSelectedIndex(0);
        getDanhMuc();
}
    void  resetTable2(){
        model2 = new DefaultTableModel();
        model2.addColumn(jrTimeColumnName);
        model2.addColumn("Số lượng đã bán ra");
        model2.addColumn("Doanh thu");
        for (Object[]x : ctdoanhthus){
            model2.addRow(x);
        }
        tblDoanhThu.setModel(model2);
    }
    void resetTable(){
        model = new DefaultTableModel();
        model. addColumn("ID");
        model. addColumn("Tên sản phẩm");
        model. addColumn("Giá");
        model. addColumn("Số lượng đã bán");
        model. addColumn("Doanh thu");
        model. addColumn(cbbParent.getSelectedItem());
        for( Object[] x :sanphams){
            model.addRow(x);
        }
        tblThongKe.setModel(model);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        cbbParent = new javax.swing.JComboBox<>();
        cbbChild = new javax.swing.JComboBox<>();
        cbbYear = new javax.swing.JComboBox<>();
        btnFilter = new javax.swing.JButton();
        cbbMonths = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThongKe = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        pnlPieChartDM = new javax.swing.JPanel();
        xuatExcelSP = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        lblDTCN = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lblDTCNDonBH = new javax.swing.JLabel();
        lblDTCNDonTC = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        lblNamNayDT = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        lblDonBHNam = new javax.swing.JLabel();
        lblDonTCNam = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblDTHomNay = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        lblDonBHHomNay = new javax.swing.JLabel();
        lblDonTCHomNay = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        lblThangNayDT = new javax.swing.JLabel();
        lable1 = new javax.swing.JLabel();
        labe4 = new javax.swing.JLabel();
        lblDonBHThang = new javax.swing.JLabel();
        lblDonTCThang = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDoanhThu = new javax.swing.JTable();
        btnXuatEXcelDT = new javax.swing.JButton();
        btnDTFilter = new javax.swing.JButton();
        btnDTRefresh = new javax.swing.JButton();
        cbbLocDate = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        dcsFrom = new com.toedter.calendar.JDateChooser();
        jPanel12 = new javax.swing.JPanel();
        dcsTo = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setLayout(null);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("FILTER"));

        cbbParent.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbParent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbParentActionPerformed(evt);
            }
        });

        cbbChild.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbChild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbChildActionPerformed(evt);
            }
        });

        cbbYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbYearActionPerformed(evt);
            }
        });

        btnFilter.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnFilter.setText("FILTER");
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });

        cbbMonths.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cbbChild, javax.swing.GroupLayout.Alignment.LEADING, 0, 216, Short.MAX_VALUE)
                            .addComponent(cbbParent, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cbbMonths, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbYear, javax.swing.GroupLayout.Alignment.LEADING, 0, 154, Short.MAX_VALUE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(cbbParent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbChild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbMonths, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(btnFilter)
                .addContainerGap())
        );

        jPanel2.add(jPanel4);
        jPanel4.setBounds(0, 0, 251, 220);

        tblThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblThongKe);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(0, 340, 900, 404);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("SẢN PHẨM BÁN CHẠY NHẤT"));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel5);
        jPanel5.setBounds(301, 0, 280, 293);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("SẢN PHẨM BÁN KÉM NHẤT"));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel6);
        jPanel6.setBounds(605, 0, 280, 293);

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(307, 318, 283, 16);

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel2.setText("jLabel1");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(608, 318, 283, 16);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        pnlPieChartDM.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlPieChartDMLayout = new javax.swing.GroupLayout(pnlPieChartDM);
        pnlPieChartDM.setLayout(pnlPieChartDMLayout);
        pnlPieChartDMLayout.setHorizontalGroup(
            pnlPieChartDMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 564, Short.MAX_VALUE)
        );
        pnlPieChartDMLayout.setVerticalGroup(
            pnlPieChartDMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnlPieChartDM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnlPieChartDM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 390, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel1);
        jPanel1.setBounds(920, 0, 570, 740);

        xuatExcelSP.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        xuatExcelSP.setText("Xuất EXCEL");
        xuatExcelSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xuatExcelSPActionPerformed(evt);
            }
        });
        jPanel2.add(xuatExcelSP);
        xuatExcelSP.setBounds(10, 300, 106, 23);

        jTabbedPane4.addTab("SẢN PHẨM", jPanel2);

        jPanel3.setForeground(new java.awt.Color(0, 204, 51));

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel27.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel27.setText("KỶ LỤC DOANH THU NGÀY");

        lblDTCN.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        lblDTCN.setForeground(new java.awt.Color(0, 204, 51));
        lblDTCN.setText("jLabel4");

        jLabel29.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel29.setText("Thành công :");

        jLabel30.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel30.setText("Bị huỷ :");

        lblDTCNDonBH.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lblDTCNDonBH.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDTCNDonBH.setText("0");

        lblDTCNDonTC.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lblDTCNDonTC.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDTCNDonTC.setText("0");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDTCN, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDTCNDonBH, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDTCNDonTC, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDTCN)
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(lblDTCNDonTC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(lblDTCNDonBH))
                .addContainerGap())
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel33.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel33.setText("DOANH THU NĂM NAY");

        lblNamNayDT.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        lblNamNayDT.setForeground(new java.awt.Color(0, 204, 51));
        lblNamNayDT.setText("jLabel4");

        jLabel35.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel35.setText("Thành công :");

        jLabel36.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel36.setText("Bị huỷ :");

        lblDonBHNam.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lblDonBHNam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDonBHNam.setText("0");

        lblDonTCNam.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lblDonTCNam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDonTCNam.setText("0");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNamNayDT, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDonBHNam, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDonTCNam, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNamNayDT)
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(lblDonTCNam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(lblDonBHNam))
                .addContainerGap())
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel3.setText("DOANH THU HÔM NAY");

        lblDTHomNay.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        lblDTHomNay.setForeground(new java.awt.Color(0, 204, 51));
        lblDTHomNay.setText("jLabel4");

        jLabel7.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Thành công :");

        lbl2.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lbl2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl2.setText("Bị huỷ :");

        lblDonBHHomNay.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lblDonBHHomNay.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDonBHHomNay.setText("0");

        lblDonTCHomNay.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lblDonTCHomNay.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDonTCHomNay.setText("0");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDTHomNay, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDonBHHomNay, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDonTCHomNay, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDTHomNay)
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblDonTCHomNay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl2)
                    .addComponent(lblDonBHHomNay))
                .addContainerGap())
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel21.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel21.setText("DOANH THU THÁNG NÀY");

        lblThangNayDT.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        lblThangNayDT.setForeground(new java.awt.Color(0, 204, 51));
        lblThangNayDT.setText("jLabel4");

        lable1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lable1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lable1.setText("Thành công :");

        labe4.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        labe4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labe4.setText("Bị huỷ :");

        lblDonBHThang.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lblDonBHThang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDonBHThang.setText("0");

        lblDonTCThang.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lblDonTCThang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDonTCThang.setText("0");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblThangNayDT, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lable1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labe4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDonBHThang, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDonTCThang, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblThangNayDT)
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lable1)
                    .addComponent(lblDonTCThang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labe4)
                    .addComponent(lblDonBHThang))
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("CHI TIẾT DOANH THU"));

        tblDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblDoanhThu);

        btnXuatEXcelDT.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnXuatEXcelDT.setText("XUẤT EXCEL");
        btnXuatEXcelDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatEXcelDTActionPerformed(evt);
            }
        });

        btnDTFilter.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnDTFilter.setText("Filter");
        btnDTFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDTFilterActionPerformed(evt);
            }
        });

        btnDTRefresh.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnDTRefresh.setText("Refresh");
        btnDTRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDTRefreshActionPerformed(evt);
            }
        });

        cbbLocDate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(cbbLocDate, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(326, 326, 326)
                .addComponent(btnDTFilter, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addComponent(btnDTRefresh)
                .addGap(18, 18, 18)
                .addComponent(btnXuatEXcelDT)
                .addGap(38, 38, 38))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 864, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXuatEXcelDT)
                    .addComponent(btnDTFilter)
                    .addComponent(btnDTRefresh)
                    .addComponent(cbbLocDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 528, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Từ ngày"));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dcsFrom, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(dcsFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Đến ngày"));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dcsTo, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(dcsTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(70, 70, 70)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane4.addTab("DOANH THU", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    void getDay(){
//        cbbFromDay.removeAllItems();
//        cbbToDay.removeAllItems();
//        for(int i = 1; i< 32; i++){
//        cbbFromDay.addItem("Ngày "+i);
//        cbbToDay.addItem("Ngày "+i);
//    }
//    }
    void getDanhMuc(){
        danhmucs = new danhMucDAO().getAll();
        cbbChild.removeAllItems();
        cbbChild.addItem("Tất cả");
        for(danhmuc x: danhmucs){
            cbbChild.addItem(x.getTen());
        }
    }
    void getThuongHieu(){
        thuonghieus = new thuongHieuDAO().getAll();
        cbbChild.removeAllItems();
        cbbChild.addItem("Tất cả");
        for(thuonghieu x: thuonghieus){
            cbbChild.addItem(x.getTen());
        }
    }
//    void get_Size(){
//        sizes = new sizeDAO().getAll();
//        cbbChild.removeAllItems();
//        cbbChild.addItem("Tất cả");
//        for(size x: sizes){
//            cbbChild.addItem(x.getTen());
//        }
//    }
//    void getMauSac(){
//        mausacs = new mauSacDAO().getAll();
//        cbbChild.removeAllItems();
//        cbbChild.addItem("Tất cả");
//        for(mausac x: mausacs){
//            cbbChild.addItem(x.getMacsac());
//        }
//    }

    void getSanPham(){
        int choice = cbbParent.getSelectedIndex();
        int id = cbbChild.getSelectedIndex();
        int year = 0;
        int month =0;
        try{
            year = years.get(cbbYear.getSelectedIndex());
            month = cbbMonths.getSelectedIndex()+1;
          
        }catch(Exception e){
            System.out.println("hhjhj");
        }
        
        switch (choice){
            case 0: 
                sanphams = id ==0 ? new thongkeDAO().getAllSP_DM(month, year):new thongkeDAO().getSP_DM(id, month, year);
                resetTable();
                break;
            case 1:
                sanphams = id ==0 ? new thongkeDAO().getAllSP_TH(month, year):new thongkeDAO().getSP_TH(id, month, year);
                resetTable();
                break;
            default:
                cbbChild.removeAllItems();
                cbbChild.addItem("Tất cả");
        }
        resetTable();
    }
    String getDayFrom(){
        Date selectedDate = dcsFrom.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(selectedDate);
    }
    String getDayTo(){
        Date selectedDate = dcsTo.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(selectedDate);
    }
    private void cbbParentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbParentActionPerformed
        // TODO add your handling code here:
        int choice = cbbParent.getSelectedIndex();
        switch (choice){
            case 0:
                getDanhMuc();
                break;
            case 1:
                getThuongHieu();
                break;
            default:
                cbbChild.removeAllItems();
                cbbChild.addItem("Tất cả");
        }
    }//GEN-LAST:event_cbbParentActionPerformed

    private void cbbChildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbChildActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbChildActionPerformed

    private void cbbYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbYearActionPerformed
        // TODO add your handling code here:
//        int year = Integer.parseInt(cbbYear.getSelectedItem().toString());
        int year = cbbYear.getSelectedItem() == null ? 2023 : years.get(cbbYear.getSelectedIndex());
    }//GEN-LAST:event_cbbYearActionPerformed

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        // TODO add your handling code here:
        getSanPham();
        resetTable();
    }//GEN-LAST:event_btnFilterActionPerformed
    String jrTimeColumnName = "Ngày";
    private void btnDTFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDTFilterActionPerformed
        // TODO add your handling code here:
//        ctdoanhthus = new thongkeDAO().getCTDTByDay(dateFrom, dateTo);
        if(cbbLocDate.getSelectedIndex()==0){
            jrTimeColumnName = "Ngày";
            ctdoanhthus = new thongkeDAO().getCTDTByDay(getDayFrom(), getDayTo());
        }
        else if(cbbLocDate.getSelectedIndex()==1){
            jrTimeColumnName = "Tháng";
            ctdoanhthus = new thongkeDAO().getCTDTByMonth(getDayFrom(), getDayTo());
        }
        else if(cbbLocDate.getSelectedIndex()==2){
            jrTimeColumnName = "Năm";
            ctdoanhthus = new thongkeDAO().getCTDTByYear(getDayFrom(), getDayTo());
        }
        else {
            jrTimeColumnName = "Ngày";
            ctdoanhthus = new thongkeDAO().getAllCTDT();
        }
        resetTable2();
    }//GEN-LAST:event_btnDTFilterActionPerformed

    private void btnDTRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDTRefreshActionPerformed
        // TODO add your handling code here:
        reNewDateChooser();
        reNewCbbLocDate();
        jrTimeColumnName = "Ngày";
        ctdoanhthus = new thongkeDAO().getAllCTDT();
        resetTable2();
    }//GEN-LAST:event_btnDTRefreshActionPerformed

    private void xuatExcelSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xuatExcelSPActionPerformed
        // TODO add your handling code here:
        xuatFileExcel(tblThongKe, pathSaveFile());
    }//GEN-LAST:event_xuatExcelSPActionPerformed

    private void btnXuatEXcelDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatEXcelDTActionPerformed
        // TODO add your handling code here:
        xuatFileExcel(tblDoanhThu, pathSaveFile());
    }//GEN-LAST:event_btnXuatEXcelDTActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThongKe1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongKe1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongKe1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongKe1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThongKe1 dialog = new ThongKe1(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDTFilter;
    private javax.swing.JButton btnDTRefresh;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnXuatEXcelDT;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbChild;
    private javax.swing.JComboBox<String> cbbLocDate;
    private javax.swing.JComboBox<String> cbbMonths;
    private javax.swing.JComboBox<String> cbbParent;
    private javax.swing.JComboBox<String> cbbYear;
    private com.toedter.calendar.JDateChooser dcsFrom;
    private com.toedter.calendar.JDateChooser dcsTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JLabel labe4;
    private javax.swing.JLabel lable1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lblDTCN;
    private javax.swing.JLabel lblDTCNDonBH;
    private javax.swing.JLabel lblDTCNDonTC;
    private javax.swing.JLabel lblDTHomNay;
    private javax.swing.JLabel lblDonBHHomNay;
    private javax.swing.JLabel lblDonBHNam;
    private javax.swing.JLabel lblDonBHThang;
    private javax.swing.JLabel lblDonTCHomNay;
    private javax.swing.JLabel lblDonTCNam;
    private javax.swing.JLabel lblDonTCThang;
    private javax.swing.JLabel lblNamNayDT;
    private javax.swing.JLabel lblThangNayDT;
    private javax.swing.JPanel pnlPieChartDM;
    private javax.swing.JTable tblDoanhThu;
    private javax.swing.JTable tblThongKe;
    private javax.swing.JButton xuatExcelSP;
    // End of variables declaration//GEN-END:variables
}

