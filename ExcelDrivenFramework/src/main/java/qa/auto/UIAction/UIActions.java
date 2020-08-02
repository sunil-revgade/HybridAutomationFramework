package qa.auto.UIAction;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import qa.auto.excelEngine.WriteExcel;
import qa.auto.excelEngine.WriteResultExcel;
import qa.auto.pageOjects.PageObjects;
import qa.auto.testScript.TestBase;
import qa.auto.utility.CreateLogs;

public class UIActions extends TestBase {


	public static void enter_URL(WebDriver driver,String TestData,String TestCaseName) throws IOException
	{
		driver.get(TestData);
		getScreenshot(TestCaseName+".png");
		//WriteExcel we = new WriteExcel();
		//WriteExcel.createfile("Test2",TestCaseName,TestData,"ad");
		CreateLogs.info("AUT URL Entered is :"+TestData);

	}

	public static void typeIn(WebDriver driver, String locatorValue, String locatorType, String testdata,String testcaseName) throws IOException 
	{
		CreateLogs.info("Entered Text Box Value :"+testdata);
		driver.findElement(PageObjects.getObject(locatorValue,locatorType)).clear();
		driver.findElement(PageObjects.getObject(locatorValue,locatorType)).sendKeys(testdata);
		getScreenshot(testcaseName+".png");
		
	}

	public static void click(WebDriver driver, String locatorValue, String locatorType, String stepName, String pageElement) throws IOException 
	{
		driver.findElement(PageObjects.getObject(locatorValue,locatorType)).click();
		CreateLogs.info("Click on :"+pageElement+" Step Nam is :"+stepName);
		
	}

	public static void validatePageTitle(String testdata) throws IOException 
	{
		String sTitle=driver.getTitle();
		System.out.println("Page Title is :"+sTitle);
		System.out.println("Expected:"+testdata);
		CreateLogs.info("Validating Page Title :"+sTitle);
		getScreenshot("Screenshot_title.png");
		if(sTitle.equalsIgnoreCase(testdata)) {
			System.out.println("Page Title Verified successfully");
			WriteResultExcel.CreateResultExcel("1", "Suite_1", "TC_VelidatePageTitle", "Page Title", testdata, sTitle, "PASS", "Screenshot_title", "");
		}
		else
		{
			System.out.println("Incorrect Page");
			WriteResultExcel.CreateResultExcel("1", "Suite_1", "TC_VelidatePageTitle", "Page Title", testdata, sTitle, "FAIL", "Screenshot_title", "Incorrect Page");
		}
	}

	public static void validatePageElement(WebDriver driver, String locatorValue, String locatorType) throws IOException 
	{
		boolean bPageElem = driver.findElement(PageObjects.getObject(locatorValue,locatorType)).isDisplayed();
		CreateLogs.info("Validating Page Element "+bPageElem);
		getScreenshot("Screenshot_ValiPageElem.png");
		
		if(bPageElem) {
			System.out.println("Page Element found");
			WriteResultExcel.CreateResultExcel("2", "Suite_1", "TC_ValidatePageElement", "Page Element", "Page Element should Displated", "Page Element Displated", "PASS", "Screenshot_ValiPageElem", "");
		}
		else
		{
			System.out.println("page Element not present");
			WriteResultExcel.CreateResultExcel("2", "Suite_1", "TC_ValidatePageElement", "Page Element", "Page Element should Displated", "Page Element not Displated", "FAIL", "Screenshot_ValiPageElem", "");
		}
	}

	public static void validateLabel(WebDriver driver, String locatorValue, String locatorType,String testdata) throws IOException 
	{

		String sElemLabel = driver.findElement(PageObjects.getObject(locatorValue,locatorType)).getText();
		System.out.println("element label :"+sElemLabel);
		System.out.println("Expected Label :"+testdata);
		CreateLogs.info("Validating Label :"+testdata);
		getScreenshot("Screenshot_Lagel.png");
		
		if(sElemLabel.equalsIgnoreCase(testdata)) {
			System.out.println("Label found");
			WriteResultExcel.CreateResultExcel("3", "Suite_1", "TC_ValidatePageLabel", "Page Label", "Exp : "+sElemLabel, "Act : "+testdata, "PASS", "Screenshot_Lagel", "");
		}
		else
		{
			System.out.println("label not found");
			WriteResultExcel.CreateResultExcel("3", "Suite_1", "TC_ValidatePageLabel", "Page Label", "Exp : "+sElemLabel, "Act : "+testdata, "FAIL", "Screenshot_Lagel", "Incorrect Label");
		}
	}

