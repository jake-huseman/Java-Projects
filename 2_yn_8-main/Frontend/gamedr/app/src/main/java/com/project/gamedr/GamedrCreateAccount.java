package com.project.gamedr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
 * Screen where you register a new account in the back-end.
 * @author Sibhat Yusef
 */
public class GamedrCreateAccount extends AppCompatActivity {

    String url = "http://coms-309-048.class.las.iastate.edu:8080/users/register/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamedr_create_account);




        EditText etUsername = findViewById(R.id.gamedr_register_username);
        EditText etFullName = findViewById(R.id.gamedr_register_name);
        EditText etEmail = findViewById(R.id.gamedr_register_email);
        EditText etPassword = findViewById(R.id.gamedr_register_password);
        EditText etActor = findViewById(R.id.gamedr_register_actor);
        Button registerButton = findViewById(R.id.gamedr_create_account_register_button);


        UserLocalStorage userLocalStorage = new UserLocalStorage(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = etUsername.getText().toString();
                String fullName = etFullName.getText().toString();
                String email = etEmail.getText().toString();
                String actor = etActor.getText().toString();
                String password = etPassword.getText().toString();

                if (username.equals("") || fullName.equals("") || email.equals("") || password.equals("") || actor.equals("")) {

                    Toast.makeText(GamedrCreateAccount.this, "Missing information!", Toast.LENGTH_SHORT).show();
                }
                else {
                    createAccount();
                }



            }

            /**
             * collects user input to register user with an account
             */
            private void createAccount() {

                String username = etUsername.getText().toString();
                String fullName = etFullName.getText().toString();
                String email = etEmail.getText().toString();
                String actor = etActor.getText().toString();
                String password = etPassword.getText().toString();
                url += password;

                Map<String, String> parameters = new HashMap<>();
                parameters.put("name", fullName);
                parameters.put("username", username);
                parameters.put("email", email);
                parameters.put("actor", actor);


                RequestQueue requestQueue = Volley.newRequestQueue(GamedrCreateAccount.this);

                JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(parameters), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Toast.makeText(GamedrCreateAccount.this, "Account Created Successfully!!", Toast.LENGTH_SHORT).show();
                        User registeredUser = new User(fullName, username, email, password, "", "", actor);
                        userLocalStorage.storeUser(registeredUser);
                    }


                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(GamedrCreateAccount.this, error.toString(), Toast.LENGTH_LONG).show();

                    }
                });

                requestQueue.add(jsonObj);

                Intent intent = new Intent(GamedrCreateAccount.this, GamedrLogin.class);
                startActivity(intent);


            }
        });









    }
}