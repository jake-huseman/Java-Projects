package com.project.gamedr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Full list of clickable successful and unsuccessful matches.
 * @author Chandler Sihom
 */
public class matchChronicle extends AppCompatActivity {

    String nameUrl = "http://coms-309-048.class.las.iastate.edu:8080/mchronicle";
    ArrayList<String> list;
    ArrayList<String> UnsuccessList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matchchronicle);

        ListView listView = findViewById(R.id.listview_user);
        ListView listView1 = findViewById(R.id.listview_user2);
        Button returnBtn = findViewById(R.id.return_radar);
        TextView errorText = findViewById(R.id.error_matchChronicles);
        list = new ArrayList<>();
        UnsuccessList = new ArrayList<>();


        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(matchChronicle.this, RadarActivity.class);
                startActivity(intent);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(matchChronicle.this);
        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, nameUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        list.add(response.getJSONObject(i).getString("id") + "\n" + response.getJSONObject(i).get("matchPair"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
                listView.setAdapter(arrayAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errorText.setText(error.toString());
            }
        });
        requestQueue.add(jsonRequest);

        RequestQueue requestQueue2 = Volley.newRequestQueue(matchChronicle.this);
        JsonArrayRequest jsonRequest2 = new JsonArrayRequest(Request.Method.GET, nameUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {
                        UnsuccessList.add(response.getJSONObject(i).getString("id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, UnsuccessList);
                listView1.setAdapter(arrayAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errorText.setText(error.toString());
            }
        });
        requestQueue2.add(jsonRequest2);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =  new Intent(matchChronicle.this, matchChroniclePair.class);
                intent.putExtra("matchPair", list.get(i));
                startActivity(intent);
            }
        });

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(matchChronicle.this, matchChroniclePair.class);
                intent.putExtra("matchPair", UnsuccessList.get(i) + "\n" + "Failed due to not accepting match");
                startActivity(intent);
            }
        });
    }
}
