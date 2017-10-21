package com.tsimbalyukstudio.cardgameanimals.ui.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.tsimbalyukstudio.cardgameanimals.USER;
import com.tsimbalyukstudio.cardgameanimals.helper.*;

import com.tsimbalyukstudio.cardgameanimals.R;
import com.tsimbalyukstudio.cardgameanimals.logic.GameLogic;

import java.util.ArrayList;

public class EasyActivity extends AppCompatActivity{

    static int score;
    private GridView gv;
    private GridAdapter ga;
    static TextView tv;
    private TextView tvLvl;
    private static Context mContext;
    private static LayoutInflater inflater;
    public static Activity self;

    private int countCards = 6;
    private int countColumn = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initialiseGrid();

        score = 0;

        mContext = EasyActivity.this;
        inflater = this.getLayoutInflater();
        self = this;
        GameLogic.isFirstClick = false;
        GameLogic.userChoises  = new ArrayList<>();

        Appodeal.show(this, Appodeal.BANNER);
    }


    private void initialiseGrid() {
        gv = findViewById(R.id.grid_card_view);
        ga = new GridAdapter(this, countCards, countColumn);
        adjustGridView();
        gv.setAdapter(ga);

        tv = findViewById(R.id.text_score_game);
        tv.setText("СЧЕТ: 0");

        tvLvl = findViewById(R.id.text_level_game);
        tvLvl.setText("УРОВЕНЬ: "+USER.LEVEL);
    }

    private void adjustGridView() {
        gv.setNumColumns(2);
    }

    public static void changeText() {
        score += 2*USER.LEVEL;
        tv.setText("СЧЕТ: " + score);
        if (score == 6*USER.LEVEL ){
            USER.TOTAL_SCORE += score;
            USER.LEVEL++;

            if (USER.LEVEL % 4 == 0) {
                Appodeal.show(self, Appodeal.NON_SKIPPABLE_VIDEO);
            }

            {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
                View dialogView = inflater.inflate(R.layout.dialog_game_win, null);
                dialogBuilder.setView(dialogView);

                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.setCancelable(false);
                alertDialog.show();

                Button newBtn = alertDialog.findViewById(R.id.new_btn);
                newBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, EasyActivity.class);
                        mContext.startActivity(intent);
                        activityKiller(self);
                    }
                });

                Button backBtn = alertDialog.findViewById(R.id.back_btn);
                backBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, MainActivity.class);
                        mContext.startActivity(intent);
                        activityKiller(self);
                    }
                });
            }
        }
    }

    public static void activityKiller(Activity activity)
    {
        if(activity != null)
        {
            activity.finish();
        }
    }

    @Override
    public void onBackPressed() {
        USER.LEVEL = 1;
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        mContext = null;
        inflater = null;
        finish();
    }
}
