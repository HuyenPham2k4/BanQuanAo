/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Helper.JDBCHelper;
import View.viewModel.ProductDetail;
import entity.chitietsanpham;
import entity.hoadon;
import entity.khachHang;
import entity.sanpham;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huyen
 */
public class hoaDonDAO implements IHDRepo {

    JDBCHelper helper;

    public hoaDonDAO() {
        helper = new JDBCHelper();
    }
    // thêm
    @Override
    public boolean add(hoadon hd) {
        String sql = "INSERT INTO HOADON (ID_NV, ID_KH, MaVocher, ThoiGian, GhiChu, TT_ThanhToan, TongTien, TrangThai) VALUES (?, ?, ?, ?, ?, 0, ?, 1)";
        helper.executeUpdate(sql, hd.getIdnv(), hd.getId_kh(), hd.getMavocher(), hd.getThoigian(), hd.getGhichu(), hd.getTongtien());
        return true;
    }
    // thanh toán
    public boolean thanhtoan(int id) {
        String sql = "UPDATE HOADON SET TrangThai = 0 WHERE ID = ?";
        helper.executeUpdate(sql, id);
        return true;
    }
    // huỷ
    public boolean huyHD(int id) {
        String sql = "UPDATE HOADON SET TrangThai = 2 WHERE ID = ?";
        helper.executeUpdate(sql, id);
        return true;
    }
    // sửa hoá đơn
    @Override
    public boolean update(hoadon hd) {
        String sql = "UPDATE HOADON SET ID_NV = ?,ID_KH=? , MaVocher = ?,  GhiChu = ?, TT_ThanhToan = ?, TongTien = ?, TrangThai = 1 WHERE ID = ?";
        helper.executeUpdate(sql, hd.getIdnv(), hd.getId_kh(), hd.getMavocher(),  hd.getGhichu(), hd.getTtthanhtoan(), hd.getTongtien(), hd.getId());
        return true;
    }
    //xoá
    @Override
    public boolean delete(hoadon hd) {
        String sql = "DELETE FROM HOADON WHERE ID = ?";
        helper.executeUpdate(sql, hd.getId());
        return true;
    }
    // lấy all nhưng phải inner join và left join với bảng khách hàng và cthd
    @Override
    public List<hoadon> getAll() {
        return selectBySQL("SELECT \n"
                + "    HOADON.ID, \n"
                + "    HOADON.ID_NV, \n"
                + "    HOADON.ID_KH, \n"
                + "    KHACHHANG.TenKhachHang,\n"
                + "    KHACHHANG.SoDienThoai, \n"
                + "    HOADON.MaVocher, \n"
                + "    HOADON.ThoiGian, \n"
                + "    HOADON.GhiChu, \n"
                + "    HOADON.TT_ThanhToan, \n"
                + "    SUM(CTHOADON.Gia) AS TongTien, \n"
                + "    HOADON.TrangThai\n"
                + "FROM \n"
                + "    HOADON \n"
                + "    LEFT JOIN CTHOADON ON HOADON.ID = CTHOADON.ID_HD\n"
                + "    INNER JOIN KHACHHANG ON HOADON.ID_KH = KHACHHANG.ID\n"
                + "WHERE \n"
                + "    HOADON.TrangThai = 1\n"
                + "GROUP BY 	 		\n"
                + "    HOADON.ID, 		\n"
                + "    HOADON.ID_NV, 		\n"
                + "    HOADON.ID_KH, \n"
                + "    KHACHHANG.TenKhachHang,\n"
                + "    KHACHHANG.SoDienThoai, 		\n"
                + "    HOADON.MaVocher, 		\n"
                + "    HOADON.ThoiGian, 		\n"
                + "    HOADON.GhiChu, 		\n"
                + "    HOADON.TT_ThanhToan, 		\n"
                + "    HOADON.TrangThai;");
    }
    // tim theo id van phai join
    public List<hoadon> getHDByID(int id) {
        return selectBySQL("SELECT \n"
                + "    HOADON.ID, \n"
                + "    HOADON.ID_NV, \n"
                + "    HOADON.ID_KH, \n"
                + "    KHACHHANG.TenKhachHang,\n"
                + "    KHACHHANG.SoDienThoai, \n"
                + "    HOADON.MaVocher, \n"
                + "    HOADON.ThoiGian, \n"
                + "    HOADON.GhiChu, \n"
                + "    HOADON.TT_ThanhToan, \n"
                + "    SUM(CTHOADON.Gia) AS TongTien, \n"
                + "    HOADON.TrangThai\n"
                + "FROM \n"
                + "    HOADON \n"
                + "    LEFT JOIN CTHOADON ON HOADON.ID = CTHOADON.ID_HD\n"
                + "    INNER JOIN KHACHHANG ON HOADON.ID_KH = KHACHHANG.ID\n"
                + "WHERE \n"
                + "    HOADON.ID = " + id + "\n"
                + "GROUP BY 	 		\n"
                + "    HOADON.ID, 		\n"
                + "    HOADON.ID_NV, 		\n"
                + "    HOADON.ID_KH, \n"
                + "    KHACHHANG.TenKhachHang,\n"
                + "    KHACHHANG.SoDienThoai, 		\n"
                + "    HOADON.MaVocher, 		\n"
                + "    HOADON.ThoiGian, 		\n"
                + "    HOADON.GhiChu, 		\n"
                + "    HOADON.TT_ThanhToan, 		\n"
                + "    HOADON.TrangThai;");
    }

