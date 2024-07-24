/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import Models.KhachHang;
import java.util.List;

/**
 *
 * @author qivub
 */
public interface IKhachHangRepo {
    public boolean insert(KhachHang _entity);
    public boolean inserts(KhachHang _entity);
    public boolean update(KhachHang _entity);
    public boolean delete(KhachHang _entity);
    public boolean temporaryDelete(KhachHang _entity);
    public List<KhachHang> selectAll();
    public List<KhachHang> selectByID(int ID);
    public List<KhachHang> findName(String name);
    public List<KhachHang> selectBySQL(String sql, Object... args);
}
