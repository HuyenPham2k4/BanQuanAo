/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Models.ChiTietSanPham;
import Utilities.JdbcHelper;
import ViewModels.ChiTietSanPhamViewModels;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author qivub
 */
public class ChiTietSanPhamRepo implements IChitietSanPhamRepo {
    private JdbcHelper _jdbcHelper;
    String INSERT_SQL = "INSERT INTO ChiTietSanPham (TenSanPham,ID_Size,ID_DanhMucSanPham ,Gia,ID_MauSac,AnhSanPham,ID_ThuongHieu,ID_ChatLieu,ID_TrongLuong,SoLuong,TrangThai ) VALUES (?,?,?,?,?,?,?,?,?,?,'1')";
    String UPDATE_SQL = "UPDATE ChiTietSanPham SET TenSanPham = ?,ID_Size = ?,ID_DanhMucSanPham = ?,Gia = ?, ID_MauSac = ?, ID_ThuongHieu = ?, ID_ChatLieu = ?, ID_TrongLuong = ?,SoLuong = ?,TrangThai = 1 WHERE ID_ChiTietSanPham = ? ";
    String TEMPORARY_DELETE_SQL = "UPDATE ChiTietSanPham SET  TrangThai = 0 WHERE ID_ChiTietSanPham = ? ";
    String DELETE_SQL = "DELETE ChiTietSanPham WHERE ID_ChiTietSanPham = ?";
    String SELECT_ALL_SQL = "SELECT * FROM ChiTietSanPham ";
    String SELECT_BY_ID_SQL = "SELECT * FROM ChiTietSanPham WHERE ID_ChiTietSanPham = ?";
    String FIND_NAME = "SELECT * FROM ChiTietSanPham WHERE TenSanPham=?";
    String SELECT_VIEW_MODELS = " SELECT\n"
            + "    ChiTietSanPham.ID_ChiTietSanPham,\n"
            + "    ChiTietSanPham.TenSanPham,\n"
            + "    Size.TenSize,\n"
            + "    DanhMucSP.TenDanhMuc,\n"
            + "    ChiTietSanPham.Gia,\n"
            + "    MauSac.TenMauSac,\n"
            + "    ChiTietSanPham.AnhSanPham,\n" 
            + "    ThuongHieu.TenThuongHieu,\n"
            + "    ChatLieu.TenChatLieu,\n"
            + "    TrongLuong.TrongLuong,\n"
            + "    ChiTietSanPham.SoLuong,\n"
            + "    ChiTietSanPham.TrangThai\n"
            + "FROM ChiTietSanPham\n"
            + "INNER JOIN Size ON ChiTietSanPham.ID_Size = Size.ID_Size\n"
            + "INNER JOIN DanhMucSP ON ChiTietSanPham.ID_DanhMucSanPham = DanhMucSP.ID_Danhmucsanpham\n"
            + "INNER JOIN MauSac ON ChiTietSanPham.ID_MauSac = MauSac.ID_MauSac\n"
            + "INNER JOIN ThuongHieu ON ChiTietSanPham.ID_ThuongHieu = ThuongHieu.ID_ThuongHieu\n"
            + "INNER JOIN ChatLieu ON ChiTietSanPham.ID_ChatLieu = ChatLieu.ID_ChatLieu\n"
            + "INNER JOIN TrongLuong ON ChiTietSanPham.ID_TrongLuong = TrongLuong.ID_TrongLuong;";

    public ChiTietSanPhamRepo() {
        _jdbcHelper = new JdbcHelper();
    }

