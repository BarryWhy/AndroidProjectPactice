package com.dell.androidprojectpactice.ui.diyview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.dell.androidprojectpactice.R;
import com.dell.androidprojectpactice.util.annotation.AnnotationParser;
import com.dell.androidprojectpactice.util.annotation.LayoutBinder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dell on 2016/7/29.
 */
@LayoutBinder(R.layout.ac_diy_view)
public class CustomTitleViewActivity extends AppCompatActivity {
    @Bind(R.id.customTitleView)
    CustomTitleView customTitleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AnnotationParser.injectActivity(this);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        Toast.makeText(CustomTitleViewActivity.this, customTitleView.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}
