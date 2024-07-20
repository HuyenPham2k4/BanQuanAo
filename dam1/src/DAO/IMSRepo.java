/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;
import entity.mausac;
import java.util.List;
/**
 *
 * @author hauvv
 */
public interface IMSRepo {

    public boolean add(mausac op);
    public boolean update(mausac op);
    public boolean delete(mausac op);
    public List<mausac> getAll();
    public List<mausac> selectBySQL(String sql, Object... args);
    public List<mausac> findByID(int id);
    public List<mausac> findByName(String name);
}
