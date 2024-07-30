/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import DAO.chitietsanphamDAO;
import DAO.danhMucDAO;
import DAO.mauSacDAO;
import DAO.sizeDAO;
import DAO.thuongHieuDAO;
import entity.chitietsanpham;
import entity.danhmuc;
import entity.mausac;
import entity.size;
import entity.thuonghieu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author huyen
 */
public class qlsanphams extends javax.swing.JPanel {

    /**
     * Creates new form qlsanphams
     */
    DefaultTableModel model;
    DefaultTableModel modelBW;
    DefaultTableModel modelFW;
    List<chitietsanpham> ctsanphams;
    HashMap<Integer, Boolean> selectedMS;
    HashMap<Integer, Boolean> selectedSize;
    List<size> sizes;
    List<mausac> mausacs;
    List<danhmuc> dms;
    List<thuonghieu> ths;

    public qlsanphams() {
        initComponents();
        jfDanhMuc_SanPham.setSize(625, 700);
        jfMauSac.setSize(625, 700);
        jfSize.setSize(625, 700);
        jfThuongHieu.setSize(625, 700);
        sizes = new sizeDAO().getAll();
        mausacs = new mauSacDAO().getAll();
        ctsanphams = new chitietsanphamDAO().getAll();
        initSelectedMS();
        initSelectedSize();
        LamMoiForm();
        getDanhMuc();
        getThuongHieu();
        getSizeSP();
        getMauSac();
        resetTableBW();
        reNewlblMauSac();
        reNewlblSize();
        fillDataTableThuongHieu();
        fillDataTableDanhMuc();
        fillDataTableSize();
        fillDataTableMauSac();
    }

    void initSelectedMS() {
        selectedMS = new HashMap<>();
        for (mausac x : mausacs) {
            selectedMS.put(x.getId() - 1, false);
        }
    }

    void initSelectedSize() {
        selectedSize = new HashMap<>();
        for (size x : sizes) {
            selectedSize.put(x.getId() - 1, false);
        }
    }

    void resetTableBW() {
        modelBW = new DefaultTableModel(new Object[]{
            "Mã SP", "Tên SP", "Số lượng SP", "Giá SP", "Mô tả"
        }, 0);
        for (chitietsanpham x : ctsanphams) {
            modelBW.addRow(new Object[]{
                x.getMasp(), x.getTensp(), x.getSoLuong(), x.getGia(), x.getMoTa()
            });
        }
        tblDSSanPham.setModel(modelBW);
    }

    void resetTableFW() {
        modelFW = new DefaultTableModel(new Object[]{
            "Mã SP", "Tên SP", "Size", "Màu sắc", "Danh mục", "Thương hiệu"
        }, 0);
        for (chitietsanpham x : ctsanphams) {
            modelFW.addRow(new Object[]{
                x.getMasp(), x.getTensp(), x.getSizesp(), x.getMauSacsp(), x.getDanhMuc(), x.getThuongHieu()
            });
        }
        tblDSSanPham.setModel(modelFW);
    }

    void getDanhMuc() {
        List<danhmuc> danhmucs = new danhMucDAO().getAll();
        cbbDanhMuc.removeAllItems();
        cbbDanhMuc.addItem("Tất cả");
        for (danhmuc x : danhmucs) {
            cbbDanhMuc.addItem(x.getTen());
        }
    }

    void getThuongHieu() {
        List<thuonghieu> thuonghieus = new thuongHieuDAO().getAll();
        cbbThuongHieu.removeAllItems();
        cbbThuongHieu.addItem("Tất cả");
        for (thuonghieu x : thuonghieus) {
            cbbThuongHieu.addItem(x.getTen());
        }
    }

    void getSizeSP() {

        cbbSize.removeAllItems();
        cbbSize.addItem("Tất cả");
        cbbSize2.removeAllItems();
        for (size x : sizes) {
            cbbSize.addItem(x.getTen());
            cbbSize2.addItem(x.getTen());
        }
    }

    void getMauSac() {
        cbbMauSac2.removeAllItems();
        cbbMauSac.removeAllItems();
        cbbMauSac.addItem("Tất cả");
        for (mausac x : mausacs) {
            cbbMauSac.addItem(x.getMacsac());
            cbbMauSac2.addItem(x.getMacsac());
        }
    }

    void LamMoiForm() {
        cbbDanhMuc.setSelectedIndex(0);
        cbbMauSac.setSelectedIndex(0);
        cbbSize.setSelectedIndex(0);
        cbbThuongHieu.setSelectedIndex(0);
        spnMaxPrice.setValue(0);
        spnMinPrice.setValue(0);
        jrMoiGia.setSelected(true);
        jrTatCa.setSelected(true);
        ctsanphams = new chitietsanphamDAO().getAll();
        resetTableBW();
        txtTimKiem.setText("");

    }

    String getLoc() {
        String DKquery = "";
        List<String> dieuKien = new ArrayList<String>();
        dieuKien.add(cbbDanhMuc.getSelectedIndex() == 0 ? "" : ("dm.ID = " + cbbDanhMuc.getSelectedIndex()));
        dieuKien.add(cbbThuongHieu.getSelectedIndex() == 0 ? "" : ("th.ID = " + cbbThuongHieu.getSelectedIndex()));
        dieuKien.add(cbbMauSac.getSelectedIndex() == 0 ? "" : ("ctm.ID_MS = " + cbbMauSac.getSelectedIndex()));
        dieuKien.add(cbbSize.getSelectedIndex() == 0 ? "" : ("cts.ID_Size = " + cbbSize.getSelectedIndex()));
        dieuKien.add(jrLocGia.isSelected() ? ("sp.gia BETWEEN " + spnMinPrice.getValue() + " and " + spnMaxPrice.getValue()) : "");
        for (String x : dieuKien) {
            DKquery += DKquery.equals("") ? "" : x.equals("") ? "" : " AND ";
            DKquery += x;
        }
        return DKquery;
    }

    void reNewlblMauSac() {
        String ms = "";
        for (Map.Entry<Integer, Boolean> entry : selectedMS.entrySet()) {
            if (entry.getValue()) {
                ms += mausacs.get(entry.getKey()).getMacsac() + ", ";
            }
        }
        lblMauSac.setText(ms.equals("") ? "Chưa có màu sắc..." : ms);
    }

