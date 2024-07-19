/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Helper.JDBCHelper;
import entity.size;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huyen
 */
public class sizeDAO implements ISRepo {
    JDBCHelper helper;

    public sizeDAO() {
        helper = new JDBCHelper();
    }

    @Override
    public boolean add(size s) {
        String sql = "INSERT INTO KICHTHUOC (id, MoTa) VALUES (?, ?)";
        helper.executeUpdate(sql, s.getId(), s.getMota());
        return true;
    }

    @Override
    public boolean update(size s) {
        String sql = "UPDATE KICHTHUOC SET ten = ?, MoTa = ? WHERE ID = ?";
        helper.executeUpdate(sql, s.getId(), s.getMota(), s.getId());
        return true;
    }

    @Override
    public boolean delete(size s) {
        String sql = "DELETE FROM KICHTHUOC WHERE ID = ?";
        helper.executeUpdate(sql, s.getId());
        return true;
    }

    @Override
    public List<size> getAll() {
        return selectBySQL("SELECT * FROM size");
    }

    @Override
    public List<size> selectBySQL(String sql, Object... args) {
        List<size> lstKT = new ArrayList<>();
        try {
            ResultSet rs = helper.executeQuery(sql, args);
            while (rs.next()) {
                size s = new size(rs.getInt("ID"), rs.getString("MaSo"), rs.getString("MoTa"));
                lstKT.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstKT;
    }

    @Override
    public size findByID(int id) {
        List<size> list = selectBySQL("SELECT * FROM size WHERE ID = ?", id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<size> findByName(String name) {
        return selectBySQL("SELECT * FROM size WHERE MaSo LIKE ?", "%" + name + "%");
    }
}
