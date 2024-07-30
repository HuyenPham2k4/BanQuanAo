/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Helper.JDBCHelper;
import entity.chitietzise;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author huyen
 */

public class chiTietSizeDAO implements ICTSRepo {
    JDBCHelper helper;

    public chiTietSizeDAO() {
        helper = new JDBCHelper();
    }

    @Override
    public boolean add(chitietzise ctsize) {
        String sql = "INSERT INTO CTSIZE (ID_SP, ID_SIZE, TrangThai) VALUES (?, ?, ?)";
        helper.executeUpdate(sql, ctsize.getIdsp(), ctsize.getIdsize(), ctsize.isTrangthai());
        return true;
    }

    @Override
    public boolean update(chitietzise ctsize) {
        String sql = "UPDATE CTSIZE SET ID_SP = ?, ID_SIZE = ?, TrangThai = ? WHERE ID = ?";
        helper.executeUpdate(sql, ctsize.getIdsp(), ctsize.getIdsize(), ctsize.isTrangthai(), ctsize.getId());
        return true;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM CTSIZE WHERE ID = ?";
        helper.executeUpdate(sql, id);
        return true;
    }

    @Override
    public List<chitietzise> getAll() {
        return selectBySQL("SELECT * FROM CTSIZE");
    }

    @Override
    public List<chitietzise> selectBySQL(String sql, Object... args) {
        List<chitietzise> lstCTSize = new ArrayList<>();
        try {
            ResultSet rs = helper.executeQuery(sql, args);
            while (rs.next()) {
                chitietzise ctsize = new chitietzise(0, 0, 0, true);
                ctsize.setId(rs.getInt("ID"));
                ctsize.setIdsp(rs.getInt("ID_SP"));
                ctsize.setIdsize(rs.getInt("ID_SIZE"));
                ctsize.setTrangthai(rs.getBoolean("TrangThai"));
                lstCTSize.add(ctsize);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstCTSize;
    }

    @Override
    public List<chitietzise> findByID(int id) {
        return selectBySQL("SELECT * FROM CTSIZE WHERE ID = ?", id);
    }

    @Override
    public List<chitietzise> findByName(String name) {
                return new ArrayList<>();
    }
}