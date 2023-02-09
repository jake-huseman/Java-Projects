package com.project.gamedr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Show list of a seekers list of matches
 * @author Sibhat Yusef
 */
public class SeekerChronicle extends AppCompatActivity {

    List<String> listOfUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeker_chronicle);


        listOfUsers = new ArrayList<>();

        Button dashboardButton = findViewById(R.id.seeker_chronicle_button_to_dashboard);

        ImageView toRadar = findViewById(R.id.seeker_chronicle_image_to_radar);

        ListView userList = findViewById(R.id.seeker_user_list);




        UserLocalStorage userLocalStorage = new UserLocalStorage(this);

        String matchedServerURL = "http://coms-309-048.class.las.iastate.edu:8080/users/" + userLocalStorage.getLoggedUser().id + "/otherUsers";


        dashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SeekerChronicle.this, MainActivity.class);
                startActivity(intent);
            }
        });
        toRadar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SeekerChronicle.this, RadarActivity.class);
                startActivity(intent);
            }
        });







        RequestQueue requestQueue = Volley.newRequestQueue(SeekerChronicle.this);



        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, matchedServerURL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {

                    try {
                        listOfUsers.add(response.getJSONObject(i).getString("id") + " " + response.getJSONObject(i).getString("username"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }



                ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, listOfUsers);
                userList.setAdapter(arrayAdapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });


        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(SeekerChronicle.this, MatchedProfile.class);
                intent.putExtra("user", listOfUsers.get(i));
                startActivity(intent);

            }
        });










        //requestQueue.add(jsonObjectRequest);
        requestQueue.add(jsonArrayRequest);











    }
}