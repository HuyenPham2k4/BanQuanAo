/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BUS;

import Models.DanhMucSP;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IDanhMucSPService {
    public boolean addDanhMucSP(DanhMucSP danhMuc);
    public boolean updateDanhMucSP(DanhMucSP ID);
    public boolean deteleDanhMucSP(DanhMucSP ID);
    public boolean temporaryDanhMucSP(DanhMucSP ID);
    public List<DanhMucSP> getData();
    public List<DanhMucSP> timkiem(String name);
    public List<DanhMucSP> getDataByTrangThai(int trangthai);
    
}
