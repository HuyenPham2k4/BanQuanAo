/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import DAO.thongkeDAO;
import java.awt.Component;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author letnp
 */
public class ThongKeWidget {
    static public void xuatFileExcel(Component parent,JTable tbl){
    try {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("danhsach");
        XSSFRow row = null;
        Cell cell = null;

        row = sheet.createRow(0); 
        int columnCount = tbl.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            cell = row.createCell(i, CellType.STRING);
            cell.setCellValue(tbl.getColumnName(i));
        }

        int rowCount = tbl.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < columnCount; j++) {
                cell = row.createCell(j, CellType.STRING);
                Object value = tbl.getValueAt(i, j);
                if (value != null) {
                    cell.setCellValue(value.toString());
                }
            }
        }
        File f = new File(pathSaveFile(parent));
        try {
            FileOutputStream fos = new FileOutputStream(f);
            workbook.write(fos);
            fos.close();
            workbook.close(); 
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(parent, "Lỗi "+ e);
        }
}catch (Exception e) {
        }
        
    }
    static public String pathSaveFile(Component parent){
        String pathName = "";
        JFileChooser folderPath = new JFileChooser();
        folderPath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        folderPath.setDialogTitle("Select a Folder");

        int x = folderPath.showDialog(null, "Save");
        if (x == JFileChooser.APPROVE_OPTION){
            pathName = folderPath.getSelectedFile().toString();
            JOptionPane.showMessageDialog(parent, "Đã xuất file Excel thành công! ");
        }
        if (!pathName.toLowerCase().endsWith(".xlsx")) {
            pathName += ".xlsx";
        }
        return  pathName;
    }
    static public void mesagePanel(Component parent){
        JOptionPane.showMessageDialog(parent, "Đã xuất file Excel thành công! ");
    }
    static public void ImgIcon(JLabel lblImg, String imgName){
        lblImg.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\src\\ANH_CSDL_270x270px\\"+imgName));
    }
    static public String convertToDateSQL(Date xdate){
        SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd");
        return sqlFormat.format(xdate);
    }
    static public String getToDaySQL(){
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
        return today.format(formatter);
    }
    static public int getTodayMonth(){
        return LocalDateTime.now().getMonth().getValue();
    }
    static public int getTodayYear(){
        return LocalDateTime.now().getYear();
    }
}
