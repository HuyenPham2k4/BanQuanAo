/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import BUS.ChiTietHoaDonService;
import BUS.ChiTietSanPhamService;
import BUS.HoaDonService;
import BUS.IChiTietHoaDonService;
import BUS.IChiTietSanPhamService;
import BUS.IHoaDonService;
import BUS.IKhachHangService;
import BUS.INhanVienService;
import BUS.KhachHangService;
import BUS.NhanVienService;
import Models.ChiTietHoaDon;
import Models.ChiTietSanPham;
import Models.HoaDon;
import Models.KhachHang;
import Models.NhanVien;
import Utilities.DialogHelper;
import Utilities.SessionHelper;
import Utilities.displayvalueModel;
import ViewModels.ChiTietSanPhamViewModels;
import ViewModels.HoaDonViewmodels;
import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author qivub
 */
public class FMGQuanLyBanHang extends javax.swing.JPanel {
    DialogHelper dig = new DialogHelper();
    private Map<Integer, String> _idNameMap = new HashMap<>();
    DefaultTableModel modelHoaDon;
    DefaultTableModel modelCTHD;
    IKhachHangService iKhachHangService;
    IHoaDonService iHoaDonService;
    IChiTietSanPhamService iCTSPService;
    IChiTietHoaDonService iCTHDService;
    INhanVienService iNVservice;
    ChiTietHoaDon _CTHD;
    HoaDon _hd;
    KhachHang _Kh;
    ChiTietSanPham _CTSP;
    NhanVien _Nv;
    SessionHelper helper;
    DefaultTableModel modelCTSP;
    /**
     * Creates new form QuanLyBanHangPanel
     */
    public FMGQuanLyBanHang() {
        initComponents();
        iCTSPService = new ChiTietSanPhamService();
        modelCTSP = (DefaultTableModel) tblCTSP.getModel();
        modelHoaDon = (DefaultTableModel) tblHoaDon_HoaDon.getModel();
        modelCTHD = (DefaultTableModel) tblCTHoaDon_ChiTietHoaDon.getModel();
        iHoaDonService = new HoaDonService();
        iKhachHangService = new KhachHangService();
        iCTHDService = new ChiTietHoaDonService();
        iNVservice = new NhanVienService();
        helper = new SessionHelper();
        getCbbKhachHang();
        getCbbNhanVien();
        fillDataTableHoaDon();
        fillDataChiTietHoaDon();
        fillDataTableCTSP();
        _hd = new HoaDon();
        _Kh = new KhachHang();
        _Nv = new NhanVien();
        _CTHD = new ChiTietHoaDon();
        _CTSP = new ChiTietSanPham();
        txtSoLuong_ChiTietHoaDon.setValue(1);
        cbbNhanVien_HoaDon.setSelectedIndex(0);
        cbbKhachHang_HoaDon.setSelectedIndex(0);
        startNgayGio();
        selectFinalHD();
    }
    public void tblCtp() {
        int viTriDongVuaBam = tblCTSP.getSelectedRow();
        Object value = tblCTSP.getValueAt(viTriDongVuaBam, 0);
        if (value != null) {
            Integer masp = Integer.parseInt(value.toString());
            _CTHD.setID_ChiTietSanPham(masp);
//            iCTHDService.getTonKho(masp);
//            System.out.println("TonKho: "+iCTHDService.getTonKho(masp));
        } else {
            System.out.println("line 1657: null");
        }

        int idHoaDon = helper.getID_HD();
        int idChiTietSanPham = _CTHD.getID_ChiTietSanPham();

        helper.setGiaSP(Integer.valueOf(tblCTSP.getValueAt(viTriDongVuaBam, 8).toString()));
        helper.setSoluong(Integer.valueOf(tblCTSP.getValueAt(viTriDongVuaBam, 9).toString()));
        
        System.out.println("line 1663: IDHD: " + helper.getID_HD());
        System.out.println("line 1664: IDCTSP: " + _CTHD.getID_ChiTietSanPham());

        List<ChiTietHoaDon> lstCTHD = iCTHDService.getSoluong(idHoaDon, idChiTietSanPham);

        if (lstCTHD.isEmpty()) {
            try {
                iCTHDService.addCTHD(getFormCTHD());
//            JOptionPane.showMessageDialog(this, "Thêm Thành Công");
            } catch (Exception e) {
                DialogHelper.alert(this, "Vui lòng chọn hoá đơn!!!!!!");
            }
        } else {
            ChiTietHoaDon cthdver2 = lstCTHD.get(0);
                int tangSoLuong = cthdver2.getSoLuong() + 1;
                cthdver2.setSoLuong(tangSoLuong);
                int gia = helper.getGiaSP();
                int tong_gia = gia * tangSoLuong;
                cthdver2.setGia(tong_gia);
                iCTHDService.updateSoLuong(cthdver2);
//            if (validateFormCTHD()) {
//                
//            }
//            JOptionPane.showMessageDialog(this, "Cập nhật Thành Công");
        }

        fillDataChiTietHoaDon();
        refesch();
    }

    public void xoaChiTietHoaDon(int idChiTietHoaDon) {
        boolean deleted = iCTHDService.deleteCTHD(idChiTietHoaDon);
        if (deleted) {
            JOptionPane.showMessageDialog(this, "Xóa chi tiết hóa đơn thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Xóa chi tiết hóa đơn không thành công");
        }
        fillDataChiTietHoaDon();
        fillDataTableHoaDon();
    }
    public void refesch() {
        cbbKhachHang_HoaDon.removeAllItems();
        cbbNhanVien_HoaDon.removeAllItems();
        getCbbKhachHang();
        getCbbNhanVien();
        fillDataTableHoaDon();
        fillDataChiTietHoaDon();
    }
    public void tittel() {
        lblMaHoaDon_HoaDon1.setForeground(Color.black);
        lblMaHoaDon_HoaDon.setForeground(Color.black);
        jLabel31.setForeground(Color.black);
        jLabel30.setForeground(Color.black);
        lblNgayLap1.setForeground(Color.black);
        lblTongTien_HoaDon1.setForeground(Color.black);
        lblNgayLap.setForeground(Color.black);
        lblTongTien_HoaDon.setForeground(Color.black);

    }

