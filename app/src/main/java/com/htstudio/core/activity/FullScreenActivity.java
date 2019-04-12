package com.htstudio.core.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.WindowManager;

import com.htstudio.core.R;

public abstract class FullScreenActivity extends BaseActivity {
    @Override
    protected void onCreateActivity(@Nullable Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setTheme(R.style.FullScreen);
    }
}
