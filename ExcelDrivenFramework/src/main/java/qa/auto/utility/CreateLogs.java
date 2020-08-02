package qa.auto.utility;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jdk.internal.org.jline.utils.Log;

import org.apache.logging.log4j.*;

public class CreateLogs {
	

	 
	 private static Logger Log = LogManager.getLogger(CreateLogs.class.getName());//

	 // This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite
	 
     
	 public static void startTestSuite(String sTestSuiteName){
	 
	 Log.info("****************************************************************************************");
	 
	 Log.info("****************************************************************************************");
	 
	 Log.info("$$$$$$$$$$$$$$$$$$$$$                 Started    		     $$$$$$$$$$$$$$$$$$");
	 Log.info("$$$$$$$$$$$$$$$$$$$$$             "+sTestSuiteName+ "                   $$$$$$$$$$$$$$$$$$");
	 
	 Log.info("****************************************************************************************");
	 
	 Log.info("****************************************************************************************");
	 
	 }
	 
	 //This is to print log for the ending of the test case
	 
	 public static void endTestSuite(String sTestSuiteName,String sTestCaseName){
	 
	 Log.info("                                                                                        ");
	 Log.info("Test Case Ended : "+sTestCaseName);
	 Log.info("****************************************************************************************");
	 Log.info("XXXXXXXXXXXXXXXXXXXXXXX             "+sTestSuiteName+"            XXXXXXXXXXXXXXXXXXXXXX");
	 Log.info("XXXXXXXXXXXXXXXXXXXXXXX             "+"-E---N---D-"+"                  XXXXXXXXXXXXXXXXXXXXXX");	 
	 }
	 
	 
	 public static void startTestCase(String sTestCaseName)
	 {
		 Log.info("Test Case Started : "+sTestCaseName);
		 Log.info("                                                                                        ");
		 
	 }
	 
	 public static void endTestCase(String sTestCaseName)
	 {
		 Log.info("                                                                                        ");
		 Log.info("Test Case Ended : "+sTestCaseName);
		 Log.info("****************************************************************************************");
	 }
	 
	 
	 // Need to create these methods, so that they can be called  
	 
	 public static void info(String message) {
	 
	 Log.info(message);
	 
	 }
	 
	 public static void warn(String message) {
	 
	    Log.warn(message);
	 
	 }
	 
	 public static void error(String message) {
	 
	    Log.error(message);
	 
	 }
	 
	 public static void fatal(String message) {
	 
	    Log.fatal(message);
	 
	 }
	 
	 public static void debug(String message) {
	 
	    Log.debug(message);
	 
	 }

	
	
	public static void main(String args[])
	{
		startTestCase("TestCase 2");
		info("info");
		warn("warining");
	//	error("e");
		endTestCase("TestCase 2");
	}
	
}
