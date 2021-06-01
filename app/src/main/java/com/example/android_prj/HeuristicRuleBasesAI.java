package com.example.android_prj;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tic_tac_toe.R;

import java.util.ArrayList;

public class HeuristicRuleBasesAI extends AppCompatActivity implements View.OnClickListener {
    /**
     * author: @amine
     * concept: evaluating each line (row:0,1,2; column:3,4,5; diagonal:6,7)
     *      for each Player move we add +10, for each Computer move we add -10
     *      Rule 1: If I have a winning move, take it.
     *      Rule 2: If the opponent has a winning move, block it.
     *      Rule 3: If I can create a fork (two winning ways) after this move, do it.
     *      Rule 4: Do not let the opponent creating a fork after my move.
     *      Rule 5: Place in the position such as I minimize c ost.
     *
     */

    TextView playerOneScore, playerTwoScore;    // layout Score Board
    ImageView [] cases = new ImageView[9];      // layout cases
    Button reset;                               // layout reset button
    int playerOneScoreCount, playerTwoScoreCount;// score keeping variables
    int movesCounter;
    boolean activeRound, activePlayer;          // iterate rounds btw player and computer

    // a line with +30 means Player win / with -30 means Computer win
    int [] linesValues = {0,0,0,0,0,0,0,0};

    // cases values = sum of line values it affects
    int [] casesValues = {0,0,0,0,0,0,0,0,0};

    // 0 for case free; 1 for case occupied by Player; 2 for case occupied by computer
    int [] casesState = {0,0,0,0,0,0,0,0,0};

    // affected lines by a case choice
    int [][] affectedLines = {{0,3,6}, {0,4}, {0,5,7}, {1,3}, {1,4,6,7}, {1,5}, {2,3,7}, {2,4}, {2,5,6}};

