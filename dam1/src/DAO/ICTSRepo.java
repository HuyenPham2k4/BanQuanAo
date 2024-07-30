/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;
import entity.chitietzise;


import java.util.List;


/**
 *
 * @author hauvv
 */
public interface ICTSRepo {
    public boolean add(chitietzise op);
    public boolean update(chitietzise op);
    public boolean delete(int id);
    public List<chitietzise> getAll();
    public List<chitietzise> selectBySQL(String sql, Object... args);
    public List<chitietzise> findByID(int id);
    public List<chitietzise> findByName(String name);
}

