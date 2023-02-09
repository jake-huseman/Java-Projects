package com.project.gamedr;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainForum extends AppCompatActivity {
    String url = "http://coms-309-048.class.las.iastate.edu:8080/Categories/";
    String postUrl = "http://coms-309-048.class.las.iastate.edu:8080/Categories/";
    Integer id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_forum);

        EditText postToForum = findViewById(R.id.post_to_forum);
        ArrayList<String> list = new ArrayList<>();
        Button postBtn = findViewById(R.id.post_forum_btn);
        TextView forumMessages = findViewById(R.id.forum_messages);
        TextView forumName = findViewById(R.id.forum_name);
        forumMessages.setMovementMethod(new ScrollingMovementMethod());
        Button toDash = findViewById(R.id.main_to_forum);
        String forumName1 = getIntent().getStringExtra("forumName");
        forumName.setText(forumName1 + " Forum");

        if (forumName1.equals("FPS")) {
            id = 2;
        }
        else if (forumName1.equals("Strategy")) {
            id = 3;
        }
        else if (forumName1.equals("Card Game")) {
            id = 4;
        }
        else {
            id = 5;
        }
        url += id + "/Posts";
        postUrl += id + "/Posts";

        toDash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainForum.this, GlobalForum.class));
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MainForum.this);
        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        list.add(response.getJSONObject(i).getString("userName") + ": " + response.getJSONObject(i).getString("text") + "\n");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                String forumMessageStr = list.toString();
                forumMessageStr = forumMessageStr.replace("[","");
                forumMessageStr = forumMessageStr.replace("]","");
                forumMessageStr = forumMessageStr.replace(",","");
                forumMessages.setText(forumMessageStr);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                forumMessages.setText(error.toString());
            }
        });
        requestQueue.add(jsonRequest);

/**
 *  nested get request inside of a post request to update the forum when you post to it.
 */
postBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        UserLocalStorage userLocalStorage = new UserLocalStorage(MainForum.this);
        User user = userLocalStorage.getLoggedUser();
        Map<String, String> params = new HashMap<>();
        params.put("postName", "");
        params.put("userName", user.username);
        params.put("text", postToForum.getText().toString());
        JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.POST, postUrl, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                RequestQueue requestQueue = Volley.newRequestQueue(MainForum.this);
                JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        list.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                list.add(response.getJSONObject(i).getString("userName") + ": " + response.getJSONObject(i).getString("text") + "\n");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        String forumMessageStr = list.toString();
                        forumMessageStr = forumMessageStr.replace("[","");
                        forumMessageStr = forumMessageStr.replace("]","");
                        forumMessageStr = forumMessageStr.replace(",","");
                        forumMessages.setText(forumMessageStr);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        forumMessages.setText(error.toString());
                    }
                });
                requestQueue.add(jsonRequest);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainForum.this, "could not post", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonObject);
    }
});
    }
}
