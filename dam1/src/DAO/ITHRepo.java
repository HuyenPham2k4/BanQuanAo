/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;
import entity.thuonghieu;
import java.util.List;

/**
 *
 * @author hauvv
 */
public interface ITHRepo {
        public boolean add(thuonghieu th);
    public boolean update(thuonghieu th);
    public boolean delete(int id);
    public List<thuonghieu> getAll();
    public List<thuonghieu> selectBySQL(String sql, Object... args);
    public thuonghieu findByID(int id);
    public List<thuonghieu> findByName(String name);

    
}
