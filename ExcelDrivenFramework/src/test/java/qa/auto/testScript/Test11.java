package qa.auto.testScript;

import java.awt.AWTException;
import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import qa.auto.excelEngine.ReadExcel;

public class Test11 extends TestBase{
	
	ReadExcel re = new ReadExcel();

  @BeforeSuite
  public void SetupEnvAndSmokeTest() throws IOException
  {
	 HashMap<String, String>hm = re.getInitializationData();
	 String sBrowserName = hm.get("Browser");
	 System.out.println(sBrowserName);
	  initialize(sBrowserName);
  }
 
  @Test
  public void loginTest() throws IOException, AWTException, InterruptedException {
	  System.out.println("Test");
	  re.getData();
  }
}
