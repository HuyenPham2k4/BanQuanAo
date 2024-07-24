/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author qivub
 */
public class ThuongHieu {
    int ID_ThuongHieu;
    String TenThuongHieu;
    String MoTaThuongHieu;
    int TrangThai;

    public ThuongHieu() {
    }

    public ThuongHieu(int ID_ThuongHieu, String TenThuongHieu, String MoTaThuongHieu, int TrangThai) {
        this.ID_ThuongHieu = ID_ThuongHieu;
        this.TenThuongHieu = TenThuongHieu;
        this.MoTaThuongHieu = MoTaThuongHieu;
        this.TrangThai = TrangThai;
    }

    public int getID_ThuongHieu() {
        return ID_ThuongHieu;
    }

    public void setID_ThuongHieu(int ID_ThuongHieu) {
        this.ID_ThuongHieu = ID_ThuongHieu;
    }

    public String getTenThuongHieu() {
        return TenThuongHieu;
    }

    public void setTenThuongHieu(String TenThuongHieu) {
        this.TenThuongHieu = TenThuongHieu;
    }

    public String getMoTaThuongHieu() {
        return MoTaThuongHieu;
    }

    public void setMoTaThuongHieu(String MoTaThuongHieu) {
        this.MoTaThuongHieu = MoTaThuongHieu;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    
}
