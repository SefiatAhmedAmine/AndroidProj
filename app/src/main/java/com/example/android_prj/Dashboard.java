package com.example.android_prj;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.tic_tac_toe.R;
import com.tic_tac_toe.view.LoginActivity;


public class Dashboard extends AppCompatActivity {

    private Button buttonOffline;
    private Button buttonHowtoplay;
    private Button buttonCredits;

    private  Button buttonOnline;
    private final int REQUEST_ENABLE_BT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //TextView offline =(TextView) findViewById(R.id.textView5);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbarDash);
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

        /**
         * @author: amine
         */
        buttonOnline = (Button) findViewById(R.id.button4);
        buttonOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToGame = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(goToGame);
                finish();
            }
        });

    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to quit the game?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        builder.create().show();
    }
}