    @Override
    public List<ChiTietSanPham> selectBySQL(String sql, Object... args) {
        List<ChiTietSanPham> list = new ArrayList<>();
        try {
            ResultSet rs = _jdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                ChiTietSanPham CTSP = new ChiTietSanPham();
                CTSP.setID_ChiTietSanPham(rs.getInt("ID_ChiTietSanPham"));
                CTSP.setTenSanPham(rs.getString("TenSanPham"));
                CTSP.setID_Size(rs.getInt("ID_Size"));
                CTSP.setID_DanhMucSanPham(rs.getInt("ID_DanhMucSanPham"));
                CTSP.setGia(rs.getInt("Gia"));
                CTSP.setID_MauSac(rs.getInt("ID_MauSac"));
//                CTSP.setID_AnhSanPham(rs.getString("ID_AnhSanPham"));
                CTSP.setID_AnhSanPham(rs.getString("AnhSanPham"));
                CTSP.setID_ThuongHieu(rs.getInt("ID_ThuongHieu"));
                CTSP.setID_ChatLieu(rs.getInt("ID_ChatLieu"));
                CTSP.setID_TrongLuong(rs.getInt("ID_TrongLuong"));
                CTSP.setSoLuong(rs.getInt("SoLuong"));
                CTSP.setTrangThai(rs.getInt("TrangThai"));
                list.add(CTSP);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ChiTietSanPhamViewModels> selectBySQLViewModels(String sql) {
        List<ChiTietSanPhamViewModels> list = new ArrayList<>();
        try {
            ResultSet rs = _jdbcHelper.executeQuery(sql);
            while (rs.next()) {
                ChiTietSanPhamViewModels CTSP = new ChiTietSanPhamViewModels();
                CTSP.setID_ChiTietSanPham(rs.getString("ID_ChiTietSanPham"));
                CTSP.setTenSanPham(rs.getString("TenSanPham"));
                CTSP.setID_Size(rs.getString("TenSize"));
                CTSP.setID_DanhMucSanPham(rs.getString("TenDanhMuc"));
                CTSP.setGia(rs.getInt("Gia"));
                CTSP.setID_MauSac(rs.getString("TenMauSac"));
                CTSP.setID_AnhSanPham(rs.getString("AnhSanPham"));
                CTSP.setID_ThuongHieu(rs.getString("TenThuongHieu"));
                CTSP.setID_ChatLieu(rs.getString("TenChatLieu"));
                CTSP.setID_TrongLuong(rs.getString("TrongLuong"));
                CTSP.setSoLuong(rs.getInt("SoLuong"));
                CTSP.setTrangThai(rs.getInt("TrangThai"));
                list.add(CTSP);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean insert(ChiTietSanPham entity) {
        _jdbcHelper.executeUpdate(INSERT_SQL,
                entity.getTenSanPham(),
                entity.getID_Size(),
                entity.getID_DanhMucSanPham(),
                entity.getGia(),
                entity.getID_MauSac(),
//                entity.getID_AnhSanPham(),
                entity.getID_ThuongHieu(),
                entity.getID_ChatLieu(),
                entity.getID_TrongLuong(),
                entity.getSoLuong());
        return true;
    }

    @Override
    public boolean update(ChiTietSanPham entity) {
        _jdbcHelper.executeUpdate(UPDATE_SQL,
                entity.getTenSanPham(),
                entity.getID_Size(),
                entity.getID_DanhMucSanPham(),
                entity.getGia(),
                entity.getID_MauSac(),
//                entity.getID_AnhSanPham(),
                entity.getID_ThuongHieu(),
                entity.getID_ChatLieu(),
                entity.getID_TrongLuong(),
                entity.getSoLuong(),
                //                                entity.getTrangThai(), 
                entity.getID_ChiTietSanPham());
        return true;
    }

    @Override
    public boolean delete(ChiTietSanPham entity) {
        _jdbcHelper.executeUpdate(DELETE_SQL,
                entity.getID_ChiTietSanPham(),
                entity.getTenSanPham(),
                entity.getID_Size(),
                entity.getID_DanhMucSanPham(),
                entity.getGia(),
                entity.getID_MauSac(),
                entity.getID_AnhSanPham(),
                entity.getID_ThuongHieu(),
                entity.getID_ChatLieu(),
                entity.getID_TrongLuong(),
                entity.getSoLuong(),
                entity.getTrangThai());
        return true;
    }

    @Override
    public boolean temporaryDelete(ChiTietSanPham entity) {
        _jdbcHelper.executeUpdate(TEMPORARY_DELETE_SQL, entity.getID_ChiTietSanPham());
        return true;
    }

    @Override
    public List<ChiTietSanPham> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public List<ChiTietSanPhamViewModels> selectByID(int ID) {
//        ID = entity.getID_ChiTietSanPham();
        return this.selectBySQLViewModels(" SELECT ChiTietSanPham.ID_ChiTietSanPham,\n"
                + "		 ChiTietSanPham.TenSanPham, \n"
                + "		 Size.TenSize, \n"
                + "		 DanhMucSP.TenDanhMuc, \n"
                + "		 ChiTietSanPham.Gia,\n"
                + "		 MauSac.TenMauSac,\n"
                + "		 MauSac.TenMauSac,\n"
                + "		 ThuongHieu.TenThuongHieu,\n"
                + "		 ChatLieu.TenChatLieu,\n"
                + "		 TrongLuong.TrongLuong,\n"
                + "		 ChiTietSanPham.SoLuong,\n"
                + "		 ChiTietSanPham.TrangThai,\n"
                + "		 ChiTietSanPham.AnhSanPham\n"
                + "	FROM ChiTietSanPham\n"
                + "		 INNER JOIN Size ON ChiTietSanPham.ID_Size = Size.ID_Size\n"
                + "		 INNER JOIN DanhMucSP ON ChiTietSanPham.ID_DanhMucSanPham = DanhMucSP.ID_Danhmucsanpham\n"
                + "		 INNER JOIN MauSac ON ChiTietSanPham.ID_MauSac = MauSac.ID_MauSac\n"
                + "		 INNER JOIN ThuongHieu ON ChiTietSanPham.ID_ThuongHieu = ThuongHieu.ID_ThuongHieu\n"
                + "		 INNER JOIN ChatLieu ON ChiTietSanPham.ID_ChatLieu = ChatLieu.ID_ChatLieu\n"
                + "		 INNER JOIN TrongLuong ON ChiTietSanPham.ID_TrongLuong = TrongLuong.ID_TrongLuong\n"
                + "		 WHERE ID_ChiTietSanPham LIKE N'" + ID + "%';");
    }

    @Override
    public List<ChiTietSanPhamViewModels> selectAllViewModels() {
        return this.selectBySQLViewModels(SELECT_VIEW_MODELS);
    }

    @Override
    public List<ChiTietSanPhamViewModels> selectByName(String name) {
        return selectBySQLViewModels(" SELECT ChiTietSanPham.ID_ChiTietSanPham,\n"
                + "		 ChiTietSanPham.TenSanPham, \n"
                + "		 Size.TenSize, \n"
                + "		 DanhMucSP.TenDanhMuc, \n"
                + "		 ChiTietSanPham.Gia,\n"
                + "		 MauSac.TenMauSac,\n"
                + "		 ThuongHieu.TenThuongHieu,\n"
                + "              ChiTietSanPham.AnhSanPham,\n" 
                + "		 ChatLieu.TenChatLieu,\n"
                + "		 TrongLuong.TrongLuong,\n"
                + "		 ChiTietSanPham.SoLuong,\n"
                + "		 ChiTietSanPham.TrangThai,\n"
                + "		 ChiTietSanPham.AnhSanPham\n"
                + "	FROM ChiTietSanPham\n"
                + "		 INNER JOIN Size ON ChiTietSanPham.ID_Size = Size.ID_Size\n"
                + "		 INNER JOIN DanhMucSP ON ChiTietSanPham.ID_DanhMucSanPham = DanhMucSP.ID_Danhmucsanpham\n"
                + "		 INNER JOIN MauSac ON ChiTietSanPham.ID_MauSac = MauSac.ID_MauSac\n"
                + "		 INNER JOIN ThuongHieu ON ChiTietSanPham.ID_ThuongHieu = ThuongHieu.ID_ThuongHieu\n"
                + "		 INNER JOIN ChatLieu ON ChiTietSanPham.ID_ChatLieu = ChatLieu.ID_ChatLieu\n"
                + "		 INNER JOIN TrongLuong ON ChiTietSanPham.ID_TrongLuong = TrongLuong.ID_TrongLuong\n"
                + "		 WHERE TenSanPham LIKE N'%"+name+"%'");
    }

    @Override
    public List<ChiTietSanPhamViewModels> locDanhMucSP(String name) {
        return selectBySQLViewModels(" SELECT ChiTietSanPham.ID_ChiTietSanPham,\n"
                + "		 ChiTietSanPham.TenSanPham, \n"
                + "		 Size.TenSize, \n"
                + "		 DanhMucSP.TenDanhMuc, \n"
                + "		 ChiTietSanPham.Gia,\n"
                + "		 MauSac.TenMauSac,\n"
                + "		 ThuongHieu.TenThuongHieu,\n"
                + "              ChiTietSanPham.AnhSanPham,\n" 
                + "		 ChatLieu.TenChatLieu,\n"
                + "		 TrongLuong.TrongLuong,\n"
                + "		 ChiTietSanPham.SoLuong,\n"
                + "		 ChiTietSanPham.TrangThai,\n"
                + "		 ChiTietSanPham.AnhSanPham\n"
                + "	FROM ChiTietSanPham\n"
                + "		 INNER JOIN Size ON ChiTietSanPham.ID_Size = Size.ID_Size\n"
                + "		 INNER JOIN DanhMucSP ON ChiTietSanPham.ID_DanhMucSanPham = DanhMucSP.ID_Danhmucsanpham\n"
                + "		 INNER JOIN MauSac ON ChiTietSanPham.ID_MauSac = MauSac.ID_MauSac\n"
                + "		 INNER JOIN ThuongHieu ON ChiTietSanPham.ID_ThuongHieu = ThuongHieu.ID_ThuongHieu\n"
                + "		 INNER JOIN ChatLieu ON ChiTietSanPham.ID_ChatLieu = ChatLieu.ID_ChatLieu\n"
                + "		 INNER JOIN TrongLuong ON ChiTietSanPham.ID_TrongLuong = TrongLuong.ID_TrongLuong\n"
                + "		 WHERE TenDanhMuc LIKE N'%"+name+"%'");
    }

    @Override
    public List<ChiTietSanPhamViewModels> locSize(String name) {
        return selectBySQLViewModels(" SELECT ChiTietSanPham.ID_ChiTietSanPham,\n"
                + "		 ChiTietSanPham.TenSanPham, \n"
                + "		 Size.TenSize, \n"
                + "		 DanhMucSP.TenDanhMuc, \n"
                + "		 ChiTietSanPham.Gia,\n"
                + "		 MauSac.TenMauSac,\n"
                + "		 ThuongHieu.TenThuongHieu,\n"
                + "              ChiTietSanPham.AnhSanPham,\n" 
                + "		 ChatLieu.TenChatLieu,\n"
                + "		 TrongLuong.TrongLuong,\n"
                + "		 ChiTietSanPham.SoLuong,\n"
                + "		 ChiTietSanPham.TrangThai,\n"
                + "		 ChiTietSanPham.AnhSanPham\n"
                + "	FROM ChiTietSanPham\n"
                + "		 INNER JOIN Size ON ChiTietSanPham.ID_Size = Size.ID_Size\n"
                + "		 INNER JOIN DanhMucSP ON ChiTietSanPham.ID_DanhMucSanPham = DanhMucSP.ID_Danhmucsanpham\n"
                + "		 INNER JOIN MauSac ON ChiTietSanPham.ID_MauSac = MauSac.ID_MauSac\n"
                + "		 INNER JOIN ThuongHieu ON ChiTietSanPham.ID_ThuongHieu = ThuongHieu.ID_ThuongHieu\n"
                + "		 INNER JOIN ChatLieu ON ChiTietSanPham.ID_ChatLieu = ChatLieu.ID_ChatLieu\n"
                + "		 INNER JOIN TrongLuong ON ChiTietSanPham.ID_TrongLuong = TrongLuong.ID_TrongLuong\n"
                + "		 WHERE TenSize LIKE N'%"+name+"%'");
    }

    @Override
    public List<ChiTietSanPhamViewModels> locChatLieu(String name) {
        return selectBySQLViewModels(" SELECT ChiTietSanPham.ID_ChiTietSanPham,\n"
                + "		 ChiTietSanPham.TenSanPham, \n"
                + "		 Size.TenSize, \n"
                + "		 DanhMucSP.TenDanhMuc, \n"
                + "		 ChiTietSanPham.Gia,\n"
                + "		 MauSac.TenMauSac,\n"
                + "		 ThuongHieu.TenThuongHieu,\n"
                + "              ChiTietSanPham.AnhSanPham,\n" 
                + "		 ChatLieu.TenChatLieu,\n"
                + "		 TrongLuong.TrongLuong,\n"
                + "		 ChiTietSanPham.SoLuong,\n"
                + "		 ChiTietSanPham.TrangThai,\n"
                + "		 ChiTietSanPham.AnhSanPham\n"
                + "	FROM ChiTietSanPham\n"
                + "		 INNER JOIN Size ON ChiTietSanPham.ID_Size = Size.ID_Size\n"
                + "		 INNER JOIN DanhMucSP ON ChiTietSanPham.ID_DanhMucSanPham = DanhMucSP.ID_Danhmucsanpham\n"
                + "		 INNER JOIN MauSac ON ChiTietSanPham.ID_MauSac = MauSac.ID_MauSac\n"
                + "		 INNER JOIN ThuongHieu ON ChiTietSanPham.ID_ThuongHieu = ThuongHieu.ID_ThuongHieu\n"
                + "		 INNER JOIN ChatLieu ON ChiTietSanPham.ID_ChatLieu = ChatLieu.ID_ChatLieu\n"
                + "		 INNER JOIN TrongLuong ON ChiTietSanPham.ID_TrongLuong = TrongLuong.ID_TrongLuong\n"
                + "		 WHERE TenChatLieu LIKE N'%"+name+"%'");
    }

    @Override
    public List<ChiTietSanPhamViewModels> locThuongHieu(String name) {
       return selectBySQLViewModels(" SELECT ChiTietSanPham.ID_ChiTietSanPham,\n"
                + "		 ChiTietSanPham.TenSanPham, \n"
                + "		 Size.TenSize, \n"
                + "		 DanhMucSP.TenDanhMuc, \n"
                + "		 ChiTietSanPham.Gia,\n"
                + "		 MauSac.TenMauSac,\n"
                + "		 ThuongHieu.TenThuongHieu,\n"
                + "              ChiTietSanPham.AnhSanPham,\n" 
                + "		 ChatLieu.TenChatLieu,\n"
                + "		 TrongLuong.TrongLuong,\n"
                + "		 ChiTietSanPham.SoLuong,\n"
                + "		 ChiTietSanPham.TrangThai,\n"
                + "		 ChiTietSanPham.AnhSanPham\n"
                + "	FROM ChiTietSanPham\n"
                + "		 INNER JOIN Size ON ChiTietSanPham.ID_Size = Size.ID_Size\n"
                + "		 INNER JOIN DanhMucSP ON ChiTietSanPham.ID_DanhMucSanPham = DanhMucSP.ID_Danhmucsanpham\n"
                + "		 INNER JOIN MauSac ON ChiTietSanPham.ID_MauSac = MauSac.ID_MauSac\n"
                + "		 INNER JOIN ThuongHieu ON ChiTietSanPham.ID_ThuongHieu = ThuongHieu.ID_ThuongHieu\n"
                + "		 INNER JOIN ChatLieu ON ChiTietSanPham.ID_ChatLieu = ChatLieu.ID_ChatLieu\n"
                + "		 INNER JOIN TrongLuong ON ChiTietSanPham.ID_TrongLuong = TrongLuong.ID_TrongLuong\n"
                + "		 WHERE TenThuongHieu LIKE N'%"+name+"%'");
    }

    @Override
    public List<ChiTietSanPhamViewModels> LocChatLieu(String name) {
        return selectBySQLViewModels(" SELECT ChiTietSanPham.ID_ChiTietSanPham,\n"
                + "		 ChiTietSanPham.TenSanPham, \n"
                + "		 Size.TenSize, \n"
                + "		 DanhMucSP.TenDanhMuc, \n"
                + "		 ChiTietSanPham.Gia,\n"
                + "		 MauSac.TenMauSac,\n"
                + "		 ThuongHieu.TenThuongHieu,\n"
                + "              ChiTietSanPham.AnhSanPham,\n" 
                + "		 ChatLieu.TenChatLieu,\n"
                + "		 TrongLuong.TrongLuong,\n"
                + "		 ChiTietSanPham.SoLuong,\n"
                + "		 ChiTietSanPham.TrangThai,\n"
                + "		 ChiTietSanPham.AnhSanPham\n"
                + "	FROM ChiTietSanPham\n"
                + "		 INNER JOIN Size ON ChiTietSanPham.ID_Size = Size.ID_Size\n"
                + "		 INNER JOIN DanhMucSP ON ChiTietSanPham.ID_DanhMucSanPham = DanhMucSP.ID_Danhmucsanpham\n"
                + "		 INNER JOIN MauSac ON ChiTietSanPham.ID_MauSac = MauSac.ID_MauSac\n"
                + "		 INNER JOIN ThuongHieu ON ChiTietSanPham.ID_ThuongHieu = ThuongHieu.ID_ThuongHieu\n"
                + "		 INNER JOIN ChatLieu ON ChiTietSanPham.ID_ChatLieu = ChatLieu.ID_ChatLieu\n"
                + "		 INNER JOIN TrongLuong ON ChiTietSanPham.ID_TrongLuong = TrongLuong.ID_TrongLuong\n"
                + "		 WHERE TenSanPham LIKE N'%"+name+"%'");
    }

    @Override
    public List<ChiTietSanPhamViewModels> locKichCo(String name) {
        return selectBySQLViewModels(" SELECT ChiTietSanPham.ID_ChiTietSanPham,\n"
                + "		 ChiTietSanPham.TenSanPham, \n"
                + "		 Size.TenSize, \n"
                + "		 DanhMucSP.TenDanhMuc, \n"
                + "		 ChiTietSanPham.Gia,\n"
                + "		 MauSac.TenMauSac,\n"
                + "		 ThuongHieu.TenThuongHieu,\n"
                + "              ChiTietSanPham.AnhSanPham,\n" 
                + "		 ChatLieu.TenChatLieu,\n"
                + "		 TrongLuong.TrongLuong,\n"
                + "		 ChiTietSanPham.SoLuong,\n"
                + "		 ChiTietSanPham.TrangThai,\n"
                + "		 ChiTietSanPham.AnhSanPham\n"
                + "	FROM ChiTietSanPham\n"
                + "		 INNER JOIN Size ON ChiTietSanPham.ID_Size = Size.ID_Size\n"
                + "		 INNER JOIN DanhMucSP ON ChiTietSanPham.ID_DanhMucSanPham = DanhMucSP.ID_Danhmucsanpham\n"
                + "		 INNER JOIN MauSac ON ChiTietSanPham.ID_MauSac = MauSac.ID_MauSac\n"
                + "		 INNER JOIN ThuongHieu ON ChiTietSanPham.ID_ThuongHieu = ThuongHieu.ID_ThuongHieu\n"
                + "		 INNER JOIN ChatLieu ON ChiTietSanPham.ID_ChatLieu = ChatLieu.ID_ChatLieu\n"
                + "		 INNER JOIN TrongLuong ON ChiTietSanPham.ID_TrongLuong = TrongLuong.ID_TrongLuong\n"
                + "		 WHERE TrongLuong LIKE N'%"+name+"%'");
    }

    @Override
    public List<ChiTietSanPhamViewModels> locMauSac(String name) {
        return selectBySQLViewModels(" SELECT ChiTietSanPham.ID_ChiTietSanPham,\n"
                + "		 ChiTietSanPham.TenSanPham, \n"
                + "		 Size.TenSize, \n"
                + "		 DanhMucSP.TenDanhMuc, \n"
                + "		 ChiTietSanPham.Gia,\n"
                + "		 MauSac.TenMauSac,\n"
                + "		 ThuongHieu.TenThuongHieu,\n"
                + "              ChiTietSanPham.AnhSanPham,\n" 
                + "		 ChatLieu.TenChatLieu,\n"
                + "		 TrongLuong.TrongLuong,\n"
                + "		 ChiTietSanPham.SoLuong,\n"
                + "		 ChiTietSanPham.TrangThai\n"
                + "		 ChiTietSanPham.AnhSanPham,\n"
                + "	FROM ChiTietSanPham\n"
                + "		 INNER JOIN Size ON ChiTietSanPham.ID_Size = Size.ID_Size\n"
                + "		 INNER JOIN DanhMucSP ON ChiTietSanPham.ID_DanhMucSanPham = DanhMucSP.ID_Danhmucsanpham\n"
                + "		 INNER JOIN MauSac ON ChiTietSanPham.ID_MauSac = MauSac.ID_MauSac\n"
                + "		 INNER JOIN ThuongHieu ON ChiTietSanPham.ID_ThuongHieu = ThuongHieu.ID_ThuongHieu\n"
                + "		 INNER JOIN ChatLieu ON ChiTietSanPham.ID_ChatLieu = ChatLieu.ID_ChatLieu\n"
                + "		 INNER JOIN TrongLuong ON ChiTietSanPham.ID_TrongLuong = TrongLuong.ID_TrongLuong\n"
                + "		 WHERE TenMauSac LIKE N'%"+name+"%'");
    }
}