    public void fillDataTableCTSP() {
        modelCTSP.setRowCount(0);
        if (iCTSPService.getDataViewModels().isEmpty()) {
            return;
        }
        rdDangCho.setSelected(true);
        for (ChiTietSanPhamViewModels CTSP : iCTSPService.getDataViewModels()) {
            Object[] rowData = {
                    CTSP.getID_ChiTietSanPham(),
                    CTSP.getTenSanPham(),
                    CTSP.getID_DanhMucSanPham(),
                    CTSP.getID_Size(),
                    CTSP.getID_MauSac(),
                    CTSP.getID_ThuongHieu(),
                    CTSP.getID_TrongLuong(),
                    CTSP.getID_ChatLieu(),
                    CTSP.getGia(),
                    CTSP.getSoLuong()};
            modelCTSP.addRow(rowData);
        }
    }

    void startNgayGio() {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
        new Timer(1000, (ActionEvent e) -> {
            lblDongHo.setText(formater.format(new Date()));
            txtNgayLapHoaDon_HoaDon.setText(formater.format(new Date()));

        }).start();
    }

    public void getCbbKhachHang() {
        List<KhachHang> lstKhachHang = iKhachHangService.getData();

        for (KhachHang khachHang : lstKhachHang) {
            cbbKhachHang_HoaDon.addItem(khachHang.getTenKhachHang());
            _idNameMap.put(khachHang.getID_KhachHang(), khachHang.getTenKhachHang());
        }

        cbbKhachHang_HoaDon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tenKhachHang = (String) cbbKhachHang_HoaDon.getSelectedItem();
                int idKhachHang = getIdFromName(tenKhachHang, _idNameMap);
                KhachHang selectedKhachHang = null;
                for (KhachHang khachHang : lstKhachHang) {
                    if (khachHang.getID_KhachHang() == idKhachHang) {
                        selectedKhachHang = khachHang;
                        break;
                    }
                }
                if (selectedKhachHang != null) {
                    _hd.setID_KhachHang(selectedKhachHang.getID_KhachHang());
                    helper.setIDKH(selectedKhachHang.getID_KhachHang());
                    _Kh.setID_KhachHang(selectedKhachHang.getID_KhachHang());
                    helper.setTenKH(tenKhachHang);
                    iKhachHangService.getDataByID(_Kh.getID_KhachHang());
                    if(selectedKhachHang.getDiaChi()==null){
                        txtDiaChi_HoaDon.setText("");
                    }else{
                        txtDiaChi_HoaDon.setText(selectedKhachHang.getDiaChi());
                        helper.setDiaChi(selectedKhachHang.getDiaChi());
                    }
                    if(String.valueOf(selectedKhachHang.getSoDienThoai())==null){
                        txtDiaChi_HoaDon.setText("");
                    }else{
                        txtSDT_HoaDon.setText(String.valueOf(selectedKhachHang.getSoDienThoai()));
                        helper.setSDT(selectedKhachHang.getSoDienThoai());
                    }
                }
            }
        });
    }

    public int updateGiaThanh(String ID, int soLuong) {
        List<ChiTietSanPham> lstctsp = iCTSPService.getData();

        int IDSP = Integer.parseInt(ID);
        ChiTietSanPham chitietSP = null;
        for (ChiTietSanPham sP : lstctsp) {
            if (sP.getID_ChiTietSanPham() == IDSP) {
                chitietSP = sP;
                break;
            }
        }
        if (chitietSP != null) {
            return chitietSP.getGia() * soLuong;
        }
        return 0;
    }

    public ChiTietHoaDon getFormCTHD() {
//        ChiTietHoaDon hd = new ChiTietHoaDon();
        try {

            _CTHD.setID_HoaDon(Integer.valueOf(txtID_HD.getText()));
            String ID = tblCTSP.getValueAt(tblCTSP.getSelectedRow(), 0).toString();
            int soluong = Integer.parseInt(txtSoLuong_ChiTietHoaDon.getValue().toString());
            _CTHD.setGia(updateGiaThanh(ID, soluong));
            _CTHD.setSoLuong(Integer.valueOf(txtSoLuong_ChiTietHoaDon.getValue().toString()));
        } catch (Exception e) {
            DialogHelper.alert(this,"Vui lòng chọn hoá đơn!!!!!!" );
        }
        return _CTHD;
    }

    public ChiTietHoaDon setFormCTHD() {
        try {
//            _CTHD.setID_CTHD(Integer.parseInt(txtID_ChiTietHoaDon.getText()));
            _CTHD.setID_HoaDon(Integer.parseInt(txtID_HD.getText()));
            String ID = tblCTSP.getValueAt(tblCTSP.getSelectedRow(), 0).toString();
            int soluong = Integer.parseInt(txtSoLuong_ChiTietHoaDon.getValue().toString());
            _CTHD.setGia(updateGiaThanh(ID, soluong));

            _CTHD.setSoLuong(Integer.parseInt(txtSoLuong_ChiTietHoaDon.getValue().toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return _CTHD;
    }

    public boolean validateFormCTHD() {
        String SoLuong = txtSoLuong_ChiTietHoaDon.getValue().toString();
        int masp = _CTHD.getID_ChiTietSanPham();
        int solg = Integer.valueOf(txtSoLuong_ChiTietHoaDon.getValue().toString());
        int soluongConLai = helper.getsoLuong();
        if (SoLuong.equals("")) {
            lblSoLuong_CTPM.setForeground(Color.red);
            return false;
        }
        if(solg > soluongConLai){
            DialogHelper.alert(this, "Số lượng còn lại là: "+soluongConLai);
            return false;
        }
        return true;
    }

    public void getCbbNhanVien() {
        List<NhanVien> lstNV = iNVservice.getData();
        for (NhanVien nhanVien : lstNV) {
            cbbNhanVien_HoaDon.addItem(nhanVien.getTenNhanVien());
            _idNameMap.put(nhanVien.getID_NhanVien(), nhanVien.getTenNhanVien());
        }
        cbbNhanVien_HoaDon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tenNV = (String) cbbNhanVien_HoaDon.getSelectedItem();
                int ID_NV = getIdFromName(tenNV, _idNameMap);
                _Nv.setID_NhanVien(ID_NV);
                helper.setMaNV(ID_NV);
//                System.out.println("Ten NV: " + tenNV);
//                System.out.println("ID NV: " + _Nv.getID_NhanVien());
            }
        });
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

    public void fillDataTableHoaDon() {
        modelHoaDon.setRowCount(0);

        if (iHoaDonService.getDataViewModels().isEmpty()) {
            return;
        }
        for (HoaDonViewmodels hoadon : iHoaDonService.getDataViewModels()) {
            Object rowData[] = new Object[10];
            rowData[0] = hoadon.getID_HoaDon();
            rowData[1] = hoadon.getMaHoaDon();
            rowData[2] = hoadon.getNhanVien();
            rowData[3] = hoadon.getTenKhachHang();
            rowData[4] = hoadon.getDiaChi();
            rowData[5] = hoadon.getSoDienThoai();
            rowData[6] = hoadon.getNgayLapHoaDon();
            rowData[7] = hoadon.getTongTien();
            rowData[8] = hoadon.getGhiChu();
            if (hoadon.getTrangThai() == 0) {
                rowData[9] = "Đã thanh toán";
            } else if (hoadon.getTrangThai() == 1) {
                rowData[9] = "Đang chờ";
            } else if (hoadon.getTrangThai() == 2) {
                rowData[9] = "Đang giao hàng";
            } else if (hoadon.getTrangThai() == 3) {
                rowData[9] = "Đã giao hàng";
            }

            modelHoaDon.addRow(rowData);
        }
    }

    public String GetComboBoxSelected(JComboBox cbb) {
        Object[] obj = cbb.getSelectedObjects();
        displayvalueModel item = (displayvalueModel) obj[0];
        return item.displayvalue.toString();

    }

    public boolean validatFormHoaDon() {
        
        if (txtDiaChi_HoaDon.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa Chỉ chưa nhập");
            txtDiaChi_HoaDon.setForeground(Color.red);
        }
        if (txtSDT_HoaDon.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "SDT chưa nhập");
            txtSDT_HoaDon.setForeground(Color.red);
        }
        if (txtTongTien_HoaDon.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tổng Tiền chưa nhập");
            txtTongTien_HoaDon.setForeground(Color.red);
        }
        return true;
    }
    public void fillDataChiTietHoaDon() {
        modelCTHD.setRowCount(0);
        int ID = helper.getID_HD();

        if (iCTHDService.getDataByID(ID).isEmpty()) {
            return;
        }

        for (ChiTietHoaDon hoadon : iCTHDService.getDataByID(ID)) {
            Object[] rowData = {
                hoadon.getID_CTHD(),
                hoadon.getID_HoaDon(),
                hoadon.getID_ChiTietSanPham(),
                hoadon.getTenSanPham(),
                hoadon.getSoLuong(),
                hoadon.getGia()};
            modelCTHD.addRow(rowData);
        }
        txtSoLuong_ChiTietHoaDon.setValue(1);
    }
    public HoaDon getFormHoaDon() {
        HoaDon hd = new HoaDon();
        try {
            int IDkh = helper.getIDKH();
            hd.setID_KhachHang(IDkh);

            int IDNv = helper.getMaNV();
            hd.setID_NhanVien(IDNv);

            String tenKh = helper.getTenKH();
            hd.setTenKhachHang(tenKh);

            String diachi = helper.getDiaChi();
            hd.setDiaChi(diachi);

            int sdt = helper.getSDT();
            hd.setSoDienThoai(sdt);

            hd.setMaHoaDon(txtMa_HoaDon.getText());
            hd.setSoDienThoai(Integer.parseInt(txtSDT_HoaDon.getText()));
            Date ngayHienTai = new Date();
            hd.setNgayLapHoaDon(new java.sql.Date(ngayHienTai.getTime()));
            hd.setTongTien(Integer.parseInt(txtTongTien_HoaDon.getText()));

            hd.setGhiChu(txtGhiChu_HoaDon.getText());
            if (rdDangCho.isSelected()) {
                hd.setTrangThai(1);
            } else if (rdDangGiaoHang.isSelected()) {
                hd.setTrangThai(2);
            } else if (rdGiaoHangThanhcong.isSelected()) {
                hd.setTrangThai(3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hd;
    }

    public HoaDon setFormHoaDon() {
        HoaDon hd = new HoaDon();
        try {
            int IDkh = helper.getIDKH();
            hd.setID_KhachHang(IDkh);

            int IDNv = helper.getMaNV();
            hd.setID_NhanVien(IDNv);

            String tenKh = helper.getTenKH();
            hd.setTenKhachHang(tenKh);

            String diachi = helper.getDiaChi();
            hd.setDiaChi(diachi);

            int sdt = helper.getSDT();
            hd.setSoDienThoai(sdt);

//            System.out.println("Hoa Don: " + hd.getID_NhanVien());
//            System.out.println("KH: " + hd.getID_KhachHang());
            hd.setID_HoaDon(Integer.valueOf(txtID_HD.getText()));
            hd.setMaHoaDon(txtMa_HoaDon.getText());
            hd.setSoDienThoai(Integer.parseInt(txtSDT_HoaDon.getText()));
            Date ngayHienTai = new Date();
            hd.setNgayLapHoaDon(new java.sql.Date(ngayHienTai.getTime()));
            hd.setTongTien(Integer.parseInt(txtTongTien_HoaDon.getText()));
            hd.setGhiChu(txtGhiChu_HoaDon.getText());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return hd;
    }

    public boolean validateFormHoaDon() {
        if (txtMa_HoaDon.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã Hoá Đơn chưa nhập");
            lblMaHoaDon_HoaDon.setForeground(Color.red);
            return false;
        } else if (cbbNhanVien_HoaDon.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Nhân viên chưa nhập");
            jLabel31.setForeground(Color.red);
            return false;
        } else if (cbbKhachHang_HoaDon.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Khách Hàng chưa nhập");
            jLabel30.setForeground(Color.red);
            return false;
        } else if (txtNgayLapHoaDon_HoaDon.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ngày lập chưa chưa nhập");
            txtNgayLapHoaDon_HoaDon.setForeground(Color.red);
            return false;
        }
        return true;
    }
    public ChiTietHoaDon muaNhieu() {
        ChiTietHoaDon ct = new ChiTietHoaDon();
        ct.setID_CTHD(Integer.valueOf(txtIDCTHD.getText()));
        ct.setSoLuong(Integer.valueOf(txtSoLuong_ChiTietHoaDon.getValue().toString()));
        return ct;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel8 = new javax.swing.JPanel();
        lblDongHo = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanelHoaDon = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tblCTHoaDon_ChiTietHoaDon = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblHoaDon_HoaDon = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCTSP = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        lblMaHoaDon_HoaDon = new javax.swing.JLabel();
        txtMa_HoaDon = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lblNgayLap1 = new javax.swing.JLabel();
        txtDiaChi_HoaDon = new javax.swing.JTextField();
        txtSDT_HoaDon = new javax.swing.JTextField();
        lblTongTien_HoaDon1 = new javax.swing.JLabel();
        lblNgayLap = new javax.swing.JLabel();
        txtNgayLapHoaDon_HoaDon = new javax.swing.JTextField();
        txtTongTien_HoaDon = new javax.swing.JLabel();
        lblTongTien_HoaDon = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        txtGhiChu_HoaDon = new javax.swing.JTextArea();
        jLabel33 = new javax.swing.JLabel();
        cbbNhanVien_HoaDon = new javax.swing.JComboBox<>();
        cbbKhachHang_HoaDon = new javax.swing.JComboBox<>();
        btnThemNhanh = new javax.swing.JButton();
        txtID_HD = new javax.swing.JTextField();
        lblMaHoaDon_HoaDon1 = new javax.swing.JLabel();
        btnThem_HoaDon = new javax.swing.JButton();
        btnThanhToan_HoaDon = new javax.swing.JButton();
        btnSua_ChiTietHoaDon = new javax.swing.JButton();
        lblTongTien_HoaDon2 = new javax.swing.JLabel();
        rdDangCho = new javax.swing.JRadioButton();
        rdDangGiaoHang = new javax.swing.JRadioButton();
        rdGiaoHangThanhcong = new javax.swing.JRadioButton();
        txtSoLuong_ChiTietHoaDon = new javax.swing.JSpinner();
        lblSoLuong_CTPM = new javax.swing.JLabel();
        lblSoLuong_CTPM1 = new javax.swing.JLabel();
        txtIDCTHD = new javax.swing.JTextField();
        btnThem_ChiTietHoaDon = new javax.swing.JButton();
        btnXoa_CTHD = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        lblSoLuong_CTPM2 = new javax.swing.JLabel();
        cbkMaHD = new javax.swing.JCheckBox();
        cbkTenKhachHang = new javax.swing.JCheckBox();
        txtTimTenSP = new javax.swing.JTextField();
        lblSoLuong_CTPM3 = new javax.swing.JLabel();

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        lblDongHo.setBackground(new java.awt.Color(255, 255, 255));
        lblDongHo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDongHo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDongHo.setText(" 11/11/2022 ");
        lblDongHo.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                lblDongHoAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(lblDongHo, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(lblDongHo)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 192, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));

        jPanelHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        jPanelHoaDon.setPreferredSize(new java.awt.Dimension(1176, 581));
        jPanelHoaDon.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelHoaDonComponentShown(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Chi Tiết Đơn Hàng", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        tblCTHoaDon_ChiTietHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Giá"
            }
        ));
        tblCTHoaDon_ChiTietHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTHoaDon_ChiTietHoaDonMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblCTHoaDon_ChiTietHoaDonMouseReleased(evt);
            }
        });
        jScrollPane15.setViewportView(tblCTHoaDon_ChiTietHoaDon);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane15, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.MatteBorder(null), "Danh Sách Hoá Đơn"));
        jPanel4.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        tblHoaDon_HoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Hoá Đơn", "Nhân Viên", "Khách Hàng", "Địa chỉ", "Số ĐT", "Ngày lập hoá đơn", "Tổng tiền", "Ghi chú", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon_HoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDon_HoaDonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblHoaDon_HoaDonMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblHoaDon_HoaDonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblHoaDon_HoaDonMouseReleased(evt);
            }
        });
        jScrollPane10.setViewportView(tblHoaDon_HoaDon);
        if (tblHoaDon_HoaDon.getColumnModel().getColumnCount() > 0) {
            tblHoaDon_HoaDon.getColumnModel().getColumn(0).setPreferredWidth(70);
            tblHoaDon_HoaDon.getColumnModel().getColumn(0).setMaxWidth(70);
            tblHoaDon_HoaDon.getColumnModel().getColumn(1).setMinWidth(120);
            tblHoaDon_HoaDon.getColumnModel().getColumn(1).setPreferredWidth(120);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.MatteBorder(null), "Sản Phẩm"));

        tblCTSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sản Phẩm ", "Tên Sản Phẩm", "Danh Mục Sản Phẩm", "Size", "Màu Sắc", "Thương Hiệu", "Trọng Lượng", "Chất Liệu", "Giá", "Số Lượng", "Trạng Thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblCTSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTSPMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblCTSPMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblCTSP);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
        );

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hoá Đơn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        lblMaHoaDon_HoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMaHoaDon_HoaDon.setText("Mã Hóa Đơn");

        txtMa_HoaDon.setEditable(false);
        txtMa_HoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMa_HoaDon.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtMa_HoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMa_HoaDonActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setText("Nhân Viên");
        jLabel31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel31MouseClicked(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setText("Khách Hàng");
        jLabel30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel30MouseClicked(evt);
            }
        });

        lblNgayLap1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNgayLap1.setText("Địa chỉ");
        lblNgayLap1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNgayLap1MouseClicked(evt);
            }
        });

        txtDiaChi_HoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDiaChi_HoaDon.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtDiaChi_HoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChi_HoaDonActionPerformed(evt);
            }
        });

        txtSDT_HoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSDT_HoaDon.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtSDT_HoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDT_HoaDonActionPerformed(evt);
            }
        });

        lblTongTien_HoaDon1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTongTien_HoaDon1.setText("Số Điện Thoại");

        lblNgayLap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNgayLap.setText("Ngày Lập");
        lblNgayLap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNgayLapMouseClicked(evt);
            }
        });

        txtNgayLapHoaDon_HoaDon.setEditable(false);
        txtNgayLapHoaDon_HoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNgayLapHoaDon_HoaDon.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtNgayLapHoaDon_HoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayLapHoaDon_HoaDonActionPerformed(evt);
            }
        });

        txtTongTien_HoaDon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTongTien_HoaDon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTongTien_HoaDon.setText("0");
        txtTongTien_HoaDon.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        lblTongTien_HoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTongTien_HoaDon.setText("Tổng Tiền");

        txtGhiChu_HoaDon.setColumns(20);
        txtGhiChu_HoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtGhiChu_HoaDon.setRows(5);
        jScrollPane16.setViewportView(txtGhiChu_HoaDon);

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setText("Ghi Chú");

        cbbNhanVien_HoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbbNhanVien_HoaDon.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 1, 0));

        cbbKhachHang_HoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnThemNhanh.setText("+");
        btnThemNhanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanhActionPerformed(evt);
            }
        });

        txtID_HD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtID_HD.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtID_HD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtID_HDActionPerformed(evt);
            }
        });

        lblMaHoaDon_HoaDon1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMaHoaDon_HoaDon1.setText("STT");

        btnThem_HoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThem_HoaDon.setText("Tạo Hoá đơn ");
        btnThem_HoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem_HoaDonActionPerformed(evt);
            }
        });

        btnThanhToan_HoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThanhToan_HoaDon.setText("Thanh Toán");
        btnThanhToan_HoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToan_HoaDonActionPerformed(evt);
            }
        });

        btnSua_ChiTietHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSua_ChiTietHoaDon.setText("Sửa");
        btnSua_ChiTietHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua_ChiTietHoaDonActionPerformed(evt);
            }
        });

        lblTongTien_HoaDon2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTongTien_HoaDon2.setText("Trạng thái");

        rdDangCho.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdDangCho);
        rdDangCho.setText("Đang chờ");

        rdDangGiaoHang.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdDangGiaoHang);
        rdDangGiaoHang.setText("Giao Hàng");

        rdGiaoHangThanhcong.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdGiaoHangThanhcong);
        rdGiaoHangThanhcong.setText("Giao Hàng thành công");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblMaHoaDon_HoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtID_HD))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbbNhanVien_HoaDon, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(lblMaHoaDon_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtMa_HoaDon))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbbKhachHang_HoaDon, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblTongTien_HoaDon1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                        .addComponent(lblNgayLap1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblNgayLap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblTongTien_HoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtSDT_HoaDon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                        .addComponent(txtDiaChi_HoaDon, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(txtNgayLapHoaDon_HoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTongTien_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThemNhanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblTongTien_HoaDon2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdDangGiaoHang)
                                    .addComponent(rdDangCho)
                                    .addComponent(rdGiaoHangThanhcong, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addGap(53, 53, 53)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnSua_ChiTietHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnThanhToan_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addGap(52, 52, 52)
                                    .addComponent(btnThem_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMaHoaDon_HoaDon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtID_HD, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMaHoaDon_HoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMa_HoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbNhanVien_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbKhachHang_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemNhanh, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTongTien_HoaDon)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtDiaChi_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1))
                            .addComponent(lblNgayLap1))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTongTien_HoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSDT_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNgayLap)
                            .addComponent(txtNgayLapHoaDon_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addComponent(lblTongTien_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTongTien_HoaDon2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdDangCho))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdDangGiaoHang)
                .addGap(9, 9, 9)
                .addComponent(rdGiaoHangThanhcong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThem_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSua_ChiTietHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThanhToan_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Hoá đơn ", jPanel2);

        txtSoLuong_ChiTietHoaDon.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                txtSoLuong_ChiTietHoaDonStateChanged(evt);
            }
        });
        txtSoLuong_ChiTietHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSoLuong_ChiTietHoaDonMouseClicked(evt);
            }
        });
        txtSoLuong_ChiTietHoaDon.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtSoLuong_ChiTietHoaDonPropertyChange(evt);
            }
        });

        lblSoLuong_CTPM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSoLuong_CTPM.setText("Số Lượng");
        lblSoLuong_CTPM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSoLuong_CTPMMouseClicked(evt);
            }
        });

        lblSoLuong_CTPM1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSoLuong_CTPM1.setText("IDCTHD");
        lblSoLuong_CTPM1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSoLuong_CTPM1MouseClicked(evt);
            }
        });

        txtIDCTHD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtIDCTHD.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtIDCTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDCTHDActionPerformed(evt);
            }
        });

        btnThem_ChiTietHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThem_ChiTietHoaDon.setText("Thêm");
        btnThem_ChiTietHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem_ChiTietHoaDonActionPerformed(evt);
            }
        });

        btnXoa_CTHD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoa_CTHD.setText("Xóa");
        btnXoa_CTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa_CTHDActionPerformed(evt);
            }
        });

        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTimKiem.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
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

        lblSoLuong_CTPM2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSoLuong_CTPM2.setText("Tìm Kiếm");
        lblSoLuong_CTPM2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSoLuong_CTPM2MouseClicked(evt);
            }
        });

        cbkMaHD.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(cbkMaHD);
        cbkMaHD.setText("Mã Hoá Đơn");

        cbkTenKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(cbkTenKhachHang);
        cbkTenKhachHang.setText("Số Điện Thoại");

        txtTimTenSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimTenSPKeyReleased(evt);
            }
        });

        lblSoLuong_CTPM3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSoLuong_CTPM3.setText("Tìm kiếm");
        lblSoLuong_CTPM3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSoLuong_CTPM3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelHoaDonLayout = new javax.swing.GroupLayout(jPanelHoaDon);
        jPanelHoaDon.setLayout(jPanelHoaDonLayout);
        jPanelHoaDonLayout.setHorizontalGroup(
            jPanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHoaDonLayout.createSequentialGroup()
                .addGroup(jPanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHoaDonLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHoaDonLayout.createSequentialGroup()
                                .addComponent(lblSoLuong_CTPM)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSoLuong_ChiTietHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addComponent(btnThem_ChiTietHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblSoLuong_CTPM1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIDCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnXoa_CTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHoaDonLayout.createSequentialGroup()
                                .addGroup(jPanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))))
                    .addGroup(jPanelHoaDonLayout.createSequentialGroup()
                        .addGroup(jPanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelHoaDonLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(lblSoLuong_CTPM2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbkMaHD)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbkTenKhachHang))
                            .addGroup(jPanelHoaDonLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblSoLuong_CTPM3)
                                .addGap(39, 39, 39)
                                .addComponent(txtTimTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelHoaDonLayout.setVerticalGroup(
            jPanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelHoaDonLayout.createSequentialGroup()
                        .addGroup(jPanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSoLuong_CTPM2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbkMaHD)
                            .addComponent(cbkTenKhachHang))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSoLuong_CTPM, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThem_ChiTietHoaDon)
                            .addComponent(txtSoLuong_ChiTietHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSoLuong_CTPM1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIDCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa_CTHD))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addGroup(jPanelHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSoLuong_CTPM3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 767, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 1233, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanelHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jPanelHoaDonComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelHoaDonComponentShown

        //        cbbNhanVien_HoaDon.setModel(GetDuLieucbb("NhanVien", "TenNhanVien", "MaNhanVien"));
        //        cbbKhachHang_HoaDon.setModel(GetDuLieucbb("KhachHang", "TenKhachHang", "MaKhachHang"));
        //        cbbSanPham_ChiTietHoaDon.setModel(GetDuLieucbb("SanPham", "TenSanPham", "MaSanPham"));
        //        txtNgayLapHoaDon_HoaDon.setText(year+"-"+month+"-"+day);
    }//GEN-LAST:event_jPanelHoaDonComponentShown

    private void btnSua_ChiTietHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua_ChiTietHoaDonActionPerformed
       
        iHoaDonService.UpdateHoaDon(setFormHoaDon());
        fillDataTableHoaDon();
        refesch();
    }//GEN-LAST:event_btnSua_ChiTietHoaDonActionPerformed

    private void btnThanhToan_HoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToan_HoaDonActionPerformed

        iHoaDonService.thanhToanHoaDon(setFormHoaDon());
        fillDataTableHoaDon();
        String khachHang = cbbKhachHang_HoaDon.getSelectedItem().toString();
        String nv = cbbNhanVien_HoaDon.getSelectedItem().toString();
        int mahd = Integer.valueOf(txtID_HD.getText());
