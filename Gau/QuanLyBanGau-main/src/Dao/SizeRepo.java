/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Models.Size;
import Utilities.JdbcHelper;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author khanh
 */
public class SizeRepo implements ISizeRepo{
    
     
    private JdbcHelper _jdbcHelper;
    String INSERT_SQL = "INSERT INTO Size (TenSize,MoTaSize,TrangThai) VALUES (?,?,'1')";
    String UPDATE_SQL = "UPDATE Size SET TenSize = ?, MoTaSize = ?,TrangThai = 1 WHERE ID_Size = ? ";
    String TEMPORARY_DELETE_SQL = "UPDATE Size SET TrangThai = 0 WHERE ID_Size = ? ";
    String DELETE_SQL = "DELETE Size WHERE ID_Size = ?";
    String SELECT_ALL_SQL = "SELECT * FROM Size";
    String SELECT_BY_ID_SQL = "SELECT * FROM Size WHERE TrangThai = ?";
    public SizeRepo(){
        JdbcHelper _jdbcHelper =  new JdbcHelper();
    }
    @Override
    public boolean insert(Size _entity) {
         _jdbcHelper.executeUpdate(INSERT_SQL, _entity.getTenSize(),_entity.getMoTaSize());
        return true;
    }

    @Override
    public boolean update(Size _entity) {
        _jdbcHelper.executeUpdate(UPDATE_SQL, _entity.getTenSize(),_entity.getMoTaSize(), _entity.getID_Size());
        return true;
    }

    @Override
    public boolean delete(Size _entity) {
          _jdbcHelper.executeUpdate(DELETE_SQL, _entity.getID_Size());
        return true;
    }

    @Override
    public boolean temporaryDelete(Size _entity) {
         _jdbcHelper.executeUpdate(TEMPORARY_DELETE_SQL, _entity.getID_Size());
        return true;
    }
    
    @Override
    public List<Size> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public List<Size> selectByTrangThai(int ID) {
        System.out.println("SELECT * FROM Size WHERE TrangThai = "+ID);
        return this.selectBySQL("SELECT * FROM Size WHERE TrangThai = "+ID);
        
    }
    

    @Override
    public List<Size> selectBySQL(String sql, Object... args) {
      List<Size> list = new ArrayList<>();
        try {
            ResultSet rs = _jdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                Size sp = new Size();
                sp.setID_Size(rs.getInt("ID_Size"));
                sp.setTenSize(rs.getString("TenSize"));
                sp.setMoTaSize(rs.getString("MoTaSize"));
                sp.setTrangThai(rs.getInt("TrangThai"));
                list.add(sp);
            }
//            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public List<Size> timkiem(String name) {
        return this.selectBySQL("SELECT * FROM Size WHERE TenSize LIKE N'%"+name+"%'");
    }
 }   