    void reNewlblSize() {
        String sz = "";
        for (Map.Entry<Integer, Boolean> entry : selectedSize.entrySet()) {
            if (entry.getValue()) {
                sz += sizes.get(entry.getKey()).getTen() + ", ";
            }
        }
        lblSize.setText(sz.equals("") ? "Chưa có size..." : sz);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jfThuongHieu = new javax.swing.JFrame();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbThuonHieu = new javax.swing.JTable();
        btnDeleteThuongHieu = new javax.swing.JButton();
        btnUpdateThuongHieu = new javax.swing.JButton();
        btnCreate_ThuongHieu = new javax.swing.JButton();
        txtTen_ThuongHieu = new javax.swing.JTextField();
        lblTen_ThuongHieu = new javax.swing.JLabel();
        lblMa_ThuongHieu = new javax.swing.JLabel();
        txtMa_ThuongHieu = new javax.swing.JTextField();
        rdConHangThuongHieu = new javax.swing.JRadioButton();
        rdHetHangThuongHieu = new javax.swing.JRadioButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtTimkiemThuongHieu = new javax.swing.JTextField();
        btnTimKiem4 = new javax.swing.JButton();
        lblTrangThai_ThuongHieu = new javax.swing.JLabel();
        lblTrangThai_ThuongHieu1 = new javax.swing.JLabel();
        txtMoTa_ThuongHieu = new javax.swing.JTextField();
        jfDanhMuc_SanPham = new javax.swing.JFrame();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDanhMuc = new javax.swing.JTable();
        btnDelete_DanhMuc = new javax.swing.JButton();
        btnUpdate_DanhMuc = new javax.swing.JButton();
        btnCreate_DanhMuc = new javax.swing.JButton();
        txtTen_TDanhMuc = new javax.swing.JTextField();
        lblTen_DanhMuc = new javax.swing.JLabel();
        lblMa_DanhMuc = new javax.swing.JLabel();
        txtMa_DanhMuc = new javax.swing.JTextField();
        rdConHangDanhMuc = new javax.swing.JRadioButton();
        rdHetHangDanhMuc = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtTimkiemDanhMuc = new javax.swing.JTextField();
        btnTimKiem5 = new javax.swing.JButton();
        lblTrangThai_DanhMuc = new javax.swing.JLabel();
        jfSize = new javax.swing.JFrame();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblSize = new javax.swing.JTable();
        btnDelete_Size = new javax.swing.JButton();
        btnUpdate_Size = new javax.swing.JButton();
        btnCreate_Size = new javax.swing.JButton();
        txtTen_Size = new javax.swing.JTextField();
        lblTen_Size = new javax.swing.JLabel();
        lblMa_Size = new javax.swing.JLabel();
        txtMa_Size = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtTimkiemSize = new javax.swing.JTextField();
        btnTimKiem6 = new javax.swing.JButton();
        lblTen_Size1 = new javax.swing.JLabel();
        txtMota_Size = new javax.swing.JTextField();
        jfMauSac = new javax.swing.JFrame();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblMauSac = new javax.swing.JTable();
        btnDelete_MauSac = new javax.swing.JButton();
        btnUpdate_MauSac = new javax.swing.JButton();
        btnCreate_MauSac = new javax.swing.JButton();
        txtTen_MauSac = new javax.swing.JTextField();
        lblTen_MauSac = new javax.swing.JLabel();
        lblMa_MauSac = new javax.swing.JLabel();
        txtMa_MauSac = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtTimkiemMauSac = new javax.swing.JTextField();
        btnTimKiem7 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        cbbDanhMuc = new javax.swing.JComboBox<>();
        cbbThuongHieu = new javax.swing.JComboBox<>();
        cbbSize = new javax.swing.JComboBox<>();
        cbbMauSac = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        spnMinPrice = new javax.swing.JSpinner();
        spnMaxPrice = new javax.swing.JSpinner();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jrMoiGia = new javax.swing.JRadioButton();
        jrLocGia = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDSSanPham = new javax.swing.JTable();
        btnBW = new javax.swing.JButton();
        btnFW = new javax.swing.JButton();
        jrTatCa = new javax.swing.JRadioButton();
        jrConHang = new javax.swing.JRadioButton();
        jrHetHang = new javax.swing.JRadioButton();
        jPanel8 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        btnLoc = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblMauSac = new javax.swing.JLabel();
        lblSize = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        cbbMauSac2 = new javax.swing.JComboBox<>();
        cbbSize2 = new javax.swing.JComboBox<>();
        btnThemMau = new javax.swing.JButton();
        btnXoaMau = new javax.swing.JButton();
        btnThemSize = new javax.swing.JButton();
        btnXoaSize = new javax.swing.JButton();
        btnThem_DanhMucSP = new javax.swing.JButton();
        btnThem_Mau = new javax.swing.JButton();
        btnThem_ThuongHieu = new javax.swing.JButton();
        btnThem_Size = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();

        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tbThuonHieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Tên Thương Hiệu", "Trạng Thái"
            }
        ));
        tbThuonHieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbThuonHieuMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbThuonHieuMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tbThuonHieu);

        btnDeleteThuongHieu.setText("Delete");
        btnDeleteThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteThuongHieuActionPerformed(evt);
            }
        });

        btnUpdateThuongHieu.setText("Update");
        btnUpdateThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateThuongHieuActionPerformed(evt);
            }
        });

        btnCreate_ThuongHieu.setText("Create");
        btnCreate_ThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreate_ThuongHieuActionPerformed(evt);
            }
        });

        lblTen_ThuongHieu.setText("Tên Thương Hiệu");

        lblMa_ThuongHieu.setText("Mã Thương Hiệu");

        txtMa_ThuongHieu.setEditable(false);
        txtMa_ThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMa_ThuongHieuActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdConHangThuongHieu);
        rdConHangThuongHieu.setText("Còn Hàng");

        buttonGroup1.add(rdHetHangThuongHieu);
        rdHetHangThuongHieu.setText("Hết Hàng");
        rdHetHangThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdHetHangThuongHieuActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel20.setText("Thương Hiệu");

        jLabel22.setText("Tìm kiếm");

        btnTimKiem4.setText("Tìm kiếm");
        btnTimKiem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiem4ActionPerformed(evt);
            }
        });

        lblTrangThai_ThuongHieu.setText("Trạng Thái");

        lblTrangThai_ThuongHieu1.setText("Mô tả");

        txtMoTa_ThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMoTa_ThuongHieuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(216, 216, 216)
                                .addComponent(jLabel20))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblTrangThai_ThuongHieu)
                                            .addComponent(jLabel22))
                                        .addGap(151, 151, 151)
                                        .addComponent(rdHetHangThuongHieu))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(lblMa_ThuongHieu)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtMa_ThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel9Layout.createSequentialGroup()
                                                .addComponent(txtTimkiemThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(btnDeleteThuongHieu)
                                                    .addComponent(btnTimKiem4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(btnCreate_ThuongHieu)
                                        .addGap(122, 122, 122)
                                        .addComponent(btnUpdateThuongHieu))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblTen_ThuongHieu)
                                            .addComponent(lblTrangThai_ThuongHieu1))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtMoTa_ThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(rdConHangThuongHieu)
                                            .addComponent(txtTen_ThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(0, 62, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimkiemThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(btnTimKiem4))
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMa_ThuongHieu)
                    .addComponent(txtMa_ThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTen_ThuongHieu)
                    .addComponent(txtTen_ThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdConHangThuongHieu)
                    .addComponent(rdHetHangThuongHieu)
                    .addComponent(lblTrangThai_ThuongHieu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTrangThai_ThuongHieu1)
                    .addComponent(txtMoTa_ThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate_ThuongHieu)
                    .addComponent(btnUpdateThuongHieu)
                    .addComponent(btnDeleteThuongHieu))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jfThuongHieuLayout = new javax.swing.GroupLayout(jfThuongHieu.getContentPane());
        jfThuongHieu.getContentPane().setLayout(jfThuongHieuLayout);
        jfThuongHieuLayout.setHorizontalGroup(
            jfThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jfThuongHieuLayout.setVerticalGroup(
            jfThuongHieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblDanhMuc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Tên Danh Mục", "Trạng Thái"
            }
        ));
        tblDanhMuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhMucMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblDanhMucMouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(tblDanhMuc);

        btnDelete_DanhMuc.setText("Delete");
        btnDelete_DanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete_DanhMucActionPerformed(evt);
            }
        });

        btnUpdate_DanhMuc.setText("Update");
        btnUpdate_DanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate_DanhMucActionPerformed(evt);
            }
        });

        btnCreate_DanhMuc.setText("Create");
        btnCreate_DanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreate_DanhMucActionPerformed(evt);
            }
        });

        lblTen_DanhMuc.setText("Tên Danh Mục");

        lblMa_DanhMuc.setText("Mã Danh Mục");

        txtMa_DanhMuc.setEditable(false);
        txtMa_DanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMa_DanhMucActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdConHangDanhMuc);
        rdConHangDanhMuc.setText("Còn Hàng");

        buttonGroup1.add(rdHetHangDanhMuc);
        rdHetHangDanhMuc.setText("Hết Hàng");

        jLabel21.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel21.setText("Danh Mục Sản Phẩm");

        jLabel23.setText("Tìm kiếm");

        btnTimKiem5.setText("Tìm kiếm");
        btnTimKiem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiem5ActionPerformed(evt);
            }
        });

        lblTrangThai_DanhMuc.setText("Trạng Thái");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblTrangThai_DanhMuc)
                                            .addComponent(jLabel23))
                                        .addGap(28, 28, 28)
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addComponent(rdConHangDanhMuc)
                                                .addGap(47, 47, 47)
                                                .addComponent(rdHetHangDanhMuc))
                                            .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(txtTen_TDanhMuc, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtMa_DanhMuc, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtTimkiemDanhMuc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))
                                                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addGap(18, 18, 18)
                                                .addComponent(btnTimKiem5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(lblTen_DanhMuc)
                                    .addComponent(lblMa_DanhMuc))
                                .addGap(0, 73, Short.MAX_VALUE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(btnCreate_DanhMuc)
                                .addGap(121, 121, 121)
                                .addComponent(btnUpdate_DanhMuc)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDelete_DanhMuc)
                                .addGap(96, 96, 96))))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimkiemDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(btnTimKiem5))
                .addGap(20, 20, 20)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMa_DanhMuc)
                    .addComponent(txtMa_DanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTen_DanhMuc)
                    .addComponent(txtTen_TDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdConHangDanhMuc)
                    .addComponent(rdHetHangDanhMuc)
                    .addComponent(lblTrangThai_DanhMuc))
                .addGap(67, 67, 67)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate_DanhMuc)
                    .addComponent(btnUpdate_DanhMuc)
                    .addComponent(btnDelete_DanhMuc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jfDanhMuc_SanPhamLayout = new javax.swing.GroupLayout(jfDanhMuc_SanPham.getContentPane());
        jfDanhMuc_SanPham.getContentPane().setLayout(jfDanhMuc_SanPhamLayout);
        jfDanhMuc_SanPhamLayout.setHorizontalGroup(
            jfDanhMuc_SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jfDanhMuc_SanPhamLayout.setVerticalGroup(
            jfDanhMuc_SanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblSize.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Tên Size", "Mô tả"
            }
        ));
        tblSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSizeMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblSizeMouseReleased(evt);
            }
        });
        jScrollPane5.setViewportView(tblSize);

        btnDelete_Size.setText("Delete");
        btnDelete_Size.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete_SizeActionPerformed(evt);
            }
        });

        btnUpdate_Size.setText("Update");
        btnUpdate_Size.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate_SizeActionPerformed(evt);
            }
        });

        btnCreate_Size.setText("Create");
        btnCreate_Size.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreate_SizeActionPerformed(evt);
            }
        });

        lblTen_Size.setText("Tên Size");

        lblMa_Size.setText("Mã Size");

        txtMa_Size.setEditable(false);
        txtMa_Size.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMa_SizeActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel24.setText("Size");

        jLabel25.setText("Tìm kiếm");

        btnTimKiem6.setText("Tìm kiếm");
        btnTimKiem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiem6ActionPerformed(evt);
            }
        });

        lblTen_Size1.setText("Mô tả");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25)
                                    .addComponent(lblTen_Size)
                                    .addComponent(lblTen_Size1))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtTen_Size, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtMa_Size, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtTimkiemSize, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                                        .addComponent(txtMota_Size, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGap(107, 107, 107)
                                        .addComponent(btnUpdate_Size))
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGap(112, 112, 112)
                                        .addComponent(jLabel24)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnDelete_Size)
                                    .addComponent(btnTimKiem6, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblMa_Size)
                            .addComponent(btnCreate_Size))
                        .addGap(0, 73, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel24)
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimkiemSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(btnTimKiem6))
                .addGap(20, 20, 20)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMa_Size)
                    .addComponent(txtMa_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTen_Size)
                    .addComponent(txtTen_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTen_Size1)
                    .addComponent(txtMota_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate_Size)
                    .addComponent(btnUpdate_Size)
                    .addComponent(btnDelete_Size))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jfSizeLayout = new javax.swing.GroupLayout(jfSize.getContentPane());
        jfSize.getContentPane().setLayout(jfSizeLayout);
        jfSizeLayout.setHorizontalGroup(
            jfSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jfSizeLayout.setVerticalGroup(
            jfSizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblMauSac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "STT", "Tên Màu "
            }
        ));
        tblMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMauSacMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblMauSacMouseReleased(evt);
            }
        });
        jScrollPane6.setViewportView(tblMauSac);

        btnDelete_MauSac.setText("Delete");
        btnDelete_MauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete_MauSacActionPerformed(evt);
            }
        });

        btnUpdate_MauSac.setText("Update");
        btnUpdate_MauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate_MauSacActionPerformed(evt);
            }
        });

        btnCreate_MauSac.setText("Create");
        btnCreate_MauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreate_MauSacActionPerformed(evt);
            }
        });

        txtTen_MauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTen_MauSacActionPerformed(evt);
            }
        });

        lblTen_MauSac.setText("Tên Màu");

        lblMa_MauSac.setText("Mã Màu");

        txtMa_MauSac.setEditable(false);
        txtMa_MauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMa_MauSacActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel26.setText("Màu Sắc");

        jLabel27.setText("Tìm kiếm");

        btnTimKiem7.setText("Tìm kiếm");
        btnTimKiem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiem7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addGap(35, 35, 35)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtTen_MauSac, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtMa_MauSac, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtTimkiemMauSac, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGap(107, 107, 107)
                                        .addComponent(btnUpdate_MauSac)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnDelete_MauSac)
                                    .addComponent(btnTimKiem7, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblTen_MauSac)
                            .addComponent(lblMa_MauSac)
                            .addComponent(btnCreate_MauSac))
                        .addGap(0, 73, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(230, 230, 230)
                .addComponent(jLabel26)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel26)
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimkiemMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(btnTimKiem7))
                .addGap(20, 20, 20)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMa_MauSac)
                    .addComponent(txtMa_MauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTen_MauSac)
                    .addComponent(txtTen_MauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate_MauSac)
                    .addComponent(btnUpdate_MauSac)
                    .addComponent(btnDelete_MauSac))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jfMauSacLayout = new javax.swing.GroupLayout(jfMauSac.getContentPane());
        jfMauSac.getContentPane().setLayout(jfMauSacLayout);
        jfMauSacLayout.setHorizontalGroup(
            jfMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jfMauSacLayout.setVerticalGroup(
            jfMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel1.setText("QUẢN LÝ SẢN PHẨM");

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cbbDanhMuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbThuongHieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        spnMinPrice.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 10000));

        spnMaxPrice.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 10000));

        jLabel18.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel18.setText("GIÁ THẤP NHẤT");

        jLabel19.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel19.setText("GIÁ CAO NHẤT");

        jrMoiGia.setText("Mọi giá");

        jrLocGia.setText("Lọc giá");
        jrLocGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrLocGiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spnMinPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(spnMaxPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jrLocGia))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jrMoiGia, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jrMoiGia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnMinPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnMaxPrice)
                    .addComponent(jrLocGia))
                .addContainerGap())
        );

        jLabel14.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel14.setText("DANH MỤC");

        jLabel15.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel15.setText("THƯƠNG HIỆU");

        jLabel16.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel16.setText("MÀU SẮC");

        jLabel17.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel17.setText("SIZE");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(cbbSize, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(134, 134, 134)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(51, 51, 51))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DANH SÁCH SẢN PHẨM", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

        tblDSSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblDSSanPham);

        btnBW.setText("<<");
        btnBW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBWActionPerformed(evt);
            }
        });

        btnFW.setText(">>");
        btnFW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFWActionPerformed(evt);
            }
        });

        jrTatCa.setText("Tất cả");
        jrTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrTatCaActionPerformed(evt);
            }
        });

        jrConHang.setText("Còn hàng");

        jrHetHang.setText("Hết hàng");
        jrHetHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrHetHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jrTatCa)
                        .addGap(28, 28, 28)
                        .addComponent(jrConHang)
                        .addGap(18, 18, 18)
                        .addComponent(jrHetHang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBW)
                        .addGap(65, 65, 65)
                        .addComponent(btnFW)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jrTatCa)
                        .addComponent(jrConHang)
                        .addComponent(jrHetHang)
                        .addComponent(btnBW, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnFW, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtTimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder("TÌM KIẾM"));
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        btnTimKiem.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnTimKiem.setText("TÌM KIẾM");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTimKiem)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnTimKiem)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnLoc.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnLoc.setText("LỌC");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        btnLamMoi.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLoc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(btnLoc)
                        .addGap(12, 12, 12)
                        .addComponent(btnLamMoi)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("DANH SÁCH", jPanel1);

        jPanel2.setLayout(null);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên sản phẩm"));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 271, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel3);
        jPanel3.setBounds(130, 90, 280, 294);

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("TÊN SẢN PHẨM");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("GIÁ SẢN PHẨM");

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("SỐ LƯỢNG");

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("DANH MỤC");

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("THƯƠNG HIỆU");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("MÀU SẢN PHẨM");

        jLabel8.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("SIZE SẢN PHẨM");

        lblMauSac.setBackground(new java.awt.Color(255, 255, 255));
        lblMauSac.setText("Chưa có màu ...");

        lblSize.setText("Chưa có size ...");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("MÔ TẢ"));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel12.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel12.setText("MÃ SẢN PHẨM");

        jLabel13.setText("jLabel13");

        jButton4.setText("Lấy mã ngẫu nhiên...");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        cbbMauSac2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbSize2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbSize2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSize2ActionPerformed(evt);
            }
        });

        btnThemMau.setText("Thêm");
        btnThemMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMauActionPerformed(evt);
            }
        });

        btnXoaMau.setText("Xoá");
        btnXoaMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaMauActionPerformed(evt);
            }
        });

        btnThemSize.setText("Thêm");
        btnThemSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSizeActionPerformed(evt);
            }
        });

        btnXoaSize.setText("Xoá");
        btnXoaSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSizeActionPerformed(evt);
            }
        });

        btnThem_DanhMucSP.setText("+");
        btnThem_DanhMucSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem_DanhMucSPActionPerformed(evt);
            }
        });

        btnThem_Mau.setText("+");
        btnThem_Mau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem_MauActionPerformed(evt);
            }
        });

        btnThem_ThuongHieu.setText("+");
        btnThem_ThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem_ThuongHieuActionPerformed(evt);
            }
        });

        btnThem_Size.setText("+");
        btnThem_Size.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem_SizeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnThem_DanhMucSP, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                                    .addComponent(btnThem_ThuongHieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSize, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbMauSac2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbSize2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnThem_Mau, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                            .addComponent(btnThem_Size, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnThemMau, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnXoaSize, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnThemSize, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnXoaMau, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(68, 68, 68))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2))
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3))
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel4))
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnThem_DanhMucSP)))
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnThem_ThuongHieu)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(lblMauSac))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(lblSize)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnThemMau)
                                    .addComponent(btnXoaSize)
                                    .addComponent(btnThem_Size))))
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemSize)
                            .addComponent(btnXoaMau)
                            .addComponent(btnThem_Mau)
                            .addComponent(cbbMauSac2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbSize2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel2.add(jPanel4);
        jPanel4.setBounds(500, 90, 620, 440);

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton7.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jButton7.setText("Thêm ảnh");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("jLabel11");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jButton7))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jButton7)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel5);
        jPanel5.setBounds(160, 420, 208, 87);

        jButton10.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jButton10.setText("THÊM");
        jPanel2.add(jButton10);
        jButton10.setBounds(650, 550, 80, 30);

        jButton11.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jButton11.setText("SỬA");
        jPanel2.add(jButton11);
        jButton11.setBounds(760, 550, 80, 30);

        jButton12.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jButton12.setText("XOÁ");
        jPanel2.add(jButton12);
        jButton12.setBounds(870, 550, 80, 30);

        jTabbedPane1.addTab("CHI TIẾT", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(472, 472, 472)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(372, 372, 372))
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1271, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBWActionPerformed
        // TODO add your handling code here:
        resetTableBW();
    }//GEN-LAST:event_btnBWActionPerformed

    private void btnFWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFWActionPerformed
        // TODO add your handling code here:
        resetTableFW();
    }//GEN-LAST:event_btnFWActionPerformed

    private void jrTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrTatCaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrTatCaActionPerformed

    private void jrHetHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrHetHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrHetHangActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        // TODO add your handling code here:
        ctsanphams = new chitietsanphamDAO().getLocSanpham(getLoc());
        resetTableBW();
    }//GEN-LAST:event_btnLocActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        LamMoiForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cbbSize2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSize2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSize2ActionPerformed

    private void btnThemMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMauActionPerformed
        // TODO add your handling code here:
        selectedMS.put(cbbMauSac2.getSelectedIndex(), true);
        reNewlblMauSac();
    }//GEN-LAST:event_btnThemMauActionPerformed

    private void btnXoaMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaMauActionPerformed
        // TODO add your handling code here:
        selectedMS.put(cbbMauSac2.getSelectedIndex(), false);
        reNewlblMauSac();
    }//GEN-LAST:event_btnXoaMauActionPerformed

    private void btnThemSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSizeActionPerformed
        // TODO add your handling code here:
        selectedSize.put(cbbSize2.getSelectedIndex(), true);
        reNewlblSize();
    }//GEN-LAST:event_btnThemSizeActionPerformed

    private void btnXoaSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSizeActionPerformed
        // TODO add your handling code here:
        selectedSize.put(cbbSize2.getSelectedIndex(), false);
        reNewlblSize();
    }//GEN-LAST:event_btnXoaSizeActionPerformed

    private void jrLocGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrLocGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrLocGiaActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void tbThuonHieuMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbThuonHieuMouseReleased
        // TODO add your handling code here:
        int row = tbThuonHieu.getSelectedRow();
        txtMa_ThuongHieu.setText(tbThuonHieu.getValueAt(row, 0).toString());
        txtTen_ThuongHieu.setText(tbThuonHieu.getValueAt(row, 1).toString());
        if (tbThuonHieu.getValueAt(row, 2).equals(0)) {
            rdHetHangThuongHieu.setSelected(true);
        } else if (tbThuonHieu.getValueAt(row, 2).equals(1)) {
            rdConHangThuongHieu.setSelected(true);
        }
        //        txtMa_TrongLuong.setText(tbTrongLuong.getValueAt(row, 0).toString());
    }//GEN-LAST:event_tbThuonHieuMouseReleased

    private void btnDeleteThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteThuongHieuActionPerformed
        // TODO add your handling code here:
        int rowIndex = tbThuonHieu.getSelectedRow();
        if (rowIndex >= 0) {
            int id = (int) model.getValueAt(rowIndex, 0);
            new thuongHieuDAO().delete(id);
            fillDataTableThuongHieu();
            clearThuongHieuForm();
            JOptionPane.showMessageDialog(this, "Xóa thương hiệu thành công.");
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để xóa.");
        }
    }//GEN-LAST:event_btnDeleteThuongHieuActionPerformed

    private void btnUpdateThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateThuongHieuActionPerformed
        // TODO add your handling code here:
        int rowIndex = tbThuonHieu.getSelectedRow();
        if (rowIndex >= 0) {
            thuonghieu th = setThuongHieuFormData();
            if (th != null) {
                new thuongHieuDAO().update(th);
                fillDataTableThuongHieu();
                clearThuongHieuForm();
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn thương hiệu cần sửa.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để sửa.");
        }
    }//GEN-LAST:event_btnUpdateThuongHieuActionPerformed

    private void btnCreate_ThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreate_ThuongHieuActionPerformed
        // TODO add your handling code here:
        if (validateThuongHieuForm()) {
            thuonghieu th = getThuongHieuFormData();
            new thuongHieuDAO().add(th);
            fillDataTableThuongHieu();
            clearThuongHieuForm();
        }
    }//GEN-LAST:event_btnCreate_ThuongHieuActionPerformed

    private void txtMa_ThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMa_ThuongHieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMa_ThuongHieuActionPerformed

    private void btnTimKiem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiem4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimKiem4ActionPerformed

    private void tblDanhMucMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhMucMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDanhMucMouseReleased

    private void btnDelete_DanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete_DanhMucActionPerformed
        // TODO add your handling code here:
        int rowIndex = tblDanhMuc.getSelectedRow();
        if (rowIndex >= 0) {
            int id = (int) model.getValueAt(rowIndex, 0);
            new danhMucDAO().delete(id);
            fillDataTableDanhMuc();
            clearDanhMucForm();
            JOptionPane.showMessageDialog(this, "Xóa danh mục thành công.");
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để xóa.");
        }
    }//GEN-LAST:event_btnDelete_DanhMucActionPerformed

    private void btnUpdate_DanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate_DanhMucActionPerformed
        // TODO add your handling code here:
        int rowIndex = tblDanhMuc.getSelectedRow();
        if (rowIndex >= 0) {
            danhmuc dm = setDanhMucFormData();
            if (dm != null) {
                new danhMucDAO().update(dm);
                fillDataTableDanhMuc();
                clearDanhMucForm();
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn danh mục cần sửa.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để sửa.");
        }
    }//GEN-LAST:event_btnUpdate_DanhMucActionPerformed

    private void btnCreate_DanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreate_DanhMucActionPerformed
        // TODO add your handling code here:
        if (validateDanhMucForm()) {
            danhmuc dm = getDanhMucFormData();
            new danhMucDAO().add(dm);
            fillDataTableDanhMuc();
            clearDanhMucForm();
        }
    }//GEN-LAST:event_btnCreate_DanhMucActionPerformed

    private void txtMa_DanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMa_DanhMucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMa_DanhMucActionPerformed

    private void btnTimKiem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiem5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimKiem5ActionPerformed

    private void tblSizeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSizeMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblSizeMouseReleased

    private void btnDelete_SizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete_SizeActionPerformed
        // TODO add your handling code here:
        int rowIndex = tblSize.getSelectedRow();
        if (rowIndex >= 0) {
            int id = (int) model.getValueAt(rowIndex, 0);
            new sizeDAO().delete(id);
            fillDataTableSize();
            clearSizeForm();
            JOptionPane.showMessageDialog(this, "Xóa size thành công.");
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để xóa.");
        }
    }//GEN-LAST:event_btnDelete_SizeActionPerformed

    private void btnUpdate_SizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate_SizeActionPerformed
        // TODO add your handling code here:
        int rowIndex = tblSize.getSelectedRow();
        if (rowIndex >= 0) {
            size s = setSizeFormData();
            if (s != null) {
                new sizeDAO().update(s);
                fillDataTableSize();
                clearSizeForm();
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn size cần sửa.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để sửa.");
        }
    }//GEN-LAST:event_btnUpdate_SizeActionPerformed

    private void btnCreate_SizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreate_SizeActionPerformed
        // TODO add your handling code here:
        if (validateSizeForm()) {
            size s = getSizeFormData();
            new sizeDAO().add(s);
            fillDataTableSize();
            clearSizeForm();
        }
    }//GEN-LAST:event_btnCreate_SizeActionPerformed

    private void txtMa_SizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMa_SizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMa_SizeActionPerformed

    private void btnTimKiem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiem6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimKiem6ActionPerformed

    private void tblMauSacMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMauSacMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblMauSacMouseReleased

    private void btnDelete_MauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete_MauSacActionPerformed
        // TODO add your handling code here:
        int rowIndex = tblMauSac.getSelectedRow();
        if (rowIndex >= 0) {
            int id = (int) model.getValueAt(rowIndex, 0);
            new mauSacDAO().delete(id);
            fillDataTableMauSac();
            clearMauSacForm();
            JOptionPane.showMessageDialog(this, "Xóa màu sắc thành công.");
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để xóa.");
        }
    }//GEN-LAST:event_btnDelete_MauSacActionPerformed

    private void btnUpdate_MauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate_MauSacActionPerformed
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(this, "Bạn muốn thêm dữ liệu?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            int rowIndex = tblMauSac.getSelectedRow();
            if (rowIndex >= 0) {
                mausac ms = setMauSacFormData();
                if (ms != null) {
                    boolean isUpdateMS =  new mauSacDAO().update(ms);
                    fillDataTableMauSac();
                    clearMauSacForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn màu sắc cần sửa.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để sửa.");
            }
        }else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để thêm.");
            }
    }//GEN-LAST:event_btnUpdate_MauSacActionPerformed

    private void btnCreate_MauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreate_MauSacActionPerformed
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(this, "Bạn muốn thêm dữ liệu?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            if (validateMauSacForm()) {
                mausac ms = getMauSacFormData();
                boolean isCreateMS = new mauSacDAO().add(ms);
                if (isCreateMS) {
                    fillDataTableMauSac();
                    clearMauSacForm();
                    JOptionPane.showMessageDialog(this, "thêm dữ liệu thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "thêm dữ liệu thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để thêm.");
            }
        }
    }//GEN-LAST:event_btnCreate_MauSacActionPerformed

    private void txtTen_MauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTen_MauSacActionPerformed
        // TODO add your handling code here:  

    }//GEN-LAST:event_txtTen_MauSacActionPerformed

    private void txtMa_MauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMa_MauSacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMa_MauSacActionPerformed

    private void btnTimKiem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiem7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimKiem7ActionPerformed

    private void btnThem_DanhMucSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem_DanhMucSPActionPerformed
        // TODO add your handling code here:
        jfDanhMuc_SanPham.setVisible(true);
    }//GEN-LAST:event_btnThem_DanhMucSPActionPerformed

    private void btnThem_MauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem_MauActionPerformed
        // TODO add your handling code here:
        jfMauSac.setVisible(true);
    }//GEN-LAST:event_btnThem_MauActionPerformed

    private void btnThem_ThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem_ThuongHieuActionPerformed
        // TODO add your handling code here:
        jfThuongHieu.setVisible(true);
    }//GEN-LAST:event_btnThem_ThuongHieuActionPerformed

    private void btnThem_SizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem_SizeActionPerformed
        // TODO add your handling code here:
        jfSize.setVisible(true);
    }//GEN-LAST:event_btnThem_SizeActionPerformed

    private void rdHetHangThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdHetHangThuongHieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdHetHangThuongHieuActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void txtMoTa_ThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMoTa_ThuongHieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMoTa_ThuongHieuActionPerformed

    private void tbThuonHieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbThuonHieuMouseClicked
        // TODO add your handling code here:
        int rowIndex = tbThuonHieu.getSelectedRow();
        txtMa_ThuongHieu.setText(tbThuonHieu.getValueAt(rowIndex, 0).toString());
        txtTen_ThuongHieu.setText(tbThuonHieu.getValueAt(rowIndex, 1).toString());
        String trangthai = tbThuonHieu.getValueAt(rowIndex, 2).toString();
        if (trangthai.equals("true")) {
            rdConHangThuongHieu.setSelected(true);
            rdHetHangThuongHieu.setSelected(false);
        } else {
            rdConHangThuongHieu.setSelected(false);
            rdHetHangThuongHieu.setSelected(true);
        }

    }//GEN-LAST:event_tbThuonHieuMouseClicked

    private void tblDanhMucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhMucMouseClicked
        int rowIndex = tblDanhMuc.getSelectedRow();
        txtMa_DanhMuc.setText(tblDanhMuc.getValueAt(rowIndex, 0).toString());
        txtTen_TDanhMuc.setText(tblDanhMuc.getValueAt(rowIndex, 1).toString());
        String trangthai = tblDanhMuc.getValueAt(rowIndex, 2).toString();
        if (trangthai.equals("true")) {
            rdConHangDanhMuc.setSelected(true);
            rdHetHangDanhMuc.setSelected(false);
        } else {

            rdConHangDanhMuc.setSelected(false);
            rdHetHangDanhMuc.setSelected(true);
        }
    }//GEN-LAST:event_tblDanhMucMouseClicked

    private void tblSizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSizeMouseClicked
        int rowIndex = tblSize.getSelectedRow();
        txtMa_Size.setText(tblSize.getValueAt(rowIndex, 0).toString());
        txtTen_Size.setText(tblSize.getValueAt(rowIndex, 1).toString());
        txtMota_Size.setText(tblSize.getValueAt(rowIndex, 2).toString());     // TODO add your handling code here:
    }//GEN-LAST:event_tblSizeMouseClicked

    private void tblMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMauSacMouseClicked
        int rowIndex = tblMauSac.getSelectedRow();
        txtMa_MauSac.setText(tblMauSac.getValueAt(rowIndex, 0).toString());
        txtTen_MauSac.setText(tblMauSac.getValueAt(rowIndex, 1).toString());
        // TODO add your handling code here:
    }//GEN-LAST:event_tblMauSacMouseClicked

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBW;
    private javax.swing.JButton btnCreate_DanhMuc;
    private javax.swing.JButton btnCreate_MauSac;
    private javax.swing.JButton btnCreate_Size;
    private javax.swing.JButton btnCreate_ThuongHieu;
    private javax.swing.JButton btnDeleteThuongHieu;
    private javax.swing.JButton btnDelete_DanhMuc;
    private javax.swing.JButton btnDelete_MauSac;
    private javax.swing.JButton btnDelete_Size;
    private javax.swing.JButton btnFW;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnThemMau;
    private javax.swing.JButton btnThemSize;
    private javax.swing.JButton btnThem_DanhMucSP;
    private javax.swing.JButton btnThem_Mau;
    private javax.swing.JButton btnThem_Size;
    private javax.swing.JButton btnThem_ThuongHieu;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTimKiem4;
    private javax.swing.JButton btnTimKiem5;
    private javax.swing.JButton btnTimKiem6;
    private javax.swing.JButton btnTimKiem7;
    private javax.swing.JButton btnUpdateThuongHieu;
    private javax.swing.JButton btnUpdate_DanhMuc;
    private javax.swing.JButton btnUpdate_MauSac;
    private javax.swing.JButton btnUpdate_Size;
    private javax.swing.JButton btnXoaMau;
    private javax.swing.JButton btnXoaSize;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbDanhMuc;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JComboBox<String> cbbMauSac2;
    private javax.swing.JComboBox<String> cbbSize;
    private javax.swing.JComboBox<String> cbbSize2;
    private javax.swing.JComboBox<String> cbbThuongHieu;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JFrame jfDanhMuc_SanPham;
    private javax.swing.JFrame jfMauSac;
    private javax.swing.JFrame jfSize;
    private javax.swing.JFrame jfThuongHieu;
    private javax.swing.JRadioButton jrConHang;
    private javax.swing.JRadioButton jrHetHang;
    private javax.swing.JRadioButton jrLocGia;
    private javax.swing.JRadioButton jrMoiGia;
    private javax.swing.JRadioButton jrTatCa;
    private javax.swing.JLabel lblMa_DanhMuc;
    private javax.swing.JLabel lblMa_MauSac;
    private javax.swing.JLabel lblMa_Size;
    private javax.swing.JLabel lblMa_ThuongHieu;
    private javax.swing.JLabel lblMauSac;
    private javax.swing.JLabel lblSize;
    private javax.swing.JLabel lblTen_DanhMuc;
    private javax.swing.JLabel lblTen_MauSac;
    private javax.swing.JLabel lblTen_Size;
    private javax.swing.JLabel lblTen_Size1;
    private javax.swing.JLabel lblTen_ThuongHieu;
    private javax.swing.JLabel lblTrangThai_DanhMuc;
    private javax.swing.JLabel lblTrangThai_ThuongHieu;
    private javax.swing.JLabel lblTrangThai_ThuongHieu1;
    private javax.swing.JRadioButton rdConHangDanhMuc;
    private javax.swing.JRadioButton rdConHangThuongHieu;
    private javax.swing.JRadioButton rdHetHangDanhMuc;
    private javax.swing.JRadioButton rdHetHangThuongHieu;
    private javax.swing.JSpinner spnMaxPrice;
    private javax.swing.JSpinner spnMinPrice;
    private javax.swing.JTable tbThuonHieu;
    private javax.swing.JTable tblDSSanPham;
    private javax.swing.JTable tblDanhMuc;
    private javax.swing.JTable tblMauSac;
    private javax.swing.JTable tblSize;
    private javax.swing.JTextField txtMa_DanhMuc;
    private javax.swing.JTextField txtMa_MauSac;
    private javax.swing.JTextField txtMa_Size;
    private javax.swing.JTextField txtMa_ThuongHieu;
    private javax.swing.JTextField txtMoTa_ThuongHieu;
    private javax.swing.JTextField txtMota_Size;
    private javax.swing.JTextField txtTen_MauSac;
    private javax.swing.JTextField txtTen_Size;
    private javax.swing.JTextField txtTen_TDanhMuc;
    private javax.swing.JTextField txtTen_ThuongHieu;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTimkiemDanhMuc;
    private javax.swing.JTextField txtTimkiemMauSac;
    private javax.swing.JTextField txtTimkiemSize;
    private javax.swing.JTextField txtTimkiemThuongHieu;
    // End of variables declaration//GEN-END:variables

    public void fillDataTableThuongHieu() {
        model = (DefaultTableModel) tbThuonHieu.getModel();
        model.setRowCount(0);
        List<thuonghieu> thl = new thuongHieuDAO().getAll();
        for (thuonghieu th : thl) {
            Object[] rowData = new Object[4];
            rowData[0] = th.getId();
            rowData[1] = th.getTen();
            rowData[2] = th.getMota();
            rowData[3] = th.isTrangthai();
            model.addRow(rowData);
        }
    }

    public void fillDataTableDanhMuc() {
        model = (DefaultTableModel) tblDanhMuc.getModel();
        model.setRowCount(0);
        List<danhmuc> dml = new danhMucDAO().getAll();
        for (danhmuc dm : dml) {
            Object[] rowData = new Object[3];
            rowData[0] = dm.getId();
            rowData[1] = dm.getTen();
            rowData[2] = dm.isTrangthai();
            model.addRow(rowData);
        }
    }

    public void fillDataTableMauSac() {
        model = (DefaultTableModel) tblMauSac.getModel();
        model.setRowCount(0);
        List<mausac> msl = new mauSacDAO().getAll();
        for (mausac ms : msl) {
            Object[] rowData = new Object[2];
            rowData[0] = ms.getId();
            rowData[1] = ms.getMacsac();
            model.addRow(rowData);
        }
    }

    public void fillDataTableSize() {
        model = (DefaultTableModel) tblSize.getModel();
        model.setRowCount(0);
        List<size> sl = new sizeDAO().getAll();
        for (size s : sl) {
            Object[] rowData = new Object[3];
            rowData[0] = s.getId();
            rowData[1] = s.getTen();
            rowData[2] = s.getMota();
            model.addRow(rowData);
        }
    }

    // Phương thức để lấy dữ liệu từ form
    private thuonghieu getThuongHieuFormData() {
        thuonghieu th = new thuonghieu();
        String ten = txtTen_ThuongHieu.getText();
        String mota = txtMoTa_ThuongHieu.getText();
        boolean trangthai = rdConHangThuongHieu.isSelected();
        th.setTen(ten);
        th.setMota(mota);
        th.setTrangthai(trangthai);
        return th;
    }

    private thuonghieu setThuongHieuFormData() {
        int id = Integer.valueOf(txtMa_ThuongHieu.getText());
        String ten = txtTen_ThuongHieu.getText();
        String mota = txtMoTa_ThuongHieu.getText();
        boolean trangthai = rdConHangThuongHieu.isSelected();
        return new thuonghieu(id, ten, mota, trangthai);
    }
