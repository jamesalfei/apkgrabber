package de.apkgrabber.model.APKMirror;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AppExistsResponseHeaders {


    @SerializedName("Allow")
    @Expose
    private String allow;


    public String getAllow(
    ) {
        return allow;
    }


    public void setAllow(
            String allow
    ) {
        this.allow = allow;
    }


}

