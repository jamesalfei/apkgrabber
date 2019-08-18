package de.apkgrabber.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.PreferenceFragmentCompat;
import de.apkgrabber.R;
import de.apkgrabber.activity.MainActivity_;
import de.apkgrabber.dialog.OwnPlayAccountDialog;
import de.apkgrabber.event.UpdateInstalledAppsEvent;
import de.apkgrabber.model.Constants;
import de.apkgrabber.util.AlarmUtil;
import de.apkgrabber.util.MyBus;
import de.apkgrabber.util.SnackBarUtil;
import eu.chainfire.libsuperuser.Shell;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

@EFragment
public class SettingsFragment extends PreferenceFragmentCompat {

	@Bean
	MyBus mBus;

	private boolean mIgnoreChange;
	SharedPreferences.OnSharedPreferenceChangeListener mChanges = new SharedPreferences.OnSharedPreferenceChangeListener() {
		@Override
		public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
			try {
				if (key.equals(getString(R.string.preferences_general_alarm_key)) || key.equals(getString(R.string.preferences_general_update_hour_key))) {
					// Change alarm
					AlarmUtil alarmUtil = new AlarmUtil(getContext());
					alarmUtil.setAlarmFromOptions();
				} else if (key.equals(getString(R.string.preferences_general_theme_key))) {
					// Change theme
					MainActivity_.intent(getContext()).flags(FLAG_ACTIVITY_CLEAR_TOP).start();
				} else if (key.equals(getString(R.string.preferences_general_exclude_system_apps_key)) || key.equals(getString(R.string.preferences_general_exclude_disabled_apps_key))) {
					// Update list of apps
					mBus.post(new UpdateInstalledAppsEvent());
				} else if (key.equals(getString(R.string.preferences_play_own_account_key))) {
					if (!mIgnoreChange && prefs.getBoolean(getString(R.string.preferences_play_own_account_key), false)) {
						// Set pref to unchecked
						setOwnPlayAccount(false);

						// Launch dialog
						OwnPlayAccountDialog d = new OwnPlayAccountDialog();
						d.setTargetFragment(SettingsFragment.this, Constants.OwnPlayAccountRequestCode);
						d.show(getFragmentManager(), getTag());
					}
					mIgnoreChange = false;
				} else if (key.equals(getString(R.string.preferences_play_root_install_key))) {
					checkForRoot(key);
				} else if (key.equals(getString(R.string.preferences_play_automatic_install_key))) {
					checkForRoot(key);
				}
			} catch (IllegalStateException ignored) {

			}
		}
	};

	@Override
	public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
		PreferenceManager.getDefaultSharedPreferences(getContext()).registerOnSharedPreferenceChangeListener(mChanges);
		addPreferencesFromResource(R.xml.preferences);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == Constants.OwnPlayAccountRequestCode && resultCode == OwnPlayAccountDialog.ResultSuccess) {
			mIgnoreChange = true;
			setOwnPlayAccount(true);
		} else {
			super.onActivityResult(requestCode, resultCode, data);
		}
	}

	private void setOwnPlayAccount(boolean b) {
		CheckBoxPreference p = (CheckBoxPreference) findPreference(getString(R.string.preferences_play_own_account_key));
		p.setChecked(b);
	}

	private void checkForRoot(String key) {
		CheckBoxPreference p = (CheckBoxPreference) findPreference(key);
		if (p == null) {
			return;
		}

		if (p.isEnabled() && !Shell.SU.available()) {
			p.setChecked(false);
			SnackBarUtil.make(getActivity(), getString(R.string.root_not_available));
		}
	}

}

