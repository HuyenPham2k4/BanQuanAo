/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import entity.danhmuc;
import java.util.List;

/**
 *
 * @author hauvv
 */
public interface IDMRepo {
    public boolean add(danhmuc op);
    public boolean update(danhmuc op);
    public boolean delete(int id);
    public List<danhmuc> getAll();
    public List<danhmuc> selectBySQL(String sql, Object... args);
    public List<danhmuc> findByID(int id);
    public List<danhmuc> findByName(String name);
}