
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

public class Robot_levels extends AppCompatActivity {

    private Button horsLigne6;
    private Button horsLigne7;
    private Button horsLigne8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.robot_levels);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.robot_levels);

        this.horsLigne6 = (Button) findViewById(R.id.button6);
        this.horsLigne7 = (Button) findViewById(R.id.button7);
        this.horsLigne8 = (Button) findViewById(R.id.button8);

        this.horsLigne6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToGame = new Intent(getApplicationContext(), robot_easy.class);
                startActivity(goToGame);
                finish();
            };
        });
        this.horsLigne7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToGame = new Intent(getApplicationContext(), HeuristicRuleBasesAI.class);
                startActivity(goToGame);
                finish();
            };
        });
        this.horsLigne8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToGame = new Intent(getApplicationContext(), robot_hard.class);
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
                        Intent backtodash = new Intent(Robot_levels.this, HorsLigne_menu.class);
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