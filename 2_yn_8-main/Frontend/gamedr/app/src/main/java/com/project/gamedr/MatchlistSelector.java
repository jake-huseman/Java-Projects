package com.project.gamedr;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
/**
 * Screen where a matchmaker can recommend a match between a list of users.
 * @author Chandler Sihom
 */
public class MatchlistSelector extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matchlist_selector);
    }
}
