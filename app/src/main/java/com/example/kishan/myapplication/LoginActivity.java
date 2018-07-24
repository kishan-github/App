package com.example.kishan.myapplication;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    EditText Email;
    EditText Password;
    Button LoginBtn;
    private final String SERVER_URL = "http://192.168.42.159/abc.php";
    public RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        setClickListner();
    }

    private void setClickListner() {
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginButtonClick();
            }
        });

    }

    private void onLoginButtonClick() {
        if(Email.getText().toString().isEmpty()) {
            DialogParameters diagParams = new DialogParameters();

            diagParams.setMessage("Email cannot be empty");
            diagParams.setIsSingleButton(true);
            diagParams.setPositiveButtonText("OK");

            createDialog(diagParams);
            return;
        }

        if(Password.getText().toString().isEmpty()) {
            DialogParameters diagParams = new DialogParameters();

            diagParams.setMessage("Password cannot be empty");
            diagParams.setIsSingleButton(true);
            diagParams.setPositiveButtonText("OK");

            createDialog(diagParams);
            return;
        }

        validateUser();
    }

    private void validateUser() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                SERVER_URL,
                (JSONObject) null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            response.getString("name");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    private void initViews() {
        Email = (EditText)findViewById(R.id.txt_Email);
        Password = (EditText)findViewById(R.id.txt_Password);
        LoginBtn = (Button)findViewById(R.id.btn_Login);
    }

    /**
     * This method is to create the alert.
     */
    private void createDialog(DialogParameters diagParams) {
        CustomDialog dialog = new CustomDialog(this, diagParams);
        //dialog.addViews(diagParams);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
