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
import ViewModels.HoaDonViewmodels;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author qivub
 */
public class FMGHoaDon extends javax.swing.JPanel {
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
     * Creates new form HoaDonJpanel
     */
    public FMGHoaDon() {
        initComponents();
        modelCTHD = (DefaultTableModel) tblCTHoaDon_ChiTietHoaDon.getModel();
        modelHoaDon = (DefaultTableModel) tblHoaDon_HoaDon.getModel();
        iCTSPService = new ChiTietSanPhamService();
        iHoaDonService = new HoaDonService();
        iKhachHangService = new KhachHangService();
        iCTHDService = new ChiTietHoaDonService();
        iNVservice = new NhanVienService();
        helper = new SessionHelper();
        getCbbKhachHang();
        getCbbNhanVien();
        fillDataTableHoaDon();
        fillDataChiTietHoaDon();
        _hd = new HoaDon();
        _Kh = new KhachHang();
        _Nv = new NhanVien();
        _CTHD = new ChiTietHoaDon();
        _CTSP = new ChiTietSanPham();
        cbbNhanVien_HoaDon.setSelectedIndex(0);
        cbbKhachHang_HoaDon.setSelectedIndex(0);
    }
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
            }
            fillDataChiTietHoaDon();
        } catch (Exception e) {
            DialogHelper.alert(jPanel1, "Ko thay hoa don"+row);
        }
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
        for (HoaDonViewmodels hoadon : iHoaDonService.hoadonDaThanhToan()) {
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
            rowData[9] = hoadon.getTrangThai();
            

            modelHoaDon.addRow(rowData);
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

        jPanel1 = new javax.swing.JPanel();
        txtMa_HoaDon = new javax.swing.JTextField();
        lblMaHoaDon_HoaDon = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        cbbNhanVien_HoaDon = new javax.swing.JComboBox<>();
        cbbKhachHang_HoaDon = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        lblNgayLap1 = new javax.swing.JLabel();
        txtDiaChi_HoaDon = new javax.swing.JTextField();
        lblTongTien_HoaDon1 = new javax.swing.JLabel();
        txtSDT_HoaDon = new javax.swing.JTextField();
        lblNgayLap = new javax.swing.JLabel();
        txtNgayLapHoaDon_HoaDon = new javax.swing.JTextField();
        lblTongTien_HoaDon = new javax.swing.JLabel();
        txtTongTien_HoaDon = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        txtGhiChu_HoaDon = new javax.swing.JTextArea();
        rdDangCho = new javax.swing.JRadioButton();
        lblTongTien_HoaDon2 = new javax.swing.JLabel();
        lblTongTien_HoaDon3 = new javax.swing.JLabel();
        lblMaHoaDon_HoaDon1 = new javax.swing.JLabel();
        txtID_HD = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblHoaDon_HoaDon = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        tblCTHoaDon_ChiTietHoaDon = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        txtMa_HoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMa_HoaDon.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtMa_HoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMa_HoaDonActionPerformed(evt);
            }
        });

        lblMaHoaDon_HoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMaHoaDon_HoaDon.setText("Mã Hóa Đơn");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setText("Nhân Viên");
        jLabel31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel31MouseClicked(evt);
            }
        });

        cbbNhanVien_HoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbbNhanVien_HoaDon.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 1, 0));

        cbbKhachHang_HoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

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

        lblTongTien_HoaDon1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTongTien_HoaDon1.setText("Số Điện Thoại");

        txtSDT_HoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSDT_HoaDon.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtSDT_HoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDT_HoaDonActionPerformed(evt);
            }
        });

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

        lblTongTien_HoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTongTien_HoaDon.setText("Tổng Tiền");

        txtTongTien_HoaDon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTongTien_HoaDon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTongTien_HoaDon.setText("0");
        txtTongTien_HoaDon.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setText("Ghi Chú");

        txtGhiChu_HoaDon.setColumns(20);
        txtGhiChu_HoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtGhiChu_HoaDon.setRows(5);
        jScrollPane16.setViewportView(txtGhiChu_HoaDon);

        rdDangCho.setBackground(new java.awt.Color(255, 255, 255));
        rdDangCho.setText("Đã Thanh Toán");

        lblTongTien_HoaDon2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTongTien_HoaDon2.setText("Hoá Đơn Đã Thanh Toán");

        lblTongTien_HoaDon3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTongTien_HoaDon3.setText("Trạng thái");

        lblMaHoaDon_HoaDon1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMaHoaDon_HoaDon1.setText("STT");

        txtID_HD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtID_HD.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtID_HD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtID_HDActionPerformed(evt);
            }
        });

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
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblHoaDon_HoaDonMouseReleased(evt);
            }
        });
        jScrollPane10.setViewportView(tblHoaDon_HoaDon);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Chi Tiết Đơn Hàng", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane15, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(131, 131, 131)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbbKhachHang_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblNgayLap1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtDiaChi_HoaDon))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbbNhanVien_HoaDon, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(lblMaHoaDon_HoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtID_HD))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(lblMaHoaDon_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtMa_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(42, 42, 42)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblTongTien_HoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblNgayLap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(lblTongTien_HoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtSDT_HoaDon)
                                            .addComponent(txtNgayLapHoaDon_HoaDon)
                                            .addComponent(txtTongTien_HoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)))
                                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTongTien_HoaDon3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rdDangCho)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(474, 474, 474)
                                .addComponent(lblTongTien_HoaDon2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 371, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblTongTien_HoaDon2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTongTien_HoaDon)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblTongTien_HoaDon3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblTongTien_HoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtSDT_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(14, 14, 14)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblNgayLap)
                                        .addComponent(txtNgayLapHoaDon_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rdDangCho))
                                    .addGap(12, 12, 12)
                                    .addComponent(lblTongTien_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33)
                        .addGap(4, 4, 4)
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblMaHoaDon_HoaDon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtID_HD, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblMaHoaDon_HoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMa_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbNhanVien_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbKhachHang_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtDiaChi_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1))
                            .addComponent(lblNgayLap1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtMa_HoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMa_HoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMa_HoaDonActionPerformed

    private void jLabel31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel31MouseClicked

    private void jLabel30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MouseClicked

    }//GEN-LAST:event_jLabel30MouseClicked

    private void lblNgayLap1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNgayLap1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblNgayLap1MouseClicked

    private void txtDiaChi_HoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChi_HoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChi_HoaDonActionPerformed

    private void txtSDT_HoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDT_HoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDT_HoaDonActionPerformed

    private void lblNgayLapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNgayLapMouseClicked
   
    }//GEN-LAST:event_lblNgayLapMouseClicked

    private void txtNgayLapHoaDon_HoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayLapHoaDon_HoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayLapHoaDon_HoaDonActionPerformed

    private void txtID_HDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtID_HDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtID_HDActionPerformed

    private void tblHoaDon_HoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDon_HoaDonMouseClicked
        //        System.out.println("clicked");
    }//GEN-LAST:event_tblHoaDon_HoaDonMouseClicked

    private void tblHoaDon_HoaDonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDon_HoaDonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHoaDon_HoaDonMouseEntered

    private void tblHoaDon_HoaDonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDon_HoaDonMouseReleased
        selectHoaDon(tblHoaDon_HoaDon.getSelectedRow());

    }//GEN-LAST:event_tblHoaDon_HoaDonMouseReleased

    private void tblCTHoaDon_ChiTietHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTHoaDon_ChiTietHoaDonMouseClicked

    }//GEN-LAST:event_tblCTHoaDon_ChiTietHoaDonMouseClicked

    private void tblCTHoaDon_ChiTietHoaDonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTHoaDon_ChiTietHoaDonMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblCTHoaDon_ChiTietHoaDonMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbbKhachHang_HoaDon;
    private javax.swing.JComboBox<String> cbbNhanVien_HoaDon;
    public static javax.swing.JLabel jLabel30;
    public static javax.swing.JLabel jLabel31;
    public static javax.swing.JLabel jLabel33;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    public static javax.swing.JScrollPane jScrollPane10;
    public static javax.swing.JScrollPane jScrollPane15;
    public static javax.swing.JScrollPane jScrollPane16;
    public static javax.swing.JLabel lblMaHoaDon_HoaDon;
    public static javax.swing.JLabel lblMaHoaDon_HoaDon1;
    public static javax.swing.JLabel lblNgayLap;
    public static javax.swing.JLabel lblNgayLap1;
    public static javax.swing.JLabel lblTongTien_HoaDon;
    public static javax.swing.JLabel lblTongTien_HoaDon1;
    public static javax.swing.JLabel lblTongTien_HoaDon2;
    public static javax.swing.JLabel lblTongTien_HoaDon3;
    private javax.swing.JRadioButton rdDangCho;
    public static javax.swing.JTable tblCTHoaDon_ChiTietHoaDon;
    public static javax.swing.JTable tblHoaDon_HoaDon;
    public static javax.swing.JTextField txtDiaChi_HoaDon;
    public static javax.swing.JTextArea txtGhiChu_HoaDon;
    public static javax.swing.JTextField txtID_HD;
    public static javax.swing.JTextField txtMa_HoaDon;
    public static javax.swing.JTextField txtNgayLapHoaDon_HoaDon;
    public static javax.swing.JTextField txtSDT_HoaDon;
    private javax.swing.JLabel txtTongTien_HoaDon;
    // End of variables declaration//GEN-END:variables
}
