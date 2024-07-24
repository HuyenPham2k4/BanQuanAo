/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import Dao.IThuongHieuRepo;
import Dao.ThuongHieuRepo;
import Models.ThuongHieu;
import Utilities.JdbcHelper;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author khanh
 */
public class ThuongHieuService implements IThuongHieuService{
     IThuongHieuRepo ithrepo;
     List<ThuongHieu> lstth;
    public ThuongHieuService(){
        JdbcHelper _jdbc =  new JdbcHelper();
        ithrepo = new ThuongHieuRepo();//định nghĩa lấy từ thuonghieureopo bên Dao
    }
    @Override
    public boolean addThuongHieu(ThuongHieu THieu) {
        if (ithrepo.insert(THieu)) {//gọi hàm để thi hành: ithrepo.insert là gọi insert bên ithuonghieurepo bên dao, tương tự với những cái bên dưới
            System.out.println("ok");
            return true;
        }
        return false;
    }
    @Override
    public boolean updateThuongHieu(ThuongHieu ID) {
         if (ithrepo.update(ID)) {
            System.out.println("ok");
            return true;
        }
        return false;
    }

    @Override
    public boolean temporaryDeleteThuongHieu(ThuongHieu ID) {
        if (ithrepo.delete(ID)) {
            System.out.println("ok");
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteThuongHieu(ThuongHieu ID) {
        if (ithrepo.delete(ID)) {
            System.out.println("ok");
            return true;
        }
        return false;
    }

    @Override
    public List<ThuongHieu> getData() {
        return ithrepo.selectAll();
    }
    
    @Override
    public List<ThuongHieu> getDataByTrangThai(int trangthai) {
         return ithrepo.selectByTrangThai(trangthai);
    }

    @Override
    public List<ThuongHieu> timkiem(String name) {
        return ithrepo.timkiem(name);
    }
    
}
