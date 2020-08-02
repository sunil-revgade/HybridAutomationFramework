package qa.auto.excelEngine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteResultExcel {
	
	
	static Properties prp ;
	static String sResultFile;
	static String sScreeshotDir;
	
	
	@SuppressWarnings("resource")
	public static void CreateResultExcel(String SrNo,String TestSuite,String TestCase,String Steps,String ExpectedResult,String ActualResult,String Status,String Screenshots,String Comments ) throws FileNotFoundException, IOException {
		
		File propFile = new File("C:\\Workspace\\ExcelDrivenFramework\\src\\main\\java\\qa\\auto\\config\\EnvironementSetup.properties");
		FileInputStream fin = new FileInputStream(propFile);
		prp = new Properties();
		prp.load(fin);
		
		String sInputeFilePart1 = prp.getProperty("ResultFileDirectory");
		String sInputeFilePart2 = prp.getProperty("ResultFileName");
		sResultFile = sInputeFilePart1 + sInputeFilePart2;
		System.out.println("Input File Details :" +sResultFile);
		
		sScreeshotDir = prp.getProperty("ScreenshotDirectory");  
      
		String titles[] = {"Sr. No","Test Suite","Test Case","Step","Expected Result","Actual Result","Status", "Screenshot","Comment"};
		String data[][] = {{SrNo,TestSuite,TestCase ,Steps ,ExpectedResult ,ActualResult ,Status ,Screenshots ,Comments}};
		
        int rowCount = 0;
        int rndNumber = 0;
        try
        {
            File xlsxFile = new File(sResultFile);

            Workbook wb = null;
            Sheet sheet = null;
            Row row = null;

            if(xlsxFile.exists()){
                FileInputStream fileInputStream = new FileInputStream(xlsxFile);
                wb = new XSSFWorkbook(fileInputStream);
                sheet = wb.getSheet("Result");
                rowCount = sheet.getPhysicalNumberOfRows();
                CreationHelper createHelper = wb.getCreationHelper();

                System.err.println("Writing on existing file ....");
                for(int i = 0 ; i < data.length ; i++){        
                    row = sheet.createRow(rowCount++);
                    System.out.println(rndNumber);
                    for(int c = 0 ; c < titles.length ; c++){
                        Cell cell = row.createCell(c);      
                      
                        CellStyle cellStyle1 = wb.createCellStyle();
                        Font font1 = wb.createFont();
                         
                       System.out.println(data[rndNumber][c]);
                       if(data[rndNumber][c].equalsIgnoreCase("PASS")){
                       	 cell.setCellValue(data[rndNumber][c]);
	                       	// font1.setColor(IndexedColors.BRIGHT_GREEN.getIndex());
	                       	 //cellStyle1.setFont(font1);
	                        
	                         cellStyle1.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		                       	cellStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		                        cell.setCellStyle(cellStyle1);
	                    }
                      else if(data[rndNumber][c].equalsIgnoreCase("FAIL")) {
                   	 cell.setCellValue(data[rndNumber][c]);
                      //	 font1.setColor(IndexedColors.RED1.getIndex());
                      //  cellStyle1.setFont(font1);
                      
                        cellStyle1.setFillForegroundColor(IndexedColors.RED.getIndex());
                       	cellStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                        cell.setCellStyle(cellStyle1);
                      }
                      else if(data[rndNumber][c].contains("Screenshot_")){
                    	  cell.setCellValue(data[rndNumber][c]);
                    	  XSSFHyperlink link = (XSSFHyperlink)createHelper.createHyperlink(HyperlinkType.FILE);
                    	  link.setAddress(sScreeshotDir+data[rndNumber][c]+".png");
                          cell.setHyperlink(link);
                      
                      }
                      else {
                    	  cell.setCellValue(data[rndNumber][c]);
                        	cellStyle1.setBorderBottom(BorderStyle.THIN);
                         	cellStyle1.setBorderTop(BorderStyle.THIN);
                         	cellStyle1.setBorderLeft(BorderStyle.THIN);
                         	cellStyle1.setBorderRight(BorderStyle.THIN);
                          cell.setCellStyle(cellStyle1);
                          }
                    }
                    rndNumber++;
                }
                fileInputStream.close();
                System.err.println(xlsxFile.delete());                                
            }else{
                System.err.println("Creating excel file ....");

                wb = new XSSFWorkbook();
                sheet = wb.createSheet("Result");
                row = sheet.createRow(rowCount++);
                CreationHelper createHelper = wb.getCreationHelper();
                
                CellStyle cellStyle = wb.createCellStyle();
                Font font = wb.createFont();

                font.setBold(true);
                font.setFontName("Arial");
                cellStyle.setFont(font);
            	cellStyle.setBorderBottom(BorderStyle.THIN);
               	cellStyle.setBorderTop(BorderStyle.THIN);
               	cellStyle.setBorderLeft(BorderStyle.THIN);
               	cellStyle.setBorderRight(BorderStyle.THIN);
                
            
                for(int i = 0 ; i < titles.length ; i++){
                    Cell cell = row.createCell(i);
                    cell.setCellValue(titles[i]);
                    cell.setCellStyle(cellStyle);
                }

                for(int i = 0 ; i < data.length ; i++){        
                    row = sheet.createRow(rowCount++);
                   
                    for(int c = 0 ; c < titles.length ; c++){
                    	 CellStyle cellStyle1 = wb.createCellStyle();
                         Font font1 = wb.createFont();
                        Cell cell = row.createCell(c);    
                        System.out.println(data[rndNumber][c]);
                        if(data[rndNumber][c].equalsIgnoreCase("PASS")){
                        	 cell.setCellValue(data[rndNumber][c]);
	                       	// font1.setColor(IndexedColors.BRIGHT_GREEN1.getIndex());
	                       	// cellStyle1.setFont(font1);
	                       	 
	                       	cellStyle1.setFillForegroundColor(IndexedColors.GREEN.getIndex());
	                       	cellStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	                       	 
	                       	cellStyle1.setBorderBottom(BorderStyle.THIN);
	                       	cellStyle1.setBorderTop(BorderStyle.THIN);
	                       	cellStyle1.setBorderLeft(BorderStyle.THIN);
	                       	cellStyle1.setBorderRight(BorderStyle.THIN);
	                         cell.setCellStyle(cellStyle1);
	                    }
                       else if(data[rndNumber][c].equalsIgnoreCase("FAIL")) {
                    	 cell.setCellValue(data[rndNumber][c]);
							/*
							 * font1.setColor(IndexedColors.RED1.getIndex()); cellStyle1.setFont(font1);
							 */
                       	cellStyle1.setFillForegroundColor(IndexedColors.RED.getIndex());
                       	cellStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                     	cellStyle1.setBorderBottom(BorderStyle.THIN);
                       	cellStyle1.setBorderTop(BorderStyle.THIN);
                       	cellStyle1.setBorderLeft(BorderStyle.THIN);
                       	cellStyle1.setBorderRight(BorderStyle.THIN);
                         cell.setCellStyle(cellStyle1);
                      	
                       } else if(data[rndNumber][c].contains("Screenshot_")){
                    	 	
                     	  cell.setCellValue(data[rndNumber][c]);
                     	  XSSFHyperlink link = (XSSFHyperlink)createHelper.createHyperlink(HyperlinkType.FILE);
                     	  link.setAddress(sScreeshotDir+data[rndNumber][c]+".png");
                           
                     	  cell.setHyperlink(link);
                     	  
                           cellStyle1.setBorderBottom(BorderStyle.THIN);
                          	cellStyle1.setBorderTop(BorderStyle.THIN);
                          	cellStyle1.setBorderLeft(BorderStyle.THIN);
                          	cellStyle1.setBorderRight(BorderStyle.THIN);
                          	cell.setCellStyle(cellStyle1);
                      
                       }
                       else {
                    cell.setCellValue(data[rndNumber][c]);
                  	cellStyle1.setBorderBottom(BorderStyle.THIN);
                   	cellStyle1.setBorderTop(BorderStyle.THIN);
                   	cellStyle1.setBorderLeft(BorderStyle.THIN);
                   	cellStyle1.setBorderRight(BorderStyle.THIN);
                    cell.setCellStyle(cellStyle1);
                       }
                    }
                    rndNumber++;
                }
            }        

            
            FileOutputStream fileOut;
            try 
            {
                fileOut = new FileOutputStream(sResultFile,true);
                wb.write(fileOut);
                fileOut.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Last row: " + sheet.getLastRowNum() + ", " + sheet.getPhysicalNumberOfRows());            
        }catch(java.lang.IllegalArgumentException illegalArgumentException){
            System.err.println(illegalArgumentException.getMessage());
        }            
    }

}
