/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import Dao.ISizeRepo;
import Dao.SizeRepo;
import Models.Size;
import java.util.List;

/**
 *
 * @author qivub
 */
public class SizeService implements ISizeService{
    ISizeRepo _iSizeRepo;
    List<Size> _lstSize;
    public SizeService() {
        _iSizeRepo = new SizeRepo();
        _lstSize = _iSizeRepo.selectAll();
    }
    @Override
    public boolean add(Size KH) {
        _iSizeRepo.insert(KH);
        return true;
    }

    @Override
    public boolean update(Size ID) {
         _iSizeRepo.update(ID);
        return true;
    }

    @Override
    public boolean temporaryDelete(Size ID) {
        _iSizeRepo.temporaryDelete(ID);
        return true;
    }

    @Override
    public boolean delete(Size ID) {
        _iSizeRepo.delete(ID);
        return true;
    }

    @Override
    public List<Size> getData() {
        return _iSizeRepo.selectAll();
    }

    @Override
    public List<Size> getDataByTrangThai(int trangthai) {
         return _iSizeRepo.selectByTrangThai(trangthai);
    }

    @Override
    public List<Size> timkiem(String name) {
        return _iSizeRepo.timkiem(name);
    }
    
}
