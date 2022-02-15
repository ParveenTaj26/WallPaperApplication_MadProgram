package com.example.wallpaper;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Button chngwallpaper;
    Timer mytimer;
    Drawable drawable;
    WallpaperManager wpm;
    int prev=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mytimer=new Timer();
        wpm=WallpaperManager.getInstance(this);
        chngwallpaper=findViewById(R.id.button);
        chngwallpaper.setOnClickListener(v -> setWallpaper());
}
    private void setWallpaper()
    {
        mytimer.schedule(new TimerTask() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void run() {
                if(prev==1)
                { // w1, w2, w3, w4 are name of the images, you can change the name
                    drawable=getResources().getDrawable(R.drawable.wall1);
                    prev=2;
                }
                else if(prev==2)
                {
                    drawable=getResources().getDrawable(R.drawable.wall2);
                    prev=3;
                }
                else if(prev==3)
                {
                    drawable=getResources().getDrawable(R.drawable.wall3);
                    prev=4;
                }
                else
                {
                    drawable=getResources().getDrawable(R.drawable.wall4);
                    prev=1;
                }
                Bitmap wallpaper=((BitmapDrawable)drawable).getBitmap();
                try {
                    wpm.setBitmap(wallpaper);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
        },0,30000);
    }
}