package com.nj.zhihu.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.nj.zhihu.R;
import com.nj.zhihu.ui.fragment.SettingFragment;

public class SettingActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        mToolbar.setTitle("设置");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getFragmentManager().beginTransaction()
                .add(R.id.container, new SettingFragment())
                .commit();
    }
}
