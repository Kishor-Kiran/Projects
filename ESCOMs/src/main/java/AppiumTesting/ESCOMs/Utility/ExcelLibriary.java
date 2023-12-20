package AppiumTesting.ESCOMs.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelLibriary {
	
	

	
	public static String path = System.getProperty("user.dir") + "/TestData/TestData.xlsx";

	// public  String path;
	 public FileInputStream fis = null; 
	 public FileOutputStream fileOut = null;
	 private XSSFWorkbook workbook = null;
	 private XSSFSheet sheet = null;
	 private XSSFRow row = null;
	 private XSSFCell cell = null;

	 public ExcelLibriary() {

	  // this.path=path;
	  try {
	   fis = new FileInputStream(path);
	   workbook = new XSSFWorkbook(fis);
	   sheet = workbook.getSheetAt(0);
	   fis.close();
	  } catch (Exception e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }
	 }

	 public ExcelLibriary(String path) {

	  this.path = path;
	  try {
	   fis = new FileInputStream(path);
	   workbook = new XSSFWorkbook(fis);
	   sheet = workbook.getSheetAt(0);
	   fis.close();
	  } catch (Exception e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }
	 }

	 // returns the row count in a sheet
	  public int getRowCount(String sheetName){
	   int index = workbook.getSheetIndex(sheetName);
	   if(index==-1)
	    return 1;
	   else{
	   sheet = workbook.getSheetAt(index);
	   int number=sheet.getLastRowNum();
	   return number;
	   }
	   
	  }
	 
	  
	  public String getCellData(String sheetName,String colName,int rowNum){
	   try{
		 
	    if(rowNum <=0)
	     return "";
	   
	   int index = workbook.getSheetIndex(sheetName);
	   int col_Num=-1;
	   if(index==-1)
	    return "";
	   
	   sheet = workbook.getSheetAt(index);
	   row=sheet.getRow(0);
       
	   for(int i=0;i<row.getLastCellNum();i++){
		  
	    System.out.println(row.getCell(i).getStringCellValue().trim());
	    if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
	     col_Num=i;
	    break;
	   }
	   if(col_Num==-1)
	    return "";
	   
	   sheet = workbook.getSheetAt(index);
	   row = sheet.getRow(rowNum-1);
	   if(row==null)
	    return "";
	   cell = row.getCell(col_Num);
	   
	   if(cell==null)
	    return "";
	   //System.out.println(cell.getCellType());
	   if(cell.getCellType().name().equals("STRING"))
	      return cell.getStringCellValue();
	   else if(cell.getCellType().name().equals("NUMERIC") || cell.getCellType().name().equals("FORMULA") ){
	      
	      String cellText  = String.valueOf(cell.getNumericCellValue());
	      if (HSSFDateUtil.isCellDateFormatted(cell)) {
	              // format in form of M/D/YY
	       double d = cell.getNumericCellValue();

	       
	       Calendar cal =Calendar.getInstance();
	       cal.setTime(HSSFDateUtil.getJavaDate(d));
	               cellText =
	                (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
	              cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
	                         cal.get(Calendar.MONTH)+1 + "/" + 
	                         cellText;
	              
	              //System.out.println(cellText);

	            }

	      
	      
	      return cellText;
	     } if(cell.getCellType().name().equals("BLANK"))
	         return ""; 
	    
	     else 
	      return String.valueOf(cell.getBooleanCellValue());
	   
	   }
	   catch(Exception e){
	    
	    e.printStackTrace();
	    return "row "+rowNum+" or column "+colName +" does not exist in xls";
	   }
	  }
	 
	  // returns the data from a cell
	  public String getCellData(String sheetName,int colNum,int rowNum){
	   try{
		  
	    if(rowNum <=0)
	     return "";
	   
	   int index = workbook.getSheetIndex(sheetName);

	   if(index==-1)
	    return "";
	   
	  
	   sheet = workbook.getSheetAt(index);
	   row = sheet.getRow(rowNum);
	   if(row==null)
	    return "";
	   cell = row.getCell(colNum);
	   if(cell==null)
	    return "";
	   
	    if(cell.getCellType().name().equals("STRING"))
	     return cell.getStringCellValue();
	    else if(cell.getCellType().name().equals("NUMERIC") || cell.getCellType().name().equals("FORMULA") ){
	     
	     String cellText  = String.valueOf(cell.getNumericCellValue());
	     if (HSSFDateUtil.isCellDateFormatted(cell)) {
	             // format in form of M/D/YY
	      double d = cell.getNumericCellValue();

	      Calendar cal =Calendar.getInstance();
	      cal.setTime(HSSFDateUtil.getJavaDate(d));
	              cellText =
	               (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
	             cellText = cal.get(Calendar.MONTH)+1 + "/" +
	                        cal.get(Calendar.DAY_OF_MONTH) + "/" +
	                        cellText;
	             
	             System.out.println(cellText);

	           }

	     
	     
	     return cellText;
	    }
	     if(cell.getCellType().name().equals("BLANK"))
	        return "";
	    else 
	     return String.valueOf(cell.getBooleanCellValue());
	   }
	   catch(Exception e){
	    
	    e.printStackTrace();
	    return "row "+rowNum+" or column "+colNum +" does not exist  in xls";
	   }
	  }
	  
	  // returns true if data is set successfully else false
	  public Boolean setCellData(String sheetName,int colName,int rowNum, String data){
	   try{
		   System.out.println("this is the sheet name : " +sheetName);
		   System.out.println("this is the sheet name : " +colName);
		   System.out.println("this is the rowNum : " +rowNum);
	   fis = new FileInputStream(path); 
	   workbook = new XSSFWorkbook(fis);

	   if(rowNum<=0)
	    return false;
	   
	   int index = workbook.getSheetIndex(sheetName);
   int colNum=0;
	   if(index==-1)
	    return false;
	   
	   
	   sheet = workbook.getSheetAt(index);
	   

	   row=sheet.getRow(0);
	   for(int i=0;i<row.getLastCellNum()-1;i++){
		   System.out.println(row.getCell(i).getStringCellValue().trim());
	    if(row.getCell(i).getStringCellValue().trim().equals(colName))
	     colNum=i;
	   }
	   if(colNum==-1)
	    return false;

	   sheet.autoSizeColumn(colNum); 
	   row = sheet.getRow(rowNum-1);
	   if (row == null)
	    row = sheet.createRow(rowNum-1);
	   
	   cell = row.getCell(colNum); 
	   if (cell == null)
	          cell = row.createCell(colNum);

	      
	      CellStyle cs = workbook.createCellStyle();
	      cs.setWrapText(true);
	      cell.setCellStyle(cs);
	      workbook.getSheet(sheetName).getRow(rowNum).createCell(colNum).setCellValue(data);
	      cell.setCellValue(data);
//	      System.out.println("this is NewexcelLibarey data" + data);
	      fileOut = new FileOutputStream(path);

	   workbook.write(fileOut);

	      fileOut.close(); 

	   }
	   catch(Exception e){
	    e.printStackTrace();
	    return false;
	   }
	   return true;
	  }
	 
	  // returns true if sheet is created successfully else false
		public String writeExcel(String sheetName,String cellvalue,int row1, int col) throws Exception {
			File file= new File(path);
			
			FileInputStream fis=new FileInputStream(file);
			
			XSSFWorkbook wb=new XSSFWorkbook(fis);
			XSSFSheet sheet=wb.getSheet(sheetName);
			
			 try{
				   fis = new FileInputStream(path); 
				   workbook = new XSSFWorkbook(fis);

				   if(row1<=0)
				    return "";
				   
				   int index = workbook.getSheetIndex(sheetName);
			   int colNum=0;
				   if(index==-1)
				    return "";
				   
				   
				   sheet = workbook.getSheetAt(index);
				   

				   row=sheet.getRow(0);
				   for(int i=0;i<row.getLastCellNum()-1;i++){
					   System.out.println(row.getCell(i).getStringCellValue().trim());
				    if(row.getCell(i).getStringCellValue().trim().equals(col))
				     colNum=i;
				   }
				   if(colNum==-1)
				    return "";

				   sheet.autoSizeColumn(colNum); 
				   row = sheet.getRow(row1-1);
				   if (row == null)
				    row = sheet.createRow(row1-1);
				   
				   cell = row.getCell(colNum); 
				   if (cell == null)
				          cell = row.createCell(colNum);
				   sheet.getRow(row1).createCell(col).setCellValue(cellvalue);
			
			FileOutputStream fos=new FileOutputStream(new File(path));
			
			wb.write(fos);
			wb.close();
			 }catch (Exception e) {
				// TODO: handle exception
			}
		return cellvalue;
		}
	  public boolean addSheet(String  sheetname){  
	   
	   FileOutputStream fileOut;
	   try {
	     workbook.createSheet(sheetname); 
	     fileOut = new FileOutputStream(path);
	     workbook.write(fileOut);
	        fileOut.close();      
	   } catch (Exception e) {   
	    e.printStackTrace();
	    return false;
	   }
	   return true;
	  }
	  
	  // returns true if sheet is removed successfully else false if sheet does not exist
	  public boolean removeSheet(String sheetName){  
	   int index = workbook.getSheetIndex(sheetName);
	   if(index==-1)
	    return false;
	   
	   FileOutputStream fileOut;
	   try {
	    workbook.removeSheetAt(index);
	    fileOut = new FileOutputStream(path);
	    workbook.write(fileOut);
	       fileOut.close();      
	   } catch (Exception e) {   
	    e.printStackTrace();
	    return false;
	   }
	   return true;
	  }
	  
	  // returns true if column is created successfully
	  public boolean addColumn(String sheetName,String colName){
	   //System.out.println("**************addColumn*********************");
	   
	   try{    
	    fis = new FileInputStream(path); 
	    workbook = new XSSFWorkbook(fis);
	    int index = workbook.getSheetIndex(sheetName);
	    if(index==-1)
	     return false;
	    
	   XSSFCellStyle style = workbook.createCellStyle();
	   //style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
	   //style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	   
	   sheet=workbook.getSheetAt(index);
	   
	   row = sheet.getRow(0);
	   if (row == null)
	    row = sheet.createRow(0);
	   
	   //cell = row.getCell(); 
	   //if (cell == null)
	   //System.out.println(row.getLastCellNum());
	   if(row.getLastCellNum() == -1)
	    cell = row.createCell(0);
	   else
	    cell = row.createCell(row.getLastCellNum());
	          
	          cell.setCellValue(colName);
	          cell.setCellStyle(style);
	          
	          fileOut = new FileOutputStream(path);
	    workbook.write(fileOut);
	       fileOut.close();      

	   }catch(Exception e){
	    e.printStackTrace();
	    return false;
	   }
	   
	   return true; 
	   
	   
	  }
	  
	  
	  
	  
	  
	  
	  // removes a column and all the contents
	  public boolean removeColumn(String sheetName, int colNum) {
	   try{
	   if(!isSheetExist(sheetName))
	    return false;
	   fis = new FileInputStream(path); 
	   workbook = new XSSFWorkbook(fis);
	   sheet=workbook.getSheet(sheetName);
	   XSSFCellStyle style = workbook.createCellStyle();
	   //style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
	   //XSSFCreationHelper createHelper = workbook.getCreationHelper();
	   //style.setFillPattern(HSSFCellStyle.NO_FILL);
	   
	      
	  
	   for(int i =0;i<getRowCount(sheetName);i++){
	    row=sheet.getRow(i); 
	    if(row!=null){
	     cell=row.getCell(colNum);
	     if(cell!=null){
	      cell.setCellStyle(style);
	      row.removeCell(cell);
	     }
	    }
	   }
	   fileOut = new FileOutputStream(path);
	   workbook.write(fileOut);
	      fileOut.close();
	   }
	   catch(Exception e){
	    e.printStackTrace();
	    return false;
	   }
	   return true;
	   
	  }
	   // find whether sheets exists 
	  public boolean isSheetExist(String sheetName){
	   int index = workbook.getSheetIndex(sheetName);
	   if(index==-1){
	    index=workbook.getSheetIndex(sheetName.toUpperCase());
	     if(index==-1)
	      return false;
	     else
	      return true;
	   }
	   else
	    return true;
	  }
	  
	  // returns number of columns in a sheet 
	  public int getColumnCount(String sheetName){
	   // check if sheet exists
	   if(!isSheetExist(sheetName))
	    return -1;
	   
	   sheet = workbook.getSheet(sheetName);
	   row = sheet.getRow(0);
	   
	   if(row==null)
	    return -1;
	   
	   return row.getLastCellNum();
	   
	  }
	  
	  public int getCellRowNum(String sheetName,String colName,String cellValue){
	   
	   for(int i=2;i<=getRowCount(sheetName);i++){
	       if(getCellData(sheetName,colName , i).equalsIgnoreCase(cellValue)){
	    	   
	        return i;
	       }
	      }
	   return -1;
	   
	  }
	  
	  
	  
	  public boolean setCellData(String sheetName, String colName, int rowNum, String data) {
		    try {
		        int colNum = -1;
		        int index = workbook.getSheetIndex(sheetName);
		        if (index == -1)
		            return false;

		        sheet = workbook.getSheetAt(index);
		        row = sheet.getRow(0);
		        
		        for (int i = 0; i < row.getLastCellNum(); i++) {
		            if (row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName)) {
		                colNum = i;
		                break;
		            }
		        }

		        if (colNum == -1)
		            return false;

		        row = sheet.getRow(rowNum);
		        if (row == null)
		            row = sheet.createRow(rowNum);

		        cell = row.getCell(colNum);
		        if (cell == null)
		            cell = row.createCell(colNum);

		        cell.setCellValue(data);
		        
		        
		        // Define cell styles for different test results
		        CellStyle passStyle = workbook.createCellStyle();
		        CellStyle failStyle = workbook.createCellStyle();
		        CellStyle skipStyle = workbook.createCellStyle();

		        // Set background color for PASS (Green)
		        passStyle.setFillForegroundColor(IndexedColors.BRIGHT_GREEN1.getIndex());
		        passStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		        // Set background color for FAIL (Red)
		        failStyle.setFillForegroundColor(IndexedColors.BROWN.getIndex());
		        failStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		        // Set background color for SKIP (Yellow)
		        skipStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		        skipStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		        // Apply the appropriate style based on the test result
		        if ("PASS".equalsIgnoreCase(data)) {
		            cell.setCellStyle(passStyle);
		        } else if ("FAIL".equalsIgnoreCase(data)) {
		            cell.setCellStyle(failStyle);
		        } else if ("SKIP".equalsIgnoreCase(data)) {
		            cell.setCellStyle(skipStyle);
		        }

		        FileOutputStream fileOut = new FileOutputStream(path);
		        workbook.write(fileOut);
		        fileOut.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		        return false;
		    }
		    return true;
		}
	  
	
	  public String writeToExcel(String result,String sheetName,int rownumber) throws Exception {
		    String excelFilePath = path ;
		    
		    FileInputStream inputStream =  new FileInputStream(new File(excelFilePath));
		    workbook = new XSSFWorkbook(inputStream);
		    
		     sheet = workbook.getSheet(sheetName);
		    
		    // Get the row number where you want to write the result
		    int rowNumber = sheet.getLastRowNum() + 1;
		    
		    // Create a new row and set the result in a cell
		    Row row = sheet.createRow(rowNumber);
		    Cell cell = row.createCell(0); // Assuming you want to write the result in column 15
		    cell.setCellValue(result);
		    
		    // Write the changes back to the Excel file
		    FileOutputStream outputStream = new FileOutputStream(excelFilePath);
		    workbook.write(outputStream);
		    
		    // Close all resources
		    outputStream.close();
		    workbook.close();
			return "row "+" or column " +" does not exist  in xls";
		}

	  				
	  
	  public int findRow(String sheetName, String columnName, String cellContent) {
		    int colNum = -1, rowNum = -1;
		    sheet = workbook.getSheet(sheetName);
		    Row firstRow = sheet.getRow(0);

		    // Find the column with the given name
		    for (Cell cell : firstRow) {
		        if (cell.getStringCellValue().equalsIgnoreCase(columnName)) {
		            colNum = cell.getColumnIndex();
		            break;
		        }
		    }

		    // If column not found, return -1
		    if (colNum == -1) return -1;

		    // Iterate over rows to find the cell with the specified content
		    for (Row row : sheet) {
		        if(row.getRowNum() == 0) continue; // Skip header row
		        Cell cell = row.getCell(colNum);
		        if (cell != null && cell.getStringCellValue().equalsIgnoreCase(cellContent)) {
		            rowNum = row.getRowNum();
		            break;
		        }
		    }
		    return rowNum;
		}
}
