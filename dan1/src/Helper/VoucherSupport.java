/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import entity.hoadon;
import entity.nhanvien;
import entity.voucher;

/**
 *
 * @author huyen
 */
public class VoucherSupport {

    public static voucher vch = null;

    public static void clear() {
        System.out.println("Clean thanh cong");
        VoucherSupport.vch = null;
    }

    public static boolean isLogin() {
        return VoucherSupport.vch != null;
    }

}
