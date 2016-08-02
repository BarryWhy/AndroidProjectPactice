package com.dell.androidprojectpactice.ui.spinner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.dell.androidprojectpactice.R;
import com.dell.androidprojectpactice.util.annotation.AnnotationParser;
import com.dell.androidprojectpactice.util.annotation.LayoutBinder;
import com.piotrek.customspinner.CustomSpinner;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dell on 2016/8/2.
 */
@LayoutBinder(R.layout.ac_custom_spinner)
public class CustomSpinnerActivity extends AppCompatActivity {
    @Bind(R.id.spinner)
    CustomSpinner spinner;
    @Bind(R.id.text)
    TextView text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AnnotationParser.injectActivity(this);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        String[] types = getResources().getStringArray(R.array.number_array);
        spinner.initializeStringValues(types, "Please choose");
        bindEvent();
    }

    private void bindEvent() {
        spinner.setSpinnerEventsListener(new CustomSpinner.OnSpinnerEventsListener() {
            @Override
            public void onSpinnerOpened() {
                text.setText("Spinner Open");
            }

            @Override
            public void onSpinnerClosed() {
                text.setText("Spinner close");
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!"Please choose".equals(adapterView.getSelectedItem().toString())) {
                    Toast.makeText(CustomSpinnerActivity.this, adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
