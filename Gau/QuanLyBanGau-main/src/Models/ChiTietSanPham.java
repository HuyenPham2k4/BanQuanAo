/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author qivub
 */
public class ChiTietSanPham {
    int ID_ChiTietSanPham;
    int ID_Size;
    String TenSanPham;
    int ID_DanhMucSanPham;
    int Gia;
    int ID_MauSac;
    String ID_AnhSanPham;
    int ID_ThuongHieu;
    int ID_ChatLieu;
    int ID_TrongLuong;
    int SoLuong;
    int TrangThai;
    String GhiChu;
    public ChiTietSanPham() {
    }

    public ChiTietSanPham(int ID_ChiTietSanPham, int ID_Size, String TenSanPham, int ID_DanhMucSanPham, int Gia, int ID_MauSac, String ID_AnhSanPham, int ID_ThuongHieu, int ID_ChatLieu, int ID_TrongLuong, int SoLuong, int TrangThai, String GhiChu) {
        this.ID_ChiTietSanPham = ID_ChiTietSanPham;
        this.ID_Size = ID_Size;
        this.TenSanPham = TenSanPham;
        this.ID_DanhMucSanPham = ID_DanhMucSanPham;
        this.Gia = Gia;
        this.ID_MauSac = ID_MauSac;
        this.ID_AnhSanPham = ID_AnhSanPham;
        this.ID_ThuongHieu = ID_ThuongHieu;
        this.ID_ChatLieu = ID_ChatLieu;
        this.ID_TrongLuong = ID_TrongLuong;
        this.SoLuong = SoLuong;
        this.TrangThai = TrangThai;
        this.GhiChu = GhiChu;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    

    public int getID_ChiTietSanPham() {
        return ID_ChiTietSanPham;
    }

    public void setID_ChiTietSanPham(int ID_ChiTietSanPham) {
        this.ID_ChiTietSanPham = ID_ChiTietSanPham;
    }

    public int getID_Size() {
        return ID_Size;
    }

    public void setID_Size(int ID_Size) {
        this.ID_Size = ID_Size;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String TenSanPham) {
        this.TenSanPham = TenSanPham;
    }

    public int getID_DanhMucSanPham() {
        return ID_DanhMucSanPham;
    }

    public void setID_DanhMucSanPham(int ID_DanhMucSanPham) {
        this.ID_DanhMucSanPham = ID_DanhMucSanPham;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int Gia) {
        this.Gia = Gia;
    }

    public int getID_MauSac() {
        return ID_MauSac;
    }

    public void setID_MauSac(int ID_MauSac) {
        this.ID_MauSac = ID_MauSac;
    }

    public String getID_AnhSanPham() {
        return ID_AnhSanPham;
    }

    public void setID_AnhSanPham(String ID_AnhSanPham) {
        this.ID_AnhSanPham = ID_AnhSanPham;
    }

    public int getID_ThuongHieu() {
        return ID_ThuongHieu;
    }

    public void setID_ThuongHieu(int ID_ThuongHieu) {
        this.ID_ThuongHieu = ID_ThuongHieu;
    }

    public int getID_ChatLieu() {
        return ID_ChatLieu;
    }

    public void setID_ChatLieu(int ID_ChatLieu) {
        this.ID_ChatLieu = ID_ChatLieu;
    }

    public int getID_TrongLuong() {
        return ID_TrongLuong;
    }

    public void setID_TrongLuong(int ID_TrongLuong) {
        this.ID_TrongLuong = ID_TrongLuong;
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
