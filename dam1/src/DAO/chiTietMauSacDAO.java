/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Helper.JDBCHelper;
import entity.chitietmausac;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huyen
 */
public class chiTietMauSacDAO implements ICTMSRepo {
    JDBCHelper helper;

    public chiTietMauSacDAO() {
        helper = new JDBCHelper();
    }
    public List<chitietmausac> findByID_SP(int idsp){
        return selectBySQL("SELECT * FROM CTMAUSAC WHERE ID_SP = ?", idsp);
    }
    @Override
    public boolean add(chitietmausac ctms) {
        String sql = "INSERT INTO CTMAUSAC (ID_MS, ID_SP, TrangThai) VALUES (?, ?, ?)";
        helper.executeUpdate(sql, ctms.getIdms(), ctms.getIdsp(), ctms.isTrangthai());
        return true;
    }

    @Override
    public boolean update(chitietmausac ctms) {
        String sql = "UPDATE CTMAUSAC SET ID_MS = ?, ID_SP = ?, TrangThai = ? WHERE ID = ?";
        helper.executeUpdate(sql, ctms.getIdms(), ctms.getIdsp(), ctms.isTrangthai(), ctms.getId());
        return true;
    }

    @Override
    public boolean delete(int ID) {
        String sql = "DELETE FROM CTMAUSAC WHERE ID = ?";
        helper.executeUpdate(sql, ID);
        return true;
    }
    public boolean deleteByIDSP(int IDSP) {
        String sql = "DELETE FROM CTMAUSAC WHERE ID_SP = ?";
        helper.executeUpdate(sql, IDSP);
        return true;
    }
    @Override
    public List<chitietmausac> getAll() {
        return selectBySQL("SELECT * FROM CTMAUSAC");
    }

    @Override
    public List<chitietmausac> selectBySQL(String sql, Object... args) {
        List<chitietmausac> lstCTMS = new ArrayList<>();
        try {
            ResultSet rs = helper.executeQuery(sql, args);
            while (rs.next()) {
                chitietmausac ctms = new chitietmausac(0, 0, 0, true);
                ctms.setId(rs.getInt("ID"));
                ctms.setIdms(rs.getInt("ID_MS"));
                ctms.setIdsp(rs.getInt("ID_SP"));
                ctms.setTrangthai(rs.getBoolean("TrangThai"));
                lstCTMS.add(ctms);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstCTMS;
    }

    @Override
    public List<chitietmausac> findByID(int id) {
        return selectBySQL("SELECT * FROM CTMAUSAC WHERE ID = ?", id);
    }

    @Override
    public List<chitietmausac> findByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
