/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import Models.*;

/**
 *
 * @author qivub
 */
public class ChiTietHoaDonViewModels {
    int ID_CTHD;
    int ID_HoaDon;
    int ID_ChiTietSanPham;
    String TenSanPhan;
    int Gia;
    int SoLuong ;
    int TrangThai;

    public ChiTietHoaDonViewModels() {
    }

    public ChiTietHoaDonViewModels(int ID_CTHD, int ID_HoaDon, int ID_ChiTietSanPham, String TenSanPhan, int Gia, int SoLuong, int TrangThai) {
        this.ID_CTHD = ID_CTHD;
        this.ID_HoaDon = ID_HoaDon;
        this.ID_ChiTietSanPham = ID_ChiTietSanPham;
        this.TenSanPhan = TenSanPhan;
        this.Gia = Gia;
        this.SoLuong = SoLuong;
        this.TrangThai = TrangThai;
    }

    public int getID_CTHD() {
        return ID_CTHD;
    }

    public void setID_CTHD(int ID_CTHD) {
        this.ID_CTHD = ID_CTHD;
    }

    public int getID_HoaDon() {
        return ID_HoaDon;
    }

    public void setID_HoaDon(int ID_HoaDon) {
        this.ID_HoaDon = ID_HoaDon;
    }

    public int getID_ChiTietSanPham() {
        return ID_ChiTietSanPham;
    }

    public void setID_ChiTietSanPham(int ID_ChiTietSanPham) {
        this.ID_ChiTietSanPham = ID_ChiTietSanPham;
    }
    

    public String getTenSanPhan() {
        return TenSanPhan;
    }

    public void setTenSanPhan(String TenSanPhan) {
        this.TenSanPhan = TenSanPhan;
    }
    
    public int getGia() {
        return Gia;
    }

    public void setGia(int Gia) {
        this.Gia = Gia;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    
}
