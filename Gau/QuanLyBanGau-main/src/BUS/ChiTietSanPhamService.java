/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import Dao.ChiTietSanPhamRepo;
import Dao.IChitietSanPhamRepo;
import Models.ChiTietSanPham;
import ViewModels.ChiTietSanPhamViewModels;
import java.util.List;

/**
 *
 * @author qivub
 */
public class ChiTietSanPhamService implements IChiTietSanPhamService{
    IChitietSanPhamRepo _iCTSPRepo;
    List<ChiTietSanPham> _lstCTSP;

    public ChiTietSanPhamService() {
        _iCTSPRepo = new ChiTietSanPhamRepo();
        _lstCTSP = _iCTSPRepo.selectAll();
    }
    
    @Override
    public boolean addSanPham(ChiTietSanPham sp) {
        _iCTSPRepo.insert(sp);    
//        System.out.println(_iCTSPRepo.insert(sp));
        return true;
    }

    @Override
    public boolean updateSanPham(ChiTietSanPham ID) {
        _iCTSPRepo.update(ID);    
        return true;
    }

    @Override
    public boolean temporaryDeleteSanPham(ChiTietSanPham ID) {
        _iCTSPRepo.temporaryDelete(ID);    
//        System.out.println(_iCTSPRepo.temporaryDelete(ID));
        return true;
    }

    @Override
    public boolean deleteSanPham(ChiTietSanPham ID) {
         _iCTSPRepo.delete(ID);    
//        System.out.println(_iCTSPRepo.delete(ID));
        return true;
    }

    @Override
    public List<ChiTietSanPham> getData() {
        return _iCTSPRepo.selectAll();
        
    }

    @Override
    public List<ChiTietSanPhamViewModels> getDataViewModels() {
        return _iCTSPRepo.selectAllViewModels();
    }


    @Override
    public List<ChiTietSanPhamViewModels> findIDSP(int ID) {
        return _iCTSPRepo.selectByID(ID);
    }

    @Override
    public List<ChiTietSanPhamViewModels> findTenSP(String ten) {
        return _iCTSPRepo.selectByName(ten);
    }

    @Override
    public List<ChiTietSanPhamViewModels> locDanhMucSP(String ten) {
        return _iCTSPRepo.locDanhMucSP(ten);
    }

    @Override
    public List<ChiTietSanPhamViewModels> locMauSac(String ten) {
        return _iCTSPRepo.locMauSac(ten);
    }

    @Override
    public List<ChiTietSanPhamViewModels> locChatLieu(String ten) {
        return _iCTSPRepo.locChatLieu(ten);
    }

    @Override
    public List<ChiTietSanPhamViewModels> locKichCo(String ten) {
        return _iCTSPRepo.locKichCo(ten);
    }

    @Override
    public List<ChiTietSanPhamViewModels> locSize(String ten) {
        return _iCTSPRepo.locSize(ten);
    }

    @Override
    public List<ChiTietSanPhamViewModels> locThuongHieu(String ten) {
        return _iCTSPRepo.locThuongHieu(ten);
    }
}
