/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class hoadon {

    int id;
    int idnv;
    int mavocher;
    String thoigian;
    String ghichu;
    int ttthanhtoan;
    int tongtien;
    boolean trangthai;

    public hoadon() {
    }

    public hoadon(int id, int idnv, int mavocher, String thoigian, String ghichu, int ttthanhtoan, int tongtien, boolean trangthai) {
        this.id = id;
        this.idnv = idnv;
        this.mavocher = mavocher;
        this.thoigian = thoigian;
        this.ghichu = ghichu;
        this.ttthanhtoan = ttthanhtoan;
        this.tongtien = tongtien;
        this.trangthai = trangthai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdnv() {
        return idnv;
    }

    public void setIdnv(int idnv) {
        this.idnv = idnv;
    }

    public int getMavocher() {
        return mavocher;
    }

    public void setMavocher(int mavocher) {
        this.mavocher = mavocher;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public int getTtthanhtoan() {
        return ttthanhtoan;
    }

    public void setTtthanhtoan(int ttthanhtoan) {
        this.ttthanhtoan = ttthanhtoan;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }

}
