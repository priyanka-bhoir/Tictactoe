package com.verl.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int activeplayer=0;//0 at yellow
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int [][] winningposition={{0,1,2},{3,4,5},{7,8,6},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};
    int flag=0,c=0;
private RelativeLayout gridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayout = findViewById(R.id.ll);
    }
    public void Dropin(View view) {
        ImageView counter = (ImageView) view;
        System.out.println(counter.getTag().toString());
        int tagcounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tagcounter] == 2) {
            flag++;
            gameState[tagcounter]=activeplayer;
            counter.setTranslationY(-1000f);
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activeplayer = 0;
            }
        }
            counter.animate().translationY(70f).rotation(360).setDuration(300);
        //LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);
            for(int [] i :winningposition) {
                String w = "RED PLAYER WON";
                if (gameState[i[0]] == gameState[i[1]] && gameState[i[1]] == gameState[i[2]] && gameState[i[2]] != 2) {
                    c++;
                    if (gameState[i[0]] == 0) {
                        w = "YELLOW PLAYER WON";
                    }

                    GridLayout grid = findViewById(R.id.gridLayout);
                    grid.setClickable(false);
                    //grid.setVisibility(view.GONE);
                  //  GridLayout grid = (LinearLayout) findViewById(R.id.gridLayout);
                    for (int j = 0; j < grid.getChildCount(); j++) {
                        View child = grid.getChildAt(j);
                        child.setEnabled(false);
                    }
                    //gridLayout.setEnabled(true);
                        open(w);
//                    TextView m = (TextView) findViewById(R.id.winnerMessage);
//                    m.setText(w + " has won");
//                    layout.setVisibility(View.VISIBLE);
                }
            }
        if(flag==9)
        {
            if(c==0) {
                // String w = "NOBODY";
//            TextView m = (TextView) findViewById(R.id.winnerMessage);
//            m.setText("Game Over");
                GridLayout grid = findViewById(R.id.gridLayout);
                for (int j = 0; j < grid.getChildCount(); j++) {
                    View child = grid.getChildAt(j);
                    child.setEnabled(false);
                }
                //grid.setVisibility(view.GONE);
//            layout.setVisibility(View.VISIBLE);
                String m = "Game Over";
                open(m);
            }
        }



    }

    public void open(String w){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(w).setCancelable(false);

                alertDialogBuilder.setPositiveButton("Play Again",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(MainActivity.this,"Play Again",Toast.LENGTH_LONG).show();
                                flag=0;
                                c=0;
                                for(int i=0;i<9;i++)
                                {
                                    gameState[i]=2;
                                }
                                GridLayout grid =(GridLayout)findViewById(R.id.gridLayout);
                                for(int i=0;i<grid.getChildCount();i++)
                                {
                                    ((ImageView)grid.getChildAt(i)).setImageResource(0);
                                    View child = grid.getChildAt(i);
                                    child.setEnabled(true);
                                }
                            }
                        });

        alertDialogBuilder.setNegativeButton("Exit",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
//    public void playAgain(View view) {
//        LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);
//        layout.setVisibility(view.INVISIBLE);
//        flag=0;
//        for(int i=0;i<9;i++)
//        {
//            gameState[i]=2;
//        }
//        GridLayout grid =(GridLayout)findViewById(R.id.gridLayout);
//        for(int i=0;i<grid.getChildCount();i++)
//        {
//            ((ImageView)grid.getChildAt(i)).setImageResource(0);
//            View child = grid.getChildAt(i);
//            child.setEnabled(true);
//        }
//       // grid.setVisibility(view.VISIBLE);
//
//
//    }
}