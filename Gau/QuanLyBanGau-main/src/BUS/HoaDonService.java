/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import Dao.HoaDonRepo;
import Dao.IHoaDonRepo;
import Models.HoaDon;
import ViewModels.HoaDonViewmodels;
import java.util.List;

/**
 *
 * @author qivub
 */
public class HoaDonService implements IHoaDonService{
    IHoaDonRepo _iHoaDonRepo;
    List<HoaDon> _lstHoaDon;
    public HoaDonService() {
        _iHoaDonRepo = new HoaDonRepo();
        _lstHoaDon = _iHoaDonRepo.selectAll();
    }
    
    @Override
    public boolean addHoaDon(HoaDon hoaDon) {
        _iHoaDonRepo.insert(hoaDon);
        return true;
    }

    @Override
    public boolean UpdateHoaDon(HoaDon ID) {
        _iHoaDonRepo.update(ID);
        return true;
    }

    @Override
    public boolean thanhToanHoaDon(HoaDon ID) {
        _iHoaDonRepo.THANH_TOAN(ID);
        return true;
    }

    @Override
    public boolean deleteHoaDon(HoaDon ID) {
        _iHoaDonRepo.delete(ID);
        return true;
    }

    @Override
    public List<HoaDon> getData() {
        return _iHoaDonRepo.selectAll();
    }

    @Override
    public List<HoaDonViewmodels> getDataViewModels() {
        return _iHoaDonRepo.selectAllViewModels();
    }   

    @Override
    public List<HoaDonViewmodels> findBySDTKhachHang(String name) {
       return _iHoaDonRepo.findByPhoneCustomer(name);
    }

    @Override
    public List<HoaDonViewmodels> findbyMaHoaDon(String maHoaDon) {
        return _iHoaDonRepo.fintByMaHD(maHoaDon);
    }

    @Override
    public List<HoaDonViewmodels> hoadonDaThanhToan() {
        return _iHoaDonRepo.hoadonDaThanhToan();
    }
}
