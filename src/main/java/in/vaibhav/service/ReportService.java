package in.vaibhav.service;

import java.util.List;

import in.vaibhav.entity.CitizenPlan;
import in.vaibhav.request.SearchDto;
import jakarta.servlet.http.HttpServletResponse;

public interface ReportService 
{
	
	public List<String> getPlanNames();
	
	 public List<String> getPlanStatus();
	 
	//method for search  functionality  button 
	 public List<CitizenPlan> search(SearchDto request);
	 
	 public boolean exportExcel(HttpServletResponse  response) throws Exception ;
	 
	 public boolean pdfExport(HttpServletResponse  response) throws Exception ;

}
