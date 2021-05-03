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
    public TextView text;                       //Maybe we're not going to use it
    //public PerfectComputerLogic perfectLogic;   MINIMAX
    int playerOneScoreCount, playerTwoScoreCount;// score keeping variables
    boolean activeRound, activePlayer;          // iterate rounds btw player and computer
    int movesCounter;

    // 0 for case free; 1 for case occupied by Player; 2 for case occupied by computer
    int [] casesState = {0,0,0,0,0,0,0,0,0};

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

    public void placeObject(int move) {
        // get the chosen move View
        View w = this.cases[move];
        if(casesState[move] != 0){
            return;
        }

        if(playerTurn){
            w.setBackgroundResource(R.drawable.cross);
            this.casesState[move] = 1; //occupied by player
        }
        //check(); // check if the game is over
        if(playerWon && !draw)
            text.setText("Player Won!");
        else if(computerWon && !draw)
            text.setText("Computer Won!");
        else if(draw)
            text.setText("It's a draw!");
        else
            changeTurn();
    }

    public void changeTurn() {
        playerTurn = !playerTurn;
        if(playerTurn)
            text.setText("Player's Turn");
        else
            text.setText("Computer's Turn");
    }

    @Override
    public void onClick(View v) {
        int move;
        // Player Move: Start

        //get the Num of the case Clicked
        String caseId = v.getResources().getResourceName(v.getId());
        int caseNum = Integer.parseInt(caseId.substring(caseId.length()-1, caseId.length())) - 1;
        // check if it's an empty case
        if (this.casesState[caseNum] != 0){
            return;
        }
        placeObject(caseNum);
    }


}