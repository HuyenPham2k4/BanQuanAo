/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import Models.DanhMucSP;
import java.util.List;

/**
 *
 * @author qivub
 */
public interface IDanhMucSPRepo {
    public boolean insert(DanhMucSP _entity);
    public boolean update(DanhMucSP _entity);
    public boolean delete(DanhMucSP _entity);
    public boolean temporaryDelete(DanhMucSP _entity);
    public List<DanhMucSP> selectByTrangThai(int TrangThai);
    public List<DanhMucSP> selectAll();
    public List<DanhMucSP> selectByID(String name);
    public List<DanhMucSP> selectBySQL(String sql, Object... args);
}
