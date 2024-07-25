/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BUS;

import Models.NhanVien;
import java.util.List;

/**
 *
 * @author qivub
 */
public interface INhanVienService {
    public boolean addNhanVien(NhanVien nv);
    public boolean updateNhanVien(NhanVien ID);
    public boolean login(String user, String pasword);
    public boolean temporaryDeleteNhanVien(NhanVien ID);
    public boolean deleteNhanVien(NhanVien ID);
    public List<NhanVien> getData();
}
