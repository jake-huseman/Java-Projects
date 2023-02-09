package com.project.gamedr;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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
/**
 * View full list of reports(moderator only).
 * @author Chandler Sihom
 */
public class ReportList extends AppCompatActivity {
    String nameUrl = "http://coms-309-048.class.las.iastate.edu:8080/report/user";
    ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportlist);

        Button toDasboardBtn = findViewById(R.id.reportlist_dashboard);
        ListView listView = findViewById(R.id.report_listview);
        TextView errorDs = findViewById(R.id.username_reportlist);
        list = new ArrayList<>();

        RequestQueue requestQueue = Volley.newRequestQueue(ReportList.this);
        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, nameUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String username = "";
                String id = "";
                String description = "";
                for (int i=0; i<response.length(); i++) {
                    try {
                        list.add(response.getJSONObject(i).getString("username") + "\n" + response.getJSONObject(i).getInt("id") + "\n" + response.getJSONObject(i).getString("report"));
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
                errorDs.setText(error.toString());
            }
        });
        requestQueue.add(jsonRequest);

        //List View button
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =  new Intent(ReportList.this, reportedDisplay.class);
                intent.putExtra("id", list.get(i));
                startActivity(intent);
            }
        });

        toDasboardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportList.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
