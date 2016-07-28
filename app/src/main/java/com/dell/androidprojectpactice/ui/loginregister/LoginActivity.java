package com.dell.androidprojectpactice.ui.loginregister;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.dell.androidprojectpactice.R;
import com.dell.androidprojectpactice.util.annotation.AnnotationParser;
import com.dell.androidprojectpactice.util.annotation.LayoutBinder;

import shem.com.materiallogin.MaterialLoginView;
import shem.com.materiallogin.MaterialLoginViewListener;

/**
 * Created by dell on 2016/7/6.
 */
@LayoutBinder(R.layout.ac_login)
public class LoginActivity extends AppCompatActivity {

    private MaterialLoginView materialLoginView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ac_login);
        AnnotationParser.injectActivity(this);
        materialLoginView = (MaterialLoginView) findViewById(R.id.login);
        initEvent();
    }

    private void initEvent() {
        materialLoginView.setListener(new MaterialLoginViewListener() {
            @Override
            public void onRegister(TextInputLayout registerUser, TextInputLayout registerPass, TextInputLayout registerPassRep) {
                if (registerUser.getEditText() != null && registerPass.getEditText() != null && registerPassRep.getEditText() != null) {
                    String user = registerUser.getEditText().getText().toString();
                    if (user.isEmpty()) {
                        registerUser.setError("User name can't be empty");
                        return;
                    }
                    registerUser.setError("");

                    String pass = registerPass.getEditText().getText().toString();
                    if (pass.isEmpty()) {
                        registerPass.setError("Password can't be empty");
                        return;
                    }
                    registerPass.setError("");

                    String passRep = registerPassRep.getEditText().getText().toString();
                    if (!pass.equals(passRep)) {
                        registerPassRep.setError("Passwords are different");
                        return;
                    }
                    registerPassRep.setError("");
                    Toast.makeText(LoginActivity.this, "Register success!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onLogin(TextInputLayout loginUser, TextInputLayout loginPass) {
                if (loginUser.getEditText() != null && loginPass.getEditText() != null) {
                    String user = loginUser.getEditText().getText().toString();
                    if (user.isEmpty()) {
                        loginUser.setError("User name can't be empty");
                        return;
                    }
                    loginUser.setError("");

                    String pass = loginPass.getEditText().getText().toString();
                    if (!pass.equals(user)) {
                        loginPass.setError("Wrong password");
                        return;
                    }
                    loginPass.setError("");
                    Toast.makeText(LoginActivity.this, "Login success!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
