/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import entity.hoadon;
import entity.nhanvien;

/**
 *
 * @author huyen
 */
public class Ivoice {

    public static hoadon hd = null;

    public static void clear() {
        System.out.println("Clean thanh cong");
        Ivoice.hd = null;
    }

    public static boolean isLogin() {
        return Ivoice.hd != null;
    }

}
