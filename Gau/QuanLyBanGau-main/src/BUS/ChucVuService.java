/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import Dao.ChucVuRepo;
import Dao.IChucVuRepo;
import Models.ChucVu;
import java.util.List;

/**
 *
 * @author qivub
 */
public class ChucVuService implements IChucVuService{
    IChucVuRepo icvRepo;
    List<ChucVu> lstCv;
    public ChucVuService() {
        icvRepo = new ChucVuRepo();
        lstCv = icvRepo.selectAllBySQL();
    }

    @Override
    public boolean add(ChucVu cv) {
        icvRepo.insert(cv);
        return true;
    }

    @Override
    public boolean update(ChucVu cv) {
        icvRepo.update(cv);
        return true;
    }

    @Override
    public boolean delete(ChucVu id) {
        icvRepo.delete(id);
        return true;
    }

    @Override
    public List<ChucVu> getdata() {
        return icvRepo.selectAllBySQL();
    }

    @Override
    public List<ChucVu> getdataByName(String name) {
        return icvRepo.findByName(name);
    }
    
    
}
