package com.example.android_prj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HorsLigne_menu extends AppCompatActivity {

    private Button horsLigne2;
    private Button horsLigne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hors_ligne_menu);

        this.horsLigne2 = (Button) findViewById(R.id.button2);
        this.horsLigne = (Button) findViewById(R.id.button);

        this.horsLigne2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToGame = new Intent(getApplicationContext(), twoplayers.class);
                startActivity(goToGame);
                finish();
            };
        });
        this.horsLigne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToGame = new Intent(getApplicationContext(), Robot_levels.class);
                startActivity(goToGame);
                finish();
            };
        });
    }
}