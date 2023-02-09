package com.project.gamedr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.net.IDN;
/**
 * Login with username and password from user list in back-end.
 * @author Sibhat Yusef and Chandler Sihom
 */
public class GamedrLogin extends AppCompatActivity {
String url = "http://coms-309-048.class.las.iastate.edu:8080/users";
    boolean check = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamedr_login);

        TextView backText = findViewById(R.id.gamedr_login_back_text);
        Button loginButton = findViewById(R.id.gamedr_login_login_button);
        TextView goToRegister = findViewById(R.id.gamedr_login_register_here);
        EditText username = findViewById(R.id.gamedr_login_username);
        EditText password = findViewById(R.id.gamedr_login_password);

        UserLocalStorage userLocalStorage = new UserLocalStorage(this);


        goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GamedrLogin.this, GamedrCreateAccount.class);
                startActivity(intent);
            }
        });


        backText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GamedrLogin.this, GamedrEnterScreen.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequestQueue requestQueue = Volley.newRequestQueue(GamedrLogin.this);
                JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i<response.length(); ++i) {
                            try {
                                if (response.getJSONObject(i).getString("username").equals(username.getText().toString()) && response.getJSONObject(i).getString("password").equals(password.getText().toString()) && response.getJSONObject(i).getBoolean("isBanned") == false) {
                                    check = true;
                                    userLocalStorage.storeUser(new User("", response.getJSONObject(i).getString("username"), response.getJSONObject(i).getString("email"), response.getJSONObject(i).getString("password"), response.getJSONObject(i).getString("id"), response.getJSONObject(i).getString("description"), response.getJSONObject(i).getString("actor")));
                                    userLocalStorage.setUserLoggedIn(true);
                                    break;
                                }
                                else if (response.getJSONObject(i).getString("username").equals(username.getText().toString()) && response.getJSONObject(i).getString("password").equals(password.getText().toString()) && response.getJSONObject(i).getBoolean("isBanned") == true) {
                                    check = false;
                                    Toast.makeText(GamedrLogin.this, "The specified user is banned", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                                else {
                                    check = false;
                                    userLocalStorage.setUserLoggedIn(false);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GamedrLogin.this, error.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
                requestQueue.add(jsonRequest);

                if (check == true) {
                    backText.setText("Success");
                    Intent intent = new Intent(GamedrLogin.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(GamedrLogin.this, "Incorrect username and/or password", Toast.LENGTH_SHORT).show();
                }
            }
        }));

    }
}