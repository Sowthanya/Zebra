package app.testautomation.API;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import app.testautomation.api.lib.APIUtil;

public class CoronaVaccineAvailabilityCheck {
	
	String baseURI = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin";
	String pinCode = "560100";
	String date = "13-08-2021";
	APIUtil util = new APIUtil();
 
	@Test
  public void checkVaccineBasedOnZipCode() {
		
	    
		//Step1 : Get Vaccination availability details based on ZipCode and Date
		String response = util.executeGetCoronaVaccine(baseURI, pinCode, date);
		if(response.equals(""))
		{
			Assert.fail();
		}
		else
		{
			Reporter.log("The get request got executed successfully to find the Corona vaccine availability for Zipcode :" + pinCode + "and for the Date:" + date);
		}
		
		//Step 2 :Print the JSON response - the Vaccine detials
		
		String vaccineDetail = util.vaccineAvailabilityFromResponse(response);
		if(vaccineDetail.equals(""))
		{
			Assert.fail();
		}
		else
		{
			Reporter.log(vaccineDetail);
		}
		    
		   
  }
}
