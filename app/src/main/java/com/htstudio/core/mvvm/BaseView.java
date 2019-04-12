package com.htstudio.core.mvvm;

import android.view.View;

import com.htstudio.core.activity.BaseFragment;
import com.htstudio.core.mvvm.interf.IController;
import com.htstudio.core.mvvm.interf.IView;


public abstract class BaseView<C extends IController> extends BaseFragment implements IView<C> {

    private C controller;
    protected View mRootView;


    @Override
    public C getController() {
        return controller;
    }

    @Override
    public void setController(C controller) {
        this.controller = controller;
    }
}
