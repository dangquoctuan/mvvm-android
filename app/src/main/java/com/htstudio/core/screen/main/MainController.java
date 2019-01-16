package com.htstudio.core.screen.main;

import android.support.v4.app.FragmentActivity;

import com.htstudio.core.mvvm.BaseController;

public class MainController extends BaseController<MainContract.View, MainContract.Model>
        implements MainContract.Controller {

    public MainController(FragmentActivity containerActivity, int containerFrame) {
        super(containerActivity, containerFrame);
    }

    @Override
    public MainContract.View onCreateView() {
        return new MainView();
    }

    @Override
    public MainContract.Model onCreateModel() {
        return new MainModel(this);
    }
}
