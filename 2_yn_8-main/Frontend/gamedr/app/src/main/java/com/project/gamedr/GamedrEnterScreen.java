package com.project.gamedr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/**
 * Screen that directs you to the login or registration screen.
 * @author Sibhat Yusef
 */
public class GamedrEnterScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamedr_enter_screen);


        Button buttonToCreateAccount = findViewById(R.id.enter_screen_to_createaccount_button);
        Button buttonToLogin = findViewById(R.id.enter_to_login_button);


        buttonToCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GamedrEnterScreen.this, GamedrCreateAccount.class);
                startActivity(intent);
            }
        });



        buttonToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GamedrEnterScreen.this, GamedrLogin.class);
                startActivity(intent);
            }
        });


    }
}