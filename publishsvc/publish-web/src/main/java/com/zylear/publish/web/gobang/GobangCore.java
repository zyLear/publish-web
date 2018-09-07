package com.zylear.publish.web.gobang;

import com.zylear.publish.web.gobang.Learn5.BestPoint;

/**
 * Created by xiezongyu on 2018/9/6.
 */
public class GobangCore {

    public static final int fifteen = 15;
    public static final int ROWS = 14;//棋盘行数
    public static final int COLS = 14;//棋盘列数


    public static int minMax(int[][] tryChess, int depth, int maxDepth, int alpha, int beta, int computerColor, BestPoint bestPoint) {

//        if (System.currentTimeMillis() - currentime > Deadline) return 1;
        depth--;
        if (depth == 0) {
            return getValueTwo(tryChess);
        }


        if (depth % 2 == 0) {

            Point[] p = new Point[225];
            for (int o = 0; o < 225; o++)
                p[o] = new Point();
            getNewDown(tryChess, p, computerColor);
            if (depth == maxDepth - 1) {
                System.out.println("shu:" + p[0].count);
            }
            for (int i = 0; i < p[0].count; i++) {


                tryChess[p[i].x][p[i].y] = computerColor;
                int t;
                if (p[0].sheng == 1) {

                    t = getValueTwo(tryChess);


                } else {
                    t = minMax(tryChess, depth, maxDepth, alpha, beta, computerColor, bestPoint);
//                    if (System.currentTimeMillis() - currentime > Deadline) return 1;
                }


                if (t >= beta) {

                    tryChess[p[i].x][p[i].y] = 0;
                    return t;
                }


                if (t > alpha) {
                    alpha = t;
                    if (depth == maxDepth - 1) {

//                        BestPoint pp = new BestPoint(p[i].x, p[i].y, alpha);
                        // bestPoints.add(pp);
                        bestPoint.x = p[i].x;
                        bestPoint.y = p[i].y;
//                        fx = p[i].x;
//                        fy = p[i].y;
                    }
                }
                tryChess[p[i].x][p[i].y] = 0;
            }


            return alpha;

        } else {
            int min = 100000000;

            Point[] p = new Point[225];
            for (int o = 0; o < 225; o++)
                p[o] = new Point();
            getNewDown(tryChess, p, 0 - computerColor);

            for (int i = 0; i < p[0].count; i++) {


                tryChess[p[i].x][p[i].y] = 0 - computerColor;
                int t;
                if (p[0].sheng == 1) {
                    t = getValueTwo(tryChess);


                } else {
                    t = minMax(tryChess, depth, maxDepth, alpha, beta, computerColor, bestPoint);
//                    if (System.currentTimeMillis() - currentime > Deadline) return 1;
                }

                if (t <= alpha) {
                    tryChess[p[i].x][p[i].y] = 0;

                    return t;
                }


                if (t < beta) {
                    beta = t;
                }


                tryChess[p[i].x][p[i].y] = 0;


            }

            return beta;
        }
    }


    public static int getValueTwo(int[][] tryChess) {
        int Ascore = 0;
        int Bscore = 0;
        int score = 0;


        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {

                if (tryChess[i][j] == 1)
                    Ascore = Ascore + getPointValueTwo(tryChess, i, j, 1);
                if (tryChess[i][j] == -1) Bscore = Bscore + getPointValueTwo(tryChess, i, j, -1);
            }
        }

