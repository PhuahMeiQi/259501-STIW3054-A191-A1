import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

public class ExcelCompare {
    public static void main (String[] args) throws IOException{

        //ExtentReports extent = new ExtentReports("Re")

        FileInputStream fileInputStream1 = new FileInputStream("D:\\Excel.xls");
        HSSFWorkbook workbook1 = new HSSFWorkbook(fileInputStream1);
        HSSFSheet worksheet1 = workbook1.getSheet("Sheet1");
        int rowCount1=worksheet1.getPhysicalNumberOfRows();

        FileInputStream fileInputStream2 = new FileInputStream("D:\\Excelsnd.xls");
        HSSFWorkbook workbook2 = new HSSFWorkbook(fileInputStream2);
        HSSFSheet worksheet2 = workbook2.getSheet("Sheet1");
        int rowCount2 = worksheet2.getPhysicalNumberOfRows();

        if (rowCount1 == rowCount2){
            for (int i=1;i<rowCount1;i++){
                HSSFRow row1 = worksheet1.getRow(i);
                HSSFRow row2 = worksheet2.getRow(i);

                String Matricstr1 = "";
                HSSFCell Matric1 = row1.getCell(0);
                if (Matric1 != null){
                    Matric1.setCellType(CellType.STRING);
                    Matricstr1 = Matric1.getStringCellValue();
                }

                String Matricstr2="";
                HSSFCell Matric2 = row2.getCell(0);
                if (Matric2 !=null){
                    Matric2.setCellType(CellType.STRING);
                    Matricstr2 = Matric2.getStringCellValue();
                }

                if(!Matricstr1.equals(Matricstr2)){
                    System.out.println(Matricstr2);
                   // test.log(LogStatus.ERROR,"Diference for id (book1)"+idstr1+"|Book 1 id = "+idstr1+"| Book 2 id = "+idstr2);
                }
            }
        }
    }
}
