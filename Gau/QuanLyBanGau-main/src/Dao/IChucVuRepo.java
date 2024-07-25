/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import Models.ChucVu;
import java.util.List;

/**
 *
 * @author qivub
 */
public interface IChucVuRepo {
    public boolean insert(ChucVu cv);
    public boolean update(ChucVu cv);
    public boolean delete(ChucVu id);
    public List<ChucVu> selectAllBySQL();
    public List<ChucVu> findByName(String name);
    public List<ChucVu> selectSQL( String sql, Object... args);
}
