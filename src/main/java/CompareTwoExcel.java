import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class CompareTwoExcel {
    public static int firstColumnNum = 1;
    public static int secondColumnNum = 0;

    public static void main(String args[]) throws IOException {
        try {
            ArrayList array1 = new ArrayList();
            ArrayList array2 = new ArrayList();
            ArrayList array3 = new ArrayList();
            ArrayList array4 = new ArrayList();

            FileInputStream file1 = new FileInputStream(new File("D:\\Excel.xls"));

            FileInputStream file2 = new FileInputStream(new File("D:\\Excelsnd.xls"));

            // Get the workbook instance for XLS file
            HSSFWorkbook workbook1 = new HSSFWorkbook(file1);
            HSSFWorkbook workbook2 = new HSSFWorkbook(file2);

            // Get only first sheet from the workbook
            HSSFSheet sheet1 = workbook1.getSheetAt(0);
            HSSFSheet sheet2 = workbook2.getSheetAt(0);


            // Get iterator to all the rows in current sheet1
            Iterator<Row> rowIterator1 = sheet1.iterator();
            Iterator<Row> rowIterator2 = sheet2.iterator();
            Iterator<Row> rowIterator3 = sheet1.iterator();
            Iterator<Row> rowIterator4 = sheet2.iterator();

            //getting date from first excel file
            while (rowIterator1.hasNext()) {
                Row row = rowIterator1.next();
                // For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    // This is for read only one column from excel

                    if (cell.getColumnIndex() == firstColumnNum) {
                        // Check the cell type and format accordingly

                            switch (cell.getCellType()) {
                                case NUMERIC:
                                    array1.add(cell.getNumericCellValue());
                                    break;
                                case STRING:
                                    array1.add(cell.getStringCellValue());
                                    break;
                            }
                    }
                    System.out.print("\t"+cell);
                }
                System.out.println(" ");
            }

            file1.close();
            System.out.println("\n\n\n\n\n\n");
            System.out.println("Students who have submitted the GitHub account: ");
            System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            // For retrive the second excel data
            while (rowIterator2.hasNext()) {
                Row row1 = rowIterator2.next();

                // For each row, iterate through all the columns
                Iterator<Cell> cellIterator1 = row1.cellIterator();
                while (cellIterator1.hasNext()) {
                    Cell cell1 = cellIterator1.next();
                    // Check the cell type and format accordingly

                    // This is for read only one column from excel
                    if (cell1.getColumnIndex() == secondColumnNum) {

                            switch (cell1.getCellType()) {
                                case NUMERIC:
                                    array2.add(cell1.getNumericCellValue());
                                    break;
                                case STRING:
                                    array2.add(cell1.getStringCellValue());
                                    break;
                            }
                    }
                    System.out.printf("| %-17s",cell1);
                    //System.out.print(cell1+"\t\t\t\t\t\t\t");
                }
                System.out.println();
            }

            // compare two arrays
            for (Object process : array1) {
                if (!array2.contains(process)) {
                    array3.add(process);
                }
            }

            for (Object process : array2) {
                if (!array1.contains(process)) {
                    array4.add(process);
                }
            }
            System.out.println("\n\n\n\n\n\n\nStudents who have not submitted the GitHub account: " + array3);

            //writeResultDataToExcel(array3);

            System.out.format("| %-10s| %-20s| %-40s\n","No","Matric","Name");
            int a = 0;
            while (rowIterator3.hasNext()) {
                Row row = rowIterator3.next();
                for (Object matric : array3) {
                    if (row.getCell(1).toString().equals(matric)) {
                        a++;
                        System.out.format("| %-10s| %-20s| %-40s\n",a,row.getCell(1),row.getCell(2));
                    }
                }
            }


            System.out.println("\n\nUnknow List: " + array4);

            System.out.format("| %-10s| %-20s| %-40s\n","No","Matric","Name","Link");
            int b = 0;
            while (rowIterator4.hasNext()) {
                Row row = rowIterator4.next();
                for (Object matric : array4) {
                    if (row.getCell(0).toString().equals(matric)) {
                        b++;
                        System.out.format("| %-10s| %-20s| %-40s\n",b,row.getCell(0),row.getCell(1),row.getCell(2));
                    }
                }
            }

            System.out.println("\nNor Aida Binti Amuruddin has wrote the wrong matric number");



            // closing the files
            file1.close();
            file2.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}