/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Models.ChatLieu;
import Models.TrongLuong;
import Utilities.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author qivub
 */
public class TrongLuongRepo implements ITrongLuongRepo{
    private JdbcHelper _jdbcHelper;
    String INSERT_SQL = "INSERT INTO TrongLuong (TrongLuong,TrangThai) VALUES (?,'1')";
    String UPDATE_SQL = "UPDATE TrongLuong SET TrongLuong = ?, TrangThai = 1 WHERE ID_TrongLuong = ? ";
    String TEMPORARY_DELETE_SQL = "UPDATE TrongLuong SET TrangThai = 0 WHERE ID_TrongLuong = ? ";
    String DELETE_SQL = "";
    String SELECT_ALL_SQL = "SELECT * FROM TrongLuong ";
    String SELECT_BY_ID_SQL = "SELECT * FROM TrongLuong WHERE TrangThai = ?";
    public TrongLuongRepo() {
        _jdbcHelper = new JdbcHelper();
    }
    

    @Override
    public boolean insert(TrongLuong _entity) {
        _jdbcHelper.executeUpdate(INSERT_SQL, _entity.getTrongLuong());
        return true;
    }

    @Override
    public boolean update(TrongLuong _entity) {
        _jdbcHelper.executeUpdate(UPDATE_SQL, _entity.getTrongLuong(),_entity.getID_TrongLuong());
        return true;
    }

    @Override
    public boolean delete(TrongLuong _entity) {
        _jdbcHelper.executeUpdate("DELETE TrongLuong WHERE ID_TrongLuong = "+ _entity.getID_TrongLuong());
        return true;
    }

    @Override
    public boolean temporaryDelete(TrongLuong _entity) {
        _jdbcHelper.executeUpdate(TEMPORARY_DELETE_SQL, _entity.getID_TrongLuong());
        return true;
    }

    @Override
    public List<TrongLuong> selectByID(int ID, TrongLuong _entity) {
//       ID = _entity.getID_TrongLuong();
       return this.selectBySQL(SELECT_BY_ID_SQL);
    }

    @Override
    public List<TrongLuong> selectAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }
    
    @Override
    public List<TrongLuong> selectByTrangThai(int ID) {
        System.out.println("SELECT * FROM ThuongHieu WHERE TrangThai = "+ID);
        return this.selectBySQL("SELECT * FROM TrongLuong WHERE TrangThai = "+ID);
    }

    @Override
    public List<TrongLuong> selectBySQL(String sql, Object... args) {
        List<TrongLuong> list = new ArrayList<>();
        try {
            ResultSet rs = _jdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                TrongLuong trongluong = new TrongLuong();
                trongluong.setID_TrongLuong(rs.getInt("ID_TrongLuong"));
                trongluong.setTrongLuong(rs.getString("TrongLuong"));
                trongluong.setTrangThai(rs.getInt("TrangThai"));
                list.add(trongluong);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public List<TrongLuong> timkiem(String name) {
        return this.selectBySQL("SELECT * FROM TrongLuong WHERE TrongLuong LIKE N'%"+name+"%'");
    }

    
}
