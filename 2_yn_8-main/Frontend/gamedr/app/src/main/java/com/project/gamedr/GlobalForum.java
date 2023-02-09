package com.project.gamedr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
/**
 * Screen where you can choose a specific genre forum to view.
 * @author Sibhat Yusef
 */
public class GlobalForum extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_forum);

        Button dashboardButton = findViewById(R.id.forum_to_dashboard_button);
        ListView listView = findViewById(R.id.global_forum_list_view);

        List<String> tagList = new ArrayList<>();
        tagList.add("FPS");
        tagList.add("Strategy");
        tagList.add("Card Game");
        tagList.add("Action");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, tagList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(GlobalForum.this, MainForum.class);
                    intent.putExtra("forumName", tagList.get(i));
                    startActivity(intent);
//                if (i == 0) {
//
//                    startActivity(new Intent(GlobalForum.this, FpsForum.class));
//                }
//                else if (i == 1) {
//
//                    startActivity(new Intent(GlobalForum.this, StrategyForum.class));
//                }
//                else if (i == 2) {
//
//                    startActivity(new Intent(GlobalForum.this, CardGameForum.class));
//                }
//                else {
//
//                    startActivity(new Intent(GlobalForum.this, ActionForum.class));
//                }
            }
        });

        dashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(GlobalForum.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}