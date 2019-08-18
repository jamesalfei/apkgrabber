package de.apkgrabber.event;

import de.apkgrabber.model.Update;

public class UpdateProgressEvent {

	private Update mUpdate;

	public UpdateProgressEvent(Update u) {
		mUpdate = u;
	}

	public Update getUpdate() {
		return mUpdate;
	}

}


