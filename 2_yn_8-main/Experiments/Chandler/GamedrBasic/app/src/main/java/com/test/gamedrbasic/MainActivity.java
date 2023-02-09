package com.test.gamedrbasic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.PasswordAuthentication;


public class MainActivity extends AppCompatActivity {

    //create instance
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button enterButton = (Button) findViewById(R.id.EnterButton);
        Button getPasswordBtn = (Button) findViewById(R.id.getPassword);

        //Method for login button to take you to the next screen.
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView username = findViewById(R.id.emailInput);
                TextView password = findViewById(R.id.PasswordInput);

                if (username.getText().toString().equals("csihom@iastate.edu") && password.getText().toString().equals("222")) {
                    startActivity(new Intent(MainActivity.this, FinderActivity.class));

                }
                else {
                    username.setText("Wrong username");
                    password.setText("Wrong password");
                }

            }
        });

        getPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
               startActivity(new Intent(MainActivity.this, PasswordActivity.class));
           }
        });
    }

}