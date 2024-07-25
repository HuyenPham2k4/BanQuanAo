/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Models.MauSac;
import Utilities.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author qivub
 */
public class MauSacRepo implements IMauSacRepo{ // cái này là cpu
    private JdbcHelper _jdbcHelper;
    String INSERT_SQL = "INSERT INTO MauSac (TenMauSac,TrangThai) VALUES (?,'1')";//câu lệnh thêm dữ liệu đb
    String UPDATE_SQL = "UPDATE MauSac SET TenMauSac = ?,TrangThai = 1 WHERE ID_MauSac = ? ";
    String TEMPORARY_DELETE_SQL = "UPDATE MauSac SET TenMauSac = ?, TrangThai = 0 WHERE  ID_MauSac = ? ";
    String DELETE_SQL = "DELETE MauSac WHERE ID_MauSac = ?";
    String SELECT_ALL_SQL = "SELECT * FROM MauSac ";
    String SELECT_BY_ID_SQL = "SELECT * FROM MauSac WHERE TrangThai = ?";
    public MauSacRepo() {
        _jdbcHelper = new JdbcHelper();
    }
    
    @Override
    public boolean insert(MauSac entity) {
        _jdbcHelper.executeUpdate(INSERT_SQL, entity.getTenMauSac());
        return true;
    }

    @Override
    public boolean update(MauSac entity) {
        _jdbcHelper.executeUpdate(UPDATE_SQL, entity.getTenMauSac(),entity.getID_MauSac());
        return true;
    }

    @Override
    public boolean delete(MauSac entity) {
         _jdbcHelper.executeUpdate(DELETE_SQL,entity.getID_MauSac());
        return true;}

    @Override
    public boolean temporaryDelete(MauSac entity) {
        _jdbcHelper.executeUpdate(TEMPORARY_DELETE_SQL,entity.getID_MauSac());
        return true;
    }

    @Override
    public List<MauSac> selectAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public List<MauSac> selectByID(int ID, MauSac entity) {
        return this.selectBySQL(SELECT_BY_ID_SQL, ID);
    }
    @Override
    public List<MauSac> selectByTrangThai(int ID) {
        System.out.println("SELECT * FROM MauSac WHERE TrangThai = ");
        return this.selectBySQL("SELECT * FROM MauSac WHERE TrangThai = "+ID);
    }

    @Override
    public List<MauSac> selectBySQL(String sql, Object... args) {
         List<MauSac> list = new ArrayList<>();
        try {
            ResultSet rs = _jdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                MauSac mau = new MauSac();
                mau.setID_MauSac(rs.getInt("ID_MauSac"));
                mau.setTenMauSac(rs.getString("TenMauSac"));
                mau.setTrangThai(rs.getInt("TrangThai"));
                list.add(mau);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public List<MauSac> timkiem(String name) {
        return this.selectBySQL("SELECT * FROM MauSac WHERE TenMauSac  LIKE N'%"+name+"%'");
    }
    
}
