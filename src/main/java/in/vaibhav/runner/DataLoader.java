package in.vaibhav.runner;



import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.vaibhav.entity.CitizenPlan;
import in.vaibhav.repo.CitizenPlanRepository;

// to load the data in db.
// Purpose of Application Runner to One Time to load data when application started.
@Component
public class DataLoader implements ApplicationRunner 
{
	@Autowired
	private CitizenPlanRepository repo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception
	{
		repo.deleteAll();
		
		// cash Plan data
		CitizenPlan c1 = new CitizenPlan();
		  c1.setCitizenName("Ankur");
		  c1.setGender("Male");
		  c1.setPlanName("Cash");
		  c1.setPlanStatus("Approved");
		  c1.setPlanStartDate(LocalDate.now());
		  c1.setPlanEndDate(LocalDate.now().plusMonths(6));
		  c1.setBenefitAmount(1000.00);
		  
		  CitizenPlan c2 = new CitizenPlan();
		  c2.setCitizenName("Ankit");
		  c2.setGender("Male");
		  c2.setPlanName("Cash");
		  c2.setPlanStatus("Denied");
		  c2.setDenialReason("Rental Income");
		  
		  CitizenPlan c3 = new CitizenPlan();
		  c3.setCitizenName("Akki");
		  c3.setGender("Male");
		  c3.setPlanName("Cash");
		  c3.setPlanStatus("terminted");
		  c3.setPlanStartDate(LocalDate.now().minusMonths(4));
		  c3.setPlanEndDate(LocalDate.now().plusMonths(6));
		  c3.setTerminatedDate(LocalDate.now());
		  c3.setTerminationReason("Employed");
		  
		  // Food Plan
		  CitizenPlan c4 = new CitizenPlan();
		  c4.setCitizenName("Anlita");
		  c4.setGender("Female");
		  c4.setPlanName("Food");
		  c4.setPlanStatus("Approved");
		  c4.setPlanStartDate(LocalDate.now());
		  c4.setPlanEndDate(LocalDate.now().plusMonths(6));
		  c4.setBenefitAmount(500.00);
		  
		  CitizenPlan c5 = new CitizenPlan();
		  c5.setCitizenName("Pooja");
		  c5.setGender("Female");
		  c5.setPlanName("Food");
		  c5.setPlanStatus("Denied");
		  c5.setDenialReason("Rental Income");
		  
		  CitizenPlan c6 = new CitizenPlan();
		  c6.setCitizenName("Chetan");
		  c6.setGender("Male");
		  c6.setPlanName("Food");
		  c6.setPlanStatus("terminted");
		  c6.setPlanStartDate(LocalDate.now().minusMonths(4));
		  c6.setPlanEndDate(LocalDate.now().plusMonths(6));
		  c6.setTerminatedDate(LocalDate.now());
		  c6.setTerminationReason("Employed");
		  
		  // Employement data
		  CitizenPlan c7 = new CitizenPlan();
		  c7.setCitizenName("Rose");
		  c7.setGender("Female");
		  c7.setPlanName("Employement");
		  c7.setPlanStatus("Approved");
		  c7.setPlanStartDate(LocalDate.now());
		  c7.setPlanEndDate(LocalDate.now().plusMonths(6));
		  c7.setBenefitAmount(2000.00);
		  
		  CitizenPlan c8 = new CitizenPlan();
		  c8.setCitizenName("Mark");
		  c8.setGender("Male");
		  c8.setPlanName("Employement");
		  c8.setPlanStatus("Denied");
		  c8.setDenialReason("Rental Income");
		  
		  CitizenPlan c9 = new CitizenPlan();
		  c9.setCitizenName("Ayan");
		  c9.setGender("Male");
		  c9.setPlanName("Empolyment");
		  c9.setPlanStatus("terminted");
		  c9.setPlanStartDate(LocalDate.now().minusMonths(4));
		  c9.setPlanEndDate(LocalDate.now().plusMonths(6));
		  c9.setTerminatedDate(LocalDate.now());
		  c9.setTerminationReason("Employed");
		  
		  // Health plan
		  CitizenPlan c10 = new CitizenPlan();
		  c10.setCitizenName("Sandra");
		  c10.setGender("Female");
		  c10.setPlanName("Health Insurance");
		  c10.setPlanStatus("Approved");
		  c10.setPlanStartDate(LocalDate.now());
		  c10.setPlanEndDate(LocalDate.now().plusMonths(6));
		  c10.setBenefitAmount(1500.00);
		  
		  CitizenPlan c11 = new CitizenPlan();
		  c11.setCitizenName("John");
		  c11.setGender("Male");
		  c11.setPlanName("Health Insurance");
		  c11.setPlanStatus("Denied");
		  c11.setDenialReason("Rental Income");
		  
		  CitizenPlan c12 = new CitizenPlan();
		  c12.setCitizenName("Sayan");
		  c12.setGender("Male");
		  c12.setPlanName("Health Insurance");
		  c12.setPlanStatus("terminted");
		  c12.setPlanStartDate(LocalDate.now().minusMonths(4));
		  c12.setPlanEndDate(LocalDate.now().plusMonths(6));
		  c12.setTerminatedDate(LocalDate.now());
		  c12.setTerminationReason("Employed");
		  
		  
		  List<CitizenPlan> list  = Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12);
		  repo.saveAll(list);
		 
		
	}
	
}
