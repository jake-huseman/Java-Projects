package com.example.roundtripuseraccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class GamedrCreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamedr_create_account);


        EditText username = findViewById(R.id.gamedr_register_username);
        EditText fullName = findViewById(R.id.gamedr_register_name);
        EditText email = findViewById(R.id.gamedr_register_email);
        EditText password = findViewById(R.id.gamedr_register_password);
        Button registerButton = findViewById(R.id.gamedr_create_account_register_button);

        TextView backText = findViewById(R.id.gamedr_register_back_text);

        backText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(view.getContext(), UserProfile.class));
            }
        });


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String name = fullName.getText().toString();
                String em = email.getText().toString();
                String pass = password.getText().toString();

                Map<String, String> parameters = new HashMap<>();
                parameters.put("name", name);
                parameters.put("username", user);
                parameters.put("email", em);


                String url2 = "https://b7f76194-0ee0-48c0-9ecd-44194c93fcbc.mock.pstmn.io/test?name&user";

                RequestQueue requestQueue = Volley.newRequestQueue(GamedrCreateAccount.this);

                JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.POST, url2, new JSONObject(parameters), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Toast.makeText(GamedrCreateAccount.this, "Success!", Toast.LENGTH_SHORT).show();

                    }


                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                requestQueue.add(jsonObj);
            }




        });
/*
        private void sendUserToDatabaseString (String user, String name){

            String url = "https://b7f76194-0ee0-48c0-9ecd-44194c93fcbc.mock.pstmn.io/test?name&user";

            RequestQueue requestQueue = Volley.newRequestQueue(this);

            StringRequest strRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Toast.makeText(GamedrCreateAccount.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Log.d("error", "" + error);

                }
            }) {
                protected HashMap<String, String> getParams() throws AuthFailureError {

                    HashMap<String, String> map = new HashMap<>();

                    map.put("name", name);
                    map.put("user", user);
                    return map;
                }
            };

            requestQueue.add(strRequest);

        }
*/

    }
}