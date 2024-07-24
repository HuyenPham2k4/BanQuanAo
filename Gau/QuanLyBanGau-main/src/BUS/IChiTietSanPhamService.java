/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BUS;

import Models.ChiTietSanPham;
import ViewModels.ChiTietSanPhamViewModels;
import java.util.List;

/**
 *
 * @author qivub
 */
public interface IChiTietSanPhamService {
    public boolean addSanPham(ChiTietSanPham sp);
    public boolean updateSanPham(ChiTietSanPham ID);
    public boolean temporaryDeleteSanPham(ChiTietSanPham ID);
    public boolean deleteSanPham(ChiTietSanPham ID);
    public List<ChiTietSanPhamViewModels> findIDSP(int ID);
    public List<ChiTietSanPhamViewModels> findTenSP(String ten);
    public List<ChiTietSanPhamViewModels> locDanhMucSP(String ten);
    public List<ChiTietSanPhamViewModels> locMauSac(String ten);
    public List<ChiTietSanPhamViewModels> locChatLieu(String ten);
    public List<ChiTietSanPhamViewModels> locKichCo(String ten);
    public List<ChiTietSanPhamViewModels> locSize(String ten);
    public List<ChiTietSanPhamViewModels> locThuongHieu(String ten);
    public List<ChiTietSanPham> getData();
    public List<ChiTietSanPhamViewModels> getDataViewModels();
//    public List<ChiTietSanPham> getDataByID(int ID);
}
