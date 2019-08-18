package de.apkgrabber.model;

public class Update {

	private String mName;
	private String mPname;
	private String mVersion;
	private String mNewVersion;
	private String mUrl;
	private boolean mIsBeta;
	private String mCookie;
	private int mNewVersionCode;
	private int mVersionCode;
	private String mChangeLog;
	private InstallStatus mInstallStatus = new InstallStatus();

	public Update(InstalledApp app, String url, String version, boolean isBeta, String cookie, int versionCode) {
		setName(app.getName());
		setPname(app.getPname());
		setVersion(app.getVersion());
		setVersionCode(app.getVersionCode());
		setUrl(url);
		setNewVersion(version);
		setIsBeta(isBeta);
		setNewVersionCode(versionCode);
		setCookie(cookie);
	}

	public String getName() {
		return mName;
	}

	public void setName(String mame) {
		mName = mame;
	}

	public boolean isBeta() {
		return mIsBeta;
	}

	public void setIsBeta(boolean isBeta) {
		mIsBeta = isBeta;
	}

	public String getPname() {
		return mPname;
	}

	public void setPname(String pname) {
		mPname = pname;
	}

	public String getNewVersion() {
		return mNewVersion;
	}

	public void setNewVersion(String version) {
		mNewVersion = version;
	}

	public String getVersion() {
		return mVersion;
	}

	public void setVersion(String version) {
		mVersion = version;
	}

	public String getUrl() {
		return mUrl;
	}

	public void setUrl(String mUrl) {
		this.mUrl = mUrl;
	}

	public String getCookie() {
		return mCookie;
	}

	public void setCookie(String cookie) {
		mCookie = cookie;
	}

	public int getVersionCode() {
		return mVersionCode;
	}

	public void setVersionCode(int versionCode) {
		mVersionCode = versionCode;
	}

	public int getNewVersionCode() {
		return mNewVersionCode;
	}

	public void setNewVersionCode(int versionCode) {
		mNewVersionCode = versionCode;
	}

	public InstallStatus getInstallStatus() {
		return mInstallStatus;
	}

	public String getChangeLog() {
		return mChangeLog;
	}

	public void setChangeLog(String changeLog) {
		mChangeLog = changeLog;
	}

}


