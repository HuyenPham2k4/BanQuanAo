/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BUS;

import Models.HoaDon;
import ViewModels.HoaDonViewmodels;
import java.util.List;

/**
 *
 * @author qivub
 */
public interface IHoaDonService {
    public boolean addHoaDon(HoaDon hoaDon);
    public boolean UpdateHoaDon(HoaDon ID);
    public boolean thanhToanHoaDon(HoaDon ID);
    public boolean deleteHoaDon(HoaDon ID);
    public List<HoaDon> getData();
    public List<HoaDonViewmodels> hoadonDaThanhToan();
    public List<HoaDonViewmodels> findBySDTKhachHang(String name);
    public List<HoaDonViewmodels> findbyMaHoaDon(String maHoaDon);
//    public List<HoaDon> getDataByID(HoaDon ID);
    public List<HoaDonViewmodels> getDataViewModels();
}
