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
 * Screen where you can match two specific users.
 * @author Chandler Sihom
 */
public class MatchsetActivity extends AppCompatActivity {
    Button setmatchBtn;
    EditText user1Txt;
    EditText user2Txt;
    EditText descriptionTxt;
    String url = "http://coms-309-048.class.las.iastate.edu:8080/matchPairs";
    TextView successText;
    Button returnBtn;
    Button matcherMoveBtn;
    Button matchlistMoveBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matchset);

        matchlistMoveBtn = findViewById(R.id.matchlistMove);
        returnBtn = findViewById(R.id.returnBtn);
        setmatchBtn = findViewById(R.id.SetMatchBtn);
        user1Txt = findViewById(R.id.user1Txt);
        user2Txt = findViewById(R.id.user2Txt);
        descriptionTxt = findViewById(R.id.DescriptionTxt);
        successText = findViewById(R.id.successText);
        matchlistMoveBtn = findViewById(R.id.matchlistMove);
        matcherMoveBtn = findViewById(R.id.matcherMoveBtn2);

        setmatchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue requestQueue = Volley.newRequestQueue(MatchsetActivity.this);
                String user1 = user1Txt.getText().toString();
                String user2 = user2Txt.getText().toString();
                url += "/" + user1;
                url += "/" + user2;
                //String description = descriptionTxt.getText().toString();

                Map<String, String> params = new HashMap<>();
                params.put("id1", user1);
                params.put("id2", user2);
                //params.put("description", description);
                JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        successText.setText("success");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        successText.setText(error.toString());
                    }
                });
                requestQueue.add(jsonObject);
            }
        });

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MatchsetActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        matcherMoveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MatchsetActivity.this, MatcherActivity.class);
                startActivity(intent);
            }
        });

        matchlistMoveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MatchsetActivity.this,MatchListActivity.class);
                startActivity(intent);
            }
        });
    }
}
