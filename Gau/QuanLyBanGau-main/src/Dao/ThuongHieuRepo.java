/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Models.ChatLieu;
import Models.ThuongHieu;
import Utilities.JdbcHelper;
//import static com.sun.tools.javac.tree.TreeInfo.args;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ThuongHieuRepo implements IThuongHieuRepo{
     private JdbcHelper jdbcHelper;
    String INSERT_SQL = "INSERT INTO ThuongHieu (TenThuongHieu,MoTaThuongHieu,TrangThai) VALUES (?,?,'1')";//lệnh sql thêm mới
    String UPDATE_SQL = "UPDATE ThuongHieu SET TenThuongHieu=?,MoTaThuongHieu=?, TrangThai = 0 WHERE ID_ThuongHieu = ? ";//vẫn là lệnh sql nhưng là thay đổi đb
    String TEMPORARY_DELETE_SQL = "UPDATE ThuongHieu SET TrangThai = 0 WHERE ID_ThuongHieu = ? ";//xoá tạm thời
    
    String SELECT_ALL_SQL = "SELECT * FROM ThuongHieu  ";//lấy tất cả trạng thái bằng 1
    String SELECT_BY_ID_SQL = "SELECT * FROM ThuongHieu WHERE TrangThai = ?";
    public ThuongHieuRepo() {
        jdbcHelper = new JdbcHelper();
    }

    @Override
    public boolean insert(ThuongHieu THieu) {
        jdbcHelper.executeUpdate(INSERT_SQL, THieu.getTenThuongHieu(),THieu.getMoTaThuongHieu());//hàm get theo vị trí dấu ? trên câu lệnh sql
        return true;
        
    }
    @Override
    public boolean update(ThuongHieu THieu) {
      jdbcHelper.executeUpdate(UPDATE_SQL,THieu.getTenThuongHieu(),THieu.getMoTaThuongHieu(),THieu.getID_ThuongHieu());//hàm get theo vị trí dấu ? trên câu lệnh sql
      return true;
    }

    @Override
    public boolean delete(ThuongHieu THieu) {
       jdbcHelper.executeUpdate(TEMPORARY_DELETE_SQL,THieu.getID_ThuongHieu());//hàm get theo vị trí dấu ? trên câu lệnh sql
       return true;
    }

    @Override
    public List<ThuongHieu> selectAll() {
        return selectBySql(SELECT_ALL_SQL);//hàm lấy all dữ liệu trong đb nhưng trạng thái bằng 1 
    }

    @Override
    public List<ThuongHieu> selectById(int ID) {
       return this.selectBySql("SELECT * FROM ThuongHieu WHERE ID_ChatLieu = "+ID);//tìm theo id
    }
    @Override
    public List<ThuongHieu> selectByTrangThai(int ID) {
        System.out.println("SELECT * FROM ThuongHieu WHERE TrangThai = "+ID);
        return this.selectBySql("SELECT * FROM ThuongHieu WHERE TrangThai = "+ID);
    }

    @Override
    public List<ThuongHieu> selectBySql(String sql, Object... args) {
        List<ThuongHieu> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                ThuongHieu THieu = new ThuongHieu();
                THieu.setID_ThuongHieu(rs.getInt("ID_ThuongHieu"));
                THieu.setTenThuongHieu(rs.getString("TenThuongHieu"));
                THieu.setMoTaThuongHieu(rs.getString("MoTaThuongHieu"));
                THieu.setTrangThai(rs.getInt("TrangThai"));
                list.add(THieu);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public List<ThuongHieu> timkiem(String name) {
        return this.selectBySql("SELECT * FROM ThuongHieu WHERE TenThuongHieu LIKE N'%"+name+"%'");//tìm theo id
    }
}
