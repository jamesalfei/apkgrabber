package de.apkgrabber.event;


public class InstalledAppTitleChange {


    private String mTitle;


    public InstalledAppTitleChange(
            String s
    ) {
        mTitle = s;
    }


    public String getTitle(
    ) {
        return mTitle;
    }


}


