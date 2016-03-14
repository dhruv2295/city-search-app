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

    private EditText StateView;
    RequestQueue mRequestQueue;
    //String data = "";
    ArrayList<String> places = new ArrayList<String>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list);
        StateView = (EditText) findViewById(R.id.statename);
        StateView.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int aft) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>=3)
                {
                    places =display(s.toString());
                }

                else if(s.length()<3)
                {
                    places.clear();
                    list(places);
                }
                //call your function here of calculation here

            }
        });
//        StateView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
//
//
//                String view = StateView.getText().toString();
//                if (view.length() >= 3) {
//
//                    places =display(view);
//                    list(places);
//                    return true;
//                }
//
//                return false;
//            }
//        });


    }

    private void list(ArrayList<String> places) {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, places);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);


    }


    public ArrayList<String> display(String name)
{
    final ArrayList<String> places2 = new ArrayList<String>();
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
//                            final String descp = jsonObject.getString("Description");
//                            final String extraval1 = jsonObject.getString("ExtraValue1");
//                            final String extraval2 = jsonObject.getString("ExtraValue2");
//                            final String extraval3 = jsonObject.getString("ExtraValue3");
//                            final String extraval4 = jsonObject.getString("ExtraValue4");
//                            final String extraval5 = jsonObject.getString("ExtraValue5");
//                            final String extraval6 = jsonObject.getString("ExtraValue6");
//                            final String extraval7 = jsonObject.getString("ExtraValue7");
//                            final String extraval8 = jsonObject.getString("ExtraValue8");
//                            final String extraval9 = jsonObject.getString("ExtraValue9");

                            places2.add(title);


                            listView.setOnItemClickListener(
                                    new AdapterView.OnItemClickListener() {
                                     public void onItemClick(AdapterView parent, View v, int position, long id)
                                     {
                                        callsecond(ids);//,descp,extraval1,extraval2,extraval3,extraval4,extraval5,extraval6,extraval7,extraval8,extraval9);

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
//    private UserLoginTask mAuthTask = null;



//    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
//        InputStream is = null;
//        private final String state;
//        private  String id,title, description,extraval1,extraval2,extraval3,extraval4,extraval5,extraval6;
//
//        UserLoginTask(String statename) {
//            state = statename;
//        }
//
//        String text;
//
//        @Override
//        protected Boolean doInBackground(Void... params) {
//            String link;
//            link = "http://test.maheshwari.org/services/testwebservice.asmx/SuggestCity?tryvalue="+state ;
//
//            try {
//                URL url = new URL(link);
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setRequestMethod("GET");
//                conn.setDoInput(true);
//                conn.connect();
//                int response = conn.getResponseCode();
//                Log.d("Well!", "The response is: " + response);
//                is = conn.getInputStream();
//
//                // Convert the InputStream into a string
//                String contentAsString = readIt(is);
//             //   return contentAsString;
//            }
//
//            catch (IOException e)
//            {
//                e.printStackTrace();
//                return false;
//            }
//
////            HttpClient Client = new DefaultHttpClient();
////            HttpGet httpget = new HttpGet(URL);
////            ResponseHandler<String> responseHandler = new BasicResponseHandler();
////            try {
////                text = Client.execute(httpget, responseHandler);
////                Log.e("Login text", text + " ");
////            } catch (IOException e) {
////                e.printStackTrace();
////                return false;
////            }
////
//
//            return true;
//        }
//
//        @Override
//        protected void onPostExecute(final Boolean success) {
//            mAuthTask = null;
//
//            JSONObject ob;
//            String flag=null;
//            String id = null;
//            if (success) {
//                try {
//                    ob = new JSONObject(text);
//                    id = ob.getString("Id");
//                    title = ob.getString("Title");
//                    description = ob.getString("Description");
//                    extraval1 = ob.getString("ExtraValue1");
//                    extraval2 = ob.getString("ExtraValue2");
//                    extraval3 = ob.getString("ExtraValue3");
//                    extraval4 = ob.getString("ExtraValue4");
//                    extraval5 = ob.getString("ExtraValue5");
//                    extraval6 = ob.getString("ExtraValue6");
//
//                    if(flag.equals("1")){
//
//                        SharedPreferences se = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
//                        SharedPreferences.Editor e = se.edit();
//                        e.putString("id",id);
//                        e.putString("number", state);
//                        e.apply();
//                        finish();
//
//                    }
//                    else {
//                        StateView.setError(getString(R.string.error_incorrect_password));
//                        StateView.requestFocus();
//                    }
//                }
//
//                catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//        }
//
//        @Override
//        protected void onCancelled() {
//            mAuthTask = null;
//        }
//    }


public void callsecond(String id)//,String title,String descp,String extraval1,String extraval2,String extraval3,String
       // extraval4,String extraval5,String extraval6,String extraval7,String extraval8,String extraval9 )
{
    Intent intent = new Intent(this, Details.class);
    intent.putExtra("Id", id);
//    intent.putExtra("title", title);
//    intent.putExtra("Description", descp);
//    intent.putExtra("ExtraValue1", extraval1);
//    intent.putExtra("ExtraValue2", extraval2);
//    intent.putExtra("ExtraValue3", extraval3);
//    intent.putExtra("ExtraValue4", extraval4);
//    intent.putExtra("ExtraValue5", extraval5);
//    intent.putExtra("ExtraValue6", extraval6);
//    intent.putExtra("ExtraValue7", extraval7);
//    intent.putExtra("ExtraValue8", extraval8);
//    intent.putExtra("ExtraValue9", extraval9);
    startActivity(intent);

}



}
