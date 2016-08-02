package com.dell.androidprojectpactice.ui.diyview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dell.androidprojectpactice.R;
import com.dell.androidprojectpactice.util.annotation.AnnotationParser;
import com.dell.androidprojectpactice.util.annotation.LayoutBinder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dell on 2016/7/29.
 */
@LayoutBinder(R.layout.ac_diy_view)
public class CustomTitleViewActivity extends AppCompatActivity {
    @Bind(R.id.customTitleView)
    CustomTitleView customTitleView;
    @Bind(R.id.params)
    EditText params;
    @Bind(R.id.submit)
    Button submit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AnnotationParser.injectActivity(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.submit)
    public void onClick() {
        String str = params.getText().toString();
        String code = customTitleView.getText().toString();
        check(str, code);
    }

    private void check(String str, String code) {
        if (!"".equals(str) && str.equals(code)) {
            Toast.makeText(CustomTitleViewActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(CustomTitleViewActivity.this, "验证失败", Toast.LENGTH_SHORT).show();
        }
    }
}


