package com.htstudio.core.mvvm;


import com.htstudio.core.mvvm.interf.IController;
import com.htstudio.core.mvvm.interf.IModel;

public abstract class BaseModel<C extends IController> implements IModel<C> {
    private C controller;

    public C getController() {
        return controller;
    }

    public BaseModel(C controller){
        this.controller = controller;
    }
}
