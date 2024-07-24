/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import Dao.IMauSacRepo;
import Dao.MauSacRepo;
import Models.MauSac;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author qivub
 */
public class MauSacService implements IMauSacService{
    IMauSacRepo iMauSacRepo;
    List<MauSac> lstMauSac;
    public MauSacService() {
        iMauSacRepo = new MauSacRepo();
        lstMauSac = iMauSacRepo.selectAll();
    }

    @Override
    public boolean addMauSac(MauSac mauSac) {
        boolean insertionResult = iMauSacRepo.insert(mauSac);    
        if (insertionResult) {
                System.out.println("MauSac inserted successfully.");
                int userChoice = JOptionPane.showConfirmDialog(null, "Xác Nhận?", "Title", JOptionPane.YES_NO_OPTION);
            return true;
        }    
        return false;
    }

    @Override
    public boolean updateMauSac(MauSac ID) {
        iMauSacRepo.update(ID);    
        return true;
    }

    @Override
    public boolean temporaryDeleteMauSac(MauSac ID) {
        iMauSacRepo.temporaryDelete(ID);    
        return true;
    }

    @Override
    public boolean deleteMauSac(MauSac ID) {
        iMauSacRepo.delete(ID);    
        return true;
    }

    @Override
    public List<MauSac> getData() {
        return iMauSacRepo.selectAll();
    }
    
    @Override
    public List<MauSac> getDataByTrangThai(int trangthai) {
         return iMauSacRepo.selectByTrangThai(trangthai);
    }

    @Override
    public List<MauSac> timkiem(String name) {
        return iMauSacRepo.timkiem(name);
    }
    
}
