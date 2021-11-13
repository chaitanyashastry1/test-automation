package com.listeners;

import com.utils.*;
import org.testng.*;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class AnnotationTransformer implements IAnnotationTransformer {
	static DataReader dataReader = null;
	private static boolean isDataInitialized;
	static GlobalVars globalVars;
	static ConfigPropertiesDataInitializer configPropertiesDataInitializer;
	private static Map<String, TestConfigElements> testCasesMap=new LinkedHashMap<>();
	static ArrayList<String> testCaseList;

	private static void initializeExecutionData(){
		try {
			if(!isDataInitialized){
				configPropertiesDataInitializer=ConfigPropertiesDataInitializer.getInstance();
				configPropertiesDataInitializer.initializeConfigPropertiesVariables();
				globalVars=GlobalVars.getInstance();
				if(globalVars.getGoogleSheetFlag()){
					GoogleSheetUtils googleSheetUtils=GoogleSheetUtils.getInstance();
					googleSheetUtils.setupDataSheet();
				}
				else {
					dataReader = DataReader.getInstance();
					dataReader.setupDataSheet();
				}
				testCasesMap= globalVars.getTestConfigMapping();
				setRunnableTestCaseList();
				isDataInitialized=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		initializeExecutionData();
		if(testCasesMap.containsKey(testMethod.getName())){
			if(!isTestCaseToBeEnabled(testMethod.getName())){
				annotation.setEnabled(false);
			}
			else {

				int priority=testCaseList.indexOf(testMethod.getName());
				annotation.setPriority(priority);
				globalVars.setTestCaseListRunModeTrue(testMethod.getName());
			}
		}
		else{
			annotation.setEnabled(false);
		}
		annotation.setRetryAnalyzer(Retry.class);

	}


	private static boolean isTestCaseToBeEnabled(String testMethod){
		boolean isTestCaseRunTrue;
		boolean isExecutionTypeTrue=true;
		boolean isPlatformApplicable=true;
		isTestCaseRunTrue=testCasesMap.get(testMethod).getIsRunTrue();
		if(isTestCaseRunTrue){
			if(globalVars.getExecutionType().equalsIgnoreCase(Constants.SANITY)){
				isExecutionTypeTrue=testCasesMap.get(testMethod).getIsSanityTrue();
			}
			if(globalVars.getExecutionType().equalsIgnoreCase(Constants.PRODUCTION)){
				isExecutionTypeTrue=testCasesMap.get(testMethod).getIsProductionTrue();
			}
			if(globalVars.getExecutionType().equalsIgnoreCase(Constants.REGRESSION)){
				isExecutionTypeTrue=testCasesMap.get(testMethod).getIsRegressionTrue();
			}
			isPlatformApplicable=checkPlatformApplicability(testMethod);
		}
		return isTestCaseRunTrue && isExecutionTypeTrue && isPlatformApplicable;
	}

	private static boolean checkPlatformApplicability(String testMethod){
		boolean isPlatformApplicable=false;
		switch (globalVars.getPlatformName()){
			case Constants.ANDROID_NATIVE:
				isPlatformApplicable=testCasesMap.get(testMethod).getIsTrueForAndroidNative();
				break;
			case Constants.ANDROID_AMP:
				isPlatformApplicable=testCasesMap.get(testMethod).getIsTrueForAndroidAMP();
				break;
			case Constants.ANDROID_WEB:
				isPlatformApplicable=testCasesMap.get(testMethod).getIsTrueForWebAndroid();
				break;
			case Constants.IOS_NATIVE:
				isPlatformApplicable=testCasesMap.get(testMethod).getIsTrueForIOSNative();
				break;
			case Constants.IOS_AMP:
				isPlatformApplicable=testCasesMap.get(testMethod).getIsTrueForIOSAMP();
				break;
			case Constants.IOS_WEB:
				isPlatformApplicable=testCasesMap.get(testMethod).getIsTrueForWebIOS();
				break;
			case Constants.DESKTOP_WEB:
				isPlatformApplicable=testCasesMap.get(testMethod).getIsTrueForWebDesktop();
				break;
		}
		return isPlatformApplicable;
	}

	private static void setRunnableTestCaseList(){
		Map<String, TestConfigElements> runnableTcMap=new LinkedHashMap<>();
		for(Map.Entry<String, TestConfigElements> entry: testCasesMap.entrySet()){
			if(isTestCaseToBeEnabled(entry.getKey())){
				runnableTcMap.put(entry.getKey(), entry.getValue());
			}
		}
		testCaseList=new ArrayList<>(runnableTcMap.keySet());
	}

}