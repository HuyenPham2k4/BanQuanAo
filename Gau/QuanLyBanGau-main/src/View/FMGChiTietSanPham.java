/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import BUS.ChatLieuService;
import BUS.ChiTietSanPhamService;
import BUS.DanhMucSPService;
import BUS.IChatLieuService;
import BUS.IChiTietSanPhamService;
import BUS.IDanhMucSPService;
import BUS.IMauSacService;
import BUS.ISizeService;
import BUS.IThuongHieuService;
import BUS.ITrongLuongService;
import BUS.MauSacService;
import BUS.SizeService;
import BUS.ThuongHieuService;
import BUS.TrongLuongService;
import Models.ChatLieu;
import Models.ChiTietSanPham;
import Models.DanhMucSP;
import Models.MauSac;
import Models.Size;
import Models.ThuongHieu;
import Models.TrongLuong;
import Utilities.DialogHelper;
import Utilities.SessionHelper;
import Utilities.displayvalueModel;
import ViewModels.ChiTietSanPhamViewModels;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author qivub
 */
public class FMGChiTietSanPham extends javax.swing.JPanel {
    private Map<Integer, String> _idNameTh = new HashMap<>();
    private Map<Integer, String> _idNameSize = new HashMap<>();
    private Map<Integer, String> _idNameChatLieu = new HashMap<>();
    private Map<Integer, String> _idNameDanhMuc = new HashMap<>();
    private Map<Integer, String> _idNameMauSac = new HashMap<>();
    private Map<Integer, String> _idNameTrongLuong = new HashMap<>();
    List<MauSac> lstMau = new ArrayList<MauSac>();
    SessionHelper helper = new SessionHelper();
    DefaultTableModel modelCTSP;
    IMauSacService iMauSacService;
    IDanhMucSPService iDanhMucSPService;
    IThuongHieuService iThuongHieuService;
    IChatLieuService iChatLieuService;
    IChiTietSanPhamService iCTSPService;
    ITrongLuongService iTrongLuongService;
    ISizeService iSizeService;
    ChiTietSanPhamViewModels view;
    Size size;
    MauSac mausac;
    DanhMucSP DanhMucSP;
    ChatLieu chatLieu;
    ThuongHieu thuongHieu;
    TrongLuong trongLuong;
    ChiTietSanPham CTSP;
    ImageIcon icon;
    String strHinhanh =null;
    /**
     * Creates new form ChiTietSPJpanel
     */
    public FMGChiTietSanPham() {
        initComponents();
        iMauSacService = new MauSacService();
        iChatLieuService = new ChatLieuService();
        iCTSPService = new ChiTietSanPhamService();
        iSizeService = new SizeService();
        iTrongLuongService = new TrongLuongService();
        iThuongHieuService = new ThuongHieuService();
        iDanhMucSPService = new DanhMucSPService();
        modelCTSP = (DefaultTableModel) tbCTSP.getModel();
        view = new ChiTietSanPhamViewModels();
        CTSP = new ChiTietSanPham();
        size = new Size();
        trongLuong = new TrongLuong();
        mausac = new MauSac();
        thuongHieu = new ThuongHieu();
        DanhMucSP = new DanhMucSP();
        chatLieu = new ChatLieu();
        reset();
    }
    public void reset() {
        
        getCbbDanhMucSP();
        getCbbThuongHieu();
        getCbbMauSac();
        getCbbSize();
        getCbbChatLieu();
        getCbbTrongLuong();
        fillDataTableCTSP();
        try {
            
        } catch (Exception e) {
            
        }
    }

    private void getCbbDanhMucSP() {
        List<DanhMucSP> danhMucList = iDanhMucSPService.getData();
        
        System.out.println("size CBB: "+danhMucList.size());
        for (DanhMucSP danhMuc : danhMucList) {
            cbbdanhMucSP.addItem(danhMuc.getTenDanhMuc());
            cbbLoc_DanhMucSP.addItem(danhMuc.getTenDanhMuc());
        }
    }

    private int getIDDanhMucSP() {
        List<DanhMucSP> danhMucList = iDanhMucSPService.getData();
        int id = -1;
        int selectedIndex = cbbdanhMucSP.getSelectedIndex();
        DanhMucSP selectedDanhMuc = danhMucList.get(selectedIndex);
        id = selectedDanhMuc.getID_Danhmucsanpham();
        helper.setID_DmSP(id);
        System.out.println(selectedDanhMuc.getTenDanhMuc());
        return id;
    }

    private String getCBBSelected(JComboBox comboBox) {
        Object item = comboBox.getSelectedItem();
        String name = item != null ? item.toString() : "";
        comboBox.getSelectedItem();
        System.out.println(item);
        return name;
    }

    private void getCbbChatLieu() {
        List<ChatLieu> chatlieu = iChatLieuService.getData();
//        cbbLocChatLieu.removeAllItems();
        for (ChatLieu sz : chatlieu) {
            cbbChatLieu.addItem((sz.getTenChatLieu()));
            cbbLocChatLieu.addItem(sz.getTenChatLieu());
        }
    }

    private int getIDChatLieu() {
        List<ChatLieu> sizes = iChatLieuService.getData();
        int id = -1;
        int index = cbbChatLieu.getSelectedIndex();
        ChatLieu tg = sizes.get(index);
        id = tg.getID_ChatLieu();
        helper.setID_ChatLieu(id);
        System.out.println(tg.getTenChatLieu());
        return id;
    }

    private void getCbbSize() {
        List<Size> sizes = iSizeService.getData();
//        cbbLocSize.removeAllItems();
        for (Size sz : sizes) {
            cbbSize.addItem((sz.getTenSize()));
            cbbLocSize.addItem(sz.getTenSize());
        }
    }

    private int getIDSize() {
        List<Size> sizes = iSizeService.getData();
        int id = -1;
        int index = cbbSize.getSelectedIndex();
        Size tg = sizes.get(index);
        id = tg.getID_Size();
        helper.setID_Size(id);
        System.out.println(tg.getTenSize());
        return id;
    }

