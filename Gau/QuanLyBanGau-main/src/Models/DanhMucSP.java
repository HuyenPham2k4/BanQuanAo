/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author qivub
 */
public class DanhMucSP {
    int ID_Danhmucsanpham;
    String TenDanhMuc;
    int TrangThai;

    public DanhMucSP() {
    }

    public DanhMucSP(int ID_Danhmucsanpham, String TenDanhMuc, int TrangThai) {
        this.ID_Danhmucsanpham = ID_Danhmucsanpham;
        this.TenDanhMuc = TenDanhMuc;
        this.TrangThai = TrangThai;
    }

    public int getID_Danhmucsanpham() {
        return ID_Danhmucsanpham;
    }

    public void setID_Danhmucsanpham(int ID_Danhmucsanpham) {
        this.ID_Danhmucsanpham = ID_Danhmucsanpham;
    }

    public String getTenDanhMuc() {
        return TenDanhMuc;
    }

    public void setTenDanhMuc(String TenDanhMuc) {
        this.TenDanhMuc = TenDanhMuc;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    
}
