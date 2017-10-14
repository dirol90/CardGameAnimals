package com.tsimbalyukstudio.cardgameanimals.ui.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.tsimbalyukstudio.cardgameanimals.USER;
import com.tsimbalyukstudio.cardgameanimals.helper.*;

import com.tsimbalyukstudio.cardgameanimals.R;

public class EasyActivity extends AppCompatActivity{

    static int score;
    private GridView gv;
    private GridAdapter ga;
    static TextView tv;
    private TextView tvLvl;

    private int countCards = 6;
    private int countColumn = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initialiseGrid();

        score = 0;

    }


    private void initialiseGrid() {
        gv = findViewById(R.id.grid_card_view);
        ga = new GridAdapter(this, countCards, countColumn);
        adjustGridView();
        gv.setAdapter(ga);

        tv = findViewById(R.id.text_score_game);
        tv.setText("SCORE: 0");

        tvLvl = findViewById(R.id.text_level_game);
        tv.setText("LEVEL: "+USER.LEVEL);
    }

    private void adjustGridView() {
        gv.setNumColumns(2);
    }

    public static void changeText() {
        score += 2;
        tv.setText("SCORE: " + score);
        if (score == 6 ){
            USER.TOTAL_SCORE += score;
            USER.LEVEL++;
        }
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
