/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Models.ChucVu;
import Utilities.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author qivub
 */
public class ChucVuRepo implements IChucVuRepo{
    JdbcHelper helper;
    String INSERT = "INSERT INTO ChucVu(TenQuyen) VALUES (?) ";
    String UPDATE = "UPDATE ChucVu SET TenQuyen= ? WHERE ID_ChucVu = ? ";
    String DELETE = "DELETE ChucVu WHERE ID_ChucVu= ?";
    String SELECT_ALL= "SELECT * FROM ChucVu";
    public ChucVuRepo(){
        helper = new JdbcHelper();
    }
    
    
    @Override
    public List<ChucVu> selectSQL(String sql, Object... args) {
        List<ChucVu> list = new ArrayList<>();
        try {
            ResultSet rs = helper.executeQuery(sql, args);
            while (rs.next()) {
                ChucVu cv = new ChucVu();
                cv.setID(rs.getInt("ID_ChucVu"));
                cv.setTenChucVu(rs.getString("TenQuyen"));
                list.add(cv);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean insert(ChucVu cv) {
        helper.executeUpdate(INSERT, cv.getTenChucVu());
        return true;
    }

    @Override
    public boolean update(ChucVu cv) {
         helper.executeUpdate(UPDATE, cv.getTenChucVu(),cv.getID());
        return true;
    }

    @Override
    public boolean delete(ChucVu id) {
        helper.executeUpdate(DELETE, id.getID());
        return true;
    }

    @Override
    public List<ChucVu> selectAllBySQL() {
        return this.selectSQL(SELECT_ALL);
    }

    @Override
    public List<ChucVu> findByName(String name) {
        return this.selectSQL("SELECT * FROM ChucVu WHERE TenQuyen LIKE N'%"+name+"%'");
    }

    
    
}
