package in.vaibhav.service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.vaibhav.entity.CitizenPlan;
import in.vaibhav.repo.CitizenPlanRepository;
import in.vaibhav.request.SearchDto;
import in.vaibhav.util.EmailUtils;
import in.vaibhav.util.ExcelGenerator;
import in.vaibhav.util.PdfGenerator;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private ExcelGenerator excelGenerator;
	
	@Autowired
	private EmailUtils emailutils;
	
	@Autowired
	private PdfGenerator pdfGenerator;
	
	@Autowired
	private CitizenPlanRepository planRepo;

	@Override
	public List<String> getPlanNames()  
	{
		
		return planRepo.getPlanNames();
	}

	@Override
	public List<String> getPlanStatus() 
	{
		
		return planRepo.getPlanStatus();
	}

	
	
	@Override
	public List<CitizenPlan> search(SearchDto request) {
		
		CitizenPlan entity = new CitizenPlan();
		
		if(null!=request.getPlanName() && !"".equals(request.getPlanName()))
		{
			entity.setPlanName(request.getPlanName());
		}
		if(null!=request.getPlanStatus() && !"".equals(request.getPlanStatus()))
		{
			entity.setPlanStatus(request.getPlanStatus());
		}
		if(null!=request.getGender() && !"".equals(request.getGender()))
		{
			entity.setGender(request.getGender());
		}
		
		if(null !=request.getStartDate() && !"".equals(request.getStartDate()))
		{
			String startDate=request.getStartDate();
			DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(startDate, formatter);
			entity.setPlanStartDate(localDate); // add this to Query(which is taken care by Spring Data Jpa)
		}
		
		if(null !=request.getEndDate() && !"".equals(request.getEndDate()))
		{
			String endDate=request.getEndDate();
			DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(endDate, formatter);
			entity.setPlanEndDate(localDate);
		}
		 
		return planRepo.findAll(Example.of(entity)); //Example is used to prepare query Dynamic Search.
	}

	
	@Override
	public boolean exportExcel (HttpServletResponse  response) throws Exception
	{
		File f = new File("Plans.xls");
		
		List<CitizenPlan> plans=planRepo.findAll();
		excelGenerator.generate(response, plans,f);
		
		String subject="Test mail subject";
		String body="<h1>Test mail Body</h1>";
		String to="vaibhavsingh528@gmail.com";
		
		
		emailutils.sendEmail(subject, body, to,f);
		
		f.delete();
		
		 return true;
	}
 
	
	@Override
	public boolean pdfExport(HttpServletResponse  response) throws Exception 
	{

		File f = new File("Plans.pdf");
		List<CitizenPlan> plans=planRepo.findAll();
		 pdfGenerator.generate(response, plans,f);
		 
		  String subject="Test mail subject";
			String body="<h1>Test mail Body</h1>";
			String to="vaibhavsingh528@gmail.com";
			emailutils.sendEmail(subject, body, to,f);
			
			f.delete();
		return true;
	} 

}
