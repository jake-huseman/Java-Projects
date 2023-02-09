package com.example.roundtripuseraccount;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);



        TextView profileName = findViewById(R.id.user_profile_name);
        TextView profileUsername = findViewById(R.id.user_profile_username);
        TextView profileEmail = findViewById(R.id.user_profile_email);
        TextView profileDescription = findViewById(R.id.user_profile_description);
        Button updateButton = findViewById(R.id.user_profile_update_button);


        String url = "https://b7f76194-0ee0-48c0-9ecd-44194c93fcbc.mock.pstmn.io/test";
        String url2 = "http://coms-309-048.class.las.iastate.edu:8080/users";

        RequestQueue requestQueue = Volley.newRequestQueue(this);


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();

            }








            private void updateProfile() {

                JsonObjectRequest updObj = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {



                            String name = response.getString("name");
                            String username = response.getString("username");
                            String email = response.getString("email");
                            String desc = response.getString("description");

                            profileName.setText(name);
                            profileUsername.setText(username);
                            profileEmail.setText(email);
                            profileDescription.setText(desc);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        profileName.setText(error.toString());
                        profileUsername.setText(error.toString());
                        profileEmail.setText(error.toString());
                        profileDescription.setText(error.toString());

                    }
                });

                requestQueue.add(updObj);
            }
        });



    }


}