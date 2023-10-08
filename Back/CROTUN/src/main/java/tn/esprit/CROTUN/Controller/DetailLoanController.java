package tn.esprit.CROTUN.Controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.CROTUN.Entities.DetailLoan;
import tn.esprit.CROTUN.Entities.Loan;
import tn.esprit.CROTUN.Exporter.ExcelExporter;
import tn.esprit.CROTUN.Services.IDetailLoanService;

@RestController
@RequestMapping("/DetailLoan")
@CrossOrigin(origins = "*")
public class DetailLoanController {
	
	@Autowired
	IDetailLoanService detailLoanService;
	
	
	@RequestMapping(value="/add-detailLoan-lists/{loan-id}", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public DetailLoan addDetailLoanLists(@RequestBody DetailLoan s,@PathVariable("loan-id") Long IdC)
	{
		DetailLoan detailLoan = detailLoanService.addDetailLoan(s, IdC);
	return detailLoan;
	}
	
	@GetMapping("/retrieve-all-detailLoan")
	@ResponseBody
	public List<DetailLoan> getLoans() {
	return detailLoanService.listAll();

	}
	
    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=detailLoan_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<DetailLoan> listDetailLoans = detailLoanService.listAll();
         
        ExcelExporter excelExporter = new ExcelExporter(listDetailLoans);
         
        excelExporter.export(response);    
    }
	
}
