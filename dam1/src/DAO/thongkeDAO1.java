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
public class thongkeDAO1 {

    JDBCHelper helper;

    public thongkeDAO1() {
        helper = new JDBCHelper();
    }

    public List<Object[]> getAllSP_DM(int month, int year) {
        return selectSPBySQL("SELECT \n"
                + "    sp.ID AS ID_SP,\n"
                + "    sp.TenSP AS TenSP,\n"
                + "    sp.Gia AS Gia,\n"
                + "    COALESCE(SUM(ct.SoLuong), 0) AS SoLuongDaBan,\n"
                + "    dm.Ten AS DacDiem\n"
                + "FROM SANPHAM sp\n"
                + "LEFT JOIN CTHOADON ct ON sp.ID = ct.ID_SP\n"
                + "JOIN HOADON hd ON ct.ID_HD = hd.ID\n"
                + "JOIN DANHMUC dm ON sp.ID_DM = dm.ID\n"
                + "WHERE hd.TrangThai = '1'AND MONTH(hd.ThoiGian) = ? AND YEAR(hd.ThoiGian) = ?\n"
                + "GROUP BY sp.ID, sp.TenSP, sp.Gia, dm.Ten\n"
                + "ORDER BY SoLuongDaBan DESC;", month, year);
    }

    public List<Object[]> getSP_DM(int ID_DM, int month, int year) {
        return selectSPBySQL("SELECT \n"
                + "    sp.ID AS ID_SP,\n"
                + "    sp.TenSP AS TenSP,\n"
                + "    sp.Gia AS Gia,\n"
                + "    COALESCE(SUM(ct.SoLuong), 0) AS SoLuongDaBan,\n"
                + "    dm.Ten AS DacDiem\n"
                + "FROM SANPHAM sp\n"
                + "LEFT JOIN CTHOADON ct ON sp.ID = ct.ID_SP\n"
                + "JOIN HOADON hd ON ct.ID_HD = hd.ID\n"
                + "JOIN DANHMUC dm ON sp.ID_DM = dm.ID\n"
                + "WHERE hd.TrangThai = '1'AND dm.ID = ? AND MONTH(hd.ThoiGian) = ? AND YEAR(hd.ThoiGian) = ?\n"
                + "GROUP BY sp.ID, sp.TenSP, sp.Gia, dm.Ten\n"
                + "ORDER BY SoLuongDaBan DESC;", ID_DM, month, year);
    }

    public List<Object[]> getAllSP_TH(int month, int year) {
        return selectSPBySQL("SELECT \n"
                + "    sp.ID AS ID_SP,\n"
                + "    sp.TenSP,\n"
                + "    sp.Gia,\n"
                + "    SUM(ct.SoLuong) AS SoLuongDaBan,\n"
                + "    th.Ten AS DacDiem\n"
                + "FROM SANPHAM sp\n"
                + "LEFT JOIN CTHOADON ct ON sp.ID = ct.ID_SP\n"
                + "JOIN HOADON hd ON ct.ID_HD = hd.ID\n"
                + "JOIN THUONGHIEU th ON sp.ID_TH = th.ID\n"
                + "WHERE hd.TrangThai = '1' AND MONTH(hd.ThoiGian) = ? AND YEAR(hd.ThoiGian) = ?\n"
                + "GROUP BY sp.ID, sp.TenSP, sp.Gia, th.Ten\n"
                + "ORDER BY SoLuongDaBan DESC;", month, year);
    }

