package com.zylear.publish.web.gobang;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NullPoint {

    public int x;
    public int y;

    public int count = 0;
    public int sheng = 0;
    public int score = 0;


    public NullPoint(int x, int y, int s) {
        this.x = x;
        this.y = y;
        score = s;

    }

    public NullPoint() {

    }

}

