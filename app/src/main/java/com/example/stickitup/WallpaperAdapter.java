package com.example.stickitup;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.WallpaperViewHolder> {

    private Context context;
    private List<Integer> wallpaperList;

    public WallpaperAdapter(Context context, List<Integer> wallpaperList) {
        this.context = context;
        this.wallpaperList = wallpaperList;
    }

    @NonNull
    @Override
    public WallpaperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.wallpaper_item, parent, false);
        return new WallpaperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WallpaperViewHolder holder, int position) {
        int imageResource = wallpaperList.get(position);
        holder.wallpaperImageView.setImageResource(imageResource);

        holder.wallpaperImageView.setOnClickListener(v -> {
            Intent intent = new Intent(context, WallpaperPreviewActivity.class);
            intent.putExtra("wallpaper_res", imageResource);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return wallpaperList.size();
    }

    static class WallpaperViewHolder extends RecyclerView.ViewHolder {
        ImageView wallpaperImageView;

        public WallpaperViewHolder(@NonNull View itemView) {
            super(itemView);
            wallpaperImageView = itemView.findViewById(R.id.wallpaperImageView);
        }
    }
}
