/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author qivub
 */
public class MauSac {
    int ID_MauSac;
    String TenMauSac;
    int TrangThai;

    public MauSac() {
    }

    public MauSac(int ID_MauSac, String TenMauSac, int TrangThai) {
        this.ID_MauSac = ID_MauSac;
        this.TenMauSac = TenMauSac;
        this.TrangThai = TrangThai;
    }

    public int getID_MauSac() {
        return ID_MauSac;
    }

    public void setID_MauSac(int ID_MauSac) {
        this.ID_MauSac = ID_MauSac;
    }

    public String getTenMauSac() {
        return TenMauSac;
    }

    public void setTenMauSac(String TenMauSac) {
        this.TenMauSac = TenMauSac;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    
    
}