    private void getCbbThuongHieu() {
        List<ThuongHieu> sizes = iThuongHieuService.getData();
//        cbbLocThuongHieu.removeAllItems();
        for (ThuongHieu sz : sizes) {
            cbbThuongHieu.addItem((sz.getTenThuongHieu()));
            cbbLocThuongHieu.addItem(sz.getTenThuongHieu());
        }
    }

    private int getIDThuongHieu() {
        List<ThuongHieu> sizes = iThuongHieuService.getData();
        int id = -1;
        int index = cbbThuongHieu.getSelectedIndex();
        ThuongHieu tg = sizes.get(index);
        id = tg.getID_ThuongHieu();
        helper.setID_ThuongHieu(id);
        System.out.println(tg.getTenThuongHieu());
        return id;
    }

    private void getCbbMauSac() {
        List<MauSac> sizes = iMauSacService.getData();
//        cbbLocMauSac.removeAllItems();
        for (MauSac sz : sizes) {
            cbbMauSac.addItem((sz.getTenMauSac()));
            cbbLocMauSac.addItem(sz.getTenMauSac());
        }
    }

    private int getIDMauSac() {
        List<MauSac> sizes = iMauSacService.getData();
        int id = -1;
        int index = cbbMauSac.getSelectedIndex();
        MauSac tg = sizes.get(index);
        id = tg.getID_MauSac();
        helper.setID_MauSac(id);
        System.out.println(tg.getTenMauSac());
        return id;
    }

    private void getCbbTrongLuong() {
        List<TrongLuong> sizes = iTrongLuongService.getData();
//        cbbLocKichCo.removeAllItems();
        for (TrongLuong sz : sizes) {
            cbbTrongLuong.addItem((sz.getTrongLuong()));
            cbbLocKichCo.addItem(sz.getTrongLuong());
        }
    }

    private int getIDTrongLuong() {
        List<TrongLuong> sizes = iTrongLuongService.getData();
        int id = -1;
        int index = cbbTrongLuong.getSelectedIndex();
        TrongLuong tg = sizes.get(index);
        id = tg.getID_TrongLuong();
        helper.setID_TrongLuong(id);
        System.out.println(tg.getTrongLuong());
        return id;
    }