// Phương thức để kiểm tra tính hợp lệ của dữ liệu nhập vào

    private boolean validateThuongHieuForm() {
        if (txtTen_ThuongHieu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu tên.");
            return false;
        }
        return true;
    }

// Phương thức để xóa trắng các trường nhập liệu
    private void clearThuongHieuForm() {
        txtMa_ThuongHieu.setText("");
        txtTen_ThuongHieu.setText("");
        txtMoTa_ThuongHieu.setText("");
        rdConHangThuongHieu.setSelected(false);
    }

// Phương thức để lấy dữ liệu từ form
    private danhmuc setDanhMucFormData() {
        int id = Integer.parseInt(txtMa_DanhMuc.getText());
        String ten = txtTen_TDanhMuc.getText();
        boolean trangthai = rdConHangDanhMuc.isSelected();
        return new danhmuc(id, ten, trangthai);
    }

    private danhmuc getDanhMucFormData() {
        danhmuc dm = new danhmuc();
        String ten = txtTen_TDanhMuc.getText();
        boolean trangthai = rdConHangDanhMuc.isSelected();
        dm.setTen(ten);
        dm.setTrangthai(trangthai);
        return dm;
    }

// Phương thức để kiểm tra tính hợp lệ của dữ liệu nhập vào
    private boolean validateDanhMucForm() {

        if (txtTen_TDanhMuc.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu tên.");
            return false;
        }
        return true;
    }

