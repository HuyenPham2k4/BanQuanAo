/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BUS;

import Models.ChiTietHoaDon;
import java.util.List;

/**
 *
 * @author qivub
 */
public interface IChiTietHoaDonService {

    public boolean addCTHD(ChiTietHoaDon KH);

    public boolean updateCTHD(ChiTietHoaDon ID);

    public boolean updateSoLuong(ChiTietHoaDon ID);

//    public boolean checkConHang(int maSanPham, int soLuong);
//
//    public int getTonKho(int maSanPham);

    public boolean muaNhieu(ChiTietHoaDon ID);

    public boolean temporaryDeleteCTHD(ChiTietHoaDon ID);

    public boolean deleteCTHD(int ID);

    public List<ChiTietHoaDon> getSoluong(int idCTSP, int idHoaDon);

    public List<ChiTietHoaDon> getDataByID(int ID);
}
