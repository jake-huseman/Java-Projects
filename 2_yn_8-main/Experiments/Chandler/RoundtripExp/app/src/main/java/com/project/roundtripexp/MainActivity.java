package com.project.roundtripexp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    IUser i;
Button matchBtn;
TextView usernameTxt;
TextView descriptionTxt;
TextView idTxt;
ImageView profilePicture;
Button profileBtn;
int[] pictures = {R.drawable.oranges, R.drawable.inspiration, R.drawable.gamedrbackground};
final String url = "https://1221a533-ac9f-46c2-9f36-428cd6825a09.mock.pstmn.io/UserArray";
    String name = null;
    String id = null;
    String description = null;
    int imageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileBtn = findViewById(R.id.profileBtn);
        profilePicture = findViewById(R.id.userImg);
        matchBtn = findViewById(R.id.findUserBtn);
        usernameTxt = findViewById(R.id.usernameText);
        descriptionTxt = findViewById(R.id.descriptionText);
        idTxt = findViewById(R.id.idText);

        matchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                JsonArrayRequest jsonRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Random rand = new Random();
                        int index = rand.nextInt(response.length());

                        try {
                            name = response.getJSONObject(index).getString("name");
                            id = response.getJSONObject(index).getString("id");
                            description = response.getJSONObject(index).getString("description");
                            imageIndex = response.getJSONObject(index).getInt("image");

                            //WIP
                            //i.setUser(response.getJSONObject(index).getString("name"));
                        } catch (JSONException e) {
                            idTxt.setText("Failed to find user");
                            e.printStackTrace();
                        }
                        idTxt.setText("id: " +  id);
                        usernameTxt.setText(name);
                        descriptionTxt.setText(description);
                        profilePicture.setImageResource(pictures[imageIndex]);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        descriptionTxt.setText(error.toString());
                    }
                });
                requestQueue.add(jsonRequest);
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra("username", name);
                intent.putExtra("id", id);
                intent.putExtra("description", description);
                intent.putExtra("ImageIndex", imageIndex);
                startActivity(intent);
            }
        });
    }
}