package com.example.sns_project.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.sns_project.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class covid19 extends AppCompatActivity {

    private TextView date,recovered,infected,death;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid);


        date=findViewById(R.id.date_id);
        infected=findViewById(R.id.infected_id);
        recovered=findViewById(R.id.recovered_id);
        death=findViewById(R.id.death_id);

        //url request

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://pomber.github.io/covid19/timeseries.json";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        String country ="Korea, South";
                        try {
                            JSONArray jsonArray= response.getJSONArray(country);
                            JSONObject jsonObject=jsonArray.getJSONObject(jsonArray.length()-1);
                            String datee= jsonObject.getString("date");
                            String confirmedd= jsonObject.getString("confirmed");
                            String deathss= jsonObject.getString("deaths");
                            String recoveredd= jsonObject.getString("recovered");

                            date.setText("Date:"+datee);
                            infected.setText("confirmed:"+ confirmedd);
                            death.setText("death:"+deathss);
                            recovered.setText("recovered:"+recoveredd);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });



        requestQueue.add(jsonObjectRequest);

    }
}
