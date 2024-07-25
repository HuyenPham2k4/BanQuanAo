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
public class KhachHangViewModels {
    int ID_KhachHang;
    String TenKhachHang;
    String DiaChi;
    int SoDienThoai;
    String GhiChu;
    int TrangThai;

    public KhachHangViewModels() {
    }

    public KhachHangViewModels(int ID_KhachHang, String TenKhachHang, String DiaChi, int SoDienThoai, String GhiChu, int TrangThai) {
        this.ID_KhachHang = ID_KhachHang;
        this.TenKhachHang = TenKhachHang;
        this.DiaChi = DiaChi;
        this.SoDienThoai = SoDienThoai;
        this.GhiChu = GhiChu;
        this.TrangThai = TrangThai;
    }

    public int getID_KhachHang() {
        return ID_KhachHang;
    }

    public void setID_KhachHang(int ID_KhachHang) {
        this.ID_KhachHang = ID_KhachHang;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String TenKhachHang) {
        this.TenKhachHang = TenKhachHang;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public int getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(int SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    

}
