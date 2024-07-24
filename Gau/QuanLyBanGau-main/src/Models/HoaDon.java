/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;

/**
 *
 * @author qivub
 */
public class HoaDon {
    int ID_HoaDon;
    String MaHoaDon;
    int ID_KhachHang;
    String TenKhachHang;
    String DiaChi;
    int SoDienThoai;
    int ID_NhanVien;
    Date NgayLapHoaDon;
    int TongTien;
    String GhiChu;
    int TrangThai;

    public HoaDon() {
    }

    public HoaDon(int ID_HoaDon, String MaHoaDon, int ID_KhachHang, String TenKhachHang, String DiaChi, int SoDienThoai, int ID_NhanVien, Date NgayLapHoaDon, int TongTien, String GhiChu, int TrangThai) {
        this.ID_HoaDon = ID_HoaDon;
        this.MaHoaDon = MaHoaDon;
        this.ID_KhachHang = ID_KhachHang;
        this.TenKhachHang = TenKhachHang;
        this.DiaChi = DiaChi;
        this.SoDienThoai = SoDienThoai;
        this.ID_NhanVien = ID_NhanVien;
        this.NgayLapHoaDon = NgayLapHoaDon;
        this.TongTien = TongTien;
        this.GhiChu = GhiChu;
        this.TrangThai = TrangThai;
    }

    public String getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(String MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
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

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    

    public int getID_HoaDon() {
        return ID_HoaDon;
    }

    public void setID_HoaDon(int ID_HoaDon) {
        this.ID_HoaDon = ID_HoaDon;
    }

    public int getID_KhachHang() {
        return ID_KhachHang;
    }

    public void setID_KhachHang(int ID_KhachHang) {
        this.ID_KhachHang = ID_KhachHang;
    }

    public int getID_NhanVien() {
        return ID_NhanVien;
    }

    public void setID_NhanVien(int ID_NhanVien) {
        this.ID_NhanVien = ID_NhanVien;
    }

    public Date getNgayLapHoaDon() {
        return NgayLapHoaDon;
    }

    public void setNgayLapHoaDon(Date NgayLapHoaDon) {
        this.NgayLapHoaDon = NgayLapHoaDon;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int TongTien) {
        this.TongTien = TongTien;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
    
}
