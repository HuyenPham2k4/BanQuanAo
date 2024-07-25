/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Models.HoaDon;
import Utilities.JdbcHelper;
import ViewModels.HoaDonViewmodels;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author qivub
 */
public class HoaDonRepo implements IHoaDonRepo{
    private JdbcHelper _jdbcHelper;
        String INSERT_SQL = "INSERT INTO HoaDon (MaHoaDon,ID_KhachHang,TenKhachHang,DiaChi,SoDienThoai,ID_NhanVien,NgayLapHoaDon,TongTien,GhiChu,TrangThai) VALUES (?,?,?,?,?,?,?,?,?,?)";
        String UPDATE_SQL = "UPDATE HoaDon SET ID_KhachHang = ?, TenKhachHang = ?,DiaChi = ?,SoDienThoai = ?,ID_NhanVien = ?,NgayLapHoaDon = ?,TongTien = ?,GhiChu = ?,TrangThai = ? WHERE MaHoaDon = ? ";
        String THANH_TOAN = "UPDATE HoaDon SET TrangThai = 0 WHERE  MaHoaDon = ? ";
        String DELETE_SQL = "DELETE HoaDon WHERE MaHoaDon = ?";
        String SELECT_VIEW_MODELS = "SELECT \n" +
                                "    HoaDon.ID_HoaDon,\n" +
                                "    HoaDon.MaHoaDon,\n" +
                                "    HoaDon.TenKhachHang,\n" +
                                "    HoaDon.SoDienThoai,\n" +
                                "    HoaDon.DiaChi,\n" +
                                "    NhanVien.TenNhanVien,\n" +
                                "    HoaDon.ID_NhanVien,\n" +
                                "    HoaDon.NgayLapHoaDon,\n" +
                                "    SUM(ChiTietHoaDon.Gia) AS TongTien,\n" +
                                "    HoaDon.GhiChu,\n" +
                                "    HoaDon.TrangThai\n" +
                                "FROM \n" +
                                "    HoaDon\n" +
                                "INNER JOIN \n" +
                                "    KhachHang ON HoaDon.ID_KhachHang = KhachHang.ID_KhachHang\n" +
                                "INNER JOIN\n" +
                                "    NhanVien ON HoaDon.ID_NhanVien = NhanVien.ID_NhanVien\n" +
                                "LEFT JOIN\n" +
                                "    ChiTietHoaDon ON HoaDon.ID_HoaDon = ChiTietHoaDon.ID_HoaDon\n" +
                                "WHERE \n" +
                                "    HoaDon.TrangThai IN (1, 2, 3)\n" +
                                "GROUP BY \n" +
                                "    HoaDon.ID_HoaDon,\n" +
                                "    HoaDon.MaHoaDon,\n" +
                                "    HoaDon.TenKhachHang,\n" +
                                "    HoaDon.SoDienThoai,\n" +
                                "    HoaDon.DiaChi,\n" +
                                "    NhanVien.TenNhanVien,\n" +
                                "    HoaDon.ID_NhanVien,\n" +
                                "    HoaDon.NgayLapHoaDon,\n" +
                                "    HoaDon.GhiChu,\n" +
                                "    HoaDon.TrangThai;";
        String SELECT_ALL_SQL = "SELECT * FROM HoaDon WHERE TrangThai IN (1, 2, 3);";
        String SELECT_BY_ID_SQL = "SELECT * FROM HoaDon WHERE ID_HoaDon = ?";
    public HoaDonRepo() {
        _jdbcHelper = new JdbcHelper();
    }
    @Override
    public List<HoaDon> selectBySQL(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = _jdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                HoaDon hoadon = new HoaDon();
                
                hoadon.setID_HoaDon(rs.getInt("ID_HoaDon"));
                hoadon.setMaHoaDon(rs.getString("MaHoaDon"));
                hoadon.setID_KhachHang(rs.getInt("ID_KhachHang"));
                hoadon.setTenKhachHang(rs.getString("TenKhachHang"));
                hoadon.setDiaChi(rs.getString("DiaChi"));
                hoadon.setSoDienThoai(rs.getInt("SoDienThoai"));
                hoadon.setID_NhanVien(rs.getInt("ID_NhanVien"));
                hoadon.setNgayLapHoaDon(rs.getDate("NgayLapHoaDon"));
                hoadon.setTongTien(rs.getInt("TongTien"));
                hoadon.setGhiChu(rs.getString("GhiChu"));
                hoadon.setTrangThai(rs.getInt("TrangThai"));
                list.add(hoadon);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }
    @Override
    public boolean insert(HoaDon entity) {
        _jdbcHelper.executeUpdate(INSERT_SQL,
                entity.getMaHoaDon(),
                entity.getID_KhachHang(),
                entity.getTenKhachHang(),
                entity.getDiaChi(), 
                entity.getSoDienThoai(), 
                entity.getID_NhanVien(), 
                entity.getNgayLapHoaDon(), 
                entity.getTongTien(),
                entity.getGhiChu(),
                entity.getTrangThai()
        );
                
        return true;
    }