    public List<hoadon> getAllByDathanhtoanvsDaHuy() {
        return selectByHoaDon("SELECT \n"
                + "    HOADON.ID, \n"
                + "    HOADON.ID_NV, \n"
                + "    HOADON.ID_KH, \n"
                + "    HOADON.MaVocher, \n"
                + "    HOADON.ThoiGian, \n"
                + "    HOADON.GhiChu, \n"
                + "    HOADON.TT_ThanhToan, \n"
                + "    SUM(CTHOADON.Gia) AS TongTien, \n"
                + "    HOADON.TrangThai\n"
                + "FROM \n"
                + "    HOADON \n"
                + "    LEFT JOIN CTHOADON ON HOADON.ID = CTHOADON.ID_HD\n"
                + "WHERE \n"
                + "    HOADON.TrangThai IN (0,2)\n"
                + "GROUP BY 	 		\n"
                + "    HOADON.ID, 		\n"
                + "    HOADON.ID_NV, 		\n"
                + "    HOADON.ID_KH,    	\n"
                + "    HOADON.MaVocher, 		\n"
                + "    HOADON.ThoiGian, 		\n"
                + "    HOADON.GhiChu, 		\n"
                + "    HOADON.TT_ThanhToan, 		\n"
                + "    HOADON.TrangThai;");
    }

    public List<hoadon> getAllByDathanhtoan() {
        return selectBySQL("SELECT \n"
                + "    HOADON.ID, \n"
                + "    HOADON.ID_NV, \n"
                + "    HOADON.ID_KH, \n"
                + "    KHACHHANG.TenKhachHang,\n"
                + "    KHACHHANG.SoDienThoai, \n"
                + "    HOADON.MaVocher, \n"
                + "    HOADON.ThoiGian, \n"
                + "    HOADON.GhiChu, \n"
                + "    HOADON.TT_ThanhToan, \n"
                + "    SUM(CTHOADON.Gia) AS TongTien, \n"
                + "    HOADON.TrangThai\n"
                + "FROM \n"
                + "    HOADON \n"
                + "    LEFT JOIN CTHOADON ON HOADON.ID = CTHOADON.ID_HD\n"
                + "    INNER JOIN KHACHHANG ON HOADON.ID_KH = KHACHHANG.ID\n"
                + "WHERE \n"
                + "    HOADON.TrangThai = 0\n"
                + "GROUP BY 	 		\n"
                + "    HOADON.ID, 		\n"
                + "    HOADON.ID_NV, 		\n"
                + "    HOADON.ID_KH, \n"
                + "    KHACHHANG.TenKhachHang,\n"
                + "    KHACHHANG.SoDienThoai, 		\n"
                + "    HOADON.MaVocher, 		\n"
                + "    HOADON.ThoiGian, 		\n"
                + "    HOADON.GhiChu, 		\n"
                + "    HOADON.TT_ThanhToan, 		\n"
                + "    HOADON.TrangThai;");
    }

