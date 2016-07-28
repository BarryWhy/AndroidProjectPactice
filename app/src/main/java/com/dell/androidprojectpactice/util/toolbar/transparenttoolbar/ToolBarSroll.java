package com.dell.androidprojectpactice.util.toolbar.transparenttoolbar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by dell on 2016/7/4.
 */
public class ToolBarSroll extends ScrollView {

    private TransparentToolBar mTransparentToolBra;

    public ToolBarSroll(Context context) {
        super(context);
    }

    public ToolBarSroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ToolBarSroll(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ToolBarSroll(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        mTransparentToolBra.setChangeTop(t);
    }

    //注入ToolBar
    public void setToolBar(TransparentToolBar toolBar) {
        mTransparentToolBra = toolBar;
    }
}
