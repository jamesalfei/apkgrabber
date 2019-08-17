package de.apkgrabber.model;


public class DownloadInfo {


    private String packageName;
    private int versionCode;
    private String versionName;


    public DownloadInfo(
            String packageName,
            int versionCode,
            String versionName
    ) {

        this.packageName = packageName;
        this.versionCode = versionCode;
        this.versionName = versionName;
    }


    public String getPackageName(
    ) {
        return packageName;
    }


    public void setPackagepName(
            String packageName
    ) {
        this.packageName = packageName;
    }


    public int getVersionCode(
    ) {
        return versionCode;
    }


    public void setVersionCode(
            int versionCode
    ) {
        this.versionCode = versionCode;
    }


    public String getVersionName(
    ) {
        return versionName;
    }


    public void setVersionName(
            String versionName
    ) {
        this.versionName = versionName;
    }


}

