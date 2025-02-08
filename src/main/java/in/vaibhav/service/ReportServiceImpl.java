package in.vaibhav.service;

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
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService {
	
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
		
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("plans-data");
        Row headerRow = sheet.createRow(0);
		
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Citizen Name");
		headerRow.createCell(2).setCellValue("Plan Name");
		headerRow.createCell(3).setCellValue("Plan Status");
		headerRow.createCell(4).setCellValue("Plan Start Date");
		headerRow.createCell(5).setCellValue("Plan End Date");
		headerRow.createCell(6).setCellValue("Benefit Amt");
		 
		List<CitizenPlan> records = planRepo.findAll();
		int dataRowIndex=1;
		
		for(CitizenPlan plan:records)
		{
			Row dataRow= sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(plan.getCitizenId());
			dataRow.createCell(1).setCellValue(plan.getCitizenName());
			dataRow.createCell(2).setCellValue(plan.getPlanName());
			dataRow.createCell(3).setCellValue(plan.getPlanStatus());
			dataRow.createCell(4).setCellValue(plan.getPlanStartDate());
			dataRow.createCell(5).setCellValue(plan.getPlanEndDate() +"");
			dataRow.createCell(6).setCellValue(plan.getBenefitAmount()+ "");
			
			if(null != plan.getPlanStartDate())
			{
				dataRow.createCell(4).setCellValue(plan.getPlanStartDate());
			}else {
				dataRow.createCell(4).setCellValue("N/A");
			
			if(null != plan.getPlanEndDate())
				{
					dataRow.createCell(5).setCellValue(plan.getPlanEndDate());
				}else {
					dataRow.createCell(5).setCellValue("N/A");
				
			if(null != plan.getBenefitAmount())
			{
				dataRow.createCell(6).setCellValue(plan.getBenefitAmount());
			}else {
				dataRow.createCell(6).setCellValue("N/A");
			}
				} 
			}
			dataRowIndex++;	
	}
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		
		 return true;
	}
 
	
	
	@Override
	public boolean pdfExport(HttpServletResponse  response) throws Exception 
	{
		Document doc= new Document(PageSize.A4);
		PdfWriter.getInstance(doc, response.getOutputStream());
		doc.open();
		
		Font fontTiltle=FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(20);
		
		Paragraph p =new Paragraph("Citizen Plans Info",fontTiltle);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		 doc.add(p);
		 
		 PdfPTable table=new PdfPTable(6);
		 table.setSpacingBefore(8);
		 
		 table.addCell("Id");
		 table.addCell("Citizen Name");
		 table.addCell("Plan Name");
		 table.addCell("Paln Status");
		 table.addCell("Start Date");
		 table.addCell("End date");
		 
		 List<CitizenPlan> plans = planRepo.findAll();
		 
		  for(CitizenPlan plan: plans)
		  {
			  table.addCell(String.valueOf(plan.getCitizenId()));
			  table.addCell(plan.getCitizenName());
			  table.addCell(plan.getPlanName());
			  table.addCell(plan.getPlanStatus());
			  table.addCell(plan.getPlanStartDate() +"");
			  table.addCell(plan.getPlanEndDate() + "");
		  }
		 doc.add(table);
		 doc.close();
		  
		
		return false;
	}

}
