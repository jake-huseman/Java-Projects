package com.test.jsontest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class ArrayActivity extends AppCompatActivity {
    Button arrayBtn;
    TextView arrayText;
    String url = "https://1221a533-ac9f-46c2-9f36-428cd6825a09.mock.pstmn.io/ArrayTest1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array);

        arrayText = findViewById(R.id.arrayText);
        arrayBtn = findViewById(R.id.arrayMoveBtn);

        arrayBtn.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View view) {
                    RequestQueue requestQueue = Volley.newRequestQueue(ArrayActivity.this);
                    JsonArrayRequest jsonRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            arrayText.setText(response.toString());
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            arrayText.setText(error.toString());
                        }
                });
                    requestQueue.add(jsonRequest);
                }
        });
    }
}
