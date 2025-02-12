package com.example.stickitup;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;

public class WallpaperPreviewActivity extends AppCompatActivity {

    private ImageView previewImageView;
    private int selectedWallpaper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper_preview);

        previewImageView = findViewById(R.id.previewImageView);
        Button setHomeScreenButton = findViewById(R.id.setHomeScreenButton);
        Button setLockScreenButton = findViewById(R.id.setLockScreenButton);
        Button setBothButton = findViewById(R.id.setBothButton);

        selectedWallpaper = getIntent().getIntExtra("wallpaper_res", 0);
        previewImageView.setImageResource(selectedWallpaper);

        setHomeScreenButton.setOnClickListener(v -> setWallpaper(WallpaperManager.FLAG_SYSTEM));
        setLockScreenButton.setOnClickListener(v -> setWallpaper(WallpaperManager.FLAG_LOCK));
        setBothButton.setOnClickListener(v -> setWallpaper(WallpaperManager.FLAG_SYSTEM | WallpaperManager.FLAG_LOCK));
    }

    private void setWallpaper(int flag) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(selectedWallpaper)).getBitmap();
        try {
            wallpaperManager.setBitmap(bitmap, null, true, flag);
            Toast.makeText(this, "Wallpaper Set!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to Set Wallpaper", Toast.LENGTH_SHORT).show();
        }
    }
}
