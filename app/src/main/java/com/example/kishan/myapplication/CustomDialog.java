package com.example.kishan.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomDialog extends Dialog implements View.OnClickListener {

    public Activity c;
    public TextView Header, Message;
    public LinearLayout layout;
    public Button btnPositive, btnNegative;
    DialogParameters diagParams;

    public CustomDialog(Activity a, DialogParameters diag) {
        super(a);
        this.c = a;
        diagParams = diag;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_custom_dialog);

        initView();
        addViews(diagParams);
        //addTextViews();
        /*
        yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);*/
        btnPositive.setOnClickListener(this);
        btnNegative.setOnClickListener(this);

    }

    public void initView() {
        Header = (TextView)findViewById(R.id.txt_Header);
        Message = (TextView)findViewById(R.id.txt_Message);

        btnPositive = (Button) findViewById(R.id.btn_Positive);
        btnNegative = (Button) findViewById(R.id.btn_Negative);
    }

    public void addViews(DialogParameters diagPaeams) {
        if (diagPaeams.getHeaderText() == null) {
            /*
            ViewGroup view = (ViewGroup)Header.getParent();
            if (null != view)
                view.removeView(Header);*/
            layout = (LinearLayout)findViewById(R.id.main_Layout);
            layout.removeView(Header);
            Message.setText(diagPaeams.getMessage());
        } else {
            Header.setText(diagPaeams.getHeaderText());
            Message.setText(diagPaeams.getMessage());
        }

        if (diagPaeams.getIsSingleButton()) {
            ViewGroup view = (ViewGroup)btnNegative.getParent();
            if (null != view) {
                view.removeView(btnNegative);
                btnPositive.setText(diagPaeams.getPositiveButtonText());
            }
        } else {
            btnPositive.setText(diagPaeams.getPositiveButtonText());
            btnNegative.setText(diagPaeams.getNegativeButtonText());
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Positive:
                c.finish();
                break;
            case R.id.btn_Negative:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}
