package qa.auto.testScript;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import qa.auto.utility.PropertyFile;
import qa.auto.utility.TestUtils;

public class TestBase {

	protected static WebDriver driver;
	static PropertyFile pro = new PropertyFile();
	
	public static void initialize(String browserName) throws IOException
	{
		System.out.println("!!!!!!!!!!!!!!!");
		//String browserName = pro.prop().getProperty("Browser");
		System.out.println(browserName);

		if(browserName.equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver", "C:\\Workspace\\ExcelDrivenFramework\\resources\\chromedriver.exe");	
			driver = new ChromeDriver(); 
		}
		else if(browserName.equalsIgnoreCase("FF")){
			System.setProperty("webdriver.gecko.driver", "/Users/naveenkhunteta/Documents/SeleniumServer/geckodriver");	
			driver = new FirefoxDriver(); 
		}
		else if(browserName.equalsIgnoreCase("IE")){
			System.setProperty("webdriver.ie.driver", "C:\\Workspace\\ExcelDrivenFramework\\resources\\IEDriverServer.exe");	
			driver = new InternetExplorerDriver(); 
		}
		else
		{
			System.out.println("Incorrect "+browserName+" browser.");
		}
		
		
		  driver.manage().window().maximize(); driver.manage().deleteAllCookies();
		  driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT,
		  TimeUnit.SECONDS);
		  driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICIT_WAIT,
		  TimeUnit.SECONDS);
		 
	}	
	
	
	public static void getScreenshot(String fileName) throws IOException {
		
		TakesScreenshot screenshot = ((TakesScreenshot)driver);
		File SrcFile=screenshot.getScreenshotAs(OutputType.FILE);
		 File DestFile=new File("C:\\Workspace\\ExcelDrivenFramework\\Results\\Screenshot\\"+fileName);
         FileUtils.copyFile(SrcFile, DestFile);
         
		
	}
}
