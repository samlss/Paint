package com.iigo.paint;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * @author SamLeung
 * @Emial 729717222@qq.com
 * @date 2018/6/21 0021 17:52
 */
public class FilterActivity extends AppCompatActivity {
    private FilterView filterView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        filterView = findViewById(R.id.fv_filter);
    }

    public void onDrawNormal(View view){
        filterView.drawNormal();
    }

    public void onDrawNegative(View view){
        filterView.drawNegative();
    }

    public void onDrawRetro(View view){
        filterView.drawRetro();
    }

    public void onDrawFair(View view){
        filterView.drawFair();
    }

    public void onDrawBAndW(View view){
        filterView.drawBlackAndWhite();
    }

    public void onDrawChange(View view){
        filterView.drawChange();
    }
}
