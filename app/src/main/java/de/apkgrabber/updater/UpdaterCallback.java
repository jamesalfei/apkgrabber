package de.apkgrabber.updater;


import de.apkgrabber.model.Update;

interface UpdaterCallback {


    void onStart();

    void onUpdate(Update update);

    void onError(Throwable e);

    void onFinish(String m);


}

