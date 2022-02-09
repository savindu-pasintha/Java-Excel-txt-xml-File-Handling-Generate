
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author HACKER PERSON
 */
public class TxtDataFilterAndRewriteToExcel {

    public String arr[];

    public void openFile() {
        try {
            File file = new File("src/dt.txt");
            file.createNewFile();
            System.out.println("File a created.");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    
    //txt file to data append/write as a xml syntax
    public int insertToTxtfile(String variable_Name, String Variable_Value, String savedFilePath) {
        int x = 1;
        //src/string_variable_xml.txt
        try {
            FileWriter file_writer = new FileWriter(savedFilePath, true); //file write append enable;
            //write data file inside
            arr = new String[2];
            //file_writer.write("<record><Stringname>" + variable_Name + "</Stringname><English>" + Variable_Value + "</English></record>" + '\n');
            file_writer.write("<string name=" + '\u0022' + variable_Name + '\u0022' + " >" + Variable_Value + "</string>" + '\n');
           //<string name="VARIABLE_NAME" >_ARABIC_TRANSLATED_VALUE</string>
            file_writer.close();
            x = 1;
        } catch (IOException ex) {
            System.out.println(ex);
            x = 0;
        }
        return x;
    }

    
    //read text file->filter world-> excel file to save data as a table : row,colum
    public void view() throws InterruptedException {
        /* count the lines in text file */
        //1-read data
        try {

            //for excel write first row
            String filePath = "src/excel_file.xlsx";
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Emp Info");
            XSSFRow row = sheet.createRow(0);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue("VARIABLE_NAME");
            cell = row.createCell(1);
            cell.setCellValue("ORIGINAL_VALUE_ENGLISH");
            cell = row.createCell(2);
            cell.setCellValue("_ARABIC_TRANSLATED_VALUE");
            cell = row.createCell(3);
            cell.setCellValue("CHINEES_TRANSLATED_VALUE");
            cell = row.createCell(4);
            cell.setCellValue("JAPAN_TRANSLATED_VALUE");

            int i = 0;
            String textline;
            i = 0;
            FileReader frnew = new FileReader("src/dt.txt");
            Scanner scanLineNew = new Scanner(frnew);
            String variable_name = "", variable_value = "";
            String[] arr;
            String s = "<string name=\"navigation_drawer_open\">Open navigation drawer</string>";
            while (scanLineNew.hasNext()) {
                textline = scanLineNew.nextLine();
                String removeTag = textline.replaceAll("string", "").replace("</>", "").replace("<", "").replace(">", "");
                //System.out.println(i+" : "+removeTag);
                //name="cn_history_deleted_title_si"Credit Note Cancellation
                arr = removeTag.split("name=");
                int idx = 0;
                for (String itm : arr) {
                    //System.out.println(idx + ":"+itm);
                    if (!itm.isEmpty()) {
                        arr = itm.split("\\u0022");//separate with double quatos " 
                        idx = 0;
                        for (String itms : arr) {
                            switch (idx) {
                                case 0 -> {
                                    System.out.println(idx + " :" + itms);
                                    variable_name = "";
                                }
                                case 1 -> {
                                    System.out.println(idx + " variable name :" + itms);
                                    variable_name = itms;
                                }
                                case 2 -> {
                                    System.out.println(idx + " value : " + itms);
                                    variable_value = itms;
                                }
                                default -> {
                                }
                            }

                            //variable_name="";variable_value="";
                            idx++;
                        }
                    }
                    idx++;
                }

                //for excel write new row
                if (!variable_name.equals("") && !variable_name.isBlank() && !variable_name.isEmpty()) {
                    int rowCount = i + 1;
                    row = sheet.createRow(rowCount);
                    cell = row.createCell(0);
                    cell.setCellValue(variable_name);
                    cell = row.createCell(1);
                    cell.setCellValue(variable_value);
                    variable_name = "";
                    variable_value = "";
                } else {
                    System.out.println("Error : variable name is empty !");
                }

                i++;
            }
            scanLineNew.close();
            frnew.close();

            //for excel write new row
            FileOutputStream outstream = new FileOutputStream(filePath);
            workbook.write(outstream);
            outstream.close();
            System.out.println(filePath + " written successfully...");

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    
    //no used : write data to excel.xlsx file as a row,colum table
    public void writeExcel(String variable_Name, String Variable_Value) throws IOException {
        String filePath = "src/excel_file.xlsx";
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Emp Info");
        Object empdata[][] = {{"1EmpIDyyy", "Namyyye", "yyJob"},
        {101, "yDavid", "Enginner"},
        {102, "ySymith", "Manager"},
        {103, "yScott", "Analyst"}
        };

        int rowCount = 0;
        for (Object emp[] : empdata) {
            XSSFRow row = sheet.createRow(rowCount++);
            int columnCount = 0;
            for (Object value : emp) {
                XSSFCell cell = row.createCell(columnCount++);

                if (value instanceof String) {
                    cell.setCellValue((String) value);
                }
                if (value instanceof Integer) {
                    cell.setCellValue((Integer) value);
                }
                if (value instanceof Boolean) {
                    cell.setCellValue((Boolean) value);
                }

            }
        }
        FileOutputStream outstream = new FileOutputStream(filePath);
        workbook.write(outstream);
        outstream.close();
        System.out.println("Employee.xls file written successfully...");

    }

    
    //excel file data read-> generate xml sysntax->save to txt file
    public void readEcxelfileAndWriteToTxtAsXml() throws IOException {
        //String excelFilePath=".\\datafiles\\countries.xlsx";
        String excelFilePath = "src/Language_Translated.xlsx";
        FileInputStream inputstream = new FileInputStream(excelFilePath);

        XSSFWorkbook workbook = new XSSFWorkbook(inputstream);
        XSSFSheet sheet = workbook.getSheetAt(0);	//XSSFSheet sheet=workbook.getSheet("Sheet1");

        Iterator iterator = sheet.iterator();
        int rowC = 0;

        while (iterator.hasNext()) {
            XSSFRow row = (XSSFRow) iterator.next();
            Iterator cellIterator = row.cellIterator();
            String variable_name = "", value = "", china="",japan="",arabic="",french="";
            int colC = 0;
            while (cellIterator.hasNext()) {
                XSSFCell cell = (XSSFCell) cellIterator.next();
                if (colC == 0) {
                    variable_name = cell.getStringCellValue();
                } else if (colC == 1) {
                    value = cell.getStringCellValue();
                }else if (colC == 2) {
                    arabic = cell.getStringCellValue();
                }else if (colC == 3) {
                    china = cell.getStringCellValue();
                }else if (colC == 4) {
                    japan = cell.getStringCellValue();
                }else if (colC == 5) {
                    french = cell.getStringCellValue();
                }
                colC++;
            }
            
            insertToTxtfile(variable_name, value, "src/string_variable_xml.txt");
            insertToTxtfile(variable_name, arabic, "src/string_variable_arabicxml.txt");
            insertToTxtfile(variable_name, china, "src/string_variable_xml_china.txt");
            insertToTxtfile(variable_name, japan, "src/string_variable_xml_japan.txt");
            insertToTxtfile(variable_name, french,"src/string_variable_xml_french.txt");
            
            variable_name = "";
            
            value = "";

            rowC++;
        }

    }

    public static void main(String[] args) throws IOException {
        TxtDataFilterAndRewriteToExcel obj = new TxtDataFilterAndRewriteToExcel();
        //obj.view();
        obj.readEcxelfileAndWriteToTxtAsXml();
    }
}
