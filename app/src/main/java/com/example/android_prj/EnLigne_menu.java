package com.example.android_prj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EnLigne_menu extends AppCompatActivity {

    private Button createGame;
    private Button joinGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_ligne_menu);

        this.createGame = (Button) findViewById(R.id.button2);
        this.joinGame = (Button) findViewById(R.id.button);

        this.createGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            };
        });

        this.joinGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}