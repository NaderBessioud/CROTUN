package tn.esprit.CROTUN.Exporter;

import java.io.IOException;
import java.util.List;
 
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tn.esprit.CROTUN.Entities.DetailLoan;
 
public class ExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<DetailLoan> listDetailLoans;
     
    public ExcelExporter(List<DetailLoan> listDetailLoans) {
        this.listDetailLoans = listDetailLoans;
        workbook = new XSSFWorkbook();
    }
 
 
    private void writeHeaderLine() {
        sheet = workbook.createSheet("DetailLoans");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "DetailLoan ID", style);      
        createCell(row, 1, "Montant restant dû", style);       
        createCell(row, 2, "Interet", style);    
        createCell(row, 3, "Amortissement", style);
        createCell(row, 4, "Mensualite", style);
         
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
     
    private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (DetailLoan detailLoan : listDetailLoans) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, Long.toString(detailLoan.getIdD()), style);
            createCell(row, columnCount++, Double.toString(detailLoan.getMontantRestant()), style);
            createCell(row, columnCount++, Double.toString(detailLoan.getInterest()), style);
            createCell(row, columnCount++, Double.toString(detailLoan.getAmortissements()), style);
            createCell(row, columnCount++, Double.toString(detailLoan.getMensualite()), style);
             
        }
    }
     
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
}
