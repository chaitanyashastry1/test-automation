package com.utils;

public class TestConfigElements {
    private String testCaseId;
    private String module;
    private String testCaseTitle;
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


    public TestConfigElements(String testCaseId, String module, String testCaseTitle, boolean isRunTrue, boolean isSanityTrue, boolean isProductionTrue, boolean isRegressionTrue, boolean isTrueForWebDesktop, boolean isTrueForWebAndroid, boolean isTrueForWebIOS, boolean isTrueForAndroidAMP, boolean isTrueForIOSAMP, boolean isTrueForAndroidNative, boolean isTrueForIOSNative) {
        this.testCaseId=testCaseId;
        this.module = module;
        this.testCaseTitle = testCaseTitle;
        this.isRunTrue = isRunTrue;
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

    public boolean getIsRunTrue() {
        return isRunTrue;
    }

    public String getModule() {
        return module;
    }

    public String getTestCaseId() {
        return testCaseId;
    }

    public boolean getIsSanityTrue() {
        return isSanityTrue;
    }

    public boolean getIsProductionTrue() {
        return isProductionTrue;
    }

    public boolean getIsRegressionTrue() {
        return isRegressionTrue;
    }

    public boolean getIsTrueForWebDesktop() {
        return isTrueForWebDesktop;
    }

    public boolean getIsTrueForWebAndroid() {
        return isTrueForWebAndroid;
    }

    public boolean getIsTrueForWebIOS() {
        return isTrueForWebIOS;
    }

    public boolean getIsTrueForAndroidAMP() {
        return isTrueForAndroidAMP;
    }

    public boolean getIsTrueForIOSAMP() {
        return isTrueForIOSAMP;
    }

    public boolean getIsTrueForAndroidNative() {
        return isTrueForAndroidNative;
    }

    public boolean getIsTrueForIOSNative() {
        return isTrueForIOSNative;
    }
}