    public List<Object[]> getSP_TH(int ID_DM, int month, int year) {
        return selectSPBySQL("SELECT \n"
                + "    sp.ID AS ID_SP,\n"
                + "    sp.TenSP,\n"
                + "    sp.Gia,\n"
                + "    SUM(ct.SoLuong) AS SoLuongDaBan,\n"
                + "    th.Ten AS DacDiem\n"
                + "FROM SANPHAM sp\n"
                + "LEFT JOIN CTHOADON ct ON sp.ID = ct.ID_SP\n"
                + "JOIN HOADON hd ON ct.ID_HD = hd.ID\n"
                + "JOIN THUONGHIEU th ON sp.ID_TH = th.ID\n"
                + "WHERE hd.TrangThai = '1' AND th.ID = ? AND MONTH(hd.ThoiGian) = ? AND YEAR(hd.ThoiGian) = ? -- Filter for January 2024\n"
                + "GROUP BY sp.ID, sp.TenSP, sp.Gia, th.Ten\n"
                + "ORDER BY SoLuongDaBan DESC;", ID_DM, month, year);
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
        return selectCTDTBySQL("SELECT \n"
                + "    FORMAT(CONVERT(DATE, hd.ThoiGian), 'dd/MM/yyyy') AS Time,\n"
                + "    COALESCE(SUM(ct.SoLuong), 0) AS SoLuongDaBan,\n"
                + "    COALESCE(FORMAT(SUM(ct.SoLuong * sp.Gia), 'N0'), '0') AS TongDT\n"
                + "FROM HOADON hd\n"
                + "JOIN CTHOADON ct ON hd.ID = ct.ID_HD\n"
                + "JOIN SANPHAM sp ON ct.ID_SP = sp.ID\n"
                + "WHERE hd.TrangThai = '1'\n"
                + "GROUP BY CONVERT(DATE, hd.ThoiGian)\n"
                + "ORDER BY CONVERT(DATE, hd.ThoiGian);");
    }

    public List<Object[]> getCTDTByDay(String dateFrom, String dateTo) {
        return selectCTDTBySQL("SELECT \n"
                + "    FORMAT(CONVERT(DATE, hd.ThoiGian), 'dd/MM/yyyy') AS Time,\n"
                + "    COALESCE(SUM(ct.SoLuong), 0) AS SoLuongDaBan,\n"
                + "    COALESCE(FORMAT(SUM(ct.SoLuong * sp.Gia), 'N0'), '0') AS TongDT\n"
                + "FROM HOADON hd\n"
                + "JOIN CTHOADON ct ON hd.ID = ct.ID_HD\n"
                + "JOIN SANPHAM sp ON ct.ID_SP = sp.ID\n"
                + "WHERE hd.TrangThai = '1'"
                + "GROUP BY CONVERT(DATE, hd.ThoiGian)\n"
                + "HAVING CONVERT(DATE, hd.ThoiGian) BETWEEN ? AND ?"
                + "ORDER BY CONVERT(DATE, hd.ThoiGian);", dateFrom, dateTo);
    }

    public List<Object[]> getCTDTByYear(String dateFrom, String dateTo) {
        return selectCTDTBySQL("SELECT \n"
                + "    YEAR(hd.ThoiGian) AS Time,\n"
                + "    COALESCE(SUM(ct.SoLuong), 0) AS SoLuongDaBan,\n"
                + "    COALESCE(FORMAT(SUM(ct.SoLuong * sp.Gia), 'N0'), '0') AS TongDT\n"
                + "FROM HOADON hd\n"
                + "JOIN CTHOADON ct ON hd.ID = ct.ID_HD\n"
                + "JOIN SANPHAM sp ON ct.ID_SP = sp.ID\n"
                + "WHERE hd.TrangThai = '1' AND CONVERT(DATE, hd.ThoiGian) BETWEEN ? AND ?\n"
                + "GROUP BY YEAR(hd.ThoiGian)\n"
                + "ORDER BY YEAR(hd.ThoiGian);", dateFrom, dateTo);
    }

    public List<Object[]> getCTDTByMonth(String dateFrom, String dateTo) {
        return selectCTDTBySQL("SELECT \n"
                + "    FORMAT(hd.ThoiGian, 'MM/yyyy') AS Time,\n"
                + "    COALESCE(SUM(ct.SoLuong), 0) AS SoLuongDaBan,\n"
                + "    COALESCE(FORMAT(SUM(ct.SoLuong * sp.Gia), 'N0'), '0') AS TongDT\n"
                + "FROM HOADON hd\n"
                + "JOIN CTHOADON ct ON hd.ID = ct.ID_HD\n"
                + "JOIN SANPHAM sp ON ct.ID_SP = sp.ID\n"
                + "WHERE hd.TrangThai = '1' AND CONVERT(DATE, hd.ThoiGian) BETWEEN ? AND ?\n"
                + "GROUP BY FORMAT(hd.ThoiGian, 'MM/yyyy')\n"
                + "ORDER BY MIN(hd.ThoiGian);", dateFrom, dateTo);
    }

