/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Helper.JDBCHelper;
import entity.mausac;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huyen
 */
public class mauSacDAO implements IMSRepo {
    JDBCHelper helper;
    public mauSacDAO() {
        helper = new JDBCHelper();
    }

    @Override
    public boolean add(mausac ms) {
        String sql = "INSERT INTO MAUSAC (MauSac) VALUES (?)";
        helper.executeUpdate(sql, ms.getMacsac());
        return true;
    }

    @Override
    public boolean update(mausac ms) {
        String sql = "UPDATE MAUSAC SET MauSac = ? WHERE Id = ?";
        helper.executeUpdate(sql, ms.getMacsac(), ms.getId());
        return true;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM MAUSAC WHERE Id = ?";
        helper.executeUpdate(sql, id);
        return true;
    }

    @Override
    public List<mausac> getAll() {
        return selectBySQL("SELECT * FROM MAUSAC");
    }

    @Override
    public List<mausac> selectBySQL(String sql, Object... args) {
        List<mausac> lstMS = new ArrayList<>();
        try {
            ResultSet rs = helper.executeQuery(sql, args);
            while (rs.next()) {
                mausac ms = new mausac();
                ms.setId(rs.getInt("Id"));
                ms.setMacsac(rs.getString("MauSac"));
                lstMS.add(ms);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstMS;
    }

    @Override
    public List<mausac> findByID(int id) {
        return selectBySQL("SELECT * FROM MAUSAC WHERE Id = ?", id);
    }

    @Override
    public List<mausac> findByName(String name) {
        return selectBySQL("SELECT * FROM MAUSAC WHERE MauSac = ?", name);
    }
}