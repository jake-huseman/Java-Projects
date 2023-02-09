package com.test.gamedrbasic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class FinderActivity extends AppCompatActivity {

    //create the instance and view of the activity finder layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finder);

        Button finderButton = findViewById(R.id.FinderButton);
        Button ExitButton = findViewById(R.id.ExitButton);

        //Finder button so that it can find a user and display their profile picture, name, and rank.
        finderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView imageView = findViewById(R.id.personImage);
                TextView personName = findViewById(R.id.personName);
                TextView rank = findViewById(R.id.rank);

                int[] images = {R.drawable.blossombackground,R.drawable.smilecat,R.drawable.walkinforest};
                Random rand = new Random();
                String[] names = {"Chandler", "Sibhat", "Jake", "David"};
                int rankNum = rand.nextInt(999);

                imageView.setImageResource(images[rand.nextInt(images.length)]);
                personName.setText(names[rand.nextInt(names.length)]);
                rank.setText("Rank: " + rankNum);

            }
        });

        //Button to logout of Gamedr. Returns you to login screen.
        ExitButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               System.exit(0);
           }
        });
    }
}