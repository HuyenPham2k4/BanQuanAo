/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BUS;

import Models.Size;
import java.util.List;

/**
 *
 * @author qivub
 */
public interface ISizeService {
    public boolean add(Size KH);
    public boolean update(Size ID);
    public boolean temporaryDelete(Size ID);
    public boolean delete(Size ID);
    public List<Size> getData();
    public List<Size> timkiem(String name);
    public List<Size> getDataByTrangThai(int trangthai);
}
