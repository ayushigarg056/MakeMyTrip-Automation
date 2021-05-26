package MainPackage;

import org.apache.log4j.xml.DOMConfigurator;
//import org.automationtesting.excelreport.Xl;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.*;
import java.util.*; 
import Launch.Launching;
import Utility.ExtentManager;

public class MainClass {

	public static ExtentReports extent;
    public static ExtentTest test; 
	public static WebDriver driver;
    public static Properties or = new Properties();
    public static Properties config = new Properties() ;
    public static String path = System.getProperty("user.dir") ;
    public static FileInputStream fs_Paths  ;
    public static FileInputStream fs_Config  ;
    public static Launching launch_browser;
//     Intializing the input parameters by default as per the assignment
    public static String uname="testplateiq@gmail.com", credentials="U", upassword="test@123",
    		triptype="RoundTrp",tripdeparture="Goa, India",tripreturn="Mumbai, India",travelclass="Economy"; 
    public static int adult=1,childern=1;
    public static WebDriverWait wait,wait1 ;
	
	//First method call when program is run
	//it will initialize the driver , testsuite, output excelfile etc
	@BeforeSuite
    public static void starting() throws Throwable
	{
//		 setting the extent Manager for logging
		 extent = ExtentManager.getInstance() ;
//		 if driver is null , then proceed
		if(driver == null)
		{
		    
		    //Accessing Properties from config File for launching werb url
		    fs_Config = new FileInputStream( path + "\\src\\Properties\\Config.properties");
            config= new Properties(System.getProperties());
            config.load(fs_Config);
            
            //Accessing xpaths from Properties File for Login and Book Flights
	        fs_Paths = new FileInputStream(path + "\\src\\Properties\\Paths.properties");
	        or= new Properties(System.getProperties());
	        or.load(fs_Paths);
	       
//	         Taking inputs if user wants on basis of yes and no
	        Scanner in = new Scanner(System.in);  
	        System.out.println("Do you want to enter the creadenetials (Yes/No)(Y/N) \n");
	        String enter= in.nextLine().trim();
//	       if user has selected yes, then take the login inputs 
	        if(enter.equalsIgnoreCase("Yes") ||enter.equalsIgnoreCase("Y")  )
	        {
	        	System.out.println("Login with Mobile number(M) or Username/Password(U): type : (M/U) \n");
//		      enter the either M or U  
		        credentials= in.nextLine().trim();
		        if(credentials.equalsIgnoreCase("M"))
		        {
//		        	 enter mobile number only
		        	System.out.print("Mobileumber : \t");
		        	uname = in.nextLine().trim();
		            System.out.println("phoneneumber"+ uname);
		        	
		        }else if(credentials.equalsIgnoreCase("U"))
		        {
//		        	 enter th eusername and password
	            System.out.print("Username : \t");
	            uname = in.nextLine().trim();
	            System.out.print("Password : \t");
	            upassword = in.nextLine().trim();
	            
	            System.out.println(uname+" "+upassword);
		        }
		        else
		        {
//		        	 in case wrong parameter has enterted , exit  
		            System.out.println("Wrong Input Parameter is passed !!");
		            System.exit(0);
		            
		        }
		        
	        }
	        //intialize the test object used for extent report
	        test = extent.startTest("LaunchBrowser");
//	        //intialize the chrome driver path
	        String chromeDriverPath = path + "\\src\\Executables\\chromedriver.exe" ;
	         
//	        //call the launch class.
	        launch_browser=new Launching(driver ,chromeDriverPath );
	        //intialize the driver
	        driver=launch_browser.launchBrowser();
//	         instailize waits
	        wait = new WebDriverWait(driver,500);
	        wait1 = new WebDriverWait(driver,50);
//	         get the title
	        System.out.println(driver.getTitle());
	  	    //verify the page title
	  		if(driver.getTitle().contains("MakeMyTrip"))
            {
//	  			 if page title present , log pass
	  			System.out.println("pass");
	  		  	test.log(LogStatus.PASS, "Navigated to the specified URL :" +config.getProperty("url"));
	  		  	clickthepage();
	  		}	
			else
			{
//				if page title is not present log fail and exit
		  		test.log(LogStatus.FAIL, "Test Failed :Unable to login to URL : "+config.getProperty("url")); 
		  		 driver.quit();
		  		 System.exit(0);
			}
	       
		}
	   
	}    

//	 End the test cases
	@AfterClass
	public static void endTest() throws Exception
	{
		extent.endTest(test);
    	extent.flush();
    		
	
	}
	
    @AfterSuite
    public static void ending() throws Exception
	{
//        quit the driver after suite is finished
    	driver.quit();
    	System.out.println("Test Cases Completed");
	}
  
    
  //Used to check whether is web element is present or not
    public static WebElement isElementPresent(WebDriver driver,String path) {
    	WebElement search=null;
        try {
        	 search= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
 			
            return search;
        } catch (NoSuchElementException e) {

            return search;

        }

    }
    
//     to click on page anywhere 
    public static void clickthepage() throws AWTException
    {
		 Robot robot = new Robot();
		 robot.mouseMove(630, 420); // move mouse point to specific location	
		 robot.delay(1500);        // delay is to make code wait for mentioned milliseconds before executing next step	
		 robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // press left click	
		 robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // release left click	
		 robot.delay(1500);

    }

    }
