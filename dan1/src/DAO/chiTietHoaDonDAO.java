/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Helper.JDBCHelper;
import entity.chitiethoadon;
import entity.chitietsanpham;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huyen
 */
// Giờ t sẽ chỉ cách để làm Dao dễ hơn so với việc viết Dao của thầy nhé
// t đang dùng intellji
// đầu tiên tạo cho t 1 cái interface
// h thì implement interface vào đây
public class chiTietHoaDonDAO implements ICTHDRepo {

    // sau khi đã implement interface thì triển khai code
    // đầu tiên t sẽ khai báo jdbchelper và tạo cho nó cái biến
    JDBCHelper helper;

    public chiTietHoaDonDAO() {
        helper = new JDBCHelper();
    }

    @Override
    public boolean add(chitiethoadon op) {// cái việc trong excute queryupdate nó có cái j thì phụ thuộc vào thứ tự cột của cau lệnh sql, gọi nó theo thứ tự
        String sql = "INSERT INTO CTHOADON (ID_HD, ID_SP,TenSP, SoLuong, Don_gia, Gia, TrangThai) VALUES (?, ?, ?, ?, ?, ?, 1)";
        helper.executeUpdate(sql, op.getIdhd(), op.getIdctsp(), op.getTenSP(), op.getSoluong(), op.getDon_gia(), op.getGia());
        return true;
    }

    @Override
    public boolean update(chitiethoadon op) {
        String sql = "UPDATE CTHOADON SET ID_HD = ?, ID_CTSP = ?, SoLuong = ?, Gia = ?, TrangThai = ? WHERE ID = ?";
        helper.executeUpdate(sql, op.getIdhd(), op.getIdctsp(), op.getSoluong(), op.getGia(), op.isTrangthai(), op.getId());
        return true;

    }

    @Override
    public boolean delete(chitiethoadon op) {
        String sql = "DELETE FROM CTHOADON WHERE ID = ?";
        helper.executeUpdate(sql, op.getId());
        return true;
    }

    public List<chitiethoadon> getAllCTHD() {
        return selectBySQL("SELECT * FROM CTHOADON WHERE ID_HD = ?");
    }

    @Override
    public List<chitiethoadon> selectBySQL(String sql, Object... args) {
        List<chitiethoadon> lstCTHD = new ArrayList<>();
        try {
            ResultSet rs = helper.executeQuery(sql, args);
            while (rs.next()) {
                chitiethoadon cthd = new chitiethoadon();
                cthd.setId(rs.getInt("ID"));
                cthd.setIdhd(rs.getInt("ID_HD"));
                cthd.setIdctsp(rs.getInt("ID_SP"));
                cthd.setTenSP(rs.getString("TenSP"));
                cthd.setSoluong(rs.getInt("SoLuong"));
                cthd.setDon_gia(rs.getInt("Don_gia"));
                cthd.setGia(rs.getInt("Gia"));
                cthd.setTrangthai(rs.getBoolean("TrangThai"));
                lstCTHD.add(cthd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstCTHD;
    }

    public boolean updateSL(chitiethoadon cthd) {
        helper.executeUpdate("UPDATE CTHOADON set SoLuong = ?,Gia=? where ID_HD = ? and ID_SP = ?", cthd.getSoluong(),
                cthd.getGia(), cthd.getIdhd(), cthd.getIdctsp());
        return true;
    }
    public boolean changeSL(int soLuong, double gia, int id) {
        helper.executeUpdate("UPDATE CTHOADON set SoLuong = ?,Gia=? where ID= ?", soLuong,
                gia , id);
        return true;
    }

    public List<chitiethoadon> selectSLByIDCTSP(int id_hd, int id_sp) {
        return selectBySQL("select * from CTHOADON where ID_HD =" + id_hd + " and ID_SP =" + id_sp);
    }

    @Override
    public List<chitiethoadon> findByIDHD(int id) { // tương tự
        return selectBySQL("select * from CTHOADON where ID_HD = ?", id);
    }

    @Override
    public List<chitiethoadon> findByName(String name) {// truyền bến name vào cây sql
        return selectBySQL("select * from chitiethoadon where ten = ?", name);
    }

    @Override
    public List<chitiethoadon> getAll(int id) {
        return selectBySQL("SELECT * FROM CTHOADON WHERE ID_HD = " + id);
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM CTHOADON WHERE ID = ?";
        helper.executeUpdate(sql, id);
        return true;
    }

}
