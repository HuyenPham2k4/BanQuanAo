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
        String sql =" cái j ở đây thì viết vào";
        helper.executeUpdate(sql, op.getIdctsp());
        return true;
    }

    @Override
    public boolean update(chitiethoadon op) {
        return false;
    }

    @Override
    public boolean delete(chitiethoadon op) {
        return false;
    }

    @Override
    public List<chitiethoadon> getAll(chitiethoadon op) {
        return List.of();
    }

    @Override
    public List<chitiethoadon> selectBySQL(String sql, Object... args) {
        List<chitiethoadon> lstCTHD = new ArrayList<>();
        try {
            ResultSet rs = helper.executeQuery(sql, args);
            while (rs.next()) {// ở đây thì cái getint thì nên để tên cột thay vì là để số cột như kia
                chitiethoadon cthd = new chitiethoadon();
                cthd.setIdctsp(rs.getInt("idchitiethoadon"));
                cthd.setGia(rs.getInt("gia"));
                lstCTHD.add(cthd);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public List<chitiethoadon> findByID(int id) { // tương tự
        return selectBySQL("select * from chitiethoadon where idchitiethoadon = ?", id);
    }

    @Override
    public List<chitiethoadon> findByName(String name) {// truyền bến name vào cây sql
        return selectBySQL("select * from chitiethoadon where ten = ?", name);
    }
}
