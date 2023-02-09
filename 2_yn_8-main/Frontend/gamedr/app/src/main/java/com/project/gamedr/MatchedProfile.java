package com.project.gamedr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MatchedProfile extends AppCompatActivity {

    TextView matchedName;
    TextView matchedUsername;
    TextView matchedDescription;
    Button backButton;
    String matchedId;
    String matchedURL = "";
    String fullname;
    String username;
    String desc;
    String matchedUserAndId;
    String [] matchedUserSplit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matched_profile);

        matchedName = findViewById(R.id.matched_profile_name);
        matchedUsername = findViewById(R.id.matched_profile_username);
        matchedDescription = findViewById(R.id.matched_user_description);
        backButton = findViewById(R.id.matched_user_back_button);

        matchedUserAndId= getIntent().getStringExtra("user");
        matchedUserSplit = matchedUserAndId.split(" ");

        matchedURL = "http://coms-309-048.class.las.iastate.edu:8080/users/" + matchedUserSplit[0];


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MatchedProfile.this, SeekerChronicle.class);

                startActivity(intent);
            }
        });



        RequestQueue requestQueue = Volley.newRequestQueue(MatchedProfile.this);

        JsonObjectRequest matchedRequest = new JsonObjectRequest(Request.Method.GET, matchedURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    fullname = response.getString("name");
                    username = response.getString("username");
                    desc = response.getString("description");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                matchedName.setText(fullname);
                matchedUsername.setText(username);
                matchedDescription.setText(desc);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                matchedName.setText(matchedId);

            }
        });

        requestQueue.add(matchedRequest);

    }
}