    public List<hoadon> getAllByDaHuy() {
        return selectBySQL("SELECT \n"
                + "    HOADON.ID, \n"
                + "    HOADON.ID_NV, \n"
                + "    HOADON.ID_KH, \n"
                + "    KHACHHANG.TenKhachHang,\n"
                + "    KHACHHANG.SoDienThoai, \n"
                + "    HOADON.MaVocher, \n"
                + "    HOADON.ThoiGian, \n"
                + "    HOADON.GhiChu, \n"
                + "    HOADON.TT_ThanhToan, \n"
                + "    SUM(CTHOADON.Gia) AS TongTien, \n"
                + "    HOADON.TrangThai\n"
                + "FROM \n"
                + "    HOADON \n"
                + "    LEFT JOIN CTHOADON ON HOADON.ID = CTHOADON.ID_HD\n"
                + "    INNER JOIN KHACHHANG ON HOADON.ID_KH = KHACHHANG.ID\n"
                + "WHERE \n"
                + "    HOADON.TrangThai = 2\n"
                + "GROUP BY 	 		\n"
                + "    HOADON.ID, 		\n"
                + "    HOADON.ID_NV, 		\n"
                + "    HOADON.ID_KH, \n"
                + "    KHACHHANG.TenKhachHang,\n"
                + "    KHACHHANG.SoDienThoai, 		\n"
                + "    HOADON.MaVocher, 		\n"
                + "    HOADON.ThoiGian, 		\n"
                + "    HOADON.GhiChu, 		\n"
                + "    HOADON.TT_ThanhToan, 		\n"
                + "    HOADON.TrangThai;");
    }