//        Utilities.InvoiceGenerator.createInvoice(khachHang, nv, mahd);
    }//GEN-LAST:event_btnThanhToan_HoaDonActionPerformed

    private void btnThem_HoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem_HoaDonActionPerformed
        randomMaHD();
        if (DialogHelper.confirm(jPanel1, "Xác nhận tạo Hoá đơn?")) {
            if (validateFormHoaDon()) {
                try {
                    txtTongTien_HoaDon.setText("0");
                    iHoaDonService.addHoaDon(getFormHoaDon());
                    fillDataTableHoaDon();
                    selectFinalHD();
                } catch (Exception e) {
                    e.printStackTrace();

                }
            } else {
                JOptionPane.showMessageDialog(this, "Hoá đơn tạo thất bại");
            }
        }
    }//GEN-LAST:event_btnThem_HoaDonActionPerformed

    private void txtID_HDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtID_HDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtID_HDActionPerformed

    private void btnThemNhanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanhActionPerformed
        // TODO add your handling code here:
        new FMGThemNhanhKhachHang().setVisible(true);
        refesch();
    }//GEN-LAST:event_btnThemNhanhActionPerformed

    private void txtNgayLapHoaDon_HoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayLapHoaDon_HoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayLapHoaDon_HoaDonActionPerformed

    private void lblNgayLapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNgayLapMouseClicked

    }//GEN-LAST:event_lblNgayLapMouseClicked

    private void txtSDT_HoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDT_HoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDT_HoaDonActionPerformed

    private void txtDiaChi_HoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChi_HoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChi_HoaDonActionPerformed

    private void lblNgayLap1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNgayLap1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNgayLap1MouseClicked

    private void jLabel30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MouseClicked

    }//GEN-LAST:event_jLabel30MouseClicked

    private void jLabel31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel31MouseClicked

    private void txtMa_HoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMa_HoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMa_HoaDonActionPerformed

    private void tblCTSPMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTSPMouseReleased

        tblCtp();
    }//GEN-LAST:event_tblCTSPMouseReleased

    private void tblCTSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTSPMouseClicked

    }//GEN-LAST:event_tblCTSPMouseClicked

    private void btnXoa_CTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa_CTHDActionPerformed

        try {
            int id = Integer.valueOf(txtIDCTHD.getText());
            xoaChiTietHoaDon(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXoa_CTHDActionPerformed

    private void txtIDCTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDCTHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDCTHDActionPerformed

    private void lblSoLuong_CTPM1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSoLuong_CTPM1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblSoLuong_CTPM1MouseClicked

    private void btnThem_ChiTietHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem_ChiTietHoaDonActionPerformed

        int idHoaDon = helper.getID_HD();
        int idChiTietSanPham = _CTHD.getID_ChiTietSanPham();
        DialogHelper.alert(jPanel1, "IDHD: " + idHoaDon + "\nIDCHI tiet: " + idChiTietSanPham);
        List<ChiTietHoaDon> lstHD = iCTHDService.getSoluong(idHoaDon, idChiTietSanPham);
        int soluongThem = Integer.parseInt(txtSoLuong_ChiTietHoaDon.getValue().toString());

        if (lstHD != null && !lstHD.isEmpty()) {
            ChiTietHoaDon cthdver2 = lstHD.get(0);
            int tangSoLuong =  soluongThem;
            cthdver2.setSoLuong(tangSoLuong);
            int gia = helper.getGiaSP();
            int tong_gia = gia * tangSoLuong;
            cthdver2.setGia(tong_gia);
            
            cthdver2.setGia(tong_gia);
            if (validateFormCTHD()) {
                iCTHDService.muaNhieu(cthdver2);
                JOptionPane.showMessageDialog(this, "Cập nhật Thành Công");
                fillDataChiTietHoaDon();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn SP cần thêm");
        }
        
        
            
    }//GEN-LAST:event_btnThem_ChiTietHoaDonActionPerformed

    private void txtSoLuong_ChiTietHoaDonPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtSoLuong_ChiTietHoaDonPropertyChange

        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuong_ChiTietHoaDonPropertyChange

    private void txtSoLuong_ChiTietHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSoLuong_ChiTietHoaDonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuong_ChiTietHoaDonMouseClicked

    private void txtSoLuong_ChiTietHoaDonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_txtSoLuong_ChiTietHoaDonStateChanged

        if (Integer.parseInt(txtSoLuong_ChiTietHoaDon.getValue().toString()) <= 0) {
            txtSoLuong_ChiTietHoaDon.setValue(1);
        }
    }//GEN-LAST:event_txtSoLuong_ChiTietHoaDonStateChanged

    private void lblSoLuong_CTPMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSoLuong_CTPMMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblSoLuong_CTPMMouseClicked

    private void selectHoaDon(int row){
        try {
            
            txtID_HD.setText((String) tblHoaDon_HoaDon.getValueAt(row, 0));
            int ID_HD = Integer.parseInt(txtID_HD.getText());

            helper.setID_HD(ID_HD);
            txtMa_HoaDon.setText((String) tblHoaDon_HoaDon.getValueAt(row, 1));

            cbbNhanVien_HoaDon.setSelectedItem(tblHoaDon_HoaDon.getValueAt(row, 2));
            cbbKhachHang_HoaDon.setSelectedItem(tblHoaDon_HoaDon.getValueAt(row, 3));
            Object diaChiValue = tblHoaDon_HoaDon.getValueAt(row, 4);
            if (diaChiValue != null) {
                txtDiaChi_HoaDon.setText((String) diaChiValue);
            } else {
                txtDiaChi_HoaDon.setText("");
            }

            Object sdtValue = tblHoaDon_HoaDon.getValueAt(row, 5);
            if (sdtValue != null) {
                txtSDT_HoaDon.setText(String.valueOf((Integer) sdtValue));
            } else {
                txtSDT_HoaDon.setText("");
            }

            Object value = tblHoaDon_HoaDon.getValueAt(row, 6);
            if (value != null) {
                if (value instanceof java.sql.Date) {
                    java.sql.Date ngayLapHD = (java.sql.Date) value;
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String ngaytao = dateFormat.format(ngayLapHD);
                    txtNgayLapHoaDon_HoaDon.setText(ngaytao);
                }
            }
            txtTongTien_HoaDon.setText((String) tblHoaDon_HoaDon.getValueAt(row, 7));
            txtGhiChu_HoaDon.setText((String) tblHoaDon_HoaDon.getValueAt(row, 8));
            if (tblHoaDon_HoaDon.getValueAt(row, 9).equals(1)) {
                rdDangCho.setSelected(true);
            } else if (tblHoaDon_HoaDon.getValueAt(row, 9).equals(2)) {
                rdDangGiaoHang.setSelected(true);
            } else if (tblHoaDon_HoaDon.getValueAt(row, 9).equals(3)) {
                rdGiaoHangThanhcong.setSelected(true);
            }
            fillDataChiTietHoaDon();
        } catch (Exception e) {
            DialogHelper.alert(jPanel1, "Ko thay hoa don"+row);
        }
    }
    private void lblDongHoAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_lblDongHoAncestorAdded
        startNgayGio();
    }//GEN-LAST:event_lblDongHoAncestorAdded

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void lblSoLuong_CTPM2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSoLuong_CTPM2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblSoLuong_CTPM2MouseClicked
    private void findDataToSQL(HoaDonViewmodels view) {
        Object[] rowData = new Object[10];
        rowData[0] = view.getID_HoaDon();
        rowData[1] = view.getMaHoaDon();
        rowData[2] = view.getNhanVien();
        rowData[3] = view.getTenKhachHang();
        rowData[4] = view.getDiaChi();
        rowData[5] = view.getSoDienThoai();
        rowData[6] = view.getNgayLapHoaDon();
        rowData[7] = view.getTongTien();
        rowData[8] = view.getGhiChu();
        rowData[9] = view.getTrangThai();
        modelHoaDon.addRow(rowData);
    }
    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
        if (cbkMaHD.isSelected()) {
            String name = txtTimKiem.getText().trim();
            List<HoaDonViewmodels> lstSearchHd = iHoaDonService.findbyMaHoaDon(name);
            modelHoaDon.setRowCount(0);
            for (HoaDonViewmodels pmh : lstSearchHd) {
                findDataToSQL(pmh);
            }
        }else if(cbkTenKhachHang.isSelected()){
            String name = txtTimKiem.getText().trim();
            List<HoaDonViewmodels> lstSearchHd = iHoaDonService.findBySDTKhachHang(name);
            modelHoaDon.setRowCount(0);
            for (HoaDonViewmodels pmh : lstSearchHd) {
                findDataToSQL(pmh);
            }
        }
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void lblSoLuong_CTPM3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSoLuong_CTPM3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblSoLuong_CTPM3MouseClicked
    private void findDataToSQL(ChiTietSanPhamViewModels view) {
        Object[] rowData = new Object[11];
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
        modelCTSP.addRow(rowData);
    }

    private void txtTimTenSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimTenSPKeyReleased
        // TODO add your handling code here:
            String name = txtTimTenSP.getText().trim();
            List<ChiTietSanPhamViewModels> foundProducts = iCTSPService.findTenSP(name);
            modelCTSP.setRowCount(0);
            for (ChiTietSanPhamViewModels product : foundProducts) {
                findDataToSQL(product);
            }
    }//GEN-LAST:event_txtTimTenSPKeyReleased

    private void tblCTHoaDon_ChiTietHoaDonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTHoaDon_ChiTietHoaDonMouseReleased
        // TODO add your handling code here:
        int row = tblCTHoaDon_ChiTietHoaDon.getSelectedRow();
        txtIDCTHD.setText(tblCTHoaDon_ChiTietHoaDon.getValueAt(row, 0).toString());
        Object soLuong = tblCTHoaDon_ChiTietHoaDon.getValueAt(row, 4);
        _CTHD.setID_ChiTietSanPham(Integer.parseInt(tblCTHoaDon_ChiTietHoaDon.getValueAt(row, 2).toString()));
        int value = ((Number) soLuong).intValue();
        txtSoLuong_ChiTietHoaDon.setValue(value);
        String gia = tblCTHoaDon_ChiTietHoaDon.getValueAt(row, 5).toString();
        String sl = tblCTHoaDon_ChiTietHoaDon.getValueAt(row, 4).toString();
        int valueGia = Integer.valueOf(gia);
        int valueSoluong = Integer.valueOf(sl);
        int donGia = valueGia/valueSoluong;

        helper.setGiaSP(donGia);
        System.out.println("line 1624" + _CTHD.getID_CTHD());
    }//GEN-LAST:event_tblCTHoaDon_ChiTietHoaDonMouseReleased

    private void tblCTHoaDon_ChiTietHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTHoaDon_ChiTietHoaDonMouseClicked

    }//GEN-LAST:event_tblCTHoaDon_ChiTietHoaDonMouseClicked

    private void tblHoaDon_HoaDonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDon_HoaDonMouseReleased
        selectHoaDon(tblHoaDon_HoaDon.getSelectedRow());

    }//GEN-LAST:event_tblHoaDon_HoaDonMouseReleased

    private void tblHoaDon_HoaDonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDon_HoaDonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHoaDon_HoaDonMouseEntered

    private void tblHoaDon_HoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDon_HoaDonMouseClicked
        //        System.out.println("clicked");
    }//GEN-LAST:event_tblHoaDon_HoaDonMouseClicked

    private void tblHoaDon_HoaDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDon_HoaDonMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHoaDon_HoaDonMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnSua_ChiTietHoaDon;
    public static javax.swing.JButton btnThanhToan_HoaDon;
    private javax.swing.JButton btnThemNhanh;
    public static javax.swing.JButton btnThem_ChiTietHoaDon;
    public static javax.swing.JButton btnThem_HoaDon;
    public static javax.swing.JButton btnXoa_CTHD;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbKhachHang_HoaDon;
    private javax.swing.JComboBox<String> cbbNhanVien_HoaDon;
    private javax.swing.JCheckBox cbkMaHD;
    private javax.swing.JCheckBox cbkTenKhachHang;
    public static javax.swing.JLabel jLabel30;
    public static javax.swing.JLabel jLabel31;
    public static javax.swing.JLabel jLabel33;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    public static javax.swing.JPanel jPanelHoaDon;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane15;
    public static javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    public static javax.swing.JLabel lblDongHo;
    public static javax.swing.JLabel lblMaHoaDon_HoaDon;
    public static javax.swing.JLabel lblMaHoaDon_HoaDon1;
    public static javax.swing.JLabel lblNgayLap;
    public static javax.swing.JLabel lblNgayLap1;
    public static javax.swing.JLabel lblSoLuong_CTPM;
    public static javax.swing.JLabel lblSoLuong_CTPM1;
    public static javax.swing.JLabel lblSoLuong_CTPM2;
    public static javax.swing.JLabel lblSoLuong_CTPM3;
    public static javax.swing.JLabel lblTongTien_HoaDon;
    public static javax.swing.JLabel lblTongTien_HoaDon1;
    public static javax.swing.JLabel lblTongTien_HoaDon2;
    private javax.swing.JRadioButton rdDangCho;
    private javax.swing.JRadioButton rdDangGiaoHang;
    private javax.swing.JRadioButton rdGiaoHangThanhcong;
    private javax.swing.JTable tblCTHoaDon_ChiTietHoaDon;
    private javax.swing.JTable tblCTSP;
    private javax.swing.JTable tblHoaDon_HoaDon;
    public static javax.swing.JTextField txtDiaChi_HoaDon;
    public static javax.swing.JTextArea txtGhiChu_HoaDon;
    public static javax.swing.JTextField txtIDCTHD;
    public static javax.swing.JTextField txtID_HD;
    public static javax.swing.JTextField txtMa_HoaDon;
    public static javax.swing.JTextField txtNgayLapHoaDon_HoaDon;
    public static javax.swing.JTextField txtSDT_HoaDon;
    private javax.swing.JSpinner txtSoLuong_ChiTietHoaDon;
    public static javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTimTenSP;
    private javax.swing.JLabel txtTongTien_HoaDon;
    // End of variables declaration//GEN-END:variables

    private void selectFinalHD(){
        int finalRow = tblHoaDon_HoaDon.getRowCount();
        selectHoaDon(finalRow-1);
    }
    
    private String randomText(){
        String random = "HD_";
        String random2 = "ABCDEFJHIKLMNOPQRSUOTZX";
        String random3 = "1234567890";
        StringBuilder randomBuilder = new StringBuilder();
        randomBuilder.append(random);
        while (randomBuilder.length() < 8) {
            Random rand = new Random();
            randomBuilder.append(random2.charAt ((int) (rand.nextFloat()*random2.length() ) ) );
        }
        randomBuilder.append("_");
        while (randomBuilder.length() < 13) {
            Random rand = new Random();
            randomBuilder.append(random3.charAt ((int) (rand.nextFloat()*random3.length() ) ) );
        }
        return randomBuilder.toString();
    }
    
    private void randomMaHD() {
        // HD_YYVG_0782
        String newMaHD= randomText();
        List<HoaDonViewmodels> results = iHoaDonService.findbyMaHoaDon(newMaHD);
        boolean flag = false;
        for (HoaDonViewmodels x : results) {
            flag = true;
        }
        if (flag){
            
            newMaHD = randomText();
        } else {
            txtMa_HoaDon.setText(newMaHD);
        }  
    }
}
