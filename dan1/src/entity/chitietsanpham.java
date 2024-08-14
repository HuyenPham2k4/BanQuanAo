/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author letnp
 */
public class chitietsanpham {
    private String masp;
    private String tensp;
    private int soLuong;
    private String gia;
    private String moTa;
    private String mauSacsp;
    private String sizesp;
    private String danhMuc;
    private String thuongHieu;

    public chitietsanpham() {
    }

    public chitietsanpham(String masp, String tensp, int soLuong, String gia, String moTa, String mauSacsp, String sizesp, String danhMuc, String thuongHieu) {
        this.masp = masp;
        this.tensp = tensp;
        this.soLuong = soLuong;
        this.gia = gia;
        this.moTa = moTa;
        this.mauSacsp = mauSacsp;
        this.sizesp = sizesp;
        this.danhMuc = danhMuc;
        this.thuongHieu = thuongHieu;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getMauSacsp() {
        return mauSacsp;
    }

    public void setMauSacsp(String mauSacsp) {
        this.mauSacsp = mauSacsp;
    }

    public String getSizesp() {
        return sizesp;
    }

    public void setSizesp(String sizesp) {
        this.sizesp = sizesp;
    }

    public String getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
    }

    public String getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(String thuongHieu) {
        this.thuongHieu = thuongHieu;
    }
    
}
