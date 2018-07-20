package com.example.kishan.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnReg, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initListners();
    }

    public void initViews() {
        btnReg = (Button) findViewById(R.id.btn_Register);
        btnLogin = (Button) findViewById(R.id.btn_Login);
    }

    public void initListners() {
        btnReg.setOnClickListener((View.OnClickListener) this);
        btnLogin.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Register: {
                openRegisterPage();
                break;
            }
            case R.id.btn_Login: {
                openLoginPage();
                break;
            }
        }

    }

    public void openLoginPage() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);

        startActivity(intent);
    }

    public void openRegisterPage() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);

        startActivity(intent);
    }

}
