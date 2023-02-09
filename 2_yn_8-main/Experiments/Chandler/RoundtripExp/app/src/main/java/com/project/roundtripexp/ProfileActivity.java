package com.project.roundtripexp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    String username;
    String id;
    String description;
    int imageId;
    IUser i;
    int[] pictures = {R.drawable.oranges, R.drawable.inspiration, R.drawable.gamedrbackground};
    TextView nameText;
    TextView idText;
    TextView descriptionText;
    ImageView profileImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        username = getIntent().getStringExtra("username");
        id = getIntent().getStringExtra("id");
        description = getIntent().getStringExtra("description");
        imageId = getIntent().getIntExtra("ImageIndex", 0);
        nameText = findViewById(R.id.nameText);
        idText = findViewById(R.id.idText2);
        descriptionText = findViewById(R.id.descriptionTxt);
        profileImg = findViewById(R.id.imageView);


        nameText.setText(username);
        idText.setText(id);
        descriptionText.setText(description);
        profileImg.setImageResource(pictures[imageId]);

        /* add code for post report params here */

//        username = i.getUser();
//        id = i.getId();
//        description = i.getDescription();
//        imageId = i.getImage();

//        nameText.setText(username);
//        idText.setText(id);
    }
}
