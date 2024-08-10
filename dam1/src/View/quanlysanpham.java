/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

/**
 *
 * @author letnp
 */
import javax.swing.table.DefaultTableModel;
import DAO.danhMucDAO;
import DAO.thuongHieuDAO;
import DAO.sizeDAO;
import DAO.mauSacDAO;

import DAO.chitietsanphamDAO;
import DAO.chiTietSizeDAO;
import DAO.chiTietMauSacDAO;
import DAO.sanPhamDAO;
import entity.chitietsanpham;
import entity.chitietmausac;
import entity.chitietsize;
import entity.danhmuc;
import entity.thuonghieu;
import entity.size;
import entity.mausac;
import entity.sanpham;
import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
public class quanlysanpham extends javax.swing.JPanel {

    /**
     * Creates new form quanlysanpham
     */
    DefaultTableModel modelBW;
    DefaultTableModel modelFW;
    List<chitietsanpham> ctsanphams;
    HashMap<Integer,Boolean> selectedMS;
    HashMap<Integer,Boolean> selectedSize;
    List<size> sizes;
    List<mausac> mausacs;
    int trangThaiSP;
    sanpham SelectedSanpham;
    public quanlysanpham() {
        initComponents();
        initComponents();
        timKiemListenDoc();
        trangThaiSP = 0;
        sizes = new sizeDAO().getAll();
        mausacs = new mauSacDAO().getAll();
        ctsanphams = new chitietsanphamDAO().getAll(trangThaiSP);
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
        refreshSPForm();
    }
    void timKiemByKeyword(String keyword){
        ctsanphams = new chitietsanphamDAO().FindByKeyword(keyword, trangThaiSP);
        resetTableBW();
    }
    void timKiemListenDoc(){
                txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                performSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                performSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                performSearch();
            }

