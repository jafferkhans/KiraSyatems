package com.kirasystems.seleniumtests;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class KiraSystemsSanityCheck {
    private ChromeDriver browser1;
    
	@BeforeTest //Do this before the tests start
	void setup()
	{
		System.setProperty("webdriver.chrome.driver","chromedriver_mac_os_x");
        ChromeOptions chromeOptions = new ChromeOptions();
        browser1 = new ChromeDriver(chromeOptions); 
	}
	
	@Test(enabled=true)
	void searchKiraSystems()
	{
		browser1.navigate().to("https://www.google.ca/");
		//All xpaths can also be saved in properties file since they can be used in other testcases too.
		browser1.findElement(By.xpath("//input[@title='Search']")).sendKeys("Kira Systems");
		browser1.findElement(By.xpath("//input[@title='Search']")).sendKeys(Keys.RETURN);
		String url = browser1.findElements(By.xpath("//div[@class='g']/link")).get(0).getAttribute("href");
		Assert.assertEquals(url,"https://kirasystems.com/");		
	}
	
	@AfterMethod //Executes after each Test method
	 void screenShot(ITestResult result)
	 {
		if(ITestResult.FAILURE==result.getStatus())
		{
			try{
				TakesScreenshot screenshot=(TakesScreenshot)browser1;
				File src=screenshot.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(src, new File("/tmp/"+result.getName()+".png"));
				System.out.println("Exception seen. Image : "+result.getName()+".png created in /tmp folder");
			}
			catch (Exception e){
				System.out.println("Snapshot failed due to : "+e.getMessage());
			}
		}
	 }
	 
	
	@AfterClass
	void tearDown()
	{
		//Close and quit the chromedriver instance.
		browser1.close();
		browser1.quit();
	}

}
