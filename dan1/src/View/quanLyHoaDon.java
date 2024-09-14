/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import DAO.chiTietHoaDonDAO;
import DAO.hoaDonDAO;
import Helper.Auth;
import Helper.Ivoice;
import Helper.VoucherSupport;
//import static View.quanLyBanHang.tblHoaDon_HoaDon;
import entity.chitiethoadon;
import entity.hoadon;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author huyen
 */
public class quanLyHoaDon extends javax.swing.JPanel {

    private int id_HD;
    hoaDonDAO hddao;
    DefaultTableModel model;
    chiTietHoaDonDAO cthdDAO;

    /**
     * Creates new form quanLyHoaDon
     */
    public quanLyHoaDon() {
        initComponents();
        hddao = new hoaDonDAO();
        cthdDAO = new chiTietHoaDonDAO();
//        model = (DefaultTableModel) tblCthd.getModel();      
        fillDataTableHoaDon(hddao.getAllByDathanhtoanvsDaHuy());
        fill();
    }

    void fill() {
        List<hoadon> lst = hddao.getAllByDathanhtoanvsDaHuy();
        fillDataTableHoaDon(lst);

        // Kiểm tra xem bảng có dữ liệu hay không
        int rowCount = tblHoaDon.getRowCount();
        if (rowCount > 0) {
            // Chọn dòng cuối cùng
            tblHoaDon.setRowSelectionInterval(rowCount - 1, rowCount - 1);

            // Lấy thông tin hóa đơn cuối cùng và hiển thị
            id_HD = Integer.parseInt(tblHoaDon.getValueAt(rowCount - 1, 0).toString());
            fillDataTableChiTietHoaDon(id_HD);
            lblThoiGian.setText(tblHoaDon.getValueAt(rowCount - 1, 1).toString());
            TTCT();
        }
    }

    void TTCT() {
        lblMahd.setText("Mã hóa đơn: " + String.valueOf(id_HD));
        lblNhanVien.setText("Nhân viên: " + Auth.user.getHoten());
//        lblTongTien.setText("Tổng tiền: " + String.valueOf(Ivoice.hd.getTtthanhtoan()) + " VND");
    }

    void fillDataTableChiTietHoaDon(int id) {
        model = (DefaultTableModel) tblCthd.getModel();
        model.setRowCount(0);

        for (chitiethoadon cthd : cthdDAO.findByIDHD(id)) {
            Object[] rowData = new Object[6];
            rowData[0] = cthd.getIdctsp();
            rowData[1] = cthd.getTenSP();
            rowData[2] = cthd.getSoluong();
            rowData[3] = cthd.getDon_gia();
            rowData[4] = cthd.getGia();
            rowData[5] = cthd.isTrangthai();
            model.addRow(rowData);
        }
    }

    void fillDataTableHoaDon(List<hoadon> lhd) {
        model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        for (hoadon hd : lhd) {
            Object[] rowData = new Object[5];
            rowData[0] = hd.getId();
            rowData[1] = hd.getThoigian();
            rowData[2] = hd.getTongtien();
            rowData[3] = hd.getTtthanhtoan();
            if(hd.getTrangthai() == 2){
                rowData[4] = "Đã huỷ";
            }
            else if(hd.getTrangthai() == 0){
                rowData[4] = "Đã thanh toán";
            }
            
            model.addRow(rowData);
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

        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        lblMahd = new javax.swing.JLabel();
        lblNhanVien = new javax.swing.JLabel();
        lblThoiGian = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCthd = new javax.swing.JTable();
        dateCbb = new com.toedter.calendar.JDateChooser();
        btnLoc = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Hoá đơn"));

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Ngày lập", "Tổng tiền", "TT_ Thanh Toán", "Trạng thái"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHoaDon);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblMahd.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        lblMahd.setText("Mã hóa đơn:");

        lblNhanVien.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        lblNhanVien.setText("Nhân viên:");

        lblThoiGian.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        lblThoiGian.setText("Thời gian :");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("HÓA ĐƠN");

        tblCthd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID_SP", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        jScrollPane1.setViewportView(tblCthd);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblMahd, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 8, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(lblMahd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNhanVien)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblThoiGian)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        dateCbb.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                dateCbbAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        btnLoc.setText("Lọc Ngày");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dateCbb, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLoc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReset)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateCbb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnLoc)
                                .addComponent(btnReset)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        int index = tblHoaDon.getSelectedRow();
        if (index >= 0) {
            // Lấy ID hóa đơn được chọn
            id_HD = Integer.parseInt(tblHoaDon.getValueAt(index, 0).toString());

            // Thiết lập ID hóa đơn được chọn để sử dụng sau này
            this.id_HD = id_HD;

            // Điền bảng chi tiết hóa đơn với thông tin của hóa đơn đã chọn
            fillDataTableChiTietHoaDon(id_HD);
            lblThoiGian.setText(tblHoaDon.getValueAt(index, 1).toString());

            // Cập nhật các nhãn với thông tin hóa đơn đã chọn
            TTCT();
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void dateCbbAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_dateCbbAncestorAdded
        // TODO add your handling code here:

    }//GEN-LAST:event_dateCbbAncestorAdded

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        // TODO add your handling code here:
        Calendar selectedDate = dateCbb.getCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(selectedDate.getTime());
        System.out.println("Date:" + formattedDate);

        List<hoadon> lsthd = hddao.findByDate(formattedDate);
        fillDataTableHoaDon(lsthd);
    }//GEN-LAST:event_btnLocActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        fill();
        
    }//GEN-LAST:event_btnResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnReset;
    private com.toedter.calendar.JDateChooser dateCbb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblMahd;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblThoiGian;
    private javax.swing.JTable tblCthd;
    private javax.swing.JTable tblHoaDon;
    // End of variables declaration//GEN-END:variables
}