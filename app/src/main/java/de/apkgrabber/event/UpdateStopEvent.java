package de.apkgrabber.event;

public class UpdateStopEvent {

	private String mMessage;

	public UpdateStopEvent(String m) {
		mMessage = m;
	}

	public String getMessage() {
		return mMessage;
	}

}


