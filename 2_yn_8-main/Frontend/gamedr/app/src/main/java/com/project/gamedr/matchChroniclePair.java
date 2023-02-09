package com.project.gamedr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
/**
 * View a specific match from matchChronicle list.
 * @author Chandler Sihom
 */
public class matchChroniclePair extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matchchroniclepair);

        Button returnBtn = findViewById(R.id.return_matchchroniclepair);
        TextView match = findViewById(R.id.matchpair_display);
        String id = getIntent().getStringExtra("matchPair");
        id.split(System.lineSeparator());
        id = id.replace("{", "");
        id = id.replace(",", "\n\n");
        id = id.replace("\"", "");
        id = id.replace("}", "");
        match.setText(id);

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(matchChroniclePair.this, matchChronicle.class);
                startActivity(intent);
            }
        });
    }
}
