/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import Models.HoaDon;
import ViewModels.HoaDonViewmodels;
import java.util.List;

/**
 *
 * @author qivub
 */
public interface IHoaDonRepo {
    public boolean insert(HoaDon _entity);
    public boolean update(HoaDon _entity);
    public boolean delete(HoaDon _entity);
    public boolean THANH_TOAN(HoaDon _entity);
    public List<HoaDonViewmodels> hoadonDaThanhToan();
    public List<HoaDon> selectAll();
    public List<HoaDonViewmodels> findByPhoneCustomer(String name);
    public List<HoaDonViewmodels> fintByMaHD(String maHD);
    public List<HoaDon> selectByID(int ID,HoaDon _entity);
    public List<HoaDon> selectBySQL(String sql, Object... args);
    public List<HoaDon> selectAllBySQL(String sql, Object... args);
    public List<HoaDonViewmodels> selectAllViewModels();
    public List<HoaDonViewmodels> selectBySQLViewModels(String sql, Object... args);
}
