package com.example.android_prj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {

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

        /*boutonOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, buttonOnline.class);
                startActivity(intent);
                finish();
            }
        });*/



    }
}