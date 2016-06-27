package com.example.azalea.myapplication;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView= (TextView) findViewById(R.id.text);
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels; // 宽度（PX）
        int height = metric.heightPixels; // 高度（PX）
        float density = metric.density; // 密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi; // 密度DPI（120 / 160 / 240）
        textView.setText("");
        textView.append("宽度＝" + width);
        textView.append("\n");
        textView.append("高度＝" + height);
        textView.append("\n");
        textView.append("密度＝" + density);
        textView.append("\n");
        textView.append("DPI="+densityDpi);
        textView.append("\n");

        String total=getTotalMemory(this);
        if(TextUtils.isEmpty(total)){
            total="";
        }else{
            long a=Long.parseLong(total);
            int b= (int) (a/1024/1024);
            textView.append("内存="+b+"M");
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static String getTotalMemory(Context c) {
        // memInfo.totalMem not supported in pre-Jelly Bean APIs.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
            ActivityManager am = (ActivityManager) c.getSystemService(Context.ACTIVITY_SERVICE);
            am.getMemoryInfo(memInfo);
            if (memInfo != null) {
                return memInfo.totalMem+"";
            } else {
                return 0+"";
            }
        } else {
            long totalMem = 0;
                String str1 = "/proc/meminfo";
                String str2="";
                try {
                    FileReader fr = new FileReader(str1);
                    BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
                    while ((str2 = localBufferedReader.readLine()) != null) {
                    }
                } catch (IOException e) {
                }
            return str2;
        }
    }
}
