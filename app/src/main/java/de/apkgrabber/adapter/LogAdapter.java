package de.apkgrabber.adapter;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import de.apkgrabber.model.LogMessage;
import de.apkgrabber.util.MyBus;
import de.apkgrabber.view.LogView;
import de.apkgrabber.view.LogView_;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;


@EBean
public class LogAdapter
        extends ArrayAdapter<LogMessage> {


    @RootContext
    Activity context;

    @Bean
    MyBus mBus;


    LogAdapter(
            Context context
    ) {
        super(context, 0);
    }


    @Override
    @NonNull
    public View getView(
            int position,
            View convertView,
            @NonNull ViewGroup parent
    ) {
        LogView log;

        if (convertView == null) {
            log = LogView_.build(context);
        } else {
            log = (LogView) convertView;
        }

        log.bind(getItem(position));

        return log;
    }


    @Override
    public void notifyDataSetChanged(
    ) {
        super.notifyDataSetChanged();
    }


}



