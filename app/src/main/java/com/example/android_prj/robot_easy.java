package com.example.android_prj;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tic_tac_toe.R;

import java.util.Random;

import static java.lang.Thread.sleep;

public class robot_easy extends AppCompatActivity {

    ImageView button1,button2,button3,button4,button5,button6,button7,button8,button9;
    private String startGame="X";
    int b1=5,b2=5,b3=5,b4=5,b5=5,b6=5,b7=5,b8=5,b9=5,xCount=0,oCount=0,i=0;
    int c1=0,c2=0,c3=0,c4=0,c5=0,c6=0,c7=0,c8=0,c9=0;
    private TextView scorex,scoreo;
    private Button Reset;

    //waiting 2sec, then calling robot
    final Handler handler = new Handler();
    final Runnable r = new Runnable() {
        public void run() {
            playRobot();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.robot_easy);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.robot_easy);
        button1=findViewById(R.id.buttonImage1);
        button2=findViewById(R.id.buttonImage2);

        button3=findViewById(R.id.buttonImage3);

        button4=findViewById(R.id.buttonImage4);

        button5=findViewById(R.id.buttonImage5);
        button6=findViewById(R.id.buttonImage6);
        button7=findViewById(R.id.buttonImage7);
        button8=findViewById(R.id.buttonImage8);
        button9=findViewById(R.id.buttonImage9);

        scorex = findViewById(R.id.ScoreX);
        scoreo = findViewById(R.id.ScoreY);

