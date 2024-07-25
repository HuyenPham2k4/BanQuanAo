/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import Dao.ChiTietHoaDonRepo;
import Dao.IChiTietHoaDonRepo;
import Models.ChiTietHoaDon;
import Utilities.SessionHelper;
import java.util.List;

/**
 *
 * @author qivub
 */
public class ChiTietHoaDonService implements IChiTietHoaDonService{
    IChiTietHoaDonRepo _iCTHDRepo;
    List<ChiTietHoaDon> _lstCTHD;

    public ChiTietHoaDonService() {
        _iCTHDRepo = new ChiTietHoaDonRepo();
//        _lstCTHD = _iCTHDRepo.selectAll();
    }

    @Override
    public boolean addCTHD(ChiTietHoaDon KH) {
        _iCTHDRepo.insert(KH);
        return true;
    }

    @Override
    public boolean updateCTHD(ChiTietHoaDon ID) {
        _iCTHDRepo.update(ID);
        return true;
    }

    @Override
    public boolean temporaryDeleteCTHD(ChiTietHoaDon ID) {
        _iCTHDRepo.temporaryDelete(ID);
        return true;
    }

    @Override
    public boolean deleteCTHD(int ID) {
        _iCTHDRepo.delete(ID);
        return true;
    }

    @Override
    public List<ChiTietHoaDon> getDataByID(int ID) {
        return _iCTHDRepo.selectByIDHoaDon(ID);
    }

    @Override
    public List<ChiTietHoaDon> getSoluong(int idCTSP, int idHoaDon) {
        return _iCTHDRepo.selectByIDCTSP(idCTSP, idHoaDon);
    }

    @Override
    public boolean updateSoLuong(ChiTietHoaDon ID) {
        _iCTHDRepo.updateSoluong(ID);
        return true;
    }

    @Override
    public boolean muaNhieu(ChiTietHoaDon ID) {
        _iCTHDRepo.updateNhieuSoLuong(ID);
        return true;
    }

//    @Override
//    public boolean checkConHang(int maSanPham, int soLuong) {
//        _iCTHDRepo.checkConHang(maSanPham, soLuong);
//        return true;
//    }
//
//    @Override
//    public int getTonKho(int maSanPham) {
//        _iCTHDRepo.getTonKho(maSanPham);
//        return maSanPham;
//    }
    
}
