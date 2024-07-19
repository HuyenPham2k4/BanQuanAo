/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class nhanvien {
    int id;
    String manv;
    String hoten;
    int sdt;
    String email;
    String tendangnhap;
    String matkhau;
    int quyenhan;
    boolean trangthai;

    public nhanvien() {
    }

    public nhanvien(int id, String manv, String hoten, int sdt, String email, String tendangnhap, String matkhau, int quyenhan, boolean trangthai) {
        this.id = id;
        this.manv = manv;
        this.hoten = hoten;
        this.sdt = sdt;
        this.email = email;
        this.tendangnhap = tendangnhap;
        this.matkhau = matkhau;
        this.quyenhan = quyenhan;
        this.trangthai = trangthai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public int getQuyenhan() {
        return quyenhan;
    }

    public void setQuyenhan(int quyenhan) {
        this.quyenhan = quyenhan;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }
    
    
}