        Reset=findViewById(R.id.Reset);

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setImageDrawable(null);
                button2.setImageDrawable(null);
                button3.setImageDrawable(null);
                button4.setImageDrawable(null);
                button5.setImageDrawable(null);
                button6.setImageDrawable(null);
                button7.setImageDrawable(null);
                button8.setImageDrawable(null);
                button9.setImageDrawable(null);
                resetValues();
                xCount=0;
                oCount=0;
                scorex.setText("Player : "+String.valueOf(xCount));
                scoreo.setText("Robot  : "+String.valueOf(oCount));
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(c1==0) {



                    if (startGame.equals("X")) {
                        button1.setImageResource(R.drawable.cross);
                        b1 = 1;
                        i++;
                        c1=1;
                    } else {
                        button1.setImageResource(R.drawable.circle);
                        b1 = 0;
                        i++;
                        c1=1;
                    }
                    if(winningGame()!=1){
                        choosePlayer();
                        handler.postDelayed(r, 1000);
                    }

                    /*winningGame();*/

                }
                else
                {
                    Toast.makeText(robot_easy.this,"Button Already Pressed",Toast.LENGTH_SHORT).show();
                }

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(c2==0)
                {


                    if(startGame.equals("X"))
                    {
                        button2.setImageResource(R.drawable.cross);
                        b2=1;
                        i++;
                        c2=1;
                    }
                    else
                    {
                        button2.setImageResource(R.drawable.circle);
                        b2=0;
                        i++;
                        c2=1;
                    }
                    if(winningGame()!=1){
                        choosePlayer();
                        handler.postDelayed(r, 1000);
                    }
                    /*winningGame();*/

                }
                else
                {
                    Toast.makeText(robot_easy.this,"Button Already Pressed",Toast.LENGTH_SHORT).show();
                }

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(c3==0)
                {


                    if(startGame.equals("X"))
                    {
                        button3.setImageResource(R.drawable.cross);
                        b3=1;
                        i++;
                        c3=1;
                    }
                    else
                    {
                        button3.setImageResource(R.drawable.circle);
                        b3=0;
                        i++;
                        c3=1;
                    }
                    if(winningGame()!=1){
                        choosePlayer();
                        handler.postDelayed(r, 1000);
                    }
                    /*winningGame();*/


                }
                else
                {
                    Toast.makeText(robot_easy.this,"Button Already Pressed",Toast.LENGTH_SHORT).show();
                }




            }

        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(c4==0)
                {

                    if(startGame.equals("X"))
                    {
                        button4.setImageResource(R.drawable.cross);
                        b4=1;
                        i++;
                        c4=1;
                    }
                    else
                    {
                        button4.setImageResource(R.drawable.circle);
                        b4=0;
                        i++;
                        c4=1;
                    }
                    if(winningGame()!=1){
                        choosePlayer();
                        handler.postDelayed(r, 1000);
                    }
                    /*winningGame();*/


                }
                else
                {
                    Toast.makeText(robot_easy.this,"Button Already Pressed",Toast.LENGTH_SHORT).show();
                }

            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(c5==0)
                {

                    if(startGame.equals("X"))
                    {
                        button5.setImageResource(R.drawable.cross);
                        b5=1;
                        i++;
                        c5=1;
                    }
                    else
                    {
                        button5.setImageResource(R.drawable.circle);
                        b5=0;
                        i++;
                        c5=1;
                    }
                    if(winningGame()!=1){
                        choosePlayer();
                        handler.postDelayed(r, 1000);
                    }
                    /*winningGame();*/

                }
                else
                {
                    Toast.makeText(robot_easy.this,"Button Already Pressed",Toast.LENGTH_SHORT).show();
                }

            }
        });


        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(c6==0)
                {

                    if(startGame.equals("X"))
                    {
                        button6.setImageResource(R.drawable.cross);
                        b6=1;
                        i++;
                        c6=1;
                    }
                    else
                    {
                        button6.setImageResource(R.drawable.circle);
                        b6=0;
                        i++;
                        c6=1;
                    }
                    if(winningGame()!=1){
                        choosePlayer();
                        handler.postDelayed(r, 1000);
                    }
                    /*winningGame();*/


                }
                else
                {
                    Toast.makeText(robot_easy.this,"Button Already Pressed",Toast.LENGTH_SHORT).show();
                }
            }
        });


        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(c7==0)
                {

                    if(startGame.equals("X"))
                    {
                        button7.setImageResource(R.drawable.cross);
                        b7=1;
                        i++;
                        c7=1;
                    }
                    else
                    {
                        button7.setImageResource(R.drawable.circle);
                        b7=0;
                        i++;
                        c7=1;
                    }
                    if(winningGame()!=1){
                        choosePlayer();
                        handler.postDelayed(r, 1000);
                    }
                    /*winningGame();*/


                }
                else
                {
                    Toast.makeText(robot_easy.this,"Button Already Pressed",Toast.LENGTH_SHORT).show();
                }
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(c8==0)
                {

                    if(startGame.equals("X"))
                    {
                        button8.setImageResource(R.drawable.cross);
                        b8=1;
                        i++;
                        c8=1;
                    }
                    else
                    {
                        button8.setImageResource(R.drawable.circle);
                        b8=0;
                        i++;
                        c8=1;
                    }
                    if(winningGame()!=1){
                        choosePlayer();
                        handler.postDelayed(r, 1000);
                    }
                    /*winningGame();*/
                }
                else
                {
                    Toast.makeText(robot_easy.this,"Button Already Pressed",Toast.LENGTH_SHORT).show();
                }

            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(c9==0)
                {

                    if(startGame.equals("X"))
                    {
                        button9.setImageResource(R.drawable.cross);
                        b9=1;
                        i++;
                        c9=1;
                    }
                    else
                    {
                        button9.setImageResource(R.drawable.circle);
                        b9=0;
                        i++;
                        c9=1;
                    }
                    if(winningGame()!=1){
                        choosePlayer();
                        handler.postDelayed(r, 1000);
                    }
                    /*winningGame();*/

                }
                else
                {
                    Toast.makeText(robot_easy.this,"Button Already Pressed",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }





    private int winningGame()
    {


        if((b1==1) && (b2==1) && (b3==1))
        {

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Player Wins").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    button1.setImageDrawable(null);
                    button2.setImageDrawable(null);
                    button3.setImageDrawable(null);
                    button4.setImageDrawable(null);
                    button5.setImageDrawable(null);
                    button6.setImageDrawable(null);
                    button7.setImageDrawable(null);
                    button8.setImageDrawable(null);
                    button9.setImageDrawable(null);
                    resetValues();
                }
            });

            AlertDialog alertDialog=builder.create();
            alertDialog.show();
            xCount++;
            scorex.setText("Player : "+String.valueOf(xCount));
            return 1;
        }


        else if((b4==1) && (b5==1) && (b6==1))
        {

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Player Wins").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    button1.setImageDrawable(null);
                    button2.setImageDrawable(null);
                    button3.setImageDrawable(null);
                    button4.setImageDrawable(null);
                    button5.setImageDrawable(null);
                    button6.setImageDrawable(null);
                    button7.setImageDrawable(null);
                    button8.setImageDrawable(null);
                    button9.setImageDrawable(null);
                    resetValues();
                }
            });

            AlertDialog alertDialog=builder.create();
            alertDialog.show();
            xCount++;
            scorex.setText("Player : "+String.valueOf(xCount));
            return 1;

        }


        else  if((b7==1) && (b8==1) && (b9==1))
        {

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Player Wins").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    button1.setImageDrawable(null);
                    button2.setImageDrawable(null);
                    button3.setImageDrawable(null);
                    button4.setImageDrawable(null);
                    button5.setImageDrawable(null);
                    button6.setImageDrawable(null);
                    button7.setImageDrawable(null);
                    button8.setImageDrawable(null);
                    button9.setImageDrawable(null);
                    resetValues();
                }
            });

            AlertDialog alertDialog=builder.create();
            alertDialog.show();
            xCount++;
            scorex.setText("Player : "+String.valueOf(xCount));
            return 1;

        }

        else  if((b1==1) && (b4==1) && (b7==1))
        {

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Player Wins").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    button1.setImageDrawable(null);
                    button2.setImageDrawable(null);
                    button3.setImageDrawable(null);
                    button4.setImageDrawable(null);
                    button5.setImageDrawable(null);
                    button6.setImageDrawable(null);
                    button7.setImageDrawable(null);
                    button8.setImageDrawable(null);
                    button9.setImageDrawable(null);
                    resetValues();
                }
            });

            AlertDialog alertDialog=builder.create();
            alertDialog.show();
            xCount++;
            scorex.setText("Player : "+String.valueOf(xCount));

            return 1;

        }


        else  if((b2==1) && (b5==1) && (b8==1))
        {

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Player Wins").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    button1.setImageDrawable(null);
                    button2.setImageDrawable(null);
                    button3.setImageDrawable(null);
                    button4.setImageDrawable(null);
                    button5.setImageDrawable(null);
                    button6.setImageDrawable(null);
                    button7.setImageDrawable(null);
                    button8.setImageDrawable(null);
                    button9.setImageDrawable(null);
                    resetValues();
                }
            });

            AlertDialog alertDialog=builder.create();
            alertDialog.show();
            xCount++;
            scorex.setText("Player : "+String.valueOf(xCount));

            return 1;
        }


        else  if((b3==1) && (b6==1) && (b9==1))
        {

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Player Wins").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    button1.setImageDrawable(null);
                    button2.setImageDrawable(null);
                    button3.setImageDrawable(null);
                    button4.setImageDrawable(null);
                    button5.setImageDrawable(null);
                    button6.setImageDrawable(null);
                    button7.setImageDrawable(null);
                    button8.setImageDrawable(null);
                    button9.setImageDrawable(null);
                    resetValues();
                }
            });

            AlertDialog alertDialog=builder.create();
            alertDialog.show();
            xCount++;
            scorex.setText("Player : "+String.valueOf(xCount));
            return 1;
        }


        else  if((b1==1) && (b5==1) && (b9==1))
        {

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Player Wins").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    button1.setImageDrawable(null);
                    button2.setImageDrawable(null);
                    button3.setImageDrawable(null);
                    button4.setImageDrawable(null);
                    button5.setImageDrawable(null);
                    button6.setImageDrawable(null);
                    button7.setImageDrawable(null);
                    button8.setImageDrawable(null);
                    button9.setImageDrawable(null);
                    resetValues();
                }
            });

            AlertDialog alertDialog=builder.create();
            alertDialog.show();
            xCount++;
            scorex.setText("Player : "+String.valueOf(xCount));
            return 1;
        }

        else  if((b3==1) && (b5==1) && (b7==1))
        {

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Player Wins").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    button1.setImageDrawable(null);
                    button2.setImageDrawable(null);
                    button3.setImageDrawable(null);
                    button4.setImageDrawable(null);
                    button5.setImageDrawable(null);
                    button6.setImageDrawable(null);
                    button7.setImageDrawable(null);
                    button8.setImageDrawable(null);
                    button9.setImageDrawable(null);
                    resetValues();
                }
            });

            AlertDialog alertDialog=builder.create();
            alertDialog.show();
            xCount++;
            scorex.setText("Player : "+String.valueOf(xCount));
            return 1;

        }

        else  if((b1==0) && (b2==0) && (b3==0))
        {

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Robot Wins").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    button1.setImageDrawable(null);
                    button2.setImageDrawable(null);
                    button3.setImageDrawable(null);
                    button4.setImageDrawable(null);
                    button5.setImageDrawable(null);
                    button6.setImageDrawable(null);
                    button7.setImageDrawable(null);
                    button8.setImageDrawable(null);
                    button9.setImageDrawable(null);
                    resetValues();
                }
            });

            AlertDialog alertDialog=builder.create();
            alertDialog.show();
            oCount++;
            scoreo.setText("Robot : "+String.valueOf(oCount));
            return 1;
        }
        else  if((b4==0) && (b5==0) && (b6==0))
        {

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Robot Wins").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    button1.setImageDrawable(null);
                    button2.setImageDrawable(null);
                    button3.setImageDrawable(null);
                    button4.setImageDrawable(null);
                    button5.setImageDrawable(null);
                    button6.setImageDrawable(null);
                    button7.setImageDrawable(null);
                    button8.setImageDrawable(null);
                    button9.setImageDrawable(null);
                    resetValues();
                }
            });

            AlertDialog alertDialog=builder.create();
            alertDialog.show();
            oCount++;
            scoreo.setText("Robot : "+String.valueOf(oCount));
            return 1;
        }



        else  if((b7==0) && (b8==0) && (b9==0))
        {

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Robot Wins").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    button1.setImageDrawable(null);
                    button2.setImageDrawable(null);
                    button3.setImageDrawable(null);
                    button4.setImageDrawable(null);
                    button5.setImageDrawable(null);
                    button6.setImageDrawable(null);
                    button7.setImageDrawable(null);
                    button8.setImageDrawable(null);
                    button9.setImageDrawable(null);
                    resetValues();
                }
            });

            AlertDialog alertDialog=builder.create();
            alertDialog.show();
            oCount++;
            scoreo.setText("Robot : "+String.valueOf(oCount));
            return 1;

        }


        else  if((b1==0) && (b4==0) && (b7==0))
        {

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Robot Wins").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    button1.setImageDrawable(null);
                    button2.setImageDrawable(null);
                    button3.setImageDrawable(null);
                    button4.setImageDrawable(null);
                    button5.setImageDrawable(null);
                    button6.setImageDrawable(null);
                    button7.setImageDrawable(null);
                    button8.setImageDrawable(null);
                    button9.setImageDrawable(null);
                    resetValues();
                }
            });

            AlertDialog alertDialog=builder.create();
            alertDialog.show();
            oCount++;
            scoreo.setText("Robot : "+String.valueOf(oCount));
            return 1;
        }

        else  if((b2==0) && (b5==0) && (b8==0))
        {

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Robot Wins").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    button1.setImageDrawable(null);
                    button2.setImageDrawable(null);
                    button3.setImageDrawable(null);
                    button4.setImageDrawable(null);
                    button5.setImageDrawable(null);
                    button6.setImageDrawable(null);
                    button7.setImageDrawable(null);
                    button8.setImageDrawable(null);
                    button9.setImageDrawable(null);
                    resetValues();
                }
            });

            AlertDialog alertDialog=builder.create();
            alertDialog.show();
            oCount++;
            scoreo.setText("Robot : "+String.valueOf(oCount));
            return 1;

        }
        else  if((b3==0) && (b6==0) && (b9==0))
        {

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Robot Wins").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    button1.setImageDrawable(null);
                    button2.setImageDrawable(null);
                    button3.setImageDrawable(null);
                    button4.setImageDrawable(null);
                    button5.setImageDrawable(null);
                    button6.setImageDrawable(null);
                    button7.setImageDrawable(null);
                    button8.setImageDrawable(null);
                    button9.setImageDrawable(null);
                    resetValues();
                }
            });

            AlertDialog alertDialog=builder.create();
            alertDialog.show();
            oCount++;
            scoreo.setText("Robot : "+String.valueOf(oCount));
            return 1;
        }
        else  if((b1==0) && (b5==0) && (b9==0))
        {

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Robot Wins").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    button1.setImageDrawable(null);
                    button2.setImageDrawable(null);
                    button3.setImageDrawable(null);
                    button4.setImageDrawable(null);
                    button5.setImageDrawable(null);
                    button6.setImageDrawable(null);
                    button7.setImageDrawable(null);
                    button8.setImageDrawable(null);
                    button9.setImageDrawable(null);
                    resetValues();
                }
            });

            AlertDialog alertDialog=builder.create();
            alertDialog.show();
            oCount++;
            scoreo.setText("Robot : "+String.valueOf(oCount));
            return 1;

        }

        else  if((b3==0) && (b5==0) && (b7==0))
        {

            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Robot Wins").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    button1.setImageDrawable(null);
                    button2.setImageDrawable(null);
                    button3.setImageDrawable(null);
                    button4.setImageDrawable(null);
                    button5.setImageDrawable(null);
                    button6.setImageDrawable(null);
                    button7.setImageDrawable(null);
                    button8.setImageDrawable(null);
                    button9.setImageDrawable(null);
                    resetValues();
                }
            });

            AlertDialog alertDialog=builder.create();
            alertDialog.show();
            oCount++;
            scoreo.setText("Robot : "+String.valueOf(oCount));
            return 1;

        }

        else
        {
            if(i==9)
            {
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setMessage("No One Wins").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        button1.setImageDrawable(null);
                        button2.setImageDrawable(null);
                        button3.setImageDrawable(null);
                        button4.setImageDrawable(null);
                        button5.setImageDrawable(null);
                        button6.setImageDrawable(null);
                        button7.setImageDrawable(null);
                        button8.setImageDrawable(null);
                        button9.setImageDrawable(null);
                        resetValues();
                    }
                });

                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
            return 0;

        }


    }


    private void choosePlayer()
    {
        if(startGame.equals("X"))
        {
            startGame="O";
        }
        else
        {
            startGame="X";
        }
    }

    private void playRobot()
    {


        Random rand = new Random();
        int random = rand.nextInt(9);

        if(random==0){
            if(c1==0)
            {

                if(startGame.equals("X"))
                {

                    button1.setImageResource(R.drawable.cross);
                    b1=1;
                    i++;
                    c1=1;
                }
                else
                {

                    button1.setImageResource(R.drawable.circle);
                    b1=0;
                    i++;
                    c1=1;
                }
                winningGame();
                choosePlayer();

            } else {
                playRobot();
            }
        }
        else if(random==1){
            if(c2==0)
            {

                if(startGame.equals("X"))
                {

                    button2.setImageResource(R.drawable.cross);
                    b2=1;
                    i++;
                    c2=1;
                }
                else
                {

                    button2.setImageResource(R.drawable.circle);
                    b2=0;
                    i++;
                    c2=1;
                }
                winningGame();
                choosePlayer();

            } else {
                playRobot();
            }
        }
        else if(random==2){
            if(c3==0)
            {

                if(startGame.equals("X"))
                {

                    button3.setImageResource(R.drawable.cross);
                    b3=1;
                    i++;
                    c3=1;
                }
                else
                {

                    button3.setImageResource(R.drawable.circle);
                    b3=0;
                    i++;
                    c3=1;
                }
                winningGame();
                choosePlayer();

            } else {
                playRobot();
            }
        }
        else if(random==3){
            if(c4==0)
            {

                if(startGame.equals("X"))
                {

                    button4.setImageResource(R.drawable.cross);
                    b4=1;
                    i++;
                    c4=1;
                }
                else
                {

                    button4.setImageResource(R.drawable.circle);
                    b4=0;
                    i++;
                    c4=1;
                }
                winningGame();
                choosePlayer();

            } else {
                playRobot();
            }
        }
        else if(random==4){
            if(c5==0)
            {

                if(startGame.equals("X"))
                {

                    button5.setImageResource(R.drawable.cross);
                    b5=1;
                    i++;
                    c5=1;
                }
                else
                {

                    button5.setImageResource(R.drawable.circle);
                    b5=0;
                    i++;
                    c5=1;
                }
                winningGame();
                choosePlayer();

            } else {
                playRobot();
            }
        }
        else if(random==5){
            if(c6==0)
            {

                if(startGame.equals("X"))
                {

                    button6.setImageResource(R.drawable.cross);
                    b6=1;
                    i++;
                    c6=1;
                }
                else
                {

                    button6.setImageResource(R.drawable.circle);
                    b6=0;
                    i++;
                    c6=1;
                }
                winningGame();
                choosePlayer();

            } else {
                playRobot();
            }
        }
        else if(random==6){
            if(c7==0)
            {

                if(startGame.equals("X"))
                {

                    button7.setImageResource(R.drawable.cross);
                    b7=1;
                    i++;
                    c7=1;
                }
                else
                {

                    button7.setImageResource(R.drawable.circle);
                    b7=0;
                    i++;
                    c7=1;
                }
                winningGame();
                choosePlayer();

            } else {
                playRobot();
            }
        }
        else if(random==7){
            if(c8==0)
            {

                if(startGame.equals("X"))
                {

                    button8.setImageResource(R.drawable.cross);
                    b8=1;
                    i++;
                    c8=1;
                }
                else
                {

                    button8.setImageResource(R.drawable.circle);
                    b8=0;
                    i++;
                    c8=1;
                }
                winningGame();
                choosePlayer();

            } else {
                playRobot();
            }
        }
        else if(random==8){
            if(c9==0)
            {

                if(startGame.equals("X"))
                {

                    button9.setImageResource(R.drawable.cross);
                    b9=1;
                    i++;
                    c9=1;
                }
                else
                {

                    button9.setImageResource(R.drawable.circle);
                    b9=0;
                    i++;
                    c9=1;
                }
                winningGame();
                choosePlayer();

            } else {
                playRobot();
            }
        }
        /*else {
            Toast.makeText(MainActivity.this,"Button Already Pressed",Toast.LENGTH_SHORT).show();
        }*/





    }



    private void resetValues() {

        b1=5;
        b2=5;;
        b3=5;
        b4=5;
        b5=5;
        b6=5;
        b7=5;
        b8=5;
        b9=5;
        i=0;
        c1=0;
        c2=0;

        c3=0;
        c4=0;
        c5=0;
        c6=0;
        c7=0;
        c8=0;
        c9=0;




    }
    public void onBackPressed() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to quit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent backtodash = new Intent(robot_easy.this, Robot_levels.class);
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