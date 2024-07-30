/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Helper.JDBCHelper;
import entity.chitiethoadon;

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
    public chiTietHoaDonDAO(){
        helper = new JDBCHelper();
    }
    @Override
    public boolean add(chitiethoadon op) {// cái việc trong excute queryupdate nó có cái j thì phụ thuộc vào thứ tự cột của cau lệnh sql, gọi nó theo thứ tự
        String sql ="INSERT INTO CTHOADON (ID_HD, ID_CTSP, SoLuong, Gia, TrangThai) VALUES (?, ?, ?, ?, ?)";
        helper.executeUpdate(sql, op.getIdctsp());
        return true;
    }

    @Override
    public boolean update(chitiethoadon op) {
        String sql = "UPDATE CTHOADON SET ID_HD = ?, ID_CTSP = ?, SoLuong = ?, Gia = ?, TrangThai = ? WHERE ID = ?";
        helper.executeUpdate(sql, op.getIdhd(), op.getIdctsp(), op.getSoluong(), op.getGia(), op.isTrangthai(), op.getId());
        return true;

    }

    public boolean delete(int id) {
    String sql = "DELETE FROM CTHOADON WHERE ID = ?";
        helper.executeUpdate(sql, id);
        return true;
    }

    public List<chitiethoadon> getAll() {
        return selectBySQL("SELECT * FROM CTHOADON");
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
                cthd.setSoluong(rs.getInt("SoLuong"));
                cthd.setGia(rs.getInt("Gia"));
                cthd.setTrangthai(rs.getBoolean("TrangThai"));
                lstCTHD.add(cthd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstCTHD;
    }


    @Override
    public List<chitiethoadon> findByID(int id) { // tương tự
        return selectBySQL("select * from chitiethoadon where idchitiethoadon = ?", id);
    }

    @Override
    public List<chitiethoadon> findByName(String name) {// truyền bến name vào cây sql
        return selectBySQL("select * from chitiethoadon where ten = ?", name);
    }

    @Override
    public List<chitiethoadon> getAll(chitiethoadon op) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(chitiethoadon op) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
