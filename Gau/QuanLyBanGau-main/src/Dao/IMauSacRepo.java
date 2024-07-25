/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import Models.MauSac;
import java.util.List;

/**
 *
 * @author qivub
 */
public interface IMauSacRepo { // cái chỗ chỗ này là ổ cứng 
    public boolean insert(MauSac entity);
    public boolean update(MauSac entity);
    public boolean delete(MauSac entity);
    public boolean temporaryDelete(MauSac entity);
    public List<MauSac> timkiem(String name);
    public List<MauSac> selectByTrangThai(int TrangThai);
    public List<MauSac> selectAll();
    public List<MauSac> selectByID(int ID,MauSac entity);
    public List<MauSac> selectBySQL(String sql, Object... args);
}
