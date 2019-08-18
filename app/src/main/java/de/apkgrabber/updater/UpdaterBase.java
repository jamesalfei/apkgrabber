package de.apkgrabber.updater;

import android.content.Context;
import de.apkgrabber.util.VersionUtil;

import java.util.List;

public class UpdaterBase extends UpdaterOptions implements IUpdater {

	protected String mPname;
	protected String mUrl;
	protected String mResultUrl;
	protected String mResultVersion;
	protected int mResultVersionCode;
	protected Throwable mError;
	protected UpdaterStatus mResultStatus;
	protected String mCurrentVersion;
	protected String mUpdaterType;
	protected boolean mIsBeta;
	protected String mResultCookie;

	UpdaterBase(Context context, String pname, String cversion, String type) {
		super(context);
		mCurrentVersion = cversion;
		mResultUrl = "";
		mPname = pname;
		mUpdaterType = type;
		mIsBeta = false;
		mResultStatus = parseUrl(getUrl(mPname));
	}

	protected String getUrl(String pname) {
		return mUrl;
	}

	protected UpdaterStatus parseUrl(String url) {
		return UpdaterStatus.STATUS_FATAL_ERROR;
	}

	protected Throwable addCommonInfoToError(Throwable error) {
		return new Exception(error.getMessage() == null ? "" : error.getMessage() + " | App: " + mPname + " | Source: " + mUpdaterType);
	}

	protected int compareVersions(String cv, String ev) throws Exception {
		List<Integer> cvl = VersionUtil.getVersionFromString(cv);
		if (cvl == null) {
			throw new Exception("Unable to parse version: " + cv);
		}

		List<Integer> evl = VersionUtil.getVersionFromString(ev);
		if (evl == null) {
			throw new Exception("Unable to parse version: " + ev);
		}

		return VersionUtil.compareVersion(cvl, evl);
	}

	@Override
	public String getResultUrl() {
		return mResultUrl;
	}

	@Override
	public Throwable getResultError() {
		return mError;
	}

	@Override
	public UpdaterStatus getResultStatus() {
		return mResultStatus;
	}

	@Override
	public String getResultVersion() {
		return mResultVersion;
	}

	@Override
	public boolean isBeta() {
		return mIsBeta;
	}

	@Override
	public String getResultCookie() {
		return mResultCookie;
	}

	@Override
	public int getResultVersionCode() {
		return mResultVersionCode;
	}

}


