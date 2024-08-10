/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Helper.JDBCHelper;
import entity.size;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        return selectSPBySQL("EXEC getAllSP_DM @month = ?, @year = ?;", month, year);
    }
    public List<Object[]> getSP_DM(int ID_DM, int month, int year) {
        return selectSPBySQL("EXEC getSP_DM @ID_DM = 1, @month = ?, @year = ?;", ID_DM, month, year);
    }
    public List<Object[]> getAllSP_TH(int month, int year) {
        return selectSPBySQL("EXEC getAllSP_TH @month = ?, @year = ?;", month, year);
    }
    public List<Object[]> getSP_TH(int ID_DM, int month, int year) {
        return selectSPBySQL("EXEC getSP_TH @ID_TH = 1, @month = 8, @year = 2024;", ID_DM, month, year);
    }
    public List<Integer> getYearFromHD() {
        List<Integer> years = new ArrayList<>();
        try {
            ResultSet rs = helper.executeQuery("SELECT DISTINCT YEAR(ThoiGian) AS year FROM HOADON ORDER BY year DESC;");
            while (rs.next()) {
                System.out.println(rs.getRow());
                years.add(rs.getInt("year"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return years;
    }
    public List<Object[]> getAllCTDT() {
        return selectCTDTBySQL("EXEC getAllCTDT;");
    }

    public List<Object[]> getCTDTByDay(String dateFrom, String dateTo) {
        return selectCTDTBySQL("EXEC getCTDTByDay @dateFrom = ?, @dateTo = ?;", dateFrom, dateTo);
    }
    public List<Object[]> getCTDTByYear(String dateFrom, String dateTo) {
        return selectCTDTBySQL("EXEC getCTDTByYear @dateFrom = ?, @dateTo = ?;", dateFrom, dateTo);
    }
    public List<Object[]> getCTDTByMonth(String dateFrom, String dateTo) {
        return selectCTDTBySQL("EXEC getCTDTByMonth @dateFrom = ?, @dateTo = ?;", dateFrom, dateTo);
    }
    public Object[] getHomNayDT() {
        return selectDTNewsBySQL("EXEC getHomNayDT;");
    }
    public Object[] getThangNayDT() {
        return selectDTNewsBySQL("EXEC getThangNayDT;");
    }
    public Object[] getNamNayDT() {
        return selectDTNewsBySQL("EXEC getNamNayDT;");
    }
    public Object[] getNgayDTCaoNhat() {
        return selectDTNewsBySQL("EXEC getNgayDTCaoNhat;");
    }
    public List<Object[]> getThuongHieuChart(int month, int year) {
        return selectDataChartBySQL("EXEC getThuongHieuChart @Month = ?, @Year = ?;", month, year);
    }
    public List<Object[]> getDanhMucChart(int month, int year) {
        return selectDataChartBySQL("EXEC getDanhMucChart @Month = ?, @Year = ?;", month, year);
    }
    public List<Object[]> getDTWeekChart(int thang, int nam){
        return selectDataChartBySQL("EXEC getDTWeekChart @Month = ?, @Year = ?;", thang, nam);
    }
    public List<Object[]> getDTYearChart(int nam){
        return selectDataChartBySQL("EXEC getDTYearChart @nam = ?;", nam);
    }
    public List<Object[]> getDT10daysChart(String today){
        return selectDataChartBySQL("EXEC GetDT10daysChart @Today = ?;", today);
    }
    public Object[] getSPTrending() {
        return selectExtremeBySQL("EXEC getSPTrending;");
    }
    public Object[] getSPBanKem() {
        return selectExtremeBySQL("EXEC getSPBanKem;");
    }
    public List<Object[]> selectSPBySQL(String sql, Object... args) {
        List<Object[]> lstSP = new ArrayList<>();
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        try {
            ResultSet rs = helper.executeQuery(sql, args);
            while (rs.next()) {
                System.out.println(rs.getRow());
                double gia = rs.getDouble("Gia");
                int soLuong = rs.getInt("SoLuongDaBan");
                Object[] obj = {
                    rs.getString("ID_SP"),
                    rs.getString("TenSP"),
                    currencyFormat.format(gia),
                    soLuong,
                    currencyFormat.format(soLuong * gia),
                    rs.getString("DacDiem")
                };
                lstSP.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstSP;
    }
    public List<Object[]> selectCTDTBySQL(String sql, Object... args) {
        List<Object[]> lstSP = new ArrayList<>();
        try {
            ResultSet rs = helper.executeQuery(sql, args);
            while (rs.next()) {
                System.out.println(rs.getRow());
                Object[] obj = {
                    rs.getString("Time"),
                    rs.getInt("SoLuongDaBan"),
                    rs.getString("TongDT")
                };
                lstSP.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstSP;
    }
    public Object[] selectDTNewsBySQL(String sql, Object... args) {
        Object[] obj = new Object[]{};
        try {
            ResultSet rs = helper.executeQuery(sql, args);
            rs.next();
            System.out.println(rs.getRow());
            obj = new Object[]{
                rs.getString("TongDT"),
                rs.getInt("SoDonTC"),
                rs.getInt("SoDonBH")
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
    public Object[] selectExtremeBySQL(String sql, Object... args) {
        Object[] obj = new Object[]{};
        try {
            ResultSet rs = helper.executeQuery(sql, args);
            rs.next();
            System.out.println(rs.getRow());
            obj = new Object[]{
                rs.getString("name"),
                rs.getString("value"),
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
    public List<Object[]> selectDataChartBySQL(String sql, Object... args) {
        List<Object[]> lstDT = new ArrayList<>();
        try {
            ResultSet rs = helper.executeQuery(sql, args);
            while (rs.next()) {
                int dtK = rs.getInt("dataValue")/1000;
                System.out.println(rs.getRow());
                Object[] obj = {
                    rs.getString("chartColumn"),
                    dtK,
                };
                lstDT.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lstDT;
    }
    public Date getMaxHDDate() {
        Date maxDate = new Date();
            Object[] maxDateString = selectExtremeBySQL("SELECT '1' as name, CAST(MAX(ThoiGian) AS DATE) AS value FROM HOADON;");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
            maxDate = format.parse(maxDateString[1].toString());
        } catch (Exception e) {
        } 
        return maxDate;
    }

    public Date getMinHDDate() {
        Date minDate = new Date();
            Object[] minDateString = selectExtremeBySQL("SELECT '1' as name , CAST(Min(ThoiGian) AS DATE) AS value FROM HOADON;");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
            minDate = format.parse(minDateString[1].toString());
        } catch (Exception e) {
        } 
        return minDate;
    }
    public static void main(String[] args) {
        new thongkeDAO().getAllCTDT();
    }
}
