/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Models.ChatLieu;
import Utilities.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author qivub
 */
public class ChatLieuRepo implements IChatLieuRepo{
    private JdbcHelper _jdbcHelper;
    String INSERT_SQL = "INSERT INTO ChatLieu (TenChatLieu,MoTaChatLieu,TrangThai) VALUES (?,?,'1')";
    String UPDATE_SQL = "UPDATE ChatLieu SET TenChatLieu = ?, MoTaChatLieu = ?,TrangThai = 1 WHERE ID_ChatLieu = ?";
    String TEMPORARY_DELETE_SQL = "UPDATE ChatLieu SET TrangThai = 0 WHERE ID_ChatLieu = ? ";
    String DELETE_SQL = "DELETE ChatL ieu WHERE ID_ChatLieu = ?";
    String SELECT_ALL_SQL = "SELECT * FROM ChatLieu";
    String SELECT_BY_ID_SQL = "SELECT * FROM ChatLieu WHERE ID_ChatLieu = ?";
    
    public ChatLieuRepo() {
        _jdbcHelper = new JdbcHelper();
    }
    
    @Override
    public List<ChatLieu> selectBySQL(String sql, Object... args) {
        List<ChatLieu> list = new ArrayList<>();
        try {
            ResultSet rs = _jdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                ChatLieu sp = new ChatLieu();
                sp.setID_ChatLieu(rs.getInt("ID_ChatLieu"));
                sp.setTenChatLieu(rs.getString("TenChatLieu"));
                sp.setMoTaChatLieu(rs.getString("MoTaChatLieu"));
                sp.setTrangThai(rs.getInt("TrangThai"));
                list.add(sp);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }
    @Override
    public boolean insert(ChatLieu entity) {
        _jdbcHelper.executeUpdate(INSERT_SQL, entity.getTenChatLieu(),entity.getMoTaChatLieu());
        return true;
    }

    @Override
    public boolean update(ChatLieu entity) {
        _jdbcHelper.executeUpdate(UPDATE_SQL, entity.getTenChatLieu(),entity.getMoTaChatLieu(), entity.getID_ChatLieu());
        return true;
    }

    @Override
    public boolean delete(ChatLieu entity) {
         _jdbcHelper.executeUpdate(DELETE_SQL, entity.getID_ChatLieu());
        return true;
    }

    @Override
    public boolean temporaryDelete(ChatLieu entity) {
        _jdbcHelper.executeUpdate(TEMPORARY_DELETE_SQL, entity.getID_ChatLieu());
        return true;
    }

    @Override
    public List<ChatLieu> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public List<ChatLieu> selectByID(int ID, ChatLieu entity) {
        ID = entity.getID_ChatLieu();
        return this.selectBySQL(SELECT_BY_ID_SQL, ID);
    }

    @Override
    public List<ChatLieu> timkiem(String name) {
        return this.selectBySQL("SELECT * FROM ChatLieu WHERE TenChatLieu  LIKE N'%"+name+"%'");
    }
}
