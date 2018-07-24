package com.example.kishan.myapplication;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {

    private static MySingleton aInstance;
    private RequestQueue requestQueue;
    private static Context context;

    private MySingleton(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {

        if( requestQueue == null ) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized MySingleton getaInstance(Context context) {

        if(aInstance == null) {
            aInstance = new MySingleton(context);
        }
        return aInstance;
    }

    public void addToRequest(Request request){
        requestQueue.add(request);
    }
}
