/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import Models.ChiTietSanPham;
import ViewModels.ChiTietSanPhamViewModels;
import java.util.List;

/**
 *
 * @author qivub
 */
public interface IChitietSanPhamRepo {
    public boolean insert(ChiTietSanPham _entity);
    public boolean update(ChiTietSanPham _entity);
    public boolean delete(ChiTietSanPham _entity);
    public boolean temporaryDelete(ChiTietSanPham _entity);
    public List<ChiTietSanPham> selectAll();
    public List<ChiTietSanPhamViewModels> locDanhMucSP(String name);
    public List<ChiTietSanPhamViewModels> locSize(String name);
    public List<ChiTietSanPhamViewModels> locChatLieu(String name);
    public List<ChiTietSanPhamViewModels> locThuongHieu(String name);
    public List<ChiTietSanPhamViewModels> LocChatLieu(String name);
    public List<ChiTietSanPhamViewModels> locKichCo(String name);
    public List<ChiTietSanPhamViewModels> locMauSac(String name);
    public List<ChiTietSanPhamViewModels> selectByID(int ID);
    public List<ChiTietSanPhamViewModels> selectByName(String name);
    public List<ChiTietSanPham> selectBySQL(String sql, Object... args);
    public List<ChiTietSanPhamViewModels> selectAllViewModels();
    public List<ChiTietSanPhamViewModels> selectBySQLViewModels(String sql);
}
