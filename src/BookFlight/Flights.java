package BookFlight;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import MainPackage.MainClass;

//Lets book the flights
public class Flights extends MainClass{
//	 Assigning some values
	public static int departuredate ,returndate ,year;
	public static String month="";
	public static HashMap<String,Integer> months=new HashMap<String,Integer>();
	
	 @Test(priority=1)
		public void bookflight()
		{
//  intializing test case for book flight in extent Report
		 test = extent.startTest("BookFlight");

			
		 try {
//			 select the flights
			 isElementPresent(driver,or.getProperty("flights")).click();
//			  select the type of flight (according assignment selecting Roundtrip by default)
			 if(triptype.equalsIgnoreCase("onewaytrip"))
			 {
				 isElementPresent(driver,or.getProperty("onewaytrip")).click();
			 }else if(triptype.equalsIgnoreCase("roundtrip"))
			 {
				 isElementPresent(driver,or.getProperty("roundtrip")).click();
			 }else if(triptype.equalsIgnoreCase("multitrip"))
			 { 
				 isElementPresent(driver,or.getProperty("multitrip")).click();
			 }
//			select Starting Point of journey (Goa,India)  
			 isElementPresent(driver,or.getProperty("from")).click();
			 isElementPresent(driver,or.getProperty("addfrom")).sendKeys(tripdeparture);
			 isElementPresent(driver,or.getProperty("selectfrom")+tripdeparture+or.getProperty("common")).click();
//			  select Destion (Mumbai, India) 
			 isElementPresent(driver,or.getProperty("addto")).sendKeys(tripreturn);
			 isElementPresent(driver,or.getProperty("selectto")+tripreturn+or.getProperty("common")).click();
		 
//			 in case any error after selecting Destination (TO)  log and and stop the test case 
			 try {
				 if(driver.findElement(By.xpath(or.getProperty("departureserror"))).isDisplayed())
				 {
					String error= driver.findElement(By.xpath(or.getProperty("departureserrortext"))).getText();
					test.log(LogStatus.FAIL	, error);
					return;
				 }} catch (Exception e) {System.out.println("No error occured while selecting Destination");}
			
//			 find the departure date as 2 day from today and return date three days from today , as mentioned assignment
			 finddates();
			 
//			 get the departure date and return date
			 String dep =  String.valueOf(month) +" "+String.valueOf(departuredate) +" "+String.valueOf(year) ;
			 String ret =  String.valueOf(month) +" "+String.valueOf(returndate) +" "+String.valueOf(year) ;
			 
//			 click departure date
			 isElementPresent(driver,or.getProperty("selectdeparturedate")).click();
			 isElementPresent(driver,or.getProperty("selectdate1")+dep+or.getProperty("selectdate2")).click();
			 
//			  click the return date
			 isElementPresent(driver,or.getProperty("selectreturndate")).click();
			 isElementPresent(driver,or.getProperty("selectdate1")+ret+or.getProperty("selectdate2")).click();
//			 1 adult and 1 childern is assigned
//			  adding 1 adult
			 isElementPresent(driver,or.getProperty("selectclass")).click();
			 isElementPresent(driver,or.getProperty("selectadult")+adult+or.getProperty("common")).click();
			 
//			  adding one childern
			 isElementPresent(driver,or.getProperty("selectchildern")+childern+or.getProperty("common")).click();
			 
//			 select class (by default selecting economy)
			 
			 if(travelclass.equalsIgnoreCase("Economy"))
				 isElementPresent(driver,or.getProperty("selecteconomy")).click();
			 else if(travelclass.equalsIgnoreCase("PremiumEconomy"))
				 isElementPresent(driver,or.getProperty("selectpremiun")).click();
			 else 
				 isElementPresent(driver,or.getProperty("selectbusiness")).click();
//			  click apply
			 isElementPresent(driver,or.getProperty("selectapply")).click();
			 
//			  search the flights
			 isElementPresent(driver,or.getProperty("search")).click();
//			  if there is no flights availabale , log the error and stop the test case
			 try {
				 if(driver.findElement(By.xpath(or.getProperty("noflights"))).isDisplayed())
				 {
					String error= driver.findElement(By.xpath(or.getProperty("noflights"))).getText();
					test.log(LogStatus.FAIL	, error);
					return;
				 }} catch (Exception e) {System.out.println("Flights are found , no error");}
			
//	 			force wait for 10 seconds , till the page is loaded
	 			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
	 			
//			select the departure second 
			 try {
				 	isElementPresent(driver,or.getProperty("selectdepartures")).click();
				 	} catch (Exception e) {
				 		test.log(LogStatus.FAIL	, "Unable to find second flight for departures");
					return;}
			
//			 select the return second
			 
			 try {
				 	isElementPresent(driver,or.getProperty("selectreturns")).click();
				  } catch (Exception e) {
					 test.log(LogStatus.FAIL, "Unable to find second flight for Return");
					return;}
			
//			  book the flights
			 isElementPresent(driver,or.getProperty("booknow")).click();

//			  click continue
			 isElementPresent(driver,or.getProperty("continue")).click();
			 
//			  a new window is pop so switch between th windows
			 String parentWindowHandle = driver.getWindowHandle();
			 
//         	a new window will open switch to the driver to another window 
			 System.out.println(driver.getWindowHandles());
 			// Switch to new window opened
 			for(String winHandle : driver.getWindowHandles())
 			{
 				System.out.println(winHandle);
 			    driver.switchTo().window(winHandle);
 			}
 			
// 			force wait for 50 seconds , till the page is loaded
 			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
 			
// 			 in case any error while booking the flights
 			 try {
				 if(driver.findElement(By.xpath(or.getProperty("noflightssearchedafterbooking"))).isDisplayed())
				 {
					String error= driver.findElement(By.xpath(or.getProperty("noflightssearchedafterbooking"))).getText();
					test.log(LogStatus.FAIL	, error);
					return;
				 }} catch (Exception e) {System.out.println("No error while booking flights");};
				 
//			 get the total and print the total and log the same
			String total= isElementPresent(driver,or.getProperty("totalamount")).getText();
			System.out.println("total"+total.substring(2));
			test.log(LogStatus.PASS, "Flight is booked successfully . Total "+total.substring(2));
			
		} catch (Exception e) {
			test.log(LogStatus.FAIL	, e.getLocalizedMessage());
		}
		 
		}
	 
//	  find the departure date 2 day after and returen date 3 day after the current dtae
	private void finddates() {
//		 get the current date
	    SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MMM-dd");
		//Setting the time zone
		dateTimeInGMT.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		String date=dateTimeInGMT.format(new Date());
		System.out.println(date);
//		 split the current date in array
		ArrayList<String> dates=new ArrayList<String>(Arrays.asList(date.split("-")));
		
//		 assign the months last value to month variable
		addmonths();
		
//		 intialize the departuredate,returndate,year
		departuredate=Integer.parseInt(dates.get(2));
		returndate=Integer.parseInt(dates.get(2));
		year=Integer.parseInt(dates.get(0));
		
//		Iterator the month and and assign the values
		 Iterator<Map.Entry<String, Integer>> itr_value = months.entrySet().iterator();
		 	while(itr_value.hasNext())
		        {
		        	 Map.Entry<String, Integer> entry = itr_value.next();
//		        	 If month is found in hash map
		            if(entry.getKey().equalsIgnoreCase(dates.get(1)))
					{
//		            	 if month is not February , assign dearture date and return date accordingly
		            	if(!entry.getKey().equalsIgnoreCase("February"))
		            	{
		            		departuredate=departuredate+2;
		            		returndate=returndate+3;
            				month=entry.getKey();
//            				if current month is of 30 days or if current month is of 31 days
            				if((returndate==33 && entry.getValue()==30) || (returndate==34 && entry.getValue()==31)) 
            				{
            					departuredate =2;
            					returndate=3;
//            					 Increase the month value
            					itr_value.hasNext();
            					month=entry.getKey();
            					break;
            					
            				}
            				else if((returndate==32 && entry.getValue()==31)) 
            				{
//                				if current month is of 31 days and current is 30 
            					departuredate =1;
            					returndate=2;

//           					 Increase the month value
           					itr_value.hasNext();
           					month=entry.getKey();
           					break;
            					
            				}
		            	}
		            	else if((year%4)==0 && entry.getKey().equalsIgnoreCase("February"))
		            	{
//		            		 if it is february and leap year , process date accordingly
		            		departuredate=departuredate+2;
		            		returndate=returndate+3;
            				month=entry.getKey();
            				if((returndate==31 && entry.getValue()==28)) 
            				{
            					departuredate =2;
            					returndate=3;

//           					 Increase the month value
           					itr_value.hasNext();
           					month=entry.getKey();
           					break;
            					
            				}
            				
		            	}
		            	else if((year%4)!=0 && entry.getKey().equalsIgnoreCase("February"))
		            	{
//		            		 if it is february and not leap year , process date accordingly
		            		departuredate=departuredate+2;
		            		returndate=returndate+3;
            				month=entry.getKey();
            				if((returndate==32 && entry.getValue()==29)) 
            				{
            					departuredate =2;
            					returndate=3;

//           					 Increase the month value
           					itr_value.hasNext();
           					month=entry.getKey();
           					break;
            					
            				}
            				else if((returndate==31 && entry.getValue()==28)) 
            				{
            					departuredate =1;
            					returndate=2;

//           					 Increase the month value
           					itr_value.hasNext();
           					month=entry.getKey();
           					break;
            					
            				}
		            	}
					}
		            					
		        }
	}
	private void addmonths() {
		months.put("January", 31);
		months.put("Februray", 29);
		months.put("March", 31);
		months.put("April", 30);
		months.put("May", 31);
		months.put("June", 30);
		months.put("July", 31);
		months.put("August", 31);
		months.put("September", 30);
		months.put("October", 31);
		months.put("November", 30);
		months.put("December", 31);
		
		
	}	
}
