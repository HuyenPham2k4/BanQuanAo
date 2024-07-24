/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Models.DanhMucSP;
import Utilities.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class DanhMucSPRepo implements IDanhMucSPRepo{
    private JdbcHelper _jdbcHelper;
    String INSERT_SQL = "INSERT INTO DanhMucSP (TenDanhMuc,TrangThai) VALUES (?,?)";
    String UPDATE_SQL = "UPDATE DanhMucSP SET TenDanhMuc = ?,TrangThai = ? WHERE ID_Danhmucsanpham = ? ";
    String TEMPORARY_DELETE_SQL = "UPDATE DanhMucSP SET  TrangThai = 0 WHERE  ID_Danhmucsanpham = ? ";
    String DELETE_SQL = "DELETE DanhMucSP WHERE ID_Danhmucsanpham = ?";
    String SELECT_ALL_SQL = "SELECT * FROM DanhMucSP ";
    String SELECT_BY_ID_SQL = "SELECT * FROM DanhMucSP WHERE TrangThai = ?";
    public DanhMucSPRepo() {
        _jdbcHelper = new JdbcHelper();
    }
    @Override
    public List<DanhMucSP> selectBySQL(String sql, Object... args) {
        List<DanhMucSP> list = new ArrayList<>();
        try {
            ResultSet rs = _jdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                DanhMucSP sp = new DanhMucSP();
                sp.setID_Danhmucsanpham(rs.getInt("ID_Danhmucsanpham"));
                sp.setTenDanhMuc(rs.getString("TenDanhMuc"));
                sp.setTrangThai(rs.getInt("TrangThai"));
                list.add(sp);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }
    @Override
    public boolean insert(DanhMucSP entity) {
        _jdbcHelper.executeUpdate(INSERT_SQL,  entity.getTenDanhMuc(),entity.getTrangThai());
        return true;
    }

    @Override
    public boolean update(DanhMucSP entity) {
        _jdbcHelper.executeUpdate(UPDATE_SQL , entity.getTenDanhMuc(),entity.getTrangThai(),entity.getID_Danhmucsanpham());
        return true;
    }

    @Override
    public boolean delete(DanhMucSP entity) {
         _jdbcHelper.executeUpdate(DELETE_SQL ,entity.getID_Danhmucsanpham());
        return true;
    }

    @Override
    public boolean temporaryDelete(DanhMucSP entity) {
        _jdbcHelper.executeUpdate(TEMPORARY_DELETE_SQL,entity.getID_Danhmucsanpham());
        return true;
    }

    @Override
    public List<DanhMucSP> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public List<DanhMucSP> selectByID(String name) {
        return this.selectBySQL("SELECT * FROM DanhMucSP WHERE TenDanhMuc LIKE N'%"+name+"%'");
    }
    
    @Override
    public List<DanhMucSP> selectByTrangThai(int ID) {
        System.out.println("SELECT * FROM DanhMucSP WHERE TrangThai = "+ID);
        return this.selectBySQL("SELECT * FROM DanhMucSP WHERE TrangThai = "+ID);
        
    }
    
}
