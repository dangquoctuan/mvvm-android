package com.htstudio.core.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.htstudio.core.R;

public class ToolBarView extends FrameLayout {
    public ToolBarView(Context context) {
        this(context,null);
    }

    public ToolBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.tool_bar_view, this,true);
    }


}
