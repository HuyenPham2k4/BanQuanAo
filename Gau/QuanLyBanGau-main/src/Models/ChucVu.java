/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author qivub
 */
public class ChucVu {
    int ID;
    String TenChucVu;

    public ChucVu() {
    }

    public ChucVu(int ID, String TenChucVu) {
        this.ID = ID;
        this.TenChucVu = TenChucVu;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenChucVu() {
        return TenChucVu;
    }

    public void setTenChucVu(String TenChucVu) {
        this.TenChucVu = TenChucVu;
    }
    
}
