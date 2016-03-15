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
       final String ids = intent.getStringExtra("Id");

        final TextView city= (TextView) findViewById(R.id.textView2);
        final TextView state= (TextView) findViewById(R.id.textView3);
        final TextView statecode= (TextView) findViewById(R.id.textView4);
        final TextView country= (TextView) findViewById(R.id.textView5);
        final TextView countrycode= (TextView) findViewById(R.id.textView6);
        final TextView isd= (TextView) findViewById(R.id.textView7);
        final TextView gplace= (TextView) findViewById(R.id.textView8);
        final TextView lat= (TextView) findViewById(R.id.textView9);
        final TextView longi= (TextView) findViewById(R.id.textView10);
        final TextView status= (TextView) findViewById(R.id.textView11);
        final TextView cityid= (TextView) findViewById(R.id.textView12);


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

                        final String cityname = jsonObject.getString("CityName");
                        final String statename = jsonObject.getJSONObject("State").getString("StateName");
                        final String scode = jsonObject.getJSONObject("State").getString("StateCode");
                        final String countryname = jsonObject.getJSONObject("Country").getString("CountryName");
                        final String countrycodes = jsonObject.getJSONObject("Country").getString("CountryCode");
                        final String isdc = jsonObject.getJSONObject("Country").getString("IsdCode");
                        final String gplaceid = jsonObject.getString("GPlaceId");
                        final String latitude = jsonObject.getString("Latitude");
                        final String longitude = jsonObject.getString("Longitude");
                        final String stats = jsonObject.getString("ActiveStatus");

                    city.setText(cityname);
                    state.setText(statename);
                    statecode.setText(scode);
                    country.setText(countryname);
                    countrycode.setText(countrycodes);
                    isd.setText(isdc);
                    gplace.setText(gplaceid);
                    lat.setText(latitude);
                    longi.setText(longitude);
                    status.setText(stats);
                    cityid.setText(ids);


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
