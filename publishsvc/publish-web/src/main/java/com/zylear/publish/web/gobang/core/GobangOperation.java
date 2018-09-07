package com.zylear.publish.web.gobang.core;

/**
 * Created by xiezongyu on 2018/9/7.
 */
public class GobangOperation {

    public static final int FIFTEEN = 15;
    public static final int ROWS = 14;//棋盘行数
    public static final int COLS = 14;//棋盘列数

    public static boolean isInside(int x, int y) {

        if (x < 0 || x >= FIFTEEN || y < 0 || y >= FIFTEEN) {
            return false;
        }

        return true;
    }

    public static boolean isLessFive(int[][] tryChess, int xIndex, int yIndex, int xDirect, int yDirect, int c) {

        boolean lessFive;
        int init = 4;
        int x;
        int y;
        int color = -c;

        for (x = xIndex + xDirect, y = yIndex + yDirect; x >= 0 && x <= COLS && y >= 0 && y <= COLS; x = x + xDirect, y = y + yDirect) {
            if (tryChess[x][y] != color)
                init--;
            if (tryChess[x][y] == color)
                break;
            if (init <= 0)
                break;
        }

        for (x = xIndex - xDirect, y = yIndex - yDirect; x >= 0 && x <= COLS && y >= 0 && y <= COLS; x = x - xDirect, y = y - yDirect) {
            if (tryChess[x][y] != color)
                init--;
            if (tryChess[x][y] == color)
                break;
            if (init <= 0)
                break;
        }


        if (init > 0)
            lessFive = true;
        else
            lessFive = false;


        return lessFive;
    }
}
