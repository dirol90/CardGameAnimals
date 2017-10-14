package com.tsimbalyukstudio.cardgameanimals.helper;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tsimbalyukstudio.cardgameanimals.COVERS;
import com.tsimbalyukstudio.cardgameanimals.IMAGES;
import com.tsimbalyukstudio.cardgameanimals.R;
import com.tsimbalyukstudio.cardgameanimals.logic.GameLogic;
import com.tsimbalyukstudio.cardgameanimals.ui.Activities.EasyActivity;
import com.tsimbalyukstudio.cardgameanimals.ui.Activities.HardActivity;
import com.tsimbalyukstudio.cardgameanimals.ui.Activities.NormalActivity;
import com.tsimbalyukstudio.cardgameanimals.ui.Card.CardObj;

import java.util.ArrayList;

import static com.tsimbalyukstudio.cardgameanimals.logic.GameLogic.isFirstClick;

public class GridAdapter extends BaseAdapter implements Animation.AnimationListener {

    private Context mContext;

    private ImageView imageView;

    private View gridView;

    public ArrayList<CardObj> cardList = new ArrayList<>();
    public ArrayList<View> chosedView = new ArrayList<>();

    private int countCards;
    private int countColumn;
    private int groupNum = 0;

    private int width;
    private int height;

    private int randomInt = (int) (Math.random() * COVERS.values().length);

    private Animation animation1;
    private Animation animation2;

    ImageView iv;

    public GridAdapter(Context c, int countCards, int countColumn) {
        mContext = c;
        this.countCards = countCards;
        this.countColumn = countColumn;


        animation1 = AnimationUtils.loadAnimation(mContext, R.anim.to_middle);
        animation1.setAnimationListener(this);

        createGrid();

    }

    public int getCount() {
        return countCards;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, final View convertView, final ViewGroup parent) {

        ScreenParam sp = new ScreenParam(mContext);

        width = ((sp.getScreenParam()[1] / 3 * 2) / (countCards / countColumn));
        height = ((sp.getScreenParam()[1] / 3 * 2) / (countCards / countColumn) + 25);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridView = inflater.inflate(R.layout.img_card, null);

            imageView = gridView.findViewById(R.id.card_img);

            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width, height);
            imageView.setLayoutParams(parms);


            final int finalPosition = position;

            gridView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {

                    iv = view.findViewById(R.id.card_img);

                    final GameLogic gl = new GameLogic(cardList.get(finalPosition).positions[0], cardList.get(finalPosition).positions[1]);

                    iv.setBackgroundResource(cardList.get(finalPosition).resId);

                    if (gl.compare() && isFirstClick) {

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                iv = view.findViewById(R.id.card_img);
                                iv.setVisibility(View.INVISIBLE);
                                view.setEnabled(false);
                                chosedView.get(0).setVisibility(View.INVISIBLE);
                                chosedView.clear();

                                if (countCards == 6) {
                                    EasyActivity.changeText();
                                } else if (countCards == 12) {
                                    NormalActivity.changeText();
                                } else if (countCards == 20) {
                                    HardActivity.changeText();
                                }

                            }
                        }, 500);
                        isFirstClick = false;
                    } else if (isFirstClick) {

                        iv.clearAnimation();
                        iv.setAnimation(animation1);
                        iv.startAnimation(animation1);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                iv = view.findViewById(R.id.card_img);

                                iv.setBackgroundResource(COVERS.values()[randomInt].resId);
                                try {
                                    chosedView.get(0).setBackgroundResource(COVERS.values()[randomInt].resId);
                                } catch (Exception e) {
                                }
                                chosedView.clear();

                            }
                        }, 500);
                        isFirstClick = false;
                    } else {
                        if (iv.getVisibility() == View.VISIBLE) {

                            iv.clearAnimation();
                            iv.setAnimation(animation1);
                            iv.startAnimation(animation1);

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    iv = view.findViewById(R.id.card_img);
                                    iv.setBackgroundResource(cardList.get(finalPosition).resId);
                                    chosedView.add(iv);

                                }
                            }, 500);
                            isFirstClick = true;
                        }
                    }
                }
            });

            imageView.setBackgroundResource(COVERS.values()[randomInt].resId);

        } else {
            gridView = convertView;
        }
        return gridView;
    }

    public void createGrid() {
        for (int x = 0; x < countCards; x++) {
            cardList.add(x, new CardObj(-1, -1, -1));
        }

        int[] tempRes = new int[countCards / 2];
        int[] tempPos = new int[countCards];

        int posCounter = 0;
        int resCounter = 0;

        for (int x = 0; x < (countCards / 2); x++) {

            int resId = IMAGES.values()[(int) (Math.random() * IMAGES.values().length)].resId;

            if (x != 0) {
                for (int i = 0; i < tempRes.length; i++) {
                    if (tempRes[i] == resId) {
                        resId = IMAGES.values()[(int) (Math.random() * IMAGES.values().length)].resId;
                        i = 0;
                    }
                }
            }

            tempRes[resCounter++] = resId;

            int position = (int) (Math.random() * countCards);
            boolean t = true;

            do {
                if (cardList.get(position).positions[0] != -1) {
                    position = (int) (Math.random() * countCards);
                } else {
                    tempPos[posCounter++] = position;

                    cardList.set(position, new CardObj(position, groupNum, resId));
                    t = false;
                }
            } while (t);

            position = (int) (Math.random() * countCards);
            t = true;

            do {
                if (cardList.get(position).positions[0] != -1) {
                    position = (int) (Math.random() * countCards);
                } else {
                    tempPos[posCounter++] = position;

                    cardList.set(position, new CardObj(position, groupNum++, resId));
                    t = false;
                }
            } while (t);
        }
    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}