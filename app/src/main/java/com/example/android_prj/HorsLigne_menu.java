package com.example.android_prj;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.tic_tac_toe.R;

public class HorsLigne_menu extends AppCompatActivity {

    private Button horsLigne2;
    private Button horsLigne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hors_ligne_menu);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to quit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent backtodash = new Intent(HorsLigne_menu.this, Dashboard.class);
                        startActivity(backtodash);
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