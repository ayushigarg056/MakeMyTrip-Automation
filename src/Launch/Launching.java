package Launch;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import MainPackage.MainClass;

public class Launching extends MainClass{

	 WebDriver driver ; 
	 String chromeDriverPath ;
	 public Launching(WebDriver driver ,String chromeDriverPath)
	 {
		 this.driver=driver;
		 this.chromeDriverPath =chromeDriverPath ;
	
	 }
//	       launch browser
	    @Test
	    public WebDriver launchBrowser()
	    {
	        
	       //set the property
	       	System.setProperty("webdriver.chrome.driver",chromeDriverPath);
	  		driver = new ChromeDriver();
	  		
	  		//get the url
	  		driver.get(config.getProperty("url"));

	  		//maximize the screen
	  		driver.manage().window().maximize();	
	  		
	  		return driver;
	    }
	    
	   
}
