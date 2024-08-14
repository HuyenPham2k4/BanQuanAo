/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;
import entity.nhanvien;
import java.util.List;

/**
 *
 * @author hauvv
 */
public interface INVRepo {
    public boolean add(nhanvien op);
    public boolean update(nhanvien op);
    public boolean delete(int id);
    public List<nhanvien> getAll();
    public List<nhanvien> selectBySQL(String sql, Object... args);
    public List<nhanvien> findByID(int id);
    public List<nhanvien> findByName(String name);
}