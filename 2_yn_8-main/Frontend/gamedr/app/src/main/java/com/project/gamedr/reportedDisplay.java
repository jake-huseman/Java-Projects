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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * View a specific report from the ReportList.
 * @author Chandler Sihom
 */
public class reportedDisplay extends AppCompatActivity {
    String url = "http://coms-309-048.class.las.iastate.edu:8080/report/ban/";
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportdisplay);

        Button returnBtn = findViewById(R.id.return_from_reportdisplay);
        TextView idDisplay = findViewById(R.id.reportdisplay_id);
        Button banBtn = findViewById(R.id.banBtn);
        TextView usernameDisplay = findViewById(R.id.reportdisplay_username);
        TextView descriptionDisplay = findViewById(R.id.reportdisplay_description);
        descriptionDisplay.setMovementMethod(new ScrollingMovementMethod());

        UserLocalStorage userLocalStorage = new UserLocalStorage(this);
        User user1 = userLocalStorage.getLoggedUser();
        if (!user1.actor.equals("MDR")) {
            banBtn.setVisibility(View.GONE);
        }

        String id = getIntent().getStringExtra("id");

        list = new ArrayList<>();
        String[] user = id.split(System.lineSeparator());
        for (int i = 0; i < user.length; i++) {
            list.add(user[i]);
        }

        idDisplay.setText("ID: " + list.get(1));
        usernameDisplay.setText("Username: " + list.get(0));

        String[] report = list.get(2).split(",");
        String reportList = "";
        for (int i = 0; i < report.length; i++) {
            reportList += report[i] + "\n";
        }
        reportList = reportList.replace("{","");
        reportList = reportList.replace("\"", "");
        reportList = reportList.replace("}", "");
        descriptionDisplay.setText("Report: \n" + reportList);

        banBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue requestQueue = Volley.newRequestQueue(reportedDisplay.this);

                Map<String, String> params = new HashMap<>();
                params.put("id", list.get(1));
                params.put("username", list.get(0));
                url += list.get(0);
                params.put("description", list.get(2));
                JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        idDisplay.setText("success");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        idDisplay.setText(error.toString());
                    }
                });
                requestQueue.add(jsonObject);
            }
        });

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(reportedDisplay.this, ReportList.class);
                startActivity(intent);
            }
        });

    }
}
