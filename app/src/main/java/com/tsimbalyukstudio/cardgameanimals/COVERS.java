package com.tsimbalyukstudio.cardgameanimals;

public enum COVERS {
    COVERS_IMG_1 (R.drawable.cover1),
    COVERS_IMG_2 (R.drawable.cover2),
    COVERS_IMG_3 (R.drawable.cover3),
    COVERS_IMG_4 (R.drawable.cover4),
    COVERS_IMG_5 (R.drawable.cover5),
    COVERS_IMG_6 (R.drawable.cover6);

    public int resId;
    COVERS(int resId) {
        this.resId = resId;
    }
}
