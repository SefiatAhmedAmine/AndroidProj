package com.example.android_prj;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {
    private final int REQUEST_ENABLE_BT = 1;
    /**
     * author: Aniss Souiyah
     */
    private static int SPLASH_SCREEN = 5000;

    Button boutonOffline ;
    Button boutonOnline;

    //variables
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView logo, slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home_page);

        boutonOffline = findViewById(R.id.button);
        boutonOnline = findViewById(R.id.button2);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        image = findViewById(R.id.imageView);
        logo = findViewById(R.id.textView);
        slogan = findViewById(R.id.textView2);

        image.setAnimation(topAnim);
        boutonOffline.setAnimation(bottomAnim);
        boutonOnline.setAnimation(bottomAnim);
        logo.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);

        boutonOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, HorsLigne_menu.class);
                startActivity(intent);
                finish();
            }
        });

        /**
         * author: A.Amine Sefiat
         */
        boutonOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                if (bluetoothAdapter == null) {
                    // Device doesn't support Bluetooth
                    Toast.makeText(HomePage.this, "This device does not support BlueTooth !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!bluetoothAdapter.isEnabled()) {
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                }
                else {
                Intent intent = new Intent(HomePage.this, EnLigne_menu.class);
                startActivity(intent);
                finish();
                }
            }
        });



    }
}