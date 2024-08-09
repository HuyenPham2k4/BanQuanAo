/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;
import entity.voucher;
import java.util.List;

/**
 *
 * @author hauvv
 */
public interface IVRepo {
    boolean add(voucher v);
    boolean update(voucher v);
    boolean delete(voucher v);
    List<voucher> getAll();
    List<voucher> selectBySQL(String sql, Object... args);
    voucher findByMaVoucher(String mavoucher);
    List<voucher> findByDateRange(int startDate, int endDate);
}

