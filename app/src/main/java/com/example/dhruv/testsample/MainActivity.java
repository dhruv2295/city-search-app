package com.example.dhruv.testsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RequestQueue mRequestQueue;
    //String data = "";
    ArrayList<String> places = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list);
        EditText stateView = (EditText) findViewById(R.id.statename);
        stateView.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int aft) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() >= 3) {
                    places = display(s.toString());
                } else if (s.length() < 3) {
                    places.clear();
                    list(places);
                }

            }
        });



    }

    private void list(ArrayList<String> places) {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, places);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);


    }


    public ArrayList<String> display(String name)
{
    final ArrayList<String> places2 = new ArrayList<>();
    mRequestQueue = Volley.newRequestQueue(this);
    String link;
    link = "http://test.maheshwari.org/services/testwebservice.asmx/SuggestCity?tryvalue="+name;
    Log.d("link:", link);

    JsonArrayRequest jsArrRequest = new JsonArrayRequest(Request.Method.GET, link, null, new Response.Listener<JSONArray>()

    {
                @Override
                public void onResponse(JSONArray response) {
                    Log.d("response:", response.toString());
                    try{
                        for(int i=0; i < response.length(); i++){

                            JSONObject jsonObject = response.getJSONObject(i);
                            final String ids = jsonObject.getString("Id");
                            final String title = jsonObject.getString("Title");

                            places2.add(title);


                            listView.setOnItemClickListener(
                                    new AdapterView.OnItemClickListener() {
                                     public void onItemClick(AdapterView parent, View v, int position, long id)
                                     {
                                        callsecond(ids);
                                     }
                            });
                        }
                        list(places2);
                      //  mTxtDisplay.setText(data);
                    }catch(JSONException e){e.printStackTrace();}


                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    // TODO Auto-generated method stub
                    Log.e("Volley","Error"+error);
                }
            });
    mRequestQueue.add(jsArrRequest);

return places2;
}



public void callsecond(String id){
    Intent intent = new Intent(this, Details.class);
    intent.putExtra("Id", id);

    startActivity(intent);

}



}
