package com.example.android_prj;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MiniMaxActivity extends AppCompatActivity implements View.OnClickListener{
    /**
     * author: @Aniss
     * concept: Implementation of the MiniMax Algorithm
     *
     */
    TextView playerOneScore, playerTwoScore;    // layout Score Board
    ImageView[] cases = new ImageView[9];
    Button reset;                               // layout reset button
    int playerOneScoreCount, playerTwoScoreCount;// score keeping variables
    boolean activeRound, activePlayer;          // iterate rounds btw player and computer
    int movesCounter;

    public boolean playerTurn = false;
    public boolean playerWon = false;
    public boolean computerWon = false;
    public boolean draw = false;
    public boolean winnable = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_max);

        playerOneScore = (TextView) findViewById(R.id.scoreX);
        playerTwoScore = (TextView) findViewById(R.id.scoreY);
        reset = (Button) findViewById(R.id.Reset);
        for (int i = 0; i < cases.length; i++){
            String caseId = "buttonImage" + (i+1);
            int resourceId = getResources().getIdentifier(caseId, "id", getPackageName());
            cases[i] = (ImageView) findViewById(resourceId);
            cases[i].setOnClickListener(this);
        }

        activeRound = true;
        activePlayer = true;
        movesCounter = 0;
        playerOneScoreCount = 0;
        playerTwoScoreCount = 0;

        reset.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                activeRound = true;
                activePlayer = true;
                movesCounter = 0;
                playerOneScoreCount = 0;
                playerTwoScoreCount = 0;
                //updatePlayerScore();
                //playAgain();
            }
        });

    }


    @Override
    public void onClick(View v) {

    }
}