// Phương thức để xóa trắng các trường nhập liệu
    private void clearDanhMucForm() {
        txtMa_DanhMuc.setText("");
        txtTen_TDanhMuc.setText("");
        rdConHangDanhMuc.setSelected(false);
    }

// Phương thức để lấy dữ liệu từ form
    private mausac setMauSacFormData() {
        int id = Integer.parseInt(txtMa_MauSac.getText());
        String mausac = txtTen_MauSac.getText();
        return new mausac(id, mausac);
    }

    private mausac getMauSacFormData() {
        mausac ms = new mausac();
        String mausac = txtTen_MauSac.getText();
        ms.setMacsac(mausac);
        return ms;
    }

// Phương thức để kiểm tra tính hợp lệ của dữ liệu nhập vào
    private boolean validateMauSacForm() {

        if (txtTen_MauSac.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu mã màu.");
            return false;
        }
        return true;
    }

// Phương thức để xóa trắng các trường nhập liệu
    private void clearMauSacForm() {
        txtMa_MauSac.setText("");
        txtTen_MauSac.setText("");
    }

// Phương thức để lấy dữ liệu từ form
    private size setSizeFormData() {
        int id = Integer.parseInt(txtMa_Size.getText());
        String ten = txtTen_Size.getText();
        String mota = txtMota_Size.getText();
        return new size(id, ten, mota);
    }

    private size getSizeFormData() {
        size s = new size();
        int id = Integer.parseInt(txtMa_Size.getText());
        String ten = txtTen_Size.getText();
        String mota = txtMota_Size.getText();
        s.setTen(ten);
        s.setMota(mota);
        return s;
    }

// Phương thức để kiểm tra tính hợp lệ của dữ liệu nhập vào
    private boolean validateSizeForm() {

        if (txtTen_Size.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thiếu tên.");
            return false;
        }
        return true;
    }

// Phương thức để xóa trắng các trường nhập liệu
    private void clearSizeForm() {
        txtMa_Size.setText("");
        txtTen_Size.setText("");
        txtMota_Size.setText("");
    }

}
