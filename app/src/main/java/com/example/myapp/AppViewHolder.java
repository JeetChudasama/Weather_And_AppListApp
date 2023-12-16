package com.example.myapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class AppViewHolder extends RecyclerView.ViewHolder {
    public ImageView appIconImageView;
    public TextView appNameTextView;

    public AppViewHolder(View itemView) {
        super(itemView);
        appIconImageView = itemView.findViewById(R.id.app_icon_image_view);
        appNameTextView = itemView.findViewById(R.id.app_name);
    }

    public void bind(AppInfo appInfo) {
        appNameTextView.setText(appInfo.getAppName());
        appIconImageView.setImageDrawable(appInfo.getAppIcon());
    }
}
