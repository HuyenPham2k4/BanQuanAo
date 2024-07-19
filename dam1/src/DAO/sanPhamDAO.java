/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Helper.JDBCHelper;
import entity.sanpham;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huyen
 */
public class sanPhamDAO implements ISPDRepo {
    JDBCHelper helper;

    public sanPhamDAO() {
        helper = new JDBCHelper();
    }

    @Override
    public boolean add(sanpham sp) {
        String sql = "INSERT INTO SANPHAM (TenSP, ID_DMSP, ID_TH, Mota, AnhSP, SoLuong, Gia, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        helper.executeUpdate(sql, sp.getTenSP(), sp.getID_DMSP(), sp.getID_TH(), sp.getMota(), sp.getAnhSP(), sp.getSoLuong(), sp.getGia(), sp.isTrangThai());
        return true;
    }

    @Override
    public boolean update(sanpham sp) {
        String sql = "UPDATE SANPHAM SET TenSP = ?, ID_DMSP = ?, ID_TH = ?, Mota = ?, AnhSP = ?, SoLuong = ?, Gia = ?, TrangThai = ? WHERE ID = ?";
        helper.executeUpdate(sql, sp.getTenSP(), sp.getID_DMSP(), sp.getID_TH(), sp.getMota(), sp.getAnhSP(), sp.getSoLuong(), sp.getGia(), sp.isTrangThai(), sp.getID());
        return true;
    }

    @Override
    public boolean delete(sanpham sp) {
        String sql = "DELETE FROM SANPHAM WHERE ID = ?";
        helper.executeUpdate(sql, sp.getID());
        return true;
    }

    @Override
    public List<sanpham> getAll() {
        return selectBySQL("SELECT * FROM SANPHAM");
    }

    @Override
    public List<sanpham> selectBySQL(String sql, Object... args) {
        List<sanpham> lstSP = new ArrayList<>();
        try {
            ResultSet rs = helper.executeQuery(sql, args);
            while (rs.next()) {
                sanpham sp = new sanpham(0, "", 0, 0, "", "", 0, 0.0, true);
                sp.setID(rs.getInt("ID"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setID_DMSP(rs.getInt("ID_DMSP"));
                sp.setID_TH(rs.getInt("ID_TH"));
                sp.setMota(rs.getString("Mota"));
                sp.setAnhSP(rs.getString("AnhSP"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setGia(rs.getDouble("Gia"));
                sp.setTrangThai(rs.getBoolean("TrangThai"));
                lstSP.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstSP;
    }

    @Override
    public sanpham findByID(int id) {
        List<sanpham> list = selectBySQL("SELECT * FROM SANPHAM WHERE ID = ?", id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<sanpham> findByName(String name) {
        return selectBySQL("SELECT * FROM SANPHAM WHERE TenSP LIKE ?", "%" + name + "%");
    }
}

