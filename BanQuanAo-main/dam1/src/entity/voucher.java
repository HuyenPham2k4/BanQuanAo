/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class voucher {
   String mavoucher;
    int PhanTramGiam;
    String ngaybatdau;
    String ngayketthuc;
    boolean trangthai;

    public voucher() {
    }

    public voucher(String mavoucher, int PhanTramGiam, String ngaybatdau, String ngayketthuc, boolean trangthai) {
        this.mavoucher = mavoucher;
        this.PhanTramGiam = PhanTramGiam;
        this.ngaybatdau = ngaybatdau;
        this.ngayketthuc = ngayketthuc;
        this.trangthai = trangthai;
    }

    public String getMavoucher() {
        return mavoucher;
    }

    public void setMavoucher(String mavoucher) {
        this.mavoucher = mavoucher;
    }

    public int getPhanTramGiam() {
        return PhanTramGiam;
    }

    public void setPhanTramGiam(int PhanTramGiam) {
        this.PhanTramGiam = PhanTramGiam;
    }

    public String getNgaybatdau() {
        return ngaybatdau;
    }

    public void setNgaybatdau(String ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }

    public String getNgayketthuc() {
        return ngayketthuc;
    }

    public void setNgayketthuc(String ngayketthuc) {
        this.ngayketthuc = ngayketthuc;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }
    
}