	public static void hover(WebDriver driver, String locatorValue, String locatorType) throws IOException {

		Actions act = new Actions(driver);

		WebElement targetElemt = driver.findElement(PageObjects.getObject(locatorValue,locatorType));
		act.moveToElement(targetElemt).build().perform();
		getScreenshot("Hover.png");

	}

	public static void hoverAndClick(WebDriver driver, String locatorValue, String locatorType) throws IOException {

		Actions act = new Actions(driver);

		WebElement targetElemt = driver.findElement(PageObjects.getObject(locatorValue,locatorType));
		act.moveToElement(targetElemt).build().perform();
		CreateLogs.info("Hover on Menu");

	}

	public static void selectRecordFromTable(WebDriver driver, String locatorValue, String locatorType, String testdata, int iPerPageCnt) {

		String[] sRowsCols = locatorValue.split(";");
		String sTableColms = sRowsCols[0];
		String sTableRow = sRowsCols[1];
		String sPaginateWebElement = sRowsCols[2];
		//	int iPerPageCnt = 5;
		boolean bFoundRecord = false;

		List<WebElement> lTableCol = driver.findElements(By.xpath(sTableColms));
		List<WebElement> lTableRow = driver.findElements(By.xpath(sTableRow));

		System.out.println("Total No of Colms "+lTableCol.size());
		System.out.println("Total No of Rows "+lTableRow.size());
		int iTotalRec =  lTableRow.size();
		int iPages = iTotalRec/iPerPageCnt;
		System.out.println(iPages+1);

		for(int j = 1;j<=iTotalRec;j++) {

			for(int i=1;i<=lTableCol.size();i++) {

				String a = sTableColms+"[";
				String b = i+"]";
				String sSpTableColms = a+b;
				String sComH = driver.findElement(By.xpath(sSpTableColms)).getText();

				String sRowElem = sTableRow+"["+j+"]/td["+i+"]";
				//System.out.println(sRowElem);
				WebElement eRowData = driver.findElement(By.xpath(sRowElem));
				System.out.println("Row No :"+j+" Column name :"+sComH+" Columne Data is "+eRowData.getText());

				if(eRowData.getText().equalsIgnoreCase(testdata))
				{
					eRowData.click();	
					CreateLogs.info("");
					System.out.println("Found Teat data "+testdata);
					bFoundRecord = true;
					break;
				}

				if(eRowData.getText().equals("")) {
					System.out.println("Paginate");
					paginateToPageNo(iPages,sPaginateWebElement);
					iPages++;
					System.out.println("Row No :"+j+" Column name :"+sComH+" Columne Data is "+eRowData.getText());
				}

				if(bFoundRecord)
				{
					break;
				}
			}
			if(bFoundRecord)
			{
				break;
			}
		}

	}

	public static void handlePopUp(WebDriver driver, String PopupInpute,String AlertType, String alertaction) {

		Alert alert = driver.switchTo().alert();

		if(AlertType.equalsIgnoreCase("alert"))
		{
			alert.accept();
		}
		else if(AlertType.equalsIgnoreCase("confirmation")) {
			if(alertaction.equalsIgnoreCase("Accept")) {
				alert.accept();
			}else if(alertaction.equalsIgnoreCase("Dismiss")){
				alert.dismiss();
			}
			else {
				System.out.println("Please Specify alertaction!!!!!");
			}
		}
		else if(AlertType.equalsIgnoreCase("prompt")){
			alert.sendKeys(PopupInpute);
			alert.accept();
		}
		else
		{
			System.out.println("please Specify Alert Type.");
		}
	}


	public static boolean paginateToPageNo(int iPageNo,String sPagenateWelElem) {

		int iPageNum =  iPageNo +1;
		String sPageElem =sPagenateWelElem+"["+iPageNum+"]/a";
		WebElement ePageEl = driver.findElement(By.xpath(sPageElem));

		ePageEl.click();

		return true;
	}