    public int getIdFromName(String selectedName, Map<Integer, String> idNameMap) {
        try {
            for (Map.Entry<Integer, String> entry : idNameMap.entrySet()) {
                if (entry.getValue().equals(selectedName)) {
                    return entry.getKey();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void validateForm() {
        if (txtChuThich_SanPham.getText().isEmpty()) {
            jLabel1.setForeground(Color.red);
        }
    }

    public void fillDataTableCTSP() {
        modelCTSP.setRowCount(0);
        int i = 1;
        if (iCTSPService.getDataViewModels().isEmpty()) {
            return;
        }
        for (ChiTietSanPhamViewModels CTSP : iCTSPService.getDataViewModels()) {
            Object rowData[] = new Object[12];
            rowData[0] = CTSP.getID_ChiTietSanPham();
            rowData[1] = CTSP.getTenSanPham();
            rowData[2] = CTSP.getID_DanhMucSanPham();
            rowData[3] = CTSP.getID_Size();
            rowData[4] = CTSP.getID_MauSac();
            rowData[5] = CTSP.getID_ThuongHieu();
            rowData[6] = CTSP.getID_TrongLuong();
            rowData[7] = CTSP.getID_ChatLieu();
            rowData[8] = CTSP.getGia();
            rowData[9] = CTSP.getSoLuong();
            rowData[10] = CTSP.getTrangThai();
            rowData[11] = CTSP.getID_AnhSanPham();
            modelCTSP.addRow(rowData);
        }
    }
//    void xuatEXECL() {
//        try {
//            JFileChooser jFileChooser = new JFileChooser();
//            jFileChooser.showSaveDialog(this);
//            File saveFile = jFileChooser.getSelectedFile();
//
//            if (saveFile != null) {
//                saveFile = new File(saveFile.toString() + ".xlsx");
//                Workbook wb = new XSSFWorkbook();
//                Sheet sheet = wb.createSheet("Doanh thu");
//                 // Tạo một dòng mới ở vị trí đầu tiên
//                Row titleRow = sheet.createRow(0);
//                Cell titleCell = titleRow.createCell(0);
//                titleCell.setCellValue("Danh Sách Sản Phẩm");
//
//                Row rowCol = sheet.createRow(1);
//                for (int i = 0; i < tbCTSP.getColumnCount(); i++) {
//
//                    Cell cell = rowCol.createCell(i);// lui sang phải
//                    cell.setCellValue(tbCTSP.getColumnName(i));
//                }
//
//                for (int j = 0; j < tbCTSP.getRowCount(); j++) {
//                    Row row = sheet.createRow(j + 2);// lui xuong
//                    for (int k = 0; k < tbCTSP.getColumnCount(); k++) {
//                        Cell cell = row.createCell(k);// sang phải
//                        if (tbCTSP.getValueAt(j, k) != null) {
//                            cell.setCellValue(tbCTSP.getValueAt(j, k).toString());
//                        }
//                    }
//                }
//                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
//                wb.write(out);
//                wb.close();
//                out.close();
//                onpenFile(saveFile.toString());
//            } else {
//                DialogHelper.alert(this, "loiiiii");
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println(e);
//        } catch (IOException io) {
//            System.out.println(io);
//        }
//
//    }
    void onpenFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException e) {
            System.out.println("e");
        }

    }
    private void findDataToSQL(ChiTietSanPhamViewModels view) {
        Object[] rowData = new Object[12];
        rowData[0]  = view.getID_ChiTietSanPham();
        rowData[1]  = view.getTenSanPham();
        rowData[2]  = view.getID_DanhMucSanPham();
        rowData[3]  = view.getID_Size();
        rowData[4]  = view.getID_MauSac();
        rowData[5]  = view.getID_ThuongHieu();
        rowData[6]  = view.getID_TrongLuong();
        rowData[7]  = view.getID_ChatLieu();
        rowData[8]  = view.getGia();
        rowData[9]  = view.getSoLuong();
        rowData[10] = view.getTrangThai();
        rowData[11] = view.getID_AnhSanPham();
        modelCTSP.addRow(rowData);
    }

    public ChiTietSanPham getFormCTSP() {

        ChiTietSanPham CTSP = new ChiTietSanPham();
        
        try {
            int chatlieu = helper.getID_ChatLieu();
            CTSP.setID_ChatLieu(chatlieu);

            int danhmucSP = helper.getID_DmSP();
            CTSP.setID_DanhMucSanPham(danhmucSP);

            int mauSac = helper.getID_MauSac();
            CTSP.setID_MauSac(mauSac);

            int trongLuong = helper.getID_TrongLuong();
            CTSP.setID_TrongLuong(trongLuong);

            int thuongHieu = helper.getID_ThuongHieu();
            CTSP.setID_ThuongHieu(thuongHieu);

            int size = helper.getID_Size();
            CTSP.setID_Size(size);
            CTSP.setTenSanPham(txtTenSanPham_ChiTietSanPham.getText());
            CTSP.setGia(Integer.valueOf(txtGia.getText()));
            
            CTSP.setSoLuong(Integer.valueOf(txt_SoLuong.getText()));
            
            if (strHinhanh == null) {
                CTSP.setID_AnhSanPham("No Avatar");
            }else{
                CTSP.setID_AnhSanPham(strHinhanh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CTSP;
    }
    public void hinhAnh(){
        try {
            JFileChooser jfc = new JFileChooser("D:\\DuAn1_Github\\QuanLyBanGauBong\\src\\AnhSanPham");
            jfc.showOpenDialog(null);
            File file = jfc.getSelectedFile();
            Image img = ImageIO.read(file);
            strHinhanh = file.getName();
            lblHinhAnh.setText("");
            int width = lblHinhAnh.getWidth();
            int height = lblHinhAnh.getHeight();
            lblHinhAnh.setIcon(new ImageIcon(img.getScaledInstance(width, height, 0)));
        } catch (IOException ex) {
            System.out.println("Error: "+ ex.toString());
        }
    }    
    public ChiTietSanPham setFormCTSP() {
        ChiTietSanPham CTSP = new ChiTietSanPham();
        try {

            int chatlieu = helper.getID_ChatLieu();
            int danhmucSP = helper.getID_DmSP();
            int mauSac = helper.getID_MauSac();
            int trongLuong = helper.getID_TrongLuong();
            int thuongHieu = helper.getID_ThuongHieu();
            int size = helper.getID_Size();

            CTSP.setID_ChiTietSanPham(Integer.valueOf(txtMaSanPham_SanPham.getText()));
            CTSP.setID_ChatLieu(chatlieu);
            CTSP.setID_DanhMucSanPham(danhmucSP);
            CTSP.setID_Size(size);
            CTSP.setID_MauSac(mauSac);
            CTSP.setID_TrongLuong(trongLuong);
            CTSP.setID_ThuongHieu(thuongHieu);
            CTSP.setTenSanPham(txtTenSanPham_ChiTietSanPham.getText());
            CTSP.setGia(Integer.valueOf(txtGia.getText()));

            CTSP.setSoLuong(Integer.valueOf(txt_SoLuong.getText()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CTSP;
    }
    

//    public void setLabelImage(String imagePath) {
//        if (imagePath == null) {
//            imagePath = "d:\\pictures\\laptop\\default.jpg";
//        }
//        BufferedImage img = null;
//        try {
//            img = ImageIO.read(new File(imagePath));
//        } catch (IOException e) {
//
//        }
//        icon = new ImageIcon(img);
//
//        lblHinhAnh.setIcon(icon);
//    }
    public void SetCombobox(String cbbselected, JComboBox cbb) {
        for (int i = 0; i < cbb.getItemCount(); i++) {
            Object obj = cbb.getItemAt(i);
            if (obj != null) {
                displayvalueModel m = (displayvalueModel) obj;
                if (cbbselected.trim().equals(m.displayMember)) {
                    cbb.setSelectedItem(m);
                }
            }
        }
    }
    public ChatLieu getformChatLieu() {
        ChatLieu cl = new ChatLieu();
        cl.setTenChatLieu(txtChatLieu_ThemNhanh.getText());
        return cl;
    }
    private MauSac addNhanhMauSac() {
        MauSac ms = new MauSac();
        ms.setTenMauSac(txtThemNhanh_MauSac.getText());
        return ms;
    }    public TrongLuong getform() {
        TrongLuong nang = new TrongLuong();
        nang.setTrongLuong(txtTrongLuong_themnhanh.getText());
        return nang;
    }    public DanhMucSP getformDanhMuc() {
        DanhMucSP danhmuc = new DanhMucSP();
        danhmuc.setTenDanhMuc(txtDanhMuc_themnhanh.getText());
        return danhmuc;
    }    public Size getformSize() {
        Size Size = new Size();
        Size.setTenSize(txtSize_ThemNhanh.getText());
        Size.setMoTaSize(txtSize_Mota.getText());
        return Size;
    }    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ThuongHieuFrame = new javax.swing.JFrame();
        txtThuongHieu = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtMotaThuongHieu = new javax.swing.JTextField();
        ChatLieuFrame = new javax.swing.JFrame();
        jButton8 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtChatLieu_ThemNhanh = new javax.swing.JTextField();
        MauSacFrame = new javax.swing.JFrame();
        jButton5 = new javax.swing.JButton();
        txtThemNhanh_MauSac = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        DanhMucFrame = new javax.swing.JFrame();
        txtDanhMuc_themnhanh = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        SizeFrame = new javax.swing.JFrame();
        txtSize_ThemNhanh = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtSize_Mota = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        TrongLuongFrame = new javax.swing.JFrame();
        jLabel10 = new javax.swing.JLabel();
        txtTrongLuong_themnhanh = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbCTSP = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        rdTenSP = new javax.swing.JRadioButton();
        rdMaSP = new javax.swing.JRadioButton();
        lableTimKiem = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        cbbLocSize = new javax.swing.JComboBox<>();
        cbbLocChatLieu = new javax.swing.JComboBox<>();
        cbbLocKichCo = new javax.swing.JComboBox<>();
        cbbLoc_DanhMucSP = new javax.swing.JComboBox<>();
        cbbLocMauSac = new javax.swing.JComboBox<>();
        cbbLocThuongHieu = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        lableTimKiem2 = new javax.swing.JLabel();
        lableTimKiem3 = new javax.swing.JLabel();
        lableTimKiem4 = new javax.swing.JLabel();
        lableTimKiem5 = new javax.swing.JLabel();
        lableTimKiem6 = new javax.swing.JLabel();
        lableTimKiem7 = new javax.swing.JLabel();
        rdBoLoc = new javax.swing.JRadioButton();
        rdAll = new javax.swing.JRadioButton();
        lableTimKiem1 = new javax.swing.JLabel();
        lableTen_SanPham = new javax.swing.JLabel();
        txtTenSanPham_ChiTietSanPham = new javax.swing.JTextField();
        lableMa_SanPham = new javax.swing.JLabel();
        txtMaSanPham_SanPham = new javax.swing.JTextField();
        lableDanhMuc_SP = new javax.swing.JLabel();
        cbbdanhMucSP = new javax.swing.JComboBox<>();
        lableGia = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        lableSize = new javax.swing.JLabel();
        cbbSize = new javax.swing.JComboBox<>();
        lableTrongLuong = new javax.swing.JLabel();
        cbbTrongLuong = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txt_SoLuong = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        cbbMauSac = new javax.swing.JComboBox<>();
        lableThuongHieu1 = new javax.swing.JLabel();
        lableThuongHieu = new javax.swing.JLabel();
        cbbThuongHieu = new javax.swing.JComboBox<>();
        btnCreate = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        rdConHang = new javax.swing.JRadioButton();
        rdHetHang = new javax.swing.JRadioButton();
        jButton11 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtChuThich_SanPham = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        lblHinhAnh = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();

        ThuongHieuFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtThuongHieu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Thương Hiệu ");

        jButton1.setText("Thêm ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Thương Hiệu ");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setText("Mô tả");

        txtMotaThuongHieu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout ThuongHieuFrameLayout = new javax.swing.GroupLayout(ThuongHieuFrame.getContentPane());
        ThuongHieuFrame.getContentPane().setLayout(ThuongHieuFrameLayout);
        ThuongHieuFrameLayout.setHorizontalGroup(
            ThuongHieuFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThuongHieuFrameLayout.createSequentialGroup()
                .addGroup(ThuongHieuFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThuongHieuFrameLayout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ThuongHieuFrameLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ThuongHieuFrameLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(ThuongHieuFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ThuongHieuFrameLayout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtMotaThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ThuongHieuFrameLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        ThuongHieuFrameLayout.setVerticalGroup(
            ThuongHieuFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThuongHieuFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ThuongHieuFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ThuongHieuFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtMotaThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        ChatLieuFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton8.setText("Thêm");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setText("Tên chất liệu");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setText("Chất Liệu");

        txtChatLieu_ThemNhanh.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        javax.swing.GroupLayout ChatLieuFrameLayout = new javax.swing.GroupLayout(ChatLieuFrame.getContentPane());
        ChatLieuFrame.getContentPane().setLayout(ChatLieuFrameLayout);
        ChatLieuFrameLayout.setHorizontalGroup(
            ChatLieuFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChatLieuFrameLayout.createSequentialGroup()
                .addGroup(ChatLieuFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ChatLieuFrameLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(ChatLieuFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ChatLieuFrameLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(13, 13, 13)
                                .addComponent(txtChatLieu_ThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ChatLieuFrameLayout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(ChatLieuFrameLayout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        ChatLieuFrameLayout.setVerticalGroup(
            ChatLieuFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChatLieuFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addGroup(ChatLieuFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(txtChatLieu_ThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton8)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        MauSacFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton5.setText("Thêm Nhanh");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel5.setText("Màu Sắc");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Màu Sắc");

        javax.swing.GroupLayout MauSacFrameLayout = new javax.swing.GroupLayout(MauSacFrame.getContentPane());
        MauSacFrame.getContentPane().setLayout(MauSacFrameLayout);
        MauSacFrameLayout.setHorizontalGroup(
            MauSacFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MauSacFrameLayout.createSequentialGroup()
                .addGroup(MauSacFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MauSacFrameLayout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jButton5))
                    .addGroup(MauSacFrameLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(MauSacFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtThemNhanh_MauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        MauSacFrameLayout.setVerticalGroup(
            MauSacFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MauSacFrameLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(MauSacFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtThemNhanh_MauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addContainerGap())
        );

        DanhMucFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtDanhMuc_themnhanh.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jButton3.setText("Thêm");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Danh MucSP");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("Danh MucSP");

        javax.swing.GroupLayout DanhMucFrameLayout = new javax.swing.GroupLayout(DanhMucFrame.getContentPane());
        DanhMucFrame.getContentPane().setLayout(DanhMucFrameLayout);
        DanhMucFrameLayout.setHorizontalGroup(
            DanhMucFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DanhMucFrameLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtDanhMuc_themnhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
            .addGroup(DanhMucFrameLayout.createSequentialGroup()
                .addGroup(DanhMucFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DanhMucFrameLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(DanhMucFrameLayout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        DanhMucFrameLayout.setVerticalGroup(
            DanhMucFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DanhMucFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addGroup(DanhMucFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(txtDanhMuc_themnhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        SizeFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtSize_ThemNhanh.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jButton7.setText("Thêm");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setText("Size");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setText("Tên Size");

        txtSize_Mota.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setText("Mô tả size");

        javax.swing.GroupLayout SizeFrameLayout = new javax.swing.GroupLayout(SizeFrame.getContentPane());
        SizeFrame.getContentPane().setLayout(SizeFrameLayout);
        SizeFrameLayout.setHorizontalGroup(
            SizeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SizeFrameLayout.createSequentialGroup()
                .addGroup(SizeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SizeFrameLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel15))
                    .addGroup(SizeFrameLayout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SizeFrameLayout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SizeFrameLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(SizeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(SizeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SizeFrameLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSize_Mota, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(SizeFrameLayout.createSequentialGroup()
                            .addComponent(jLabel16)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtSize_ThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
        );
        SizeFrameLayout.setVerticalGroup(
            SizeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SizeFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addGroup(SizeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(txtSize_ThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(SizeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(txtSize_Mota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(22, 22, 22))
        );

        TrongLuongFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Trọng lượng");

        txtTrongLuong_themnhanh.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jButton2.setText("Thêm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Trọng lượng");

        javax.swing.GroupLayout TrongLuongFrameLayout = new javax.swing.GroupLayout(TrongLuongFrame.getContentPane());
        TrongLuongFrame.getContentPane().setLayout(TrongLuongFrameLayout);
        TrongLuongFrameLayout.setHorizontalGroup(
            TrongLuongFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TrongLuongFrameLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTrongLuong_themnhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
            .addGroup(TrongLuongFrameLayout.createSequentialGroup()
                .addGroup(TrongLuongFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TrongLuongFrameLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TrongLuongFrameLayout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        TrongLuongFrameLayout.setVerticalGroup(
            TrongLuongFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TrongLuongFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(TrongLuongFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(txtTrongLuong_themnhanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Zilla Slab Medium", 3, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PRODUCT DETAIL");

        tbCTSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sản Phẩm ", "Tên Sản Phẩm", "Danh Mục Sản Phẩm", "Size", "Màu Sắc", "Thương Hiệu", "Trọng Lượng", "Chất Liệu", "Giá", "Số Lượng", "Trạng Thái", "Ảnh Sản Phẩm"
            }
        ));
        tbCTSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCTSPMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbCTSPMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tbCTSP);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        rdTenSP.setText("Tên sản phẩm");

        rdMaSP.setText("Mã sản phẩm");

        lableTimKiem.setFont(new java.awt.Font("Source Sans Pro Black", 0, 14)); // NOI18N
        lableTimKiem.setForeground(new java.awt.Color(0, 51, 153));
        lableTimKiem.setText("Tìm Kiếm");

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        cbbLocSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cbbLocSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocSizeActionPerformed(evt);
            }
        });

        cbbLocChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cbbLocChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocChatLieuActionPerformed(evt);
            }
        });

        cbbLocKichCo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cbbLocKichCo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocKichCoActionPerformed(evt);
            }
        });

        cbbLoc_DanhMucSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cbbLoc_DanhMucSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLoc_DanhMucSPActionPerformed(evt);
            }
        });
        cbbLoc_DanhMucSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cbbLoc_DanhMucSPKeyReleased(evt);
            }
        });

        cbbLocMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cbbLocMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocMauSacActionPerformed(evt);
            }
        });

        cbbLocThuongHieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cbbLocThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocThuongHieuActionPerformed(evt);
            }
        });

        jPanel2.setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lableTimKiem2.setFont(new java.awt.Font("Source Sans Pro Black", 0, 14)); // NOI18N
        lableTimKiem2.setForeground(new java.awt.Color(0, 51, 153));
        lableTimKiem2.setText("Danh Mục SP");

        lableTimKiem3.setFont(new java.awt.Font("Source Sans Pro Black", 0, 14)); // NOI18N
        lableTimKiem3.setForeground(new java.awt.Color(0, 51, 153));
        lableTimKiem3.setText("Size");

        lableTimKiem4.setFont(new java.awt.Font("Source Sans Pro Black", 0, 14)); // NOI18N
        lableTimKiem4.setForeground(new java.awt.Color(0, 51, 153));
        lableTimKiem4.setText("Chất Liệu");

        lableTimKiem5.setFont(new java.awt.Font("Source Sans Pro Black", 0, 14)); // NOI18N
        lableTimKiem5.setForeground(new java.awt.Color(0, 51, 153));
        lableTimKiem5.setText("Kích cỡ");

        lableTimKiem6.setFont(new java.awt.Font("Source Sans Pro Black", 0, 14)); // NOI18N
        lableTimKiem6.setForeground(new java.awt.Color(0, 51, 153));
        lableTimKiem6.setText("Màu Sắc");

        lableTimKiem7.setFont(new java.awt.Font("Source Sans Pro Black", 0, 14)); // NOI18N
        lableTimKiem7.setForeground(new java.awt.Color(0, 51, 153));
        lableTimKiem7.setText("Thương Hiệu");

        rdBoLoc.setText("Kích Hoạt bộ lọc");

        rdAll.setText("Tất cả");

        lableTimKiem1.setFont(new java.awt.Font("Source Sans Pro Black", 0, 14)); // NOI18N
        lableTimKiem1.setForeground(new java.awt.Color(0, 51, 153));
        lableTimKiem1.setText("Bộ Lọc");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lableTimKiem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rdBoLoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rdMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rdAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rdTenSP))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lableTimKiem3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lableTimKiem2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lableTimKiem4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbbLoc_DanhMucSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbLocSize, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbLocChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lableTimKiem6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lableTimKiem7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lableTimKiem5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbbLocMauSac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbLocThuongHieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbLocKichCo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(25, 25, 25))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lableTimKiem1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lableTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdTenSP)
                    .addComponent(rdMaSP))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdBoLoc)
                    .addComponent(rdAll))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(lableTimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbLocKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbLoc_DanhMucSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lableTimKiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lableTimKiem5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbLocSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lableTimKiem3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lableTimKiem6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbLocMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbLocChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lableTimKiem4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lableTimKiem7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbLocThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        lableTen_SanPham.setText("Mã Sản Phẩm");

        txtTenSanPham_ChiTietSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSanPham_ChiTietSanPhamActionPerformed(evt);
            }
        });

        lableMa_SanPham.setText("Tên sản phẩm");

        txtMaSanPham_SanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSanPham_SanPhamActionPerformed(evt);
            }
        });

        lableDanhMuc_SP.setText("Danh Mục Sản Phẩm");

        cbbdanhMucSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbdanhMucSPMouseClicked(evt);
            }
        });
        cbbdanhMucSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbdanhMucSPActionPerformed(evt);
            }
        });

        lableGia.setText("Màu Sắc");

        txtGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaActionPerformed(evt);
            }
        });

        lableSize.setText("Size");

        cbbSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSizeActionPerformed(evt);
            }
        });

        lableTrongLuong.setText("Kích cỡ");

        cbbTrongLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTrongLuongActionPerformed(evt);
            }
        });

        jLabel2.setText("Thương Hiệu");

        txt_SoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SoLuongActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Source Sans Pro Black", 0, 14)); // NOI18N
        jButton10.setText("+");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        cbbMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMauSacActionPerformed(evt);
            }
        });

        lableThuongHieu1.setText("Giá");

        lableThuongHieu.setText("Số Lượng");

        cbbThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbThuongHieuActionPerformed(evt);
            }
        });

        btnCreate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setText("Trạng Thái");

        rdConHang.setSelected(true);
        rdConHang.setText("Còn Hàng");

        rdHetHang.setText("Hết Hàng");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdConHang, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rdHetHang, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(31, 31, 31))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(21, 21, 21))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(rdConHang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rdHetHang))
        );

        jButton11.setFont(new java.awt.Font("Source Sans Pro Black", 0, 14)); // NOI18N
        jButton11.setText("+");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Source Sans Pro Black", 0, 14)); // NOI18N
        jButton4.setText("+");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Source Sans Pro Black", 0, 14)); // NOI18N
        jButton12.setText("+");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton14.setFont(new java.awt.Font("Source Sans Pro Black", 0, 14)); // NOI18N
        jButton14.setText("+");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        txtChuThich_SanPham.setColumns(20);
        txtChuThich_SanPham.setRows(5);
        jScrollPane1.setViewportView(txtChuThich_SanPham);

        jLabel4.setText("Chú Thích Sản Phẩm");

        lblHinhAnh.setBackground(new java.awt.Color(255, 204, 204));
        lblHinhAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinhAnh.setText("Hinh Anh");
        lblHinhAnh.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblHinhAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhAnhMouseClicked(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton9.setText("Xuất File Exel");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lableGia, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(66, 66, 66)
                                        .addComponent(btnCreate)))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addComponent(btnUpdate)
                                        .addGap(69, 69, 69)
                                        .addComponent(btnDelete))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(cbbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(176, 176, 176)
                                .addComponent(jButton9)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaSanPham_SanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenSanPham_ChiTietSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lableTen_SanPham)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lableMa_SanPham)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(lableDanhMuc_SP))
                                    .addComponent(cbbdanhMucSP, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lableThuongHieu1)
                                .addGap(725, 725, 725)
                                .addComponent(jLabel4))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lableSize, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbbSize, 0, 179, Short.MAX_VALUE)
                                            .addComponent(lableTrongLuong)
                                            .addComponent(txtGia))
                                        .addGap(7, 7, 7))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(cbbTrongLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton11)
                                    .addComponent(jButton10, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton12, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lableThuongHieu)
                                            .addComponent(txt_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lableTen_SanPham)
                        .addComponent(lableThuongHieu1))
                    .addComponent(jLabel4))
                .addGap(4, 4, 4)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(lableThuongHieu)
                        .addGap(12, 12, 12)
                        .addComponent(txt_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMaSanPham_SanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addComponent(lableMa_SanPham)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTenSanPham_ChiTietSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(lableDanhMuc_SP)
                                .addGap(17, 17, 17)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbbdanhMucSP, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbTrongLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(lableSize)
                                .addGap(13, 13, 13)
                                .addComponent(cbbSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(lableTrongLuong)))))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(lableGia))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel2)))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addComponent(btnCreate))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnUpdate)
                                    .addComponent(btnDelete))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addComponent(jButton9)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(195, 358, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(390, 390, 390))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaSanPham_SanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSanPham_SanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSanPham_SanPhamActionPerformed

    private void txtTenSanPham_ChiTietSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSanPham_ChiTietSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSanPham_ChiTietSanPhamActionPerformed

    private void txt_SoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SoLuongActionPerformed

    private void tbCTSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCTSPMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbCTSPMouseClicked

    private void tbCTSPMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCTSPMouseReleased
        // TODO add your handling code here:
        String anh = null;
        int viTriDongVuaBam = tbCTSP.getSelectedRow();
        txtMaSanPham_SanPham.setText(tbCTSP.getValueAt(viTriDongVuaBam, 0).toString());
        txtTenSanPham_ChiTietSanPham.setText(tbCTSP.getValueAt(viTriDongVuaBam, 1).toString());
        cbbdanhMucSP.setSelectedItem(tbCTSP.getValueAt(viTriDongVuaBam, 2));
        cbbSize.setSelectedItem(tbCTSP.getValueAt(viTriDongVuaBam, 3));
        cbbMauSac.setSelectedItem(tbCTSP.getValueAt(viTriDongVuaBam, 4));
        cbbThuongHieu.setSelectedItem(tbCTSP.getValueAt(viTriDongVuaBam, 5));
        cbbTrongLuong.setSelectedItem(tbCTSP.getValueAt(viTriDongVuaBam, 6));
        cbbChatLieu.setSelectedItem(tbCTSP.getValueAt(viTriDongVuaBam, 7));
        txtGia.setText(tbCTSP.getValueAt(viTriDongVuaBam, 8).toString());
        txt_SoLuong.setText(tbCTSP.getValueAt(viTriDongVuaBam, 9).toString());
        if (tbCTSP.getValueAt(viTriDongVuaBam, 10).equals(0)) {
            rdHetHang.setSelected(true);
        } else if (tbCTSP.getValueAt(viTriDongVuaBam, 10).equals(1)) {
            rdConHang.setSelected(true);
        }
    }//GEN-LAST:event_tbCTSPMouseReleased
    public boolean validateFormCTSP(){
        if (txtGia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá chưa nhập");
            return false;
        }
        if (txtTenSanPham_ChiTietSanPham.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá chưa nhập");
            return false;
        }
        if (txt_SoLuong.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá chưa nhập");
            return false;
        }
        return true;
    }
    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // TODO add your handling code here:
        if (DialogHelper.confirm(this, "Thêm Sản Phẩm")) {
            if(validateFormCTSP()){
                iCTSPService.addSanPham(getFormCTSP());
                DialogHelper.alert(this, "Thêm Thành công");
                fillDataTableCTSP();
            }
        }
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (DialogHelper.confirm(this, "Sửa Sản Phẩm?")) {
            if (validateFormCTSP()) {
                iCTSPService.updateSanPham(setFormCTSP());
                DialogHelper.alert(jPanel1, "Sửa Thành công");
                fillDataTableCTSP();
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        if (DialogHelper.confirm(jPanel1, "Xoá sản phẩm?")) {
            if (validateFormCTSP()) {
                iCTSPService.temporaryDeleteSanPham(setFormCTSP());
                DialogHelper.alert(jPanel1, "Xoá thành công");
                fillDataTableCTSP();
            }
        }
        //        reset();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
        if (rdMaSP.isSelected()) {
            int ID = Integer.valueOf(txtTimKiem.getText().trim());
            List<ChiTietSanPhamViewModels> foundProducts = iCTSPService.findIDSP(ID);
            modelCTSP.setRowCount(0);
            for (ChiTietSanPhamViewModels product : foundProducts) {
                findDataToSQL(product);
            }
        } else if (rdTenSP.isSelected()) {
            String name = txtTimKiem.getText().trim();
            List<ChiTietSanPhamViewModels> foundProducts = iCTSPService.findTenSP(name);
            modelCTSP.setRowCount(0);
            for (ChiTietSanPhamViewModels product : foundProducts) {
                findDataToSQL(product);
            }
        }
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void cbbLocSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocSizeActionPerformed

        String name = getCBBSelected(cbbLocSize);
        if (rdBoLoc.isSelected()) {
            if (name.equals("Tất cả")) {
                System.out.println(name);
                fillDataTableCTSP();
            } else {
                List<ChiTietSanPhamViewModels> view = iCTSPService.locSize(name);
                modelCTSP.setRowCount(0);
                for (ChiTietSanPhamViewModels ctsp : view) {
                    findDataToSQL(ctsp);
                }
            }
        }
    }//GEN-LAST:event_cbbLocSizeActionPerformed

    private void cbbLocChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocChatLieuActionPerformed
        // TODO add your handling code here:
        String name = getCBBSelected(cbbLocChatLieu);
        if (rdBoLoc.isSelected()) {
            if (name.equals("Tất cả")) {
                System.out.println(name);
                fillDataTableCTSP();
            } else {
                List<ChiTietSanPhamViewModels> view = iCTSPService.locChatLieu(name);
                modelCTSP.setRowCount(0);
                for (ChiTietSanPhamViewModels ctsp : view) {
                    findDataToSQL(ctsp);
                }
            }
        }
    }//GEN-LAST:event_cbbLocChatLieuActionPerformed

    private void cbbLocKichCoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocKichCoActionPerformed
        // TODO add your handling code here:
        String name = getCBBSelected(cbbLocKichCo);
        if (rdBoLoc.isSelected()) {
            if (name.equals("Tất cả")) {
                System.out.println(name);
                fillDataTableCTSP();
            } else {
                List<ChiTietSanPhamViewModels> view = iCTSPService.locKichCo(name);
                modelCTSP.setRowCount(0);
                for (ChiTietSanPhamViewModels ctsp : view) {
                    findDataToSQL(ctsp);
                }
            }
        }
    }//GEN-LAST:event_cbbLocKichCoActionPerformed

    private void cbbLoc_DanhMucSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLoc_DanhMucSPActionPerformed
        // TODO add your handling code here:
        String name = getCBBSelected(cbbLoc_DanhMucSP);
        if (rdBoLoc.isSelected()) {
            if (name.equals("Tất cả")) {
                System.out.println(name);
                fillDataTableCTSP();
            } else {
                List<ChiTietSanPhamViewModels> view = iCTSPService.locDanhMucSP(name);
                modelCTSP.setRowCount(0);
                for (ChiTietSanPhamViewModels ctsp : view) {
                    findDataToSQL(ctsp);
                }
            }
        }
    }//GEN-LAST:event_cbbLoc_DanhMucSPActionPerformed

    private void cbbLoc_DanhMucSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbbLoc_DanhMucSPKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbLoc_DanhMucSPKeyReleased

    private void cbbLocMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocMauSacActionPerformed
        // TODO add your handling code here:
        String name = getCBBSelected(cbbLocMauSac);
        if (rdBoLoc.isSelected()) {
            if (name.equals("Tất cả")) {
                System.out.println(name);
                fillDataTableCTSP();
            } else {
                List<ChiTietSanPhamViewModels> view = iCTSPService.locMauSac(name);
                modelCTSP.setRowCount(0);
                for (ChiTietSanPhamViewModels ctsp : view) {
                    findDataToSQL(ctsp);
                }
            }
        }
    }//GEN-LAST:event_cbbLocMauSacActionPerformed

    private void cbbLocThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocThuongHieuActionPerformed
        // TODO add your handling code here:
        String name = getCBBSelected(cbbLocThuongHieu);
        if (rdBoLoc.isSelected()) {
            if (name.equals("Tất cả")) {
                System.out.println(name);
                fillDataTableCTSP();
            } else {
                List<ChiTietSanPhamViewModels> view = iCTSPService.locThuongHieu(name);
                modelCTSP.setRowCount(0);
                for (ChiTietSanPhamViewModels ctsp : view) {
                    findDataToSQL(ctsp);
                }
            }
        }
    }//GEN-LAST:event_cbbLocThuongHieuActionPerformed

    private void cbbThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbThuongHieuActionPerformed
        // TODO add your handling code here:
        //        getCbbThuongHieu();
        System.out.println(getIDThuongHieu());
    }//GEN-LAST:event_cbbThuongHieuActionPerformed

    private void cbbMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMauSacActionPerformed
        // TODO add your handling code here:
        //        getIDMau();
        System.out.println(getIDMauSac());
    }//GEN-LAST:event_cbbMauSacActionPerformed

    private void cbbSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSizeActionPerformed
        // TODO add your handling code here:
        System.out.println(getIDSize());
    }//GEN-LAST:event_cbbSizeActionPerformed

    private void cbbTrongLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTrongLuongActionPerformed
        // TODO add your handling code here:
        System.out.println(getIDTrongLuong());
    }//GEN-LAST:event_cbbTrongLuongActionPerformed

    private void cbbdanhMucSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbdanhMucSPMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbdanhMucSPMouseClicked

    private void cbbdanhMucSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbdanhMucSPActionPerformed
        // TODO add your handling code here:
        //        getIDDanhMucSP();
        System.out.println(getIDDanhMucSP());
    }//GEN-LAST:event_cbbdanhMucSPActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        DanhMucFrame.setSize(395, 170);
        DanhMucFrame.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        TrongLuongFrame.setSize(395, 170);
        TrongLuongFrame.setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        ThuongHieuFrame.setSize(395, 170);
        ThuongHieuFrame.setVisible(true);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        MauSacFrame.setSize(395, 170);
        MauSacFrame.setVisible(true);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        SizeFrame.setSize(395, 170);
        SizeFrame.setVisible(true);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        iChatLieuService.addChatLieu(getformChatLieu());
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String tenMau = txtThemNhanh_MauSac.getText();
        iMauSacService.addMauSac(addNhanhMauSac());
        reset();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        iDanhMucSPService.addDanhMucSP(getformDanhMuc());
        reset();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        iSizeService.add(getformSize());
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        iTrongLuongService.addTrongLuong(getform());
        getCbbTrongLuong();
    }//GEN-LAST:event_jButton2ActionPerformed
    public ThuongHieu getformThuonghieu(){
        ThuongHieu th = new ThuongHieu();
        th.setTenThuongHieu(txtThuongHieu.getText());
        th.setMoTaThuongHieu(txtMotaThuongHieu.getText());
        return th;
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        iThuongHieuService.addThuongHieu(getformThuonghieu());
        getCbbThuongHieu();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
//        xuatEXECL();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void lblHinhAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhAnhMouseClicked
        // TODO add your handling code here:
        hinhAnh();
    }//GEN-LAST:event_lblHinhAnhMouseClicked

    private void txtGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame ChatLieuFrame;
    private javax.swing.JFrame DanhMucFrame;
    private javax.swing.JFrame MauSacFrame;
    private javax.swing.JFrame SizeFrame;
    private javax.swing.JFrame ThuongHieuFrame;
    private javax.swing.JFrame TrongLuongFrame;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbbLocChatLieu;
    private javax.swing.JComboBox<String> cbbLocKichCo;
    private javax.swing.JComboBox<String> cbbLocMauSac;
    private javax.swing.JComboBox<String> cbbLocSize;
    private javax.swing.JComboBox<String> cbbLocThuongHieu;
    private javax.swing.JComboBox<String> cbbLoc_DanhMucSP;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JComboBox<String> cbbSize;
    private javax.swing.JComboBox<String> cbbThuongHieu;
    private javax.swing.JComboBox<String> cbbTrongLuong;
    private javax.swing.JComboBox<String> cbbdanhMucSP;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lableDanhMuc_SP;
    private javax.swing.JLabel lableGia;
    private javax.swing.JLabel lableMa_SanPham;
    private javax.swing.JLabel lableSize;
    private javax.swing.JLabel lableTen_SanPham;
    private javax.swing.JLabel lableThuongHieu;
    private javax.swing.JLabel lableThuongHieu1;
    private javax.swing.JLabel lableTimKiem;
    private javax.swing.JLabel lableTimKiem1;
    private javax.swing.JLabel lableTimKiem2;
    private javax.swing.JLabel lableTimKiem3;
    private javax.swing.JLabel lableTimKiem4;
    private javax.swing.JLabel lableTimKiem5;
    private javax.swing.JLabel lableTimKiem6;
    private javax.swing.JLabel lableTimKiem7;
    private javax.swing.JLabel lableTrongLuong;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JRadioButton rdAll;
    private javax.swing.JRadioButton rdBoLoc;
    private javax.swing.JRadioButton rdConHang;
    private javax.swing.JRadioButton rdHetHang;
    private javax.swing.JRadioButton rdMaSP;
    private javax.swing.JRadioButton rdTenSP;
    private javax.swing.JTable tbCTSP;
    private javax.swing.JTextField txtChatLieu_ThemNhanh;
    private javax.swing.JTextArea txtChuThich_SanPham;
    private javax.swing.JTextField txtDanhMuc_themnhanh;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMaSanPham_SanPham;
    private javax.swing.JTextField txtMotaThuongHieu;
    private javax.swing.JTextField txtSize_Mota;
    private javax.swing.JTextField txtSize_ThemNhanh;
    private javax.swing.JTextField txtTenSanPham_ChiTietSanPham;
    private javax.swing.JTextField txtThemNhanh_MauSac;
    private javax.swing.JTextField txtThuongHieu;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTrongLuong_themnhanh;
    private javax.swing.JTextField txt_SoLuong;
    // End of variables declaration//GEN-END:variables
}