    @Override
    public boolean update(HoaDon entity) {
        _jdbcHelper.executeUpdate(UPDATE_SQL, 
                                entity.getID_KhachHang(),
                                entity.getTenKhachHang(),
                                entity.getDiaChi(), 
                                entity.getSoDienThoai(), 
                                entity.getID_NhanVien(), 
                                entity.getNgayLapHoaDon(), 
                                entity.getTongTien(), 
                                entity.getGhiChu(),
                                entity.getTrangThai(),
                                entity.getMaHoaDon()
                                
        );
        return true;
    }

    @Override
    public boolean delete(HoaDon entity) {
        _jdbcHelper.executeUpdate(DELETE_SQL, entity.getMaHoaDon());
        return true;
    }

    @Override
    public boolean THANH_TOAN(HoaDon entity) {
       _jdbcHelper.executeUpdate(THANH_TOAN, entity.getMaHoaDon());
       return true;
    }

    @Override
    public List<HoaDon> selectAll() {
        return this.selectAllBySQL(SELECT_ALL_SQL);
    }

    @Override
    public List<HoaDon> selectByID(int ID, HoaDon entity) {
        return this.selectBySQL(SELECT_BY_ID_SQL, ID);
    }

    @Override
    public List<HoaDonViewmodels> selectAllViewModels() {
        return this.selectBySQLViewModels(SELECT_VIEW_MODELS);
    }

