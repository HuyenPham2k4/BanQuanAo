/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Helper.JDBCHelper;
import entity.nhanvien;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author huyen
 */

public class nhanhVienDAO implements INVRepo {
    JDBCHelper helper;

    public nhanhVienDAO() {
        helper = new JDBCHelper();
    }

    @Override
    public boolean add(nhanvien nv) {
        String sql = "INSERT INTO NHANVIEN (MaNV, HoTen, SDT, Email, TenDN, MatKhau, QuyenHan, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        helper.executeUpdate(sql, nv.getManv(), nv.getHoten(), nv.getSdt(), nv.getEmail(), nv.getTendangnhap(), nv.getMatkhau(), nv.getQuyenhan(), nv.isTrangthai());
        return true;
    }

    @Override
    public boolean update(nhanvien nv) {
        String sql = "UPDATE NHANVIEN SET MaNV = ?, HoTen = ?, SDT = ?, Email = ?, TenDN = ?, MatKhau = ?, QuyenHan = ?, TrangThai = ? WHERE ID = ?";
        helper.executeUpdate(sql, nv.getManv(), nv.getHoten(), nv.getSdt(), nv.getEmail(), nv.getTendangnhap(), nv.getMatkhau(), nv.getQuyenhan(), nv.isTrangthai(), nv.getId());
        return true;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM NHANVIEN WHERE ID = ?";
        helper.executeUpdate(sql,  id);
        return true;
    }

    @Override
    public List<nhanvien> getAll() {
        return selectBySQL("SELECT * FROM NHANVIEN");
    }

    @Override
    public List<nhanvien> selectBySQL(String sql, Object... args) {
        List<nhanvien> lstNV = new ArrayList<>();
        try {
            ResultSet rs = helper.executeQuery(sql, args);
            while (rs.next()) {
                nhanvien nv = new nhanvien();
                nv.setId(rs.getInt("ID"));
                nv.setManv(rs.getString("MaNV"));
                nv.setHoten(rs.getString("HoTen"));
                nv.setSdt(rs.getInt("SDT"));
                nv.setEmail(rs.getString("Email"));
                nv.setTendangnhap(rs.getString("TenDN"));
                nv.setMatkhau(rs.getString("MatKhau"));
                nv.setQuyenhan(rs.getBoolean("QuyenHan"));
                nv.setTrangthai(rs.getBoolean("TrangThai"));
                lstNV.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstNV;
    }

    @Override
    public List<nhanvien> findByID(int id) {
        return selectBySQL("SELECT * FROM NHANVIEN WHERE ID = ?", id);
    }

    @Override
    public List<nhanvien> findByName(String name) {
        return selectBySQL("SELECT * FROM NHANVIEN WHERE HoTen LIKE N'%" + name + "%'");
    }
    public List<nhanvien> selectByUser(String username, String password) {
        List<nhanvien> nv = selectBySQL("SELECT * FROM NhanVien WHERE TenDN = '"+username+"' AND MatKhau='"+password+"'");
        return nv;
    }
    
    public boolean login(String username, String password){
        List<nhanvien> nv = selectByUser(username, password);
        if(nv.isEmpty()){
            System.out.println("null");
            return false;
        }
        return true;
    }
}
