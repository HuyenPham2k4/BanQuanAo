/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import Dao.DanhMucSPRepo;
import Dao.IDanhMucSPRepo;
import Models.DanhMucSP;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class DanhMucSPService implements IDanhMucSPService{
//  Là Nơi Gọi Đến Các Service Ở Tầng DAO 
//  Không Được Phép Gọi Trực Tiếp Các File Ở Class Các Tầng
//  Phải Gọi Thông quá interface
    IDanhMucSPRepo _iDanhMucSPRepo;
    List<DanhMucSP> _lstDanhMucSP;
    public DanhMucSPService() {
        _iDanhMucSPRepo = new DanhMucSPRepo();
        _lstDanhMucSP = _iDanhMucSPRepo.selectAll();
    }
    

    @Override
    public boolean addDanhMucSP(DanhMucSP danhMuc) {
        _iDanhMucSPRepo.insert(danhMuc);    
//        System.out.println(_iDanhMucSPRepo.insert(danhMuc));
        return true;
    }

    @Override
    public boolean updateDanhMucSP(DanhMucSP ID) {
        _iDanhMucSPRepo.update(ID);    
//        System.out.println(_iDanhMucSPRepo.update(ID));
        return true;
    }


    @Override
    public List<DanhMucSP> getData() {
        return _iDanhMucSPRepo.selectAll();
    }

    @Override
    public boolean deteleDanhMucSP(DanhMucSP ID) {
        _iDanhMucSPRepo.delete(ID);    
//        System.out.println(_iDanhMucSPRepo.delete(ID));
        return true;
    }

    @Override
    public boolean temporaryDanhMucSP(DanhMucSP ID) {
        _iDanhMucSPRepo.temporaryDelete(ID);    
        return true;
    }
    @Override
    public List<DanhMucSP> getDataByTrangThai(int trangthai) {
        
         return _iDanhMucSPRepo.selectByTrangThai(trangthai);
    }

    @Override
    public List<DanhMucSP> timkiem(String name) {
        return _iDanhMucSPRepo.selectByID(name);
    }

  
}
