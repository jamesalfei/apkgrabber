package de.apkgrabber.event;

import de.apkgrabber.model.Update;

import java.util.List;

public class UpdateFinalProgressEvent {

	private List<Update> mUpdate;

	public UpdateFinalProgressEvent(List<Update> u) {
		mUpdate = u;
	}

	public List<Update> getUpdates() {
		return mUpdate;
	}

}


