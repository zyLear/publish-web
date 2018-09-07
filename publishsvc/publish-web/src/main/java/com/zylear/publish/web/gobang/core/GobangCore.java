package com.zylear.publish.web.gobang.core;

import com.zylear.publish.web.gobang.GobangPanel.BestPoint;
import com.zylear.publish.web.gobang.NullPoint;
import com.zylear.publish.web.gobang.Point;
import com.zylear.publish.web.gobang.bean.GobangConstants;

import java.awt.*;

/**
 * Created by xiezongyu on 2018/9/6.
 */
public class GobangCore {

    public static final Integer ALPHA = Integer.MIN_VALUE;
    public static final Integer BETA = Integer.MAX_VALUE;
    public static final Integer deadline = 60000;
    public static int count = 0;


    public static void calculate(int[][] tryChess, int gameDepth, int executeDepth, int calculateColor, BestPoint bestPoint) {
//        long currentTime = System.currentTimeMillis();
        int score;
        score = GobangCore.execute(tryChess, executeDepth, executeDepth, ALPHA, BETA, calculateColor, bestPoint);
        if (score != GobangConstants.WIN_SCORE) {
            System.out.println("算杀失败，开启敌人算杀");
            calculateColor = -calculateColor;
            score = GobangCore.execute(tryChess, executeDepth, executeDepth, ALPHA, BETA, calculateColor, bestPoint);
            calculateColor = -calculateColor;
            if (score != GobangConstants.WIN_SCORE) {
                System.out.println("对手算杀失败，正常博弈...");
                score = GobangCore.minMax(tryChess, gameDepth, gameDepth, ALPHA, BETA, calculateColor, bestPoint);
            } else {
                System.out.println("对手算杀成功");
            }
        } else {
            System.out.println("电脑算杀成功");
        }

        System.out.println("分数：" + score + " count:" + count);
        count = 0;
//        long time = (System.currentTimeMillis() - currentTime);

//        if (time > deadline) {
//
//            gameDepth -= 2;
//            for (int x = 0; x < GobangConstants.FIFTEEN; x++) {
//                for (int y = 0; y < GobangConstants.FIFTEEN; y++) {
//                    tryChess[x][y] = 0;
//                }
//            }
//
////            for (int i = 0; i < chessCount; i++) {
////                if (chessList[i].getColor() == Color.white)
////                    tryChess[chessList[i].getX()][chessList[i].getY()] = 1;
////                else tryChess[chessList[i].getX()][chessList[i].getY()] = -1;
////            }
//
//
//            System.out.println("时间超时，采用快速搜索战略");
////            noti.setText("时间超时，采用快速搜索战略");
////            currentTime = System.currentTimeMillis();
//            highScore = GobangCore.minMax(tryChess, gameDepth, gameDepth, ALPHA, BETA, computerColor, bestPoint);
//        }


    }


    public static int minMax(int[][] tryChess, int depth, int maxDepth, int ALPHA, int BETA, int calculateColor, BestPoint bestPoint) {

//        if (System.currentTimeMillis() - currentime > deadline) return 1;
        depth--;
        if (depth == 0) {
            count++;
            return GobangChessScoreCoreV2.getChessScoreV2(tryChess, calculateColor);
        }


        if (depth % 2 == 0) {

            Point[] tryPoints = new Point[225];
            GobangTryChessCore.getTryPoints(tryChess, tryPoints, calculateColor);
            if (depth == maxDepth - 1) {
                System.out.println("shu:" + tryPoints[0].count);
            }
            for (int i = 0; i < tryPoints[0].count; i++) {


                tryChess[tryPoints[i].x][tryPoints[i].y] = calculateColor;
                int t;
                if (tryPoints[0].sheng == 1) {
                    t = GobangChessScoreCoreV2.getChessScoreV2(tryChess, calculateColor);
                } else {
                    t = minMax(tryChess, depth, maxDepth, ALPHA, BETA, calculateColor, bestPoint);
//                    if (System.currentTimeMillis() - currentime > deadline) return 1;
                }


                if (t >= BETA) {

                    tryChess[tryPoints[i].x][tryPoints[i].y] = 0;
                    return t;
                }


                if (t > ALPHA) {
                    ALPHA = t;
                    if (depth == maxDepth - 1) {
//                        BestPoint pp = new BestPoint(p[i].x, p[i].y, ALPHA);
                        // bestPoints.add(pp);
                        bestPoint.x = tryPoints[i].x;
                        bestPoint.y = tryPoints[i].y;
                    }
                }
                tryChess[tryPoints[i].x][tryPoints[i].y] = 0;
            }


            return ALPHA;

        } else {

            Point[] p = new Point[225];
            GobangTryChessCore.getTryPoints(tryChess, p, -calculateColor);
            for (int i = 0; i < p[0].count; i++) {
                tryChess[p[i].x][p[i].y] = -calculateColor;
                int t;
                if (p[0].sheng == 1) {
                    t = GobangChessScoreCoreV2.getChessScoreV2(tryChess, calculateColor);
                } else {
                    t = minMax(tryChess, depth, maxDepth, ALPHA, BETA, calculateColor, bestPoint);
//                    if (System.currentTimeMillis() - currentime > deadline) return 1;
                }

                if (t <= ALPHA) {
                    tryChess[p[i].x][p[i].y] = 0;
                    return t;
                }

                if (t < BETA) {
                    BETA = t;
                }

                tryChess[p[i].x][p[i].y] = 0;

            }

            return BETA;
        }
    }


