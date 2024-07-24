/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Models.NhanVien;
import Utilities.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author qivub
 */
public class NhanVienRepo implements INhanVienRepo{
    private JdbcHelper _jdbcHelper;
        String INSERT_SQL = "INSERT INTO NhanVien (TenNhanVien,Email,TenDangNhap,MatKhau,ID_ChucVu,TrangThai) VALUES (?,?,?,?,?,1)";
        String UPDATE_SQL = "UPDATE NhanVien SET TenNhanVien = ?, Email = ?,TenDangNhap = ?,MatKhau = ?,ID_ChucVu = ?,TrangThai = 1 WHERE ID_NhanVien = ? ";
        String TEMPORARY_DELETE_SQL = "UPDATE NhanVien SET TrangThai = 0 WHERE  ID_NhanVien = ? ";
        String DELETE_SQL = "DELETE NhanVien WHERE ID_NhanVien = ?";
        String LOGIN = "SELECT * FROM NhanVien WHERE TenDangNhap = ? AND MatKhau=?";
        String SELECT_ALL_SQL = "SELECT * FROM NhanVien WHERE TrangThai = 1";
        String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien WHERE ID_NhanVien = ?";
    @Override
    public List<NhanVien> selectBySQL(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = _jdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                NhanVien nhanvien = new NhanVien();
                nhanvien.setID_NhanVien(rs.getInt("ID_NhanVien"));
                nhanvien.setTenNhanVien(rs.getString("TenNhanVien"));
                nhanvien.setEmail(rs.getString("Email"));
                nhanvien.setTenDangNhap(rs.getString("TenDangNhap"));
                nhanvien.setMatKhau(rs.getString("MatKhau"));
                nhanvien.setID_ChucVu(rs.getInt("ID_ChucVu"));
                nhanvien.setTrangThai(rs.getInt("TrangThai"));
                list.add(nhanvien);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }
    @Override
    public boolean insert(NhanVien entity) {
        _jdbcHelper.executeUpdate(INSERT_SQL, entity.getTenNhanVien(),entity.getEmail(),entity.getTenDangNhap(), entity.getMatKhau(), entity.getID_ChucVu());
        return true;
    }

    @Override
    public boolean update(NhanVien entity) {
         _jdbcHelper.executeUpdate(UPDATE_SQL, entity.getTenNhanVien(),entity.getEmail(),entity.getTenDangNhap(), entity.getMatKhau(), entity.getID_ChucVu(),entity.getID_NhanVien());
        return true;
    }

    @Override
    public boolean delete(NhanVien entity) {
        _jdbcHelper.executeUpdate(DELETE_SQL,entity.getID_NhanVien());
        return true;
    }

    @Override
    public boolean temporaryDelete(NhanVien entity) {
        _jdbcHelper.executeUpdate(TEMPORARY_DELETE_SQL,entity.getID_NhanVien());
        return true;
    }

    @Override
    public List<NhanVien> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public List<NhanVien> selectByID(int ID, NhanVien entity) {
        ID = entity.getID_NhanVien();
        return this.selectBySQL(SELECT_BY_ID_SQL, ID);
    }

    @Override
    public boolean login(String username, String password) {
         ResultSet rs = _jdbcHelper.executeQuery(LOGIN, username, password);
         return true;
    }


    @Override
    public List<NhanVien> selectByUser(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = _jdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                NhanVien nhanvien = new NhanVien();
                nhanvien.setID_NhanVien(rs.getInt("ID_NhanVien"));
                nhanvien.setTenNhanVien(rs.getString("TenNhanVien"));
                nhanvien.setEmail(rs.getString("Email"));
                nhanvien.setTenDangNhap(rs.getString("TenDangNhap"));
                nhanvien.setMatKhau(rs.getString("MatKhau"));
                nhanvien.setID_ChucVu(rs.getInt("ID_ChucVu"));
                nhanvien.setTrangThai(rs.getInt("TrangThai"));
                list.add(nhanvien);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }


    
        
}
