import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;

public class saveToExcel {
    public void saveData() {
        try {

            scrapeWikiData scrapeWikiData = new scrapeWikiData();
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("List_of_Student");

            //Create Heading
            Row rowHeading = sheet.createRow(0);
            rowHeading.createCell(0).setCellValue("  No");
            rowHeading.createCell(1).setCellValue("         Matric        ");
            rowHeading.createCell(2).setCellValue("         Name        ");

            //1st Row Font Size
            for (int i = 0; i <= 2; i++) {
                CellStyle style = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBold(true);
                font.setFontName(HSSFFont.FONT_ARIAL);
                font.setFontHeightInPoints((short) 12);
                style.setFont(font);
                style.setVerticalAlignment(VerticalAlignment.CENTER);
                rowHeading.getCell(i).setCellStyle(style);
            }


            int r = 1;
            for (Data data : scrapeWikiData.findAll()) {
                //Create row
                Row row = sheet.createRow(r);
                //Column 1: Number
                Cell cellId = row.createCell(0);
                cellId.setCellValue(data.getColumn1());
                //Column 2 : TR Data
                Cell cellColumn1 = row.createCell(1);
                cellColumn1.setCellValue(data.getColumn2());
                //Column 3 : TR Data
                Cell cellColumn2 = row.createCell(2);
                cellColumn2.setCellValue(data.getColumn3());
                r++;
            }
            //Auto Adjust Width
            for (int i = 1; i <= 35; i++)
                sheet.autoSizeColumn(i);

            //Save to Excel FILE
            FileOutputStream out = new FileOutputStream(new File("D:\\Excel.xls"));
            workbook.write(out);
            out.close();
            workbook.close();

        } catch (
                Exception e) {
            System.out.print(e.getStackTrace());
        }
    }
}