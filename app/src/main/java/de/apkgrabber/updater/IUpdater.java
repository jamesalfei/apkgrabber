package de.apkgrabber.updater;


public interface IUpdater {


    String getResultUrl();

    String getResultCookie();

    int getResultVersionCode();

    Throwable getResultError();

    UpdaterStatus getResultStatus();

    String getResultVersion();

    boolean isBeta();


}


