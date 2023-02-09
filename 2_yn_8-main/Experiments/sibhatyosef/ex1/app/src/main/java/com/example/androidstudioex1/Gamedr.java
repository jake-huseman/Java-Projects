package com.example.androidstudioex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Gamedr extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamedr);


        Button backButton = findViewById(R.id.gamedr_back_button_to_main);
        Button buttonToLoginScreen = findViewById(R.id.gamedr_button_to_login_screen);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(view.getContext(), MainActivity.class));
            }
        });

        buttonToLoginScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(view.getContext(), GamedrLogin.class));
            }
        });

    }
}