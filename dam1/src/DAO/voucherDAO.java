/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Helper.JDBCHelper;
import entity.voucher;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author huyen
 */
public class voucherDAO implements IVRepo {
    JDBCHelper helper;

    public voucherDAO() {
        helper = new JDBCHelper();
    }

    @Override
    public boolean add(voucher v) {
        String sql = "INSERT INTO VOUCHER (MaVocher,NgayBatDau, NgayKetThuc, TrangThai) VALUES (?, ?, ?, ?)";
        helper.executeUpdate(sql, v.getMavoucher(), v.getNgaybatdau(), v.getNgayketthuc(), v.isTrangthai());
        return true;
    }

    @Override
    public boolean update(voucher v) {
        String sql = "UPDATE VOUCHER SET NgayBatDau = ?, NgayKetThuc = ?, TrangThai = ? WHERE MaVocher = ?";
        helper.executeUpdate(sql, v.getNgaybatdau(), v.getNgayketthuc(), v.isTrangthai(), v.getMavoucher());
        return true;
    }

    @Override
    public boolean delete(voucher v) {
        String sql = "DELETE FROM VOUCHER WHERE MaVocher = ?";
        helper.executeUpdate(sql, v.getMavoucher());
        return true;
    }

    @Override
    public List<voucher> getAll() {
        return selectBySQL("SELECT * FROM VOUCHER");
    }

    @Override
    public List<voucher> selectBySQL(String sql, Object... args) {
        List<voucher> lstVoucher = new ArrayList<>();
        try {
            ResultSet rs = helper.executeQuery(sql, args);
            while (rs.next()) {
                voucher v = new voucher();
                v.setMavoucher(rs.getString("MaVocher"));
                v.setNgaybatdau(rs.getInt("NgayBatDau"));
                v.setNgayketthuc(rs.getInt("NgayKetThuc"));
                v.setTrangthai(rs.getBoolean("TrangThai"));
                lstVoucher.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstVoucher;
    }

    @Override
    public voucher findByID(String mavoucher) {
        List<voucher> list = selectBySQL("SELECT * FROM VOUCHER WHERE MaVocher = ?", mavoucher);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<voucher> findByDateRange(int startDate, int endDate) {
        return selectBySQL("SELECT * FROM VOUCHER WHERE NgayBatDau >= ? AND NgayKetThuc <= ?", startDate, endDate);
    }
}


  
