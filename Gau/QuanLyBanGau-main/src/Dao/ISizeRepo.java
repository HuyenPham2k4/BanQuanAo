/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;
import java.util.List;
import Models.Size;

/**
 *
 * @author khanh
 */
public interface ISizeRepo {
    public boolean insert(Size _entity);
    public boolean update(Size _entity);
    public boolean delete(Size _entity);
    public boolean temporaryDelete(Size _entity);
    public List<Size> selectAll();
    public List<Size> timkiem(String name);
    
    public List<Size> selectByTrangThai(int TrangThai);
    public List<Size> selectBySQL(String sql, Object... args);
}
