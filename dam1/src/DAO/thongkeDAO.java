/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Helper.JDBCHelper;
import entity.size;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author letnp
 */
public class thongkeDAO {
    JDBCHelper helper;

    public thongkeDAO() {
        helper = new JDBCHelper();
    }
    public List<Object[]> getAllSP_DM(int month, int year) {
        return selectSPBySQL("SELECT \n" +
"    sp.ID AS ID_SP,\n" +
"    sp.TenSP AS TenSP,\n" +
"    sp.Gia AS Gia,\n" +
"    COALESCE(SUM(ct.SoLuong), 0) AS SoLuongDaBan,\n" +
"    dm.Ten AS DacDiem\n" +
"FROM SANPHAM sp\n" +
"LEFT JOIN CTHOADON ct ON sp.ID = ct.ID_SP\n" +
"JOIN HOADON hd ON ct.ID_HD = hd.ID\n" +
"JOIN DANHMUC dm ON sp.ID_DM = dm.ID\n" +
"WHERE MONTH(hd.ThoiGian) = ? AND YEAR(hd.ThoiGian) = ?\n" +
"GROUP BY sp.ID, sp.TenSP, sp.Gia, dm.Ten\n" +
"ORDER BY SoLuongDaBan DESC;", month, year);
    }
    public List<Object[]> getSP_DM(int ID_DM, int month, int year) {
        return selectSPBySQL("SELECT \n" +
"    sp.ID AS ID_SP,\n" +
"    sp.TenSP AS TenSP,\n" +
"    sp.Gia AS Gia,\n" +
"    COALESCE(SUM(ct.SoLuong), 0) AS SoLuongDaBan,\n" +
"    dm.Ten AS DacDiem\n" +
"FROM SANPHAM sp\n" +
"LEFT JOIN CTHOADON ct ON sp.ID = ct.ID_SP\n" +
"JOIN HOADON hd ON ct.ID_HD = hd.ID\n" +
"JOIN DANHMUC dm ON sp.ID_DM = dm.ID\n" +
"WHERE dm.ID = ? AND MONTH(hd.ThoiGian) = ? AND YEAR(hd.ThoiGian) = ?\n" +
"GROUP BY sp.ID, sp.TenSP, sp.Gia, dm.Ten\n" +
"ORDER BY SoLuongDaBan DESC;", ID_DM, month, year);
    }
    public List<Object[]> getAllSP_TH(int month, int year) {
        return selectSPBySQL("SELECT \n" +
"    sp.ID AS ID_SP,\n" +
"    sp.TenSP,\n" +
"    sp.Gia,\n" +
"    SUM(ct.SoLuong) AS SoLuongDaBan,\n" +
"    th.Ten AS DacDiem\n" +
"FROM SANPHAM sp\n" +
"LEFT JOIN CTHOADON ct ON sp.ID = ct.ID_SP\n" +
"JOIN HOADON hd ON ct.ID_HD = hd.ID\n" +
"JOIN THUONGHIEU th ON sp.ID_TH = th.ID\n" +
"WHERE MONTH(hd.ThoiGian) = ? AND YEAR(hd.ThoiGian) = ?\n" +
"GROUP BY sp.ID, sp.TenSP, sp.Gia, th.Ten\n" +
"ORDER BY SoLuongDaBan DESC;", month, year);
    }
    public List<Object[]> getSP_TH(int ID_DM, int month, int year) {
        return selectSPBySQL("SELECT \n" +
"    sp.ID AS ID_SP,\n" +
"    sp.TenSP,\n" +
"    sp.Gia,\n" +
"    SUM(ct.SoLuong) AS SoLuongDaBan,\n" +
"    th.Ten AS DacDiem\n" +
"FROM SANPHAM sp\n" +
"LEFT JOIN CTHOADON ct ON sp.ID = ct.ID_SP\n" +
"JOIN HOADON hd ON ct.ID_HD = hd.ID\n" +
"JOIN THUONGHIEU th ON sp.ID_TH = th.ID\n" +
"WHERE th.ID = ? AND MONTH(hd.ThoiGian) = ? AND YEAR(hd.ThoiGian) = ? -- Filter for January 2024\n" +
"GROUP BY sp.ID, sp.TenSP, sp.Gia, th.Ten\n" +
"ORDER BY SoLuongDaBan DESC;", ID_DM, month, year);
    }
    
    public List<Integer> getMonthFromHD(int year){
        List<Integer> months = new ArrayList<>();
        try {
            ResultSet rs = helper.executeQuery("SELECT DISTINCT MONTH(ThoiGian) AS month FROM HOADON WHERE YEAR(ThoiGian) =? ORDER BY month;", year);
            while (rs.next()) {
                System.out.println(rs.getRow());
                months.add(rs.getInt("month"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return months;
    }
        public List<Integer> getYearFromHD(){
        List<Integer> years = new ArrayList<>();
        try {
            ResultSet rs = helper.executeQuery("SELECT DISTINCT YEAR(ThoiGian) AS year FROM HOADON ORDER BY year;");
            while (rs.next()) {
                System.out.println(rs.getRow());
                years.add(rs.getInt("year"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return years;
    }
//    public List<Object[]> getAllSP_MS() {
//        return selectSPBySQL("SELECT sp.ID AS ID_SP,sp.TenSP,sp.SoLuong,sp.Gia,ms.MauSac AS DacDiem FROM SANPHAM sp\n" +
//"JOIN CTMAUSAC ctm ON sp.ID = ctm.ID_SP JOIN MAUSAC ms ON ctm.ID_MS = ms.ID;");
//    }
//    public List<Object[]> getSP_MS(int ID_MS) {
//        return selectSPBySQL("SELECT sp.ID AS ID_SP,sp.TenSP,sp.SoLuong,sp.Gia,ms.MauSac AS DacDiem FROM SANPHAM sp\n" +
//"JOIN CTMAUSAC ctm ON sp.ID = ctm.ID_SP JOIN MAUSAC ms ON ctm.ID_MS = ms.ID Where ms.ID = ?;", ID_MS);
//    }
//    public List<Object[]> getAllSP_Size() {
//        return selectSPBySQL("SELECT sp.ID AS ID_SP,sp.TenSP,sp.SoLuong,sp.Gia,sz.Ten AS DacDiem FROM SANPHAM sp\n" +
//"JOIN CTSIZE cts ON sp.ID = cts.ID_SP JOIN SIZE sz ON cts.ID_SIZE = sz.ID;");
//    }
//    public List<Object[]> getSP_Size(int ID_Size) {
//        return selectSPBySQL("SELECT sp.ID AS ID_SP,sp.TenSP,sp.SoLuong,sp.Gia,sz.Ten AS DacDiem FROM SANPHAM sp\n" +
//"JOIN CTSIZE cts ON sp.ID = cts.ID_SP JOIN SIZE sz ON cts.ID_SIZE = sz.ID Where sz.ID = ?;", ID_Size);
//    }
    public List<Object[]> selectSPBySQL(String sql, Object... args) {
        List<Object[]> lstSP = new ArrayList<>();
        try {
            ResultSet rs = helper.executeQuery(sql, args);
            while (rs.next()) {
                System.out.println(rs.getRow());
                 double gia =rs.getDouble("Gia");
                 int soLuong = rs.getInt("SoLuongDaBan");
                Object[] obj = {
                    rs.getString("ID_SP"),
                    rs.getString("TenSP"),
                    gia,
                    soLuong,
                    soLuong*gia,
                    rs.getString("DacDiem")
                };
                lstSP.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstSP;
    }
    public static void main(String[] args) {
        new thongkeDAO().getMonthFromHD(2024);
    }
}
