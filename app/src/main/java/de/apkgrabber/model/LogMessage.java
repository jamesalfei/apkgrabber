package de.apkgrabber.model;


public class LogMessage {


    public static final int SEVERITY_INFO = 0;
    public static final int SEVERITY_WARNING = 1;
    public static final int SEVERITY_ERROR = 2;


    private String mTitle;
    private String mMessage;
    private int mSeverity;
    private long mTime;


    public LogMessage(
            String title,
            String message,
            int severity
    ) {
        setTitle(title);
        setSeverity(severity);
        setMessage(message);
        mTime = System.currentTimeMillis();
    }


    public String getMessage(
    ) {
        return mMessage;
    }

    public void setMessage(
            String message
    ) {
        mMessage = message;
    }

    public long getTime(
    ) {
        return mTime;
    }

    public int getSeverity(
    ) {
        return mSeverity;
    }


    public void setSeverity(
            int severity
    ) {
        mSeverity = severity;
    }


    public String getTitle(
    ) {
        return mTitle;
    }


    public void setTitle(
            String title
    ) {
        mTitle = title;
    }


}

