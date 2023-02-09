package com.project.interfaceexp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements IView {
Button getBtn;
TextView userText;
TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getBtn = findViewById(R.id.getBtn);
        userText = findViewById(R.id.userText);
        responseText = findViewById(R.id.responseText);
        GetUser userResponse = new GetUser();

        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userResponse.getUser(MainActivity.this);
            }
        });
    }

    @Override
    public void showText(String s) {
        userText.setText(s);
    }

    @Override
    public void showError(String s) {
        responseText.setText(s);
    }
}