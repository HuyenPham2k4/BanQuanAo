/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BUS;

import Models.ChucVu;
import java.util.List;

/**
 *
 * @author qivub
 */
public interface IChucVuService {
    public boolean add(ChucVu cv);
    public boolean update(ChucVu cv);
    public boolean delete(ChucVu id);
    public List<ChucVu> getdata();
    public List<ChucVu> getdataByName(String name);
}
