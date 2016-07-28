package com.dell.androidprojectpactice.ui.blankapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dell.androidprojectpactice.R;

import org.blankapp.validation.Rule;
import org.blankapp.validation.ValidationError;
import org.blankapp.validation.ValidationListener;
import org.blankapp.validation.Validator;
import org.blankapp.validation.validators.DateValidator;

import java.util.List;

/**
 * Created by dell on 2016/7/5.
 */
public class ValidationActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private Validator validator = new Validator();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_validation);
        editText = (EditText) findViewById(R.id.edit);
        button = (Button) findViewById(R.id.submit);
        initEvent();
    }

    private void initEvent() {
        // 邮箱必须输入，必须为邮箱格式
        validator.add(Rule.with(editText).required().email());
        // 用户名必须输入，只能输入字母及下划线
        validator.add(Rule.with(editText).required().alphaDash());
        validator.add(Rule.with(editText).required().minLength(2).maxLength(32));
        // 密码必须输入，最小长度为6位，最大长度为32位
        validator.add(Rule.with(editText).required().minLength(6).maxLength(32));
        // 生日必须输入，格式为yyyy-MM-dd（1991-06-01）并且是今天之前的日期
        validator.add(Rule.with(editText).required().date("yyyy-MM-dd").before(DateValidator.TODAY));
        // 年龄必须输入，并且为 18 ~ 60 岁
        validator.add(Rule.with(editText).required().between(18, 60));
        // 简介必须输入，并且最大长度不能超过255个字符
        validator.add(Rule.with(editText).required().maxLength(255));
        // 用户协议必须接受 该规则仅支持 CompoundButton 仅其派生控件
        validator.add(Rule.with(editText).accepted());

        validator.setValidatorListener(new ValidationListener() {
            @Override
            public void onValid() {
                Toast.makeText(ValidationActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInValid(List<ValidationError> errors) {
                for (ValidationError error : errors) {
                    Log.w("MainActivity", "Id:" + error.view().getId());
                    for (String key : error.errorMessages().keySet()) {
                        Log.e("MainActivity", error.errorMessages().get(key));
                    }
                }
                Toast.makeText(ValidationActivity.this, "验证失败", Toast.LENGTH_SHORT).show();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
            }
        });
    }
}

