package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button button;
    Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button2 =findViewById(R.id.russelButton);
        button = findViewById(R.id.toCounterBtn);

        button.setOnClickListener(this);
        button2.setOnClickListener(this);



    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.russelButton:
                openMainActivity2();
                    break;
            case R.id.toCounterBtn:
                openCounterActivity();
                    break;
        }
    }

    public void openCounterActivity(){
        Intent intent = new Intent(this, CounterActivity.class);
        startActivity(intent);
    }

    public void openMainActivity2(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }


}