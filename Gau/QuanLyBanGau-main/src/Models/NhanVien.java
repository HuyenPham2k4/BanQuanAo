/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author qivub
 */
public class NhanVien {
    int ID_NhanVien;
    String TenNhanVien;
    String Email;
    String TenDangNhap;
    String MatKhau;
    int ID_ChucVu;
    int TrangThai;
    public NhanVien() {
    }

    public NhanVien(int ID_NhanVien, String TenNhanVien, String Email, String TenDangNhap, String MatKhau, int ID_ChucVu, int TrangThai) {
        this.ID_NhanVien = ID_NhanVien;
        this.TenNhanVien = TenNhanVien;
        this.Email = Email;
        this.TenDangNhap = TenDangNhap;
        this.MatKhau = MatKhau;
        this.ID_ChucVu = ID_ChucVu;
        this.TrangThai = TrangThai;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    public int getID_NhanVien() {
        return ID_NhanVien;
    }

    public void setID_NhanVien(int ID_NhanVien) {
        this.ID_NhanVien = ID_NhanVien;
    }

    public String getTenNhanVien() {
        return TenNhanVien;
    }

    public void setTenNhanVien(String TenNhanVien) {
        this.TenNhanVien = TenNhanVien;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String TenDangNhap) {
        this.TenDangNhap = TenDangNhap;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public int getID_ChucVu() {
        return ID_ChucVu;
    }

    public void setID_ChucVu(int ID_ChucVu) {
        this.ID_ChucVu = ID_ChucVu;
    }

}
