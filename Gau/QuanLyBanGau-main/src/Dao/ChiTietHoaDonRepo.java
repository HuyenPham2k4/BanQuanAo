package Dao;

import Models.ChiTietHoaDon;
import Utilities.DialogHelper;
import Utilities.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author qivub
 */
public class ChiTietHoaDonRepo implements IChiTietHoaDonRepo {

    private JdbcHelper _jdbcHelper;
    String INSERT_SQL = "INSERT INTO ChiTietHoaDon(ID_HoaDon,ID_ChiTietSanPham,SoLuong,Gia,TrangThai) VALUES (?,?,?,?,1)";
    String UPDATE_SQL = "UPDATE ChiTietHoaDon SET ID_HoaDon = ?,ID_ChiTietSanPham = ?,SoLuong = ?,Gia = ?, TrangThai = ? WHERE ID_CTHD = ? ";
    String TEMPORARY_DELETE_SQL = "UPDATE ChiTietHoaDon SET  TrangThai = 0 WHERE ID_CTHD = ? ";
    String MUA_SOLUONG_NHIEU = "UPDATE ChiTietHoaDon set SoLuong = ?,Gia=? where ID_HoaDon = ? and ID_ChiTietSanPham = ?";
    String SELECT_SOLUONG = "SELECT ID_HoaDon, ID_ChiTietSanPham,SoLuong, Gia FROM ChiTietHoaDon WHERE  ID_HoaDon = ? AND  ID_ChiTietSanPham= ?";
    String UPDATE_SOLUONG = "UPDATE ChiTietHoaDon set SoLuong = ?,Gia=? where ID_HoaDon = ? and ID_ChiTietSanPham = ?";
    String DELETE_SQL = "DELETE ChiTietHoaDon WHERE ID_CTHD = ?";
    String SELECT_ALL_SQL = "SELECT ChiTietHoaDon.ID_CTHD,\n"
            + "       ChiTietHoaDon.ID_HoaDon,\n"
            + "       ChiTietSanPham.ID_ChiTietSanPham,\n"
            + "       ChiTietSanPham.TenSanPham,\n"
            + "       ChiTietHoaDon.SoLuong,\n"
            + "       ChiTietHoaDon.Gia,\n"
            + "       ChiTietHoaDon.TrangThai\n"
            + "FROM ChiTietHoaDon\n"
            + "INNER JOIN ChiTietSanPham ON ChiTietHoaDon.ID_ChiTietSanPham = ChiTietSanPham.ID_ChiTietSanPham\n"
            + "WHERE ID_HoaDon = ?;";
    String SELECT_BY_ID_SQL = " SELECT "
            + "       ChiTietHoaDon.ID_CTHD,\n"
            + "       ChiTietHoaDon.ID_HoaDon,\n"
            + "       ChiTietSanPham.ID_ChiTietSanPham,\n"
            + "       ChiTietSanPham.TenSanPham,\n"
            + "       ChiTietHoaDon.SoLuong,\n"
            + "       ChiTietHoaDon.Gia,\n"
            + "       ChiTietHoaDon.TrangThai\n"
            + "FROM ChiTietHoaDon\n"
            + "INNER JOIN ChiTietSanPham ON ChiTietHoaDon.ID_ChiTietSanPham = ChiTietSanPham.ID_ChiTietSanPham\n"
            + "WHERE ID_HoaDon = ?;";

    @Override
    public boolean insert(ChiTietHoaDon _entity) {
        _jdbcHelper.executeUpdate(INSERT_SQL, _entity.getID_HoaDon(), _entity.getID_ChiTietSanPham(), _entity.getSoLuong(), _entity.getGia());
        return true;
    }

    @Override
    public boolean update(ChiTietHoaDon _entity) {
        _jdbcHelper.executeUpdate(UPDATE_SQL, _entity.getID_HoaDon(), _entity.getID_ChiTietSanPham(), _entity.getSoLuong(), _entity.getGia(), _entity.getTrangThai(), _entity.getID_CTHD());
        return true;
    }

    @Override
    public boolean delete(int _entity) {
        _jdbcHelper.executeUpdate(DELETE_SQL, _entity);
        return true;
    }

    @Override
    public boolean temporaryDelete(ChiTietHoaDon _entity) {
        _jdbcHelper.executeUpdate(TEMPORARY_DELETE_SQL, _entity.getTrangThai(), _entity.getID_CTHD());
        return true;
    }

