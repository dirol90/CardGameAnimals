package com.tsimbalyukstudio.cardgameanimals.logic;

import java.util.ArrayList;

public class GameLogic {
    public static boolean isFirstClick = false;
    public static ArrayList<int[]> userChoises  = new ArrayList<>();
    private int[] choise;

    public GameLogic (int pos, int group) {

        choise = new int[]{pos, group};

        if (userChoises.isEmpty()){
            userChoises.add(0, choise);
        }
        else {
            userChoises.add(1, choise);
        }
    }

    public boolean compare(){
        try {
            if (userChoises.get(0)[0] != userChoises.get(1)[0] && userChoises.get(0)[1] == userChoises.get(1)[1]){
                userChoises.clear();
                    return true;
            } else {
                userChoises.clear();
            return false;
            }
        } catch (Exception e){
            return false;
        }
    }

}
