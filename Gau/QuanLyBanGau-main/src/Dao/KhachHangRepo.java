/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Models.KhachHang;
import Utilities.JdbcHelper;
import Utilities.displayvalueModel;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;



public class KhachHangRepo implements IKhachHangRepo{
    private JdbcHelper _jdbcHelper;
    String INSERT_SQL = "INSERT INTO KhachHang (TenKhachHang,DiaChi,SoDienThoai ,GhiChu ,TrangThai ) VALUES (?,?,?,?,'1')";
    String INSERTS_SQL = "INSERT INTO KhachHang (TenKhachHang,SoDienThoai,TrangThai ) VALUES (?,?,'1')";
    String UPDATE_SQL = "UPDATE KhachHang SET  TrangThai = 1 WHERE ID_KhachHang = ? ";
    String TEMPORARY_DELETE_SQL = "UPDATE KhachHang SET TrangThai = 0 WHERE ID_KhachHang = ? ";
    String DELETE_SQL = "DELETE KhachHang WHERE ID_KhachHang = ?";
    String SELECT_ALL_SQL = "SELECT * FROM KhachHang WHERE TrangThai = 1";
    String SELECT_BY_ID_SQL = "SELECT * FROM KhachHang WHERE ID_KhachHang = ?";
    String SELECT_BY_NAME_SQL = "SELECT * FROM KhachHang WHERE TenKhachHang = ?";
    public KhachHangRepo() {
        _jdbcHelper = new JdbcHelper();
    }
    

    @Override
    public List<KhachHang> selectBySQL(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = _jdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setID_KhachHang(rs.getInt("ID_KhachHang"));
                kh.setTenKhachHang(rs.getString("TenKhachHang"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setSoDienThoai(rs.getInt("SoDienThoai"));
                kh.setGhiChu(rs.getString("GhiChu"));
                kh.setTrangThai(rs.getInt("TrangThai"));
                list.add(kh);
            }
//            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }
    @Override
    public boolean insert(KhachHang entity) {
        _jdbcHelper.executeUpdate(INSERT_SQL, entity.getTenKhachHang(),entity.getDiaChi(),entity.getSoDienThoai(),entity.getGhiChu());
        return true;
    }

    @Override
    public boolean update(KhachHang entity) {
        _jdbcHelper.executeUpdate(UPDATE_SQL, entity.getTenKhachHang(),entity.getDiaChi(),entity.getSoDienThoai(),entity.getGhiChu(), entity.getID_KhachHang());
        return true;
    }

    @Override
    public boolean delete(KhachHang entity) {
        _jdbcHelper.executeUpdate(DELETE_SQL, entity.getID_KhachHang());
        return true;
    }

    @Override
    public boolean temporaryDelete(KhachHang entity) {
      _jdbcHelper.executeUpdate(TEMPORARY_DELETE_SQL, entity.getID_KhachHang());
      return true;  
    }

    @Override
    public List<KhachHang> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
        
    }

    @Override
    public List<KhachHang> selectByID(int ID) {
        KhachHang entity = new KhachHang();
        ID = entity.getID_KhachHang();
        return this.selectBySQL(SELECT_BY_ID_SQL, ID);
    }

    @Override
    public boolean inserts(KhachHang _entity) {
        _jdbcHelper.executeUpdate(INSERTS_SQL, _entity.getTenKhachHang(),_entity.getSoDienThoai());
      return true;  
    }

    @Override
    public List<KhachHang> findName(String ID) {
       return this.selectBySQL("SELECT * FROM KhachHang WHERE TenKhachHang LIKE N'%" + ID + "%'");
    }

   
    
}
