package de.apkgrabber.event;


public class SearchTitleChange {


    private String mTitle;


    public SearchTitleChange(
            String s
    ) {
        mTitle = s;
    }


    public String getTitle(
    ) {
        return mTitle;
    }


}


