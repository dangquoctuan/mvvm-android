package com.htstudio.core.activity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
