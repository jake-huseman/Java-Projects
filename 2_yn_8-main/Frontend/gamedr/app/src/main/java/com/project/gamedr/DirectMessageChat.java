package com.project.gamedr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.w3c.dom.Text;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Screen where you can direct message a user.
 * @author Sibhat Yusef
 */
public class DirectMessageChat extends AppCompatActivity {


   // Button dashboardButton = findViewById(R.id.dm_button_to_dashboard);
   // TextView username = findViewById(R.id.username_to_profile);


    Button b1, sendButton;
    EditText e1, e2;
    TextView t1;


    TextView chatBox;
    EditText chatInput;
    ImageView backArrow;

    String s;

    TextView userCheck;
    String userToChatSplit;
    String[] userToChatArray;

    String userToChat;

    private WebSocketClient cc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_message_chat);


       // b1 = (Button) findViewById(R.id.bt1);
        sendButton = (Button) findViewById(R.id.chat_button_to_send);
        chatBox = findViewById(R.id.chat_chatbox);
        chatInput = findViewById(R.id.chat_input_text);
        backArrow = findViewById(R.id.chat_back_arrow);
        //e1 = (EditText) findViewById(R.id.et1);
       // e2 = (EditText) findViewById(R.id.et2);
       // t1 = (TextView) findViewById(R.id.tx1);

        userToChat = getIntent().getStringExtra("user");
        userCheck = findViewById(R.id.chat_user_name);


        userToChatSplit = getIntent().getStringExtra("user");

        userToChatArray = userToChatSplit.split(" ");

        userCheck.setText(userToChatArray[1]);


        UserLocalStorage userLocalStorage = new UserLocalStorage(this);


        Draft[] drafts = {
                new Draft_6455()
        };

        String URICONNECTION = "ws://coms-309-048.class.las.iastate.edu:8080/chat/" + userLocalStorage.getLoggedUser().username + "/" + userToChatArray[1];

        try {
            Log.d("Socket:", "Trying socket");

            cc = new WebSocketClient(new URI(URICONNECTION), (Draft) drafts[0]) {


                @Override
                public void onMessage(String message) {
                    Log.d("", "run() returned: " + message);
                     s += message + "\n";

                    chatBox.setText(s);
                }

                @Override
                public void onOpen(ServerHandshake handshake) {
                    Log.d("OPEN", "run() returned: " + "is connecting");

                    Toast.makeText(DirectMessageChat.this, "you are connecting", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.d("CLOSE", "onClose() returned: " + reason);

                    Toast.makeText(DirectMessageChat.this, "You have left", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onError(Exception e) {
                    Log.d("Exception:", e.toString());
                }

            };


        }
        catch (URISyntaxException e) {

            Log.d("Exception:", e.getMessage().toString());
            e.printStackTrace();


        }
        cc.connect();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cc.send("@"+ userToChatArray[1] + " " + chatInput.getText().toString());
                } catch (Exception e) {
                    Log.d("ExceptionSendMessage:", e.getMessage().toString());
                }
            }
        });


        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cc.close();

                Intent intent = new Intent(DirectMessageChat.this, DirectMessageHub.class);
                startActivity(intent);

            }
        });


    }
}
//chatInput.getText().toString()























