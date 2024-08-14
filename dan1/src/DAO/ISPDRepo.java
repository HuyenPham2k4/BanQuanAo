/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;
import entity.sanpham;
import java.util.List;
/**
 *
 * @author hauvv
 */
public interface ISPDRepo {
    public boolean add(sanpham sp);
    public boolean update(sanpham sp);
    public boolean delete(int id);
    public List<sanpham> getAll();
    public List<sanpham> selectBySQL(String sql, Object... args);
    public sanpham findByID(int id);
    public List<sanpham> findByName(String name);
}
