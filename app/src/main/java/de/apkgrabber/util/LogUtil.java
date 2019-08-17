package de.apkgrabber.util;

import de.apkgrabber.model.LogMessage;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;


@EBean(scope = EBean.Scope.Singleton)
public class LogUtil {


    @Bean
    MyBus mBus;


    public void log(
            String title,
            String message,
            int severity
    ) {
        mBus.post(new LogMessage(title, message, severity));
    }


}

