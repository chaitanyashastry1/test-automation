package com.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class DataReader {

    private static Map<String, TestConfigElements> testConfigMapping =new HashMap<>();
    Workbook workBook=null;
    Sheet testConfigSheet =null;
    Sheet testDataSheet =null;
    private static DataReader dataReader;
    private static GlobalVars globalVars;

    public static DataReader getInstance(){
        if(dataReader==null)
            dataReader=new DataReader();
        return dataReader;
    }

    /**
     * @param: none
     * @return: void
     * @description: This function sets up the entire test data from the excel sheet.
     * @author: Ataur
     */
    public void setupDataSheet() throws IOException {
        globalVars=GlobalVars.getInstance();
        InputStream input = getClass().getClassLoader().getResourceAsStream("testdata_"+globalVars.getProjectName()+".xlsx");
        setDataObject(input);
    }

    /**
     * Returns map of test cases and their params
     * @author A Rahman
     */
    public Map<String, TestConfigElements> getTestCaseMapping(){
        return testConfigMapping;
    }

    /**
     * @param inputStream
     * @return: void
     * @description: Presets all test case data from the excel sheet.
     * @author: Ataur
     */
    private void setDataObject(InputStream inputStream) throws IOException {
        System.out.println("####### googleSheetFlag: "+globalVars.getGoogleSheetFlag());
        System.out.println("####### buildNumber: "+globalVars.getBuildNumber());
        System.out.println("####### executionType: "+globalVars.getExecutionType());
        workBook = new XSSFWorkbook(inputStream);
        try{
            testConfigSheet =workBook.getSheet(Constants.TEST_CONFIG);
            testDataSheet=workBook.getSheet(globalVars.getEnvironment());
            //testConfigMapping= getTestConfigData(testConfigSheet);
            globalVars.setTestConfigMapping(getTestConfigData(testConfigSheet));
            globalVars.setTestDataElementsMap(getTestData(testDataSheet));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @param cell
     * @return: cellData as String
     * @description: This function takes a cell as an argument and returns the cell value based on the type of cell value type
     * @author: Ataur
     */
    @SuppressWarnings("deprecation")
    public String getCellData(Cell cell){
        String cellData="";
        try{
            if(cell!=null){
                switch(cell.getCellType()){

                    case Cell.CELL_TYPE_STRING:
                        cellData=cell.getStringCellValue();
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        cellData=(int)cell.getNumericCellValue()+"";
                        break;
                    case Cell.CELL_TYPE_BLANK:
                        cellData="";
                        break;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return cellData.trim();
    }

    /**
     * @param sheet takes the Sheet object returned from the workbook
     * @return: Map<String, DataElements>
     * @description return the map of page elements with element name and object of pageElement for the same.
     * @author: Ataur
     */
    private Map<String, TestConfigElements> getTestConfigData(Sheet sheet) {
        Map<String, TestConfigElements> dataElementsMap = new LinkedHashMap<>();
        String module="";
        String testCaseId="";
        String testCaseTitle = "";
        boolean isRunTrue =false;
        boolean isSanityTrue =false;
        boolean isProductionTrue =false;
        boolean isRegressionTrue =false;
        boolean isWebDesktopTrue =false;
        boolean isWebAndroidTrue =false;
        boolean isWebIOSTrue =false;
        boolean isAndroidAMPTrue=false;
        boolean isIOSAMPTrue=false;
        boolean isAndroidNativeTrue=false;
        boolean isIOSNativeTrue=false;

        for (int row = 1; row <= sheet.getLastRowNum(); row++) {
            Row dataRow = sheet.getRow(row);
            try{
                if(dataRow!=null) {
                    testCaseId = getCellData(dataRow.getCell(0));
                    module = getCellData(dataRow.getCell(1));
                    testCaseTitle = getCellData(dataRow.getCell(2));
                    isRunTrue = parseStringToBoolean(getCellData(dataRow.getCell(3)));
                    isProductionTrue = parseStringToBoolean(getCellData(dataRow.getCell(4)));
                    isSanityTrue = parseStringToBoolean(getCellData(dataRow.getCell(5)));
                    isRegressionTrue = parseStringToBoolean(getCellData(dataRow.getCell(6)));
                    isWebDesktopTrue = parseStringToBoolean(getCellData(dataRow.getCell(7)));
                    isWebAndroidTrue = parseStringToBoolean(getCellData(dataRow.getCell(8)));
                    isWebIOSTrue = parseStringToBoolean(getCellData(dataRow.getCell(9)));
                    isAndroidAMPTrue = parseStringToBoolean(getCellData(dataRow.getCell(10)));
                    isIOSAMPTrue = parseStringToBoolean(getCellData(dataRow.getCell(11)));
                    isAndroidNativeTrue = parseStringToBoolean(getCellData(dataRow.getCell(12)));
                    isIOSNativeTrue = parseStringToBoolean(getCellData(dataRow.getCell(13)));
                }
                else{
                    break;
                }

            }catch(Exception e){
                e.printStackTrace();
            }
            dataElementsMap.put(testCaseTitle, new TestConfigElements(testCaseId, module, testCaseTitle, isRunTrue, isSanityTrue, isProductionTrue, isRegressionTrue, isWebDesktopTrue, isWebAndroidTrue, isWebIOSTrue, isAndroidAMPTrue, isIOSAMPTrue, isAndroidNativeTrue, isIOSNativeTrue));
        }
        return dataElementsMap;
    }

    /**
     * @param sheet takes the Sheet object returned from the workbook
     * @return: Map<String, DataElements>
     * @description return the map of page elements with element name and object of pageElement for the same.
     * @author: Ataur
     */
    private Map<String, Map<String, String>> getTestData(Sheet sheet) {
        Map<String, Map<String, String>> dataElementsMap = new LinkedHashMap<>();
        String testCaseTitle = "";
        String property;
        Row propertyRow= sheet.getRow(0);
        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Map<String, String> propertyWiseDataMap=new HashMap<>();
            Row dataRow = sheet.getRow(rowNum);
            try{
                if(dataRow!=null) {
                    testCaseTitle = getCellData(dataRow.getCell(1));
                    int propertyCount=propertyRow.getLastCellNum();
                    for(int j=2; j<propertyCount; j++){
                        property=getCellData(propertyRow.getCell(j));
                        propertyWiseDataMap.put(property, getCellData(dataRow.getCell(j)));
                    }
                }
                else{
                    System.out.println("****** No test data found !!! *********");
                    break;
                }

            }catch(Exception e){
                e.printStackTrace();
            }
            dataElementsMap.put(testCaseTitle, propertyWiseDataMap);
        }
        return dataElementsMap;
    }

    /**
     * @param: String
     * @return: true/false
     * @description: This function takes a String with value yes or no and returns the corresponding boolean value
     * @author: Ataur
     */
    public static boolean parseStringToBoolean(String value){
        boolean boolResult=false;
        if(value.equalsIgnoreCase(Constants.YES) || value.equalsIgnoreCase("y")){
            boolResult=true;
        }
        else if(value.equalsIgnoreCase(Constants.NO) || value.equalsIgnoreCase("n")){
            boolResult=false;
        }
        return boolResult;
    }
}
