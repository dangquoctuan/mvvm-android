package com.htstudio.core.mvvm.interf;

public interface IView<C extends IController> {
    void setController(C controller);
    C getController();
}
