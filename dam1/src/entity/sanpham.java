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
    int id;
    String tensp;
    int iddmsp;
    int idth;
    int mota;
    String anhsp;
    int soluong;
    int gia;
    boolean trangthai;

    public sanpham() {
    }

    public sanpham(int id, String tensp, int iddmsp, int idth, int mota, String anhsp, int soluong, int gia, boolean trangthai) {
        this.id = id;
        this.tensp = tensp;
        this.iddmsp = iddmsp;
        this.idth = idth;
        this.mota = mota;
        this.anhsp = anhsp;
        this.soluong = soluong;
        this.gia = gia;
        this.trangthai = trangthai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getIddmsp() {
        return iddmsp;
    }

    public void setIddmsp(int iddmsp) {
        this.iddmsp = iddmsp;
    }

    public int getIdth() {
        return idth;
    }

    public void setIdth(int idth) {
        this.idth = idth;
    }

    public int getMota() {
        return mota;
    }

    public void setMota(int mota) {
        this.mota = mota;
    }

    public String getAnhsp() {
        return anhsp;
    }

    public void setAnhsp(String anhsp) {
        this.anhsp = anhsp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }
    
    
}
