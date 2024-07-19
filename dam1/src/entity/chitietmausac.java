/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class chitietmausac {
    int id;
    int idms;
    int idsp;
    boolean trangthai;

    public chitietmausac() {
    }

    public chitietmausac(int id, int idms, int idsp, boolean trangthai) {
        this.id = id;
        this.idms = idms;
        this.idsp = idsp;
        this.trangthai = trangthai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdms() {
        return idms;
    }

    public void setIdms(int idms) {
        this.idms = idms;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }
    
}
