package com.tsimbalyukstudio.cardgameanimals.ui.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.tsimbalyukstudio.cardgameanimals.R;
import com.tsimbalyukstudio.cardgameanimals.USER;
import com.tsimbalyukstudio.cardgameanimals.helper.GridAdapter;

public class HardActivity extends AppCompatActivity {

    static int score = 0;
    private GridView gv;
    private GridAdapter ga;
    static TextView tv;
    private TextView tvLvl;
    int countCards = 20;
    int countColumn = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initialiseGrid();
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
        gv.setNumColumns(countColumn);
    }

    public static void changeText() {
        score += 10;
        tv.setText("SCORE: " + score);
        if (score == 100 ){
            USER.TOTAL_SCORE += score;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
