package qa.auto.pageOjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;

public class PageObjects {
	
	static String path = System.getProperty("user.dir");
	
			     
	public static By getObject(String ObjectName, String locatorType) throws	IOException{
			     
			
			         
			//find by xpath
			if(locatorType.equalsIgnoreCase("XPATH")){
			      return By.xpath(ObjectName); 
			// ObjectName is read and its value is returned
			}
			         
			//find by class
			else if(locatorType.equalsIgnoreCase("ID")){
			       return By.id(ObjectName);
			// ObjectName is read and its value is returned
			 
			}
			             
			//find by name
			else if(locatorType.equalsIgnoreCase("NAME")){
			       return By.name(ObjectName);
			// ObjectName is read and its value is returned
			 
			}
			
			else if(locatorType.equalsIgnoreCase("LINKTEXT")) {
				return By.linkText(ObjectName);
			}
			
			else if(locatorType.equalsIgnoreCase("TAGNAME")) {
				return By.tagName(ObjectName);
			}
			
			else if(locatorType.equalsIgnoreCase("CLASSNAME")) {
				return By.className(ObjectName);
			}
			
			else if(locatorType.equalsIgnoreCase("CSSSELECTOR")) {
				return By.cssSelector(ObjectName);
			}
			
			else if(locatorType.equalsIgnoreCase("PARTIALLINKTEXT")) {
				return By.partialLinkText(ObjectName);
			}
			return null;
			         
		}
}
