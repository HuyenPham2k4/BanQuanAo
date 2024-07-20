/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Helper.JDBCHelper;
import entity.danhmuc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huyen
 */
public class danhMucDAO implements IDMRepo {
    JDBCHelper helper;

    public danhMucDAO() {
        helper = new JDBCHelper();
    }

    @Override
    public boolean add(danhmuc dm) {
        String sql = "INSERT INTO DANH_MUC (Ten, TrangThai) VALUES (?, ?)";
        helper.executeUpdate(sql, dm.getTen(), dm.isTrangthai());
        return true;
    }

    @Override
    public boolean update(danhmuc dm) {
        String sql = "UPDATE DANH_MUC SET Ten = ?, TrangThai = ? WHERE ID = ?";
        helper.executeUpdate(sql, dm.getTen(), dm.isTrangthai(), dm.getId());
        return true;
    }

    @Override
    public boolean delete(danhmuc dm) {
        String sql = "DELETE FROM DANH_MUC WHERE ID = ?";
        helper.executeUpdate(sql, dm.getId());
        return true;
    }

    @Override
    public List<danhmuc> getAll() {
        return selectBySQL("SELECT * FROM DANH_MUC");
    }

    @Override
    public List<danhmuc> selectBySQL(String sql, Object... args) {
        List<danhmuc> lstDM = new ArrayList<>();
        try {
            ResultSet rs = helper.executeQuery(sql, args);
            while (rs.next()) {
                danhmuc dm = new danhmuc(0, sql, true);
                dm.setId(rs.getInt("ID"));
                dm.setTen(rs.getString("Ten"));
                dm.setTrangthai(rs.getBoolean("TrangThai"));
                lstDM.add(dm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstDM;
    }

    @Override
    public List<danhmuc> findByID(int id) {
        return selectBySQL("SELECT * FROM DANH_MUC WHERE ID = ?", id);
    }

    @Override
    public List<danhmuc> findByName(String name) {
        return selectBySQL("SELECT * FROM DANH_MUC WHERE Ten = ?", name);
    }
}