	public static void CalendarSelectDate(String date) throws InterruptedException {

		String[] lDate = date.split("/");
		String day = lDate[0];
		String month = lDate[1];
		int imonth = Integer.parseInt(month);
		String sYear = lDate[2].trim();

		System.out.println(day+"  "+month+" "+sYear);
		String sMonth=null;

		switch(imonth) {

		case 1:
			sMonth = "Jan";
			break;

		case 2:
			sMonth = "Feb";
			break;
		case 3:
			sMonth = "Mar";
			break;
		case 4:
			sMonth = "Apr";
			break;
		case 5:
			sMonth = "May";
			break;
		case 6:
			sMonth = "Jun";
			break;
		case 7:
			sMonth = "Jul";
			break;
		case 8:
			sMonth = "Aug";
			break;
		case 9:
			sMonth = "Sep";
			break;
		case 10:
			sMonth = "Oct";
			break;
		case 11:
			sMonth = "Nov";
			break;
		case 12:
			sMonth = "Dec";
			break;
		}

		System.out.println(sMonth);

		driver.findElement(By.xpath("//*[@id=\"treemenu\"]/li/ul/li[2]/a")).click();
		driver.findElement(By.xpath("")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("")).click();

		driver.findElement(By.xpath("")).click();;
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[3]/div[2]/table/thead/tr[2]/th[2]")).click();;


		for(int i =0;i<=4;i++) {
			List<WebElement> years = driver.findElements(By.xpath("/html/body/div[3]/div[3]/table/tbody/tr/td/span"));		
			boolean found = false;

			for(int j=1;j<= years.size();j++)
			{

				String path1 = "/html/body/div[3]/div[3]/table/tbody/tr/td/span[";
				String path2= "]";
				String fullpath = path1+j+path2;

				WebElement t = driver.findElement(By.xpath(fullpath));		
				System.out.println("Years are :"+t.getText());


				if(t.getText().equalsIgnoreCase(sYear)) { t.click();
				System.out.println("Year Found"); 
				found = true;
				break;
				} 
				if(found) {break;}


			}
			if(found) {break;}
			driver.findElement(By.xpath(
					"/html/body/div[3]/div[3]/table/thead/tr[2]/th[1]")).click(); 
			//	/html/body/div[3]/div[3]/table/thead/tr[2]/th[1]
		}



		List<WebElement> lmonths = driver.findElements(By.xpath(
				"/html/body/div[3]/div[2]/table/tbody/tr/td/span"));

		for(WebElement elm : lmonths) {

			boolean bfound1 = false;
			System.out.println("Months are :"+elm.getText());

			if(elm.getText().equalsIgnoreCase(sMonth)) { elm.click();
			System.out.println("Month Found");
			bfound1 = true;
			break;

			}
			if(bfound1) {break;}
		}

		String sWeek  = "/html/body/div[3]/div[1]/table/tbody/tr";
		String sDay = "/html/body/div[3]/div[1]/table/tbody/tr[1]/td";


		for(int p=1;p<=6;p++) {
			boolean found2 = false;
			String sWeekPath1 = sWeek+"["+p+"]";
			System.out.println(sWeekPath1);

			for(int q = 1;q<=7;q++) {


				String sDayPath = sWeekPath1+"/td["+q+"]";

				//  System.out.println(sDayPath);

				WebElement dayElem = driver.findElement(By.xpath(sDayPath));
				System.out.println("Date in moth "+dayElem.getText());
				System.out.println("Attribute is :"+dayElem.getAttribute("class"));

				if(dayElem.getText().equals(day) && dayElem.getAttribute("class").equals("day"))
				{
					System.out.println("Selected date is :"+day);
					dayElem.click();
					found2 = true;
					break;
				}
				if(found2) {break;}
			}

			if(found2) {break;}
		}

	}


	public static void DragAndDrop(WebDriver driver, String locatorValue, String locatorType) throws IOException {

		String[] locatoyValues = locatorValue.split(";");
		String sSourceVal = locatoyValues[0];
		String sDestiVal = locatoyValues[1];

		Actions act = new Actions(driver);

		WebElement source = driver.findElement(PageObjects.getObject(sSourceVal,locatorType));
		WebElement target = driver.findElement(PageObjects.getObject(sDestiVal,locatorType));

		act.clickAndHold(source).moveToElement(target).release().build().perform();
		validateDragAndDrop(driver, locatorValue, locatorType);
		CreateLogs.info("Drag and Drop Perfomed Successfully");
		}
	
