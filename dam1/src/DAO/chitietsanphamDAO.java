/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Helper.JDBCHelper;
import entity.chitietsanpham;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author letnp
 */
public class chitietsanphamDAO {
    JDBCHelper helper;
    private String sqlQuery = "  SELECT DISTINCT\n" +
"    sp.ID AS MaSP,sp.TenSP,sp.SoLuong,COALESCE(FORMAT(sp.Gia, 'N0'), '0') AS GiaSP,sp.MoTa,\n" +
"    (SELECT STRING_AGG(ms.MauSac, ', ') \n" +
"        FROM CTMAUSAC ct\n" +
"        JOIN MAUSAC ms ON ct.ID_MS = ms.ID\n" +
"        WHERE ct.ID_SP = sp.ID) AS MauSac,\n" +
"    (SELECT STRING_AGG(s.Ten, ', ') \n" +
"        FROM CTSIZE ct\n" +
"        JOIN SIZE s ON ct.ID_SIZE = s.ID\n" +
"        WHERE ct.ID_SP = sp.ID) AS Size,\n" +
"    dm.Ten AS DanhMuc,th.Ten AS ThuongHieu\n" +
"FROM SANPHAM sp\n" +
"LEFT JOIN DANHMUC dm ON sp.ID_DM = dm.ID\n" +
"LEFT JOIN THUONGHIEU th ON sp.ID_TH = th.ID \n";
    public chitietsanphamDAO(){
        helper = new JDBCHelper();
    }
    
    public List<chitietsanpham> getAll(int TrangThai) {
        String dieuKien =  TrangThai ==0 ? "" : TrangThai == 1 ? "WHERE sp.TrangThai = 1" : "WHERE sp.TrangThai = 0";
        
        return selectBySQL(sqlQuery+dieuKien);
    }
    public List<chitietsanpham> FindByKeyword(String keyword, int TrangThai) {
        keyword = "%"+keyword+"%";
        String dieuKien =  TrangThai ==0 ? "WHERE " : TrangThai == 1 ? "WHERE sp.TrangThai = 1 AND " : "WHERE sp.TrangThai = 0 AND ";
        String dkKeyWord = " sp.TenSP like ?;";
        return selectBySQL(sqlQuery+dieuKien+dkKeyWord,keyword);
    }
    public List<chitietsanpham> getLocSanpham(String Filter) {
        String sqlFilter = sqlQuery + "LEFT JOIN CTSIZE cts ON cts.ID_SP = sp.ID \n" +
"LEFT JOIN CTMAUSAC ctm ON ctm.ID_sp = sp.ID\n" +
"WHERE " + Filter;
        return selectBySQL(sqlFilter);
    }
    public List<chitietsanpham> selectBySQL(String sql, Object... args) {
        List<chitietsanpham> ctsanphams = new ArrayList<>();
        try {
            ResultSet rs = helper.executeQuery(sql, args);
            while (rs.next()) {
                System.out.println(rs.getRow());
                ctsanphams.add( new chitietsanpham(
                    rs.getString("MaSP"),
                    rs.getString("TenSP"),
                    rs.getInt("SoLuong"),
                    rs.getString("GiaSP"),
                    rs.getString("MoTa"),
                    rs.getString("MauSac"),
                    rs.getString("Size"),
                    rs.getString("DanhMuc"),
                    rs.getString("ThuongHieu")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ctsanphams;
    }
    public static void main(String[] args) {
        new chitietsanphamDAO().FindByKeyword( "á", -1);
    }
}