    @Override
    public List<ChiTietHoaDon> selectByIDHoaDon(int ID) {
        return this.selectBySQL(SELECT_BY_ID_SQL, ID);

    }

    @Override
    public List<ChiTietHoaDon> selectBySQL(String sql, Object... args) {
        List<ChiTietHoaDon> list = new ArrayList<>();
        try (ResultSet rs = _jdbcHelper.executeQuery(sql, args)) {
            while (rs.next()) {
                ChiTietHoaDon CTHD = new ChiTietHoaDon();
                CTHD.setID_CTHD(rs.getInt("ID_CTHD"));
                CTHD.setID_HoaDon(rs.getInt("ID_HoaDon"));
                CTHD.setID_ChiTietSanPham(rs.getInt("ID_ChiTietSanPham"));
                CTHD.setTenSanPham(rs.getString("TenSanPham"));
                CTHD.setSoLuong(rs.getInt("SoLuong"));
                CTHD.setGia(rs.getInt("Gia"));
                CTHD.setTrangThai(rs.getInt("TrangThai"));
                list.add(CTHD);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error executing SQL query", e);
        }
    }

    @Override
    public List<ChiTietHoaDon> selectSoluong(String sql, Object... args) {
        List<ChiTietHoaDon> list = new ArrayList<>();
        try (ResultSet rs = _jdbcHelper.executeQuery(sql, args)) {
            while (rs.next()) {
                ChiTietHoaDon CTHD = new ChiTietHoaDon();
                CTHD.setID_HoaDon(rs.getInt("ID_HoaDon"));
                CTHD.setID_ChiTietSanPham(rs.getInt("ID_ChiTietSanPham"));
                CTHD.setSoLuong(rs.getInt("SoLuong"));
                list.add(CTHD);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error executing SQL query", e);
        }
    }

    @Override
    public boolean updateSoluong(ChiTietHoaDon _entity) {
        _jdbcHelper.executeUpdate(UPDATE_SOLUONG, _entity.getSoLuong(), _entity.getGia(), _entity.getID_HoaDon(), _entity.getID_ChiTietSanPham());
        return true;
    }

    @Override
    public List<ChiTietHoaDon> selectByIDCTSP(int id_hoadon, int id_ctsp) {
        return this.selectSoluong(SELECT_SOLUONG, id_hoadon, id_ctsp);
    }

    @Override
    public boolean updateNhieuSoLuong(ChiTietHoaDon _entity) {
        _jdbcHelper.executeUpdate(MUA_SOLUONG_NHIEU, _entity.getSoLuong(), _entity.getGia(), _entity.getID_HoaDon(), _entity.getID_ChiTietSanPham());
        return true;
    }

    private int getSoLuongConlai(int maSanPham) {

        ResultSet rs = _jdbcHelper.executeQuery("  SELECT ID_ChiTietSanPham, sum(SoLuong) AS SoLuong FROM ChiTietSanPham  WHERE ID_ChiTietSanPham = '" + maSanPham + "' GROUP BY ID_ChiTietSanPham");
        int result = 0;

        try {
            if (rs.next()) {
                result = rs.getInt("SoLuong");
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return result;
    }

    public int getSoLuongBanByIdSanPham(int idsp) {
        ResultSet rs = _jdbcHelper.executeQuery("  SELECT ID_ChiTietSanPham, sum(SoLuong) AS SoLuong FROM ChiTietHoaDon  WHERE ID_ChiTietSanPham = '" 
                + idsp + "' GROUP BY ID_ChiTietSanPham");
        int result = 0;
        try {
            if (rs.next()) {
                result = rs.getInt("SoLuong");
                System.out.println(result);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return result;
    }
    
//    @Override
//    public int getTonKho(int maSanPham) {
//        
//        ResultSet rs = _jdbcHelper.executeQuery("  SELECT ID_ChiTietSanPham, SoLuong FROM ChiTietSanPham  WHERE ID_ChiTietSanPham = '" + maSanPham + "'");
//        int result = 0;
//
//        try {
//            if (rs.next()) {
//                result = rs.getInt("SoLuong");
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.toString());
//        }
//        return result;
//    }
    
//    @Override
//    public boolean checkConHang(int maSanPham, int soLuong) {
//        if ((getTonKho(maSanPham) - soLuong) >= 0) {
//            return true;
//        }
//        return false;
//    }
}
