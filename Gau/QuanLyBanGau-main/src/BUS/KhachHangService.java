/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import Dao.IKhachHangRepo;
import Dao.KhachHangRepo;
import Models.KhachHang;
import java.util.List;

/**
 *
 * @author khanh
 */
public class KhachHangService implements IKhachHangService{
    IKhachHangRepo iKhachHangRepo;
    List<KhachHang> lstKhachHang;
    public KhachHangService() {
        iKhachHangRepo = new KhachHangRepo();
        lstKhachHang = iKhachHangRepo.selectAll();
    }
    @Override
    public boolean addKhachHang(KhachHang KH) {
        iKhachHangRepo.insert(KH);
        return true;
    }

    @Override
    public boolean updateKhachHang(KhachHang KH) {
        iKhachHangRepo.update(KH);
        return true;
    }

    @Override
    public boolean temporaryDeleteKhachHang(KhachHang KH) {
        iKhachHangRepo.temporaryDelete(KH);
        return true;
    }

    @Override
    public boolean deleteKhachHang(KhachHang KH) {
       iKhachHangRepo.delete(KH);
        return true;
    }

    @Override
    public List<KhachHang> getData() {
         return iKhachHangRepo.selectAll();
    }

    @Override
    public List<KhachHang> getDataByID(int ID) {
//        KhachHang ennity = new KhachHang();
//        ID = ennity.getID_KhachHang();
        return iKhachHangRepo.selectByID(ID);
    }

    @Override
    public boolean addNhanhKhachHang(KhachHang KH) {
        iKhachHangRepo.inserts(KH);
        return true;
    }

    @Override
    public List<KhachHang> getDataByName(String ID) {
        return iKhachHangRepo.findName(ID);
    }

    

    

}