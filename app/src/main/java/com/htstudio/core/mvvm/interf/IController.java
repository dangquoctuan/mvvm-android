package com.htstudio.core.mvvm.interf;

public interface IController<V extends IView, M extends IModel> {
    V getView();
    M getModel();
    V onCreateView();
    M onCreateModel();
    void presentView();
}
