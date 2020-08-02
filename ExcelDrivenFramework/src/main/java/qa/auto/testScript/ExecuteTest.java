package qa.auto.testScript;

import java.awt.AWTException;
import java.io.IOException;
import java.util.HashMap;

import qa.auto.UIAction.UIActions;

public class ExecuteTest extends TestBase {

	String sSuiteName, sTestCaseName, sTestSteps, sAction, sPageElement, sLocatorType, sLocatorValue, sInput, sExpectedResult, 
			sPopUpType, sPopUpAction, sPopUpInput, sIsWindowHandle, sIsiFrame, sSelectBy, sPerPageRecords;
	
	public void executeTest(HashMap<String, String> hm) throws IOException, AWTException, InterruptedException  {
		
		System.out.println(hm.get("Sr.No"));
		System.out.println(hm.get("SuiteName"));
		System.out.println(hm.get("TestCaseName"));
		System.out.println(hm.get("TestSteps"));
		System.out.println(hm.get("Action"));
		System.out.println(hm.get("PageElement"));
		System.out.println(hm.get("LocatorType"));
		System.out.println(hm.get("LocatorValue"));
		System.out.println(hm.get("Input"));
		System.out.println(hm.get("ExpectedResult")); 
		System.out.println(hm.get("PopUpType")); 
		System.out.println(hm.get("PopUpAction")); 
		System.out.println(hm.get("PopUpInput")); 
		System.out.println(hm.get("IsWindowHandle"));
		System.out.println(hm.get("IsiFrame"));
		System.out.println(hm.get("SelectBy"));
		System.out.println(hm.get("PerPageRecords"));
		
		sSuiteName = 	hm.get("SuiteName"); 
		sTestCaseName= hm.get("TestCaseName");
		sTestSteps = hm.get("TestSteps");
		sAction = hm.get("Action");
		sPageElement = hm.get("PageElement");
		sLocatorType = hm.get("LocatorType");
		sLocatorValue = hm.get("LocatorValue");
		sInput = hm.get("Input");
		sExpectedResult = hm.get("ExpectedResult"); 
		sPopUpType = hm.get("PopUpType"); 
		sPopUpAction = hm.get("PopUpAction"); 
		sPopUpInput = hm.get("PopUpInpute");
		sIsWindowHandle = hm.get("IsWindowHandle");
		sIsiFrame = hm.get("IsiFrame");
		sSelectBy = hm.get("SelectBy");
		sPerPageRecords = hm.get("PerPageRecords");
		
		String actionType = hm.get("Action");
		switch(actionType) {
		
		case "OpenURL":
			UIActions.enter_URL(driver, sInput, sTestSteps);
			break;
			
		case "SetText":
			UIActions.typeIn(driver, sLocatorValue, sLocatorType, sInput,sTestSteps);
			break;
			
		
		case "Click":
			UIActions.click(driver, sLocatorValue, sLocatorType,sTestSteps,sPageElement);
			break;
			
		case "SelectDropDown":
			UIActions.selectDropDownValue(driver, sLocatorValue, sLocatorType,sSelectBy, sInput);
			break;
			
		case "Hover":
			UIActions.hover(driver, sLocatorValue, sLocatorType);;
			break;
			
		case "DragAndDrop":
			UIActions.DragAndDrop(driver,sLocatorValue, sLocatorType);
			break;
			
		case "PopUpHandle":
			UIActions.handlePopUp(driver,sPopUpInput,sPopUpType, sPopUpAction );
			break;
			
		case "SwitchToWindow":
			UIActions.windowHandle(driver);
			break;
			
		case "KeyBordAction":
			UIActions.robot_arrowDownEnter();
			break;		
			
		case "SelectValueFromTable":
			UIActions.selectRecordFromTable(driver, sLocatorValue, sLocatorType, sExpectedResult,Integer.parseInt(sPerPageRecords));
			break;
		
		case "iFrameHandle":
			UIActions.iFrame(driver, sLocatorValue, sLocatorType, sInput);
			break;
			
		case "DoubleClick":
			UIActions.doubleClickOn(driver, sLocatorValue, sLocatorType);
			break;
		case "RightClick":
			UIActions.rightClickOn(driver, sLocatorValue, sLocatorType);
			break;
			
		case "Validate:PageTitle":
			UIActions.validatePageTitle(sExpectedResult);
			break;
			
		case "Validate:PageElement":
			UIActions.validatePageElement(driver, sLocatorValue, sLocatorType);
			break;
	
		case "Validate:Label":
			UIActions.validateLabel(driver, sLocatorValue, sLocatorType, sExpectedResult);
			break;
	
		}
		
	}
	
}
