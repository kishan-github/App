package com.example.kishan.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.print.PrintAttributes;
import android.renderscript.Sampler;
import android.view.*;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class SecondActivity extends Activity {

    private Button button;
    private EditText name;
    private EditText age;
    private EditText email;
    private EditText password;
    private DBConnectr db;
    private UserDetails user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initObjects();
        initViews();
        setButtonOnClick();
    }

    public void initObjects() {
        db = new DBConnectr(this);
        user = new UserDetails();
    }

    public void initViews() {
        name = (EditText) findViewById(R.id.txt_Name);
        age = (EditText)findViewById(R.id.txt_Age);
        email = (EditText)findViewById(R.id.txt_Email);
        password = (EditText)findViewById(R.id.txt_Password);
    }

    public void setButtonOnClick() {
        button = (Button)findViewById(R.id.Login_Button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                checkDetails();
            }
        });
    }

    public void checkDetails() {
        if(name.getText().toString().isEmpty()) {
            DialogParameters diagParams = new DialogParameters();

            diagParams.setIsSingleButton(true);
            diagParams.setMessage("Name cannot be empty");
            diagParams.setPositiveButtonText("OK");

            CustomDialog dialog = new CustomDialog(this, diagParams);
            //dialog.addViews(diagParams);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
            return;
        }
        if(age.getText().toString().isEmpty()) {
            DialogParameters diagParams = new DialogParameters();

            diagParams.setIsSingleButton(true);
            diagParams.setMessage("Name cannot be empty. Please enter a name");
            diagParams.setPositiveButtonText("OK");

            CustomDialog dialog = new CustomDialog(this, diagParams);
            //dialog.addViews(diagParams);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
            return;
        }
        if(email.getText().toString().isEmpty()) {
            DialogParameters diagParams = new DialogParameters();

            diagParams.setHeaderText("ARE YOU SURE?");
            diagParams.setMessage("Name cannot be empty");
            diagParams.setPositiveButtonText("CONFIRM");
            diagParams.setNegativeButtonText("CANCEL");

            CustomDialog dialog = new CustomDialog(this, diagParams);
            //dialog.addViews(diagParams);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
            return;
        }
        if(android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
            DialogParameters diagParams = new DialogParameters();

            diagParams.setHeaderText("ARE YOU SURE YOU WANT TO DO THIS?");
            diagParams.setMessage("Name cannot be empty. Please provide a name");
            diagParams.setPositiveButtonText("CONFIRM");
            diagParams.setNegativeButtonText("CANCEL");

            CustomDialog dialog = new CustomDialog(this, diagParams);
            //dialog.addViews(diagParams);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
            return;
        }
        if(password.getText().toString().isEmpty()) {
            DialogParameters diagParams = new DialogParameters();

            diagParams.setHeaderText("ARE YOU SURE YOU WANT TO DO THIS?");
            diagParams.setMessage("Name cannot be empty. Please provide a name");
            diagParams.setPositiveButtonText("CONFIRM");
            diagParams.setNegativeButtonText("CANCEL");

            CustomDialog dialog = new CustomDialog(this, diagParams);
            //dialog.addViews(diagParams);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
            return;
        }
        /*
        if(!db.checkUser(email.toString())) {

            user.setName(name.toString().trim());
            user.setAge(Integer.parseInt(age.toString()));
            user.setEmail(email.toString().trim());
            user.setPassword(password.toString());

            db.addUser(user);
            emptyInputEditText();
            finish();
            return;
        }*/
        emptyInputEditText();
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        name.setText(null);
        age.setText(null);
        email.setText(null);
        password.setText(null);
    }

}
