/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Helper.JDBCHelper;
import entity.hoadon;

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
        String sql = "INSERT INTO HOADON (ID_NV, MaVocher, ThoiGian, GhiChu, TT_ThanhToan, TenKhachHang, SoDienThoaiKH, TongTien, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        helper.executeUpdate(sql, hd.getIdnv(), hd.getMavocher(), hd.getThoigian(), hd.getGhichu(), hd.getTtthanhtoan(), hd.getTenkhachhang(), hd.getSodienthoaikhachhang(), hd.getTongtien(), hd.isTrangthai());
        return true;
    }

    @Override
    public boolean update(hoadon hd) {
        String sql = "UPDATE HOADON SET ID_NV = ?, MaVocher = ?, ThoiGian = ?, GhiChu = ?, TT_ThanhToan = ?, TenKhachHang = ?, SoDienThoaiKH = ?, TongTien = ?, TrangThai = ? WHERE ID = ?";
        helper.executeUpdate(sql, hd.getIdnv(), hd.getMavocher(), hd.getThoigian(), hd.getGhichu(), hd.getTtthanhtoan(), hd.getTenkhachhang(), hd.getSodienthoaikhachhang(), hd.getTongtien(), hd.isTrangthai(), hd.getId());
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
        return selectBySQL("SELECT * FROM HOADON");
    }

    @Override
    public List<hoadon> selectBySQL(String sql, Object... args) {
        List<hoadon> lstHD = new ArrayList<>();
        try {
            ResultSet rs = helper.executeQuery(sql, args);
            while (rs.next()) {
                hoadon hd = new hoadon(0, 0, 0, sql, sql, 0, 0, 0, 0, true);
                hd.setId(rs.getInt("ID"));
                hd.setIdnv(rs.getInt("ID_NV"));
                hd.setMavocher(rs.getInt("MaVocher"));
                hd.setThoigian(rs.getString("ThoiGian"));
                hd.setGhichu(rs.getString("GhiChu"));
                hd.setTtthanhtoan(rs.getInt("TT_ThanhToan"));
                hd.setTenkhachhang(rs.getInt("TenKhachHang"));
                hd.setSodienthoaikhachhang(rs.getInt("SoDienThoaiKH"));
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
    public List<hoadon> findByID(int id) {
        return selectBySQL("SELECT * FROM HOADON WHERE ID = ?", id);
    }

    @Override
    public List<hoadon> findByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}

