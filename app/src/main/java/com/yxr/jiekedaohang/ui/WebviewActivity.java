package com.yxr.jiekedaohang.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.yxr.jiekedaohang.R;
import com.yxr.jiekedaohang.yxwebview.LxWebContainerView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by icer on 16/6/7.
 */
public class WebviewActivity extends AppCompatActivity {


    public static final String AGR_URL = "agr_url";

    @Bind(R.id.yxwb_h5web_container)
    LxWebContainerView mYxwbH5webContainer;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        String arg_url = getIntent().getStringExtra(AGR_URL);
        mYxwbH5webContainer.loadUrl(arg_url);

        setSupportActionBar(mToolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // 设置主标题及其颜色
        mToolbar.setTitle("AndroidViewDemo");
        mToolbar.setTitleTextColor(Color.WHITE);

        // 设置次标题及其颜色
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }
}
