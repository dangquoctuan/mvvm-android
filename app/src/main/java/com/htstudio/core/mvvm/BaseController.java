package com.htstudio.core.mvvm;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.htstudio.core.mvvm.interf.IController;
import com.htstudio.core.mvvm.interf.IModel;
import com.htstudio.core.mvvm.interf.IView;


public abstract class BaseController<V extends IView, M extends IModel> implements IController<V, M> {

    private V mView;
    private M mModel;

    private FragmentActivity containerActivity;
    private int containerFrame;

    public BaseController(FragmentActivity containerActivity, int containerFrame) {
        this.containerActivity = containerActivity;
        this.containerFrame = containerFrame;
        this.mView = onCreateView();
        this.mModel = onCreateModel();
        mView.setController(this);
    }

    @Override
    public V getView() {
        return mView;
    }

    @Override
    public M getModel() {
        return mModel;
    }

    protected Activity getViewContext(){
        return containerActivity;
    }

    @Override
    public void presentView() {
        containerActivity
                .getSupportFragmentManager()
                .beginTransaction()
                .add(containerFrame, (Fragment) mView)
                .addToBackStack(((Fragment) mView).getClass().getSimpleName())
                .commit();

    }
}
