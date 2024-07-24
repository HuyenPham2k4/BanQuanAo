/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BUS;

import Models.MauSac;
import java.util.List;

/**
 *
 * @author qivub
 */
public interface IMauSacService {
    public boolean addMauSac(MauSac mauSac);
    public boolean updateMauSac(MauSac ID);
    public boolean temporaryDeleteMauSac(MauSac ID);
    public boolean deleteMauSac(MauSac ID);
    public List<MauSac> getData();
    public List<MauSac> timkiem(String name);
    public List<MauSac> getDataByTrangThai(int TrangThai);
}
