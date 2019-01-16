package com.htstudio.core.screen.main;

import com.htstudio.core.mvvm.BaseView;
import com.htstudio.core.R;

public class MainView extends BaseView<MainContract.Controller>
        implements MainContract.View {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }
}

