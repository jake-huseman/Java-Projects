package com.project.gamedr;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Main screen where you can reject or match with a random user.
 * @author Chandler Sihom
 */
public class RadarActivity extends AppCompatActivity {
    String urlPost = "http://coms-309-048.class.las.iastate.edu:8080/matchPairs";
    String url = "http://coms-309-048.class.las.iastate.edu:8080/users";
    TextView username;
    TextView id;
    TextView description;
    String user = "";
    String idG = "";
    String descriptionG = "";
    ArrayList<String> list;
    ArrayList<String> addedTags;
    TextView userTags;
    String userTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar);

        TextView selectedTags = findViewById(R.id.radar_selectedTags);
        userTags = findViewById(R.id.radar_theirTags);
        Button chronicleBtn = findViewById(R.id.to_chronicle);
        Button returnBtn = findViewById(R.id.radarToDash);
        Button acceptBtn = findViewById(R.id.radar_acceptBtn);
        Button rejectBtn = findViewById(R.id.radar_rejectBtn);
        username = findViewById(R.id.radar_usernameTxt);
        id = findViewById(R.id.radar_idTxt);
        description = findViewById(R.id.radar_descriptionTxt);


        UserLocalStorage userLocalStorage = new UserLocalStorage(this);
        User user = userLocalStorage.getLoggedUser();
        if (!user.actor.equals("MTR")) {
            chronicleBtn.setVisibility(View.GONE);
        }

        list = new ArrayList<>();
        ListView listView = findViewById(R.id.tag_listview);
        list.add("FPS");
        list.add("MMO");
        list.add("SPORTS");
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);

        addedTags = new ArrayList<>();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (addedTags.size() != 0) {
                    for (int s = 0; s < addedTags.size(); s++) {
                        if (addedTags.get(s) == list.get(i)) {
                            addedTags.remove(s);
                        } else {
                            addedTags.add(list.get(i));
                        }
                    }
                }
                else {
                    addedTags.add(list.get(i));
                }
                selectedTags.setText("Selected tags: " + addedTags.toString());
            }
        });
    if (addedTags.size() != 0) {
        findUser(addedTags);
    }

        //to be worked on
        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMatch();
            }
        });

        rejectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addedTags.size() != 0) {
                    findUser(addedTags);
                }
                else {
                    username.setText("Please select a tag");
                }
            }
        });

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RadarActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        chronicleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RadarActivity.this, matchChronicle.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Helper that finds a new user to display on rejection.
     * @param addedTags arraylist of tags added to array to filter genres.
     */
    public void findUser(ArrayList<String> addedTags) {
        ArrayList<String> tags = addedTags;
        RequestQueue requestQueue = Volley.newRequestQueue(RadarActivity.this);
        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(JSONArray response) {
                Random rand = new Random();
               for (int i = 0; i < response.length(); i++) {
                    int ind = rand.nextInt(response.length());
                    try {
                        if (response.getJSONObject(ind).getString("game").equals(addedTags.get(0))) {
                            username.setText("Username: " + response.getJSONObject(ind).getString("username"));
                            id.setText("ID: " + response.getJSONObject(ind).getString("id"));
                            description.setText("Description: " + response.getJSONObject(ind).getString("description"));
                            userTags.setText("User's Tag: " + response.getJSONObject(ind).getString("game"));
                            user = response.getJSONObject(ind).getString("username");
                            idG = response.getJSONObject(ind).getString("id");
                            descriptionG = response.getJSONObject(ind).getString("description");
                            userTag = response.getJSONObject(ind).getString("game");
                            break;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RadarActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonRequest);
    }

    /**
     * Helper that adds a match to back-end on accepting.
     */
    public void addMatch() {
        RequestQueue requestQueue = Volley.newRequestQueue(RadarActivity.this);
        Map<String, String> params = new HashMap<>();
        params.put("user", user);
        params.put("id", idG);
        params.put("description", descriptionG);
        JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.POST, urlPost, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                username.setText("Accepted match");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RadarActivity.this, error.toString() + " Failed to match with user", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObject);
    }

}
