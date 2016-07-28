package com.dell.androidprojectpactice.ui.popview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dell.androidprojectpactice.R;

import rb.popview.PopField;

/**
 * Created by dell on 2016/7/4.
 */
public class PopviewActivity extends AppCompatActivity {

    private ImageView icon1;
    private ImageView icon2;
    private ImageView icon3;
    private TextView text;

    private PopField popField;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_popview);
        popField = PopField.attach2Window(this);
        initView();
        initEvent();
    }

    private void initEvent() {
        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View addView = layoutInflater.inflate(R.layout.ac_popview_image, null, false);
                AppCompatImageView newImage = (AppCompatImageView) addView.findViewById(R.id.icon);
                popField.popView(icon1, newImage);
            }
        });

        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popField.popView(icon2);
            }
        });

        icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View addView = layoutInflater.inflate(R.layout.ac_popview_image, null, false);
                ImageView newImage = (ImageView) addView.findViewById(R.id.icon);
                popField.popView(icon3, newImage, true);
            }
        });

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View addView = layoutInflater.inflate(R.layout.ac_popview_text, null, false);
                TextView newText = (TextView) addView.findViewById(R.id.text);
                newText.setText("New Sample Text");
                popField.popView(text, newText, true);
            }
        });
    }

    private void initView() {
        icon1 = (ImageView) findViewById(R.id.icon1);
        icon2 = (ImageView) findViewById(R.id.icon2);
        icon3 = (ImageView) findViewById(R.id.icon3);
        text = (TextView) findViewById(R.id.text);
    }
}
