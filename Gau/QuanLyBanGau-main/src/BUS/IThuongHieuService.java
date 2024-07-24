/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BUS;

import Models.ThuongHieu;
import java.util.List;

/**
 *
 * @author khanh
 */
public interface IThuongHieuService  {
       //java class no de de dinh nghia code
    //tất cả ở đây để khai báo hàm
    public boolean addThuongHieu(ThuongHieu THieu);//khai báo hàm
    public boolean updateThuongHieu(ThuongHieu ID);
    public boolean temporaryDeleteThuongHieu(ThuongHieu ID);
    public boolean deleteThuongHieu(ThuongHieu ID);
    public List<ThuongHieu> getData();
    public List<ThuongHieu> timkiem(String name);
    
    public List<ThuongHieu> getDataByTrangThai(int TrangThai);
}
