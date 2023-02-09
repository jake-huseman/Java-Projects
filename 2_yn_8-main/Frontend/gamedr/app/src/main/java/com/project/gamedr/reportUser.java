package com.project.gamedr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
/**
 * Create a report of a specific user.
 * @author Chandler Sihom
 */
public class reportUser extends AppCompatActivity {
    String url = "http://coms-309-048.class.las.iastate.edu:8080/report?username=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportuser);
        EditText username = findViewById(R.id.username_report);
        EditText id = findViewById(R.id.ID_report);
        EditText description = findViewById(R.id.description_report);
        Button dashboard = findViewById(R.id.report_to_dashboard);
        Button reportUserBtn = findViewById(R.id.reportuserBtn);
        TextView successTxt = findViewById(R.id.report_success);

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(reportUser.this, MainActivity.class);
                startActivity(intent);
            }
        });

        reportUserBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RequestQueue requestQueue = Volley.newRequestQueue(reportUser.this);
            String usernameTxt = username.getText().toString();
            String idTxt = id.getText().toString();
            String descriptionTxt = description.getText().toString();
            url += usernameTxt + "&reason=" + descriptionTxt;
            Map<String, String> params = new HashMap<>();
            params.put("username", usernameTxt);
            JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    successTxt.setText("Successfully reported");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    successTxt.setText(error.toString());
                }
            });
            requestQueue.add(jsonObject);
        }
    });
    }
}
