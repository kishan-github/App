package com.example.kishan.myapplication;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SecondActivity extends Activity {

    private Button button;
    private EditText name;
    private EditText age;
    private EditText email;
    private EditText password;
    private DBConnectr db;
    private UserDetails user;
    private final String SERVER_URL = "http://192.168.42.237/register.php";
    public RequestQueue requestQueue;

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

            createDialog(diagParams);
            return;
        }
        if(age.getText().toString().isEmpty()) {
            DialogParameters diagParams = new DialogParameters();

            diagParams.setIsSingleButton(true);
            diagParams.setMessage("Name cannot be empty. Please enter a name");
            diagParams.setPositiveButtonText("OK");

            createDialog(diagParams);
            return;
        }
        if(email.getText().toString().isEmpty()) {
            DialogParameters diagParams = new DialogParameters();

            diagParams.setHeaderText("ARE YOU SURE?");
            diagParams.setMessage("Name cannot be empty");
            diagParams.setPositiveButtonText("CONFIRM");
            diagParams.setNegativeButtonText("CANCEL");

            createDialog(diagParams);
            return;
        }
        if(password.getText().toString().isEmpty()) {
            DialogParameters diagParams = new DialogParameters();

            diagParams.setHeaderText("ARE YOU SURE YOU WANT TO DO THIS?");
            diagParams.setMessage("Name cannot be empty. Please provide a name");
            diagParams.setPositiveButtonText("CONFIRM");
            diagParams.setNegativeButtonText("CANCEL");

            createDialog(diagParams);
            return;
        }

        startServerConnection();
        emptyInputEditText();
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

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        name.setText(null);
        age.setText(null);
        email.setText(null);
        password.setText(null);
    }

    /**
     * This method is to start the connection with the server
     */
    private void startServerConnection() {

        /*
        Cache cache = new DiskBasedCache(getCacheDir(), 1024*1024);
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);

        requestQueue.start();*/

        /*
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
*/





        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                SERVER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.toString().equalsIgnoreCase("SUCCESS")) {
                            DialogParameters diagParams = new DialogParameters();

                            diagParams.setIsSingleButton(true);
                            diagParams.setMessage(response + ": Registered successfuly");
                            diagParams.setPositiveButtonText("OK");
                            createDialog(diagParams);

                            //requestQueue.stop();
                        }else if (response.toString().equalsIgnoreCase("USER EXIST")){
                            DialogParameters diagParams = new DialogParameters();

                            diagParams.setIsSingleButton(true);
                            diagParams.setMessage(response + ": User already registered");
                            diagParams.setPositiveButtonText("OK");
                            createDialog(diagParams);
                        }
                        else {
                            DialogParameters diagParams = new DialogParameters();

                            diagParams.setIsSingleButton(true);
                            diagParams.setMessage("ERROR : " + response);
                            diagParams.setPositiveButtonText("OK");
                            createDialog(diagParams);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        DialogParameters diagParams = new DialogParameters();

                        diagParams.setIsSingleButton(true);
                        diagParams.setMessage(error.toString() + "Something went wrong!!!!!!!!!!");
                        diagParams.setPositiveButtonText("OK");
                        createDialog(diagParams);

                        error.printStackTrace();
                        //requestQueue.stop();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("NAME", name.getText().toString().trim());
                params.put("AGE", age.getText().toString().trim());
                params.put("EMAIL", email.getText().toString().trim());
                params.put("PASSWORD", password.getText().toString().trim());

                return params;
            }
        };

                MySingleton.getaInstance(getApplicationContext()).addToRequest(stringRequest);
                //requestQueue.add(stringRequest);
    }

}