    @Override
    public List<HoaDonViewmodels> selectBySQLViewModels(String sql, Object... args) {
        List<HoaDonViewmodels> list = new ArrayList<>();
        try {
            ResultSet rs = _jdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                HoaDonViewmodels hoaDonView = new HoaDonViewmodels();
                hoaDonView.setID_HoaDon(rs.getString("ID_HoaDon")); 
                hoaDonView.setMaHoaDon(rs.getString("MaHoaDon"));
                hoaDonView.setNhanVien(rs.getString("TenNhanVien"));
                hoaDonView.setTenKhachHang(rs.getString("TenKhachHang"));
                hoaDonView.setDiaChi(rs.getString("DiaChi"));
                hoaDonView.setSoDienThoai(rs.getInt("SoDienThoai"));
                hoaDonView.setNgayLapHoaDon(rs.getDate("NgayLapHoaDon"));
                hoaDonView.setTongTien(rs.getString("TongTien"));
                hoaDonView.setGhiChu(rs.getString("GhiChu"));
                hoaDonView.setTrangThai(rs.getInt("TrangThai"));
                list.add(hoaDonView);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<HoaDon> selectAllBySQL(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = _jdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                HoaDon hoadon = new HoaDon();
                
                hoadon.setMaHoaDon(rs.getString("MaHoaDon"));
                hoadon.setID_KhachHang(rs.getInt("ID_KhachHang"));
                hoadon.setTenKhachHang(rs.getString("TenKhachHang"));
                hoadon.setDiaChi(rs.getString("DiaChi"));
                hoadon.setSoDienThoai(rs.getInt("SoDienThoai"));
                hoadon.setID_NhanVien(rs.getInt("ID_NhanVien"));
                hoadon.setNgayLapHoaDon(rs.getDate("NgayLapHoaDon"));
                hoadon.setTongTien(rs.getInt("TongTien"));
                hoadon.setGhiChu(rs.getString("GhiChu"));
                hoadon.setTrangThai(rs.getInt("TrangThai"));
                list.add(hoadon);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public List<HoaDonViewmodels> findByPhoneCustomer(String name) {
        return selectBySQLViewModels("SELECT \n"
                + "	HoaDon.ID_HoaDon,\n"
                + "    HoaDon.MaHoaDon,\n"
                + "    HoaDon.TenKhachHang,\n"
                + "    HoaDon.SoDienThoai,\n"
                + "    HoaDon.DiaChi,\n"
                + "    NhanVien.TenNhanVien,\n"
                + "    HoaDon.ID_NhanVien,\n"
                + "    HoaDon.NgayLapHoaDon,\n"
                + "    HoaDon.GhiChu,\n"
                + "    HoaDon.TrangThai,\n"
                + "	HoaDon.TongTien\n"
                + "	FROM HoaDon\n"
                + "	INNER JOIN NhanVien ON HoaDon.ID_NhanVien = NhanVien.ID_NhanVien\n"
                + "	WHERE SoDienThoai LIKE N'%" + name + "%'");
    }

    @Override
    public List<HoaDonViewmodels> fintByMaHD(String maHD) {
        return selectBySQLViewModels("SELECT \n"
                + "	HoaDon.ID_HoaDon,\n"
                + "    HoaDon.MaHoaDon,\n"
                + "    HoaDon.TenKhachHang,\n"
                + "    HoaDon.SoDienThoai,\n"
                + "    HoaDon.DiaChi,\n"
                + "    NhanVien.TenNhanVien,\n"
                + "    HoaDon.ID_NhanVien,\n"
                + "    HoaDon.NgayLapHoaDon,\n"
                + "    HoaDon.GhiChu,\n"
                + "    HoaDon.TrangThai,\n"
                + "	HoaDon.TongTien\n"
                + "	FROM HoaDon\n"
                + "	INNER JOIN NhanVien ON HoaDon.ID_NhanVien = NhanVien.ID_NhanVien\n"
                + "	WHERE MaHoaDon LIKE N'%" + maHD + "%'");
    }

    @Override
    public List<HoaDonViewmodels> hoadonDaThanhToan() {
         return this.selectBySQLViewModels("SELECT \n" +
                                "    HoaDon.ID_HoaDon,\n" +
                                "    HoaDon.MaHoaDon,\n" +
                                "    HoaDon.TenKhachHang,\n" +
                                "    HoaDon.SoDienThoai,\n" +
                                "    HoaDon.DiaChi,\n" +
                                "    NhanVien.TenNhanVien,\n" +
                                "    HoaDon.ID_NhanVien,\n" +
                                "    HoaDon.NgayLapHoaDon,\n" +
                                "    SUM(ChiTietHoaDon.Gia) AS TongTien,\n" +
                                "    HoaDon.GhiChu,\n" +
                                "    HoaDon.TrangThai\n" +
                                "FROM \n" +
                                "    HoaDon\n" +
                                "INNER JOIN \n" +
                                "    KhachHang ON HoaDon.ID_KhachHang = KhachHang.ID_KhachHang\n" +
                                "INNER JOIN\n" +
                                "    NhanVien ON HoaDon.ID_NhanVien = NhanVien.ID_NhanVien\n" +
                                "LEFT JOIN\n" +
                                "    ChiTietHoaDon ON HoaDon.ID_HoaDon = ChiTietHoaDon.ID_HoaDon\n" +
                                "WHERE \n" +
                                "    HoaDon.TrangThai=0\n" +
                                "GROUP BY \n" +
                                "    HoaDon.ID_HoaDon,\n" +
                                "    HoaDon.MaHoaDon,\n" +
                                "    HoaDon.TenKhachHang,\n" +
                                "    HoaDon.SoDienThoai,\n" +
                                "    HoaDon.DiaChi,\n" +
                                "    NhanVien.TenNhanVien,\n" +
                                "    HoaDon.ID_NhanVien,\n" +
                                "    HoaDon.NgayLapHoaDon,\n" +
                                "    HoaDon.GhiChu,\n" +
                                "    HoaDon.TrangThai;");
    }

   
    
    
}
