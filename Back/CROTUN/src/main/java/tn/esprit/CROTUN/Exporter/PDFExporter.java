package tn.esprit.CROTUN.Exporter;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;
 
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import tn.esprit.CROTUN.Entities.Slice;
 
 
public class PDFExporter {
    private List<Slice> listSlices;
     
    public PDFExporter(List<Slice> listSlices) {
        this.listSlices = listSlices;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Slice ID", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Repayment Date", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Satus", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Price", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Loan ID", font));
        table.addCell(cell);       
    }
     
    private void writeTableData(PdfPTable table) {
        for (Slice slice : listSlices) {
            table.addCell(String.valueOf(slice.getIdS()));
            table.addCell(String.valueOf(slice.getRepaymentDate()));
            table.addCell(String.valueOf(slice.getVerified()));
            table.addCell(String.valueOf(slice.getPrice()));
            table.addCell(String.valueOf(slice.getLoanSlice().getIdL()));
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("List of Slices", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}
