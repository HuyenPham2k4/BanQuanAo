/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import Dao.ChatLieuRepo;
import Dao.IChatLieuRepo;
import Models.ChatLieu;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Hieu
 */
public class ChatLieuService implements IChatLieuService{
//  Là Nơi Gọi Đến Các Service Ở Tầng DAO 
//  Không Được Phép Gọi Trực Tiếp Các File Ở Class Các Tầng
//  Phải Gọi Thông quá interface
    IChatLieuRepo _iChatLieuRepo;//gọi bên dao ra
    List<ChatLieu> _lstChatLieu;
    public ChatLieuService() {
        _iChatLieuRepo = new ChatLieuRepo();
        _lstChatLieu = _iChatLieuRepo.selectAll();
    }
    

    @Override
    public boolean addChatLieu(ChatLieu chatlieu) {
        _iChatLieuRepo.insert(chatlieu);    
        return true;
    }

    @Override
    public boolean updateChatLieu(ChatLieu ID) {
        _iChatLieuRepo.update(ID);    
        return true;
    }

    @Override
    public boolean temporaryDeleteChatLieu(ChatLieu ID) {
        _iChatLieuRepo.temporaryDelete(ID);    
        return true;
    }

    @Override
    public boolean deleteChatLieu(ChatLieu ID) {
        _iChatLieuRepo.delete(ID);    
        return true;
    }

    @Override
    public List<ChatLieu> getData() {
        return _iChatLieuRepo.selectAll();
    }

    @Override
    public List<ChatLieu> timkiem(String name) {
         return _iChatLieuRepo.timkiem(name);
    }

  
}
