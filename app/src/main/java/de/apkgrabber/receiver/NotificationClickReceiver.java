package de.apkgrabber.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import de.apkgrabber.activity.MainActivity_;
import de.apkgrabber.model.AppState;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EReceiver;

@EReceiver
public class NotificationClickReceiver extends BroadcastReceiver {

	@Bean
	AppState mAppState;

	@Override
	public void onReceive(Context context, Intent intent) {
		mAppState.setFirstStart(false);
		mAppState.setSelectedTab(1);
		MainActivity_.intent(context).extra("isFromNotification", true).flags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP).start();
	}

}

