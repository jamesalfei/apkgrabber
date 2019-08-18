package de.apkgrabber.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import de.apkgrabber.service.UpdaterService;
import de.apkgrabber.service.UpdaterService_;
import org.androidannotations.annotations.EReceiver;

@EReceiver
public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent updaterIntent = new Intent(context, UpdaterService_.class).putExtra(UpdaterService.isFromAlarmExtra, true);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			context.startForegroundService(updaterIntent);
		} else {
			context.startService(updaterIntent);
		}
	}

}

