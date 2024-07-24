/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;


import java.awt.Component;
import javax.swing.JOptionPane;
/**
 *
 * @author qivub
 */
public class DialogHelper {
    public static void alert(Component parent, String content) {
        JOptionPane.showMessageDialog(parent, content,"Cảnh báo", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void showDialog(Component parent, String content){
        JOptionPane.showMessageDialog(parent, content,"Cảnh báo", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static boolean confirm(Component parent, String content){
        int result = JOptionPane.showConfirmDialog(parent, content,"Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }
    
    public static String prompt(Component parent, String content){
        return JOptionPane.showInputDialog(parent, content,"Lưu ý", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean exitNow(Component parent, String content){
        int result = JOptionPane.showConfirmDialog(parent, content,"Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }


}
