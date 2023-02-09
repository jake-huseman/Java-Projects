package com.project.gamedr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
/**
 * Screen to view your profile or a user's profile.
 * @author Sibhat Yosef
 */
public class UserProfile extends AppCompatActivity {

    String tagAddURL = "";
    String tagDelURL = "";
    String selectedButton = "";
    Button fpsButton;
    Button mmoButton;
    Button sportsButton;
    Button tagUpdateButton;
    ImageView deleteImage;
    TextView tagDisplay;
    String profileTag;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        TextView profileName = findViewById(R.id.user_profile_name);
        TextView profileUser = findViewById(R.id.user_profile_username);
        TextView profileEmail = findViewById(R.id.user_profile_email);
        TextView profileDescription = findViewById(R.id.user_profile_description);
        TextView profileId = findViewById(R.id.profile_id);
        Button updateButton = findViewById(R.id.user_profile_update_button);
        Button dashboardButton = findViewById(R.id.buttom_to_home);
        ImageButton messageIcon = findViewById(R.id.profile_to_message_hub_image_button);

        tagDisplay = findViewById(R.id.user_profile_tag_display);
        fpsButton = findViewById(R.id.fpsTag);
        mmoButton = findViewById(R.id.mmoTag);
        sportsButton = findViewById(R.id.sportsTag);
        tagUpdateButton = findViewById(R.id.profile_update_tag_button);
        deleteImage = findViewById(R.id.delete_tag_image);

        profileDescription.setMovementMethod(new ScrollingMovementMethod());

        UserLocalStorage userLocalStorage = new UserLocalStorage(this);

        User user = userLocalStorage.getLoggedUser();

        profileName.setText(user.fullName);
        profileEmail.setText(user.email);
        profileUser.setText(user.username);
        profileId.setText(user.id);
        profileDescription.setText(user.description);

        tagDelURL = "http://coms-309-048.class.las.iastate.edu:8080/users/game/" + user.id + "/del";


        fpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedButton = "FPS";
                tagAddURL = "http://coms-309-048.class.las.iastate.edu:8080/users/game/" + user.id + "/" + selectedButton;

                Toast.makeText(UserProfile.this, "FPS tag selected", Toast.LENGTH_SHORT).show();

            }
        });


        sportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedButton = "SPORTS";
                tagAddURL = "http://coms-309-048.class.las.iastate.edu:8080/users/game/" + user.id + "/" + selectedButton;

                Toast.makeText(UserProfile.this, "Sports tag selected", Toast.LENGTH_SHORT).show();

            }
        });


        mmoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedButton = "MMO";
                tagAddURL = "http://coms-309-048.class.las.iastate.edu:8080/users/game/" + user.id + "/" + selectedButton;

                Toast.makeText(UserProfile.this, "MMO tag selected", Toast.LENGTH_SHORT).show();

            }
        });






        RequestQueue requestQueue = Volley.newRequestQueue(this);

        tagUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addTag();

            }

            private void addTag() {

                StringRequest tagRequest = new StringRequest(Request.Method.PUT, tagAddURL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(UserProfile.this, selectedButton + " tag was added", Toast.LENGTH_LONG).show();
                        tagDisplay.setText(selectedButton);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(UserProfile.this, error.toString(), Toast.LENGTH_LONG).show();

                        tagDisplay.setText(error.toString());
                    }
                });


                requestQueue.add(tagRequest);
            }


        });



        deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteTag();

            }


            private void deleteTag() {

                StringRequest tagDelRequest = new StringRequest(Request.Method.DELETE, tagDelURL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(UserProfile.this, "Successfully deselected tag", Toast.LENGTH_LONG).show();
                        tagDisplay.setText("");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(UserProfile.this, error.toString(), Toast.LENGTH_LONG).show();

                        profileDescription.setText(error.toString());
                    }
                });


                requestQueue.add(tagDelRequest);

            }

        });







        dashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(UserProfile.this, MainActivity.class);
                startActivity(intent);
            }
        });

        messageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(UserProfile.this, DirectMessageHub.class);
                startActivity(intent);
            }
        });


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }

        });






    }


}