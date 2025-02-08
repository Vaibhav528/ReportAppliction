package in.vaibhav.util;

import java.io.*;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.vaibhav.entity.CitizenPlan;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class PdfGenerator 
{
	public void generate(HttpServletResponse response, List<CitizenPlan> plans, File f) throws Exception
	{
		Document doc= new Document(PageSize.A4);
		PdfWriter.getInstance(doc, response.getOutputStream());
		PdfWriter.getInstance(doc, new FileOutputStream(f));
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
	}
	 

}
