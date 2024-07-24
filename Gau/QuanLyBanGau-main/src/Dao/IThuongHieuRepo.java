/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import Models.ThuongHieu;
import java.util.List;

/**
 *
 * @author khanh
 */
public interface IThuongHieuRepo {
    public boolean insert(ThuongHieu THieu);// tạo hàm 
    public boolean update (ThuongHieu id);
    public boolean delete (ThuongHieu id);
    public List<ThuongHieu> selectAll();//tạo hàm để lấy dữ liệu
    public List<ThuongHieu> selectByTrangThai(int TrangThai);
    public List<ThuongHieu> selectById(int ID);//truyền nó vào từ bên thuộc tính bằng cách lấy 1 chuỗi hoặc 1 id có dạng số nguyên từ txttimkiem(người code đặt biến)
    public List<ThuongHieu> selectBySql(String sql, Object... args);//hàm để lấy theo cột trong sql
    public List<ThuongHieu> timkiem(String name);
 
   //dua ra thong tin nv nam co tuoi lon hon 15
    //select *from nhanvien where gioitinh ='nam' and tuoi cout(>15)
}
