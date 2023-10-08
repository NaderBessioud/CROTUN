package tn.esprit.CROTUN.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import tn.esprit.CROTUN.Entities.Loan;
import tn.esprit.CROTUN.Entities.Slice;
import tn.esprit.CROTUN.Exporter.PDFExporter;
import tn.esprit.CROTUN.Services.ISliceService;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;



@RestController
@RequestMapping("/slice")
@CrossOrigin(origins = "*")
public class SliceController {
	
	@Autowired
	ISliceService sliceService;
	
	@GetMapping("/retrieve-slice/{slice-id}")
	@ResponseBody
	public Slice retrieveLoan(@PathVariable("slice-id") Long SliceId) {
	return sliceService.retrieveSlice(SliceId);
	
	}
	
	@RequestMapping(value="/add-slice/{loan-id}", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Slice addSlice(@RequestBody Slice s,@PathVariable("loan-id") Long IdC)
	{
		System.out.print("iddddd "+IdC);
		Slice slice = sliceService.addSlice(s,IdC);
	return slice;
	}
	
	@RequestMapping(value="/add-slice-lists/{loan-id}", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Slice addSliceLists(@RequestBody Slice s,@PathVariable("loan-id") Long IdC)
	{
		Slice slice = sliceService.addSliceLists(s,IdC);
	return slice;
	}
	
	
    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=slice_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        List<Slice> listUsers = sliceService.listAll();
         
        PDFExporter exporter = new PDFExporter(listUsers);
        exporter.export(response);
         
    }
	
	@GetMapping("/retrieve-all-slices")
	@ResponseBody
	public List<Slice> retrieveAllSlices() {
		List<Slice> listLoans = sliceService.retrieveAllSlices();
		return listLoans;
	}
	@GetMapping("/retrieve-all-Slices-paye")
	@ResponseBody
	public List<Slice> getslicePaye() {
	List<Slice> list = sliceService.retrieveAllSlicePaye();
	return list;
	}
	
	@GetMapping("/retrieve-all-Slices-non-paye")
	@ResponseBody
	public List<Slice> getsliceNonPaye() {
	List<Slice> list = sliceService.retrieveAllSliceNonPaye();
	return list;
	
	}
}
