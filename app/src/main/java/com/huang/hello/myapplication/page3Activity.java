package com.huang.hello.myapplication;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class page3Activity extends AppCompatActivity implements View.OnClickListener{

    private boolean player1turn=true;

    private Button[][] buttons=new Button[4][4];

    private int player1points;
    private int player2points;

    private TextView textViewplayer1;
    private TextView textViewplayer2;
    Typeface tf1;

    private int roundcount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);

        textViewplayer1=findViewById(R.id.Textview_1);
        textViewplayer2=findViewById(R.id.Textview_2);
        tf1=Typeface.createFromAsset(getAssets(),"fonts/setofont.ttf");
        textViewplayer1.setTypeface(tf1);
        textViewplayer2.setTypeface(tf1);


        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                String buttonID="button_"+i+j;
                int resID=getResources().getIdentifier(buttonID,"id",getPackageName());
                buttons[i][j]=findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button button_reset=findViewById(R.id.button_Reset);
        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if(player1turn){
            ((Button) v).setText("X");
        }
        else {
            ((Button) v).setText("O");
        }
        roundcount++;

        if(checkforwin()){
            if(player1turn){
                playerwins();
            }
            else{
                player2wins();
            }
        }else if(roundcount==16){
            draw();
        }else{
            player1turn=!player1turn;
        }
    }
    private boolean checkforwin(){
        String[][]field=new String[4][4];

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 4; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2])&&field[i][0].equals(field[i][3]) && !field[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 4; i++) {
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i])&&field[0][i].equals(field[3][i]) && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2])&&field[0][0].equals(field[3][3]) && !field[0][0].equals("")) {
            return true;

        }

        if (field[3][0].equals(field[2][1]) && field[3][0].equals(field[1][2]) && field[3][0].equals(field[0][3])&& !field[3][0].equals("")) {
            return true;
        }
        return false;
    }
    private void playerwins(){
        player1points++;
        Toast.makeText(this,"玩家1獲勝!!!",Toast.LENGTH_SHORT).show();
        updatePoints();
        resetBoard();
    }
    private void player2wins(){
        player2points++;
        Toast.makeText(this,"玩家2獲勝!!!",Toast.LENGTH_SHORT).show();
        updatePoints();
        resetBoard();
    }
    private void draw(){
        player1points++;
        Toast.makeText(this,"平手!",Toast.LENGTH_SHORT).show();
        resetBoard();
    }
    private void updatePoints(){
        textViewplayer1.setText("玩家1: "+player1points);
        textViewplayer2.setText("玩家2: "+player2points);


    }
    private void resetBoard(){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                buttons[i][j].setText("");
            }
        }
        roundcount=0;
        player1turn=true;

    }
    private void resetGame(){
        player1points=0;
        player2points=0;
        updatePoints();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCound",roundcount);
        outState.putInt("player1points",player1points);
        outState.putInt("player2points",player2points);
        outState.putBoolean("player1turn",player1turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundcount=savedInstanceState.getInt("roundcount");
        player1points=savedInstanceState.getInt("player1points");
        player2points=savedInstanceState.getInt("player2points");
        player1turn=savedInstanceState.getBoolean("playerturn");
    }


}
