package qa.auto.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFile {
	
	FileInputStream fis;
	Properties pro;
	public Properties prop() throws IOException {
	
		
		fis = new FileInputStream("C:\\Workspace\\ExcelDrivenFramework\\src\\main\\java\\qa\\auto\\config\\EnvironementSetup.properties");
		pro = new Properties();
		pro.load(fis);
		return pro;

	}
	
}
