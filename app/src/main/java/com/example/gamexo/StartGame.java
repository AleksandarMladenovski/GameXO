package com.example.gamexo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StartGame extends AppCompatActivity {
    int nextToClick=1;
    int [][]matrica=new int[3][3];
    int player1PointCount=0;
    int player2PointCount=0;
    int totalClicks=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startgame);
        Log.d("TAG", "onCreate: y");
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
               matrica[i][j]=5;
            }
        }
        player1PointCount=0;
        player2PointCount=0;
    }
    public void btnClicked(View view){
        Button touched = (Button) view;
        if (touched.getText().toString().equals("")) {
            if(nextToClick==1)
            ((Button) view).setText("X");
            else{
                ((Button) view).setText("O");
            }
        }
        else{
            return;
        }
        totalClicks++;
        int x = 5;
            int y = 5;
            switch (view.getId()) {
                case R.id.buttonNum0:
                    x = 0;
                    y = 0;
                    break;
                case R.id.buttonNum1:
                    x = 0;
                    y = 1;
                    break;
                case R.id.buttonNum2:
                    x = 0;
                    y = 2;
                    break;
                case R.id.buttonNum3:
                    x = 1;
                    y = 0;
                    break;
                case R.id.buttonNum4:
                    x = 1;
                    y = 1;
                    break;
                case R.id.buttonNum5:
                    x = 1;
                    y = 2;
                    break;
                case R.id.buttonNum6:
                    x = 2;
                    y = 0;
                    break;
                case R.id.buttonNum7:
                    x = 2;
                    y = 1;
                    break;
                case R.id.buttonNum8:
                    x = 2;
                    y = 2;
                    break;
            }
                if (nextToClick == 1) {
                    matrica[x][y] = 1;
                    nextToClick = 0;
                } else {
                    matrica[x][y] = 0;
                    nextToClick = 1;
                }
                findWinner();
    }
    public void findWinner() {
        int counterOO = 0;
        int counterNN = 0;
        for (int i = 0; i < 3; i++) {
            int counterRed = 0;
            int counterKol = 0;

            for (int j = 0; j < 3; j++) {

                if (i == j) {
                    if (matrica[i][j] == 1) {
                        counterOO += 1;
                    } else if (matrica[i][j] == 0) {
                        counterOO += 10;
                    }

                }

                if (j == 3 - i - 1) {
                    if (matrica[i][j] == 1) {
                        counterNN += 1;
                    } else if (matrica[i][j] == 0) {
                        counterNN += 10;
                    }
                }

                if (matrica[i][j] == 1) {
                    counterRed += 1;
                } else if (matrica[i][j] == 0) {
                    counterRed += 10;
                }
                if (matrica[j][i] == 1) {
                    counterKol += 1;
                } else if (matrica[j][i] == 0) {
                    counterKol += 10;
                }
            }
            if (counterKol == 30 || counterRed == 30) {
                announceWinner(0);
            } else if (counterKol == 3 || counterRed == 3) {
                announceWinner(1);
            }

        }
        if (counterNN == 3 || counterOO == 3) {
            announceWinner(1);
        } else if (counterNN == 30 || counterOO == 30) {
            announceWinner(0);
        } else if (totalClicks == 9) {
            announceWinner(2);
        }
    }
    public void announceWinner(int winner){
        if(winner==1){
            TextView view=findViewById(R.id.textViewPlayer1Score);
            player1PointCount++;
            view.setText(Integer.toString(player1PointCount));
            view=findViewById(R.id.textViewPlayer1);
            Toast.makeText(this,view.getText()+"  WINNER",Toast.LENGTH_LONG).show();
        }
        else if(winner==0){
            TextView view=findViewById(R.id.textViewPlayer2Score);
            player2PointCount++;
            view.setText(Integer.toString(player2PointCount));
            view=findViewById(R.id.textViewPlayer2);
            Toast.makeText(this,view.getText()+"  WINNER",Toast.LENGTH_LONG).show();
        }
        else{

            Toast.makeText(this,"DRAW",Toast.LENGTH_LONG).show();
        }
        init();
    }

    public void initButtons(){
        View v=findViewById(R.id.buttonNum0);
        ((Button)v).setText("");
        v=findViewById(R.id.buttonNum1);
        ((Button)v).setText("");
        v=findViewById(R.id.buttonNum2);
        ((Button)v).setText("");
        v=findViewById(R.id.buttonNum3);
        ((Button)v).setText("");
        v=findViewById(R.id.buttonNum4);
        ((Button)v).setText("");
        v=findViewById(R.id.buttonNum5);
        ((Button)v).setText("");
        v=findViewById(R.id.buttonNum6);
        ((Button)v).setText("");
        v=findViewById(R.id.buttonNum7);
        ((Button)v).setText("");
        v=findViewById(R.id.buttonNum8);
        ((Button)v).setText("");
    }
    public void init(){
        initButtons();
        totalClicks=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                matrica[i][j]=5;
            }
        }
    }
    public void ButtonReset(View view){
        player2PointCount=0;
        player1PointCount=0;
        TextView v=findViewById(R.id.textViewPlayer1Score);
        v.setText("0");
        v=findViewById(R.id.textViewPlayer2Score);
        v.setText("0");
        init();
    }
}
