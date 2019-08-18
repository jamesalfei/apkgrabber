package de.apkgrabber.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@EBean(scope = EBean.Scope.Singleton)
public class AppState {

	private final static String SELECTED_TAB_KEY = "selected_tab_key";
	private final static String CURRENT_THEME_KEY = "current_theme_key";
	private final static String UPDATE_LIST_KEY = "update_list_key";
	private final static String SETTINGS_ACTIVE_KEY = "settings_active_key";
	private final static String LOG_ACTIVE_KEY = "log_active_key";
	private final static String ABOUT_ACTIVE_KEY = "about_active_key";
	static private boolean mFirstStart = true;

	@RootContext
	Context mContext;
	private AtomicInteger mUpdateProgress = new AtomicInteger(0);
	private int mUpdateMax = 0;
	private Map<Long, DownloadInfo> mDownloadInfo = new HashMap<>();
	private Map<Integer, Long> mDownloadIds = new HashMap<>();

	AppState(Context context) {
		mContext = context;
	}

	public int increaseUpdateProgress() {
		return mUpdateProgress.incrementAndGet();
	}

	public int getUpdateProgress() {
		return mUpdateProgress.get();
	}

	public void setUpdateProgress(int progress) {
		mUpdateProgress = new AtomicInteger(progress);
	}

	public int getUpdateMax() {
		return mUpdateMax;
	}

	public void setUpdateMax(int max) {
		mUpdateMax = max;
	}

	public int getSelectedTab() {
		return getSelectedTabFromSharedPrefs(mContext);
	}

	public void setSelectedTab(int selectedTab) {
		setSelectedTabToSharedPrefs(mContext, selectedTab);
	}

	public boolean getSettingsActive() {
		return getSettingsActiveFromSharedPrefs(mContext);
	}

	public void setSettingsActive(boolean active) {
		if (active) {
			setLogActive(false);
			setAboutActive(false);
		}
		setSettingsActiveToSharedPrefs(mContext, active);
	}

	public boolean getFirstStart() {
		return mFirstStart;
	}

	public void setFirstStart(boolean firstStart) {
		mFirstStart = firstStart;
	}

	public boolean getLogActive() {
		return getLogActiveFromSharedPrefs(mContext);
	}

	public void setLogActive(boolean active) {
		if (active) {
			setSettingsActive(false);
			setAboutActive(false);
		}
		setLogActiveToSharedPrefs(mContext, active);
	}

	public boolean getAboutActive() {
		return getAboutActiveFromSharedPrefs(mContext);
	}

	public void setAboutActive(boolean active) {
		if (active) {
			setSettingsActive(false);
			setLogActive(false);
		}
		setAboutActiveToSharedPrefs(mContext, active);
	}

	public int getCurrentTheme() {
		return getCurrentThemeFromSharedPrefs(mContext);
	}

	public void setCurrentTheme(int currentTheme) {
		setCurrentThemeToSharedPrefs(mContext, currentTheme);
	}

	public List<Update> getUpdates() {
		return getUpdatesFromSharedPrefs(mContext);
	}

	public void setUpdates(List<Update> updates) {
		setUpdatesToSharedPrefs(mContext, updates);
	}

	public void clearUpdates() {
		clearUpdatesFromSharedPrefs(mContext);
	}

	private int getSelectedTabFromSharedPrefs(Context context) {
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
		return sharedPref.getInt(SELECTED_TAB_KEY, 0);
	}

	private void setSelectedTabToSharedPrefs(Context context, int tab) {
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
		sharedPref.edit().putInt(SELECTED_TAB_KEY, tab).apply();
	}

	private boolean getSettingsActiveFromSharedPrefs(Context context) {
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
		return sharedPref.getBoolean(SETTINGS_ACTIVE_KEY, false);
	}

	private void setSettingsActiveToSharedPrefs(Context context, boolean active) {
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
		sharedPref.edit().putBoolean(SETTINGS_ACTIVE_KEY, active).apply();
	}

	private boolean getLogActiveFromSharedPrefs(Context context) {
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
		return sharedPref.getBoolean(LOG_ACTIVE_KEY, false);
	}

	private void setLogActiveToSharedPrefs(Context context, boolean active) {
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
		sharedPref.edit().putBoolean(LOG_ACTIVE_KEY, active).apply();
	}

	private boolean getAboutActiveFromSharedPrefs(Context context) {
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
		return sharedPref.getBoolean(ABOUT_ACTIVE_KEY, false);
	}

	private void setAboutActiveToSharedPrefs(Context context, boolean active) {
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
		sharedPref.edit().putBoolean(ABOUT_ACTIVE_KEY, active).apply();
	}

	private int getCurrentThemeFromSharedPrefs(Context context) {
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
		return sharedPref.getInt(CURRENT_THEME_KEY, 0);
	}

	private void setCurrentThemeToSharedPrefs(Context context, int theme) {
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
		sharedPref.edit().putInt(CURRENT_THEME_KEY, theme).apply();
	}

	private List<Update> getUpdatesFromSharedPrefs(Context context) {
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
		List<Update> updates = new ArrayList<>();

		// Get the string, if its empty return empty array
		String s = sharedPref.getString(UPDATE_LIST_KEY, "");
		if (s.isEmpty()) {
			return updates;
		}

		// Try to convert the json string to an object
		try {
			updates = new Gson().fromJson(s, new TypeToken<List<Update>>() {
			}.getType());
		} catch (Exception ignored) {

		}

		return updates;
	}

	private void setUpdatesToSharedPrefs(Context context, List<Update> updates) {
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
		sharedPref.edit().putString(UPDATE_LIST_KEY, new Gson().toJson(updates)).apply();
	}

	private void clearUpdatesFromSharedPrefs(Context context) {
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
		sharedPref.edit().remove(UPDATE_LIST_KEY).apply();
	}

	public Map<Long, DownloadInfo> getDownloadInfo() {
		return mDownloadInfo;
	}

	public Map<Integer, Long> getDownloadIds() {
		return mDownloadIds;
	}

}