    // robot delay handler
    final Handler handler = new Handler();
    final Thread r = new Thread(){
        @Override
        public void run() {
            if (!activePlayer)
                playRobot();
        }
    };
    final Thread replay = new Thread(){
        @Override
        public void run() {
            if (endGame(1) || endGame(-1)){
                playAgain();
            }
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

        reset.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                activeRound = true;
                activePlayer = true;
                movesCounter = 0;
                playerOneScoreCount = 0;
                playerTwoScoreCount = 0;
                updatePlayerScore();
                playAgain();
            }
        });
    }


    /**
     *
     * @return true if there's a winner.
     */
    protected boolean checkWinner(int player){
        boolean result = false;
        for (int i = 0; i < this.linesValues.length; i++){
            if (this.linesValues[i] == player * 30) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     *
     * @param player
     * check if the game ended, if so it shows the result and clears the board
     */
    public boolean endGame(int player){
        boolean result = false;
        if (checkWinner(player)){
            if (player > 0){
                playerOneScoreCount ++;
                updatePlayerScore();
                Toast.makeText(HeuristicRuleBasesAI.this, "You Won!", Toast.LENGTH_SHORT).show();
            }
            else{
                playerTwoScoreCount ++;
                updatePlayerScore();
                Toast.makeText(HeuristicRuleBasesAI.this, "Computer Wins!", Toast.LENGTH_SHORT).show();
            }
            result = true;
        }
        else if (movesCounter == 9){
            Toast.makeText(this, "It's a Draw!", Toast.LENGTH_SHORT).show();
            result = true;
        }
        return result;
    }

    /**
     * update the players scores in the layout
     */
    public void updatePlayerScore(){
        String playerOneScoreText = (String) playerOneScore.getText();
        playerOneScore.setText(playerOneScoreText.substring(0, playerOneScoreText.length()-1) + this.playerOneScoreCount);
        String playerTwoScoreText = (String) playerTwoScore.getText();
        playerTwoScore.setText(playerTwoScoreText.substring(0, playerTwoScoreText.length()-1) + this.playerTwoScoreCount);

    }

    /**
     * clean the board and reset its cases and lines
     */
    public void playAgain(){
        Log.i("play again", "play again called");

        this.movesCounter = 0;
        this.activeRound = !this.activeRound;
        this.activePlayer = this.activeRound;
        for (int i = 0; i < this.cases.length; i++){
            this.casesState[i] = 0;
            this.casesValues[i] = 0;
            this.cases[i].setBackground(null);
            this.cases[i].setBackgroundColor(getResources().getColor(R.color.yellow));
        }
        for (int i = 0; i < this.linesValues.length; i++){
            this.linesValues[i] = 0;
        }

    }

    /**
     *
     * @param player
     * @return winning move if it exists, -1 otherwise.
     */
    protected int winningMove(int player){
        int result = -1;
        for (int i = 0; i < this.linesValues.length; i++){
            if (this.linesValues[i] == player*20){
                if (i < 3) {
                    // cases concerned: [0,1,2] , [3,4,5] , [6,7,8]
                    for (int j = 0; j < 3; j++){
                        if (this.casesState[3*i + j] == 0){
                            result = 3*i + j;
                            break;
                        }
                    }
                }
                else if (i<6) {
                    // cases concerned: [0,3,6] , [1,4,7] , [2,5,8]
                    for (int j = 0; j < 3; j++){
                        if (this.casesState[i-3 + j*3] == 0){
                            result = i-3 + j*3;
                            break;
                        }
                    }
                }
                else{
                    // cases concerned: [0,4,8] , [2,4,6]
                    for (int j = 0; j < 3; j++){
                        int result1 = (2 * (i % 6)) + j * (4 / (i - 5));
                        if (this.casesState[result1] == 0){
                            result = result1;
                            break;
                        }
                    }
                }
                break;
            }
        }
        return result;
    }

    /**
     *
     * @param player
     * @return move creating a fork if it exists, -1 otherwise.
     */
    protected int forkMove(int player){
        int result = -1, countWinningMoves, i, j;
        int [] copyValues = this.linesValues.clone();
        for (i = 0; i < this.casesState.length; i++){
            if (this.casesState[i] == 0){
                countWinningMoves = 0;
                for (j = 0; j<this.affectedLines[i].length; j++){
                    copyValues[affectedLines[i][j]] += player*10;
                }
                for (j = 0; j < copyValues.length; j++){
                    if (copyValues[j] == player*20){
                        countWinningMoves++;
                    }
                }
                if (countWinningMoves > 1){
                    result = i;
                    break;
                }
                copyValues = this.linesValues.clone();
            }
        }
        return result;
    }

    /**
     *
     * @param player
     * @return free case with min(for computer)/max(for player) value
     */
    protected int optimumMove(int player) {
        ArrayList<Integer> freeCases = new ArrayList<Integer>();
        for (int i = 0; i < this.casesState.length; i++){
            if (this.casesState[i] == 0){
                freeCases.add(i);
            }
        }
        int optima = 40, caseNum = 0;
        for (int i = 0; i < freeCases.size(); i++){
            if (this.casesValues[freeCases.get(i)] < optima){
                optima = this.casesValues[freeCases.get(i)];
                caseNum = freeCases.get(i);
            }
        }
        return caseNum;
    }

    /**
     *
     * @param move
     * @param player
     *  update the object after a move.
     */

    protected void playMove(int move, int player){
        // get the chosen move View
        View w = this.cases[move];
        if (player > 0 && this.activePlayer) {
            w.setBackgroundResource(R.drawable.cross);
            this.casesState[move] = 1;
        }
        else if (player < 0){
            w.setBackgroundResource(R.drawable.circle);
            this.casesState[move] = 2;
        }
        else return;

        for (int i = 0; i<this.affectedLines[move].length; i++){
            this.linesValues[affectedLines[move][i]] += player*10;
        }
        this.casesValues[move] = 0;
        // updating cases values
        for (int i = 0; i<this.affectedLines[move].length; i++){
            int line = this.affectedLines[move][i];
            if (line < 3) {
                // lines concerned: [0,1,2] , [3,4,5] , [6,7,8]
                for (int j = 0; j < 3; j++){
                    this.casesValues[3*line + j] += player * 10;
                }
            }
            else if (line < 6) {
                // cases concerned: [0,3,6] , [1,4,7] , [2,5,8]
                for (int j = 0; j < 3; j++){
                    this.casesValues[line-3 + j*3] += player * 10;
                }
            }
            else{
                // cases concerned: [0,4,8] , [2,4,6]
                for (int j = 0; j < 3; j++){
                    this.casesValues[2 * (line % 6) + j * (4 / (line - 5))] += player * 10;
                }
            }
        }
        this.movesCounter++;
        this.activePlayer = !this.activePlayer;
        return;
    }

    protected void playRobot(){
        int move;
        // check for computer winning move
        move = winningMove(-1);
        if (move != -1){
            playMove(move, -1);
        }
        else {
            // check if Player has winning move to block
            move = winningMove(1);
            if (move != -1) {
                playMove(move, -1);
            } else {
                // check if Computer can create a forkMove
                move = forkMove(-1);
                if (move != -1) {
                    playMove(move, -1);
                } else {
                    //check if Computer can has a move creating a forkMove to block
                    move = forkMove(-1);
                    if (move != -1) {
                        playMove(move, -1);
                    } else {
                        //check if Player can has a move creating a forkMove to block
                        move = forkMove(1);
                        if (move != -1) {
                            playMove(move, -1);
                        } else {
                            // none of the above-- choose a case from the line with min value
                            move = optimumMove(-1);
                            if (move != -1) {
                                playMove(move, -1);
                            }
                        }
                    }
                }
            }
        }
        Log.i("Computer Move", Integer.toString(move));
        handler.postDelayed(replay, 250);
    }

    /**
     *
     * @param v
     * Computer action after Player Move
     */
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
        playMove(caseNum, 1);
        // Player Move: End
        handler.postDelayed(replay, 250);
        //-------------------------------------------------------------

        // Computer Move: Start


        handler.postDelayed(r, 500);



    }
    public void onBackPressed() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to quit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent backtodash = new Intent(HeuristicRuleBasesAI.this, Robot_levels.class);
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