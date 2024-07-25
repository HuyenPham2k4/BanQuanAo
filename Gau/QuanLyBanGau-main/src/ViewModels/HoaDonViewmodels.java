/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import java.util.Date;

/**
 *
 * @author qivub
 */
public class HoaDonViewmodels {
    String ID_HoaDon;
    String MaHoaDon;
    String TenKhachHang;
    String DiaChi;
    int SoDienThoai;
    String NhanVien;
    Date NgayLapHoaDon;
    String TongTien;
    String GhiChu;
    int TrangThai;

    public HoaDonViewmodels() {
    }

    public HoaDonViewmodels(String ID_HoaDon, String MaHoaDon, String TenKhachHang, String DiaChi, int SoDienThoai, String NhanVien, Date NgayLapHoaDon, String TongTien, String GhiChu, int TrangThai) {
        this.ID_HoaDon = ID_HoaDon;
        this.MaHoaDon = MaHoaDon;
        this.TenKhachHang = TenKhachHang;
        this.DiaChi = DiaChi;
        this.SoDienThoai = SoDienThoai;
        this.NhanVien = NhanVien;
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

    

    public String getID_HoaDon() {
        return ID_HoaDon;
    }

    public void setID_HoaDon(String ID_HoaDon) {
        this.ID_HoaDon = ID_HoaDon;
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

    public String getNhanVien() {
        return NhanVien;
    }

    public void setNhanVien(String NhanVien) {
        this.NhanVien = NhanVien;
    }

    public Date getNgayLapHoaDon() {
        return NgayLapHoaDon;
    }

    public void setNgayLapHoaDon(Date NgayLapHoaDon) {
        this.NgayLapHoaDon = NgayLapHoaDon;
    }

    public String getTongTien() {
        return TongTien;
    }

    public void setTongTien(String TongTien) {
        this.TongTien = TongTien;
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
