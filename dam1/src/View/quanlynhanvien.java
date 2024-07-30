/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import DAO.mauSacDAO;
import DAO.nhanhVienDAO;
import entity.mausac;
import entity.nhanvien;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hauvv
 */
public class quanlynhanvien extends javax.swing.JPanel {

    DefaultTableModel model;
    nhanhVienDAO nvDao = new nhanhVienDAO();

    /**
     * Creates new form quanlynhanvien
     */
    public quanlynhanvien() {
        initComponents();
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        fillTable();
    }

    public void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);
        List<nhanvien> NhanVienList = nvDao.getAll();
        for (nhanvien nvDao : NhanVienList) {
            Object[] row = {nvDao.getId(),nvDao.getManv(), nvDao.getHoten(), nvDao.getEmail(), nvDao.getTendangnhap(), nvDao.getSdt(), nvDao.getMatkhau(), nvDao.getQuyenhan(), nvDao.isTrangthai()};
            model.addRow(row);
        }
        tblNhanVien.setModel(model);
    }

    private nhanvien getDaTa() {
        nhanvien nv = new nhanvien();
        String manv = txtMaNhanVien.getText();
        String tennv = txtTenNhanVien.getText();
        int sdt = Integer.parseInt(txtSDT.getText());
        String email = txtEmail.getText();
        String tendangnhap = txtTenDN.getText();
        String matkhau = txtMatKhau.getText();
        boolean quyenhan = jrNhanVien.isSelected();
        boolean trangThai = jrLam.isSelected();
        nv.setHoten(tennv);
        nv.setSdt(sdt);
        nv.setEmail(email);
        nv.setTendangnhap(tendangnhap);
        nv.setMatkhau(matkhau);
        nv.setQuyenhan(quyenhan);
        nv.isTrangthai();
        return nv;
    }

    private nhanvien setDaTa() {
        String manv = txtMaNhanVien.getText();
        String tennv = txtTenNhanVien.getText();
        int sdt = Integer.parseInt(txtSDT.getText());
        String email = txtEmail.getText();
        String tendangnhap = txtTenDN.getText();
        String matkhau = txtMatKhau.getText();
        boolean quyenhan = jrNhanVien.isSelected();
        boolean trangThai = jrLam.isSelected();
        return new nhanvien(0, manv, tennv, sdt, email, tendangnhap, matkhau, quyenhan, trangThai);
    }

    public void clearTable() {
        txtID.setText("");
        txtMaNhanVien.setText("");
        txtTenNhanVien.setText("");
        txtEmail.setText("");
        txtSDT.setText("");
        txtTenDN.setText("");
        txtMatKhau.setText("");
        jrNhanVien.setSelected(false);
        jrLam.setSelected(false);
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnXoa_NV = new javax.swing.JButton();
        btnSua_NV = new javax.swing.JButton();
        btnThem_NV = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTenNhanVien = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtTenDN = new javax.swing.JTextField();
        txtMatKhau = new javax.swing.JTextField();
        jrNhanVien = new javax.swing.JRadioButton();
        jrQuanLy = new javax.swing.JRadioButton();
        jButton4 = new javax.swing.JButton();
        txtSDT = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtMaNhanVien = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jrLam = new javax.swing.JRadioButton();
        jrNghi = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("Tên nhân viên");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("Email");

        btnXoa_NV.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnXoa_NV.setText("xóa");
        btnXoa_NV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa_NVActionPerformed(evt);
            }
        });

        btnSua_NV.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnSua_NV.setText("sửa");
        btnSua_NV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua_NVActionPerformed(evt);
            }
        });

        btnThem_NV.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnThem_NV.setText("Thêm");
        btnThem_NV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem_NVActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("Tên đăng nhập");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("Mật khẩu");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Quyền hạn");

        txtTenNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenNhanVienActionPerformed(evt);
            }
        });

        buttonGroup1.add(jrNhanVien);
        jrNhanVien.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jrNhanVien.setText("Nhân viên");

        buttonGroup1.add(jrQuanLy);
        jrQuanLy.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jrQuanLy.setText("Quản lý");

        jButton4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton4.setText("Hiện mật khẩu");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Số điện thoại");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("Mã nhân viên");

        txtMaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNhanVienActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel10.setText("Trạng thái");

        buttonGroup2.add(jrLam);
        jrLam.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jrLam.setText("Làm việc");

        buttonGroup2.add(jrNghi);
        jrNghi.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jrNghi.setText("Nghỉ việc");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel11.setText("STT");

        txtID.setEditable(false);
        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(75, 75, 75)
                                .addComponent(jrLam))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(69, 69, 69)
                                .addComponent(jrNhanVien)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jrQuanLy)
                            .addComponent(jrNghi))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                        .addGap(4, 4, 4)))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton4))
                                    .addComponent(txtTenDN)
                                    .addComponent(txtEmail)
                                    .addComponent(txtSDT)
                                    .addComponent(txtTenNhanVien)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(btnThem_NV)
                .addGap(84, 84, 84)
                .addComponent(btnSua_NV)
                .addGap(95, 95, 95)
                .addComponent(btnXoa_NV)
                .addContainerGap(139, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenDN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jrNhanVien)
                    .addComponent(jrQuanLy))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jrLam)
                    .addComponent(jrNghi))
                .addGap(42, 42, 42)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem_NV)
                    .addComponent(btnSua_NV)
                    .addComponent(btnXoa_NV))
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(303, 303, 303)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(306, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("cập nhật ", jPanel2);

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã nhân viên", "Tên nhân viên", "Số điện thoại", "Email", "Tên đăng nhập", "Mật khẩu", "Quyền hạn", "Trạng thái"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel8.setText("Tìm kiếm");

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jButton5.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTextField6))
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jButton5)
                .addContainerGap(137, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 998, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("danh sách", jPanel1);

        jLabel6.setText("QUẢN LÝ NHÂN VIÊN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(471, 471, 471)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel6)
                .addGap(29, 29, 29)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void btnThem_NVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem_NVActionPerformed
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(this, "Bạn muốn thêm dữ liệu?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            try {
                nhanvien newNhanVien = getDaTa();
                boolean isAdded = nvDao.add(newNhanVien);
                if (isAdded) {
                    fillTable();
                    JOptionPane.showMessageDialog(this, "Thêm dữ liệu thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm dữ liệu thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
        clearTable();
    }//GEN-LAST:event_btnThem_NVActionPerformed

    private void btnSua_NVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua_NVActionPerformed
        int rowIndex = tblNhanVien.getSelectedRow();
        nhanvien nv = new nhanvien();
        int result = JOptionPane.showConfirmDialog(this, "Bạn muốn sửa dữ liệu?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION){
        if (rowIndex >= 0) {
            nv = setDaTa();
            if (nv != null) {
                boolean isUpdate =  new nhanhVienDAO().update(nv);
                if (isUpdate) {
                    fillTable();
                    JOptionPane.showMessageDialog(this, "Sửa dữ liệu thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa dữ liệu thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần sửa.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để sửa.");
        }
        clearTable();
        }
    }//GEN-LAST:event_btnSua_NVActionPerformed

    private void btnXoa_NVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa_NVActionPerformed
        int rowIndex = tblNhanVien.getSelectedRow();
        nhanvien nv = new nhanvien();
          int result = JOptionPane.showConfirmDialog(this, "Bạn muốn xoá dữ liệu?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION){
        if (rowIndex >= 0) {
            int id = (int) model.getValueAt(rowIndex, 0);
            boolean isDelete =   new nhanhVienDAO().delete(id);
            if (isDelete) {
                    fillTable();
                    JOptionPane.showMessageDialog(this, "Xoá dữ liệu thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Xoá dữ liệu thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để xoá.");
        }
        }
    }//GEN-LAST:event_btnXoa_NVActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:
        int row = tblNhanVien.getSelectedRow();
        txtID.setText(tblNhanVien.getValueAt(row, 0).toString());
        txtMaNhanVien.setText(tblNhanVien.getValueAt(row, 1).toString());
        txtTenNhanVien.setText(tblNhanVien.getValueAt(row, 2).toString());
        txtEmail.setText(tblNhanVien.getValueAt(row, 3).toString());
        txtSDT.setText(tblNhanVien.getValueAt(row, 4).toString());
        txtTenDN.setText(tblNhanVien.getValueAt(row, 5).toString());
        txtMatKhau.setText(tblNhanVien.getValueAt(row, 6).toString());
        String quyenHan = tblNhanVien.getValueAt(row, 7).toString();
        String trangthai = tblNhanVien.getValueAt(row, 8).toString();
        if (quyenHan.equals("true")) {
            jrNhanVien.setSelected(true);
            jrQuanLy.setSelected(false);
        } else {
            jrNhanVien.setSelected(false);
            jrQuanLy.setSelected(true);
        }
        if (trangthai.equals("true")) {
            jrLam.setSelected(true);
            jrNghi.setSelected(false);
        } else {
            jrLam.setSelected(false);
            jrNghi.setSelected(true);
        }
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void txtTenNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenNhanVienActionPerformed

    private void txtMaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNhanVienActionPerformed

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua_NV;
    private javax.swing.JButton btnThem_NV;
    private javax.swing.JButton btnXoa_NV;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JRadioButton jrLam;
    private javax.swing.JRadioButton jrNghi;
    private javax.swing.JRadioButton jrNhanVien;
    private javax.swing.JRadioButton jrQuanLy;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenDN;
    private javax.swing.JTextField txtTenNhanVien;
    // End of variables declaration//GEN-END:variables
}
