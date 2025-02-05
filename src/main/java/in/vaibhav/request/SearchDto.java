package in.vaibhav.request;

import java.time.LocalDate;

import lombok.Data;
 // purpose : This class perform the Form binding when data come from the jsp form or from the UI give it to Controller to perform the DB operations.
@Data
public class SearchDto 

{
	private String planName;
	private String planStatus;
	private String gender;
	private LocalDate startDate;
	private LocalDate endDate;
	

}
