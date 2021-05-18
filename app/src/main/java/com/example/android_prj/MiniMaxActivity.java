package com.example.android_prj;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tic_tac_toe.R;

public class MiniMaxActivity extends AppCompatActivity implements View.OnClickListener{
    /**
     * author: @Aniss
     * concept: Implementation of the MiniMax Algorithm
     *
     */
    TextView playerOneScore, playerTwoScore;    // layout Score Board
    ImageView[] cases = new ImageView[9];
    Button reset;                               // layout reset button
    public String text;                       //Maybe we're not going to use it
    public PerfectComputerLogic perfectLogic;   //MINIMAX
    int playerOneScoreCount, playerTwoScoreCount;// score keeping variables PLAYER AND COMPUTER
    boolean activeRound, activePlayer;          // iterate rounds btw player and computer
    int movesCounter;

    // 0 for case free; 1 for case occupied by Player; 2 for case occupied by computer
    int [] casesState = {0,0,0,0,0,0,0,0,0};

    public boolean playerTurn = true;
    public boolean playerWon = false;
    public boolean computerWon = false;
    public boolean draw = false;
    public boolean winnable = true;

    Handler handler = new Handler();
    final Thread anothergameM = new Thread(){
        @Override
        public void run() {
            anotherGameMA();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

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
        text="Player's turn";
        System.out.println(text);

        reset.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                activeRound = true;
                activePlayer = true;
                movesCounter = 0;
                playerOneScoreCount = 0;
                playerTwoScoreCount = 0;
                updatePlayerScore();
                //playAgain();

                text="Player's turn";
                System.out.println(text);
                playerTurn = true;
                playerWon = false;
                computerWon = false;
                draw = false;
                casesState[0] = 0;
                casesState[1] = 0;
                casesState[2] = 0;
                casesState[3] = 0;
                casesState[4] = 0;
                casesState[5] = 0;
                casesState[6] = 0;
                casesState[7] = 0;
                casesState[8] = 0;
                for(int i = 0; i<9; i++){
                    cases[i].setBackground(null);
                    cases[i].setBackgroundColor(Color.rgb(255,235,59));}
            }
        });
        perfectLogic = new PerfectComputerLogic();
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
        check(); // check if the game is over
        if(playerWon && !draw) {
            text = "Player Won!";
            System.out.println(text);
            handler.postDelayed(anothergameM, 1000);
            playerOneScoreCount++;
            updatePlayerScore();
        }else if(computerWon && !draw) {
            text = "Computer Won!";
            System.out.println(text);
            handler.postDelayed(anothergameM, 1000);
            playerTwoScoreCount++;
            updatePlayerScore();
        }else if(draw) {
            text = "It's a draw!";
            System.out.println(text);
            handler.postDelayed(anothergameM, 1000);
            updatePlayerScore();
        }else
            changeTurn();
    }

    public void changeTurn() {
        playerTurn = !playerTurn;
        if(playerTurn)
            text = "Player's Turn";
        else
            text = "Computer's Turn";
        System.out.println(text);
    }

    public void check() {
        if (crossHorizontal() || crossVertical() || crossDiagonal()) {
            playerWon = true;

        } else if (naughtsHorizontal() || naughtsVertical()
                || naughtsDiagonal()) {
            computerWon = true;
        } else if(full() && !playerWon && !computerWon)
            draw = true;
    }

    @Override
    public void onClick(View v) {
        int move;
        // Player Move: Start

        //get the Num of the case Clicked
        String caseId = v.getResources().getResourceName(v.getId());
        System.out.println(caseId);
        int caseNum = Integer.parseInt(caseId.substring(caseId.length()-1, caseId.length())) - 1;
        // check if it's an empty case
        if (this.casesState[caseNum] != 0){
            return;
        }
        placeObject(caseNum);
        perfectLogic.nextMove(this); // Use minimax
    }

    public boolean full(){
        return casesState[0]!=0
                && casesState[1]!=0
                && casesState[2]!=0
                && casesState[3]!=0
                && casesState[4]!=0
                && casesState[5]!=0
                && casesState[6]!=0
                && casesState[7]!=0
                && casesState[8]!=0;
    }
    public boolean crossHorizontal() {
        return (casesState[0]==1 && casesState[1]==1 && casesState[2]==1) ||
                (casesState[3]==1 && casesState[4]==1 && casesState[5]==1) ||
                (casesState[6]==1 && casesState[7]==1 && casesState[8]==1);
    }

    public boolean naughtsHorizontal() {
        return (casesState[0]==2 && casesState[1]==2 && casesState[2]==2) ||
                (casesState[3]==2 && casesState[4]==2 && casesState[5]==2) ||
                (casesState[6]==2 && casesState[7]==2 && casesState[8]==2);
    }

    public boolean crossVertical() {
        return (casesState[0]==1 && casesState[3]==1 && casesState[6]==1) ||
                (casesState[1]==1 && casesState[4]==1 && casesState[7]==1) ||
                (casesState[2]==1 && casesState[5]==1 && casesState[8]==1);
    }

    public boolean naughtsVertical() {
        return (casesState[0]==2 && casesState[3]==2 && casesState[6]==2) ||
                (casesState[1]==2 && casesState[4]==2 && casesState[7]==2) ||
                (casesState[2]==2 && casesState[5]==2 && casesState[8]==2);
    }

    public boolean crossDiagonal() {
        return  (casesState[0]==1 && casesState[4]==1 && casesState[8]==1) ||
                (casesState[2]==1 && casesState[4]==1 && casesState[6]==1);
    }

    public boolean naughtsDiagonal() {
        return (casesState[0]==2 && casesState[4]==2 && casesState[8]==2) ||
                (casesState[2]==2 && casesState[4]==2 && casesState[6]==2);
    }

    public void anotherGameMA(){
        text="Player's turn";
        System.out.println(text);
        playerTurn = true;
        playerWon = false;
        computerWon = false;
        draw = false;
        casesState[0] = 0;
        casesState[1] = 0;
        casesState[2] = 0;
        casesState[3] = 0;
        casesState[4] = 0;
        casesState[5] = 0;
        casesState[6] = 0;
        casesState[7] = 0;
        casesState[8] = 0;
        for(int i = 0; i<9; i++){
            cases[i].setBackground(null);
            cases[i].setBackgroundColor(Color.rgb(255,235,59));}

    }
    public void updatePlayerScore(){
        String playerOneScoreText = (String) playerOneScore.getText();
        playerOneScore.setText(playerOneScoreText.substring(0, playerOneScoreText.length()-1) + this.playerOneScoreCount);
        String playerTwoScoreText = (String) playerTwoScore.getText();
        playerTwoScore.setText(playerTwoScoreText.substring(0, playerTwoScoreText.length()-1) + this.playerTwoScoreCount);

    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to quit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent backtodash = new Intent(MiniMaxActivity.this, Dashboard.class);
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