package com.project.gamedr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
/**
  Forum for card game genre.
 @author Sibhat Yusef
 */
public class CardGameForum extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_game_forum);

        final TextView[] conversationText = {findViewById(R.id.card_game_conversation)};
        EditText sentText = findViewById(R.id.card_game_edit_text);
        conversationText[0].setMovementMethod(new ScrollingMovementMethod());
        Button sendButton = findViewById(R.id.card_game_send_button);
        final String[] totalConversation = {""};
        String postURL = "https://coms-309-048.class.las.iastate.edu:8080/Forums/";




        UserLocalStorage userLocalStorage = new UserLocalStorage(this);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendMessage();

            }



            private void sendMessage() {

                String message = sentText.getText().toString();

                Map<String, String> parameters = new HashMap<>();
                parameters.put("username", userLocalStorage.getLoggedUser().username);
                parameters.put("post", message);


                RequestQueue requestQueue = Volley.newRequestQueue(CardGameForum.this);

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postURL, new JSONObject(parameters), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(CardGameForum.this, "Message Sent!", Toast.LENGTH_SHORT).show();









                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(CardGameForum.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                });

                String url2 = "https://b7f76194-0ee0-48c0-9ecd-44194c93fcbc.mock.pstmn.io/testforum";
                String urlReal = "";


                JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET, url2, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        totalConversation[0] += userLocalStorage.getLoggedUser().username + ": ";
                        totalConversation[0] += message + "\n" + "\n";

                        conversationText[0].setText(totalConversation[0]);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                requestQueue.add(jsonObjectRequest);
                requestQueue.add(jsonObjectRequest1);
            }



        });













    }
}