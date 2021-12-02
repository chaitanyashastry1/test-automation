package com.utils;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.*;

public class GoogleSheetUtils {

    private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();//JacksonFactory.getDefaultInstance();

    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static GoogleSheetUtils googleSheetUtils;

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
    private static final String CREDENTIALS_FILE_PATH = "credentials.json";

    public static GoogleSheetUtils getInstance(){
        if(googleSheetUtils==null){
            googleSheetUtils=new GoogleSheetUtils();
        }
        return googleSheetUtils;
    }

    /**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {

        InputStream in= GoogleSheetUtils.class.getClassLoader().getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    /**
     * Prints the names and majors of students in a sample spreadsheet:
     * https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
     */
/*    public static void main(String... args) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        final String spreadsheetId = "1kthjqhoCYKxGHudCs3CWiOAeh83Haq14_35VVFMsDto";
        final String range = "SmartCast!A1:R";
        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.isEmpty()) {
            System.out.println("No data found.");
        } else {
            System.out.println("Name, Major");
            for (List row : values) {
                // Print columns A and E, which correspond to indices 0 and 4.
                System.out.printf("%s, %s\n", row.get(0), row.get(4));
            }
        }
    }*/

    /**
     * @param: none
     * @return: void
     * @description: This function sets up the entire test data from the google sheet.
     */
    public void setupDataSheet() throws IOException, GeneralSecurityException {
        GlobalVars globalVars=GlobalVars.getInstance();
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        //final String spreadsheetId = "1kthjqhoCYKxGHudCs3CWiOAeh83Haq14_35VVFMsDto";
        final String spreadsheetId = globalVars.getSpreadsheetId();
        final String testConfigSheetRange = "TestConfig!A1:N";
        final String testDataSheetRange = globalVars.getEnvironment()+"!A1:Z";
        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        ValueRange responseTestConfig = service.spreadsheets().values()
                .get(spreadsheetId, testConfigSheetRange)
                .execute();
        List<List<Object>> valuesTestConfig = responseTestConfig.getValues();

        ValueRange responseTestData = service.spreadsheets().values()
                .get(spreadsheetId, testDataSheetRange)
                .execute();
        List<List<Object>> valuesTestData = responseTestData.getValues();

        if (valuesTestConfig == null || valuesTestConfig.isEmpty()) {
            System.out.println("********* No Test Config data found !!! ********");
        }
        else{

            globalVars.setTestConfigMapping(getTestConfigData(valuesTestConfig));
        }

        if (valuesTestData == null || valuesTestData.isEmpty()) {
            System.out.println("********* No Test data found !!! ********");
        }
        else{
            globalVars.setTestDataElementsMap(getTestData(valuesTestData));
        }

    }

    /**
     * @param valuesTestConfig takes the Sheet object returned from the workbook
     * @return: Map<String, DataElements>
     * @description return the map of page elements with element name and object of pageElement for the same.
     * @author: Ataur
     */
    private Map<String, TestConfigElements> getTestConfigData(List<List<Object>> valuesTestConfig) {
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


        for (int rowNum = 1; rowNum < valuesTestConfig.size(); rowNum++) {
            try{
                List row = valuesTestConfig.get(rowNum);
                if(row!=null) {
                    testCaseId = String.valueOf(row.get(0));
                    module = String.valueOf(row.get(1));
                    testCaseTitle = String.valueOf(row.get(2));
                    isRunTrue = parseStringToBoolean(String.valueOf(row.get(3)));
                    isProductionTrue = parseStringToBoolean(String.valueOf(row.get(4)));
                    isSanityTrue = parseStringToBoolean(String.valueOf(row.get(5)));
                    isRegressionTrue = parseStringToBoolean(String.valueOf(row.get(6)));
                    isWebDesktopTrue = parseStringToBoolean(String.valueOf(row.get(7)));
                    isWebAndroidTrue = parseStringToBoolean(String.valueOf(row.get(8)));
                    isWebIOSTrue = parseStringToBoolean(String.valueOf(row.get(9)));
                    isAndroidAMPTrue = parseStringToBoolean(String.valueOf(row.get(10)));
                    isIOSAMPTrue = parseStringToBoolean(String.valueOf(row.get(11)));
                    isAndroidNativeTrue = parseStringToBoolean(String.valueOf(row.get(12)));
                    isIOSNativeTrue = parseStringToBoolean(String.valueOf(row.get(13)));
                }
                else{
                    break;
                }

            }
            catch (IndexOutOfBoundsException ie){
                System.out.println("****** test configuration missing for: "+testCaseTitle);
            }
            catch(Exception e){
                e.printStackTrace();
            }
            dataElementsMap.put(testCaseTitle, new TestConfigElements(testCaseId, module, testCaseTitle, isRunTrue, isSanityTrue, isProductionTrue, isRegressionTrue, isWebDesktopTrue, isWebAndroidTrue, isWebIOSTrue, isAndroidAMPTrue, isIOSAMPTrue, isAndroidNativeTrue, isIOSNativeTrue));
        }
        return dataElementsMap;
    }

    /**
     * @param valuesTestData<List<Object>> valuesTestConfig takes the Sheet object returned from the workbook
     * @return: Map<String, DataElements>
     * @description return the map of page elements with element name and object of pageElement for the same.
     */
    private Map<String, Map<String, String>> getTestData(List<List<Object>> valuesTestData) {
        Map<String, Map<String, String>> dataElementsMap = new LinkedHashMap<>();
        String testCaseTitle = "";

        List propertyRow= valuesTestData.get(0);
        for (int rowNum = 1; rowNum < valuesTestData.size(); rowNum++) {
            Map<String, String> propertyWiseDataMap=new HashMap<>();
            List dataRow = valuesTestData.get(rowNum);
            try{
                if(dataRow!=null) {
                    testCaseTitle = String.valueOf(dataRow.get(1));
                    int propertyCount=propertyRow.size();
                    for(int j=2; j<propertyCount; j++){
                        String property=String.valueOf(propertyRow.get(j));
                        propertyWiseDataMap.put(property, String.valueOf(dataRow.get(j)));
                    }
                }
                else{
                    System.out.println("****** No test data found !!! *********");
                    break;
                }

            }
            catch (IndexOutOfBoundsException ie){
                System.out.println("****** test data does not exist for: "+testCaseTitle);
            }
            catch(Exception e){
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
     */
    public static boolean parseStringToBoolean(String value){
        boolean boolResult=false;
        if(value.equalsIgnoreCase(Constants.YES) || value.equalsIgnoreCase("y")){
            boolResult=true;
        }
        else if(value.equalsIgnoreCase(Constants.NO) || value.equalsIgnoreCase("n")){
            boolResult=false;
        }
        else{
            System.out.println("********** Please specify correct value (i.e. yes, no, y or n) in TestConfig Sheet !!! *********");
        }
        return boolResult;
    }
}
