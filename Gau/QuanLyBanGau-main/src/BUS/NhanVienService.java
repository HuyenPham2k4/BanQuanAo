/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import Dao.INhanVienRepo;
import Dao.NhanVienRepo;
import Models.NhanVien;
import java.util.List;

/**
 *
 * @author qivub
 */
public class NhanVienService implements INhanVienService{
    INhanVienRepo _iNhanVienRepo;
    List<NhanVien> _lstNhanVien;
    public NhanVienService() {
        _iNhanVienRepo = new NhanVienRepo();
        _lstNhanVien = _iNhanVienRepo.selectAll();
    }

    @Override
    public boolean addNhanVien(NhanVien nv) {
         _iNhanVienRepo.insert(nv);    
        System.out.println(_iNhanVienRepo.insert(nv));
        return true;
    }

    @Override
    public boolean updateNhanVien(NhanVien ID) {
         _iNhanVienRepo.update(ID);    
        System.out.println(_iNhanVienRepo.insert(ID));
        return true;
    }

    @Override
    public boolean temporaryDeleteNhanVien(NhanVien ID) {
         _iNhanVienRepo.temporaryDelete(ID);    
        System.out.println(_iNhanVienRepo.temporaryDelete(ID));
        return true;
    }

    @Override
    public boolean deleteNhanVien(NhanVien ID) {
        _iNhanVienRepo.delete(ID);    
        System.out.println(_iNhanVienRepo.delete(ID));
        return true;
    }

    @Override
    public List<NhanVien> getData() {
        return _iNhanVienRepo.selectAll();
    }

    @Override
    public boolean login(String user, String pasword) {
        return _iNhanVienRepo.login(user, pasword);
    }
}
