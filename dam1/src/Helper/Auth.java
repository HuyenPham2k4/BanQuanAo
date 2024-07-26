/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import entity.nhanvien;

/**
 *
 * @author huyen
 */
    public class Auth {
    public static nhanvien user = null;
    public static void clear(){
        System.out.println("Clean thanh cong");
        Auth.user = null;
    }
    public static boolean isLogin()
    {
        return Auth.user !=null;
    }
    public static boolean isManager()
    {
        return Auth.isLogin() && user.getQuyenhan();
    }
}

