/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Helper.JDBCHelper;
import entity.khachHang;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author huyen
 */
public class khachHangDao  {
    JDBCHelper helper;

    public khachHangDao() {
        helper = new JDBCHelper();
    }

    public boolean add(khachHang kh) {
        String sql = "INSERT INTO KHACHHANG (SoDienThoai, TenKhachHang, GioiTinh, Email, DiaChi) VALUES (?, ?, ?, ?, ?)";
        helper.executeUpdate(sql, kh.getSoDienThoai(), kh.getTenKhachHang(), kh.isGioiTinh(), kh.getEmail(), kh.getDiaChi());
        return true;
    }

    public boolean update(khachHang kh) {
        String sql = "UPDATE KHACHHANG SET SoDienThoai = ?, TenKhachHang = ?, GioiTinh = ?, Email = ?, DiaChi = ? WHERE ID = ?";
        helper.executeUpdate(sql, kh.getSoDienThoai(), kh.getTenKhachHang(), kh.isGioiTinh()?1:0, kh.getEmail(), kh.getDiaChi(), kh.getId());
        return true;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM KHACHHANG WHERE ID = ?";
        helper.executeUpdate(sql, id);
        return true;
    }
    public List<khachHang> getAll() {
        return selectBySQL("SELECT * FROM KHACHHANG");
    }

    public List<khachHang> selectBySQL(String sql, Object... args) {
        List<khachHang> lstKhachHang = new ArrayList<>();
        try {
            ResultSet rs = helper.executeQuery(sql, args);
            while (rs.next()) {
                khachHang kh = new khachHang();
                kh.setId(rs.getInt("ID"));
                kh.setSoDienThoai(rs.getString("SoDienThoai"));
                kh.setTenKhachHang(rs.getString("TenKhachHang"));
                kh.setGioiTinh(rs.getBoolean("GioiTinh"));
                kh.setEmail(rs.getString("Email"));
                kh.setDiaChi(rs.getString("DiaChi"));
                lstKhachHang.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstKhachHang;
    }

    public khachHang findBySDT(String SDT) {
        List<khachHang> list = selectBySQL("SELECT * FROM KHACHHANG WHERE SoDienThoai = ?",SDT);
        return list.isEmpty() ? null : list.get(0);
    }
    public List<khachHang> searchSDT(String SDT) {
       
        return selectBySQL("SELECT * FROM KHACHHANG WHERE SoDienThoai LIKE N'%"+SDT+"%'");
         
    }
    public khachHang findByID(int id) {
        List<khachHang> list = selectBySQL("SELECT * FROM KHACHHANG WHERE ID = ?",id);
        return list.isEmpty() ? null : list.get(0);
    }
    
    public khachHang findKH(String SDT) {
        List<khachHang> list = selectBySQL("SELECT * FROM KHACHHANG WHERE SoDienThoai = "+SDT);
        return list.isEmpty() ? null : list.get(0);
    }

    public boolean delete(khachHang kh) {
        return delete(kh.getId());
    }

    public List<khachHang> findByDateRange(int startDate, int endDate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}


  
