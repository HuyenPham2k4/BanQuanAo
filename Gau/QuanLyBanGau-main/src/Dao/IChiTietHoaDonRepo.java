/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import Models.ChiTietHoaDon;
import java.util.List;

/**
 *
 * @author qivub
 */
public interface IChiTietHoaDonRepo {

    public boolean insert(ChiTietHoaDon _entity);

    public boolean update(ChiTietHoaDon _entity);

    public boolean delete(int _entity);

    public boolean updateNhieuSoLuong(ChiTietHoaDon _entity);

    public boolean updateSoluong(ChiTietHoaDon _entity);

//    public boolean checkConHang(int maSanPham, int soLuong);

//    public int getTonKho(int maSanPham);

    public boolean temporaryDelete(ChiTietHoaDon _entity);

    public List<ChiTietHoaDon> selectByIDCTSP(int ID, int id_hoadon);

    public List<ChiTietHoaDon> selectByIDHoaDon(int ID);

    public List<ChiTietHoaDon> selectBySQL(String sql, Object... args);

    public List<ChiTietHoaDon> selectSoluong(String sql, Object... args);
}
