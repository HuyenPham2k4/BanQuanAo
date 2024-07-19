/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class sanpham {
    private int ID;
    private String TenSP;
    private int ID_DMSP;
    private int ID_TH;
    private String Mota;
    private String AnhSP;
    private int SoLuong;
    private double Gia;
    private boolean TrangThai;

    public sanpham() {
    }

    public sanpham(int ID, String TenSP, int ID_DMSP, int ID_TH, String Mota, String AnhSP, int SoLuong, double Gia, boolean TrangThai) {
        this.ID = ID;
        this.TenSP = TenSP;
        this.ID_DMSP = ID_DMSP;
        this.ID_TH = ID_TH;
        this.Mota = Mota;
        this.AnhSP = AnhSP;
        this.SoLuong = SoLuong;
        this.Gia = Gia;
        this.TrangThai = TrangThai;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public int getID_DMSP() {
        return ID_DMSP;
    }

    public void setID_DMSP(int ID_DMSP) {
        this.ID_DMSP = ID_DMSP;
    }

    public int getID_TH() {
        return ID_TH;
    }

    public void setID_TH(int ID_TH) {
        this.ID_TH = ID_TH;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String Mota) {
        this.Mota = Mota;
    }

    public String getAnhSP() {
        return AnhSP;
    }

    public void setAnhSP(String AnhSP) {
        this.AnhSP = AnhSP;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public double getGia() {
        return Gia;
    }

    public void setGia(double Gia) {
        this.Gia = Gia;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }
    
}