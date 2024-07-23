/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Helper.JDBCHelper;
import entity.thuonghieu;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huyen
 */
public class thuongHieuDAO implements ITHRepo {
    JDBCHelper helper;

    public thuongHieuDAO() {
        helper = new JDBCHelper();
    }

    @Override
    public boolean add(thuonghieu th) {
        String sql = "INSERT INTO THUONGHIEU (ten, MoTa, TrangThai) VALUES (?, ?, ?)";
        helper.executeUpdate(sql, th.getTen(), th.getMota(), th.isTrangthai());
        return true;
    }

    @Override
    public boolean update(thuonghieu th) {
        String sql = "UPDATE THUONGHIEU SET ten = ?, MoTa = ?, TrangThai = ? WHERE ID = ?";
        helper.executeUpdate(sql, th.getTen(), th.getMota(), th.isTrangthai(), th.getId());
        return true;
    }

    @Override
    public boolean delete(thuonghieu th) {
        String sql = "DELETE FROM THUONGHIEU WHERE ID = ?";
        helper.executeUpdate(sql, th.getId());
        return true;
    }

    @Override
    public List<thuonghieu> getAll() {
        return selectBySQL("SELECT * FROM THUONGHIEU");
    }

    @Override
    public List<thuonghieu> selectBySQL(String sql, Object... args) {
        List<thuonghieu> lstTH = new ArrayList<>();
        try {
            ResultSet rs = helper.executeQuery(sql, args);
            while (rs.next()) {
                thuonghieu th = new thuonghieu(rs.getInt("ID"), rs.getString("Ten"), rs.getString("MoTa"), rs.getBoolean("TrangThai"));
                lstTH.add(th);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstTH;
    }

    @Override
    public thuonghieu findByID(int id) {
        List<thuonghieu> list = selectBySQL("SELECT * FROM THUONGHIEU WHERE ID = ?", id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<thuonghieu> findByName(String name) {
        return selectBySQL("SELECT * FROM THUONGHIEU WHERE ten LIKE ?", "%" + name + "%");
    }
}