    @Override
    public List<hoadon> selectBySQL(String sql, Object... args) {
        List<hoadon> lstHD = new ArrayList<>();
        try {
            ResultSet rs = helper.executeQuery(sql, args);
            while (rs.next()) {
                hoadon hd = new hoadon();
                hd.setId(rs.getInt("ID"));//id hd
                hd.setIdnv(rs.getInt("ID_NV"));// id nhan vien
                hd.setId_kh(rs.getInt("ID_KH"));// id khach hang 
                hd.setSdt(rs.getString("SoDienThoai"));// sdt
                hd.setTenkh(rs.getString("TenKhachHang")); // ten kh 
                hd.setMavocher(rs.getString("MaVocher")); // ma voucher
                hd.setThoigian(rs.getString("ThoiGian")); // thoi gian tao hd
                hd.setGhichu(rs.getString("GhiChu"));// ghi chu 
                hd.setTtthanhtoan(rs.getInt("TT_ThanhToan"));// tt thanh toan
                hd.setTongtien(rs.getInt("TongTien"));// tong tien san pham
                hd.setTrangthai(rs.getInt("TrangThai"));// trang thai
                lstHD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHD;
    }
    
    public List<hoadon> selectByHoaDon(String sql, Object... args) {
        List<hoadon> lstHD = new ArrayList<>();
        try {
            ResultSet rs = helper.executeQuery(sql, args);
            while (rs.next()) {
                hoadon hd = new hoadon();
                hd.setId(rs.getInt("ID"));//id hd
                hd.setIdnv(rs.getInt("ID_NV"));// id nhan vien
                hd.setId_kh(rs.getInt("ID_KH"));// id khach hang 
                hd.setMavocher(rs.getString("MaVocher")); // ma voucher
                hd.setThoigian(rs.getString("ThoiGian")); // thoi gian tao hd
                hd.setGhichu(rs.getString("GhiChu"));// ghi chu 
                hd.setTtthanhtoan(rs.getInt("TT_ThanhToan"));// tt thanh toan
                hd.setTongtien(rs.getInt("TongTien"));// tong tien san pham
                hd.setTrangthai(rs.getInt("TrangThai"));// trang thai
                lstHD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHD;
    }

    @Override
    public hoadon findByID(int id) {
        List<hoadon> lsthd = selectBySQL("SELECT \n"
                + "    HOADON.ID, \n"
                + "    HOADON.ID_NV, \n"
                + "    HOADON.ID_KH, \n"
                + "    KHACHHANG.TenKhachHang,\n"
                + "    KHACHHANG.SoDienThoai, \n"
                + "    HOADON.MaVocher, \n"
                + "    HOADON.ThoiGian, \n"
                + "    HOADON.GhiChu, \n"
                + "    HOADON.TT_ThanhToan, \n"
                + "    SUM(CTHOADON.Gia) AS TongTien, \n"
                + "    HOADON.TrangThai\n"
                + "FROM \n"
                + "    HOADON \n"
                + "    LEFT JOIN CTHOADON ON HOADON.ID = CTHOADON.ID_HD\n"
                + "    INNER JOIN KHACHHANG ON HOADON.ID_KH = KHACHHANG.ID\n"
                + "WHERE \n"
                + "    HOADON.ID = "+id+"\n"
                + "GROUP BY 	 		\n"
                + "    HOADON.ID, 		\n"
                + "    HOADON.ID_NV, 		\n"
                + "    HOADON.ID_KH, \n"
                + "    KHACHHANG.TenKhachHang,\n"
                + "    KHACHHANG.SoDienThoai, 		\n"
                + "    HOADON.MaVocher, 		\n"
                + "    HOADON.ThoiGian, 		\n"
                + "    HOADON.GhiChu, 		\n"
                + "    HOADON.TT_ThanhToan, 		\n"
                + "    HOADON.TrangThai;");
        if (lsthd.isEmpty()) {
            return null;
        }
        // lấy phần tử đầu tiên của list
        return lsthd.get(0);
    }

    
    public List<hoadon> findByDate(String ngaylap) {
        return selectByHoaDon("SELECT * FROM HOADON WHERE CONVERT(VARCHAR, ThoiGian, 120) LIKE '%" + ngaylap + "%'");
    }

    public List<ProductDetail> selectAllByProduct(String sql, Object... args) {
        List<ProductDetail> lst = new ArrayList<>();
        try {
            ResultSet rs = helper.executeQuery(sql, args);
            while (rs.next()) {
                ProductDetail prd = new ProductDetail();
                prd.setProductID(rs.getInt("ProductID"));
                prd.setProductName(rs.getString("ProductName"));
                prd.setProductDescription(rs.getString("ProductDescription"));
                prd.setProductImage(rs.getString("ProductImage"));
                prd.setQuantity(rs.getInt("Quantity"));
                prd.setPrice(rs.getDouble("Price"));
                prd.setProductStatus(rs.getBoolean("ProductStatus"));
                prd.setCategoryName(rs.getString("CategoryName"));
                prd.setBrandName(rs.getString("BrandName"));
                prd.setSizeNames(rs.getString("SizeNames"));
                prd.setColorNames(rs.getString("ColorNames"));
                lst.add(prd);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    public ProductDetail findProductByID(int id) {
        List<ProductDetail> detail = selectAllByProduct("select * from ProductDetails where ProductID = " + id + " AND ProductStatus = 1");
        if (detail.isEmpty()) {
            return null;
        }
        return detail.get(0);
    }

    public List<ProductDetail> fillAllProduct() {
        return selectAllByProduct("select * from ProductDetails where ProductStatus = 1");
    }

    public List<ProductDetail> findByNameProduct(String name) {
        return selectAllByProduct("select * from ProductDetails where ProductName LIKE N'%" + name + "%'");
    }

    public List<ProductDetail> findByCategory(String name) {
        return selectAllByProduct("select * from ProductDetails where CategoryName LIKE N'%" + name + "%'");
    }

    public List<ProductDetail> findByColorProduct(String name) {
        return selectAllByProduct("select * from ProductDetails where ColorNames LIKE N'%" + name + "%'");
    }

    public List<ProductDetail> findBySizeProduct(String name) {
        return selectAllByProduct("select * from ProductDetails where SizeNames LIKE N'%" + name + "%'");
    }

    public List<ProductDetail> findByBrandProduct(String name) {
        return selectAllByProduct("select * from ProductDetails where BrandName LIKE N'%" + name + "%'");
    }

}
