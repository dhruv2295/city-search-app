package com.example.dhruv.testsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Details extends AppCompatActivity {
    RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        String ids = intent.getStringExtra("Id");

        final  TextView city= (TextView) findViewById(R.id.textView2);
        final  TextView state= (TextView) findViewById(R.id.textView3);
        final TextView statecode= (TextView) findViewById(R.id.textView4);
        final TextView  country= (TextView) findViewById(R.id.textView5);
        final TextView countrycode= (TextView) findViewById(R.id.textView6);
        final TextView isd= (TextView) findViewById(R.id.textView7);
        final TextView gplace= (TextView) findViewById(R.id.textView8);
        final TextView lat= (TextView) findViewById(R.id.textView9);
        final TextView longi= (TextView) findViewById(R.id.textView10);
        final TextView status= (TextView) findViewById(R.id.textView11);


        mRequestQueue = Volley.newRequestQueue(this);
        String link;
        link = "http://test.maheshwari.org/services/testwebservice.asmx/GetCity?cityId="+ids;
        Log.d("link:", link);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, link, null, new Response.Listener<JSONObject>()

        {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.d("response:", jsonObject.toString());
                try{

                       // JSONObject jsonObject = new JSONObject(response.toString());
                        final String cityname = jsonObject.getString("CityName");
                        final String statename = jsonObject.getJSONObject("State").getString("StateName");
                        final String scode = jsonObject.getJSONObject("State").getString("StateCode");
                        final String extraval2 = jsonObject.getJSONObject("State").getString("StateCode");
                        final String extraval3 = jsonObject.getString("ExtraValue3");
                        final String extraval4 = jsonObject.getString("ExtraValue4");
                        final String extraval5 = jsonObject.getString("ExtraValue5");
                        final String extraval6 = jsonObject.getString("ExtraValue6");
                        final String extraval7 = jsonObject.getString("ExtraValue7");
                        final String extraval8 = jsonObject.getString("ExtraValue8");
                        final String extraval9 = jsonObject.getString("ExtraValue9");
                    city.setText(cityname);
                    state.setText(statename);
                    statecode.setText(scode);
                    country.setText(extraval2);
                    countrycode.setText(extraval3);
                    isd.setText(extraval4);
                    gplace.setText(extraval5);
                    lat.setText(extraval6);
                    longi.setText(extraval7);
                    status.setText(extraval8);


                }catch(JSONException e){e.printStackTrace();}


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.e("Volley","Error"+error);
            }
        });
        mRequestQueue.add(jsObjRequest);


    }



}
