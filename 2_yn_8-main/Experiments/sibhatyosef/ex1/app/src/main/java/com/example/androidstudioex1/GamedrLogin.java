package com.example.androidstudioex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class GamedrLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamedr_login);

        TextView backText = findViewById(R.id.gamedr_login_back_text);

        backText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(view.getContext(), Gamedr.class));
            }
        });


        TextView username = findViewById(R.id.gamedr_login_username);
        TextView password = findViewById(R.id.gamedr_login_password);

        Button loginButton = findViewById(R.id.gamedr_login_login_button);
        CheckBox robotCheckBox = findViewById(R.id.gamedr_login_not_a_robot_checkbox);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (username.getText().toString().equals("gamedr") && password.getText().toString().equals("gamedr") && robotCheckBox.isChecked()) {

                   startActivity(new Intent(view.getContext(), SuccessfulLoginScreen.class));
                }
                else if (username.getText().toString().equals("gamedr") && password.getText().toString().equals("gamedr")) {

                    Toast.makeText(getApplicationContext(),"Forgot to check box!",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Oops, wrong credentials!",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}