package com.project.gamedr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Dashboard where you can navigate to other screens/views.
 * @author Chandler Sihom and Sibhat Yusef
 */
//Dashboard screen add button to start intent to move to your specific screen.
public class MainActivity extends AppCompatActivity {
Button matchsetBtn;
Button logoutBtn;
Button radarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matchsetBtn = findViewById(R.id.MatchsetBtn);
        Button profileBtn = findViewById(R.id.profileBtn);
        Button reportUserBtn = findViewById(R.id.to_reportuser);
        logoutBtn = findViewById(R.id.logoutBtn);
        radarBtn = findViewById(R.id.radarBtn);
        TextView username = findViewById(R.id.dashboard_username_to_profile);
        ImageView arrow = findViewById(R.id.dashboard_arrow_to_message_hub);
        Button forumButton = findViewById(R.id.dashboard_to_global_forum_button);
        ImageView toSeekersChronicle = findViewById(R.id.dashboard_image_to_seekers_chronicle);
        Button reportListBtn = findViewById(R.id.to_reportlist);

        UserLocalStorage userLocalStorage = new UserLocalStorage(this);
        username.setText(userLocalStorage.getLoggedUser().username);

        if (!userLocalStorage.getLoggedUser().actor.equals("MDR")) {
            reportListBtn.setVisibility(View.GONE);
        }
        if (!userLocalStorage.getLoggedUser().actor.equals("SKR")) {
            toSeekersChronicle.setVisibility(View.GONE);
        }

        reportListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ReportList.class);
                startActivity(intent);
            }
        });

        reportUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, reportUser.class);
                startActivity(intent);
            }
        });

        toSeekersChronicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SeekerChronicle.class);
                startActivity(intent);
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, UserProfile.class);
                startActivity(intent);
            }
        });

        matchsetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MatchsetActivity.class);
                startActivity(intent);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userLocalStorage.clearUser();
                userLocalStorage.setUserLoggedIn(false);

                Intent intent = new Intent(MainActivity.this, GamedrEnterScreen.class);
                startActivity(intent);
            }
        });

        radarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RadarActivity.class);
                startActivity(intent);
            }
        });

        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserProfile.class);
                startActivity(intent);
            }
        });

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DirectMessageHub.class);
                startActivity(intent);
            }
        });

        forumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, GlobalForum.class);
                startActivity(intent);
            }
        });

    }
}