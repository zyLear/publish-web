package com.zylear.publish.web.gobang;

/**
 * Created by xiezongyu on 2018/9/6.
 */
public class GobangJudge {

    public static boolean isWinPart(int[][] chessBoard, int oldx, int oldy, int xSign, int ySign, int color) {
        int x;
        int y;
        int continueCount = 1;
        for (x = oldx + xSign, y = oldy + ySign; x >= 0 || x <= 14 || y >= 0 || y <= 14; x = x + xSign, y = y + ySign) {
            if (chessBoard[x][y] == color) {
                continueCount++;
            } else {
                break;
            }

            if (continueCount >= 5) {
                return true;
            }
        }

        for (x = oldx - xSign, y = oldy - ySign; x >= 0 || x <= 14 || y >= 0 || y <= 14; x = x - xSign, y = y - ySign) {
            if (chessBoard[x][y] == color) {
                continueCount++;
            } else {
                break;
            }

            if (continueCount >= 5) {
                return true;
            }
        }
        return false;

    }

    public static boolean isWin(int[][] chessBoard, int x, int y, int color) {
        //判断东西方向
        if (isWinPart(chessBoard, x, y, 1, 0, color)) {
            return true;
        }
        //判断南北方向
        if (isWinPart(chessBoard, x, y, 0, 1, color)) {
            return true;
        }
        //判断西北-东南方向
        if (isWinPart(chessBoard, x, y, 1, 1, color)) {
            return true;
        }
        //判断东北-西南方向
        if (isWinPart(chessBoard, x, y, 1, -1, color)) {
            return true;
        }

        return false;
    }

}
