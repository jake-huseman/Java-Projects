package com.test.jsontest;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class PostActivity extends AppCompatActivity {
    Button postBtn;
    EditText postText;
    String url = "https://13a3142e-e4e5-46b3-8347-224ba34dc905.mock.pstmn.io/PostTest2";
    TextView successText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        postBtn = findViewById(R.id.postBtn);
        postText = findViewById(R.id.editText);
        successText = findViewById(R.id.successText);

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue requestQueue = Volley.newRequestQueue(PostActivity.this);
                JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        successText.setText("success");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        successText.setText(error.toString());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        try
                        {
                            // etRequest should contain a JSON object string as your POST body
                            // similar to what you would have in POSTMAN-body field
                            JSONObject body = new JSONObject(postText.getText().toString());

                            Iterator<String> iter = body.keys();
                            while (iter.hasNext())
                            {
                                String key = iter.next();
                                params.put(key, (String) body.get(key));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return params;
                    }
                };
                requestQueue.add(jsonObject);
            }
        });
    }
}
