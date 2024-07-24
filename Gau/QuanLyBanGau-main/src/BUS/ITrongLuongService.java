/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BUS;

import Models.TrongLuong;
import java.util.List;

/**
 *
 * @author Hieu
 */
public interface ITrongLuongService {
//  Interface Không Có body code;
//  Trong interface Mặc Đinh là public không thể sử dụng private;
//  Hiểu đơn giản interface là phần xác mà chưa có hồn
    
    public boolean addTrongLuong(TrongLuong chatlieu);
    public boolean updateTrongLuong(TrongLuong ID);
    public boolean temporaryDeleteTrongLuong(TrongLuong ID);
    public boolean deleteTrongLuong(TrongLuong ID);
    public List<TrongLuong> getData();
    public List<TrongLuong> timkiem(String name);
    public List<TrongLuong> getDataByTrangThai(int TrangThai);
}
