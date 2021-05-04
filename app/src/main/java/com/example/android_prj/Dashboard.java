package com.example.android_prj;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

        /**
         * @author: amine
         */
        buttonOnline = (Button) findViewById(R.id.button4);
        buttonOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                if (bluetoothAdapter == null) {
                    // Device doesn't support Bluetooth
                    Toast.makeText(Dashboard.this, "This device does not support BlueTooth !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!bluetoothAdapter.isEnabled()) {
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                }
                else {
                    Intent intent = new Intent(Dashboard.this, EnLigne_menu.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}