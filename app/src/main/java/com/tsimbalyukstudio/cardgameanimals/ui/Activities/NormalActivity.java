package com.tsimbalyukstudio.cardgameanimals.ui.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.appodeal.ads.Appodeal;
import com.tsimbalyukstudio.cardgameanimals.R;
import com.tsimbalyukstudio.cardgameanimals.USER;
import com.tsimbalyukstudio.cardgameanimals.helper.GridAdapter;
import com.tsimbalyukstudio.cardgameanimals.logic.GameLogic;

import java.util.ArrayList;

public class NormalActivity extends AppCompatActivity {

    static int score = 0;
    private GridView gv;
    private GridAdapter ga;
    static TextView tv;
    private TextView tvLvl;
    int countCards = 12;
    int countColumn = 3;
    private static Context mContext;
    private static LayoutInflater inflater;
    public static Activity self;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initialiseGrid();

        score = 0;
        mContext =  NormalActivity.this;
        inflater = this.getLayoutInflater();
        self = this;
        GameLogic.isFirstClick = false;
        GameLogic.userChoises  = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Appodeal.show(NormalActivity.this, Appodeal.BANNER);
            }
        }).start();
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
        gv.setNumColumns(countColumn);
    }

    public static void changeText() {
        score += 5*USER.LEVEL;
        tv.setText("СЧЕТ: " + score);
        if (score == 30*USER.LEVEL){
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
                        Intent intent = new Intent(mContext, NormalActivity.class);
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
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}