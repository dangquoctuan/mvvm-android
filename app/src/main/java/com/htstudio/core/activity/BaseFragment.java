package com.htstudio.core.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.htstudio.core.R;

public abstract class BaseFragment extends Fragment {

    protected abstract int getLayoutId();
    protected abstract boolean enableToolBar();
    private boolean initedLayout = false;

    protected LinearLayout rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!initedLayout){
            initedLayout = true;
            if (enableToolBar()){

            }else{
                rootView = new LinearLayout(getContext());
                View view = inflater.inflate(getLayoutId(), null, false);
                rootView.addView(view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }

        }

        return rootView;
    }
}
