package com.utils;

public class DataElements {
    private String testCaseId;
    private String testCaseTitle;
    private String parameters;
    private boolean isRunTrue;
    private boolean isSanityTrue;
    private boolean isProductionTrue;
    private boolean isRegressionTrue;
    private boolean isTrueForWebDesktop;
    private boolean isTrueForWebAndroid;
    private boolean isTrueForWebIOS;
    private boolean isTrueForAndroidAMP;
    private boolean isTrueForIOSAMP;
    private boolean isTrueForAndroidNative;
    private boolean isTrueForIOSNative;



    public DataElements(String testCaseId, String testCaseTitle, String parameters, boolean isRunTrue, boolean isSanityTrue, boolean isProductionTrue, boolean isRegressionTrue, boolean isTrueForWebDesktop, boolean isTrueForWebAndroid, boolean isTrueForWebIOS, boolean isTrueForAndroidAMP, boolean isTrueForIOSAMP, boolean isTrueForAndroidNative, boolean isTrueForIOSNative) {
        this.testCaseId=testCaseId;
        this.testCaseTitle = testCaseTitle;
        this.isRunTrue = isRunTrue;
        this.parameters = parameters;
        this.isSanityTrue=isSanityTrue;
        this.isProductionTrue = isProductionTrue;
        this.isRegressionTrue=isRegressionTrue;
        this.isTrueForWebDesktop = isTrueForWebDesktop;
        this.isTrueForWebAndroid = isTrueForWebAndroid;
        this.isTrueForWebIOS = isTrueForWebIOS;
        this.isTrueForAndroidAMP=isTrueForAndroidAMP;
        this.isTrueForIOSAMP=isTrueForIOSAMP;
        this.isTrueForAndroidNative=isTrueForAndroidNative;
        this.isTrueForIOSNative=isTrueForIOSNative;
    }


    public String getTestCaseTitle() {
        return testCaseTitle;
    }

    public void setTestCaseTitle(String testCaseTitle) {
        this.testCaseTitle = testCaseTitle;
    }

    public boolean getIsRunTrue() {
        return isRunTrue;
    }

    public void setIsRunTrue(boolean isRunTrue) {
        this.isRunTrue = isRunTrue;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
    }

    public boolean getIsSanityTrue() {
        return isSanityTrue;
    }

    public void setIsSanityTrue(boolean sanityTrue) {
        isSanityTrue = sanityTrue;
    }

    public boolean getIsProductionTrue() {
        return isProductionTrue;
    }

    public void setIsProductionTrue(boolean productionTrue) {
        isProductionTrue = productionTrue;
    }

    public boolean getIsRegressionTrue() {
        return isRegressionTrue;
    }

    public void setIsRegressionTrue(boolean regressionTrue) {
        isRegressionTrue = regressionTrue;
    }

    public boolean getIsTrueForWebDesktop() {
        return isTrueForWebDesktop;
    }

    public void setIsTrueForWebDesktop(boolean trueForWebDesktop) {
        isTrueForWebDesktop = trueForWebDesktop;
    }

    public boolean getIsTrueForWebAndroid() {
        return isTrueForWebAndroid;
    }

    public void setIsTrueForWebAndroid(boolean trueForWebAndroid) {
        isTrueForWebAndroid = trueForWebAndroid;
    }

    public boolean getIsTrueForWebIOS() {
        return isTrueForWebIOS;
    }

    public void setIsTrueForWebIOS(boolean trueForWebIOS) {
        isTrueForWebIOS = trueForWebIOS;
    }

    public boolean getIsTrueForAndroidAMP() {
        return isTrueForAndroidAMP;
    }

    public void setIsTrueForAndroidAMP(boolean trueForAndroidAMP) {
        isTrueForAndroidAMP = trueForAndroidAMP;
    }

    public boolean getIsTrueForIOSAMP() {
        return isTrueForIOSAMP;
    }

    public void setIsTrueForIOSAMP(boolean trueForIOSAMP) {
        isTrueForIOSAMP = trueForIOSAMP;
    }

    public boolean getIsTrueForAndroidNative() {
        return isTrueForAndroidNative;
    }

    public void setIsTrueForAndroidNative(boolean trueForAndroidNative) {
        isTrueForAndroidNative = trueForAndroidNative;
    }

    public boolean getIsTrueForIOSNative() {
        return isTrueForIOSNative;
    }

    public void setIsTrueForIOSNative(boolean trueForIOSNative) {
        isTrueForIOSNative = trueForIOSNative;
    }
}
