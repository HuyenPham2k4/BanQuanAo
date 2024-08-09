/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;
import entity.size;
import java.util.List;
/**
 *
 * @author hauvv
 */
public interface ISRepo {
   public boolean add(size S);
    public boolean update(size s);
    public boolean delete(int id);
    public List<size> getAll();
    public List<size> selectBySQL(String sql, Object... args);
    public size findByID(int id);
    public List<size> findByName(String name);
}


