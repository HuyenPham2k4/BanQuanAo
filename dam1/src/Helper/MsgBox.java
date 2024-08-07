/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class MsgBox {
    public static void alert(Component parent, String message){
        JOptionPane.showMessageDialog(parent, message, "Cửa hàng quần áo  Visual Box", JOptionPane.INFORMATION_MESSAGE);
    }
    public static boolean confirm(Component parent, String message){
        int result = JOptionPane.showConfirmDialog(parent, message, "Cửa hàng quần áo  Visual Box", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }
    public static String prompt(Component parent, String message){
        return JOptionPane.showInputDialog(parent, message, "Cửa hàng quần áo  Visual Box", JOptionPane.INFORMATION_MESSAGE);
    }
}