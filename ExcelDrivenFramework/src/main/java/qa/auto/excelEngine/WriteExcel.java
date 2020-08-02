package qa.auto.excelEngine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {
	
	static XSSFFactory lPOIfs = null;
	
	@SuppressWarnings("unused")
	public static void createfile(String fileName, String TC,String Act,String exp) throws IOException {
	
		FileOutputStream out = new FileOutputStream(
			       new File("C:\\Workspace\\ExcelDrivenFramework\\Results\\"+fileName+".xlsx"),true);
			    	
	//Create blank workbook
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet spreadsheet = workbook.createSheet( " Employee Info ");
     
    //Read Sheet
    Map < String, Object[] > empinfo = new TreeMap < String, Object[] >();
    int d =0;
    
    File filenamefull= new File("C:\\Workspace\\ExcelDrivenFramework\\Results\\"+fileName+".xlsx");
    if(filenamefull.exists()) {
    	FileInputStream fis=new FileInputStream(filenamefull);
    	
        XSSFWorkbook workbook1 = new XSSFWorkbook(fis);
        XSSFSheet spreadsheet1 = workbook1.getSheet(" Employee Info ");
        int c = spreadsheet1.getLastRowNum();
        d = c+1;
        String e = String.valueOf(d);
        
        empinfo.put("3" , new Object[] {TC,Act,exp});
        
        int rowid = d;
        Set<String> keyid = empinfo.keySet();
        for (String key : keyid) {
            XSSFRow row = spreadsheet.createRow(rowid++);
            Object [] objectArr = empinfo.get(key);
            int cellid = 0;
            
            for (Object obj : objectArr){
               Cell cell = row.createCell(cellid++);
               cell.setCellValue((String)obj);
            }
         }
    }
    else {
    	System.out.println("File not exist.");
    	 empinfo.put( "1", new Object[] {"EMP ID", "EMP NAME", "DESIGNATION" });
    	 empinfo.put("2" , new Object[] {TC,Act,exp});
    	 
    	 int rowid=0;
    	 Set<String> keyid = empinfo.keySet();
    	 for (String key : keyid) {
    		 XSSFRow row = spreadsheet.createRow(rowid++);
    	       Object [] objectArr = empinfo.get(key);
    	       int cellid = 0;
    	       
    	       for (Object obj : objectArr){
    	          Cell cell = row.createCell(cellid++);
    	          cell.setCellValue((String)obj);
    	       }
    	    }
    }
    
   //Create row object
    XSSFRow row;
    
    

    //Iterate over data and write to sheet
   

    //Write the workbook in file system
    
    workbook.write(out);
    out.close();
    System.out.println("Writesheet.xlsx written successfully");
 }
	

	
	public void createandupdateFile() throws FileNotFoundException, IOException {
		
		
		try (InputStream inp = new FileInputStream("C:\\Workspace\\ExcelDrivenFramework\\Results\\Workook.xlsx")) {
			//InputStream inp = new FileInputStream("workbook.xlsx");
			    Workbook wb = WorkbookFactory.create(inp);
			    Sheet sheet = wb.getSheetAt(0);
			    sheet.getRow(4).createCell(5).setCellValue("sss");
			    Row row = sheet.getRow(0);
			    Cell cell = row.getCell(3);
			    if (cell == null)
			        cell = row.createCell(3);
			    cell.setCellType(CellType.STRING);
			    cell.setCellValue("a test");
			    // Write the output to a file
			    try (OutputStream fileOut = new FileOutputStream("workbook.xls")) {
			        wb.write(fileOut);
			    }
			}
	}
	
	public static void main(String args[]) throws FileNotFoundException, IOException
	{
		third();
	}
	
	public static void first() throws FileNotFoundException, IOException {
		String a ="1", b="Suite_1", c= "TC_1",d= "Home Vali 1",e= "Google", f="Google",
				g="PASS", h= "Screenshot_Hover",i="";
		
		WriteResultExcel.CreateResultExcel(a,b,c ,d ,e ,f ,g ,h ,i);
	}
	
	public static void sec() throws FileNotFoundException, IOException {
		String a ="2", b="Suite_2", c= "TC_2",d= "Home Vali 2",e= "Facebook", f="Facebook",
				g="PASS", h= "Screenshot_Hover",i="";
		
		WriteResultExcel.CreateResultExcel(a,b,c ,d ,e ,f ,g ,h ,i);
		}
	
	public static void third() throws FileNotFoundException, IOException {
		String a ="3", b="Suite_3", c= "TC_3",d= "Home Vali 3",e= "Twitter", f="Twitter",
				g="FAIL", h= "Screenshot_Hover",i="Incorrect Page Appeared";
		WriteResultExcel.CreateResultExcel(a,b,c ,d ,e ,f ,g ,h ,i);
	}

}
