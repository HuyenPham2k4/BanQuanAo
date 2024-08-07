/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;
import entity.chitietsize;


import java.util.List;


/**
 *
 * @author hauvv
 */
public interface ICTSRepo {
    public boolean add(chitietsize op);
    public boolean update(chitietsize op);
    public boolean delete(int op);
    public List<chitietsize> getAll();
    public List<chitietsize> selectBySQL(String sql, Object... args);
    public List<chitietsize> findByID(int id);
    public List<chitietsize> findByName(String name);
}

