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
public class ChiTietSanPhamViewModels {
    String ID_ChiTietSanPham;
    String ID_Size;
    String TenSanPham;
    String ID_DanhMucSanPham;
    int Gia;
    String ID_MauSac;
    String ID_AnhSanPham;
    String ID_ThuongHieu;
    String ID_ChatLieu;
    String ID_TrongLuong;
    int SoLuong;
    int TrangThai;

    public ChiTietSanPhamViewModels() {
    }

    public ChiTietSanPhamViewModels(String ID_ChiTietSanPham, String ID_Size, String TenSanPham, String ID_DanhMucSanPham, int Gia, String ID_MauSac, String ID_AnhSanPham, String ID_ThuongHieu, String ID_ChatLieu, String ID_TrongLuong, int SoLuong, int TrangThai) {
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
    }

    public String getID_ChiTietSanPham() {
        return ID_ChiTietSanPham;
    }

    public void setID_ChiTietSanPham(String ID_ChiTietSanPham) {
        this.ID_ChiTietSanPham = ID_ChiTietSanPham;
    }

    public String getID_Size() {
        return ID_Size;
    }

    public void setID_Size(String ID_Size) {
        this.ID_Size = ID_Size;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String TenSanPham) {
        this.TenSanPham = TenSanPham;
    }

    public String getID_DanhMucSanPham() {
        return ID_DanhMucSanPham;
    }

    public void setID_DanhMucSanPham(String ID_DanhMucSanPham) {
        this.ID_DanhMucSanPham = ID_DanhMucSanPham;
    }

    public int getGia() {
        return Gia;
    }

    public void setGia(int Gia) {
        this.Gia = Gia;
    }

    public String getID_MauSac() {
        return ID_MauSac;
    }

    public void setID_MauSac(String ID_MauSac) {
        this.ID_MauSac = ID_MauSac;
    }

    public String getID_AnhSanPham() {
        return ID_AnhSanPham;
    }

    public void setID_AnhSanPham(String ID_AnhSanPham) {
        this.ID_AnhSanPham = ID_AnhSanPham;
    }

    public String getID_ThuongHieu() {
        return ID_ThuongHieu;
    }

    public void setID_ThuongHieu(String ID_ThuongHieu) {
        this.ID_ThuongHieu = ID_ThuongHieu;
    }

    public String getID_ChatLieu() {
        return ID_ChatLieu;
    }

    public void setID_ChatLieu(String ID_ChatLieu) {
        this.ID_ChatLieu = ID_ChatLieu;
    }

    public String getID_TrongLuong() {
        return ID_TrongLuong;
    }

    public void setID_TrongLuong(String ID_TrongLuong) {
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
