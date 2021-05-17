package com.tic_tac_toe.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tic_tac_toe.R;

//import com.example.minimaxtictactoe.R;

public class LoginActivity extends AppCompatActivity {


    private TextView twoPlayerLabel;
    private TextView optionsLabel;
    private MediaPlayer intro;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        twoPlayerLabel = (TextView) findViewById(R.id.two_player_label);
        optionsLabel = (TextView) findViewById(R.id.options_label);
    }



    public void twoPlayer(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        final View layout = inflater.inflate(R.layout.two_player_options, null);
        //final Intent twoPlayerLocal = new Intent(LoginActivity.this, TwoPlayerActivityLocal.class);
        final TextView playerOneName = (TextView) layout.findViewById(R.id.player_one);
        final TextView playerTwoName = (TextView) layout.findViewById(R.id.player_two);
        //final RadioButton localGame = (RadioButton) layout.findViewById(R.id.local_game);
        //final RadioButton bluetoothGame = (RadioButton) layout.findViewById(R.id.bluetooth_game);
        //final RadioButton oMarker = (RadioButton) layout.findViewById(R.id.o_marker);
        //final RadioButton xMarker = (RadioButton) layout.findViewById(R.id.x_marker);
/*
        localGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTwoName.setVisibility(View.VISIBLE);
                oMarker.setVisibility(View.VISIBLE);
                xMarker.setVisibility(View.VISIBLE);
            }
        });*/
/*
        bluetoothGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTwoName.setVisibility(View.GONE);
                oMarker.setVisibility(View.GONE);
                xMarker.setVisibility(View.GONE);

            }
        });

        oMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oMarker.setBackgroundResource(R.drawable.tic_tac_toe_o_black);
                xMarker.setBackgroundResource(R.drawable.tic_tac_toe_x);
            }
        });

        xMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xMarker.setBackgroundResource(R.drawable.tic_tac_toe_x_black);
                oMarker.setBackgroundResource(R.drawable.tic_tac_toe_o);
            }
        });*/
        builder.setView(layout)
                .setTitle("Two Player Game Options")
                .setPositiveButton("Start", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                            Intent bluetoothIntent = new Intent(LoginActivity.this, TwoPlayerActivityBluetooth.class);
                            String playerOne = null;
                            if (playerOneName.getText().length() > 0) {
                                playerOne = playerOneName.getText().toString();
                                bluetoothIntent.putExtra("playerOne", playerOne);
                                startActivity(bluetoothIntent);
                            } else {
                                Toast.makeText(LoginActivity.this, "Please enter a player name", Toast.LENGTH_SHORT).show();
                            }

                        }

                    }
                )
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        // Create the AlertDialog object and return it

        builder.create().show();
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

