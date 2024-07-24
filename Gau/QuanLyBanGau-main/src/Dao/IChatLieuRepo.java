/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import Models.ChatLieu;
import java.util.List;

/**
 *
 * @author Hieu
 */
public interface IChatLieuRepo {
    public boolean insert(ChatLieu _entity);
    public boolean update(ChatLieu _entity);
    public boolean delete(ChatLieu _entity);
    public boolean temporaryDelete(ChatLieu _entity);
    public List<ChatLieu> selectAll();
    public List<ChatLieu> timkiem(String name);
    public List<ChatLieu> selectByID(int ID,ChatLieu _entity);
    public List<ChatLieu> selectBySQL(String sql, Object... args);
}
