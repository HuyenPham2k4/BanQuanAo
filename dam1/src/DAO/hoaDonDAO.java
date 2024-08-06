/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Helper.JDBCHelper;
import View.viewModel.ProductDetail;
import entity.chitietsanpham;
import entity.hoadon;
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

    @Override
    public boolean add(hoadon hd) {
        String sql = "INSERT INTO HOADON (ID_NV, MaVocher, ThoiGian, GhiChu, TT_ThanhToan, TongTien, TrangThai) VALUES (?, ?, ?, ?, 0, ?, 1)";
        helper.executeUpdate(sql, hd.getIdnv(), hd.getMavocher(), hd.getThoigian(), hd.getGhichu(), hd.getTongtien());
        return true;
    }
    
    public boolean thanhtoan(int id){
        String sql = "UPDATE HOADON SET TrangThai = 0 WHERE ID = ?";
        helper.executeUpdate(sql, id);
        return true;
    }
    
    @Override
    public boolean update(hoadon hd) {
        String sql = "UPDATE HOADON SET ID_NV = ?, MaVocher = ?, ThoiGian = ?, GhiChu = ?, TT_ThanhToan = ?, TongTien = ?, TrangThai = 1 WHERE ID = ?";
        helper.executeUpdate(sql, hd.getIdnv(), hd.getMavocher(), hd.getThoigian(), hd.getGhichu(), hd.getTtthanhtoan(), hd.getTongtien(),  hd.getId());
        return true;
    }

    @Override
    public boolean delete(hoadon hd) {
        String sql = "DELETE FROM HOADON WHERE ID = ?";
        helper.executeUpdate(sql, hd.getId());
        return true;
    }

    @Override
    public List<hoadon> getAll() {
        return selectBySQL("select \n"
                + "		HOADON.ID,\n"
                + "		HOADON.ID_NV,\n"
                + "		HOADON.MaVocher,\n"
                + "		HOADON.ThoiGian,\n"
                + "		HOADON.GhiChu,\n"
                + "		HOADON.TT_ThanhToan,\n"
                + "		SUM(CTHOADON.Gia) AS TongTien,\n"
                + "		HOADON.TrangThai\n"
                + "	From \n"
                + "		HOADON\n"
                + "	LEFT JOIN CTHOADON ON HOADON.ID  = CTHOADON.ID_HD \n"
                + "	WHERE HOADON.TrangThai =1\n"
                + "	 GROUP BY 	\n"
                + "		HOADON.ID,\n"
                + "		HOADON.ID_NV,\n"
                + "		HOADON.MaVocher,\n"
                + "		HOADON.ThoiGian,\n"
                + "		HOADON.GhiChu,\n"
                + "		HOADON.TT_ThanhToan,\n"
                + "		HOADON.TrangThai");
    }
    
    public List<hoadon> getAllByDathanhtoan() {
        return selectBySQL("select \n"
                + "		HOADON.ID,\n"
                + "		HOADON.ID_NV,\n"
                + "		HOADON.MaVocher,\n"
                + "		HOADON.ThoiGian,\n"
                + "		HOADON.GhiChu,\n"
                + "		HOADON.TT_ThanhToan,\n"
                + "		SUM(CTHOADON.Gia) AS TongTien,\n"
                + "		HOADON.TrangThai\n"
                + "	From \n"
                + "		HOADON\n"
                + "	LEFT JOIN CTHOADON ON HOADON.ID  = CTHOADON.ID_HD \n"
                + "	WHERE HOADON.TrangThai =0\n"
                + "	 GROUP BY 	\n"
                + "		HOADON.ID,\n"
                + "		HOADON.ID_NV,\n"
                + "		HOADON.MaVocher,\n"
                + "		HOADON.ThoiGian,\n"
                + "		HOADON.GhiChu,\n"
                + "		HOADON.TT_ThanhToan,\n"
                + "		HOADON.TrangThai");
    }
    
    
    @Override
    public List<hoadon> selectBySQL(String sql, Object... args) {
        List<hoadon> lstHD = new ArrayList<>();
        try {
            ResultSet rs = helper.executeQuery(sql, args);
            while (rs.next()) {
                hoadon hd = new hoadon();
                hd.setId(rs.getInt("ID"));
                hd.setIdnv(rs.getInt("ID_NV"));
                hd.setMavocher(rs.getString("MaVocher"));
                hd.setThoigian(rs.getString("ThoiGian"));
                hd.setGhichu(rs.getString("GhiChu"));
                hd.setTtthanhtoan(rs.getInt("TT_ThanhToan"));
                hd.setTongtien(rs.getInt("TongTien"));
                hd.setTrangthai(rs.getBoolean("TrangThai"));
                lstHD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstHD;
    }

    @Override
    public hoadon findByID(int id) {
        List<hoadon> lsthd = selectBySQL("SELECT * FROM HOADON WHERE ID = ?", id);
        if(lsthd.isEmpty()){
            return null;
        }
        return lsthd.get(0);
    }

    @Override
    public List<hoadon> findByName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
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

    public ProductDetail fillSLvsGiaProduct(int id) {
        List<ProductDetail> detail = selectAllByProduct("select * from ProductView where ProductID = " + id + " AND ProductStatus = 1");
        if (detail.isEmpty()) {
            return null;
        }
        return detail.get(0);
    }

    public List<ProductDetail> fillAllProduct() {
        return selectAllByProduct("select * from ProductView where ProductStatus = 1");
    }

    public List<ProductDetail> findByNameProduct(String name) {
        return selectAllByProduct("select * from ProductView where ProductName LIKE N'%" + name + "%'");
    }

    public List<ProductDetail> findByCategory(String name) {
        return selectAllByProduct("select * from ProductView where CategoryName = '" + name + "'");
    }

    public List<ProductDetail> findByColorProduct(String name) {
        return selectAllByProduct("select * from ProductView where ColorNames = '" + name + "'");
    }

    public List<ProductDetail> findBySizeProduct(String name) {
        return selectAllByProduct("select * from ProductView where SizeNames = '" + name + "'");
    }

    public List<ProductDetail> findByBrandProduct(String name) {
        return selectAllByProduct("select * from ProductView where BrandName = '" + name + "'");
    }
}
