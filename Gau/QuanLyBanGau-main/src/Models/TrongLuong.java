/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author qivub
 */
public class TrongLuong {
    int ID_TrongLuong;
    String TrongLuong;
    int TrangThai;

    public TrongLuong() {
    }
    
    public TrongLuong(int ID_TrongLuong, String TrongLuong, int TrangThai) {
        this.ID_TrongLuong = ID_TrongLuong;
        this.TrongLuong = TrongLuong;
        this.TrangThai = TrangThai;
    }
    
    public int getID_TrongLuong() {
        return ID_TrongLuong;
    }

    public void setID_TrongLuong(int ID_TrongLuong) {
        this.ID_TrongLuong = ID_TrongLuong;
    }

    public String getTrongLuong() {
        return TrongLuong;
    }

    public void setTrongLuong(String TrongLuong) {
        this.TrongLuong = TrongLuong;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    
}
