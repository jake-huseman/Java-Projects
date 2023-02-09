package com.project.gamedr;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Random;

/**
 * Shows random match of users.
 * @author Chandler Sihom
 */
public class MatcherActivity extends AppCompatActivity {
    TextView user1ds;
    Button returnBtn;
    String url = "http://coms-309-048.class.las.iastate.edu:8080/matchPairs/random";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matcher);
        user1ds = findViewById(R.id.user1dp);
        user1ds.setMovementMethod(new ScrollingMovementMethod());
        returnBtn = findViewById(R.id.returnBtn3);


                RequestQueue requestQueue = Volley.newRequestQueue(MatcherActivity.this);
                JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String user1 = "Match Pair: \n";
                        try {
                            for (int i = 0; i < response.length();i++) {
                                int j = 0;
                                JSONObject json1 = response.getJSONObject(i);
                                Iterator<String> json2 = json1.keys();
                                while (j < json1.length() - 1) {
                                    j++;
                                    String key = json2.next();
                                    user1 += json1.get(key) + "\n";
                                }
                                user1 += "\n";
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        user1ds.setText(user1);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        user1ds.setText(error.toString());
                    }
                });
                requestQueue.add(jsonRequest);

                returnBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MatcherActivity.this, MatchsetActivity.class);
                        startActivity(intent);
                    }
                });
    }
}
