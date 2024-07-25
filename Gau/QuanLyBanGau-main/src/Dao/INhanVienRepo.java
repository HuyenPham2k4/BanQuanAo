/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import Models.NhanVien;
import java.util.List;

/**
 *
 * @author qivub
 */
public interface INhanVienRepo {
    public boolean insert(NhanVien entity);
    public boolean update(NhanVien entity);
    public boolean delete(NhanVien entity);
    public boolean login(String username,String password);
    public boolean temporaryDelete(NhanVien entity);
    public List<NhanVien> selectAll();
    public List<NhanVien> selectByID(int ID,NhanVien entity);
    public List<NhanVien> selectBySQL(String sql, Object... args);
    public List<NhanVien> selectByUser(String sql, Object... args);
    
}
