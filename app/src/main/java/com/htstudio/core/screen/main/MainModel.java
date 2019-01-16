package com.htstudio.core.screen.main;

import com.htstudio.core.mvvm.BaseModel;

public class MainModel extends BaseModel<MainContract.Controller>
        implements MainContract.Model {

    MainModel(MainContract.Controller controller) {
        super(controller);
    }
}
