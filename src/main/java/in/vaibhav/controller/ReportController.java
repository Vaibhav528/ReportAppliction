package in.vaibhav.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.vaibhav.entity.CitizenPlan;
import in.vaibhav.request.SearchDto;
import in.vaibhav.service.ReportService;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ReportController 
{
	@Autowired
	private ReportService services;
	
	@GetMapping("/pdf")
	public void downloadpdf(HttpServletResponse  response) throws Exception
	{
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition" , "attachment;filename=plans.pdf");
		services.pdfExport(response);
	}
	
	@GetMapping("/excel")
	public void downloadExcel(HttpServletResponse  response) throws Exception
	{
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition" , "attachment;filename=plans.xls");
		services.exportExcel(response);
	}
	
	@PostMapping("/search")
	public String handleSearch(@ModelAttribute("searchDto") SearchDto request, Model model) 
	 {
	    System.out.println(request);
	    
	    List<CitizenPlan> plans = services.search(request);
	    model.addAttribute("plans", plans);
	    init(model);
	    
	    return "index";
	}

	
	@GetMapping("/")
	public String indexPage(Model model)
	{
		 SearchDto searchObj = new SearchDto();
		 model.addAttribute("searchDto", searchObj);  // ✅ Use 'searchDto' instead of 'search'
	    init(model);  // Ensures searchDto is always available
	    return "index";
	}

	private void init(Model model) 
	{
	   
	    model.addAttribute("names", services.getPlanNames());
	    model.addAttribute("status", services.getPlanStatus());
	}

	
	
	 

}
 