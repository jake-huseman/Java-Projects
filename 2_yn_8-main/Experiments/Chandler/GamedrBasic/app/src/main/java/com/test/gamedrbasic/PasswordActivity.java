package com.test.gamedrbasic;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PasswordActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        Button returnBtn = findViewById(R.id.returnButton);
        Button getPasswordBtn = findViewById(R.id.passwordButton);
        TextView passwordEmail = findViewById(R.id.getPasswordEmail);
        TextView passwordOutput = findViewById(R.id.passwordOutput);

        getPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passwordEmail.getText().toString().equals("csihom@iastate.edu")) {
                    passwordOutput.setText("222");
                }
                else {
                    passwordOutput.setText("Username does not exist");
                }
            }
        });

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
    }
}
