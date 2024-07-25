/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BUS;

import Models.KhachHang;
import java.util.List;

/**
 *
 * @author khanh
 */
public interface IKhachHangService {
    public boolean addNhanhKhachHang(KhachHang KH);
    public boolean addKhachHang(KhachHang KH);
    public boolean updateKhachHang(KhachHang ID);
    public boolean temporaryDeleteKhachHang(KhachHang ID);
    public boolean deleteKhachHang(KhachHang ID);
    public List<KhachHang> getData();
    public List<KhachHang> getDataByID(int ID);
    public List<KhachHang> getDataByName(String ID);
}
