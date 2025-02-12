package com.example.stickitup;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WallpaperAdapter adapter;
    private List<Integer> wallpaperList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.wallpaperRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        loadWallpapers();
    }

    private void loadWallpapers() {
        wallpaperList = new ArrayList<>();
        Field[] fields = R.drawable.class.getDeclaredFields();

        for (Field field : fields) {
            try {
                if (field.getName().startsWith("wallpaper_")) {
                    int resId = field.getInt(null);
                    wallpaperList.add(resId);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        adapter = new WallpaperAdapter(this, wallpaperList);
        recyclerView.setAdapter(adapter);
    }
}
