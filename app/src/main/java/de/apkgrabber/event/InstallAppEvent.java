package de.apkgrabber.event;

public class InstallAppEvent {

	private boolean success;
	private long id;
	private String packageName;

	public InstallAppEvent(String packageName, long id, boolean success) {
		this.packageName = packageName;
		this.id = id;
		this.success = success;
	}

	public String getPackageName() {
		return packageName;
	}

	public boolean isSuccess() {
		return success;
	}

	public long getId() {
		return id;
	}

}

