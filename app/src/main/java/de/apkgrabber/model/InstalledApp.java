package de.apkgrabber.model;


public class InstalledApp {


    private String mName;
    private String mPname;
    private String mVersion;
    private Integer mVersionCode;
    private InstallStatus mInstallStatus = new InstallStatus();


    public String getName(
    ) {
        return mName;
    }


    public void setName(
            String mame) {
        mName = mame;
    }


    public String getPname() {
        return mPname;
    }


    public void setPname(String pname) {
        mPname = pname;
    }


    public String getVersion() {
        return mVersion;
    }


    public void setVersion(String version) {
        mVersion = version;
    }


    public Integer getVersionCode(
    ) {
        return mVersionCode;
    }


    public void setVersionCode(
            Integer mVersionCode
    ) {
        this.mVersionCode = mVersionCode;
    }


    public InstallStatus getInstallStatus(
    ) {
        return mInstallStatus;
    }


    public void setInstallStatus(
            InstallStatus installStatus
    ) {
        mInstallStatus = installStatus;
    }


}