    public Object[] getHomNayDT() {
        return selectDTNewsBySQL("SELECT \n"
                + "    COALESCE(FORMAT(SUM(CASE WHEN hd.TrangThai = '1' THEN ct.SoLuong * sp.Gia ELSE 0 END), 'N0'), '0') AS TongDT,\n"
                + "    COUNT(CASE WHEN hd.TrangThai = '1' THEN 1 END) AS SoDonTC,\n"
                + "    COUNT(CASE WHEN hd.TrangThai = '0' THEN 1 END) AS SoDonBH\n"
                + "FROM HOADON hd\n"
                + "JOIN CTHOADON ct ON hd.ID = ct.ID_HD\n"
                + "JOIN SANPHAM sp ON ct.ID_SP = sp.ID\n"
                + "WHERE hd.TrangThai = '1' AND CONVERT(DATE, hd.ThoiGian) = CONVERT(DATE, GETDATE());");
    }

    public Object[] getThangNayDT() {
        return selectDTNewsBySQL("SELECT \n"
                + "    COALESCE(FORMAT(SUM(CASE WHEN hd.TrangThai = '1' THEN ct.SoLuong * sp.Gia ELSE 0 END), 'N0'), '0') AS TongDT,\n"
                + "    COUNT(CASE WHEN hd.TrangThai = '1' THEN 1 END) AS SoDonTC,\n"
                + "    COUNT(CASE WHEN hd.TrangThai = '0' THEN 1 END) AS SoDonBH\n"
                + "FROM HOADON hd\n"
                + "JOIN CTHOADON ct ON hd.ID = ct.ID_HD\n"
                + "JOIN SANPHAM sp ON ct.ID_SP = sp.ID\n"
                + "WHERE hd.TrangThai = '1' AND YEAR(hd.ThoiGian) = YEAR(GETDATE()) \n"
                + "  AND MONTH(hd.ThoiGian) = MONTH(GETDATE());");
    }

    public Object[] getNamNayDT() {
        return selectDTNewsBySQL("  SELECT \n"
                + "    COALESCE(FORMAT(SUM(CASE WHEN hd.TrangThai = '1' THEN ct.SoLuong * sp.Gia ELSE 0 END), 'N0'), '0') AS TongDT,\n"
                + "    COUNT(CASE WHEN hd.TrangThai = '1' THEN 1 END) AS SoDonTC,\n"
                + "    COUNT(CASE WHEN hd.TrangThai = '0' THEN 1 END) AS SoDonBH\n"
                + "FROM HOADON hd\n"
                + "JOIN CTHOADON ct ON hd.ID = ct.ID_HD\n"
                + "JOIN SANPHAM sp ON ct.ID_SP = sp.ID\n"
                + "WHERE hd.TrangThai = '1' AND YEAR(hd.ThoiGian) = YEAR(GETDATE());");
    }

