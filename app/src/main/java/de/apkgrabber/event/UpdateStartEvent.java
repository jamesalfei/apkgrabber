package de.apkgrabber.event;


public class UpdateStartEvent {


    private int mNumUpdates;


    public UpdateStartEvent(
            int n
    ) {
        mNumUpdates = n;
    }


    public int getNumUpdates(
    ) {
        return mNumUpdates;
    }


}


