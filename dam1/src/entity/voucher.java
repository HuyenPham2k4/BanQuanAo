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
    int ngaybatdau;
    int ngayketthuc;
    boolean trangthai;

    public voucher() {
    }

    public voucher(String mavoucher, int ngaybatdau, int ngayketthuc, boolean trangthai) {
        this.mavoucher = mavoucher;
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

    public int getNgaybatdau() {
        return ngaybatdau;
    }

    public void setNgaybatdau(int ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }

    public int getNgayketthuc() {
        return ngayketthuc;
    }

    public void setNgayketthuc(int ngayketthuc) {
        this.ngayketthuc = ngayketthuc;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }
    
    
}
