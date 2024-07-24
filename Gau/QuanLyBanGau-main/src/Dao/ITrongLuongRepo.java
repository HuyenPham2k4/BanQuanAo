/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import Models.TrongLuong;
import java.util.List;

/**
 *
 * @author Hieu
 */
public interface ITrongLuongRepo {
    public boolean insert(TrongLuong _entity);
    public boolean update(TrongLuong _entity);
    public boolean delete(TrongLuong _entity);
    public boolean temporaryDelete(TrongLuong _entity);
    public List<TrongLuong> selectByTrangThai(int TrangThai);
    public List<TrongLuong> selectAll();
    public List<TrongLuong> timkiem(String name);
    public List<TrongLuong> selectByID(int ID,TrongLuong _entity);
    public List<TrongLuong> selectBySQL(String sql, Object... args);
}
