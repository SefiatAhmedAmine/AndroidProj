package com.example.android_prj;

import android.graphics.Color;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.tic_tac_toe.R;

import java.util.ArrayList;

public class PerfectComputerLogic extends AppCompatActivity {

    private robot_hard board;
    private String[] textBoard = new String[9]; // The state of the board in text form
    private static String max; // The piece computer is controlling(X or O)
    private static String min; // The piece player is controlling(X or O)
    private String level; // Current level of operation (Max or Min)

    public void nextMove(final robot_hard board) {
        this.board = board;

        if(board.playerTurn)
            return;

        max = "O";
        min = "X";

        level = "max";
        final Thread anothergame = new Thread(){
            @Override
            public void run() {
                anotherGame();
            }
        };

        Handler handler = new Handler(); // Create a time delay of 1 second to make it realistic
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setTextBoard(); // Convert the current board state in text format
                ResultMM bestMove = minimax(textBoard, level, 0, 0); // (textBoard, level, recurse, depth)
                applyTextBoard(bestMove.matrix); // Apply the text board to the actual view
                board.check();
                if (board.playerWon && !board.draw) {
                    board.text = "Player Won!";
                    System.out.println(board.text);
                    //endGame();
                    handler.postDelayed(anothergame, 1000);
                    board.playerOneScoreCount++;
                    updatePlayerScore();
                }else if (board.computerWon && !board.draw) {
                    board.text = "Computer Won!";
                    //endGame();
                    handler.postDelayed(anothergame, 1000);
                    System.out.println(board.text);
                    board.playerTwoScoreCount++;
                    updatePlayerScore();
                }else if (board.draw) {
                    board.text = "It's a draw!";
                    System.out.println(board.text);
                    //endGame();
                    handler.postDelayed(anothergame, 1000);
                }else
                    board.changeTurn();
            }
        }, 1000);
    }

    private ResultMM getResult(ArrayList<ResultMM> listScore, String level) {
        ResultMM result = listScore.get(0);

        if(level.equals("max")) {
            for(int i = 1; i < listScore.size(); i++) {
                if((listScore.get(i).getScore() > result.getScore()) ||
                        (listScore.get(i).getScore() == result.getScore()
                                && listScore.get(i).depth < result.depth))
                    result = listScore.get(i);
            }
        } else {
            for(int i = 1; i < listScore.size(); i++) {
                if((listScore.get(i).getScore() < result.getScore()) ||
                        (listScore.get(i).getScore() == result.getScore()
                                && listScore.get(i).depth < result.depth))
                    result = listScore.get(i);
            }
        }

        return result;
    }

    private ResultMM minimax(String[] textBoard, String level, int recurse, int depth) {
        ArrayList<String[]> children = generateSuccessor(textBoard, level); // Get list of all possible moves

        if(children == null || gameOver(textBoard)) {
            return new ResultMM(textBoard, getScore(textBoard), depth); // if no children or game over
        } else {
            ArrayList<ResultMM> listScore = new ArrayList<>(); // Get a list of scores for all available children
            for(int i = 0; i < children.size(); i++) {
                listScore.add(minimax(children.get(i), invertLevel(level), 1, depth + 1)); // recursive call
                // with recurse = 1 and depth + 1
            }

            ResultMM res = getResult(listScore, level); // Get the child
            // with maximum (for max condition) or minimum (for min condition) score
            if(recurse == 1)
                res.setMatrix(textBoard); // If this is a recursive call, set the result
            return res;
        }
    }



    private ArrayList<String[]> generateSuccessor(String[] textBoard, String level) {
        ArrayList<String[]> successor = new ArrayList<>();

        for(int i = 0; i < textBoard.length; i++) {
            if(textBoard[i].equals("")) {
                String[] child = new String[9];
                System.arraycopy(textBoard, 0, child, 0, 9); // First match the child with the parent board state

                if(level.equals("max"))
                    child[i] = max;
                else if(level.equals("min"))
                    child[i] = min;

                successor.add(child);
            }
        }

        return (successor.size() == 0) ? null : successor;
    }

    private String invertLevel(String level) {
        return (level.equals("max")) ? "min" : "max"; // If the level is max, set to min and vice-versa
    }

    private boolean gameOver(String[] textBoard) {
        return (getScore(textBoard) != 0); // Is the game over (is the score not 0)
    }

    // Return +1 when the computer wins, -1 when the computer loses and 0 otherwise
    private int getScore(String[] textBoard) {
        if(max.equals("O")) {
            if((textBoard[0].equals("X") && textBoard[1].equals("X") && textBoard[2].equals("X")) ||
                    (textBoard[3].equals("X") && textBoard[4].equals("X") && textBoard[5].equals("X")) ||
                    (textBoard[6].equals("X") && textBoard[7].equals("X") && textBoard[8].equals("X")) ||
                    (textBoard[0].equals("X") && textBoard[3].equals("X") && textBoard[6].equals("X")) ||
                    (textBoard[1].equals("X") && textBoard[4].equals("X") && textBoard[7].equals("X")) ||
                    (textBoard[2].equals("X") && textBoard[5].equals("X") && textBoard[8].equals("X")) ||
                    (textBoard[0].equals("X") && textBoard[4].equals("X") && textBoard[8].equals("X")) ||
                    (textBoard[2].equals("X") && textBoard[4].equals("X") && textBoard[6].equals("X")))
                return -1;
            else if((textBoard[0].equals("O") && textBoard[1].equals("O") && textBoard[2].equals("O")) ||
                    (textBoard[3].equals("O") && textBoard[4].equals("O") && textBoard[5].equals("O")) ||
                    (textBoard[6].equals("O") && textBoard[7].equals("O") && textBoard[8].equals("O")) ||
                    (textBoard[0].equals("O") && textBoard[3].equals("O") && textBoard[6].equals("O")) ||
                    (textBoard[1].equals("O") && textBoard[4].equals("O") && textBoard[7].equals("O")) ||
                    (textBoard[2].equals("O") && textBoard[5].equals("O") && textBoard[8].equals("O")) ||
                    (textBoard[0].equals("O") && textBoard[4].equals("O") && textBoard[8].equals("O")) ||
                    (textBoard[2].equals("O") && textBoard[4].equals("O") && textBoard[6].equals("O")))
                return 1;
        } else if(max.equals("X")) {
            if((textBoard[0].equals("X") && textBoard[1].equals("X") && textBoard[2].equals("X")) ||
                    (textBoard[3].equals("X") && textBoard[4].equals("X") && textBoard[5].equals("X")) ||
                    (textBoard[6].equals("X") && textBoard[7].equals("X") && textBoard[8].equals("X")) ||
                    (textBoard[0].equals("X") && textBoard[3].equals("X") && textBoard[6].equals("X")) ||
                    (textBoard[1].equals("X") && textBoard[4].equals("X") && textBoard[7].equals("X")) ||
                    (textBoard[2].equals("X") && textBoard[5].equals("X") && textBoard[8].equals("X")) ||
                    (textBoard[0].equals("X") && textBoard[4].equals("X") && textBoard[8].equals("X")) ||
                    (textBoard[2].equals("X") && textBoard[4].equals("X") && textBoard[6].equals("X")))
                return 1;
            else if((textBoard[0].equals("O") && textBoard[1].equals("O") && textBoard[2].equals("O")) ||
                    (textBoard[3].equals("O") && textBoard[4].equals("O") && textBoard[5].equals("O")) ||
                    (textBoard[6].equals("O") && textBoard[7].equals("O") && textBoard[8].equals("O")) ||
                    (textBoard[0].equals("O") && textBoard[3].equals("O") && textBoard[6].equals("O")) ||
                    (textBoard[1].equals("O") && textBoard[4].equals("O") && textBoard[7].equals("O")) ||
                    (textBoard[2].equals("O") && textBoard[5].equals("O") && textBoard[8].equals("O")) ||
                    (textBoard[0].equals("O") && textBoard[4].equals("O") && textBoard[8].equals("O")) ||
                    (textBoard[2].equals("O") && textBoard[4].equals("O") && textBoard[6].equals("O")))
                return -1;
        }

        return 0;
    }

    private void setTextBoard() {
        textBoard[0] = giveDescription(board.casesState[0]);
        textBoard[1] = giveDescription(board.casesState[1]);
        textBoard[2] = giveDescription(board.casesState[2]);
        textBoard[3] = giveDescription(board.casesState[3]);
        textBoard[4] = giveDescription(board.casesState[4]);
        textBoard[5] = giveDescription(board.casesState[5]);
        textBoard[6] = giveDescription(board.casesState[6]);
        textBoard[7] = giveDescription(board.casesState[7]);
        textBoard[8] = giveDescription(board.casesState[8]);
    }

    //function takes the state of a case ( 0 means empty and 1 means occupied by player and 2 means occupied by computer)
    private String giveDescription(int caseState){
        String description = "";
        if(caseState == 0){
            description = "";
        }
        if(caseState == 1){
            description = "X";
        }
        if(caseState == 2){
            description  = "O";
        }
        return description;
    }


    private void applyTextBoard(String[] textBoard) {

        for(int i = 0; i<textBoard.length; i++){
            if(textBoard[i]=="X"){
                board.cases[i].setBackgroundResource(R.drawable.cross);
                board.casesState[i]=1;
            }
            else if(textBoard[i]=="O"){
                board.cases[i].setBackgroundResource(R.drawable.circle);
                board.casesState[i]=2;
            }
            else{

                board.casesState[i]=0;
            }
        }

    }
    public void anotherGame(){
        board.text="Player's turn";
        System.out.println(board.text);
        board.playerTurn = true;
        board.playerWon = false;
        board.computerWon = false;
        board. draw = false;
        board.casesState[0] = 0;
        board.casesState[1] = 0;
        board.casesState[2] = 0;
        board.casesState[3] = 0;
        board.casesState[4] = 0;
        board.casesState[5] = 0;
        board.casesState[6] = 0;
        board.casesState[7] = 0;
        board.casesState[8] = 0;
        for(int i = 0; i<9; i++){
            board.cases[i].setBackground(null);
            board.cases[i].setBackgroundColor(Color.rgb(255,235,59));}

    }
    public void updatePlayerScore(){
        String playerOneScoreText = (String) board.playerOneScore.getText();
        board.playerOneScore.setText(playerOneScoreText.substring(0, playerOneScoreText.length()-1) + board.playerOneScoreCount);
        String playerTwoScoreText = (String) board.playerTwoScore.getText();
        board.playerTwoScore.setText(playerTwoScoreText.substring(0, playerTwoScoreText.length()-1) + board.playerTwoScoreCount);

    }
    /*public void endGame(){
        if(board.text =="Player Won!")
            Toast.makeText(getApplicationContext(), "You Won!", Toast.LENGTH_SHORT).show();
        else if(board.text =="Computer Won!")
            Toast.makeText(getApplicationContext(), "Computer Won!", Toast.LENGTH_SHORT).show();
        else if(board.text == "It's a draw!")
            Toast.makeText(getApplicationContext(), "It's a Draw!", Toast.LENGTH_SHORT).show();

    }*/
}