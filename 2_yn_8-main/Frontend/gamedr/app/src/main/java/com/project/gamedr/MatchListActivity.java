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
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Random;
/**
 * View list of matches.
 * @author Chandler Sihom
 */
public class MatchListActivity extends AppCompatActivity {
    Button returnBtn;
    TextView userlist;
    String nameUrl = "http://coms-309-048.class.las.iastate.edu:8080/users/ordered/username/asc";
    String genreUrl = "http://coms-309-048.class.las.iastate.edu:8080/users/ordered/username/des";
    Button sortByName;
    Button sortByGenre;
    TextView idDs;
    TextView emaillist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matchlist);

        returnBtn = findViewById(R.id.returnBtn2);
        userlist = findViewById(R.id.userlistTxt);
        sortByGenre = findViewById(R.id.sortGenre);
        sortByName = findViewById(R.id.sortName);
        idDs = findViewById(R.id.idTxt);
        emaillist = findViewById(R.id.emailTxt);
        userlist.setMovementMethod(new ScrollingMovementMethod());

        sortByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue requestQueue = Volley.newRequestQueue(MatchListActivity.this);
                JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, nameUrl, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String username = "";
                        String id = "";
                        String email = "";
                        for (int i=0; i<response.length(); i++) {
                            try {
                                username += response.getJSONObject(i).get("username") + " \n";
                                id += response.getJSONObject(i).get("id") + " \n";
                                email += response.getJSONObject(i).get("email") + " \n";
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        userlist.setText(username);
                        idDs.setText(id);
                        emaillist.setText(email);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        userlist.setText(error.toString());
                    }
                });
                requestQueue.add(jsonRequest);
            }
        });

        sortByGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue requestQueue = Volley.newRequestQueue(MatchListActivity.this);
                JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, genreUrl, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String username = "";
                        String id = "";
                        String email = "";
                        for (int i=0; i<response.length(); i++) {
                            try {
                                username += response.getJSONObject(i).get("username") + " \n";
                                id += response.getJSONObject(i).get("id") + " \n";
                                email += response.getJSONObject(i).get("email") + " \n";
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        userlist.setText(username);
                        idDs.setText(id);
                        emaillist.setText(email);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        userlist.setText(error.toString());
                    }
                });
                requestQueue.add(jsonRequest);
            }
        });

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MatchListActivity.this, MatchsetActivity.class);
                startActivity(intent);
            }
        });
    }
}
