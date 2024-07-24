/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import Dao.ChatLieuRepo;
import Dao.IChatLieuRepo;
import Dao.ITrongLuongRepo;
import Dao.TrongLuongRepo;
import Models.ChatLieu;
import Models.TrongLuong;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Hieu
 */
public class TrongLuongService implements ITrongLuongService{

    ITrongLuongRepo _iTrongLuongRepo;
    List<TrongLuong> _lstTrongLuong;
    public TrongLuongService() {
        _iTrongLuongRepo = new TrongLuongRepo ();
        _lstTrongLuong = _iTrongLuongRepo.selectAll();
    }
  

    
    @Override
    public boolean addTrongLuong(TrongLuong chatlieu) {
        _iTrongLuongRepo.insert(chatlieu);    
        return true;
    }

    @Override
    public boolean updateTrongLuong(TrongLuong ID) {
       _iTrongLuongRepo.update(ID);    
        return true;
    }

    @Override
    public boolean temporaryDeleteTrongLuong(TrongLuong ID) {
        _iTrongLuongRepo.temporaryDelete(ID);    
        return true;
    }

    @Override
    public boolean deleteTrongLuong(TrongLuong ID) {
        _iTrongLuongRepo.delete(ID);    
        return true;
    }

    @Override
    public List<TrongLuong> getData() {
         return _iTrongLuongRepo.selectAll();
    }
    @Override
    public List<TrongLuong> getDataByTrangThai(int trangthai) {
         return _iTrongLuongRepo.selectByTrangThai(trangthai);
    }

    @Override
    public List<TrongLuong> timkiem(String name) {
        return _iTrongLuongRepo.timkiem(name);
    }

  
}
