/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import entity.hoadon;
import java.util.List;

/**
 *
 * @author hauvv
 */
public interface IHDRepo {
   
    public boolean add(hoadon op);
    public boolean update(hoadon op);
    public boolean delete(hoadon op);
    public List<hoadon> getAll();
    public List<hoadon> selectBySQL(String sql, Object... args);
    public hoadon findByID(int id);
    public List<hoadon> findByName(String name);
}


