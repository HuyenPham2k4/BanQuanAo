/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class chitiethoadon {
    int id;
    int idhd;
    int idctsp;
    String tenSP;
    int soluong;
    double don_gia;
    double gia;
    boolean trangthai;

    public chitiethoadon() {
    }

    public chitiethoadon(int id, int idhd, int idctsp , String tenSP, int soluong, double don_gia, double gia, boolean trangthai) {
        this.id = id;
        this.idhd = idhd;
        this.idctsp = idctsp;
        this.tenSP = tenSP;
        this.soluong = soluong;
        this.don_gia = don_gia;
        this.gia = gia;
        this.trangthai = trangthai;
    }
    //getter
    public int getId() {
        return id;
    }

    public int getIdhd() {
        return idhd;
    }

    public int getIdctsp() {
        return idctsp;
    }

    public String getTenSP() {
        return tenSP;
    }

    public int getSoluong() {
        return soluong;
    }

    public double getDon_gia() {
        return don_gia;
    }

    public double getGia() {
        return gia;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    //settter
    public void setId(int id) {
        this.id = id;
    }

    public void setIdhd(int idhd) {
        this.idhd = idhd;
    }

    public void setIdctsp(int idctsp) {
        this.idctsp = idctsp;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public void setDon_gia(double don_gia) {
        this.don_gia = don_gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }

    
}
