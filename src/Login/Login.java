package Login;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.awt.AWTException;
import java.util.concurrent.TimeUnit;

import com.relevantcodes.extentreports.LogStatus;
import MainPackage.MainClass;


public class Login extends MainClass{
	
	
	@Test(priority=0)
	public void loging() throws Throwable
	{
		 test = extent.startTest("Login");
		 
//		     click Login Button
			 isElementPresent(driver,or.getProperty("clicklogin")).click();
//			  Click the Personal Account
			 isElementPresent(driver,or.getProperty("personalaccount")).click();
//			 If credentials are chosen Username / Password 
			 if(!credentials.equalsIgnoreCase("M"))
			 {
//				  Enter username
				 isElementPresent(driver,or.getProperty("username")).sendKeys(uname);
//				  Press Tab from username
				 driver.findElement(By.xpath(or.getProperty("username"))).sendKeys(Keys.TAB);
				 Boolean usererror=true;
//				  Check if there is any error after entering username
				try {usererror = getusernameerror();} catch (Exception e) {}
//				if error is present , then stop the test case , log it and return
				 if(!usererror)
					 return;
				 else 
					 test.log(LogStatus.INFO,"Username is entered successfully");
//				  if there is no error from username , then click continue
				 isElementPresent(driver,or.getProperty("continuelogin")).click();
//				enter the password
				 isElementPresent(driver,or.getProperty("password")).sendKeys(upassword);
//				  click login
				 isElementPresent(driver,or.getProperty("login")).click();
				 boolean passworderror=true;
//				  Check if there is any error after entering password
				 try {passworderror = getmobileerror();} catch (Exception e) {}
//					if error is present , then stop the test case , log it and return
				 if(!passworderror)
					 return;
				 else 
					 test.log(LogStatus.INFO,"Password is entered successfully");
//				  Enter the Random OTP genarated
				 isElementPresent(driver,or.getProperty("otp")).sendKeys("123456");
//				  click otp login
				 isElementPresent(driver,or.getProperty("otplogin")).click();
//				  Check if there is any error after entering otp				  
				 boolean otperror=true;
				 try {otperror = getotperror();} catch (Exception e) {}
//					if error is present , then stop the test case , log it and return
				 if(!otperror)
					 return;
				 else
				 {
//						if error is not present , then log as passed

					 test.log(LogStatus.PASS, "Login Successfull");
					 System.out.println("Login Successfull");
					
				 }
			 }
			 else
			 {
//				 If credentials are chosen as Mobile Number

//				  Enter username
				 isElementPresent(driver,or.getProperty("username")).sendKeys(uname);

//				  Press Tab from username
				 driver.findElement(By.xpath(or.getProperty("username"))).sendKeys(Keys.TAB);
				 Boolean usererror=true;

//				  Check if there is any error after entering username
				 try {usererror = getmobileerror();} catch (Exception e) {}

//					if error is present , then stop the test case , log it and return
				 if(!usererror)
					 return;
				 else 
					 test.log(LogStatus.INFO,"Username is entered successfully");
				 isElementPresent(driver,or.getProperty("continuelogin")).click();

//				  Enter the Random OTP 
				 isElementPresent(driver,or.getProperty("otpmobile")).sendKeys("123456");;
// 				 clcik otp
				 isElementPresent(driver,or.getProperty("otpmobileverify")).click();
				 boolean otperror=true;

//				  Check if there is any error after entering otp
				 try {otperror = getotperror();} catch (Exception e) {}

//					if error is present , then stop the test case , log it and return
				 System.out.println("otperror"+otperror);
				 if(!otperror)
					 return;
				 else
				 {
//					 if error is not present , then log as passed
					 test.log(LogStatus.PASS, "Login Successfull");
					 System.out.println("Login Successfull");
					
				 }
			 }
//			 click the page anywhere to close the login window
			 clickthepage();
		
	}

// capturing the error genearated  while processing through userbnames
	public  Boolean getusernameerror() throws AWTException {
		try {
//			try to find the error 
			 WebElement found=driver.findElement(By.xpath(or.getProperty("error")));
//			  if error is dislplayed then log the error 
			 boolean error=found.isDisplayed();
			 if(error)
			 {
//				  get the text from error and log it as failed and return false(as test case failed)
				 Thread.sleep(2000);
				 String errortext= isElementPresent(driver,or.getProperty("errorusertext")).getText();
				 test.log(LogStatus.FAIL, errortext);
				 System.out.println(errortext);
//				 click the page anywhere to close the login window
				 clickthepage();
				return false; 
			 }else
			  return true;
			 
		} catch (InterruptedException e) {
//			 no error while processing login 
			System.out.println("Unable to capture any error");
		}
		return true; 
	}
	
	// capturing the error generated  while processing through mobile number

	public  Boolean getmobileerror() throws AWTException {
		try {
//			try to catch the error
			WebElement found=driver.findElement(By.xpath(or.getProperty("error")));

//			  if error is displayed then log the error 
			boolean error=found.isDisplayed();
			
			 System.out.println("user error :"+error);
			 if(error)
			 {
//				  get the text from error and log it as failed and return false(as test case failed)

				 Thread.sleep(2000);
				 String errortext= isElementPresent(driver,or.getProperty("errortext")).getText();
				 test.log(LogStatus.FAIL, errortext);
				 System.out.println(errortext);
//				 click the page anywhere to close the login window
				 clickthepage();
				return false; 
			 }else
			  return true;
			 
		} catch (InterruptedException e) {
//			 no error while processing login 
			System.out.println("Unable to capture any error");

		}
		return true; 
	}

	public  Boolean getotperror() throws AWTException {
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
//			try to catch  the error
			WebElement found=driver.findElement(By.xpath(or.getProperty("otperror")));

//			  if error is displayed then log the error 
			boolean error=found.isDisplayed();
			
			 System.out.println("user error :"+error);
			 if(error)
			 {
//				  get the text from error and log it as failed and return false(as test case failed)

				 Thread.sleep(2000);
				 String errortext= isElementPresent(driver,or.getProperty("otperror")).getText();
				 test.log(LogStatus.FAIL,errortext);
				 System.out.println(errortext);
//				 click the page anywhere to close the login window
				 clickthepage();
				return false; 
			 }else
			  return true;
			 
		} catch (InterruptedException e) {
//			 no error while processing login 
			System.out.println("Unable to capture any error");

		}
		return true; 
	}
}
