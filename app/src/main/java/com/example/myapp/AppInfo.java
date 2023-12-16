package com.example.myapp;

import android.graphics.drawable.Drawable;

public class AppInfo {
    private String appName;
    private Drawable appIcon;

    public AppInfo(String appName, Drawable appIcon) {
        this.appName = appName;
        this.appIcon = appIcon;
    }

    public String getAppName() {
        return appName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }
}
