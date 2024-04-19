package com.demo123;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpController {
	
	@PostMapping(value = "/empSave" )
	public  ResponseEntity<?>   saveEmp(@RequestBody Map<String, String> data) throws Exception {
		
		System.out.println(data);
		
		Map map=new LinkedHashMap();
	
		map.put("code", "200");
		map.put("status", true);
		map.put("error", "successfully saved");
			
				
				return ResponseEntity.status(200).body(map);
				
			}
	
	
	@PostMapping(value = "/texCal" )
	public  ResponseEntity<?>   getTexCal(@RequestBody Map<String, String> data) throws Exception {
		
		
		LocalDate dateOfJoining = null;
		
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-mm-yyyy");

		try {
			//dateOfJoining = LocalDate.parse(data.get("doj").toString(), pattern);
		    System.out.println(dateOfJoining); 
		} catch (DateTimeParseException e) {
		  
		}
		
		 // LocalDate dateOfJoining = LocalDate.of(new BigDecimal(data.get("y")), new BigDecimal(data.get("m")), new BigDecimal(data.get("dd")));

		  dateOfJoining = LocalDate.of(2023, 5, 16); 
		//  LocalDate dateOfJoining = LocalDate.of(Integer.parseInt(data.get("y").toString()), Integer.parseInt(data.get("m").toString()), Integer.parseInt(data.get("dd").toString()));
		
		double texBal = TaxCalculator.calculateTax(Double.parseDouble(data.get("sal").toString()), dateOfJoining);
		
		System.out.println(data);
		
		Map map=new LinkedHashMap();
	
		map.put("code", "200");
		map.put("sal_total", texBal);
	
			
				
				return ResponseEntity.status(200).body(map);
				
			}
	

}
