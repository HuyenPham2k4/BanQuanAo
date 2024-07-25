/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BUS;

import Models.ChatLieu;
import java.util.List;

/**
 *
 * @author Hieu
 */
public interface IChatLieuService {
//  Interface Không Có body code;
//  Trong interface Mặc Đinh là public không thể sử dụng private;
//  Hiểu đơn giản interface là phần xác mà chưa có hồn
    
    public boolean addChatLieu(ChatLieu chatlieu);
    public boolean updateChatLieu(ChatLieu ID);
    public boolean temporaryDeleteChatLieu(ChatLieu ID);
    public boolean deleteChatLieu(ChatLieu ID);
    public List<ChatLieu> getData();
    public List<ChatLieu> timkiem(String name);
}
