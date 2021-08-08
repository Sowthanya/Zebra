package app.testautomation.api.lib;

import org.json.JSONArray;
import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIUtil {

public String executeGetCoronaVaccine(String baseURI, String pincode, String date)
{
       try
       {
		RestAssured.baseURI = baseURI;
		RequestSpecification request = RestAssured.given();
          
		Response response = request.queryParam("pincode", pincode)
									.queryParam("date", date)
									.get();
		
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		return responseBody;
       }catch(Exception e)
       {
    	   e.printStackTrace();
    	   return "";
       }
		
}

public String vaccineAvailabilityFromResponse(String responseBody)
{     
	try
	{
	String htmlStr1 = "<html><head><style>table, th, td {border: 1px solid black;}</style></head><body><table><tr><th> Area</th><th> medical center Name </th><th> Address </th><th> Vaccine Name </th><th> Available Date </th></tr>";
	String htmlStr2 = "</table></body></html>";

		 JSONArray jsonArray = new JSONArray(responseBody.replace("{\"sessions\":", "").replace("}]}", "}]"));
		 String vaccineCenters = "";
		    for (int i = 0; i < jsonArray.length(); i++) {

		        JSONObject jsonObject = jsonArray.getJSONObject(i);
               
		         vaccineCenters = vaccineCenters +  "<tr><td>" + jsonObject.getString("block_name") + "</td>" 
		        						+ "<td>" + jsonObject.getString("name") + "</td>"
		        						+ "<td>" + jsonObject.getString("address") + "</td>"
		        						+ "<td>" + jsonObject.getString("vaccine") + "</td>"
		        						+ "<td>" + jsonObject.getString("date") + "</td></tr>";
		        								    }
		    
		    return htmlStr1 + vaccineCenters + htmlStr2;
	}catch(Exception e)
	{
		e.printStackTrace();
		return "";
	}
	}

}