	public static void validateDragAndDrop(WebDriver driver, String locatorValue, String locatorType) throws IOException {

		String[] locatoyValues = locatorValue.split(";");
		String sSourceVal = locatoyValues[0];
		String sDestiVal = locatoyValues[1];


		boolean bDropPlace = driver.findElement(PageObjects.getObject(sDestiVal,locatorType)).isSelected();
		getScreenshot("Screenshot_DragDrop.png");
		if(bDropPlace) {
			System.out.println("Drag and Drop done successfully");
			WriteResultExcel.CreateResultExcel("1", "Suite_1", "TC_ValidateDragAndDrop", "Drop Location", "Drag Locaton is Enabled", "Drag Locaton is Enabled", "PASS", "Screenshot_DragDrop", "");
		}
		else
		{
			System.out.println("Drag and Drop not completed successfully.");
			WriteResultExcel.CreateResultExcel("1", "Suite_1", "TC_ValidateDragAndDrop", "Drop Location", "Drag Locaton is Enabled", "Drag Locaton is not Enabled", "FAIL", "Screenshot_DragDrop", "Incorrect Page");
		}
	}

	public static void windowHandle(WebDriver driver) {


		String parentHadle = driver.getWindowHandle();

		Set<String> winHandle = driver.getWindowHandles();
		Iterator<String> I1= winHandle.iterator();

		while(I1.hasNext())
		{
			String child_window=I1.next();
			if(!parentHadle.equals(child_window))
			{
				driver.switchTo().window(child_window);
				System.out.println("Child Window Title is :"+driver.getTitle());
				driver.manage().window().maximize();
			}
		}
	}
	public static void robot_arrowDownEnter() throws AWTException, InterruptedException, IOException
	{
		Robot rb = new Robot();
		rb.keyPress(KeyEvent.VK_DOWN);
		Thread.sleep(1000);
		rb.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(2000);
		getScreenshot("ArrowDown.png");
		rb.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
		rb.keyRelease(KeyEvent.VK_ENTER);

	}

	public static void selectDropDownValue(WebDriver driver, String locatorValue, String locatorType, String SelectBy, String selectValue) throws IOException {

		WebElement sDropDown = driver.findElement(PageObjects.getObject(locatorValue,locatorType));

		Select select = new Select(sDropDown);

		if(SelectBy.equalsIgnoreCase("VisibleText"))
		{
			select.selectByVisibleText(selectValue);
		}else if (SelectBy.equalsIgnoreCase("index"))
		{
			select.selectByIndex(Integer.parseInt(selectValue));
		}else if (SelectBy.equalsIgnoreCase("value"))
		{
			select.selectByValue(selectValue);
		}
		else {
			System.out.println("Incorrect SelectBy Value :"+SelectBy);
		}
	}

	public void iFrame(WebDriver driver, String frameIdentifier) {

		driver.switchTo().frame(frameIdentifier);

	}

	public static void iFrame(WebDriver driver, String locatorValue, String locatorType,String frameIdentifier) throws IOException {
		WebElement e1 = driver.findElement(PageObjects.getObject(locatorValue,locatorType)); 
		driver.switchTo().frame(e1);

	}

	public static void doubleClickOn(WebDriver driver) throws IOException {

		Actions act = new Actions(driver);
		act.doubleClick();

	}

	public static void doubleClickOn(WebDriver driver, String locatorValue, String locatorType) throws IOException {

		WebElement e1 = driver.findElement(PageObjects.getObject(locatorValue,locatorType)); 

		Actions act = new Actions(driver);
		act.doubleClick(e1);

	}

	public static void rightClickOn(WebDriver driver, String locatorValue, String locatorType) throws IOException {

		WebElement e1 = driver.findElement(PageObjects.getObject(locatorValue,locatorType)); 

		Actions act = new Actions(driver);
		act.contextClick(e1).build().perform();
	}
}

