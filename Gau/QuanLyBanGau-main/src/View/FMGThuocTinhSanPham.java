/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;
import BUS.ChatLieuService;
import BUS.ChiTietSanPhamService;
import BUS.ChucVuService;
import BUS.DanhMucSPService;
import BUS.IChatLieuService;
import BUS.IChiTietSanPhamService;
import BUS.IChucVuService;
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
import Models.ChucVu;
import Models.DanhMucSP;
import Models.MauSac;
import Models.Size;
import Models.ThuongHieu;
import Models.TrongLuong;
import Utilities.DialogHelper;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author qivub
 */
public class FMGThuocTinhSanPham extends javax.swing.JPanel {
    DefaultTableModel modelChucVu;
    DefaultTableModel modelSanPham;
    DefaultTableModel modelChatlieu;
    DefaultTableModel modelMauSac;
    DefaultTableModel modeldanhMucSP;
    DefaultTableModel modelSize;
    DefaultTableModel modelTrongLuong;
    ISizeService iSizeService;
    IMauSacService iMauSacService;
    IDanhMucSPService iDanhMucSPService;
    IChatLieuService iChatLieuService;
    IChiTietSanPhamService iCTSPService;
    IThuongHieuService iThuongHieuService;
    ITrongLuongService itrongLuongService;
    IChucVuService icvService;
    DefaultTableModel modelThuongHieu;
    /**
     * Creates new form QuanLyThuocTinhSanPham
     */
    public FMGThuocTinhSanPham() {
        initComponents();
        iThuongHieuService = new ThuongHieuService();
        iMauSacService = new MauSacService();
//        iSanPhamService = new SanPhamService();
        iChatLieuService = new ChatLieuService();
        iCTSPService = new ChiTietSanPhamService();
        iDanhMucSPService = new DanhMucSPService();
        itrongLuongService = new TrongLuongService();
        icvService = new ChucVuService();
        iSizeService = new SizeService();
        modelTrongLuong = (DefaultTableModel) tbTrongLuong.getModel();
        modelChatlieu = (DefaultTableModel) tblChatLieu.getModel();
        modelMauSac = (DefaultTableModel) tblMauSac.getModel();
        modelSize = (DefaultTableModel) tbSize.getModel();
        modelThuongHieu = (DefaultTableModel) tbThuongHieu.getModel();
        modeldanhMucSP = (DefaultTableModel) tblDanhMucSP.getModel();
        fillDataTableMauSac();
        fillDataTableChatLieu();
        fillDataTableSize();
        filldatatableThuongHieu();
        fillDataTableTrongLuong();
        fillDataTableDanhMucSP();
    }
    public boolean validateSize(){
        if (txtTenSize.getText().isEmpty()) {
            DialogHelper.alert(jPanel1, "Thiếu tên");
            return false;
        }else if (txtMotasize.getText().isEmpty()) {
            DialogHelper.alert(jPanel1, "Thiếu mô tả");
            return false;
        }
        return true;
    }
    public boolean validateChatLieu(){
        if (txtTenChatlieu.getText().isEmpty()) {
            DialogHelper.alert(jPanel1, "Thiếu tên");
            return false;
        }else if (txtMota.getText().isEmpty()) {
            DialogHelper.alert(jPanel1, "Thiếu mô tả");
            return false;
        }
        return true;
    }
    
    public void kiemtra() {
        if (txtMaChatlieu.getText().isEmpty()) {
            jLabel5.setForeground(Color.red);
        }
        if (txtTenChatlieu.getText().isEmpty()) {
            lblten.setForeground(Color.red);
        }
        if (txtMota.getText().isEmpty()) {
            jLabel10.setForeground(Color.red);
        }
    }

    public void fillDataTableTrongLuong() {
        modelTrongLuong.setRowCount(0);
        if (itrongLuongService.getData().isEmpty()) {
            return;
        }
        for (TrongLuong tronglg : itrongLuongService.getData()) {
            Object[] rowData = {tronglg.getID_TrongLuong(), tronglg.getTrongLuong(), tronglg.getTrangThai()};

            modelTrongLuong.addRow(rowData);
        }
    }

    public void fillDataTableMauSac() {
        modelMauSac.setRowCount(0);
        if (iMauSacService.getData().isEmpty()) {
            return;
        }
        for (MauSac mausac : iMauSacService.getData()) {
            Object[] rowData = {mausac.getID_MauSac(), mausac.getTenMauSac(), mausac.getTrangThai()};

            modelMauSac.addRow(rowData);
        }
    }

