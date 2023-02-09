package com.project.gamedr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class DirectMessageHub extends AppCompatActivity {

    List<String> listOfUsers;
    String matchedURL = "https://b7f76194-0ee0-48c0-9ecd-44194c93fcbc.mock.pstmn.io/matchForChat";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_message_hub);


        ListView userList = findViewById(R.id.list_of_users);



        listOfUsers = new ArrayList<>();
        UserLocalStorage userLocalStorage = new UserLocalStorage(this);

        String matchedServerURL = "http://coms-309-048.class.las.iastate.edu:8080/users/" + userLocalStorage.getLoggedUser().id + "/otherUsers";

        RequestQueue requestQueue = Volley.newRequestQueue(DirectMessageHub.this);


        //for full implementation, will have a post request to send id and in the response I will pull the matched users of that id
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

                Toast.makeText(DirectMessageHub.this, error.toString(), Toast.LENGTH_LONG).show();

            }
        });

        requestQueue.add(jsonArrayRequest);

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(DirectMessageHub.this, DirectMessageChat.class);
                intent.putExtra("user", listOfUsers.get(i));
                startActivity(intent);

            }
        });

    }
}