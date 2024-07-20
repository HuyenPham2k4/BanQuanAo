/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;
import entity.chitietmausac;
import java.util.List;

/**
 *
 * @author hauvv
 */
public interface ICTMSRepo {
    public boolean add(chitietmausac op);
    public boolean update(chitietmausac op);
    public boolean delete(chitietmausac op);
    public List<chitietmausac> getAll();
    public List<chitietmausac> selectBySQL(String sql, Object... args);
    public List<chitietmausac> findByID(int id);
    public List<chitietmausac> findByName(String name);
}


