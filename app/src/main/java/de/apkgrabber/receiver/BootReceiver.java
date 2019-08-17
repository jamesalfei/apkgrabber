package de.apkgrabber.receiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import de.apkgrabber.util.AlarmUtil;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EReceiver;


@EReceiver
public class BootReceiver
        extends BroadcastReceiver {


    @Bean
    AlarmUtil alarmUtil;


    @Override
    public void onReceive(
            Context context,
            Intent intent
    ) {
        alarmUtil.setAlarmFromOptions();
    }


}

