/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import java.util.Date;
import java.sql.Time;

/**
 *
 * @author qivub
 */
public class SessionHelper {
    public static int Soluong=0;
    public static int ID_CTHD = 0;
    public static int GIA_SP = 0;
    public static int CURRENT_MA_HD = 0;
    public static String CURRENT_MaHD = "";
    public static String CURRENT_TenKH = "";
    public static String CURRENT_DiaChi = "";
    public static int CURRENT_SDT = 0;
    public static int CURRENT_MaNV = 0;
    public static int CURRENT_IDKH = -1;
    public static String CURRENT_USER_ID = "";
    public static String CURRENT_USER_Name = "";
    public static String CURRENT_USER_Pass = "";
    public static String CURRENT_USER_ScretKey = "";
    public static int CURRENT_SIZE = 0;
    public static int CURRENT_THUONGHIEU = 0;
    public static int CURRENT_TRONGLUONG = -1;
    public static int CURRENT_MAUSAC = -1;
    public static int CURRENT_DANHMUCSP = -1;
    public static int CURRENT_CHATLIEU = -1;
    public static int CURRENT_ROLE = -1;
    public static String CURRENT_ID=null;
    public SessionHelper() {
    }
    
    public static void setSoluong(int soluong) {
        Soluong = soluong;
    }

    public static int getsoLuong() {
        return Soluong;
    }
    public static void setIDCTHD(int cthd) {
        ID_CTHD = cthd;
    }

    public static int getIDCTHD() {
        return ID_CTHD;
    }

    public static void setGiaSP(int gia) {
        GIA_SP = gia;
    }

    public static int getGiaSP() {
        return GIA_SP;
    }

    public static void setRole(int roll) {
        CURRENT_ROLE = roll;
    }

    public static int getRole() {
        return CURRENT_ROLE;
    }

    public static boolean isValid() {
        return CURRENT_ROLE == -1;
    }

    public static void removeRole() {
        CURRENT_ROLE = -1;
    }

    public static void setID_HD(int MAHD) {
        CURRENT_MA_HD = MAHD;
    }

    public static int getID_HD() {
        return CURRENT_MA_HD;
    }
//    

    public static void setID_Size(int size) {
        CURRENT_SIZE = size;
    }

    public static int getID_Size() {
        return CURRENT_SIZE;
    }
//    

    public static void setID_ThuongHieu(int thuonghieu) {
        CURRENT_THUONGHIEU = thuonghieu;
    }

    public static int getID_ThuongHieu() {
        return CURRENT_THUONGHIEU;
    }
//    

    public static void setID_TrongLuong(int TrongLuong) {
        CURRENT_TRONGLUONG = TrongLuong;
    }

    public static int getID_TrongLuong() {
        return CURRENT_TRONGLUONG;
    }
//    

    public static void setID_MauSac(int mausac) {
        CURRENT_MAUSAC = mausac;
    }

    public static int getID_MauSac() {
        return CURRENT_MAUSAC;
    }

    public static void setID_DmSP(int loaisp) {
        CURRENT_DANHMUCSP = loaisp;
    }

    public static int getID_DmSP() {
        return CURRENT_DANHMUCSP;
    }
//    

    public static void setID_ChatLieu(int chatlieu) {
        CURRENT_CHATLIEU = chatlieu;
    }

    public static int getID_ChatLieu() {
        return CURRENT_CHATLIEU;
    }

    public static void setDiaChi(String Diachi) {
        CURRENT_DiaChi = Diachi;
    }

    public static String getDiaChi() {
        return CURRENT_DiaChi;
    }

    public static void setTenKH(String TenKH) {
        CURRENT_TenKH = TenKH;
    }

    public static String getTenKH() {
        return CURRENT_TenKH;
    }

    public static void setSDT(int SDT) {
        CURRENT_SDT = SDT;
    }

    public static int getSDT() {
        return CURRENT_SDT;
    }

    public static void setMaNV(int maNV) {
        CURRENT_MaNV = maNV;
    }

    public static int getMaNV() {
        return CURRENT_MaNV;
    }

    public static void setIDKH(int idKH) {
        CURRENT_IDKH = idKH;
    }

    public static int getIDKH() {
        return CURRENT_IDKH;
    }

    public static void setIdUser(String id) {
        CURRENT_USER_ID = id;
    }

    public static String getIdUser() {
        return CURRENT_USER_ID;
    }

    public static void setUsername(String name) {
        CURRENT_USER_Name = name;
    }

    public static String getUsername() {
        return CURRENT_USER_Name;
    }

    public static void setPasword(String password) {
        CURRENT_USER_Pass = password;
    }

    public static String getPasword() {
        return CURRENT_USER_Pass;
    }

    public static void setSecretKey(String name) {
        CURRENT_USER_ScretKey = name;
    }

    public static String getSecretKey() {
        return CURRENT_USER_ScretKey;
    }

//    public static boolean isValid() {
//        return CURRENT_IDKH == -1;
//    }
//
//    public static void removeRole() {
//        CURRENT_IDKH = -1;
//    }  
    public static void setCURRENT_ID(String CURRENT_ID) {
        SessionHelper.CURRENT_ID = CURRENT_ID;
    }
    public static String getCURRENT_ID() {
        return CURRENT_ID;
    }
    public static void setCURRENT_ROLE(int roll){
        CURRENT_ROLE=roll;
    }
    public static int getCURRENT_ROLE(){
        return CURRENT_ROLE;
    }
}
