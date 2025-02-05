package in.vaibhav.service;

import java.util.List;

import in.vaibhav.entity.CitizenPlan;
import in.vaibhav.request.SearchDto;

public interface ReportService 
{
	
	public List<String> getPlannames();
	
	 public List<String> getPlanStatus();
	 
	//method for search  functionality  button 
	 public List<CitizenPlan> search(SearchDto request);
	 
	 public boolean exportExcel();
	 
	 public boolean pdfExport();

}