    public Object[] getNgayDTCaoNhat() {
        return selectDTNewsBySQL("    SELECT TOP 1 \n"
                + "        COALESCE(FORMAT(SUM(CASE WHEN hd.TrangThai = '1' THEN ct.SoLuong * sp.Gia ELSE 0 END), 'N0'), '0') AS TongDT,\n"
                + "        COUNT(CASE WHEN hd.TrangThai = '1' THEN 1 END) AS SoDonTC,\n"
                + "        COUNT(CASE WHEN hd.TrangThai = '0' THEN 1 END) AS SoDonBH,\n"
                + "        CONVERT(DATE, hd.ThoiGian) AS Ngay\n"
                + "    FROM HOADON hd\n"
                + "    JOIN CTHOADON ct ON hd.ID = ct.ID_HD\n"
                + "    JOIN SANPHAM sp ON ct.ID_SP = sp.ID\n"
                + "    GROUP BY CONVERT(DATE, hd.ThoiGian)\n"
                + "    ORDER BY TongDT DESC");
    }
    public List<Object[]> getThuongHieuChart(int month, int year) {
        return selectDataChartBySQL("SELECT  TH.Ten AS chartColumn, SUM(CTHD.SoLuong * CTHD.Gia) AS dataValue\n" +
"FROM THUONGHIEU TH\n" +
"JOIN SANPHAM SP ON TH.ID = SP.ID_TH\n" +
"JOIN CTHOADON CTHD ON SP.ID = CTHD.ID_SP\n" +
"JOIN HOADON HD ON CTHD.ID_HD = HD.ID\n" +
"WHERE HD.TrangThai = 1 AND MONTH(HD.ThoiGian) = ? AND YEAR(HD.ThoiGian) = ?\n" +
"GROUP BY TH.Ten;", month, year);
    }
    public List<Object[]> getDanhMucChart(int month, int year) {
        return selectDataChartBySQL("SELECT DM.Ten AS chartColumn,SUM(CTHD.SoLuong * CTHD.Gia) AS dataValue\n" +
"FROM DANHMUC DM\n" +
"JOIN SANPHAM SP ON DM.ID = SP.ID_DM\n" +
"JOIN CTHOADON CTHD ON SP.ID = CTHD.ID_SP\n" +
"JOIN HOADON HD ON CTHD.ID_HD = HD.ID\n" +
"WHERE HD.TrangThai = 1 AND MONTH(HD.ThoiGian) = ? AND YEAR(HD.ThoiGian) = ?\n" +
"GROUP BY DM.Ten;", month, year);
    }
    public List<Object[]> getDTWeekChart(int thang, int nam){
        return selectDataChartBySQL("SELECT DATEPART(WEEK, HD.ThoiGian) AS chartColumn,"+ 
"SUM(CTHD.SoLuong * CTHD.Gia) AS dataValue\n" +
"FROM HOADON HD JOIN CTHOADON CTHD ON HD.ID = CTHD.ID_HD\n" +
"WHERE MONTH(HD.ThoiGian) = ? AND YEAR(HD.ThoiGian) = ? AND HD.TrangThai = 1\n" +
"GROUP BY DATEPART(WEEK, HD.ThoiGian)\n" +
"ORDER BY chartColumn;", thang, nam);
    }
    public List<Object[]> getDTYearChart(int nam){
        return selectDataChartBySQL("SELECT FORMAT(HD.ThoiGian, 'MM/yyyy') AS chartColumn,"+ 
                "SUM(CTHD.SoLuong * CTHD.Gia) AS dataValue\n" +
"FROM HOADON HD JOIN CTHOADON CTHD ON HD.ID = CTHD.ID_HD\n" +
"WHERE YEAR(HD.ThoiGian) = ? AND HD.TrangThai = 1 \n" +
"GROUP BY FORMAT(HD.ThoiGian, 'MM/yyyy') ORDER BY chartColumn;", nam);
    }
    public List<Object[]> getDT10daysChart(String today){
        return selectDataChartBySQL(
"SELECT FORMAT(CAST(HD.ThoiGian AS DATE), 'dd/MM') AS chartColumn,\n" +
"SUM(CTHD.SoLuong * CTHD.Gia) AS dataValue FROM HOADON HD\n" +
"JOIN CTHOADON CTHD ON HD.ID = CTHD.ID_HD\n" +
"WHERE HD.ThoiGian >= DATEADD(DAY, -10, ?) AND HD.TrangThai = 1 \n" +
"GROUP BY CAST(HD.ThoiGian AS DATE)  ORDER BY CAST(HD.ThoiGian AS DATE);", today);
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
    public Object[] getSPTrending() {
        return selectExtremeBySQL("SELECT TOP 1 SP.TenSP as name, SP.AnhSP AS value\n" +
"FROM SANPHAM SP JOIN CTHOADON CTHD ON SP.ID = CTHD.ID_SP\n" +
"JOIN HOADON HD ON CTHD.ID_HD = HD.ID\n" +
"WHERE HD.ThoiGian >= DATEADD(DAY, -60, GETDATE()) AND HD.TrangThai = 1\n" +
"GROUP BY SP.TenSP, SP.AnhSP  ORDER BY SUM(CTHD.SoLuong * CTHD.Gia) DESC;\n");
    }
    public Object[] getSPBanKem() {
        return selectExtremeBySQL("SELECT TOP 1 SP.TenSP as name, SP.AnhSP AS value\n" +
"FROM SANPHAM SP JOIN CTHOADON CTHD ON SP.ID = CTHD.ID_SP\n" +
"JOIN HOADON HD ON CTHD.ID_HD = HD.ID\n" +
"WHERE HD.ThoiGian >= DATEADD(DAY, -60, GETDATE()) AND HD.TrangThai = 1\n" +
"GROUP BY SP.TenSP, SP.AnhSP  ORDER BY SUM(CTHD.SoLuong * CTHD.Gia) ASC;\n");
    }
    public static void main(String[] args) {
        new thongkeDAO1().getDTYearChart(2024);
    }
}
