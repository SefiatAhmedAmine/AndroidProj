package com.example.android_prj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Dashboard extends AppCompatActivity {

    private Button buttonOffline;
    private Button buttonHowtoplay;
    private Button buttonCredits;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //TextView offline =(TextView) findViewById(R.id.textView5);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        this.buttonOffline = (Button) findViewById(R.id.button3);
        this.buttonHowtoplay = (Button) findViewById(R.id.button5);
        this.buttonCredits = (Button) findViewById(R.id.button7);

        /**/
        this.buttonOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToGame = new Intent(getApplicationContext(), HorsLigne_menu.class);
                startActivity(goToGame);
                finish();
            }
        });

        this.buttonHowtoplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHP = new Intent(getApplicationContext(), HowToPlay.class);
                startActivity(intentHP);
                finish();
            }
        });

        this.buttonCredits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCr = new Intent(getApplicationContext(), Credits.class);
                startActivity(intentCr);
                finish();
            }
        });

    }
}