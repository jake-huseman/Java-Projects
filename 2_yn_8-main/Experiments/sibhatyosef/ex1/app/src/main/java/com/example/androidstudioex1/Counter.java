package com.example.androidstudioex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Counter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.counter);


        Button backButton = findViewById(R.id.counter_back_button);
        Button incrementButton = findViewById(R.id.counter_increment_button);
        Button decrementButton = findViewById((R.id.counter_decrement_button));

        TextView counterText = findViewById(R.id.counter_counterText);

        Integer[] count = {0};

        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                count[0]++;
                counterText.setText(count[0].toString());
            }
        });

        decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count[0]--;
                counterText.setText(count[0].toString());
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(view.getContext(), MainActivity.class));
            }
        });

    }
}