        score = (int) (Ascore - Bscore);
        return score;
    }

    public static int getPointValueTwo(int[][] tryChess, int a, int b, int c) {

        int x;
        int y;
//        int init = 4;

        int continueCount = 1;//连续棋子的个数
        int Count = 0;
        int sign = 0;
        int score = 0;


        boolean lessFive = false;
        int xIndex = a;
        int yIndex = b;
        boolean bb = false;

//1
        int chance = 1;


        if (!isLessFive(tryChess, xIndex, yIndex, 1, 0, c)) {

            bb = false;

            for (x = xIndex - 1; x >= 0; x--) {
                if (tryChess[x][yIndex] == c)
                    continueCount++;
                else {
                    if (tryChess[x][yIndex] != 0)
                        sign = 1;

                    break;
                }
            }

            if (x < 0) sign = 1;


            for (x = xIndex + 1; x <= COLS; x++) {

                if (tryChess[x][yIndex] == c)
                    continueCount++;


                else {

                    if (chance == 1 && tryChess[x][yIndex] == 0) {
                        chance--;
                        Count = continueCount;
                        continue;
                    }


                    if (sign == 0 && tryChess[x][yIndex] == 0 && chance == 0 && Count != continueCount)
                        bb = true;


                    break;

                }

            }
            if (sign == 0 && chance == 0 && Count == continueCount)
                bb = true;

            if (sign == 1 && x > COLS) continueCount = 0;


            score += getPointScoreTwo(continueCount, bb);


//2


            //  if(false)

            sign = 0;
            chance = 1;
            continueCount = 1;
            bb = false;

            for (x = xIndex + 1; x <= COLS; x++) {

                if (tryChess[x][yIndex] == c)
                    continueCount++;


                else {
                    if (tryChess[x][yIndex] != 0)

                        sign = 1;

                    break;
                }
            }
            if (x > COLS) sign = 1;

            for (x = xIndex - 1; x >= 0; x--) {
                if (tryChess[x][yIndex] == c)
                    continueCount++;


                else {
                    if (chance == 1 && tryChess[x][yIndex] == 0) {
                        chance--;
                        Count = continueCount;
                        continue;
                    }


                    if (sign == 0 && tryChess[x][yIndex] == 0 && chance == 0 && Count != continueCount)
                        bb = true;


                    break;
                }
            }
            if (sign == 0 && chance == 0 && Count == continueCount)
                bb = true;

            if (sign == 1 && x < 0) continueCount = 0;

            score += getPointScoreTwo(continueCount, bb);

        }


        //3
        if (!isLessFive(tryChess, xIndex, yIndex, 0, 1, c)) {
            bb = false;

            sign = 0;
            chance = 1;
            continueCount = 1;
            for (y = yIndex - 1; y >= 0; y--) {

                if (tryChess[xIndex][y] == c)
                    continueCount++;
                else {
                    if (tryChess[xIndex][y] != 0)
                        sign = 1;
                    break;
                }
            }
            if (y < 0) sign = 1;


            for (y = yIndex + 1; y <= ROWS; y++) {

                if (tryChess[xIndex][y] == c)
                    continueCount++;


                else {
                    if (chance == 1 && tryChess[xIndex][y] == 0) {
                        chance--;
                        Count = continueCount;
                        continue;
                    }

                    if (sign == 0 && tryChess[xIndex][y] == 0 && chance == 0 && Count != continueCount)
                        bb = true;


                    break;
                }

            }
            if (sign == 0 && chance == 0 && Count == continueCount)
                bb = true;

            if (sign == 1 && y > ROWS) continueCount = 0;

            score += getPointScoreTwo(continueCount, bb);


//4

            bb = false;
            sign = 0;
            chance = 1;
            continueCount = 1;
            for (y = yIndex + 1; y <= ROWS; y++) {

                if (tryChess[xIndex][y] == c)
                    continueCount++;


                else {
                    if (tryChess[xIndex][y] != 0)

                        sign = 1;
                    break;
                }

            }
            if (y > ROWS) sign = 1;


            for (y = yIndex - 1; y >= 0; y--) {

                if (tryChess[xIndex][y] == c)
                    continueCount++;


                else {
                    if (chance == 1 && tryChess[xIndex][y] == 0) {
                        chance--;
                        Count = continueCount;
                        continue;
                    }


                    if (sign == 0 && tryChess[xIndex][y] == 0 && chance == 0 && Count != continueCount)
                        bb = true;

                    break;
                }
            }

            if (sign == 1 && y < 0) continueCount = 0;
            if (sign == 0 && chance == 0 && Count == continueCount)
                bb = true;

            score += getPointScoreTwo(continueCount, bb);
        }

        ////xxxxxxxxxx3


        //5
        if (!isLessFive(tryChess, xIndex, yIndex, 1, -1, c)) {

            bb = false;
            sign = 0;
            chance = 1;
            continueCount = 1;

            for (x = xIndex + 1, y = yIndex - 1; y >= 0 && x <= COLS; x++, y--) {

                if (tryChess[x][y] == c)
                    continueCount++;


                else {
                    if (tryChess[xIndex][y] != 0)

                        sign = 1;
                    break;
                }
            }
            if (x > ROWS || y < 0) sign = 1;

            //西南寻找
            for (x = xIndex - 1, y = yIndex + 1; x >= 0 && y <= ROWS; x--, y++) {

                if (tryChess[x][y] == c) {
                    continueCount++;
                } else {
                    if (chance == 1 && tryChess[x][y] == 0) {
                        chance--;
                        Count = continueCount;
                        continue;
                    }

                    if (sign == 0 && tryChess[x][y] == 0 && chance == 0 && Count != continueCount)
                        bb = true;

                    break;
                }
            }

            if (sign == 1 && (x < 0 || y > ROWS)) continueCount = 0;
            if (sign == 0 && chance == 0 && Count == continueCount)
                bb = true;

            score += getPointScoreTwo(continueCount, bb);


            //6


            bb = false;
            sign = 0;
            chance = 1;
            continueCount = 1;


            //西南寻找
            for (x = xIndex - 1, y = yIndex + 1; x >= 0 && y <= ROWS; x--, y++) {

                if (tryChess[x][y] == c)
                    continueCount++;


                else {
                    if (tryChess[x][y] != 0)

                        sign = 1;
                    break;
                }
            }
            if (x < 0 || y > ROWS) sign = 1;


            for (x = xIndex + 1, y = yIndex - 1; y >= 0 && x <= COLS; x++, y--) {

                if (tryChess[x][y] == c) {
                    continueCount++;

                } else {
                    if (chance == 1 && tryChess[x][y] == 0) {
                        chance--;
                        Count = continueCount;
                        continue;
                    }

                    if (sign == 0 && tryChess[x][y] == 0 && chance == 0 && Count != continueCount)
                        bb = true;

                    break;
                }


            }

            if (sign == 1 && (x > ROWS || y < 0)) continueCount = 0;
            if (sign == 0 && chance == 0 && Count == continueCount)
                bb = true;

            score += getPointScoreTwo(continueCount, bb);
        }


        ////////xxxxxxxxx4
//.7

        if (!isLessFive(tryChess, xIndex, yIndex, 1, 1, c)) {
            bb = false;
            sign = 0;
            chance = 1;
            continueCount = 1;

            for (x = xIndex - 1, y = yIndex - 1; x >= 0 && y >= 0; x--, y--) {

                if (tryChess[x][y] == c)
                    continueCount++;

                else {
                    if (tryChess[x][y] != 0)

                        sign = 1;
                    break;
                }
            }
            if (x < 0 || y < 0) sign = 1;


            for (x = xIndex + 1, y = yIndex + 1; x <= COLS && y <= ROWS; x++, y++) {

                if (tryChess[x][y] == c) {
                    continueCount++;

                } else {
                    if (chance == 1 && tryChess[x][y] == 0) {
                        chance--;
                        Count = continueCount;
                        continue;
                    }

                    if (sign == 0 && tryChess[x][y] == 0 && chance == 0 && Count != continueCount)
                        bb = true;

                    break;
                }
            }

            if (sign == 1 && (x > ROWS || y > ROWS)) continueCount = 0;
            if (sign == 0 && chance == 0 && Count == continueCount)
                bb = true;

            score += getPointScoreTwo(continueCount, bb);


            //8


            bb = false;
            chance = 1;
            continueCount = 1;
            sign = 0;

            for (x = xIndex + 1, y = yIndex + 1; x <= COLS && y <= ROWS; x++, y++) {

                if (tryChess[x][y] == c)
                    continueCount++;


                else {
                    if (tryChess[x][y] != 0)

                        sign = 1;
                    break;
                }
            }
            if (x > 14 || y > 14) sign = 1;


            for (x = xIndex - 1, y = yIndex - 1; x >= 0 && y >= 0; x--, y--) {

                if (tryChess[x][y] == c) {
                    continueCount++;
                } else {
                    if (chance == 1 && tryChess[x][y] == 0) {
                        chance--;
                        Count = continueCount;
                        continue;
                    }

                    if (sign == 0 && tryChess[x][y] == 0 && chance == 0 && Count != continueCount)
                        bb = true;

                    break;
                }
            }

            if (sign == 1 && (x < 0 || y < 0)) continueCount = 0;


            if (sign == 0 && chance == 0 && Count == continueCount)
                bb = true;

            score += getPointScoreTwo(continueCount, bb);

        }


        //1


        return score;


    }

    private static boolean isLessFive(int[][] tryChess, int xIndex, int yIndex, int xDirect, int yDirect, int c) {

        boolean lessfive = true;
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
            lessfive = true;
        else
            lessfive = false;


        return lessfive;
    }


    private static int getPointScoreTwo(int t, boolean bb) {
        switch (t) {
            case 0:
                return 0;
            case 1:
                if (bb == false) {
                    return 0;
                }
                if (bb = true) {
                    return 10;
                }

            case 2:
                if (bb == false) {
                    return 10;
                }
                if (bb = true) {
                    return 100;
                }
            case 3:
                if (bb == false) {
                    return 100;
                }
                if (bb = true) {
                    return 1000;
                }
            case 4:
                if (bb == false) {
                    return 1000;
                }
                if (bb = true) {
                    return 10000;
                }
            case 5:
                return 1000000;
            //if(c==2){return 1000000;}
            // if(c==1){return 100000;}
            //if(c==0){return 1000000;}
            default:
                return 1000000;
            //	if(c==2){return 1000000;}
            // if(c==1){return 100000;}
            // if(c==0){return 1000000;}
        }
    }

    private static void getNewDown(int[][] tryChess, Point[] P, int c) {

        int[][] tempNull = new int[15][15];


        P[0].count = 0;
        int max = 0;

        for (int x = 0; x < fifteen; x++) {
            for (int y = 0; y < fifteen; y++) {
                if (tryChess[x][y] == 0) {
                    boolean flag = false;


                    if (isInside(x - 1, y)) {
                        if (tryChess[x - 1][y] != 0) {
                            tempNull[x][y] = getNullPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }
                    if (isInside(x - 2, y)) {
                        if (tryChess[x - 2][y] != 0) {
                            tempNull[x][y] = getNullPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }
                    if (isInside(x + 1, y)) {
                        if (tryChess[x + 1][y] != 0) {
                            tempNull[x][y] = getNullPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }
                    if (isInside(x + 2, y)) {
                        if (tryChess[x + 2][y] != 0) {
                            tempNull[x][y] = getNullPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }

                    if (isInside(x, y - 1)) {
                        if (tryChess[x][y - 1] != 0) {
                            tempNull[x][y] = getNullPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }

                    if (isInside(x, y - 2)) {
                        if (tryChess[x][y - 2] != 0) {
                            tempNull[x][y] = getNullPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }

                    if (isInside(x, y + 1)) {
                        if (tryChess[x][y + 1] != 0) {
                            tempNull[x][y] = getNullPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }

                    if (isInside(x, y + 2)) {
                        if (tryChess[x][y + 2] != 0) {
                            tempNull[x][y] = getNullPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }


                    if (isInside(x + 1, y + 1)) {
                        if (tryChess[x + 1][y + 1] != 0) {
                            tempNull[x][y] = getNullPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }


                    if (isInside(x + 2, y + 2)) {
                        if (tryChess[x + 2][y + 2] != 0) {
                            tempNull[x][y] = getNullPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }


                    if (isInside(x - 1, y + 1)) {
                        if (tryChess[x - 1][y + 1] != 0) {
                            tempNull[x][y] = getNullPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }


                    if (isInside(x - 2, y + 2)) {
                        if (tryChess[x - 2][y + 2] != 0) {
                            tempNull[x][y] = getNullPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }

                    if (isInside(x + 1, y - 1)) {
                        if (tryChess[x + 1][y - 1] != 0) {
                            tempNull[x][y] = getNullPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }

                    if (isInside(x + 2, y - 2)) {
                        if (tryChess[x + 2][y - 2] != 0) {
                            tempNull[x][y] = getNullPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }


                    if (isInside(x - 1, y - 1)) {
                        if (tryChess[x - 1][y - 1] != 0) {
                            tempNull[x][y] = getNullPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }


                    if (isInside(x - 2, y - 2)) {
                        if (tryChess[x - 2][y - 2] != 0) {
                            tempNull[x][y] = getNullPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }

                    if (isInside(x - 1, y + 2)) {
                        if (tryChess[x - 1][y + 2] != 0) {
                            tempNull[x][y] = getNullPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }

                    if (isInside(x - 2, y + 1)) {
                        if (tryChess[x - 2][y + 1] != 0) {
                            tempNull[x][y] = getNullPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }

                    if (isInside(x + 1, y + 2)) {
                        if (tryChess[x + 1][y + 2] != 0) {
                            tempNull[x][y] = getNullPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }

                    if (isInside(x + 2, y + 1)) {
                        if (tryChess[x + 2][y + 1] != 0) {
                            tempNull[x][y] = getNullPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }
                    if (isInside(x + 1, y - 2)) {
                        if (tryChess[x + 1][y - 2] != 0) {
                            tempNull[x][y] = getNullPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }

                    if (isInside(x + 2, y - 1)) {
                        if (tryChess[x + 2][y - 1] != 0) {
                            tempNull[x][y] = getNullPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }

                    if (isInside(x - 1, y - 2)) {
                        if (tryChess[x - 1][y - 2] != 0) {
                            tempNull[x][y] = getNullPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }

                    if (isInside(x - 2, y - 1)) {
                        if (tryChess[x - 2][y - 1] != 0) {
                            tempNull[x][y] = getNullPointScore(tryChess, x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }


                }

            }
        }


        if (max == 10) {
            P[0].count = 1;
            P[0].sheng = 1;
            return;
        } else if (max == 9) {
            P[0].count = 1;
            return;

        } else if (max == 8) {

            P[0].count = 0;
            for (int x = 0; x < fifteen; x++) {
                for (int y = 0; y < fifteen; y++) {
                    if (tempNull[x][y] == 8) {
                        P[P[0].count].x = x;
                        P[P[0].count].y = y;
                        P[0].count++;
                    }
                }
            }

            return;
        }


        int count = 0;
        int u = 7;
        while (u > 0) {
            for (int x = 0; x < fifteen; x++) {
                for (int y = 0; y < fifteen; y++) {
                    if (tempNull[x][y] == u) {
                        P[count].x = x;
                        P[count].y = y;
                        count++;
                    }
                }
            }
            u--;

        }
        return;
    }

    private static boolean isInside(int x, int y) {

        if (x < 0 || x > 14 || y < 0 || y > 14) {
            return false;
        }

        return true;
    }


    public static int getNullPointScore(int[][] tryChess, int xIndex, int yIndex, int c) {

        int continueCount = 0;//连续棋子的个数

        int d = (c == 1 ? -1 : 1);
        int max = 0;
        int chance = 0;

        int score = 0;
        int x;
        int y;
//////1
        for (x = xIndex - 1; x >= 0; x--) {
            if (tryChess[x][yIndex] == c)
                continueCount++;
            else {
                if (tryChess[x][yIndex] == d)
                    chance++;
                break;
            }
        }
        if (x < 0) chance++;
        for (x = xIndex + 1; x <= COLS; x++) {
            if (tryChess[x][yIndex] == c)
                continueCount++;
            else {
                if (tryChess[x][yIndex] == d)
                    chance++;
                break;
            }
        }
        if (x > fifteen - 1) chance++;
        score = getMyNullScore(continueCount, chance);
        if (score > max) max = score;


///////2

        continueCount = 0;
        chance = 0;
        for (y = yIndex - 1; y >= 0; y--) {
            if (tryChess[xIndex][y] == c)
                continueCount++;
            else {
                if (tryChess[xIndex][y] == d)
                    chance++;
                break;
            }
        }
        if (y < 0) chance++;

        for (y = yIndex + 1; y <= ROWS; y++) {
            if (tryChess[xIndex][y] == c)
                continueCount++;
            else {
                if (tryChess[xIndex][y] == d)
                    chance++;
                break;
            }
        }
        if (y > fifteen - 1) chance++;
        score = getMyNullScore(continueCount, chance);
        if (score > max) max = score;


/////////3


        continueCount = 0;
        chance = 0;
        for (x = xIndex + 1, y = yIndex - 1; y >= 0 && x <= COLS; x++, y--) {
            if (tryChess[x][y] == c)
                continueCount++;
            else {
                if (tryChess[x][y] == d)
                    chance++;
                break;
            }
        }
        if (y < 0 || x > fifteen - 1) chance++;

        for (x = xIndex - 1, y = yIndex + 1; x >= 0 && y <= ROWS; x--, y++) {
            if (tryChess[x][y] == c)
                continueCount++;
            else {
                if (tryChess[x][y] == d)
                    chance++;
                break;
            }
        }
        if (x < 0 || y > fifteen - 1) chance++;
        score = getMyNullScore(continueCount, chance);
        if (score > max) max = score;


        //////////4

        continueCount = 0;
        chance = 0;
        for (x = xIndex - 1, y = yIndex - 1; x >= 0 && y >= 0; x--, y--) {
            if (tryChess[x][y] == c)
                continueCount++;
            else {
                if (tryChess[x][y] == d)
                    chance++;
                break;
            }
        }
        if (x < 0 || y < 0) chance++;

        for (x = xIndex + 1, y = yIndex + 1; x <= COLS && y <= ROWS; x++, y++) {
            if (tryChess[x][y] == c)
                continueCount++;
            else {
                if (tryChess[x][y] == d)
                    chance++;
                break;
            }
        }
        if (x > fifteen - 1 || y > fifteen - 1) chance++;
        score = getMyNullScore(continueCount, chance);
        if (score > max) max = score;


        //xxxxxxxxxxxxxxxxxxxxx2222222

        continueCount = 0;
        chance = 0;
        for (x = xIndex - 1; x >= 0; x--) {
            if (tryChess[x][yIndex] == d)
                continueCount++;
            else {
                if (tryChess[x][yIndex] == c)
                    chance++;
                break;
            }
        }
        if (x < 0) chance++;
        for (x = xIndex + 1; x <= COLS; x++) {
            if (tryChess[x][yIndex] == d)
                continueCount++;
            else {
                if (tryChess[x][yIndex] == c)
                    chance++;
                break;
            }
        }
        if (x > fifteen - 1) chance++;
        score = getHeNullScore(continueCount, chance);
        if (score > max) max = score;


///////2

        continueCount = 0;
        chance = 0;
        for (y = yIndex - 1; y >= 0; y--) {
            if (tryChess[xIndex][y] == d)
                continueCount++;
            else {
                if (tryChess[xIndex][y] == c)
                    chance++;
                break;
            }
        }
        if (y < 0) chance++;

        for (y = yIndex + 1; y <= ROWS; y++) {
            if (tryChess[xIndex][y] == d)
                continueCount++;
            else {
                if (tryChess[xIndex][y] == c)
                    chance++;
                break;
            }
        }
        if (y > fifteen - 1) chance++;
        score = getHeNullScore(continueCount, chance);
        if (score > max) max = score;


/////////3


        continueCount = 0;
        chance = 0;
        for (x = xIndex + 1, y = yIndex - 1; y >= 0 && x <= COLS; x++, y--) {
            if (tryChess[x][y] == d)
                continueCount++;
            else {
                if (tryChess[x][y] == c)
                    chance++;
                break;
            }
        }
        if (y < 0 || x > fifteen - 1) chance++;

        for (x = xIndex - 1, y = yIndex + 1; x >= 0 && y <= ROWS; x--, y++) {
            if (tryChess[x][y] == d)
                continueCount++;
            else {
                if (tryChess[x][y] == c)
                    chance++;
                break;
            }
        }
        if (x < 0 || y > fifteen - 1) chance++;
        score = getHeNullScore(continueCount, chance);
        if (score > max) max = score;


        //////////4

        continueCount = 0;
        chance = 0;
        for (x = xIndex - 1, y = yIndex - 1; x >= 0 && y >= 0; x--, y--) {
            if (tryChess[x][y] == d)
                continueCount++;
            else {
                if (tryChess[x][y] == c)
                    chance++;
                break;
            }
        }
        if (x < 0 || y < 0) chance++;

        for (x = xIndex + 1, y = yIndex + 1; x <= COLS && y <= ROWS; x++, y++) {
            if (tryChess[x][y] == d)
                continueCount++;
            else {
                if (tryChess[x][y] == c)
                    chance++;
                break;
            }
        }
        if (x > fifteen - 1 || y > fifteen - 1) chance++;
        score = getHeNullScore(continueCount, chance);
        if (score > max) max = score;
        return max;
    }


    private static int getMyNullScore(int t, int c) {
        switch (t) {
            case 0:
                return 2;
            case 1:
                if (c == 2) {
                    return 0;
                }
                if (c == 1) {
                    return 2;
                }
                if (c == 0) {
                    return 4;
                }
            case 2:
                if (c == 2) {
                    return 0;
                }
                if (c == 1) {
                    return 4;
                }
                if (c == 0) {
                    return 6;
                }
            case 3:
                if (c == 2) {
                    return 0;
                }
                if (c == 1) {
                    return 6;
                }
                if (c == 0) {
                    return 8;
                }
            case 4:
                return 10;

            default:
                return 10;
        }

    }

    private static int getHeNullScore(int t, int c) {
        switch (t) {
            case 0:
                return 1;
            case 1:
                if (c == 2) {
                    return 0;
                }
                if (c == 1) {
                    return 1;
                }
                if (c == 0) {
                    return 3;
                }
            case 2:
                if (c == 2) {
                    return 0;
                }
                if (c == 1) {
                    return 3;
                }
                if (c == 0) {
                    return 5;
                }
            case 3:
                if (c == 2) {
                    return 0;
                }
                if (c == 1) {
                    return 5;
                }
                if (c == 0) {
                    return 7;
                }
            case 4:
                return 9;

            default:
                return 9;
        }

    }


    public static class GameResult {

        public GameResult(Integer score, Integer x, Integer y) {
            this.score = score;
            this.x = x;
            this.y = y;
        }

        private Integer score;
        private Integer x;
        private Integer y;

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }

        public Integer getX() {
            return x;
        }

        public void setX(Integer x) {
            this.x = x;
        }

        public Integer getY() {
            return y;
        }

        public void setY(Integer y) {
            this.y = y;
        }
    }


}