    //
    public static int execute(int[][] tryChess, int depth, int maxDepth, int ALPHA, int BETA, int calculateColor, BestPoint bestPoint) {


        depth--;
        if (depth == 0) {
            return 0;
        }


        if (depth % 2 == 0) {

            NullPoint[] tryPoints = new NullPoint[225];

            NullPoint np;
            NullPoint mark = new NullPoint();

//            getSSNewDown(p, mark, calculateColor);
            GobangExecuteTryChessCore.getTryPoints(tryChess, tryPoints, mark, calculateColor);

            if (depth == maxDepth - 1) {
                System.out.println("shu：" + mark.count);
            }

            if (mark.count == 0) {
                return 0;
            }
            for (int i = 0; i < mark.count; i++) {


                np = tryPoints[i];
                tryChess[np.x][np.y] = calculateColor;
                int t;
                if (mark.sheng == 1) {

                    t = GobangConstants.WIN_SCORE;


                } else
                    t = execute(tryChess, depth, maxDepth, ALPHA, BETA, calculateColor, bestPoint);


                if (t >= BETA) {

                    tryChess[np.x][np.y] = 0;
                    return t;
                }

                if (t > ALPHA) {
                    ALPHA = t;

                    if (depth == maxDepth - 1) {
//                        fx = np.x;
//                        fy = np.y;
                        bestPoint.x = tryPoints[i].x;
                        bestPoint.y = tryPoints[i].y;


                        if (ALPHA == 10000000) {
                            System.out.println("准备结束！");
                            break;
                        }
                    }


                }

                if (depth == maxDepth - 1) {
                    System.out.println("aa下层分数：" + ALPHA);
                }

                tryChess[np.x][np.y] = 0;

            }

            return ALPHA;

        } else {

            NullPoint[] tryPoints = new NullPoint[225];


            NullPoint np;
            NullPoint mark = new NullPoint();

            GobangExecuteTryChessCore.getTryPoints(tryChess, tryPoints, mark, -calculateColor);


            if (mark.count == 0) {
                return 0;
            }
            for (int i = 0; i < mark.count; i++) {

                np = tryPoints[i];
                tryChess[np.x][np.y] = -calculateColor;   //尝试下棋子

                int t;
                if (mark.sheng == 1) {
                    t = GobangConstants.LOSE_SCORE;


                } else
                    t = execute(tryChess, depth, maxDepth, ALPHA, BETA, calculateColor, bestPoint);

                if (t <= ALPHA) {
                    tryChess[np.x][np.y] = 0;

                    return t;
                }


                if (t < BETA) {
                    BETA = t;
                }

                tryChess[np.x][np.y] = 0;       //取走尝试下的棋子

            }
            return BETA;

        }

    }

//    public static class GameResult {
//
//        public GameResult(Integer score, Integer x, Integer y) {
//            this.score = score;
//            this.x = x;
//            this.y = y;
//        }
//
//        private Integer score;
//        private Integer x;
//        private Integer y;
//
//        public Integer getScore() {
//            return score;
//        }
//
//        public void setScore(Integer score) {
//            this.score = score;
//        }
//
//        public Integer getX() {
//            return x;
//        }
//
//        public void setX(Integer x) {
//            this.x = x;
//        }
//
//        public Integer getY() {
//            return y;
//        }
//
//        public void setY(Integer y) {
//            this.y = y;
//        }
//    }


}
