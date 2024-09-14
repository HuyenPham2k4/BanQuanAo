/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View;
import java.util.*;
import DAO.danhMucDAO;
import DAO.thuongHieuDAO;
import DAO.thongkeDAO;
import entity.chitiethoadon;
import entity.mausac;
import entity.danhmuc;
import entity.thuonghieu;
import entity.size;
import javax.swing.table.DefaultTableModel;
import Helper.PieChart;
import Helper.ColumnChart;
import Helper.ThongKeWidget;
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
        pnlSPPieChartDraw();
        pnlSPColumnChartDraw();
        pnlDTLColumnChartDraw();
        AnhOfferSP();
        lblIMGBanChay.setSize(316, 316);
    }
    void AnhOfferSP(){
        Object[] banChay = new thongkeDAO().getSPTrending();
        Object[] banKem = new thongkeDAO().getSPBanKem();
        lblNameBanChay.setText(banChay[0].toString());
        ThongKeWidget.ImgIcon(lblIMGBanChay, banChay[1].toString());
        lblNameBanKem.setText(banKem[0].toString());
        ThongKeWidget.ImgIcon(lblIMGBanKem, banKem[1].toString());
        
    }
    void pnlDTLColumnChartDraw(){
        String category = "";
        List<Object[]> dts;
        int month = ThongKeWidget.getTodayMonth();
        int year = ThongKeWidget.getTodayYear();
        switch (cbbDTChart.getSelectedIndex()) {
            case 0:
                dts = new thongkeDAO().getDT10daysChart(ThongKeWidget.getToDaySQL());
                category = "Ngày";
                break;
            case 1:
                dts = new thongkeDAO().getDTWeekChart(month, year);
                category = "Tuần";
                break;
            case 2:
                dts = new thongkeDAO().getDTYearChart(year);
                category = "Tháng";
                break;
            default:
                throw new AssertionError();
        }
        ColumnChart columnChart = new ColumnChart("Biểu đồ doanh thu của "+cbbDTChart.getSelectedItem(),category,"nghìn VNĐ");
        
        DefaultTableModel modeltblChart = new DefaultTableModel();
        modeltblChart.addColumn(category);
        modeltblChart.addColumn("Doanh thu (nghìn VNĐ)");
        
        for(Object[] dt: dts){
            columnChart.setValue(dt[0].toString(),(Number) dt[1]);
            modeltblChart.addRow(new Object[]{
                dt[0].toString(),
                dt[1].toString()
            });
        }
        columnChart.drawChart(pnlDTLColumnChart);
        tblDTChart.setModel(modeltblChart);
        
    }
    void pnlSPColumnChartDraw(){
        int month = cbbMonths.getSelectedIndex()+1;
        int year = years.get(cbbYear.getSelectedIndex());
        List<Object[]> dts = new thongkeDAO().getDTWeekChart(month , year);
        ColumnChart columnChart = new ColumnChart("Biểu đồ doanh thu của mỗi tuần tháng "+month+" năm "+year,"Tuần thứ","nghìn VNĐ");
        for(Object[] dt: dts){
            columnChart.setValue(dt[0].toString(),(Number) dt[1]);
        }
        columnChart.drawChart(pnlColumnChartSP);
    }
    void pnlSPPieChartDraw(){
        List<Object[]> dts;
        PieChart pc = new PieChart();
        int month = cbbMonths.getSelectedIndex()+1;
        int year = years.get(cbbYear.getSelectedIndex());
        if(cbbParent.getSelectedIndex()==0){
            dts = new thongkeDAO().getDanhMucChart(month,year);
            pc.setTitle("Biểu đồ doanh thu của mỗi danh mục tháng "+month+" năm "+year);
        }
        else{
            dts = new thongkeDAO().getThuongHieuChart(month, year);
            pc.setTitle("Biểu đồ doanh thu của mỗi thương hiệu tháng "+month+" năm "+year);
        }
        for(Object[] dt : dts){
            pc.setValue(dt[0].toString(), (Number) dt[1]);
        }
        pc.drawChart(pnlPieChartSP);

    }
    void reNewCbbLocDate(){
        cbbLocDate.removeAllItems();
        cbbLocDate.addItem("Lọc theo ngày");
        cbbLocDate.addItem("Lọc theo tháng");
        cbbLocDate.addItem("Lọc theo năm");
    }
    void reNewDateChooser(){
        thongkeDAO tkd = new thongkeDAO();
        dcsTo.setDate(tkd.getMaxHDDate());
        dcsFrom.setDate(tkd.getMinHDDate());
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
        lblNameBanChay = new javax.swing.JLabel();
        lblNameBanKem = new javax.swing.JLabel();
        xuatExcelSP = new javax.swing.JButton();
        pnlPieChartSP = new javax.swing.JPanel();
        pnlColumnChartSP = new javax.swing.JPanel();
        lblIMGBanKem = new javax.swing.JLabel();
        lblIMGBanChay = new javax.swing.JLabel();
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
        jPanel9 = new javax.swing.JPanel();
        dcsFrom = new com.toedter.calendar.JDateChooser();
        jPanel12 = new javax.swing.JPanel();
        dcsTo = new com.toedter.calendar.JDateChooser();
        pnlDTChartInfo = new javax.swing.JPanel();
        pnlDTLColumnChart = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDTChart = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        cbbDTChart = new javax.swing.JComboBox<>();
        cbbLocDate = new javax.swing.JComboBox<>();
        btnDTFilter = new javax.swing.JButton();
        btnDTRefresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        lblNameBanChay.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lblNameBanChay.setText("jLabel1");

        lblNameBanKem.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lblNameBanKem.setText("jLabel1");

        xuatExcelSP.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        xuatExcelSP.setText("Xuất EXCEL");
        xuatExcelSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xuatExcelSPActionPerformed(evt);
            }
        });

        pnlPieChartSP.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlPieChartSPLayout = new javax.swing.GroupLayout(pnlPieChartSP);
        pnlPieChartSP.setLayout(pnlPieChartSPLayout);
        pnlPieChartSPLayout.setHorizontalGroup(
            pnlPieChartSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );
        pnlPieChartSPLayout.setVerticalGroup(
            pnlPieChartSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );

        pnlColumnChartSP.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlColumnChartSPLayout = new javax.swing.GroupLayout(pnlColumnChartSP);
        pnlColumnChartSP.setLayout(pnlColumnChartSPLayout);
        pnlColumnChartSPLayout.setHorizontalGroup(
            pnlColumnChartSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );
        pnlColumnChartSPLayout.setVerticalGroup(
            pnlColumnChartSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
        );

        lblIMGBanKem.setBorder(javax.swing.BorderFactory.createTitledBorder("SẢN PHẨM ĐANG BÁN KÉM"));

        lblIMGBanChay.setBorder(javax.swing.BorderFactory.createTitledBorder("SẢN PHẨM ĐANG BÁN CHẠY"));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(xuatExcelSP, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblNameBanChay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lblIMGBanChay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIMGBanKem, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblNameBanKem, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPieChartSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlColumnChartSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(xuatExcelSP))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblIMGBanKem, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIMGBanChay, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNameBanChay, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNameBanKem))))
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(pnlPieChartSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlColumnChartSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

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

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(btnXuatEXcelDT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Từ ngày"));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dcsFrom, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(dcsFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Đến ngày"));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dcsTo, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(dcsTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlDTChartInfo.setBackground(new java.awt.Color(255, 255, 255));

        pnlDTLColumnChart.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlDTLColumnChartLayout = new javax.swing.GroupLayout(pnlDTLColumnChart);
        pnlDTLColumnChart.setLayout(pnlDTLColumnChartLayout);
        pnlDTLColumnChartLayout.setHorizontalGroup(
            pnlDTLColumnChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlDTLColumnChartLayout.setVerticalGroup(
            pnlDTLColumnChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 352, Short.MAX_VALUE)
        );

        tblDTChart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "", "Doanh thu"
            }
        ));
        jScrollPane3.setViewportView(tblDTChart);

        javax.swing.GroupLayout pnlDTChartInfoLayout = new javax.swing.GroupLayout(pnlDTChartInfo);
        pnlDTChartInfo.setLayout(pnlDTChartInfoLayout);
        pnlDTChartInfoLayout.setHorizontalGroup(
            pnlDTChartInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlDTLColumnChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
        );
        pnlDTChartInfoLayout.setVerticalGroup(
            pnlDTChartInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDTChartInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDTLColumnChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        cbbDTChart.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10 ngày vừa qua", "tháng này", "năm này" }));
        cbbDTChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbDTChartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbbDTChart, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(366, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(cbbDTChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        cbbLocDate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbbLocDate, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnDTRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDTFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlDTChartInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(btnDTFilter)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbLocDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)))
                        .addComponent(btnDTRefresh)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlDTChartInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82))
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
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 782, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        return ThongKeWidget.convertToDateSQL(dcsFrom.getDate());
    }
    String getDayTo(){
        return ThongKeWidget.convertToDateSQL(dcsTo.getDate());
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
        int year = cbbYear.getSelectedItem() == null ? 2023 : years.get(cbbYear.getSelectedIndex());
    }//GEN-LAST:event_cbbYearActionPerformed

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        // TODO add your handling code here:
        getSanPham();
        resetTable();
        pnlSPPieChartDraw();
        pnlSPColumnChartDraw();
    }//GEN-LAST:event_btnFilterActionPerformed
    String jrTimeColumnName = "Ngày";
    private void btnDTFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDTFilterActionPerformed
        // TODO add your handling code here:
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
//        xuatFileExcel(tblThongKe, pathSaveFile());
        ThongKeWidget.xuatFileExcel(rootPane, tblThongKe);
    }//GEN-LAST:event_xuatExcelSPActionPerformed

    private void btnXuatEXcelDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatEXcelDTActionPerformed
        // TODO add your handling code here:
//        xuatFileExcel(tblDoanhThu, pathSaveFile());
        ThongKeWidget.xuatFileExcel(rootPane, tblDoanhThu);
    }//GEN-LAST:event_btnXuatEXcelDTActionPerformed

    private void cbbDTChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbDTChartActionPerformed
        // TODO add your handling code here:
        pnlDTLColumnChartDraw();
    }//GEN-LAST:event_cbbDTChartActionPerformed
    
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
        //</editor-fold>
        //</editor-fold>
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
    private javax.swing.JComboBox<String> cbbDTChart;
    private javax.swing.JComboBox<String> cbbLocDate;
    private javax.swing.JComboBox<String> cbbMonths;
    private javax.swing.JComboBox<String> cbbParent;
    private javax.swing.JComboBox<String> cbbYear;
    private com.toedter.calendar.JDateChooser dcsFrom;
    private com.toedter.calendar.JDateChooser dcsTo;
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
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
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
    private javax.swing.JLabel lblIMGBanChay;
    private javax.swing.JLabel lblIMGBanKem;
    private javax.swing.JLabel lblNamNayDT;
    private javax.swing.JLabel lblNameBanChay;
    private javax.swing.JLabel lblNameBanKem;
    private javax.swing.JLabel lblThangNayDT;
    private javax.swing.JPanel pnlColumnChartSP;
    private javax.swing.JPanel pnlDTChartInfo;
    private javax.swing.JPanel pnlDTLColumnChart;
    private javax.swing.JPanel pnlPieChartSP;
    private javax.swing.JTable tblDTChart;
    private javax.swing.JTable tblDoanhThu;
    private javax.swing.JTable tblThongKe;
    private javax.swing.JButton xuatExcelSP;
    // End of variables declaration//GEN-END:variables
}

