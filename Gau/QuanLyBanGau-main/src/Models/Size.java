/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author qivub
 */
public class Size {
    int ID_Size;
    String TenSize;
    String MoTaSize;
    int TrangThai;
    
    public Size() {
    }

    public Size(int ID_Size, String TenSize, String MoTaSize, int TrangThai) {
        this.ID_Size = ID_Size;
        this.TenSize = TenSize;
        this.MoTaSize = MoTaSize;
        this.TrangThai = TrangThai;
    }

    
    public int getID_Size() {
        return ID_Size;
    }

    public void setID_Size(int ID_Size) {
        this.ID_Size = ID_Size;
    }

    public String getTenSize() {
        return TenSize;
    }

    public void setTenSize(String TenSize) {
        this.TenSize = TenSize;
    }

    public String getMoTaSize() {
        return MoTaSize;
    }

    public void setMoTaSize(String MoTaSize) {
        this.MoTaSize = MoTaSize;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    
}
