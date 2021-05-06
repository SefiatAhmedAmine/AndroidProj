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
        /**
         * go to a server-side activity where you gonna start a bluetooth server socket "BSS"
         * and wait to accept a connection to start the game
         */
        this.createGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            };
        });

        /**
         * tasks to do (we'll see how to present them in the app):
         *  - get paired devices
         *  - scan for available devices
         *  - create a client-side activity where you gonna start a socket
         *      that is gonna try to connect to the "BSS" and then play
         *
         * ps: it is possible that this layout (create game / join game) is gonna change
         *      depending on how we are gonna approach the process of connection
         */
        this.joinGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listDevice = new Intent(EnLigne_menu.this, PairedDevices.class);
                startActivity(listDevice);
                finish();

            }
        });
    }
}