    public MauSac getFormMauSac() {
        MauSac ms = new MauSac();
        try {
            ms.setTenMauSac(txtTenMauSac.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ms;
    }

    public MauSac setFormMauSac() {
        MauSac ms = new MauSac();
        try {
            ms.setID_MauSac(Integer.valueOf(txtIDMauSac.getText()));
            ms.setTenMauSac(txtTenMauSac.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ms;
    }

    public void MouseClickTBMauSac() {
        int row = tblMauSac.getSelectedRow();
        txtIDMauSac.setText(tblMauSac.getValueAt(row, 0).toString());
        txtTenMauSac.setText( tblMauSac.getValueAt(row, 1).toString());
        if (tblMauSac.getValueAt(row, 2).equals(0)) {
            rdHetHangMauSac1.setSelected(true);
        } else if (tblMauSac.getValueAt(row, 2).equals(1)) {
            rdConHangMauSac1.setSelected(true);
        }
    }

    public ChatLieu getFormChatLieu() {
        ChatLieu ms = new ChatLieu();
        try {
            ms.setTenChatLieu(txtTenChatlieu.getText());
            ms.setMoTaChatLieu(txtMota.getText());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ms;
    }

    public void fillDataTableChatLieu() {
        modelChatlieu.setRowCount(0);
        if (iChatLieuService.getData().isEmpty()) {
            return;
        }
        for (ChatLieu chatlieu : iChatLieuService.getData()) {
            Object[] rowData = {chatlieu.getID_ChatLieu(), chatlieu.getTenChatLieu(), chatlieu.getMoTaChatLieu(), chatlieu.getTrangThai()};
            modelChatlieu.addRow(rowData);
        }
    }

    public void fillDataTableSize() {
        modelSize.setRowCount(0);
        if (iSizeService.getData().isEmpty()) {
            return;
        }
        for (Size size : iSizeService.getData()) {
            Object[] rowData = {size.getID_Size(), size.getTenSize(), size.getMoTaSize(),size.getTrangThai()};
            modelSize.addRow(rowData);
        }
    }

   

    public ChatLieu setFormChatLieu() {
        ChatLieu ms = new ChatLieu();
        try {
            ms.setID_ChatLieu(Integer.valueOf(txtMaChatlieu.getText()));
            ms.setTenChatLieu(txtTenChatlieu.getText());
            ms.setMoTaChatLieu(txtMota.getText());
//            ms.setTrangThai(Integer.valueOf(txtTrangThai.getText()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ms;
    }

    public void MouseClickTBChatLieu() {
        int row = tblChatLieu.getSelectedRow();
        txtMaChatlieu.setText( tblChatLieu.getValueAt(row, 0).toString());
        txtTenChatlieu.setText( tblChatLieu.getValueAt(row, 1).toString());
        txtMota.setText((String) tblChatLieu.getValueAt(row, 2));
        if (tblChatLieu.getValueAt(row, 3).equals(0)) {
            rdHetHangChatLieu.setSelected(true);
        } else if (tblChatLieu.getValueAt(row, 3).equals(1)) {
            rdConHangChatLieu.setSelected(true);
        }
//        txtTrangThai.setText(String.valueOf((Integer) tblChatLieu.getValueAt(row, 3)));
    }

    public void MouseClickTBSize() {
        int row = tbSize.getSelectedRow();
        txtIDSize.setText(String.valueOf((Integer) tbSize.getValueAt(row, 0)));
        txtTenSize.setText((String) tbSize.getValueAt(row, 1));
        txtMotasize.setText((String) tbSize.getValueAt(row, 2));
        if (tbSize.getValueAt(row, 3).equals(0)) {
            rdHetHang.setSelected(true);
        } else if (tbSize.getValueAt(row, 3).equals(1)) {
            rdConHang.setSelected(true);
        }
//        txtTrangThai.setText(String.valueOf((Integer) tbSize.getValueAt(row, 3)));
    }

    public Size getFormSize() {
        Size ms = new Size();
        try {
            ms.setTenSize(txtTenSize.getText());
            ms.setMoTaSize(txtMotasize.getText());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ms;
    }

    public Size setFormSize() {
        Size ms = new Size();
        try {
            ms.setID_Size(Integer.valueOf(txtIDSize.getText()));
            ms.setTenSize(txtTenSize.getText());
            ms.setMoTaSize(txtMotasize.getText());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ms;
    }

    public void reset() {
        txtIDSize.setText("");
        txtTenSize.setText("");
        txtMotasize.setText("");
        txtMaChatlieu.setText("");
        txtTenChatlieu.setText("");
        txtMota.setText("");
//        txtTrangThai.setText("");
        txtIDMauSac.setText("");
        txtTenMauSac.setText("");
        txtMa_ThuongHieu.setText("");
        txtTen_ThuongHieu.setText("");
        txtMa_DanhMucSP.setText("");
        txtTen_DanhMucSP.setText("");
        txtMa_TrongLuong.setText("");
        txtTen_TrongLuong.setText("");
    }

    public boolean NhapDu() {
        if (txtTen_ThuongHieu.getText().isEmpty()) {
            lblTen_ThuongHieu.setForeground(Color.red);
        }
        if (txtMa_ThuongHieu.getText().isEmpty()) {
            lblMa_ThuongHieu.setForeground(Color.red);
        }
        return false;
    }

    public void filldatatableThuongHieu() {//hàm này để đưa dữ liệu hiển thị lên bảng 
        modelThuongHieu.setRowCount(0);//model khai báo và định nghĩa bên trên chỗ public class r
        int i = 0;
        if (iThuongHieuService.getData().isEmpty()) {
            return;
        }
        for (ThuongHieu thuonghieu : iThuongHieuService.getData()) {
            Object[] rowData = {//dưa dữ liệu vào bảng, thêm vào theo cột lần lượt từ trên xuống
                i++,
                thuonghieu.getID_ThuongHieu(),
                thuonghieu.getTenThuongHieu(),
                thuonghieu.getMoTaThuongHieu(),
                thuonghieu.getTrangThai()};
            modelThuongHieu.addRow(rowData);
        }
    }

    public ThuongHieu getForm() {//hàm này để lấy dữ liệu set vào đb của insert
        ThuongHieu th = new ThuongHieu();
        th.setTenThuongHieu(txtTen_ThuongHieu.getText());
        th.setMoTaThuongHieu(txtMoTaTh.getText());
        if (rdConHang.isSelected()) {
            th.setTrangThai(1);
        }
        if (rdHetHang.isSelected()) {
            th.setTrangThai(0);
        }
        return th;
    }

    public ThuongHieu setForm() {//hàm này để lấy dữ liệu set vào đb của update và delete
        ThuongHieu th = new ThuongHieu();
        th.setID_ThuongHieu(Integer.valueOf(txtMa_ThuongHieu.getText()));
        th.setTenThuongHieu(txtTen_ThuongHieu.getText());
        th.setMoTaThuongHieu(txtMoTaTh.getText());
        if (rdConHang.isSelected()) {
            th.setTrangThai(1);
        }
        if (rdHetHang.isSelected()) {
            th.setTrangThai(0);
        }
        return th;
    }
    public DanhMucSP setformDanhMucSP() {
        DanhMucSP dm = new DanhMucSP();
        dm.setID_Danhmucsanpham(Integer.valueOf(txtMa_DanhMucSP.getText()));
        dm.setTenDanhMuc(txtTen_DanhMucSP.getText());
        if (rdConHangDanhmucSP.isSelected()) {
            dm.setTrangThai(1);
        }else if (rdHetHangDanhMucSP.isSelected()) {
            dm.setTrangThai(0);
        }
        return dm;
    }

    public DanhMucSP getformDanhMucSP() {
        DanhMucSP dm = new DanhMucSP();
        dm.setTenDanhMuc(txtTen_DanhMucSP.getText());
        return dm;
    }
    public TrongLuong getFormTrongLuong() {
        TrongLuong kg = new TrongLuong();
//        kg.setID_TrongLuong(Integer.valueOf(txtMa_TrongLuong.getText()));
        kg.setTrongLuong(txtTen_TrongLuong.getText());
        return kg;
    }

    public TrongLuong setFormTrongLuong() {
        TrongLuong kg = new TrongLuong();
        kg.setID_TrongLuong(Integer.valueOf(txtMa_TrongLuong.getText()));
        kg.setTrongLuong(txtTen_TrongLuong.getText());
        return kg;
    }              
    public boolean validateMauSac(){
        if(txtTenMauSac.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Vui lòng nhập lên màu sắc");
            return false;
        }
        return true;
    }     
    private void findDataToSQLSize(Size view) {
        Object[] rowData = new Object[11];
        rowData[0] = view.getID_Size();
        rowData[1] = view.getTenSize();
        rowData[2] = view.getMoTaSize();
        rowData[3] = view.getTrangThai();
        modelSize.addRow(rowData);
    }   
    private void findDataToSQLDanhMucSP(DanhMucSP view) {
        Object[] rowData = new Object[11];
        rowData[0] = view.getID_Danhmucsanpham();
        rowData[1] = view.getTenDanhMuc();
        rowData[2] = view.getTrangThai();
        modeldanhMucSP.addRow(rowData);
    }
    private void findDataToSQLChatLieu(ChatLieu view) {
        Object[] rowData = new Object[11];
        rowData[0] = view.getID_ChatLieu();
        rowData[1] = view.getTenChatLieu();
        rowData[2] = view.getMoTaChatLieu();
        rowData[3] = view.getTrangThai();
        modelChatlieu.addRow(rowData);
    }
    public void fillDataTableDanhMucSP() {
        modeldanhMucSP.setRowCount(0);
        if (iDanhMucSPService.getData().isEmpty()) {
            return;
        }
        for (DanhMucSP dmsp : iDanhMucSPService.getData()) {
            Object[] rowData = {
                dmsp.getID_Danhmucsanpham(),
                dmsp.getTenDanhMuc(),
                dmsp.getTrangThai()};
            modeldanhMucSP.addRow(rowData);
        }
    }   
    private void findDataToSQLThuongHieu(ThuongHieu view) {
        int c = 1;
        Object[] rowData = new Object[11];
        rowData[0] = c++;
        rowData[1] = view.getID_ThuongHieu();
        rowData[2] = view.getTenThuongHieu();
        rowData[3] = view.getMoTaThuongHieu();
        rowData[4] = view.getTrangThai();
        modelThuongHieu.addRow(rowData);
    }
    public void fillDataTableThuongHieu() {
        modelThuongHieu.setRowCount(0);
        if (iThuongHieuService.getData().isEmpty()) {
            return;
        }
        for (ThuongHieu th : iThuongHieuService.getData()) {
            Object[] rowData = {
                th.getID_ThuongHieu(),
                th.getTenThuongHieu(),
                th.getTrangThai()};
            modelThuongHieu.addRow(rowData);
        }
    }    private void findDataToSQLMauSac(MauSac view) {
        Object[] rowData = new Object[11];
        rowData[0] = view.getID_MauSac();
        rowData[1] = view.getTenMauSac();
        rowData[2] = view.getTrangThai();
        modelMauSac.addRow(rowData);
    }

    public void fillDatatableMauSac() {
        modelMauSac.setRowCount(0);
        if (iMauSacService.getData().isEmpty()) {
            return;
        }
        for (MauSac ms : iMauSacService.getData()) {
            Object[] rowData = {
                ms.getID_MauSac(),
                ms.getTenMauSac(),
                ms.getTrangThai()};
            modelMauSac.addRow(rowData);
        }
    }    private void findDataToSQLTrongLuong(TrongLuong view) {
        Object[] rowData = new Object[11];
        rowData[0] = view.getID_TrongLuong();
        rowData[1] = view.getTrongLuong();
        rowData[2] = view.getTrangThai();
        modelTrongLuong.addRow(rowData);
    }

    public void fillDatatableTrongLuong() {
        modelTrongLuong.setRowCount(0);
        if (itrongLuongService.getData().isEmpty()) {
            return;
        }
        for (TrongLuong tl : itrongLuongService.getData()) {
            Object[] rowData = {
                tl.getID_TrongLuong(),
                tl.getTrongLuong(),
                tl.getTrangThai()};
            modelMauSac.addRow(rowData);
        }
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
        jPanel1 = new javax.swing.JPanel();
        pnTrongLuong = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbTrongLuong = new javax.swing.JTable();
        cbbLocTrongLuong = new javax.swing.JComboBox<>();
        jButton12 = new javax.swing.JButton();
        btnDeleteTrongLuong = new javax.swing.JButton();
        btnUpdateTrongLuong = new javax.swing.JButton();
        btnCreate_DanhMucSP1 = new javax.swing.JButton();
        txtTen_TrongLuong = new javax.swing.JTextField();
        lblTen_TrongLuong = new javax.swing.JLabel();
        lblMa_TrongLuong = new javax.swing.JLabel();
        txtMa_TrongLuong = new javax.swing.JTextField();
        rdConHangTrongLuong = new javax.swing.JRadioButton();
        rdHetHangTrongLuong = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtTimkiemTrongLuong = new javax.swing.JTextField();
        btnTimKiem4 = new javax.swing.JButton();
        pnChatLieu = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lblten = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rdHetHangChatLieu = new javax.swing.JRadioButton();
        rdConHangChatLieu = new javax.swing.JRadioButton();
        txtMota = new javax.swing.JTextField();
        txtTenChatlieu = new javax.swing.JTextField();
        txtMaChatlieu = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblChatLieu = new javax.swing.JTable();
        tblThuongHieu7 = new javax.swing.JLabel();
        txtTimkiemChatLieu = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        btnTimKiem3 = new javax.swing.JButton();
        pnDanhMucSanPham = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        btnUpdate_DanhMucSP = new javax.swing.JButton();
        btnCreate_DanhMucSP = new javax.swing.JButton();
        btnDelete_DanhMucSP = new javax.swing.JButton();
        txtMa_DanhMucSP = new javax.swing.JTextField();
        lblMa_DanhMucSP = new javax.swing.JLabel();
        lblTen_DanhMucSP = new javax.swing.JLabel();
        rdConHangDanhmucSP = new javax.swing.JRadioButton();
        rdHetHangDanhMucSP = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDanhMucSP = new javax.swing.JTable();
        txtTen_DanhMucSP = new javax.swing.JTextField();
        jButton15 = new javax.swing.JButton();
        cbbLocDanhMucsp = new javax.swing.JComboBox<>();
        btnTimKiem2 = new javax.swing.JButton();
        txtTimKiemDanhMucSanPham = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        pnSize = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbSize = new javax.swing.JTable();
        txtMotasize = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtIDSize = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        txtTenSize = new javax.swing.JTextField();
        rdHetHangSize = new javax.swing.JRadioButton();
        rdConHangSize = new javax.swing.JRadioButton();
        tblThuongHieu8 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtTimKiemSize = new javax.swing.JTextField();
        btnTimKiem1 = new javax.swing.JButton();
        pnMauSac = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtIDMauSac = new javax.swing.JTextField();
        txtTenMauSac = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblMauSac = new javax.swing.JTable();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        rdConHangMauSac1 = new javax.swing.JRadioButton();
        rdHetHangMauSac1 = new javax.swing.JRadioButton();
        cbbLocMauSac1 = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        txtTimKiemMauSac = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        btnTimKiem = new javax.swing.JButton();
        pnThuongHieu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbThuongHieu = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtTimKiemThuongHieu1 = new javax.swing.JTextField();
        ckbTimKiem_ThuongHieu = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        cbbLocThuongHieu = new javax.swing.JComboBox<>();
        rdHetHang = new javax.swing.JRadioButton();
        rdConHang = new javax.swing.JRadioButton();
        tblThuongHieu = new javax.swing.JLabel();
        txtMa_ThuongHieu = new javax.swing.JTextField();
        lblMa_ThuongHieu = new javax.swing.JLabel();
        lblTen_ThuongHieu = new javax.swing.JLabel();
        txtTen_ThuongHieu = new javax.swing.JTextField();
        lblTen_ThuongHieu1 = new javax.swing.JLabel();
        txtMoTaTh = new javax.swing.JTextField();
        btnCreate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        tblThuongHieu1 = new javax.swing.JLabel();
        tblThuongHieu2 = new javax.swing.JLabel();
        tblThuongHieu3 = new javax.swing.JLabel();
        tblThuongHieu4 = new javax.swing.JLabel();
        tblThuongHieu5 = new javax.swing.JLabel();
        tblThuongHieu6 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.CardLayout());

        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tbTrongLuong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Tên Trọng Lượng", "Trạng Thai"
            }
        ));
        tbTrongLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbTrongLuongMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tbTrongLuong);

        cbbLocTrongLuong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "Tất Cả" }));
        cbbLocTrongLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocTrongLuongActionPerformed(evt);
            }
        });

        jButton12.setText("Reset");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        btnDeleteTrongLuong.setText("Delete");
        btnDeleteTrongLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteTrongLuongActionPerformed(evt);
            }
        });

        btnUpdateTrongLuong.setText("Update");
        btnUpdateTrongLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateTrongLuongActionPerformed(evt);
            }
        });

        btnCreate_DanhMucSP1.setText("Create");
        btnCreate_DanhMucSP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreate_DanhMucSP1ActionPerformed(evt);
            }
        });

        lblTen_TrongLuong.setText("Trọng Lượng");

        lblMa_TrongLuong.setText("Mã Trọng Lượng");

        txtMa_TrongLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMa_TrongLuongActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdConHangTrongLuong);
        rdConHangTrongLuong.setText("Còn Hàng");

        buttonGroup1.add(rdHetHangTrongLuong);
        rdHetHangTrongLuong.setText("Hết Hàng");

        jLabel13.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel13.setText("TRỌNG LƯỢNG");

        jLabel22.setText("Tìm kiếm");

        btnTimKiem4.setText("Tìm kiếm");
        btnTimKiem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiem4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(jLabel13))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtMa_TrongLuong)
                                        .addComponent(txtTen_TrongLuong, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                            .addComponent(btnCreate_DanhMucSP1)
                                            .addGap(57, 57, 57)
                                            .addComponent(btnUpdateTrongLuong)
                                            .addGap(56, 56, 56)
                                            .addComponent(btnDeleteTrongLuong)
                                            .addGap(64, 64, 64)
                                            .addComponent(jButton12)))
                                    .addComponent(lblTen_TrongLuong)
                                    .addComponent(lblMa_TrongLuong))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdConHangTrongLuong, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(rdHetHangTrongLuong, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbbLocTrongLuong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addComponent(txtTimkiemTrongLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTimKiem4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimkiemTrongLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(btnTimKiem4))
                .addGap(23, 23, 23)
                .addComponent(lblMa_TrongLuong)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMa_TrongLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdConHangTrongLuong))
                .addGap(26, 26, 26)
                .addComponent(lblTen_TrongLuong)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTen_TrongLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdHetHangTrongLuong))
                .addGap(30, 30, 30)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbLocTrongLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12)
                    .addComponent(btnUpdateTrongLuong)
                    .addComponent(btnCreate_DanhMucSP1)
                    .addComponent(btnDeleteTrongLuong))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(237, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 75, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnTrongLuongLayout = new javax.swing.GroupLayout(pnTrongLuong);
        pnTrongLuong.setLayout(pnTrongLuongLayout);
        pnTrongLuongLayout.setHorizontalGroup(
            pnTrongLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTrongLuongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        pnTrongLuongLayout.setVerticalGroup(
            pnTrongLuongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTrongLuongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 807, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(pnTrongLuong, "card3");

        jLabel5.setText("mã");

        lblten.setText("tên");

        jLabel10.setText("mô tả");

        jLabel4.setText("trạng thái");

        buttonGroup1.add(rdHetHangChatLieu);
        rdHetHangChatLieu.setText("Hết hàng");

        buttonGroup1.add(rdConHangChatLieu);
        rdConHangChatLieu.setText("Còn hàng");

        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Sửa ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton13.setText("Reset");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton3.setText("Xoá");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        tblChatLieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Tên Chất liệu ", "Mô tả", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChatLieuMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblChatLieu);

        tblThuongHieu7.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        tblThuongHieu7.setText("CHẤT LIỆU");

        jLabel21.setText("Tìm kiếm");

        btnTimKiem3.setText("Tìm kiếm");
        btnTimKiem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiem3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnChatLieuLayout = new javax.swing.GroupLayout(pnChatLieu);
        pnChatLieu.setLayout(pnChatLieuLayout);
        pnChatLieuLayout.setHorizontalGroup(
            pnChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnChatLieuLayout.createSequentialGroup()
                .addGroup(pnChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnChatLieuLayout.createSequentialGroup()
                        .addGap(282, 282, 282)
                        .addComponent(tblThuongHieu7, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnChatLieuLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnChatLieuLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(pnChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel21)
                                .addGroup(pnChatLieuLayout.createSequentialGroup()
                                    .addGap(84, 84, 84)
                                    .addComponent(txtTimkiemChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnTimKiem3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnChatLieuLayout.createSequentialGroup()
                                .addGroup(pnChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnChatLieuLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(73, 73, 73)
                                        .addComponent(txtMaChatlieu, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnChatLieuLayout.createSequentialGroup()
                                        .addComponent(lblten)
                                        .addGap(73, 73, 73)
                                        .addComponent(txtTenChatlieu, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnChatLieuLayout.createSequentialGroup()
                                        .addGroup(pnChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel4))
                                        .addGap(39, 39, 39)
                                        .addGroup(pnChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtMota, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(pnChatLieuLayout.createSequentialGroup()
                                                .addComponent(rdHetHangChatLieu)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(rdConHangChatLieu)))))
                                .addGap(76, 76, 76)
                                .addGroup(pnChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pnChatLieuLayout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addGap(40, 40, 40)
                                        .addComponent(jButton2))
                                    .addGroup(pnChatLieuLayout.createSequentialGroup()
                                        .addComponent(jButton3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton13)))))))
                .addContainerGap(201, Short.MAX_VALUE))
        );
        pnChatLieuLayout.setVerticalGroup(
            pnChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnChatLieuLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(tblThuongHieu7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimkiemChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(btnTimKiem3))
                .addGap(42, 42, 42)
                .addGroup(pnChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(pnChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMaChatlieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)))
                .addGap(6, 6, 6)
                .addGroup(pnChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblten)
                    .addComponent(txtTenChatlieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnChatLieuLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(pnChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(txtMota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdHetHangChatLieu)
                            .addComponent(jLabel4)
                            .addComponent(rdConHangChatLieu)))
                    .addGroup(pnChatLieuLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(pnChatLieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton13))))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(347, Short.MAX_VALUE))
        );

        jPanel1.add(pnChatLieu, "card4");

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnUpdate_DanhMucSP.setText("Update");
        btnUpdate_DanhMucSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate_DanhMucSPActionPerformed(evt);
            }
        });

        btnCreate_DanhMucSP.setText("Create");
        btnCreate_DanhMucSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreate_DanhMucSPActionPerformed(evt);
            }
        });

        btnDelete_DanhMucSP.setText("Delete");
        btnDelete_DanhMucSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete_DanhMucSPActionPerformed(evt);
            }
        });

        txtMa_DanhMucSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMa_DanhMucSPActionPerformed(evt);
            }
        });

        lblMa_DanhMucSP.setText("Mã Danh Mục Sản Phẩm");

        lblTen_DanhMucSP.setText("Tên Danh Mục Sản Phẩm");

        buttonGroup1.add(rdConHangDanhmucSP);
        rdConHangDanhmucSP.setText("Còn Hàng");
        rdConHangDanhmucSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdConHangDanhmucSPActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdHetHangDanhMucSP);
        rdHetHangDanhMucSP.setText("Hết Hàng");
        rdHetHangDanhMucSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdHetHangDanhMucSPActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 204, 204));
        jLabel3.setFont(new java.awt.Font("Source Sans Pro Black", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("DANH MỤC SẢN PHẨM");

        tblDanhMucSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Tên Danh Mục Sản Phẩm", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhMucSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhMucSPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDanhMucSP);

        jButton15.setText("reset");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        cbbLocDanhMucsp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "0", "1" }));
        cbbLocDanhMucsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocDanhMucspActionPerformed(evt);
            }
        });

        btnTimKiem2.setText("Tìm kiếm");
        btnTimKiem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiem2ActionPerformed(evt);
            }
        });

        jLabel20.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rdConHangDanhmucSP))
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel12Layout.createSequentialGroup()
                                    .addGap(31, 31, 31)
                                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel12Layout.createSequentialGroup()
                                            .addComponent(btnUpdate_DanhMucSP)
                                            .addGap(48, 48, 48)
                                            .addComponent(btnDelete_DanhMucSP)
                                            .addGap(55, 55, 55)
                                            .addComponent(jButton15))
                                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtMa_DanhMucSP, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTen_DanhMucSP, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblMa_DanhMucSP)
                                            .addComponent(lblTen_DanhMucSP)))
                                    .addGap(67, 67, 67)
                                    .addComponent(rdHetHangDanhMucSP))
                                .addGroup(jPanel12Layout.createSequentialGroup()
                                    .addGap(34, 34, 34)
                                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel12Layout.createSequentialGroup()
                                            .addComponent(btnCreate_DanhMucSP)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbbLocDanhMucsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(309, 309, 309)
                        .addComponent(jLabel3)))
                .addContainerGap(110, Short.MAX_VALUE))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(txtTimKiemDanhMucSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimKiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemDanhMucSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(btnTimKiem2))
                .addGap(20, 20, 20)
                .addComponent(lblMa_DanhMucSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMa_DanhMucSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdConHangDanhmucSP))
                .addGap(18, 18, 18)
                .addComponent(lblTen_DanhMucSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTen_DanhMucSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdHetHangDanhMucSP, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbLocDanhMucsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15)
                    .addComponent(btnDelete_DanhMucSP)
                    .addComponent(btnUpdate_DanhMucSP)
                    .addComponent(btnCreate_DanhMucSP))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnDanhMucSanPhamLayout = new javax.swing.GroupLayout(pnDanhMucSanPham);
        pnDanhMucSanPham.setLayout(pnDanhMucSanPhamLayout);
        pnDanhMucSanPhamLayout.setHorizontalGroup(
            pnDanhMucSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDanhMucSanPhamLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );
        pnDanhMucSanPhamLayout.setVerticalGroup(
            pnDanhMucSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDanhMucSanPhamLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(250, Short.MAX_VALUE))
        );

        jPanel1.add(pnDanhMucSanPham, "card5");

        tbSize.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Tên", "Mô Tả", "Trạng thái"
            }
        ));
        tbSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSizeMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbSize);

        jLabel9.setText("Mô Tả");

        jLabel8.setText("Tên Size");

        jLabel7.setText("ID");

        txtIDSize.setEditable(false);

        jButton8.setText("thêm");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("sửa");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("reset");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("xoá");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdHetHangSize);
        rdHetHangSize.setText("Hết Hàng");

        buttonGroup1.add(rdConHangSize);
        rdConHangSize.setText("Còn Hàng");
        rdConHangSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdConHangSizeActionPerformed(evt);
            }
        });

        tblThuongHieu8.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        tblThuongHieu8.setText("SIZE");

        jLabel19.setText("Tìm kiếm");

        btnTimKiem1.setText("Tìm kiếm");
        btnTimKiem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiem1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGap(62, 62, 62)
                                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel13Layout.createSequentialGroup()
                                                .addComponent(rdHetHangSize)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(rdConHangSize))
                                            .addGroup(jPanel13Layout.createSequentialGroup()
                                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtMotasize, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtIDSize, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtTenSize, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(27, 27, 27)
                                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 64, Short.MAX_VALUE)))))
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGap(195, 195, 195)
                                        .addComponent(tblThuongHieu8, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel19))))
                .addGap(0, 71, Short.MAX_VALUE))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(txtTimKiemSize, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTimKiem1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(tblThuongHieu8, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(btnTimKiem1))
                .addGap(31, 31, 31)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtIDSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtTenSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtMotasize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdHetHangSize)
                            .addComponent(rdConHangSize)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton8)
                            .addComponent(jButton9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton11)
                            .addComponent(jButton10))))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnSizeLayout = new javax.swing.GroupLayout(pnSize);
        pnSize.setLayout(pnSizeLayout);
        pnSizeLayout.setHorizontalGroup(
            pnSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnSizeLayout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );
        pnSizeLayout.setVerticalGroup(
            pnSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSizeLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(210, Short.MAX_VALUE))
        );

        jPanel1.add(pnSize, "card6");

        jPanel14.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel11.setText("ID Màu sắc");

        jLabel16.setText("Tên Màu sắc");

        txtIDMauSac.setEditable(false);
        txtIDMauSac.setBackground(new java.awt.Color(255, 255, 255));
        txtIDMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDMauSacActionPerformed(evt);
            }
        });

        jScrollPane7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane7MouseClicked(evt);
            }
        });

        tblMauSac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID Màu Sắc", "Tên Màu Sắc", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMauSacMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblMauSac);

        jButton16.setText("Thêm");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setText("Xóa");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setText("Sửa");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setText("Làm Mới");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdConHangMauSac1);
        rdConHangMauSac1.setText("Còn hàng");

        buttonGroup1.add(rdHetHangMauSac1);
        rdHetHangMauSac1.setText("Hết hàng");

        cbbLocMauSac1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "Tất cả" }));
        cbbLocMauSac1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocMauSac1ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel17.setText("MÀU SẮC");

        jLabel18.setText("Tìm kiếm");

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(380, 380, 380)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jButton16)
                                .addGap(18, 18, 18)
                                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(jButton19)
                                .addGap(72, 72, 72)
                                .addComponent(cbbLocMauSac1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel16))
                        .addGap(61, 61, 61)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(18, 18, 18)
                                .addComponent(txtTimKiemMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtTenMauSac)
                            .addComponent(txtIDMauSac))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdHetHangMauSac1)
                            .addComponent(rdConHangMauSac1))))
                .addGap(105, 105, 105))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(btnTimKiem))
                .addGap(39, 39, 39)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtIDMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdConHangMauSac1))
                .addGap(33, 33, 33)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtTenMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdHetHangMauSac1))
                .addGap(27, 27, 27)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton17)
                    .addComponent(jButton16)
                    .addComponent(jButton18)
                    .addComponent(cbbLocMauSac1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton19))
                .addGap(79, 79, 79)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(225, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnMauSacLayout = new javax.swing.GroupLayout(pnMauSac);
        pnMauSac.setLayout(pnMauSacLayout);
        pnMauSacLayout.setHorizontalGroup(
            pnMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnMauSacLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnMauSacLayout.setVerticalGroup(
            pnMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(pnMauSac, "card7");

        tbThuongHieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Thương Hiệu", "Tên Thuong Hiệu", "Mô tả", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbThuongHieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbThuongHieuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbThuongHieu);

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel14.setText("TÌM KIẾM");

        txtTimKiemThuongHieu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemThuongHieu1ActionPerformed(evt);
            }
        });

        ckbTimKiem_ThuongHieu.setText("Tìm kiếm");
        ckbTimKiem_ThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbTimKiem_ThuongHieuActionPerformed(evt);
            }
        });

        jLabel15.setText("Tên thương hiệu");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtTimKiemThuongHieu1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ckbTimKiem_ThuongHieu)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addGap(108, 108, 108))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiemThuongHieu1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckbTimKiem_ThuongHieu)
                .addGap(18, 18, 18))
        );

        jButton14.setText("Làm Mới");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        cbbLocThuongHieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "Tất cả" }));
        cbbLocThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocThuongHieuActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdHetHang);
        rdHetHang.setText("Hết Hàng");

        buttonGroup1.add(rdConHang);
        rdConHang.setText("Còn Hàng");

        tblThuongHieu.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        tblThuongHieu.setText("THƯƠNG HIỆU");

        lblMa_ThuongHieu.setText("Mã Thương Hiệu");

        lblTen_ThuongHieu.setText("Tên Thương Hiệu");

        txtTen_ThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTen_ThuongHieuActionPerformed(evt);
            }
        });

        lblTen_ThuongHieu1.setText("Mô tả");

        btnCreate.setText("Thêm");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnThuongHieuLayout = new javax.swing.GroupLayout(pnThuongHieu);
        pnThuongHieu.setLayout(pnThuongHieuLayout);
        pnThuongHieuLayout.setHorizontalGroup(
            pnThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThuongHieuLayout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addGroup(pnThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnThuongHieuLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnThuongHieuLayout.createSequentialGroup()
                        .addGroup(pnThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTen_ThuongHieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblTen_ThuongHieu1)
                                .addComponent(txtMoTaTh, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pnThuongHieuLayout.createSequentialGroup()
                                    .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(47, 47, 47)
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblMa_ThuongHieu)
                                .addComponent(txtMa_ThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTen_ThuongHieu)))
                        .addGap(18, 18, 18)
                        .addGroup(pnThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdConHang)
                            .addComponent(jButton14)
                            .addGroup(pnThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cbbLocThuongHieu, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rdHetHang, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(161, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnThuongHieuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tblThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(345, 345, 345))
        );
        pnThuongHieuLayout.setVerticalGroup(
            pnThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThuongHieuLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(tblThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(lblMa_ThuongHieu)
                .addGap(18, 18, 18)
                .addGroup(pnThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnThuongHieuLayout.createSequentialGroup()
                        .addGroup(pnThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnThuongHieuLayout.createSequentialGroup()
                                .addComponent(txtMa_ThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTen_ThuongHieu)
                                .addGap(18, 18, 18)
                                .addComponent(txtTen_ThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnThuongHieuLayout.createSequentialGroup()
                                .addComponent(rdConHang)
                                .addGap(39, 39, 39)
                                .addComponent(rdHetHang)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTen_ThuongHieu1)
                        .addGap(13, 13, 13)
                        .addGroup(pnThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMoTaTh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbLocThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(pnThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCreate)
                            .addComponent(btnUpdate)
                            .addComponent(btnDelete)
                            .addComponent(jButton14)))
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(299, Short.MAX_VALUE))
        );

        jPanel1.add(pnThuongHieu, "card8");

        tblThuongHieu1.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        tblThuongHieu1.setText("THƯƠNG HIỆU");
        tblThuongHieu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuongHieu1MouseClicked(evt);
            }
        });

        tblThuongHieu2.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        tblThuongHieu2.setText("TRỌNG LƯỢNG");
        tblThuongHieu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblThuongHieu2MouseReleased(evt);
            }
        });

        tblThuongHieu3.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        tblThuongHieu3.setText("MÀU SẮC");
        tblThuongHieu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuongHieu3MouseClicked(evt);
            }
        });

        tblThuongHieu4.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        tblThuongHieu4.setText("DANH MỤC SP");
        tblThuongHieu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuongHieu4MouseClicked(evt);
            }
        });

        tblThuongHieu5.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        tblThuongHieu5.setText("SIZE");
        tblThuongHieu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuongHieu5MouseClicked(evt);
            }
        });

        tblThuongHieu6.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        tblThuongHieu6.setText("CHẤT LIỆU");
        tblThuongHieu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuongHieu6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tblThuongHieu1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tblThuongHieu2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tblThuongHieu3, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tblThuongHieu4, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tblThuongHieu5, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tblThuongHieu6, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(tblThuongHieu1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tblThuongHieu2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tblThuongHieu3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tblThuongHieu4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tblThuongHieu6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tblThuongHieu5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbTrongLuongMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTrongLuongMouseReleased
        // TODO add your handling code here:
        int row = tbTrongLuong.getSelectedRow();
        txtMa_TrongLuong.setText(tbTrongLuong.getValueAt(row, 0).toString());
        txtTen_TrongLuong.setText(tbTrongLuong.getValueAt(row, 1).toString());
        if (tbTrongLuong.getValueAt(row, 2).equals(0)) {
            rdHetHangTrongLuong.setSelected(true);
        } else if (tbTrongLuong.getValueAt(row, 2).equals(1)) {
            rdConHangTrongLuong.setSelected(true);
        }
        //        txtMa_TrongLuong.setText(tbTrongLuong.getValueAt(row, 0).toString());
    }//GEN-LAST:event_tbTrongLuongMouseReleased

    private void cbbLocTrongLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocTrongLuongActionPerformed
        // TODO add your handling code here:
        if (cbbLocTrongLuong.getSelectedItem().equals("0")) {
            int trangthai = 0;
            modelTrongLuong.setRowCount(0);
            List<TrongLuong> thuonghieu = itrongLuongService.getDataByTrangThai(trangthai);
            for(TrongLuong ms : thuonghieu){
                findDataToSQLTrongLuong(ms);
            }
        }else if(cbbLocTrongLuong.getSelectedItem().equals("1")){
            int trangthai = 1;
            modelTrongLuong.setRowCount(0);
            List<TrongLuong> thuonghieu = itrongLuongService.getDataByTrangThai(trangthai);
            for(TrongLuong ms : thuonghieu){
                findDataToSQLTrongLuong(ms);
            }
        }else if(cbbLocTrongLuong.getSelectedItem().equals("Tất cả")){
            fillDataTableTrongLuong();
        }
    }//GEN-LAST:event_cbbLocTrongLuongActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void btnDeleteTrongLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteTrongLuongActionPerformed
        // TODO add your handling code here:
        itrongLuongService.deleteTrongLuong(setFormTrongLuong());//truyền setform để lấy dữ liệu
        fillDataTableTrongLuong();
        JOptionPane.showMessageDialog(this, "Xóa Thành công");
    }//GEN-LAST:event_btnDeleteTrongLuongActionPerformed

    private void btnUpdateTrongLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateTrongLuongActionPerformed
        // TODO add your handling code here:
        itrongLuongService.updateTrongLuong(setFormTrongLuong());//truyền setform để lấy dữ liệu
        fillDataTableTrongLuong();
        JOptionPane.showMessageDialog(this, "Update Thành công");
    }//GEN-LAST:event_btnUpdateTrongLuongActionPerformed

    private void btnCreate_DanhMucSP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreate_DanhMucSP1ActionPerformed
        // TODO add your handling code here:
        if(DialogHelper.confirm(jPanel1, "Thêm trọng lượng mới?")){
            if(validateChatLieu()){
                itrongLuongService.addTrongLuong(getFormTrongLuong());
                fillDataTableTrongLuong();
                JOptionPane.showMessageDialog(this, "Thêm thành công");
            }
        }
    }//GEN-LAST:event_btnCreate_DanhMucSP1ActionPerformed

    private void txtMa_TrongLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMa_TrongLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMa_TrongLuongActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(DialogHelper.confirm(this, "Xác nhận ?")){
            if (validateChatLieu()) {
                iChatLieuService.addChatLieu(getFormChatLieu());
                fillDataTableChatLieu();
                JOptionPane.showConfirmDialog(this, "Thêm Thành công");
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (DialogHelper.confirm(this, "Xác nhận ?")) {
            if (validateChatLieu()) {
                iChatLieuService.updateChatLieu(setFormChatLieu());
                fillDataTableChatLieu();
                JOptionPane.showMessageDialog(this, "Update Thành công");
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(DialogHelper.confirm(this, "Xác nhận ?")){
            if (validateChatLieu()) {
                iChatLieuService.temporaryDeleteChatLieu(setFormChatLieu());
                fillDataTableChatLieu();
                JOptionPane.showMessageDialog(this, "Xoá Thành công");
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tblChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChatLieuMouseClicked
        MouseClickTBChatLieu();
    }//GEN-LAST:event_tblChatLieuMouseClicked

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void btnUpdate_DanhMucSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate_DanhMucSPActionPerformed
        // TODO add your handling code here:
        if(DialogHelper.confirm(jPanel1, "Sửa?")){
        iDanhMucSPService.updateDanhMucSP(setformDanhMucSP());
        fillDataTableDanhMucSP();
        JOptionPane.showMessageDialog(this, "Update Thành công");
        }
    }//GEN-LAST:event_btnUpdate_DanhMucSPActionPerformed

    private void btnCreate_DanhMucSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreate_DanhMucSPActionPerformed
        // TODO add your handling code here:
if(DialogHelper.confirm(jPanel1, "Thêm?")){
        iDanhMucSPService.addDanhMucSP(getformDanhMucSP());
        fillDataTableDanhMucSP();
        JOptionPane.showMessageDialog(this, "Thêm Thành công");
}
    }//GEN-LAST:event_btnCreate_DanhMucSPActionPerformed

    private void btnDelete_DanhMucSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete_DanhMucSPActionPerformed
        // TODO add your handling code here:
        if(DialogHelper.confirm(jPanel1, "Xoá?")){
            iDanhMucSPService.temporaryDanhMucSP(setformDanhMucSP());
            fillDataTableDanhMucSP();
            JOptionPane.showMessageDialog(this, "Xoá Thành công");
        }
    }//GEN-LAST:event_btnDelete_DanhMucSPActionPerformed

    private void txtMa_DanhMucSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMa_DanhMucSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMa_DanhMucSPActionPerformed

    private void rdConHangDanhmucSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdConHangDanhmucSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdConHangDanhmucSPActionPerformed

    private void rdHetHangDanhMucSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdHetHangDanhMucSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdHetHangDanhMucSPActionPerformed

    private void tblDanhMucSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhMucSPMouseClicked
        // TODO add your handling code here:
        int row = tblDanhMucSP.getSelectedRow();
        txtMa_DanhMucSP.setText(tblDanhMucSP.getValueAt(row, 0).toString());
        txtTen_DanhMucSP.setText(tblDanhMucSP.getValueAt(row, 1).toString());
        if (tblDanhMucSP.getValueAt(row, 2).equals(0)) {
            rdHetHangDanhMucSP.setSelected(true);
        } else if (tblDanhMucSP.getValueAt(row, 2).equals(1)) {
            rdConHangDanhmucSP.setSelected(true);
        }
    }//GEN-LAST:event_tblDanhMucSPMouseClicked

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void cbbLocDanhMucspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocDanhMucspActionPerformed
        // TODO add your handling code here:
        if (cbbLocDanhMucsp.getSelectedItem().equals("0")) {//equal : so sánh chuỗi
            int trangthai = 0;
            modeldanhMucSP.setRowCount(0);
            List<DanhMucSP> danhmucsp = iDanhMucSPService.getDataByTrangThai(trangthai);
            for(DanhMucSP dmsp : danhmucsp){
                findDataToSQLDanhMucSP(dmsp);
            }
        }else if(cbbLocDanhMucsp.getSelectedItem().equals("1")){
            int trangthai = 1;
            modeldanhMucSP.setRowCount(0);
            List<DanhMucSP> danhmucsp = iDanhMucSPService.getDataByTrangThai(trangthai);
            for(DanhMucSP dmsp : danhmucsp){
                findDataToSQLDanhMucSP(dmsp);
            }
        }else if(cbbLocDanhMucsp.getSelectedItem().equals("Tất cả")){
            fillDataTableDanhMucSP();
        }
    }//GEN-LAST:event_cbbLocDanhMucspActionPerformed

    private void tbSizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSizeMouseClicked
        // TODO add your handling code here:
        int row = tbSize.getSelectedRow();
        txtIDSize.setText(String.valueOf((Integer) tbSize.getValueAt(row, 0)));
        txtTenSize.setText((String) tbSize.getValueAt(row, 1));
        txtMotasize.setText(tbSize.getValueAt(row, 2).toString());
        if (tbSize.getValueAt(row, 3).equals(0)) {
            rdConHangSize.setSelected(true);
        } else if (tbSize.getValueAt(row, 3).equals(1)) {
            rdHetHangSize.setSelected(true);
        }
    }//GEN-LAST:event_tbSizeMouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if(DialogHelper.confirm(this, "Xác nhận ?")){
            if(validateSize()){
                iSizeService.add(getFormSize());
                fillDataTableSize();
                JOptionPane.showMessageDialog(this, "Thêm thành công");
            }
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if(DialogHelper.confirm(this, "Xác nhận ?")){
            if (validateSize()) {
                iSizeService.update(setFormSize());
                fillDataTableSize();
                JOptionPane.showMessageDialog(this, "Sửa thành công");
            }

        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here: reset();
        reset();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        if(DialogHelper.confirm(this, "Xác nhận ?")){
            if (validateSize()) {
                    iSizeService.temporaryDelete(setFormSize());
                    fillDataTableSize();
                    JOptionPane.showMessageDialog(this, "Xoá Thành công");
                
            }

        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void rdConHangSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdConHangSizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdConHangSizeActionPerformed

    private void tbThuongHieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbThuongHieuMouseClicked
        // TODO add your handling code here:
        int row = tbThuongHieu.getSelectedRow();
        txtMa_ThuongHieu.setText(tbThuongHieu.getValueAt(row, 1).toString());
        txtTen_ThuongHieu.setText(tbThuongHieu.getValueAt(row, 2).toString());
        txtMoTaTh.setText(tbThuongHieu.getValueAt(row, 3).toString());
        if (tbThuongHieu.getValueAt(row, 4).equals(0)) {
            rdHetHang.setSelected(true);
        } else if (tbThuongHieu.getValueAt(row, 4).equals(1)) {
            rdConHang.setSelected(true);
        }
    }//GEN-LAST:event_tbThuongHieuMouseClicked

    private void txtTimKiemThuongHieu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemThuongHieu1ActionPerformed
        // TODO add your handling code here:
        String name = txtTimKiemThuongHieu1.getText();
        iThuongHieuService.timkiem(name);
        List<ThuongHieu> chucvu = iThuongHieuService.timkiem(name);
        modelThuongHieu.setRowCount(0);
        for (ThuongHieu product : chucvu) {
            findDataToSQLThuongHieu(product);
        }
    }//GEN-LAST:event_txtTimKiemThuongHieu1ActionPerformed

    private void ckbTimKiem_ThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbTimKiem_ThuongHieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ckbTimKiem_ThuongHieuActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here: reset();
        reset();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void cbbLocThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocThuongHieuActionPerformed

        if (cbbLocThuongHieu.getSelectedItem().equals("0")) {
            int trangthai = 0;
            modelThuongHieu.setRowCount(0);
            List<ThuongHieu> thuonghieu = iThuongHieuService.getDataByTrangThai(trangthai);
            for(ThuongHieu th : thuonghieu){
                findDataToSQLThuongHieu(th);
            }
        }else if(cbbLocThuongHieu.getSelectedItem().equals("1")){
            int trangthai = 1;
            modelThuongHieu.setRowCount(0);
            List<ThuongHieu> thuonghieu = iThuongHieuService.getDataByTrangThai(trangthai);
            for(ThuongHieu th : thuonghieu){
                findDataToSQLThuongHieu(th);
            }
        }else if(cbbLocThuongHieu.getSelectedItem().equals("Tất cả")){
            filldatatableThuongHieu();
        }
    }//GEN-LAST:event_cbbLocThuongHieuActionPerformed

    private void txtTen_ThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTen_ThuongHieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTen_ThuongHieuActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // TODO add your handling code here:
        iThuongHieuService.addThuongHieu(getForm());//truyền getform vào để lấy đc dữ liệu
        filldatatableThuongHieu();
        JOptionPane.showMessageDialog(this, "Thêm Thành công");
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:set
        iThuongHieuService.temporaryDeleteThuongHieu(setForm());
        filldatatableThuongHieu();
        JOptionPane.showMessageDialog(this, "Xoá Thành công");
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        iThuongHieuService.updateThuongHieu(setForm());//truyền setform để lấy dữ liệu
        filldatatableThuongHieu();
        JOptionPane.showMessageDialog(this, "Update Thành công");
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void txtIDMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDMauSacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDMauSacActionPerformed

    private void tblMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMauSacMouseClicked
        // TODO add your handling code here:
        MouseClickTBMauSac();
    }//GEN-LAST:event_tblMauSacMouseClicked

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        if(validateMauSac()){
            if(DialogHelper.confirm(jPanel1, "Bạn có muốn thêm màu sắc?")){
                iMauSacService.addMauSac(getFormMauSac());
                fillDataTableMauSac();
                JOptionPane.showMessageDialog(this, "Thêm Thành công");
            }

        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        if (validateMauSac()) {
            if (DialogHelper.confirm(jPanel1, "Bạn có muốn xoá màu sắc?")) {
                iMauSacService.deleteMauSac(setFormMauSac());
                txtIDMauSac.setText("");
                txtTenMauSac.setText("");
                JOptionPane.showMessageDialog(this, "Xoá Thành công");
                fillDataTableMauSac();
            }
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        if (validateMauSac()) {
            if (DialogHelper.confirm(jPanel1, "Bạn có muốn sửa màu sắc?")) {
                iMauSacService.updateMauSac(setFormMauSac());
                fillDataTableMauSac();
                JOptionPane.showMessageDialog(this, "Update Thành công");
            }
        }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_jButton19ActionPerformed

    private void cbbLocMauSac1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocMauSac1ActionPerformed
        // TODO add your handling code here:
        if (cbbLocMauSac1.getSelectedItem().equals("0")) {
            int trangthai = 0;
            modelMauSac.setRowCount(0);
            List<MauSac> mausac = iMauSacService.getDataByTrangThai(trangthai);
            for(MauSac ms : mausac){
                findDataToSQLMauSac(ms);
            }
        }else if(cbbLocMauSac1.getSelectedItem().equals("1")){
            int trangthai = 1;
            modelMauSac.setRowCount(0);
            List<MauSac> mausac = iMauSacService.getDataByTrangThai(trangthai);
            for(MauSac ms : mausac){
                findDataToSQLMauSac(ms);
            }
        }else if(cbbLocMauSac1.getSelectedItem().equals("Tất cả")){
            fillDataTableMauSac();
        }
    }//GEN-LAST:event_cbbLocMauSac1ActionPerformed

    private void tblThuongHieu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuongHieu1MouseClicked
        // TODO add your handling code here:
        pnThuongHieu.show(true);
        pnTrongLuong.show(false);    
        pnChatLieu.show(false);
        pnDanhMucSanPham.show(false);
        pnMauSac.show(false);
        pnSize.show(false);
    }//GEN-LAST:event_tblThuongHieu1MouseClicked

    private void tblThuongHieu2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuongHieu2MouseReleased
        pnThuongHieu.show(false);
        pnTrongLuong.show(true);    
        pnChatLieu.show(false);
        pnDanhMucSanPham.show(false);
        pnMauSac.show(false);
        pnSize.show(false);       // TODO add your handling code here:
    }//GEN-LAST:event_tblThuongHieu2MouseReleased

    private void tblThuongHieu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuongHieu3MouseClicked
        // TODO add your handling code here:
        pnThuongHieu.show(false);
        pnTrongLuong.show(false);    
        pnChatLieu.show(false);
        pnDanhMucSanPham.show(false);
        pnMauSac.show(true);
        pnSize.show(false);   
    }//GEN-LAST:event_tblThuongHieu3MouseClicked

    private void tblThuongHieu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuongHieu4MouseClicked
        // TODO add your handling code here:
         pnThuongHieu.show(false);
        pnTrongLuong.show(false);    
        pnChatLieu.show(false);
        pnDanhMucSanPham.show(true);
        pnMauSac.show(false);
        pnSize.show(false);   
    }//GEN-LAST:event_tblThuongHieu4MouseClicked

    private void tblThuongHieu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuongHieu6MouseClicked
        // TODO add your handling code here:
         pnThuongHieu.show(false);
        pnTrongLuong.show(false);    
        pnChatLieu.show(true);
        pnDanhMucSanPham.show(false);
        pnMauSac.show(false);
        pnSize.show(false);   
    }//GEN-LAST:event_tblThuongHieu6MouseClicked

    private void tblThuongHieu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuongHieu5MouseClicked
        // TODO add your handling code here:
         pnThuongHieu.show(false);
        pnTrongLuong.show(false);    
        pnChatLieu.show(false);
        pnDanhMucSanPham.show(false);
        pnMauSac.show(false);
        pnSize.show(true);   
    }//GEN-LAST:event_tblThuongHieu5MouseClicked

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        String name = txtTimKiemMauSac.getText();
        iMauSacService.timkiem(name);
        List<MauSac> chucvu = iMauSacService.timkiem(name);
        modelMauSac.setRowCount(0);
        for (MauSac product : chucvu) {
            findDataToSQLMauSac(product);
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnTimKiem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiem1ActionPerformed
        // TODO add your handling code here:
        String name = txtTimKiemSize.getText();//lấy ra, rồi chạy tìm kiếm theo tên
        iSizeService.timkiem(name);
        List<Size> chucvu = iSizeService.timkiem(name);
        modelSize.setRowCount(0);
        for (Size product : chucvu) {
            findDataToSQLSize(product);
        }
    }//GEN-LAST:event_btnTimKiem1ActionPerformed

    private void btnTimKiem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiem2ActionPerformed
        // TODO add your handling code here:
         String name = txtTimKiemDanhMucSanPham.getText();
        iDanhMucSPService.timkiem(name);
        List<DanhMucSP> chucvu = iDanhMucSPService.timkiem(name);
        modeldanhMucSP.setRowCount(0);
        for (DanhMucSP product : chucvu) {
            findDataToSQLDanhMucSP(product);
        }
    }//GEN-LAST:event_btnTimKiem2ActionPerformed

    private void btnTimKiem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiem3ActionPerformed
        // TODO add your handling code here:
        String name = txtTimkiemChatLieu.getText();
        iChatLieuService.timkiem(name);
        List<ChatLieu> chatlieu = iChatLieuService.timkiem(name);
        modelChatlieu.setRowCount(0);
        for (ChatLieu product : chatlieu) {
            findDataToSQLChatLieu(product);
        }
    }//GEN-LAST:event_btnTimKiem3ActionPerformed

    private void btnTimKiem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiem4ActionPerformed
        // TODO add your handling code here:
        String name = txtTimkiemTrongLuong.getText();
        itrongLuongService.timkiem(name);
        List<TrongLuong> chucvu = itrongLuongService.timkiem(name);
        modelTrongLuong.setRowCount(0);
        for (TrongLuong product : chucvu) {
            findDataToSQLTrongLuong(product);
        }
    }//GEN-LAST:event_btnTimKiem4ActionPerformed

    private void jScrollPane7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane7MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jScrollPane7MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnCreate_DanhMucSP;
    private javax.swing.JButton btnCreate_DanhMucSP1;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDeleteTrongLuong;
    private javax.swing.JButton btnDelete_DanhMucSP;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTimKiem1;
    private javax.swing.JButton btnTimKiem2;
    private javax.swing.JButton btnTimKiem3;
    private javax.swing.JButton btnTimKiem4;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdateTrongLuong;
    private javax.swing.JButton btnUpdate_DanhMucSP;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbLocDanhMucsp;
    private javax.swing.JComboBox<String> cbbLocMauSac1;
    private javax.swing.JComboBox<String> cbbLocThuongHieu;
    private javax.swing.JComboBox<String> cbbLocTrongLuong;
    private javax.swing.JCheckBox ckbTimKiem_ThuongHieu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lblMa_DanhMucSP;
    private javax.swing.JLabel lblMa_ThuongHieu;
    private javax.swing.JLabel lblMa_TrongLuong;
    private javax.swing.JLabel lblTen_DanhMucSP;
    private javax.swing.JLabel lblTen_ThuongHieu;
    private javax.swing.JLabel lblTen_ThuongHieu1;
    private javax.swing.JLabel lblTen_TrongLuong;
    private javax.swing.JLabel lblten;
    private javax.swing.JPanel pnChatLieu;
    private javax.swing.JPanel pnDanhMucSanPham;
    private javax.swing.JPanel pnMauSac;
    private javax.swing.JPanel pnSize;
    private javax.swing.JPanel pnThuongHieu;
    private javax.swing.JPanel pnTrongLuong;
    private javax.swing.JRadioButton rdConHang;
    private javax.swing.JRadioButton rdConHangChatLieu;
    private javax.swing.JRadioButton rdConHangDanhmucSP;
    private javax.swing.JRadioButton rdConHangMauSac1;
    private javax.swing.JRadioButton rdConHangSize;
    private javax.swing.JRadioButton rdConHangTrongLuong;
    private javax.swing.JRadioButton rdHetHang;
    private javax.swing.JRadioButton rdHetHangChatLieu;
    private javax.swing.JRadioButton rdHetHangDanhMucSP;
    private javax.swing.JRadioButton rdHetHangMauSac1;
    private javax.swing.JRadioButton rdHetHangSize;
    private javax.swing.JRadioButton rdHetHangTrongLuong;
    private javax.swing.JTable tbSize;
    private javax.swing.JTable tbThuongHieu;
    private javax.swing.JTable tbTrongLuong;
    public static javax.swing.JTable tblChatLieu;
    private javax.swing.JTable tblDanhMucSP;
    private javax.swing.JTable tblMauSac;
    private javax.swing.JLabel tblThuongHieu;
    private javax.swing.JLabel tblThuongHieu1;
    private javax.swing.JLabel tblThuongHieu2;
    private javax.swing.JLabel tblThuongHieu3;
    private javax.swing.JLabel tblThuongHieu4;
    private javax.swing.JLabel tblThuongHieu5;
    private javax.swing.JLabel tblThuongHieu6;
    private javax.swing.JLabel tblThuongHieu7;
    private javax.swing.JLabel tblThuongHieu8;
    private javax.swing.JTextField txtIDMauSac;
    private javax.swing.JTextField txtIDSize;
    private javax.swing.JTextField txtMaChatlieu;
    private javax.swing.JTextField txtMa_DanhMucSP;
    private javax.swing.JTextField txtMa_ThuongHieu;
    private javax.swing.JTextField txtMa_TrongLuong;
    private javax.swing.JTextField txtMoTaTh;
    private javax.swing.JTextField txtMota;
    private javax.swing.JTextField txtMotasize;
    private javax.swing.JTextField txtTenChatlieu;
    private javax.swing.JTextField txtTenMauSac;
    private javax.swing.JTextField txtTenSize;
    private javax.swing.JTextField txtTen_DanhMucSP;
    private javax.swing.JTextField txtTen_ThuongHieu;
    private javax.swing.JTextField txtTen_TrongLuong;
    private javax.swing.JTextField txtTimKiemDanhMucSanPham;
    private javax.swing.JTextField txtTimKiemMauSac;
    private javax.swing.JTextField txtTimKiemSize;
    private javax.swing.JTextField txtTimKiemThuongHieu1;
    private javax.swing.JTextField txtTimkiemChatLieu;
    private javax.swing.JTextField txtTimkiemTrongLuong;
    // End of variables declaration//GEN-END:variables
}
