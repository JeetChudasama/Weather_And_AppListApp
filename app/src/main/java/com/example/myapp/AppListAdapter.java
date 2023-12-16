package com.example.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AppListAdapter extends RecyclerView.Adapter<AppViewHolder> {

    private Context context;
    private ArrayList<AppInfo> appInfos;

    public AppListAdapter(Context context, ArrayList<AppInfo> appInfos) {
        this.context = context;
        this.appInfos = appInfos;
    }

    @Override
    public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AppViewHolder holder, int position) {
        AppInfo appInfo = appInfos.get(position);
        holder.bind(appInfo);
    }

    @Override
    public int getItemCount() {
        return appInfos.size();
    }
}
