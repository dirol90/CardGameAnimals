package com.tsimbalyukstudio.cardgameanimals.ui.Card;

public class CardObj implements Cloneable {

    public int resId;
    public int[] positions = new int[2];

    public CardObj(int position, int groupNum, int resId) {
        positions[0] = position;
        positions[1] = groupNum;
        this.resId = resId;
    }

}
