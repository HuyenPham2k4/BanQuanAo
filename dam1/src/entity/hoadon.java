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
    String sdt;
    String tenkh;
    String mavocher;
    String thoigian;
    String ghichu;
    int ttthanhtoan;
    int tongtien;
    int trangthai;

    public hoadon() {
    }

    public hoadon(int id, int idnv, String sdt, String tenkh, String mavocher, String thoigian, String ghichu, int ttthanhtoan, int tongtien, int trangthai) {
        this.id = id;
        this.idnv = idnv;
        this.sdt = sdt;
        this.tenkh = tenkh;
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

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getMavocher() {
        return mavocher;
    }

    public void setMavocher(String mavocher) {
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

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    
}
