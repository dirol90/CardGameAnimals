package com.tsimbalyukstudio.cardgameanimals.helper;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;


public class ScreenParam {

    Context context;

    public ScreenParam(Context context) {
        this.context = context;
    }

    public int[] getScreenParam (){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        return new int[]{width, height};
    }

}
