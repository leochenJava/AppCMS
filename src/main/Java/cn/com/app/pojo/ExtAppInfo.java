package cn.com.app.pojo;

public class ExtAppInfo extends AppInfo{
    AppCategory appCategory;
    DataDictionary dataDictionary;
    AppVersion appVersion;
    String versionNo;
    String categoryLv1;
    String categoryLv2;
    String categoryLv3;
    String platformName;
    String appStatus;

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }

    public String getCategoryLv1() {
        return categoryLv1;
    }

    public void setCategoryLv1(String categoryLv1) {
        this.categoryLv1 = categoryLv1;
    }

    public String getCategoryLv2() {
        return categoryLv2;
    }

    public void setCategoryLv2(String categoryLv2) {
        this.categoryLv2 = categoryLv2;
    }

    public String getCategoryLv3() {
        return categoryLv3;
    }

    public void setCategoryLv3(String categoryLv3) {
        this.categoryLv3 = categoryLv3;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public AppCategory getAppCategory() {
        return appCategory;
    }

    public void setAppCategory(AppCategory appCategory) {
        this.appCategory = appCategory;
    }

    public DataDictionary getDataDictionary() {
        return dataDictionary;
    }

    public AppVersion getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(AppVersion appVersion) {
        this.appVersion = appVersion;
    }

    public void setDataDictionary(DataDictionary dataDictionary) {
        this.dataDictionary = dataDictionary;
    }
}
