package in.vaibhav.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.vaibhav.entity.CitizenPlan;
import in.vaibhav.repo.CitizenPlanRepository;
import in.vaibhav.request.SearchDto;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private CitizenPlanRepository planRepo;

	@Override
	public List<String> getPlannames() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getPlanStatus() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CitizenPlan> search(SearchDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exportExcel() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pdfExport() {
		// TODO Auto-generated method stub
		return false;
	}

}
