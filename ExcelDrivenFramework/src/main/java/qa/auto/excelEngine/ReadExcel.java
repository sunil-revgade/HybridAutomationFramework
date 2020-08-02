package qa.auto.excelEngine;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import qa.auto.testScript.ExecuteTest;
import qa.auto.testScript.TestBase;
import qa.auto.utility.CreateLogs;
import qa.auto.utility.PropertyFile;

public class ReadExcel extends TestBase {

	PropertyFile pr = new PropertyFile();	
	ExecuteTest ex = new ExecuteTest();
	static String path = System.getProperty("user.dir");
	String Dir = path+"\\src\\main\\java\\qa\\auto\\dataEngine\\";
	
	static String sSrNo, sSuiteName,sTestCaseName, sTestSteps, sAction, sLocator,sLocatorType, sLocatorValue, sInput, sExpectedResult,sStepExecute   ; 
	String sOldTestCaseName="oldTC";


	public HashMap<String, String> getInitializationData() throws IOException {
		
		String excelFileName = pr.prop().getProperty("TestDataFileName");
		HashMap<String, String> hmEnv = new HashMap<String, String>();
		
		FileInputStream fis=new FileInputStream(Dir+excelFileName);
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		XSSFSheet sheet=workbook.getSheetAt(1);
		
		String sCol1 = sheet.getRow(0).getCell(0).getStringCellValue();
		String sCol2 = sheet.getRow(0).getCell(1).getStringCellValue();
		String sBrowserName = sheet.getRow(1).getCell(0).getStringCellValue();
		String sURL= sheet.getRow(1).getCell(1).getStringCellValue();
	
		hmEnv.put(sCol1, sBrowserName);
		hmEnv.put(sCol2, sURL);
		return hmEnv;
		
	}
	
	public void getData() throws IOException, AWTException, InterruptedException
	{
		String excelFileName = pr.prop().getProperty("TestDataFileName");
				HashMap<String, String> hm = new HashMap<String, String>();
				HashMap<String, String> hm_1 = new HashMap<String, String>();
				
				FileInputStream fis=new FileInputStream(Dir+excelFileName);
				XSSFWorkbook workbook=new XSSFWorkbook(fis);
				int sheets=workbook.getNumberOfSheets();
				XSSFSheet sheet=workbook.getSheetAt(0);
				
				for(int j=1 ; j<=sheet.getLastRowNum(); j++)
				{
					
				for(int i = 0;i<=15;i++) {
						
						String colmHeader = sheet.getRow(0).getCell(i).getStringCellValue();
						String r = sheet.getRow(j).getCell(i).getStringCellValue();
					System.out.println(r);
						hm.put(colmHeader, r);
						
				}
				
					sStepExecute=hm.get("StepExecute");
					
					if(sStepExecute.equalsIgnoreCase("Yes"))
					{
					sTestCaseName = hm.get("TestCaseName");
					
					if(sOldTestCaseName.equalsIgnoreCase(sTestCaseName))
					{
						System.out.println("Same Test Case "+sTestCaseName);
						ex.executeTest(hm);
						
					}
					else
					{
						if(!sOldTestCaseName.equals("oldTC")) {CreateLogs.endTestCase(sOldTestCaseName);}						
						System.out.println("New Test case started :"+sTestCaseName);
						CreateLogs.startTestCase(sTestCaseName);					
						ex.executeTest(hm);
					}
					
					sOldTestCaseName = sTestCaseName;
					}
					else
					{
						System.out.println("Step Execution is set to No....");
					}
					
				}
				
				
	}
	public static void main(String args[]) throws IOException, AWTException, InterruptedException
	{
		// getData("Suite_1");
		CreateLogs.startTestSuite("Sanity Test Suite");
		System.out.println(path);
		initialize("Chrome");
		ReadExcel re = new ReadExcel();
		re.getData();
		CreateLogs.endTestSuite("Sanity Test Suite",sTestCaseName);
	}
	

	}
