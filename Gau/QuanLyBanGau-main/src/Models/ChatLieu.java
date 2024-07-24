/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author qivub
 */
public class ChatLieu {
    int ID_ChatLieu;
    String TenChatLieu;
    String MoTaChatLieu;
    int TrangThai;

    public ChatLieu() {
        
    }
    
    public ChatLieu(int ID_ChatLieu, String TenChatLieu, String MoTaChatLieu, int TrangThai) {
        this.ID_ChatLieu = ID_ChatLieu;
        this.TenChatLieu = TenChatLieu;
        this.MoTaChatLieu = MoTaChatLieu;
        this.TrangThai = TrangThai;
    }

    public int getID_ChatLieu() {
        return ID_ChatLieu;
    }

    public void setID_ChatLieu(int ID_ChatLieu) {
        this.ID_ChatLieu = ID_ChatLieu;
    }

    public String getTenChatLieu() {
        return TenChatLieu;
    }

    public void setTenChatLieu(String TenChatLieu) {
        this.TenChatLieu = TenChatLieu;
    }

    public String getMoTaChatLieu() {
        return MoTaChatLieu;
    }

    public void setMoTaChatLieu(String MoTaChatLieu) {
        this.MoTaChatLieu = MoTaChatLieu;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    
}