            private void performSearch() {
                // Perform your search operation
                timKiemByKeyword(txtTimKiem.getText());
                resetTableBW();
            }
        });
    }
    sanpham getSanPhamfromForm(){
        return new sanpham(
                lblMaSP.getText().equals("")?-1:Integer.parseInt(lblMaSP.getText()),
                txtTenSP.getText(),
                cbbCTDanhMuc.getSelectedIndex()+1,
                cbbCTThuongHieu.getSelectedIndex()+1,
                taeMoTa.getText(),
                lblFileName.getText(),
                Integer.parseInt(spnSoLuong.getValue().toString()),
                Integer.parseInt(spnGiaSP.getValue().toString()),
                jrConHang2.isSelected()?true:false
        );
    }
    void reLoadCTSanPhams(){
        ctsanphams = txtTimKiem.equals("")? new chitietsanphamDAO().getAll(trangThaiSP) : new chitietsanphamDAO().FindByKeyword(txtTimKiem.getText(), trangThaiSP);
    }
    void updateMauSacSP(int ID_SP){
        new chiTietMauSacDAO().deleteByIDSP(ID_SP);
        for (Map.Entry<Integer, Boolean> entry : selectedMS.entrySet()){
            if (entry.getValue()){
                new chiTietMauSacDAO().add(new chitietmausac(-1, (int) entry.getKey()+1, ID_SP,true));
            }
        }
    }
    void updateSizeSP(int ID_SP){
        new chiTietSizeDAO().deleteByIDSP(ID_SP);
        for (Map.Entry<Integer, Boolean> entry : selectedSize.entrySet()){
            if (entry.getValue()){
                new chiTietSizeDAO().add(new chitietsize(-1, ID_SP , (int) entry.getKey()+1,true));
            }
        }
    }
    void copyImgToDB(File srcFile){
        String des = System.getProperty("user.dir")+"\\src\\ANH_CSDL_270x270px";
        if (srcFile.exists()){
            File desFile = new File(des + File.separator + srcFile.getName());
            try {
                Files.copy(srcFile.toPath(), desFile.toPath(),StandardCopyOption.REPLACE_EXISTING);
            
        } catch (Exception e) {
        }
        }
    }
    void SetAnh(String imageName){
        lblAnh.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\src\\ANH_CSDL_270x270px\\"+imageName));
    }
    void initSelectedMS(){
        selectedMS = new HashMap<>();
        for(mausac x: mausacs){
            selectedMS.put(x.getId()-1, false);
        }
        reNewlblMauSac();
    }
    void initSelectedSize(){
        selectedSize = new HashMap<>();
        for(size x:sizes){
            selectedSize.put(x.getId()-1, false);
        }
        reNewlblSize();
    }
    void resetTableBW(){
        reLoadCTSanPhams();
        modelBW = new DefaultTableModel( new Object[]{
            "Mã SP", "Tên SP", "Số lượng SP", "Giá SP", "Mô tả"  
        }, 0);
        for (chitietsanpham x: ctsanphams){
            modelBW.addRow(new Object[]{
                x.getMasp(), x.getTensp(), x.getSoLuong(), x.getGia(), x.getMoTa()
            }); 
        }
        tblDSSanPham.setModel(modelBW); 
    }
        void resetTableFW(){
        modelFW = new DefaultTableModel( new Object[]{
            "Mã SP", "Tên SP", "Size", "Màu sắc", "Danh mục", "Thương hiệu"  
        }, 0);
        for (chitietsanpham x: ctsanphams){
            modelFW.addRow(new Object[]{
                x.getMasp(), x.getTensp(), x.getSizesp(), x.getMauSacsp(), x.getDanhMuc(), x.getThuongHieu()
            }); 
        }
        tblDSSanPham.setModel(modelFW); 
    }
    void getDanhMuc(){
        List<danhmuc> danhmucs = new danhMucDAO().getAll();
        cbbDanhMuc.removeAllItems();
        cbbCTDanhMuc.removeAllItems();
        cbbDanhMuc.addItem("Tất cả");
        for(danhmuc x :danhmucs){
            cbbDanhMuc.addItem(x.getTen());
            cbbCTDanhMuc.addItem(x.getTen());
        }
    }
    void getThuongHieu(){
        List<thuonghieu> thuonghieus = new thuongHieuDAO().getAll();
        cbbThuongHieu.removeAllItems();
        cbbCTThuongHieu.removeAllItems();
        cbbThuongHieu.addItem("Tất cả");
        for(thuonghieu x :thuonghieus){
            cbbThuongHieu.addItem(x.getTen());
            cbbCTThuongHieu.addItem(x.getTen());
        }
    }
    void getSizeSP(){
        
        cbbSize.removeAllItems();
        cbbSize.addItem("Tất cả");
        cbbCTSize.removeAllItems();
        for(size x :sizes){
            cbbSize.addItem(x.getTen());
            cbbCTSize.addItem(x.getTen());
        }
    } 
    void getMauSac(){
        cbbCTMauSac.removeAllItems();
        cbbMauSac.removeAllItems();
        cbbMauSac.addItem("Tất cả");
        for(mausac x :mausacs){
            cbbMauSac.addItem(x.getMacsac());
            cbbCTMauSac.addItem(x.getMacsac());
        }
    }
    void LamMoiForm(){
        cbbDanhMuc.setSelectedIndex(0);
        cbbMauSac.setSelectedIndex(0);
        cbbSize.setSelectedIndex(0);
        cbbThuongHieu.setSelectedIndex(0);
        spnMaxPrice.setValue(0);
        spnMinPrice.setValue(0);
        jrMoiGia.setSelected(true);
        jrTatCa.setSelected(true);
        ctsanphams = new chitietsanphamDAO().getAll(trangThaiSP);
        txtTimKiem.setText("");
        resetTableBW();
        
    }
    String getLoc(){
        String DKquery = "";
        List <String> dieuKien = new ArrayList<String>();
        dieuKien.add( cbbDanhMuc.getSelectedIndex()== 0 ? "":("dm.ID = "+ cbbDanhMuc.getSelectedIndex()));
        dieuKien.add( cbbThuongHieu.getSelectedIndex() == 0? "":("th.ID = "+ cbbThuongHieu.getSelectedIndex()));
        dieuKien.add( cbbMauSac.getSelectedIndex() == 0?"":("ctm.ID_MS = "+ cbbMauSac.getSelectedIndex()));
        dieuKien.add( cbbSize.getSelectedIndex() == 0?"":("cts.ID_Size = "+cbbSize.getSelectedIndex()));
        dieuKien.add( jrLocGia.isSelected() ? ("sp.gia BETWEEN "+spnMinPrice.getValue()+" and "+ spnMaxPrice.getValue()):"");
        for(String x: dieuKien){
            DKquery += DKquery.equals("")?"": x.equals("")?"":" AND ";
            DKquery += x;
        }
        return DKquery;
    }
    void reNewlblMauSac(){
        String ms = "";
        for (Map.Entry<Integer, Boolean> entry : selectedMS.entrySet()) {
            if (entry.getValue()) {
                ms += mausacs.get(entry.getKey()).getMacsac()+", ";
            }
        }
        lblMauSac.setText(ms.equals("") ? "Chưa có màu sắc...": ms);
    }
    void reNewlblSize(){
        String sz = "";
        for (Map.Entry<Integer, Boolean> entry : selectedSize.entrySet()) {
            if (entry.getValue()) {
                sz += sizes.get(entry.getKey()).getTen()+", ";
            }
        }
        lblSize.setText(sz.equals("") ? "Chưa có size...": sz);
    }
    int selectedSanPhamIndex;
    void fillFromSanpham(){
        sanpham sp = new sanPhamDAO().findByID(selectedSanPhamIndex);
        
        lblMaSP.setText(String.valueOf(sp.getID()));
        txtTenSP.setText(sp.getTenSP());
        int gia = (int)Math.floor(sp.getGia());
        spnGiaSP.setValue(gia);
        cbbCTDanhMuc.setSelectedIndex(sp.getID_DMSP()-1);
        cbbCTThuongHieu.setSelectedIndex(sp.getID_TH()-1);
        taeMoTa.setText(sp.getMota());
        spnSoLuong.setValue(sp.getSoLuong());
        SetAnh(sp.getAnhSP());
        
        getSelectedSPMauSac();
        getSelectedSPSize();
        tab.setSelectedIndex(1);
    }
    void getSelectedSPMauSac(){
        List<chitietmausac> spMauSacs = new chiTietMauSacDAO().findByID_SP(selectedSanPhamIndex);
        for(chitietmausac x : spMauSacs){
            selectedMS.put(x.getIdms()-1, true);
        }
        reNewlblMauSac();
    }
    void getSelectedSPSize(){
        List<chitietsize> spSizes = new chiTietSizeDAO().findByID_SP(selectedSanPhamIndex);
        for(chitietsize x : spSizes){
            selectedSize.put(x.getIdsize()-1, true);
        }
        reNewlblSize();
    }
    void refreshSPForm(){
        lblMaSP.setText("");
        initSelectedMS();
        initSelectedSize();
        spnGiaSP.setValue(0);
        spnSoLuong.setValue(0);
        txtTenSP.setText("");
        cbbCTDanhMuc.setSelectedIndex(0);
        cbbCTThuongHieu.setSelectedIndex(0);
        cbbCTSize.setSelectedIndex(0);
        cbbCTMauSac.setSelectedIndex(0);
        taeMoTa.setText("");
        lblAnh.setIcon(null);
        lblFileName.setText("");
        jrConHang2.setSelected(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tab = new javax.swing.JTabbedPane();
        jPanel30 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        cbbDanhMuc = new javax.swing.JComboBox<>();
        cbbThuongHieu = new javax.swing.JComboBox<>();
        cbbSize = new javax.swing.JComboBox<>();
        cbbMauSac = new javax.swing.JComboBox<>();
        jPanel28 = new javax.swing.JPanel();
        spnMinPrice = new javax.swing.JSpinner();
        spnMaxPrice = new javax.swing.JSpinner();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jrLocGia = new javax.swing.JRadioButton();
        jrMoiGia = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        btnBW = new javax.swing.JButton();
        btnFW = new javax.swing.JButton();
        jrTatCa = new javax.swing.JRadioButton();
        jrConHang = new javax.swing.JRadioButton();
        jrHetHang = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDSSanPham = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        btnLoc = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        spnSoLuong = new javax.swing.JSpinner();
        cbbCTDanhMuc = new javax.swing.JComboBox<>();
        cbbCTThuongHieu = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblMauSac = new javax.swing.JLabel();
        lblSize = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taeMoTa = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        lblMaSP = new javax.swing.JLabel();
        cbbCTMauSac = new javax.swing.JComboBox<>();
        cbbCTSize = new javax.swing.JComboBox<>();
        btnThemMau = new javax.swing.JButton();
        btnXoaMau = new javax.swing.JButton();
        btnThemSize = new javax.swing.JButton();
        btnXoaSize = new javax.swing.JButton();
        spnGiaSP = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        jrConHang2 = new javax.swing.JRadioButton();
        jrHetHang2 = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        btnThemAnh = new javax.swing.JButton();
        lblFileName = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        lblAnh = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel1.setText("QUẢN LÝ SẢN PHẨM");

        jPanel30.setBorder(null);

        jPanel29.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cbbDanhMuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbThuongHieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        spnMinPrice.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 10000));

        spnMaxPrice.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 10000));

        jLabel32.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel32.setText("GIÁ THẤP NHẤT");

        jLabel33.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel33.setText("GIÁ CAO NHẤT");

        jrLocGia.setText("Lọc giá");

        jrMoiGia.setText("Mọi giá");

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spnMinPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jrLocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jrMoiGia, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(spnMaxPrice)
                        .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)))
                .addGap(85, 85, 85))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnMinPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnMaxPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrLocGia)
                    .addComponent(jrMoiGia)))
        );

        jLabel14.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel14.setText("DANH MỤC");

        jLabel15.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel15.setText("THƯƠNG HIỆU");

        jLabel16.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel16.setText("MÀU SẮC");

        jLabel17.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel17.setText("SIZE");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbSize, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DANH SÁCH SẢN PHẨM", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

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
        jrConHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrConHangActionPerformed(evt);
            }
        });

        jrHetHang.setText("Hết hàng");
        jrHetHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrHetHangActionPerformed(evt);
            }
        });

        tblDSSanPham.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDSSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDSSanPham);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jrTatCa)
                .addGap(28, 28, 28)
                .addComponent(jrConHang)
                .addGap(18, 18, 18)
                .addComponent(jrHetHang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 841, Short.MAX_VALUE)
                .addComponent(btnBW)
                .addGap(65, 65, 65)
                .addComponent(btnFW)
                .addContainerGap())
            .addComponent(jScrollPane3)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jrTatCa)
                        .addComponent(jrConHang)
                        .addComponent(jrHetHang)
                        .addComponent(btnBW, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnFW, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtTimKiem.setBackground(new java.awt.Color(255, 255, 255));
        txtTimKiem.setText("jTextField3");
        txtTimKiem.setBorder(javax.swing.BorderFactory.createTitledBorder("TÌM KIẾM"));
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 1209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLoc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLamMoi, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(26, 26, 26))))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(btnLoc)
                        .addGap(14, 14, 14)
                        .addComponent(btnLamMoi)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab.addTab("DANH SÁCH", jPanel30);

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("TÊN SẢN PHẨM");

        txtTenSP.setText("jTextField1");

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

        spnSoLuong.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 10));

        cbbCTDanhMuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbCTThuongHieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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

        taeMoTa.setColumns(20);
        taeMoTa.setRows(5);
        jScrollPane1.setViewportView(taeMoTa);

        jLabel12.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel12.setText("MÃ SẢN PHẨM");

        lblMaSP.setText("jLabel13");

        cbbCTMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbCTSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbCTSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbCTSizeActionPerformed(evt);
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

        spnGiaSP.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 10000));

        jLabel9.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel9.setText("TRẠNG THÁI");

        jrConHang2.setText("Còn hàng");
        jrConHang2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrConHang2ActionPerformed(evt);
            }
        });

        jrHetHang2.setText("Hết hàng");

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
                        .addComponent(lblMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spnGiaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(cbbCTDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(cbbCTThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(190, 190, 190)
                                .addComponent(cbbCTMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addComponent(btnThemMau, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoaMau, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSize, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(190, 190, 190)
                                .addComponent(cbbCTSize, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addComponent(btnThemSize, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoaSize, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jrConHang2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(jrHetHang2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(lblMaSP))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2))
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(spnGiaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel4))
                    .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5))
                    .addComponent(cbbCTDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6))
                    .addComponent(cbbCTThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbCTMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThemMau)
                        .addComponent(btnXoaMau))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(lblMauSac))))
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbCTSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThemSize)
                        .addComponent(btnXoaSize))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(lblSize))))
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jrConHang2)
                    .addComponent(jrHetHang2))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnThemAnh.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnThemAnh.setText("Thêm ảnh");
        btnThemAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemAnhActionPerformed(evt);
            }
        });

        lblFileName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFileName.setText("jLabel11");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(btnThemAnh))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblFileName, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(btnThemAnh)
                .addGap(18, 18, 18)
                .addComponent(lblFileName)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        btnThem.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnThem.setText("THÊM");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnSua.setText("SỬA");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnXoa.setText("XOÁ");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        lblAnh.setBorder(javax.swing.BorderFactory.createTitledBorder("Ảnh sản phẩm"));

        jButton13.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jButton13.setText("LÀM MỚI");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(128, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(119, 119, 119)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(140, 140, 140))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnXoa)
                        .addComponent(jButton13)))
                .addGap(117, 117, 117))
        );

        tab.addTab("CHI TIẾT", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(372, 372, 372))
            .addComponent(tab)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        if(jrTatCa.isSelected()){
            trangThaiSP = 0;
            resetTableBW();
        }
    }//GEN-LAST:event_jrTatCaActionPerformed

    private void jrConHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrConHangActionPerformed
        // TODO add your handling code here:

        if(jrConHang.isSelected()){
            trangThaiSP = 1;
            resetTableBW();
        }
    }//GEN-LAST:event_jrConHangActionPerformed

    private void jrHetHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrHetHangActionPerformed
        // TODO add your handling code here:

        if(jrHetHang.isSelected()){
            trangThaiSP = -1;
            resetTableBW();
        }
    }//GEN-LAST:event_jrHetHangActionPerformed

    private void tblDSSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSSanPhamMouseClicked
        // TODO add your handling code here:
        selectedSanPhamIndex = Integer.parseInt(tblDSSanPham.getValueAt(tblDSSanPham.getSelectedRow(), 0).toString());
        fillFromSanpham();
    }//GEN-LAST:event_tblDSSanPhamMouseClicked

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        // TODO add your handling code here:
        ctsanphams = new chitietsanphamDAO().getLocSanpham(getLoc());
        modelBW = new DefaultTableModel( new Object[]{
            "Mã SP", "Tên SP", "Số lượng SP", "Giá SP", "Mô tả"
        }, 0);
        for (chitietsanpham x: ctsanphams){
            modelBW.addRow(new Object[]{
                x.getMasp(), x.getTensp(), x.getSoLuong(), x.getGia(), x.getMoTa()
            });
        }
        tblDSSanPham.setModel(modelBW);
    }//GEN-LAST:event_btnLocActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        LamMoiForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void cbbCTSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbCTSizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbCTSizeActionPerformed

    private void btnThemMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMauActionPerformed
        // TODO add your handling code here:
        selectedMS.put(cbbCTMauSac.getSelectedIndex(), true);
        reNewlblMauSac();
    }//GEN-LAST:event_btnThemMauActionPerformed

    private void btnXoaMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaMauActionPerformed
        // TODO add your handling code here:
        selectedMS.put(cbbCTMauSac.getSelectedIndex(), false);
        reNewlblMauSac();
    }//GEN-LAST:event_btnXoaMauActionPerformed

    private void btnThemSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSizeActionPerformed
        // TODO add your handling code here:
        selectedSize.put(cbbCTSize.getSelectedIndex(), true);
        reNewlblSize();
    }//GEN-LAST:event_btnThemSizeActionPerformed

    private void btnXoaSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSizeActionPerformed
        // TODO add your handling code here:
        selectedSize.put(cbbCTSize.getSelectedIndex(), false);
        reNewlblSize();
    }//GEN-LAST:event_btnXoaSizeActionPerformed

    private void jrConHang2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrConHang2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrConHang2ActionPerformed
    File fileImg;
    private void btnThemAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemAnhActionPerformed
        // TODO add your handling code here:

        JFileChooser fileChooserImg = new JFileChooser();
        FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("img", "jpg","png");
        fileChooserImg.setFileFilter(imgFilter);
        int x = fileChooserImg.showDialog(this, "Chon file");
        if (x == JFileChooser.APPROVE_OPTION){
            fileImg = fileChooserImg.getSelectedFile();
            lblFileName.setText(fileImg.getName());
            lblAnh.setIcon(new ImageIcon(fileImg.toString()));
            //            taeMoTa.setText(fileImg.toString());
        }
    }//GEN-LAST:event_btnThemAnhActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        try {
            if(fileImg == null){
                JOptionPane.showMessageDialog(this, "Hãy chọn ảnh trước khi thêm !", "Cảnh báo",0);
            }
            else{
                copyImgToDB(fileImg);
                sanpham newsp = getSanPhamfromForm();
                new sanPhamDAO().add(newsp);
                int newIDSP = new sanPhamDAO().newestSP_ID();
                updateMauSacSP(newIDSP);
                updateSizeSP(newIDSP);
                JOptionPane.showMessageDialog(this, "Bạn đã thêm sản phẩm "+newsp.getTenSP()+" !");
                resetTableBW();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi "+e+" !");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        try {
            sanpham sp = getSanPhamfromForm();
            new sanPhamDAO().update(sp);
            updateMauSacSP(sp.getID());
            updateSizeSP(sp.getID());
            JOptionPane.showMessageDialog(this, "Bạn đã thay đổi thông thin sản phẩm thành công !");
            resetTableBW();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi "+e+" !");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        try {
            int response = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá sản phẩm này không ?", "Xoá", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response ==JOptionPane.YES_OPTION){
                new chiTietMauSacDAO().deleteByIDSP(selectedSanPhamIndex);
                new chiTietSizeDAO().deleteByIDSP(selectedSanPhamIndex);
                new sanPhamDAO().delete(selectedSanPhamIndex);
            }
            resetTableBW();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi "+e+" !");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        refreshSPForm();
    }//GEN-LAST:event_jButton13ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBW;
    private javax.swing.JButton btnFW;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemAnh;
    private javax.swing.JButton btnThemMau;
    private javax.swing.JButton btnThemSize;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaMau;
    private javax.swing.JButton btnXoaSize;
    private javax.swing.JComboBox<String> cbbCTDanhMuc;
    private javax.swing.JComboBox<String> cbbCTMauSac;
    private javax.swing.JComboBox<String> cbbCTSize;
    private javax.swing.JComboBox<String> cbbCTThuongHieu;
    private javax.swing.JComboBox<String> cbbDanhMuc;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JComboBox<String> cbbSize;
    private javax.swing.JComboBox<String> cbbThuongHieu;
    private javax.swing.JButton jButton13;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton jrConHang;
    private javax.swing.JRadioButton jrConHang2;
    private javax.swing.JRadioButton jrHetHang;
    private javax.swing.JRadioButton jrHetHang2;
    private javax.swing.JRadioButton jrLocGia;
    private javax.swing.JRadioButton jrMoiGia;
    private javax.swing.JRadioButton jrTatCa;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblFileName;
    private javax.swing.JLabel lblMaSP;
    private javax.swing.JLabel lblMauSac;
    private javax.swing.JLabel lblSize;
    private javax.swing.JSpinner spnGiaSP;
    private javax.swing.JSpinner spnMaxPrice;
    private javax.swing.JSpinner spnMinPrice;
    private javax.swing.JSpinner spnSoLuong;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JTextArea taeMoTa;
    private javax.swing.JTable tblDSSanPham;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
