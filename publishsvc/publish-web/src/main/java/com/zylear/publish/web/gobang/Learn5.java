package com.zylear.publish.web.gobang;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Learn5 extends JPanel implements MouseListener {


    public static class BestPoint {
        public BestPoint() {

        }

        public int x;
        public int y;
        public int score;

        public BestPoint(int x, int y, int score) {
            this.x = x;
            this.y = y;
            this.score = score;
        }
    }

    double time;
    public int fx = 0, fy = 0;
    public static final int MARGIN = 30;//边距
    public static final int GRID_SPAN = 35;//网格间距
    public static final int ROWS = 14;//棋盘行数
    public static final int COLS = 14;//棋盘列数
    public static final int fifteen = 15;
    public int nullCount = 0;
    public int trychessListCount = 0;
    final int alpha = Integer.MIN_VALUE;
    final int beta = Integer.MAX_VALUE;
    public int GameDeepth = 7;
    public int SuanShaDeepth = 11;
    public boolean gamestart = false;
    boolean isVisual = false;
    private int Deadline = 60000;
    private double currentime;

    private ArrayList<BestPoint> bestPoints = new ArrayList<>();
    private int highScore;

    int begin = 1;
    Point[] chessList = new Point[(ROWS + 1) * (COLS + 1)];//初始每个数组元素为null


    int[][] trychessList = new int[15][15];
    int[][] nullpointnew = new int[15][15];
    JLabel noti = new JLabel("通知");

    private boolean pressMark = false;
    public String string1 = "1";

    boolean isBlack = true;//默认开始是黑棋先
    boolean gameOver = false;//游戏是否结束
    int chessCount = 0;//当前棋盘棋子的个数
    int xIndex, yIndex;//当前刚下棋子的索引
    int x1, x2, y1, y2;
    boolean b;  //判断输赢
    boolean paint = false;

    int computerscore = 0;
    int humanscore = 0;
    public static final int WHITE = 1;
    public static final int BLACK = -1;
    int computerColor = 1;

    Image img;
    Image shadows;
    Color colortemp;


    private int[][] allChess = new int[15][15];

    public Learn5() {
        noti.setFont(new Font("宋体", Font.PLAIN, 20));
        Point ch = new Point(7, 7, Color.white);
        //  chessList[0]=ch;
        // setBackground(Color.blue);//设置背景色为橘黄色
        //		   img=Toolkit.getDefaultToolkit().getImage("board.jpg");
        //		   shadows=Toolkit.getDefaultToolkit().getImage("shadows.jpg");
        this.setLayout(null);
        //     this.add(noti,new BorderLayout().SOUTH);
        this.add(noti);
        noti.setBounds(210, 530, 200, 50);
        // noti.setVisible(false);
        addMouseListener(this);
        noti.setVisible(true);
        addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {

            }

            public void mouseMoved(MouseEvent e) {
                int x1 = (e.getX() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
                //将鼠标点击的坐标位置转成网格索引
                int y1 = (e.getY() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
                //游戏已经结束不能下
                //落在棋盘外不能下
                //x，y位置已经有棋子存在，不能下
                if (x1 < 0 || x1 > ROWS || y1 < 0 || y1 > COLS || gameOver || findChess(x1, y1))
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    //设置成默认状态
                else setCursor(new Cursor(Cursor.HAND_CURSOR));

            }
        });

        restart();  //默认 电脑先走
    }


    protected boolean findChess(int x1, int y1) {

        for (Point c : chessList) {
            if (c != null && x1 == c.getX() && y1 == c.getY())
                return true;
        }
        return false;
    }


    protected boolean getChess(int x, int y, Color c) {

        for (Point p : chessList) {
            if (p != null && p.getX() == x && p.getY() == y && p.getColor() == c)
                return true;
        }

        return false;
    }


    protected boolean isNull(int x, int y) {


        if (getChess(x, y, Color.black) || (getChess(x, y, Color.white))) {
            return false;
        }

        return true;
    }


    public void restart() {
        for (int a = 0; a < chessCount; a++) {
            chessList[a] = null;
        }

        chessCount = 1;
        Point ch = new Point(7, 7, Color.white);
        chessList[0] = ch;
        allChess = new int[15][15];
        allChess[7][7] = WHITE;

        isBlack = true;
        gameOver = false;
        b = false;
        begin = 1;
    }


    public void goback() {
        if (chessCount == 0) return;
        chessList[chessCount - 1] = null;
        chessCount--;
        isBlack = !isBlack;
        repaint();
    }


    public int getScore(int t, int c) {
        switch (t) {
            case 1:
                if (c == 2) {
                    return 0;
                }
                if (c == 1) {
                    return 0;
                }
                if (c == 0) {
                    return 10;
                }
            case 2:
                if (c == 2) {
                    return 0;
                }
                if (c == 1) {
                    return 10;
                }
                if (c == 0) {
                    return 100;
                }
            case 3:
                if (c == 2) {
                    return 0;
                }
                if (c == 1) {
                    return 100;
                }
                if (c == 0) {
                    return 1000;
                }
            case 4:
                if (c == 2) {
                    return 0;
                }
                if (c == 1) {
                    return 1000;
                }
                if (c == 0) {
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


    @Override
    public void mouseClicked(MouseEvent e) {


    }


    @Override
    public void mouseEntered(MouseEvent e) {
        //if(isVisual)
        //noti.setVisible(true);

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {

//		   xIndex=(e.getX()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
//		   yIndex=(e.getY()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
//
//		   //落在棋盘外不能下
//		   if(xIndex<0||xIndex>ROWS||yIndex<0||yIndex>COLS)
//			   return;
//		// noti.setVisible(true);

        // noti.setText("电脑正在思考.....");

        if (pressMark) {
            return;
        }
        pressMark = true;
        String colorName = isBlack ? "黑棋" : "白棋";


        //  isVisual=true;
        //将鼠标点击的坐标位置转换成网格索引
        xIndex = (e.getX() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
        yIndex = (e.getY() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;

        //落在棋盘外不能下
        if (xIndex < 0 || xIndex > ROWS || yIndex < 0 || yIndex > COLS) {
            pressMark = false;
            return;
        }

        //如果x，y位置已经有棋子存在，不能下
        if (findChess(xIndex, yIndex)) {
            pressMark = false;
            return;
        }


        //可以进行时的处理
        Point ch = new Point(xIndex, yIndex, isBlack ? Color.black : Color.white);
        chessList[chessCount++] = ch;
        int color = isBlack ? BLACK : WHITE;
        allChess[xIndex][yIndex] = color;

        b = GobangJudge.isWin(allChess, xIndex, yIndex, color);


        //如果胜出则给出提示信息，不能继续下棋
        if (b) {
            String msg = String.format("恭喜，%s赢了！", colorName);

            JOptionPane.showMessageDialog(this, msg);
            gameOver = true;
            restart();
            pressMark = false;
            return;
        }


        // string1+="1";
        noti.setText("电脑正在思考.....");
        repaint();


        new Thread(new Runnable() {

            @Override
            public void run() {

                Learn5.this.myPress();

            }

        }).start();

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {


    }


    public void paintComponent(Graphics g) {

        super.paintComponent(g);//画棋盘

        ImageIcon ii = null;
        ii = new ImageIcon(/*"d:\\java\\图像\\first.jpg"*/);

        Image io = ii.getImage();

        if (paint)
            g.drawImage(io, 0, 0, 585, 585, this);


        this.setBackground(Color.ORANGE);//LIGHT_GRAY

        for (int i = 0; i <= ROWS; i++) {//画横线
            g.drawLine(MARGIN, MARGIN + i * GRID_SPAN, MARGIN + COLS * GRID_SPAN, MARGIN + i * GRID_SPAN);
        }
        for (int i = 0; i <= COLS; i++) {//画竖线
            g.drawLine(MARGIN + i * GRID_SPAN, MARGIN, MARGIN + i * GRID_SPAN, MARGIN + ROWS * GRID_SPAN);

        }

        //画棋子
        for (int i = 0; i < chessCount; i++) {
            //网格交叉点x，y坐标
            int xPos = chessList[i].getX() * GRID_SPAN + MARGIN;
            int yPos = chessList[i].getY() * GRID_SPAN + MARGIN;
            g.setColor(chessList[i].getColor());//设置颜色
            // g.fillOval(xPos-Point.DIAMETER/2, yPos-Point.DIAMETER/2,
            //Point.DIAMETER, Point.DIAMETER);
            //g.drawImage(shadows, xPos-Point.DIAMETER/2, yPos-Point.DIAMETER/2, Point.DIAMETER, Point.DIAMETER, null);
            colortemp = chessList[i].getColor();
            if (colortemp == Color.black) {
                RadialGradientPaint paint = new RadialGradientPaint(xPos - Point.DIAMETER / 2 + 25, yPos - Point.DIAMETER / 2 + 10, 20, new float[]{0f, 1f}
                        , new Color[]{Color.WHITE, Color.BLACK});
                ((Graphics2D) g).setPaint(paint);
                ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);

            } else if (colortemp == Color.white) {
                RadialGradientPaint paint = new RadialGradientPaint(xPos - Point.DIAMETER / 2 + 25, yPos - Point.DIAMETER / 2 + 10, 70, new float[]{0f, 1f}
                        , new Color[]{Color.WHITE, Color.BLACK});
                ((Graphics2D) g).setPaint(paint);
                ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);

            }

            Ellipse2D e = new Ellipse2D.Float(xPos - Point.DIAMETER / 2, yPos - Point.DIAMETER / 2, 34, 35);
            ((Graphics2D) g).fill(e);
            //标记最后一个棋子的红矩形框


            if (i == chessCount - 1) {//如果是最后一个棋子
                g.setColor(Color.red);
                g.drawRect(xPos - Point.DIAMETER / 2, yPos - Point.DIAMETER / 2,
                        34, 35);
            }

            if (b) {
                g.setColor(Color.red);
                g.drawLine(x1 * 35 + 30, y1 * 35 + 30, x2 * 35 + 30, y2 * 35 + 30);
                g.drawLine(x1 * 35 + 30 + 1, y1 * 35 + 30, x2 * 35 + 30 + 1, y2 * 35 + 30);
                g.drawLine(x1 * 35 + 30 - 1, y1 * 35 + 30, x2 * 35 + 30 - 1, y2 * 35 + 30);
                g.drawLine(x1 * 35 + 30, y1 * 35 + 30 + 1, x2 * 35 + 30, y2 * 35 + 30 + 1);
                g.drawLine(x1 * 35 + 30, y1 * 35 + 30 - 1, x2 * 35 + 30, y2 * 35 + 30 - 1);

            }

        }
    }


    public int getPointValue(int xIndex, int yIndex, int c) {
        int continueCount = 1;//连续棋子的个数
        int d = 1;
        if (c == 1)
            d = -1;
        int chance = 0;
        int x;
        int y;
        int score = 0;
//////1
        for (x = xIndex - 1; x >= 0; x--) {
            if (trychessList[x][yIndex] == c)
                continueCount++;
            else {
                if (trychessList[x][yIndex] == d)
                    chance++;
                break;
            }
        }
        if (x < 0) chance++;
        for (x = xIndex + 1; x <= COLS; x++) {
            if (trychessList[x][yIndex] == c)
                continueCount++;
            else {
                if (trychessList[x][yIndex] == d)
                    chance++;
                break;
            }
        }
        if (x > fifteen - 1) chance++;
        score = score + getScore(continueCount, chance);


///////2

        continueCount = 1;
        chance = 0;
        for (y = yIndex - 1; y >= 0; y--) {
            if (trychessList[xIndex][y] == c)
                continueCount++;
            else {
                if (trychessList[xIndex][y] == d)
                    chance++;
                break;
            }
        }
        if (y < 0) chance++;

        for (y = yIndex + 1; y <= ROWS; y++) {
            if (trychessList[xIndex][y] == c)
                continueCount++;
            else {
                if (trychessList[xIndex][y] == d)
                    chance++;
                break;
            }
        }
        if (y > fifteen - 1) chance++;
        score = score + getScore(continueCount, chance);


/////////3


        continueCount = 1;
        chance = 0;
        for (x = xIndex + 1, y = yIndex - 1; y >= 0 && x <= COLS; x++, y--) {
            if (trychessList[x][y] == c)
                continueCount++;
            else {
                if (trychessList[x][y] == d)
                    chance++;
                break;
            }
        }
        if (y < 0 || x > fifteen - 1) chance++;

        for (x = xIndex - 1, y = yIndex + 1; x >= 0 && y <= ROWS; x--, y++) {
            if (trychessList[x][y] == c)
                continueCount++;
            else {
                if (trychessList[x][y] == d)
                    chance++;
                break;
            }
        }
        if (x < 0 || y > fifteen - 1) chance++;
        score = score + getScore(continueCount, chance);


        //////////4

        continueCount = 1;
        chance = 0;
        for (x = xIndex - 1, y = yIndex - 1; x >= 0 && y >= 0; x--, y--) {
            if (trychessList[x][y] == c)
                continueCount++;
            else {
                if (trychessList[x][y] == d)
                    chance++;
                break;
            }
        }
        if (x < 0 || y < 0) chance++;

        for (x = xIndex + 1, y = yIndex + 1; x <= COLS && y <= ROWS; x++, y++) {
            if (trychessList[x][y] == c)
                continueCount++;
            else {
                if (trychessList[x][y] == d)
                    chance++;
                break;
            }
        }
        if (x > fifteen - 1 || y > fifteen - 1) chance++;
        score = score + getScore(continueCount, chance);

        return score;
    }


    public int MinMax(int deepth, int alpha, int beta) {

        if (System.currentTimeMillis() - currentime > Deadline) return 1;
        deepth--;
        if (deepth == 0) {
            return getValueTwo();
        }


        if (deepth % 2 == 0) {

            Point[] p = new Point[225];
            for (int o = 0; o < 225; o++)
                p[o] = new Point();
            getNewDown(p, computerColor);
            if (deepth == GameDeepth - 1) {
                System.out.println("shu:" + p[0].count);
            }
            for (int i = 0; i < p[0].count; i++) {


                trychessList[p[i].x][p[i].y] = computerColor;
                int t;
                if (p[0].sheng == 1) {

                    t = getValueTwo();


                } else {
                    t = MinMax(deepth, alpha, beta);
                    if (System.currentTimeMillis() - currentime > Deadline) return 1;
                }


                if (t >= beta) {

                    trychessList[p[i].x][p[i].y] = 0;
                    return t;
                }


                if (t > alpha) {
                    alpha = t;
                    if (deepth == GameDeepth - 1) {

                        BestPoint pp = new BestPoint(p[i].x, p[i].y, alpha);
                        // bestPoints.add(pp);
                        fx = p[i].x;
                        fy = p[i].y;
                    }
                }


//			 if(deepth==GameDeepth-1)
//		        {
//             System.out.println("下层分"+t+"位置："+p[i].x+","+p[i].y);
//		        }
//


                trychessList[p[i].x][p[i].y] = 0;


            }


            return alpha;

        } else {
            int min = 100000000;

            Point[] p = new Point[225];
            for (int o = 0; o < 225; o++)
                p[o] = new Point();
            getNewDown(p, 0 - computerColor);

            for (int i = 0; i < p[0].count; i++) {


                trychessList[p[i].x][p[i].y] = 0 - computerColor;
                int t;
                if (p[0].sheng == 1) {
                    t = getValueTwo();


                } else {
                    t = MinMax(deepth, alpha, beta);
                    if (System.currentTimeMillis() - currentime > Deadline) return 1;
                }

                if (t <= alpha) {
                    trychessList[p[i].x][p[i].y] = 0;

                    return t;
                }


                if (t < beta) {
                    beta = t;
                }


                trychessList[p[i].x][p[i].y] = 0;


            }

            return beta;
        }


    }


    private int getValue() {

        int Ascore = 0;
        int Bscore = 0;
        int score = 0;


        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {

                if (trychessList[i][j] == 1)
                    Ascore = Ascore + getPointValue(i, j, 1);
                if (trychessList[i][j] == -1) Bscore = Bscore + getPointValue(i, j, -1);


            }
        }


        score = (int) (Ascore - Bscore);

        return score;


    }


    private void getNewDown(Point[] P, int c) {

        int[][] tempNull = new int[15][15];


        P[0].count = 0;
        int max = 0;

        for (int x = 0; x < fifteen; x++) {
            for (int y = 0; y < fifteen; y++) {
                if (trychessList[x][y] == 0) {
                    boolean flag = false;


//					 if(x>=2&&x<=12&&y>=2&&y<=12){
//
//						  if(  trychessList[x-1][y]!=0||trychessList[x-2][y]!=0||trychessList[x+1][y]!=0||trychessList[x+2][y]!=0
//						  ||trychessList[x][y-1]!=0||trychessList[x][y-2]!=0||trychessList[x][y+1]!=0||trychessList[x][y+2]!=0
//						  ||trychessList[x-1][y-1]!=0||trychessList[x+1][y+1]!=0||trychessList[x+2][y+2]!=0||trychessList[x-2][y-2]!=0
//						  ||trychessList[x+1][y-1]!=0||trychessList[x-1][y+1]!=0||trychessList[x+2][y-2]!=0||trychessList[x-2][y+2]!=0
//						  ||trychessList[x+1][y-2]!=0||trychessList[x-1][y+2]!=0||trychessList[x+2][y-1]!=0||trychessList[x-2][y+1]!=0
//					      ||trychessList[x+1][y+2]!=0||trychessList[x-1][y-2]!=0||trychessList[x+2][y+1]!=0||trychessList[x-2][y-1]!=0
//								  )
//						  {    tempNull[x][y]=getNullPointScore(x,y,c);
//						               if(max<tempNull[x][y]) { max=tempNull[x][y]; P[0].x=x;P[0].y=y;}
//						           P[0].count++;
//						  }
//				       }

                    if (isInside(x - 1, y)) {
                        if (trychessList[x - 1][y] != 0) {
                            tempNull[x][y] = getNullPointScore(x, y, c);
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
                        if (trychessList[x - 2][y] != 0) {
                            tempNull[x][y] = getNullPointScore(x, y, c);
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
                        if (trychessList[x + 1][y] != 0) {
                            tempNull[x][y] = getNullPointScore(x, y, c);
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
                        if (trychessList[x + 2][y] != 0) {
                            tempNull[x][y] = getNullPointScore(x, y, c);
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
                        if (trychessList[x][y - 1] != 0) {
                            tempNull[x][y] = getNullPointScore(x, y, c);
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
                        if (trychessList[x][y - 2] != 0) {
                            tempNull[x][y] = getNullPointScore(x, y, c);
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
                        if (trychessList[x][y + 1] != 0) {
                            tempNull[x][y] = getNullPointScore(x, y, c);
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
                        if (trychessList[x][y + 2] != 0) {
                            tempNull[x][y] = getNullPointScore(x, y, c);
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
                        if (trychessList[x + 1][y + 1] != 0) {
                            tempNull[x][y] = getNullPointScore(x, y, c);
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
                        if (trychessList[x + 2][y + 2] != 0) {
                            tempNull[x][y] = getNullPointScore(x, y, c);
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
                        if (trychessList[x - 1][y + 1] != 0) {
                            tempNull[x][y] = getNullPointScore(x, y, c);
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
                        if (trychessList[x - 2][y + 2] != 0) {
                            tempNull[x][y] = getNullPointScore(x, y, c);
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
                        if (trychessList[x + 1][y - 1] != 0) {
                            tempNull[x][y] = getNullPointScore(x, y, c);
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
                        if (trychessList[x + 2][y - 2] != 0) {
                            tempNull[x][y] = getNullPointScore(x, y, c);
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
                        if (trychessList[x - 1][y - 1] != 0) {
                            tempNull[x][y] = getNullPointScore(x, y, c);
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
                        if (trychessList[x - 2][y - 2] != 0) {
                            tempNull[x][y] = getNullPointScore(x, y, c);
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
                        if (trychessList[x - 1][y + 2] != 0) {
                            tempNull[x][y] = getNullPointScore(x, y, c);
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
                        if (trychessList[x - 2][y + 1] != 0) {
                            tempNull[x][y] = getNullPointScore(x, y, c);
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
                        if (trychessList[x + 1][y + 2] != 0) {
                            tempNull[x][y] = getNullPointScore(x, y, c);
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
                        if (trychessList[x + 2][y + 1] != 0) {
                            tempNull[x][y] = getNullPointScore(x, y, c);
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
                        if (trychessList[x + 1][y - 2] != 0) {
                            tempNull[x][y] = getNullPointScore(x, y, c);
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
                        if (trychessList[x + 2][y - 1] != 0) {
                            tempNull[x][y] = getNullPointScore(x, y, c);
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
                        if (trychessList[x - 1][y - 2] != 0) {
                            tempNull[x][y] = getNullPointScore(x, y, c);
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
                        if (trychessList[x - 2][y - 1] != 0) {
                            tempNull[x][y] = getNullPointScore(x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }


//					 else if(x>=2&&x<=12&&y>=2&&y<=12){
//
//						  if(  trychessList[x-1][y]!=0||trychessList[x-2][y]!=0||trychessList[x+1][y]!=0||trychessList[x+2][y]!=0
//						  ||trychessList[x][y-1]!=0||trychessList[x][y-2]!=0||trychessList[x][y+1]!=0||trychessList[x][y+2]!=0
//						  ||trychessList[x-1][y-1]!=0||trychessList[x+1][y+1]!=0||trychessList[x+2][y+2]!=0||trychessList[x-2][y-2]!=0
//						  ||trychessList[x+1][y-1]!=0||trychessList[x-1][y+1]!=0||trychessList[x+2][y-2]!=0||trychessList[x-2][y+2]!=0
//						    )
//						  {    tempNull[x][y]=getNullPointScore(x,y,c);
//						               if(max<tempNull[x][y]) { max=tempNull[x][y]; P[0].x=x;P[0].y=y;}
//						           P[0].count++;
//						  }
//
//
//
//				  }
//				  else if(x>=2&&x<=12&&y==0){
//
//					  if(  trychessList[x-1][y]!=0||trychessList[x-2][y]!=0||trychessList[x+1][y]!=0||trychessList[x+2][y]!=0
//							  ||trychessList[x][y+1]!=0||trychessList[x][y+2]!=0
//							  ||trychessList[x+1][y+1]!=0||trychessList[x+2][y+2]!=0
//							  ||trychessList[x-1][y+1]!=0||trychessList[x-2][y+2]!=0
//							    )
//					  {    tempNull[x][y]=getNullPointScore(x,y,c);
//					  if(max<tempNull[x][y]) { max=tempNull[x][y]; P[0].x=x;P[0].y=y;}
//			           P[0].count++;
//			  }
//				  }
//
//				  else if(x>=2&&x<=12&&y==1){
//
//					  if(  trychessList[x-1][y]!=0||trychessList[x-2][y]!=0||trychessList[x+1][y]!=0||trychessList[x+2][y]!=0
//							  ||trychessList[x][y-1]!=0||trychessList[x][y+1]!=0||trychessList[x][y+2]!=0
//							  ||trychessList[x-1][y-1]!=0||trychessList[x+1][y+1]!=0||trychessList[x+2][y+2]!=0
//							  ||trychessList[x+1][y-1]!=0||trychessList[x-1][y+1]!=0||trychessList[x-2][y+2]!=0
//							    )
//					  {    tempNull[x][y]=getNullPointScore(x,y,c);
//					  if(max<tempNull[x][y]) { max=tempNull[x][y]; P[0].x=x;P[0].y=y;}
//			           P[0].count++;
//			  }
//				  }
//
//                else if(x>=2&&x<=12&&y==13){
//
//                	  if(  trychessList[x-1][y]!=0||trychessList[x-2][y]!=0||trychessList[x+1][y]!=0||trychessList[x+2][y]!=0
//    						  ||trychessList[x][y-1]!=0||trychessList[x][y-2]!=0||trychessList[x][y+1]!=0
//    						  ||trychessList[x-1][y-1]!=0||trychessList[x+1][y+1]!=0||trychessList[x-2][y-2]!=0
//    						  ||trychessList[x+1][y-1]!=0||trychessList[x-1][y+1]!=0||trychessList[x+2][y-2]!=0
//    						    )
//                	  {    tempNull[x][y]=getNullPointScore(x,y,c);
//                	  if(max<tempNull[x][y]) { max=tempNull[x][y]; P[0].x=x;P[0].y=y;}
//			           P[0].count++;
//			  }
//				  }
//
//                else if(x>=2&&x<=12&&y==14){
//
//              	  if(  trychessList[x-1][y]!=0||trychessList[x-2][y]!=0||trychessList[x+1][y]!=0||trychessList[x+2][y]!=0
//  						  ||trychessList[x][y-1]!=0||trychessList[x][y-2]!=0
//  						  ||trychessList[x-1][y-1]!=0||trychessList[x-2][y-2]!=0
//  						  ||trychessList[x+1][y-1]!=0||trychessList[x+2][y-2]!=0
//  						    )
//              	 {    tempNull[x][y]=getNullPointScore(x,y,c);
//                        	if(max<tempNull[x][y]) { max=tempNull[x][y]; P[0].x=x;P[0].y=y;}
//		           P[0].count++;
//		  }
//				  }
//
//                else if(y>=2&&y<=12&&x==0){
//
//                	if(  trychessList[x+1][y]!=0||trychessList[x+2][y]!=0
//    						  ||trychessList[x][y-1]!=0||trychessList[x][y-2]!=0||trychessList[x][y+1]!=0||trychessList[x][y+2]!=0
//    						  ||trychessList[x+1][y+1]!=0||trychessList[x+2][y+2]!=0
//    						  ||trychessList[x+1][y-1]!=0||trychessList[x+2][y-2]!=0
//    						    )
//                	 {    tempNull[x][y]=getNullPointScore(x,y,c);
//                	 if(max<tempNull[x][y]) { max=tempNull[x][y]; P[0].x=x;P[0].y=y;}
//			           P[0].count++;
//			  }
//                }
//
//                else if(y>=2&&y<=12&&x==1){
//
//              	  if(  trychessList[x-1][y]!=0||trychessList[x+1][y]!=0||trychessList[x+2][y]!=0
//  						  ||trychessList[x][y-1]!=0||trychessList[x][y-2]!=0||trychessList[x][y+1]!=0||trychessList[x][y+2]!=0
//  						  ||trychessList[x-1][y-1]!=0||trychessList[x+1][y+1]!=0||trychessList[x+2][y+2]!=0
//  						  ||trychessList[x+1][y-1]!=0||trychessList[x-1][y+1]!=0||trychessList[x+2][y-2]!=0
//  						    )
//              	 {    tempNull[x][y]=getNullPointScore(x,y,c);
//              	if(max<tempNull[x][y]) { max=tempNull[x][y]; P[0].x=x;P[0].y=y;}
//		           P[0].count++;
//		  }
//              }
//
//                else if(y>=2&&y<=12&&x==13){
//
//                	 if(  trychessList[x-1][y]!=0||trychessList[x-2][y]!=0||trychessList[x+1][y]!=0
//   						  ||trychessList[x][y-1]!=0||trychessList[x][y-2]!=0||trychessList[x][y+1]!=0||trychessList[x][y+2]!=0
//   						  ||trychessList[x-1][y-1]!=0||trychessList[x+1][y+1]!=0||trychessList[x-2][y-2]!=0
//   						  ||trychessList[x+1][y-1]!=0||trychessList[x-1][y+1]!=0||trychessList[x-2][y+2]!=0
//   						    )
//                	 {    tempNull[x][y]=getNullPointScore(x,y,c);
//                	 if(max<tempNull[x][y]) { max=tempNull[x][y]; P[0].x=x;P[0].y=y;}
//			           P[0].count++;
//			  }
//                }
//
//                else if(y>=2&&y<=12&&x==14){
//
//               	 if(  trychessList[x-1][y]!=0||trychessList[x-2][y]!=0
//  						  ||trychessList[x][y-1]!=0||trychessList[x][y-2]!=0||trychessList[x][y+1]!=0||trychessList[x][y+2]!=0
//  						  ||trychessList[x-1][y-1]!=0||trychessList[x-2][y-2]!=0
//  						  ||trychessList[x-1][y+1]!=0||trychessList[x-2][y+2]!=0
//  						    )
//               	 {    tempNull[x][y]=getNullPointScore(x,y,c);
//               	if(max<tempNull[x][y]){ max=tempNull[x][y]; P[0].x=x;P[0].y=y;}
//		           P[0].count++;
//		  }
//               }
//

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


    public int getNullPointScore(int xIndex, int yIndex, int c) {

        int continueCount = 0;//连续棋子的个数

        int d = (c == 1 ? -1 : 1);
        int max = 0;
        int chance = 0;

        int score = 0;
        int x;
        int y;
//////1
        for (x = xIndex - 1; x >= 0; x--) {
            if (trychessList[x][yIndex] == c)
                continueCount++;
            else {
                if (trychessList[x][yIndex] == d)
                    chance++;
                break;
            }
        }
        if (x < 0) chance++;
        for (x = xIndex + 1; x <= COLS; x++) {
            if (trychessList[x][yIndex] == c)
                continueCount++;
            else {
                if (trychessList[x][yIndex] == d)
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
            if (trychessList[xIndex][y] == c)
                continueCount++;
            else {
                if (trychessList[xIndex][y] == d)
                    chance++;
                break;
            }
        }
        if (y < 0) chance++;

        for (y = yIndex + 1; y <= ROWS; y++) {
            if (trychessList[xIndex][y] == c)
                continueCount++;
            else {
                if (trychessList[xIndex][y] == d)
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
            if (trychessList[x][y] == c)
                continueCount++;
            else {
                if (trychessList[x][y] == d)
                    chance++;
                break;
            }
        }
        if (y < 0 || x > fifteen - 1) chance++;

        for (x = xIndex - 1, y = yIndex + 1; x >= 0 && y <= ROWS; x--, y++) {
            if (trychessList[x][y] == c)
                continueCount++;
            else {
                if (trychessList[x][y] == d)
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
            if (trychessList[x][y] == c)
                continueCount++;
            else {
                if (trychessList[x][y] == d)
                    chance++;
                break;
            }
        }
        if (x < 0 || y < 0) chance++;

        for (x = xIndex + 1, y = yIndex + 1; x <= COLS && y <= ROWS; x++, y++) {
            if (trychessList[x][y] == c)
                continueCount++;
            else {
                if (trychessList[x][y] == d)
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
            if (trychessList[x][yIndex] == d)
                continueCount++;
            else {
                if (trychessList[x][yIndex] == c)
                    chance++;
                break;
            }
        }
        if (x < 0) chance++;
        for (x = xIndex + 1; x <= COLS; x++) {
            if (trychessList[x][yIndex] == d)
                continueCount++;
            else {
                if (trychessList[x][yIndex] == c)
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
            if (trychessList[xIndex][y] == d)
                continueCount++;
            else {
                if (trychessList[xIndex][y] == c)
                    chance++;
                break;
            }
        }
        if (y < 0) chance++;

        for (y = yIndex + 1; y <= ROWS; y++) {
            if (trychessList[xIndex][y] == d)
                continueCount++;
            else {
                if (trychessList[xIndex][y] == c)
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
            if (trychessList[x][y] == d)
                continueCount++;
            else {
                if (trychessList[x][y] == c)
                    chance++;
                break;
            }
        }
        if (y < 0 || x > fifteen - 1) chance++;

        for (x = xIndex - 1, y = yIndex + 1; x >= 0 && y <= ROWS; x--, y++) {
            if (trychessList[x][y] == d)
                continueCount++;
            else {
                if (trychessList[x][y] == c)
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
            if (trychessList[x][y] == d)
                continueCount++;
            else {
                if (trychessList[x][y] == c)
                    chance++;
                break;
            }
        }
        if (x < 0 || y < 0) chance++;

        for (x = xIndex + 1, y = yIndex + 1; x <= COLS && y <= ROWS; x++, y++) {
            if (trychessList[x][y] == d)
                continueCount++;
            else {
                if (trychessList[x][y] == c)
                    chance++;
                break;
            }
        }
        if (x > fifteen - 1 || y > fifteen - 1) chance++;
        score = getHeNullScore(continueCount, chance);
        if (score > max) max = score;


        return max;


    }


    private int getMyNullScore(int t, int c) {
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

    private int getHeNullScore(int t, int c) {
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


    public int SuanSha(int deepth, int alpha, int beta) {

        deepth--;
        if (deepth == 0) {
            return 0;
        }


        if (deepth % 2 == 0) {

            NullPoint[] p = new NullPoint[225];
            for (int o = 0; o < 225; o++)
                p[o] = new NullPoint();

            NullPoint np;
            NullPoint mark = new NullPoint();

            getSSNewDown(p, mark, computerColor);

            if (deepth == SuanShaDeepth - 1) {
                System.out.println("shu：" + mark.count);
            }

            if (mark.count == 0) {
                return 0;
            }
            for (int i = 0; i < mark.count; i++) {


                np = p[i];
                trychessList[np.x][np.y] = computerColor;
                int t;
                if (mark.sheng == 1) {

                    t = 10000000;


                } else
                    t = SuanSha(deepth, alpha, beta);


                if (t >= beta) {

                    trychessList[np.x][np.y] = 0;
                    return t;
                }

                if (t > alpha) {
                    alpha = t;

                    if (deepth == SuanShaDeepth - 1) {
                        fx = np.x;
                        fy = np.y;
                        if (alpha == 10000000) {
                            System.out.println("准备结束！");
                            break;
                        }
                    }


                }

                if (deepth == SuanShaDeepth - 1) {
                    System.out.println("aa下层分数：" + alpha);
                }

                trychessList[np.x][np.y] = 0;

            }

            return alpha;

        } else {

            NullPoint[] p = new NullPoint[225];
            for (int o = 0; o < 225; o++)
                p[o] = new NullPoint();

            NullPoint np;
            NullPoint mark = new NullPoint();

            getSSNewDown(p, mark, -computerColor);


            if (mark.count == 0) {
                return 0;
            }
            for (int i = 0; i < mark.count; i++) {

                np = p[i];
                trychessList[np.x][np.y] = -computerColor;   //尝试下棋子

                int t;
                if (mark.sheng == 1) {
                    t = -10000000;


                } else
                    t = SuanSha(deepth, alpha, beta);

                if (t <= alpha) {
                    trychessList[np.x][np.y] = 0;

                    return t;
                }


                if (t < beta) {
                    beta = t;
                }

                trychessList[np.x][np.y] = 0;       //取走尝试下的棋子

            }
            return beta;

        }


    }


    private boolean SuanShagetNewDown(Point[] P, int c) {

        int[][] tempNull = new int[15][15];

        P[0].count = 0;
//		if(P[0].count==0)return false;
        int max = 0;

        for (int x = 0; x < fifteen; x++) {
            for (int y = 0; y < fifteen; y++) {
                if (trychessList[x][y] == 0) {
//				    if(x>=2&&x<=12&&y>=2&&y<=12){
//
//						  if(  trychessList[x-1][y]!=0||trychessList[x-2][y]!=0||trychessList[x+1][y]!=0||trychessList[x+2][y]!=0
//						  ||trychessList[x][y-1]!=0||trychessList[x][y-2]!=0||trychessList[x][y+1]!=0||trychessList[x][y+2]!=0
//						  ||trychessList[x-1][y-1]!=0||trychessList[x+1][y+1]!=0||trychessList[x+2][y+2]!=0||trychessList[x-2][y-2]!=0
//						  ||trychessList[x+1][y-1]!=0||trychessList[x-1][y+1]!=0||trychessList[x+2][y-2]!=0||trychessList[x-2][y+2]!=0
//						    )
//						  {    tempNull[x][y]=getNullPointScore(x,y,c);
//						               if(max<tempNull[x][y]) { max=tempNull[x][y]; P[0].x=x;P[0].y=y;}
//						           P[0].count++;
//						  }
//
//
//
//				  }
//				  else if(x>=2&&x<=12&&y==0){
//
//					  if(  trychessList[x-1][y]!=0||trychessList[x-2][y]!=0||trychessList[x+1][y]!=0||trychessList[x+2][y]!=0
//							  ||trychessList[x][y+1]!=0||trychessList[x][y+2]!=0
//							  ||trychessList[x+1][y+1]!=0||trychessList[x+2][y+2]!=0
//							  ||trychessList[x-1][y+1]!=0||trychessList[x-2][y+2]!=0
//							    )
//					  {    tempNull[x][y]=getNullPointScore(x,y,c);
//					  if(max<tempNull[x][y]) { max=tempNull[x][y]; P[0].x=x;P[0].y=y;}
//			           P[0].count++;
//			  }
//				  }
//
//				  else if(x>=2&&x<=12&&y==1){
//
//					  if(  trychessList[x-1][y]!=0||trychessList[x-2][y]!=0||trychessList[x+1][y]!=0||trychessList[x+2][y]!=0
//							  ||trychessList[x][y-1]!=0||trychessList[x][y+1]!=0||trychessList[x][y+2]!=0
//							  ||trychessList[x-1][y-1]!=0||trychessList[x+1][y+1]!=0||trychessList[x+2][y+2]!=0
//							  ||trychessList[x+1][y-1]!=0||trychessList[x-1][y+1]!=0||trychessList[x-2][y+2]!=0
//							    )
//					  {    tempNull[x][y]=getNullPointScore(x,y,c);
//					  if(max<tempNull[x][y]) { max=tempNull[x][y]; P[0].x=x;P[0].y=y;}
//			           P[0].count++;
//			  }
//				  }
//
//                else if(x>=2&&x<=12&&y==13){
//
//                	  if(  trychessList[x-1][y]!=0||trychessList[x-2][y]!=0||trychessList[x+1][y]!=0||trychessList[x+2][y]!=0
//    						  ||trychessList[x][y-1]!=0||trychessList[x][y-2]!=0||trychessList[x][y+1]!=0
//    						  ||trychessList[x-1][y-1]!=0||trychessList[x+1][y+1]!=0||trychessList[x-2][y-2]!=0
//    						  ||trychessList[x+1][y-1]!=0||trychessList[x-1][y+1]!=0||trychessList[x+2][y-2]!=0
//    						    )
//                	  {    tempNull[x][y]=getNullPointScore(x,y,c);
//                	  if(max<tempNull[x][y]) { max=tempNull[x][y]; P[0].x=x;P[0].y=y;}
//			           P[0].count++;
//			  }
//				  }
//
//                else if(x>=2&&x<=12&&y==14){
//
//              	  if(  trychessList[x-1][y]!=0||trychessList[x-2][y]!=0||trychessList[x+1][y]!=0||trychessList[x+2][y]!=0
//  						  ||trychessList[x][y-1]!=0||trychessList[x][y-2]!=0
//  						  ||trychessList[x-1][y-1]!=0||trychessList[x-2][y-2]!=0
//  						  ||trychessList[x+1][y-1]!=0||trychessList[x+2][y-2]!=0
//  						    )
//              	 {    tempNull[x][y]=getNullPointScore(x,y,c);
//                        	if(max<tempNull[x][y]) { max=tempNull[x][y]; P[0].x=x;P[0].y=y;}
//		           P[0].count++;
//		  }
//				  }
//
//                else if(y>=2&&y<=12&&x==0){
//
//                	if(  trychessList[x+1][y]!=0||trychessList[x+2][y]!=0
//    						  ||trychessList[x][y-1]!=0||trychessList[x][y-2]!=0||trychessList[x][y+1]!=0||trychessList[x][y+2]!=0
//    						  ||trychessList[x+1][y+1]!=0||trychessList[x+2][y+2]!=0
//    						  ||trychessList[x+1][y-1]!=0||trychessList[x+2][y-2]!=0
//    						    )
//                	 {    tempNull[x][y]=getNullPointScore(x,y,c);
//                	 if(max<tempNull[x][y]) { max=tempNull[x][y]; P[0].x=x;P[0].y=y;}
//			           P[0].count++;
//			  }
//                }
//
//                else if(y>=2&&y<=12&&x==1){
//
//              	  if(  trychessList[x-1][y]!=0||trychessList[x+1][y]!=0||trychessList[x+2][y]!=0
//  						  ||trychessList[x][y-1]!=0||trychessList[x][y-2]!=0||trychessList[x][y+1]!=0||trychessList[x][y+2]!=0
//  						  ||trychessList[x-1][y-1]!=0||trychessList[x+1][y+1]!=0||trychessList[x+2][y+2]!=0
//  						  ||trychessList[x+1][y-1]!=0||trychessList[x-1][y+1]!=0||trychessList[x+2][y-2]!=0
//  						    )
//              	 {    tempNull[x][y]=getNullPointScore(x,y,c);
//              	if(max<tempNull[x][y]) { max=tempNull[x][y]; P[0].x=x;P[0].y=y;}
//		           P[0].count++;
//		  }
//              }
//
//                else if(y>=2&&y<=12&&x==13){
//
//                	 if(  trychessList[x-1][y]!=0||trychessList[x-2][y]!=0||trychessList[x+1][y]!=0
//   						  ||trychessList[x][y-1]!=0||trychessList[x][y-2]!=0||trychessList[x][y+1]!=0||trychessList[x][y+2]!=0
//   						  ||trychessList[x-1][y-1]!=0||trychessList[x+1][y+1]!=0||trychessList[x-2][y-2]!=0
//   						  ||trychessList[x+1][y-1]!=0||trychessList[x-1][y+1]!=0||trychessList[x-2][y+2]!=0
//   						    )
//                	 {    tempNull[x][y]=getNullPointScore(x,y,c);
//                	 if(max<tempNull[x][y]) { max=tempNull[x][y]; P[0].x=x;P[0].y=y;}
//			           P[0].count++;
//			  }
//                }
//
//                else if(y>=2&&y<=12&&x==14){
//
//               	 if(  trychessList[x-1][y]!=0||trychessList[x-2][y]!=0
//  						  ||trychessList[x][y-1]!=0||trychessList[x][y-2]!=0||trychessList[x][y+1]!=0||trychessList[x][y+2]!=0
//  						  ||trychessList[x-1][y-1]!=0||trychessList[x-2][y-2]!=0
//  						  ||trychessList[x-1][y+1]!=0||trychessList[x-2][y+2]!=0
//  						    )
//               	 {    tempNull[x][y]=getNullPointScore(x,y,c);
//               	if(max<tempNull[x][y]){ max=tempNull[x][y]; P[0].x=x;P[0].y=y;}
//		           P[0].count++;
//		  }
//               }


                    if (isInside(x - 1, y)) {
                        if (trychessList[x - 1][y] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x - 2][y] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x + 1][y] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x + 2][y] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x][y - 1] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x][y - 2] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x][y + 1] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x][y + 2] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x + 1][y + 1] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x + 2][y + 2] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x - 1][y + 1] != 0) {
                            tempNull[x][y] = getNullPointScore(x, y, c);
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
                        if (trychessList[x - 2][y + 2] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x + 1][y - 1] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x + 2][y - 2] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x - 1][y - 1] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x - 2][y - 2] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x - 1][y + 2] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x - 2][y + 1] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x + 1][y + 2] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x + 2][y + 1] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x + 1][y - 2] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x + 2][y - 1] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x - 1][y - 2] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x - 2][y - 1] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
                            if (max < tempNull[x][y]) {
                                max = tempNull[x][y];
                                P[0].x = x;
                                P[0].y = y;
                            }
                            P[0].count++;
                            continue;
                        }
                    }

                    ///***标记
                }

            }
        }


        if (max == 10) {
            P[0].count = 1;
            P[0].sheng = 1;
            return true;
        } else if (max == 9) {
            P[0].count = 1;
            return true;

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

            return true;
        } else if (max >= 4 && max <= 7) {
            int count = 0;
            int u = 7;
            while (u >= 4) {
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
            P[0].count = count;
            return true;
        }
        P[0].count = 0;

        return false;
    }


    public int getPointValueTwo(int a, int b, int c) {

        int x;
        int y;
        int init = 4;

        int continueCount = 1;//连续棋子的个数
        int Count = 0;
        int sign = 0;
        int score = 0;


        boolean lessfive = false;
        int xIndex = a;
        int yIndex = b;
        boolean bb = false;

//1
        int chance = 1;


        if (!isLessFive(xIndex, yIndex, 1, 0, c)) {

            bb = false;

            for (x = xIndex - 1; x >= 0; x--) {
                if (trychessList[x][yIndex] == c)
                    continueCount++;
                else {
                    if (trychessList[x][yIndex] != 0)
                        sign = 1;

                    break;
                }
            }

            if (x < 0) sign = 1;


            for (x = xIndex + 1; x <= COLS; x++) {

                if (trychessList[x][yIndex] == c)
                    continueCount++;


                else {

                    if (chance == 1 && trychessList[x][yIndex] == 0) {
                        chance--;
                        Count = continueCount;
                        continue;
                    }


                    if (sign == 0 && trychessList[x][yIndex] == 0 && chance == 0 && Count != continueCount)
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

                if (trychessList[x][yIndex] == c)
                    continueCount++;


                else {
                    if (trychessList[x][yIndex] != 0)

                        sign = 1;

                    break;
                }
            }
            if (x > COLS) sign = 1;

            for (x = xIndex - 1; x >= 0; x--) {
                if (trychessList[x][yIndex] == c)
                    continueCount++;


                else {
                    if (chance == 1 && trychessList[x][yIndex] == 0) {
                        chance--;
                        Count = continueCount;
                        continue;
                    }


                    if (sign == 0 && trychessList[x][yIndex] == 0 && chance == 0 && Count != continueCount)
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
        if (!isLessFive(xIndex, yIndex, 0, 1, c)) {
            bb = false;

            sign = 0;
            chance = 1;
            continueCount = 1;
            for (y = yIndex - 1; y >= 0; y--) {

                if (trychessList[xIndex][y] == c)
                    continueCount++;


                else {
                    if (trychessList[xIndex][y] != 0)

                        sign = 1;
                    break;
                }
            }
            if (y < 0) sign = 1;


            for (y = yIndex + 1; y <= ROWS; y++) {

                if (trychessList[xIndex][y] == c)
                    continueCount++;


                else {
                    if (chance == 1 && trychessList[xIndex][y] == 0) {
                        chance--;
                        Count = continueCount;
                        continue;
                    }


                    if (sign == 0 && trychessList[xIndex][y] == 0 && chance == 0 && Count != continueCount)
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

                if (trychessList[xIndex][y] == c)
                    continueCount++;


                else {
                    if (trychessList[xIndex][y] != 0)

                        sign = 1;
                    break;
                }

            }
            if (y > ROWS) sign = 1;


            for (y = yIndex - 1; y >= 0; y--) {

                if (trychessList[xIndex][y] == c)
                    continueCount++;


                else {
                    if (chance == 1 && trychessList[xIndex][y] == 0) {
                        chance--;
                        Count = continueCount;
                        continue;
                    }


                    if (sign == 0 && trychessList[xIndex][y] == 0 && chance == 0 && Count != continueCount)
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
        if (!isLessFive(xIndex, yIndex, 1, -1, c)) {

            bb = false;
            sign = 0;
            chance = 1;
            continueCount = 1;

            for (x = xIndex + 1, y = yIndex - 1; y >= 0 && x <= COLS; x++, y--) {

                if (trychessList[x][y] == c)
                    continueCount++;


                else {
                    if (trychessList[xIndex][y] != 0)

                        sign = 1;
                    break;
                }
            }
            if (x > ROWS || y < 0) sign = 1;

            //西南寻找
            for (x = xIndex - 1, y = yIndex + 1; x >= 0 && y <= ROWS; x--, y++) {

                if (trychessList[x][y] == c) {
                    continueCount++;
                } else {
                    if (chance == 1 && trychessList[x][y] == 0) {
                        chance--;
                        Count = continueCount;
                        continue;
                    }

                    if (sign == 0 && trychessList[x][y] == 0 && chance == 0 && Count != continueCount)
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

                if (trychessList[x][y] == c)
                    continueCount++;


                else {
                    if (trychessList[x][y] != 0)

                        sign = 1;
                    break;
                }
            }
            if (x < 0 || y > ROWS) sign = 1;


            for (x = xIndex + 1, y = yIndex - 1; y >= 0 && x <= COLS; x++, y--) {

                if (trychessList[x][y] == c) {
                    continueCount++;

                } else {
                    if (chance == 1 && trychessList[x][y] == 0) {
                        chance--;
                        Count = continueCount;
                        continue;
                    }

                    if (sign == 0 && trychessList[x][y] == 0 && chance == 0 && Count != continueCount)
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

        if (!isLessFive(xIndex, yIndex, 1, 1, c)) {
            bb = false;
            sign = 0;
            chance = 1;
            continueCount = 1;

            for (x = xIndex - 1, y = yIndex - 1; x >= 0 && y >= 0; x--, y--) {

                if (trychessList[x][y] == c)
                    continueCount++;

                else {
                    if (trychessList[x][y] != 0)

                        sign = 1;
                    break;
                }
            }
            if (x < 0 || y < 0) sign = 1;


            for (x = xIndex + 1, y = yIndex + 1; x <= COLS && y <= ROWS; x++, y++) {

                if (trychessList[x][y] == c) {
                    continueCount++;

                } else {
                    if (chance == 1 && trychessList[x][y] == 0) {
                        chance--;
                        Count = continueCount;
                        continue;
                    }

                    if (sign == 0 && trychessList[x][y] == 0 && chance == 0 && Count != continueCount)
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

                if (trychessList[x][y] == c)
                    continueCount++;


                else {
                    if (trychessList[x][y] != 0)

                        sign = 1;
                    break;
                }
            }
            if (x > 14 || y > 14) sign = 1;


            for (x = xIndex - 1, y = yIndex - 1; x >= 0 && y >= 0; x--, y--) {

                if (trychessList[x][y] == c) {
                    continueCount++;
                } else {
                    if (chance == 1 && trychessList[x][y] == 0) {
                        chance--;
                        Count = continueCount;
                        continue;
                    }

                    if (sign == 0 && trychessList[x][y] == 0 && chance == 0 && Count != continueCount)
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


    public int getValueTwo() {
        int Ascore = 0;
        int Bscore = 0;
        int score = 0;


        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {

                if (trychessList[i][j] == 1)
                    Ascore = Ascore + getPointValueTwo(i, j, 1);
                if (trychessList[i][j] == -1) Bscore = Bscore + getPointValueTwo(i, j, -1);


            }
        }


        score = (int) (Ascore - Bscore);

        return score;


    }


    private int getPointScoreTwo(int t, boolean bb) {
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


    public void myPress() {

        pressMark = true;
        String info;
        Point ch;
        String colorName = null;
        for (int x = 0; x < fifteen; x++) {
            for (int y = 0; y < fifteen; y++) {
                trychessList[x][y] = 0;

            }
        }


        isBlack = !isBlack;
        int color;

        if (begin == 1) {
            color = isBlack ? BLACK : WHITE;
            if (isNull(chessList[0].x - 1, chessList[0].y - 1)) {
                allChess[chessList[0].x - 1][chessList[0].y - 1] = color;
                ch = new Point(chessList[0].x - 1, chessList[0].y - 1, isBlack ? Color.black : Color.white);
            } else {
                ch = new Point(chessList[0].x - 1, chessList[0].y + 1, isBlack ? Color.black : Color.white);
                allChess[chessList[0].x - 1][chessList[0].y + 1] = color;
            }
            chessList[chessCount++] = ch;


            begin--;
            noti.setText("用时：0.001秒");
        } else {

            colorName = isBlack ? "黑棋" : "白棋";

            for (int i = 0; i < chessCount; i++) {
                if (chessList[i].getColor() == Color.white)
                    trychessList[chessList[i].getX()][chessList[i].getY()] = 1;
                else trychessList[chessList[i].getX()][chessList[i].getY()] = -1;
            }


            currentime = System.currentTimeMillis();
            System.out.println();
            System.out.println("电脑正在思考.....");


            int r = 0;
            //bestPoints.clear();
            BestPoint bestPoint = new BestPoint();

            r = SuanSha(SuanShaDeepth, alpha, beta);
            bestPoint.x = fx;
            bestPoint.y = fy;
            if (r != 10000000) {
                System.out.println("算杀失败，开启敌人算杀");
                computerColor = 0 - computerColor;
                r = SuanSha(SuanShaDeepth, alpha, beta);
                computerColor = 0 - computerColor;
                bestPoint.x = fx;
                bestPoint.y = fy;
                if (r != 10000000) {
                    System.out.println("对手算杀失败，正常博弈");
                    r = GobangCore.minMax(trychessList, GameDeepth, GameDeepth, alpha, beta, computerColor, bestPoint);
//                    r = MinMax(GameDeepth, alpha, beta);
                }
            }


            highScore = r;

            System.out.println("分数：" + r);
            time = (System.currentTimeMillis() - currentime);

            if (time > Deadline) {

                GameDeepth -= 2;
                for (int x = 0; x < fifteen; x++) {
                    for (int y = 0; y < fifteen; y++) {
                        trychessList[x][y] = 0;
                    }
                }

                for (int i = 0; i < chessCount; i++) {
                    if (chessList[i].getColor() == Color.white)
                        trychessList[chessList[i].getX()][chessList[i].getY()] = 1;
                    else trychessList[chessList[i].getX()][chessList[i].getY()] = -1;
                }


                System.out.println("时间超时，采用快速搜索战略");
                noti.setText("时间超时，采用快速搜索战略");
                currentime = System.currentTimeMillis();

                bestPoints.clear();
                highScore = GobangCore.minMax(trychessList, GameDeepth, GameDeepth, alpha, beta, computerColor, bestPoint);
//                highScore = MinMax(GameDeepth, alpha, beta);


                GameDeepth += 2;
            }


            time = (System.currentTimeMillis() - currentime);
            System.out.println("用时：" + time / 1000.0 + "秒");
            noti.setText("用时：" + time / 1000.0 + "秒");
            //  System.out.println(r);

            //int []a=new int[225];


//      int yy=bestPoints.size();
//      for(int i=0;i<yy;i++){
//    	  if(bestPoints.get(i).score!=highScore){
//    		  bestPoints.remove(i);
//    		  i--;
//    		  yy--;
//    		  //continue;
//    	  }
//      }

//      BestPoint op=bestPoints.get(((int)(Math.random()*(bestPoints.size()-1))));
//      System.out.println("下子的分数："+op.score+"   可以下子数量："+bestPoints.size());
//
            //xIndex=fx;yIndex=fy;
            // xIndex=op.x;
            //  yIndex=op.y;

            fx = bestPoint.x;
            fy = bestPoint.y;
            xIndex = fx;
            yIndex = fy;
            ch = new Point(xIndex, yIndex, isBlack ? Color.black : Color.white);
            chessList[chessCount++] = ch;

            color = isBlack ? BLACK : WHITE;
            allChess[xIndex][yIndex] = color;


        }

        b = GobangJudge.isWin(allChess, xIndex, yIndex, color);
//        b = isWin();
        // new Thread(new Learn5()).start();
        // repaint();//通知系统重新绘制

        repaint();


        //如果胜出则给出提示信息，不能继续下棋
        if (b) {
            String msg = String.format("恭喜，%s赢了！", colorName);
            JOptionPane.showMessageDialog(this, msg);

            gameOver = true;
            restart();
            pressMark = false;
            return;
        }

        isBlack = !isBlack;
        pressMark = false;
        //noti.setText("");

    }

    private boolean isInside(int x, int y) {

        if (x < 0 || x > 14 || y < 0 || y > 14) {
            return false;
        }

        return true;
    }


    public int getSuanShaNullPointScore(int xIndex, int yIndex, int c) {
        int d = 0 - c;
        int max = 0;
        int t = 0;
        ////////////////////////////
        if (!isLessFive(xIndex, yIndex, 1, 0, c)) {
            t = getSSNullPoinScoreDiv(xIndex, yIndex, 1, 0, c, 0);
            if (t > max) {
                max = t;
            }

            t = getSSNullPoinScoreDiv(xIndex, yIndex, -1, 0, c, 0);
            if (t > max) {
                max = t;
            }
        }

        /////////////////////////////
        if (!isLessFive(xIndex, yIndex, 0, 1, c)) {
            t = getSSNullPoinScoreDiv(xIndex, yIndex, 0, 1, c, 0);
            if (t > max) {
                max = t;
            }

            t = getSSNullPoinScoreDiv(xIndex, yIndex, 0, -1, c, 0);
            if (t > max) {
                max = t;
            }
        }

        /////////////////////
        if (!isLessFive(xIndex, yIndex, 1, 1, c)) {
            t = getSSNullPoinScoreDiv(xIndex, yIndex, 1, 1, c, 0);
            if (t > max) {
                max = t;
            }

            t = getSSNullPoinScoreDiv(xIndex, yIndex, -1, -1, c, 0);
            if (t > max) {
                max = t;
            }
        }


        //////////////////////////
        if (!isLessFive(xIndex, yIndex, 1, -1, c)) {
            t = getSSNullPoinScoreDiv(xIndex, yIndex, 1, -1, c, 0);
            if (t > max) {
                max = t;
            }

            t = getSSNullPoinScoreDiv(xIndex, yIndex, -1, 1, c, 0);
            if (t > max) {
                max = t;
            }
        }


        ////////////////////////////对手**********************/
        if (!isLessFive(xIndex, yIndex, 1, 0, d)) {
            t = getSSNullPoinScoreDiv(xIndex, yIndex, 1, 0, d, 1);
            if (t > max) {
                max = t;
            }

            t = getSSNullPoinScoreDiv(xIndex, yIndex, -1, 0, d, 1);
            if (t > max) {
                max = t;
            }
        }

        /////////////////////////////
        if (!isLessFive(xIndex, yIndex, 0, 1, d)) {
            t = getSSNullPoinScoreDiv(xIndex, yIndex, 0, 1, d, 1);
            if (t > max) {
                max = t;
            }

            t = getSSNullPoinScoreDiv(xIndex, yIndex, 0, -1, d, 1);
            if (t > max) {
                max = t;
            }
        }

        /////////////////////
        if (!isLessFive(xIndex, yIndex, 1, 1, d)) {
            t = getSSNullPoinScoreDiv(xIndex, yIndex, 1, 1, d, 1);
            if (t > max) {
                max = t;
            }

            t = getSSNullPoinScoreDiv(xIndex, yIndex, -1, -1, d, 1);
            if (t > max) {
                max = t;
            }
        }


        //////////////////////////
        if (!isLessFive(xIndex, yIndex, 1, -1, d)) {
            t = getSSNullPoinScoreDiv(xIndex, yIndex, 1, -1, d, 1);
            if (t > max) {
                max = t;
            }

            t = getSSNullPoinScoreDiv(xIndex, yIndex, -1, 1, d, 1);
            if (t > max) {
                max = t;
            }
        }


        return max;
    }


    private boolean isLessFive(int xIndex, int yIndex, int xDirect, int yDirect, int c) {

        boolean lessfive = true;
        int init = 4;
        int x;
        int y;
        int color = -c;

        for (x = xIndex + xDirect, y = yIndex + yDirect; x >= 0 && x <= COLS && y >= 0 && y <= COLS; x = x + xDirect, y = y + yDirect) {
            if (trychessList[x][y] != color)
                init--;
            if (trychessList[x][y] == color)
                break;
            if (init <= 0)
                break;
        }

        for (x = xIndex - xDirect, y = yIndex - yDirect; x >= 0 && x <= COLS && y >= 0 && y <= COLS; x = x - xDirect, y = y - yDirect) {
            if (trychessList[x][y] != color)
                init--;
            if (trychessList[x][y] == color)
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


    private int getSSNullPoinScoreDiv(int xIndex, int yIndex, int xDirect, int yDirect, int color, int isMyColor) {
        int x;
        int y;

        int continueCount = 0;//连续棋子的个数
        int Count = 0;
        boolean block = false;
        int t = 0;


        int chance = 0;
        boolean live = true;


        for (x = xIndex + xDirect, y = yIndex + yDirect; x >= 0 && x <= 14 && y >= 0 && y <= 14; x = x + xDirect, y = y + yDirect) {
            if (trychessList[x][y] == color)
                continueCount++;
            else {
                if (trychessList[x][y] != 0) {
                    chance++;
                    block = true;
                }
                break;
            }
        }

        if (x < 0 || x > 14 || y < 0 || y > 14) chance++;


        for (x = xIndex - xDirect, y = yIndex - yDirect; x >= 0 && x <= 14 && y >= 0 && y <= 14; x = x - xDirect, y = y - yDirect) {

            if (trychessList[x][y] == color)
                continueCount++;


            else {

                if (live && trychessList[x][y] == 0) {
                    live = false;
                    Count = continueCount;
                    continue;
                }


                if (trychessList[x][y] != 0)
                    chance++;


                break;

            }
        }
        if (x < 0 || x > 14 || y < 0 || y > 14) chance++;
        if (live) Count = continueCount;
        if (!block && !live && Count == continueCount) chance = 0;

        t = getSSNullScore(Count, continueCount, live, block, chance, isMyColor);


        return t;

//		  int continueCount=0;//连续棋子的个数
//
//		  int d=-color;
//
//		  int chance=0;
//
//		  int score=0;
//		  int x;
//		  int y;
////////1
//		  for(x=xIndex+xDirect,y=yIndex+yDirect;x>=0&&x<=14&&y>=0&&y<=14;x=x+xDirect,y=y+yDirect){
//				if(trychessList[x][y]==color)
//					 continueCount++;
//					else
//					    {if(trychessList[x][y]==d)
//					        chance++;
//					    break;}
//				    }
//		  if(x>14||x<0||y<0||y>14) chance++;
//		  for(x=xIndex-xDirect,y=yIndex-yDirect;x>=0&&x<=14&&y>=0&&y<=14;x=x-xDirect,y=y-yDirect){
//				if(trychessList[x][y]==color)
//					 continueCount++;
//					else
//					    {if(trychessList[x][y]==d)
//					        chance++;
//					    break;}
//				    }
//		  if(x>14||x<0||y<0||y>14) chance++;
//
////		  if(isMyColor==0){
////			  score=getMyNullScore(continueCount,chance);
////		  }
////		  else{
////			  score=getHeNullScore(continueCount,chance);
////		  }
//		  score=getSSNullScore(continueCount,continueCount,true,true,chance,isMyColor);
//		return score;
//
//


    }


    private int getSSNullScore(int count, int continueCount, boolean live, boolean block, int chance, int isMyColor) {
//		if(count>=4){
//			return 10-isMyColor;
//		}
//		else if(count==3&&!block&&!live){
//			return 8-isMyColor;
//		}
//		else if(count==3){
//			return 6-isMyColor;
//		}
//		else if(continueCount==3&&chance==0){
//			return 4-isMyColor;
//		}
//		return 0;
//

        if (count >= 4) {
            return 10 - isMyColor;
        } else if (count == 3 && !block && !live) {
            return 8 - isMyColor;
        } else if (continueCount >= 3) {
            return 6 - isMyColor;
        } else if (continueCount == 2 && chance == 0) {
            return 4 - isMyColor;
        }
        return 0;

//		switch(count){
//		case 0:
//			return 2-isMyColor;
//		case 1:
//			if(chance==2){return 0-isMyColor;}
//			if(chance==1){return 2-isMyColor;}
//			if(chance==0){return 4-isMyColor;}
//		case 2:
//			if(chance==2){return 0-isMyColor;}
//			if(chance==1){return 4-isMyColor;}
//			if(chance==0){return 6-isMyColor;}
//		case 3:
//			if(chance==2){return 0-isMyColor;}
//			if(chance==1){return 6-isMyColor;}
//			if(chance==0){return 8-isMyColor;}
//		case 4:
//			return 10-isMyColor;
//
//	    default:
//	    	return 10-isMyColor;
//		}
    }

//	private boolean getSSNewDown(ArrayList<NullPoint> P, NullPoint mark,int c) {
//
//		int [][]tempNull=new int[15][15];
//
//		mark.count=0;
//
//		int score;
//		int max=0;
//
//		  for(int x=0;x<fifteen;x++){
//			  for(int y=0;y<fifteen;y++){
//				 if(trychessList[x][y]==0) {
//
////					 if(isInside(x-1,y)||isInside(x-2,y)||isInside(x+1,y)||isInside(x+2,y)||isInside(x,y-1)||isInside(x,y-2)
////					   ||isInside(x,y+1)||isInside(x,y+2)||isInside(x+1,y+1)||isInside(x+2,y+2)||isInside(x-1,y+1)||isInside(x-2,y+2)
////					   ||isInside(x+1,y-1)||isInside(x+2,y-2)||isInside(x-1,y-1)||isInside(x-2,y-2)){
////
////							 int score=0;
////							 score=getSuanShaNullPointScore(x,y,c);
////	//					 NullPoint p=new NullPoint(x,y,score);
//////
//////							 int number=0;
//////							 for(number=0;number<P.size();number++){
//////								 if(p.score>P.get(number).score){
//////									 break;
//////								 }
//////							 }
////	//						 P.add(p);
////
////							 tempNull[x][y]=score;
////				             if(max<score)
////				             {
////				            	 max=score; mark.x=x;mark.y=y;
////				             }
////				             mark.count++;
////
////
////					 }
//
//
//					 if(isInside(x-1,y)){
//						 if(trychessList[x-1][y]!=0){
//							 score=0;
//							 score=getNullPointScore(x,y,c);
//						     NullPoint p=new NullPoint(x,y,score);
//							 int number=0;
//							 for(number=0;number<P.size();number++){
//								 if(p.score>P.get(number).score){
//									 break;
//								 }
//							 }
//							 P.add(number,p);
//				             if(max<score)
//				             {
//				            	 max=score; mark.x=x;mark.y=y;
//				             }
//	//			             mark.count++;
//							 continue;
//						 }
//					 }
//					 if(isInside(x-2,y)){
//						 if(trychessList[x-2][y]!=0){
//							 score=0;
//							 score=getNullPointScore(x,y,c);
//						     NullPoint p=new NullPoint(x,y,score);
//							 int number=0;
//							 for(number=0;number<P.size();number++){
//								 if(p.score>P.get(number).score){
//									 break;
//								 }
//							 }
//							 P.add(number,p);
//				             if(max<score)
//				             {
//				            	 max=score; mark.x=x;mark.y=y;
//				             }
//	//			             mark.count++;
//							 continue;
//						 }
//					 }
//					 if(isInside(x+1,y)){
//						 if(trychessList[x+1][y]!=0){
//							 score=0;
//							 score=getNullPointScore(x,y,c);
//						     NullPoint p=new NullPoint(x,y,score);
//							 int number=0;
//							 for(number=0;number<P.size();number++){
//								 if(p.score>P.get(number).score){
//									 break;
//								 }
//							 }
//							 P.add(number,p);
//				             if(max<score)
//				             {
//				            	 max=score; mark.x=x;mark.y=y;
//				             }
//	//			             mark.count++;
//							 continue;
//						 }
//					 }
//					 if(isInside(x+2,y)){
//						 if(trychessList[x+2][y]!=0){
//							 score=0;
//							 score=getNullPointScore(x,y,c);
//						     NullPoint p=new NullPoint(x,y,score);
//							 int number=0;
//							 for(number=0;number<P.size();number++){
//								 if(p.score>P.get(number).score){
//									 break;
//								 }
//							 }
//							 P.add(number,p);
//				             if(max<score)
//				             {
//				            	 max=score; mark.x=x;mark.y=y;
//				             }
//	//			             mark.count++;
//							 continue;
//						 }
//					 }
//
//					 if(isInside(x,y-1)){
//						 if(trychessList[x][y-1]!=0){
//							 score=0;
//							 score=getNullPointScore(x,y,c);
//						     NullPoint p=new NullPoint(x,y,score);
//							 int number=0;
//							 for(number=0;number<P.size();number++){
//								 if(p.score>P.get(number).score){
//									 break;
//								 }
//							 }
//							 P.add(number,p);
//				             if(max<score)
//				             {
//				            	 max=score; mark.x=x;mark.y=y;
//				             }
//	//			             mark.count++;
//							 continue;
//						 }
//					 }
//
//					 if(isInside(x,y-2)){
//						 if(trychessList[x][y-2]!=0){
//							 score=0;
//							 score=getNullPointScore(x,y,c);
//						     NullPoint p=new NullPoint(x,y,score);
//							 int number=0;
//							 for(number=0;number<P.size();number++){
//								 if(p.score>P.get(number).score){
//									 break;
//								 }
//							 }
//							 P.add(number,p);
//				             if(max<score)
//				             {
//				            	 max=score; mark.x=x;mark.y=y;
//				             }
//	//			             mark.count++;
//							 continue;
//						 }
//					 }
//
//					 if(isInside(x,y+1)){
//						 if(trychessList[x][y+1]!=0){
//							 score=0;
//							 score=getNullPointScore(x,y,c);
//						     NullPoint p=new NullPoint(x,y,score);
//							 int number=0;
//							 for(number=0;number<P.size();number++){
//								 if(p.score>P.get(number).score){
//									 break;
//								 }
//							 }
//							 P.add(number,p);
//				             if(max<score)
//				             {
//				            	 max=score; mark.x=x;mark.y=y;
//				             }
//	//			             mark.count++;
//							 continue;
//						 }
//					 }
//
//					 if(isInside(x,y+2)){
//						 if(trychessList[x][y+2]!=0){
//							 score=0;
//							 score=getNullPointScore(x,y,c);
//						     NullPoint p=new NullPoint(x,y,score);
//							 int number=0;
//							 for(number=0;number<P.size();number++){
//								 if(p.score>P.get(number).score){
//									 break;
//								 }
//							 }
//							 P.add(number,p);
//				             if(max<score)
//				             {
//				            	 max=score; mark.x=x;mark.y=y;
//				             }
//	//			             mark.count++;
//							 continue;
//						 }
//					 }
//
//
//
//					 if(isInside(x+1,y+1)){
//						 if(trychessList[x+1][y+1]!=0){
//							 score=0;
//							 score=getNullPointScore(x,y,c);
//						     NullPoint p=new NullPoint(x,y,score);
//							 int number=0;
//							 for(number=0;number<P.size();number++){
//								 if(p.score>P.get(number).score){
//									 break;
//								 }
//							 }
//							 P.add(number,p);
//				             if(max<score)
//				             {
//				            	 max=score; mark.x=x;mark.y=y;
//				             }
//	//			             mark.count++;
//							 continue;
//						 }
//					 }
//
//
//
//					 if(isInside(x+2,y+2)){
//						 if(trychessList[x+2][y+2]!=0){
//							 score=0;
//							 score=getNullPointScore(x,y,c);
//						     NullPoint p=new NullPoint(x,y,score);
//							 int number=0;
//							 for(number=0;number<P.size();number++){
//								 if(p.score>P.get(number).score){
//									 break;
//								 }
//							 }
//							 P.add(number,p);
//				             if(max<score)
//				             {
//				            	 max=score; mark.x=x;mark.y=y;
//				             }
//	//			             mark.count++;
//							 continue;
//						 }
//					 }
//
//
//					 if(isInside(x-1,y+1)){
//						 if(trychessList[x-1][y+1]!=0){
//							 score=0;
//							 score=getNullPointScore(x,y,c);
//						     NullPoint p=new NullPoint(x,y,score);
//							 int number=0;
//							 for(number=0;number<P.size();number++){
//								 if(p.score>P.get(number).score){
//									 break;
//								 }
//							 }
//							 P.add(number,p);
//				             if(max<score)
//				             {
//				            	 max=score; mark.x=x;mark.y=y;
//				             }
//	//			             mark.count++;
//							 continue;
//						 }
//					 }
//
//
//					 if(isInside(x-2,y+2)){
//						 if(trychessList[x-2][y+2]!=0){
//							 score=0;
//							 score=getNullPointScore(x,y,c);
//						     NullPoint p=new NullPoint(x,y,score);
//							 int number=0;
//							 for(number=0;number<P.size();number++){
//								 if(p.score>P.get(number).score){
//									 break;
//								 }
//							 }
//							 P.add(number,p);
//				             if(max<score)
//				             {
//				            	 max=score; mark.x=x;mark.y=y;
//				             }
//	//			             mark.count++;
//							 continue;
//						 }
//					 }
//
//					 if(isInside(x+1,y-1)){
//						 if(trychessList[x+1][y-1]!=0){
//							 score=0;
//							 score=getNullPointScore(x,y,c);
//						     NullPoint p=new NullPoint(x,y,score);
//							 int number=0;
//							 for(number=0;number<P.size();number++){
//								 if(p.score>P.get(number).score){
//									 break;
//								 }
//							 }
//							 P.add(number,p);
//				             if(max<score)
//				             {
//				            	 max=score; mark.x=x;mark.y=y;
//				             }
//	//			             mark.count++;
//							 continue;
//						 }
//					 }
//
//					 if(isInside(x+2,y-2)){
//						 if(trychessList[x+2][y-2]!=0){
//							 score=0;
//							 score=getNullPointScore(x,y,c);
//						     NullPoint p=new NullPoint(x,y,score);
//							 int number=0;
//							 for(number=0;number<P.size();number++){
//								 if(p.score>P.get(number).score){
//									 break;
//								 }
//							 }
//							 P.add(number,p);
//				             if(max<score)
//				             {
//				            	 max=score; mark.x=x;mark.y=y;
//				             }
//	//			             mark.count++;
//							 continue;
//						 }
//					 }
//
//
//					 if(isInside(x-1,y-1)){
//						 if(trychessList[x-1][y-1]!=0){
//							 score=0;
//							 score=getNullPointScore(x,y,c);
//						     NullPoint p=new NullPoint(x,y,score);
//							 int number=0;
//							 for(number=0;number<P.size();number++){
//								 if(p.score>P.get(number).score){
//									 break;
//								 }
//							 }
//							 P.add(number,p);
//				             if(max<score)
//				             {
//				            	 max=score; mark.x=x;mark.y=y;
//				             }
//	//			             mark.count++;
//							 continue;
//						 }
//					 }
//
//
//					 if(isInside(x-2,y-2)){
//						 if(trychessList[x-2][y-2]!=0){
//							 score=0;
//							 score=getNullPointScore(x,y,c);
//						     NullPoint p=new NullPoint(x,y,score);
//							 int number=0;
//							 for(number=0;number<P.size();number++){
//								 if(p.score>P.get(number).score){
//									 break;
//								 }
//							 }
//							 P.add(number,p);
//				             if(max<score)
//				             {
//				            	 max=score; mark.x=x;mark.y=y;
//				             }
//	//			             mark.count++;
//							 continue;
//						 }
//					 }
//
//					 if(isInside(x-1,y+2)){
//					 if(trychessList[x-1][y+2]!=0){
//						 score=0;
//						 score=getNullPointScore(x,y,c);
//					     NullPoint p=new NullPoint(x,y,score);
//						 int number=0;
//						 for(number=0;number<P.size();number++){
//							 if(p.score>P.get(number).score){
//								 break;
//							 }
//						 }
//						 P.add(number,p);
//			             if(max<score)
//			             {
//			            	 max=score; mark.x=x;mark.y=y;
//			             }
////			             mark.count++;
//						 continue;
//					 }
//				 }
//
//				 if(isInside(x-2,y+1)){
//					 if(trychessList[x-2][y+1]!=0){
//						 tempNull[x][y]=getNullPointScore(x,y,c);
//						 score=0;
//						 score=getNullPointScore(x,y,c);
//					     NullPoint p=new NullPoint(x,y,score);
//						 int number=0;
//						 for(number=0;number<P.size();number++){
//							 if(p.score>P.get(number).score){
//								 break;
//							 }
//						 }
//						 P.add(number,p);
//			             if(max<score)
//			             {
//			            	 max=score; mark.x=x;mark.y=y;
//			             }
////			             mark.count++;
//						 continue;
//					 }
//				 }
//
//					 if(isInside(x+1,y+2)){
//					 if(trychessList[x+1][y+2]!=0){
//						 score=0;
//						 score=getNullPointScore(x,y,c);
//					     NullPoint p=new NullPoint(x,y,score);
//						 int number=0;
//						 for(number=0;number<P.size();number++){
//							 if(p.score>P.get(number).score){
//								 break;
//							 }
//						 }
//						 P.add(number,p);
//			             if(max<score)
//			             {
//			            	 max=score; mark.x=x;mark.y=y;
//			             }
////			             mark.count++;
//						 continue;
//					 }
//				 }
//
//				 if(isInside(x+2,y+1)){
//					 if(trychessList[x+2][y+1]!=0){
//						 tempNull[x][y]=getNullPointScore(x,y,c);
//			             if(max<tempNull[x][y]) { max=tempNull[x][y]; P[0].x=x;P[0].y=y;}
//			             P[0].count++;
//						 continue;
//					 }
//				 }
//					 if(isInside(x+1,y-2)){
//						 if(trychessList[x+1][y-2]!=0){
//							 score=0;
//							 score=getNullPointScore(x,y,c);
//						     NullPoint p=new NullPoint(x,y,score);
//							 int number=0;
//							 for(number=0;number<P.size();number++){
//								 if(p.score>P.get(number).score){
//									 break;
//								 }
//							 }
//							 P.add(number,p);
//				             if(max<score)
//				             {
//				            	 max=score; mark.x=x;mark.y=y;
//				             }
//	//			             mark.count++;
//							 continue;
//						 }
//					 }
//
//					 if(isInside(x+2,y-1)){
//						 if(trychessList[x+2][y-1]!=0){
//							 score=0;
//							 score=getNullPointScore(x,y,c);
//						     NullPoint p=new NullPoint(x,y,score);
//							 int number=0;
//							 for(number=0;number<P.size();number++){
//								 if(p.score>P.get(number).score){
//									 break;
//								 }
//							 }
//							 P.add(number,p);
//				             if(max<score)
//				             {
//				            	 max=score; mark.x=x;mark.y=y;
//				             }
//	//			             mark.count++;
//							 continue;
//						 }
//					 }
//
//					 if(isInside(x-1,y-2)){
//						 if(trychessList[x-1][y-2]!=0){
//							 score=0;
//							 score=getNullPointScore(x,y,c);
//						     NullPoint p=new NullPoint(x,y,score);
//							 int number=0;
//							 for(number=0;number<P.size();number++){
//								 if(p.score>P.get(number).score){
//									 break;
//								 }
//							 }
//							 P.add(number,p);
//				             if(max<score)
//				             {
//				            	 max=score; mark.x=x;mark.y=y;
//				             }
//	//			             mark.count++;
//							 continue;
//						 }
//					 }
//
//					 if(isInside(x-2,y-1)){
//						 if(trychessList[x-2][y-1]!=0){
//							 score=0;
//							 score=getNullPointScore(x,y,c);
//						     NullPoint p=new NullPoint(x,y,score);
//							 int number=0;
//							 for(number=0;number<P.size();number++){
//								 if(p.score>P.get(number).score){
//									 break;
//								 }
//							 }
//							 P.add(number,p);
//				             if(max<score)
//				             {
//				            	 max=score; mark.x=x;mark.y=y;
//				             }
//	//			             mark.count++;
//							 continue;
//						 }
//					 }
//
//
//				 }
//			 }
//		 }
//
//
//
//
//
//
//
//		  if(max==10){
//			mark.count=1;
//			mark.sheng=1;
//			  return true;
//		  }
//
//		  else if(max==9){
//			  mark.count=1;
//			  return true;
//
//		  }
//
//
//		  else if(max==8){
//
//			mark.count=1;
//
//						return true;
//				}
//		  else if(max>=4&&max<=7){
//			 // int count=0;
//			  int i=0;
//			  int size=P.size();
//			  for(i=0;i<size;i++){
//				  if(P.get(i).score<4){
//					  break;
//				  }
//			  }
//				mark.count=i;
//				return true;
//		  }
//
//		  mark.count=0;
//		 return false;
//	}


    private boolean getSSNewDown(NullPoint[] P, NullPoint mark, int c) {

        int[][] tempNull = new int[15][15];

        mark.count = 0;


        int max = 0;

        for (int x = 0; x < fifteen; x++) {
            for (int y = 0; y < fifteen; y++) {
                if (trychessList[x][y] == 0) {

//					 if(isInside(x-1,y)||isInside(x-2,y)||isInside(x+1,y)||isInside(x+2,y)||isInside(x,y-1)||isInside(x,y-2)
//					   ||isInside(x,y+1)||isInside(x,y+2)||isInside(x+1,y+1)||isInside(x+2,y+2)||isInside(x-1,y+1)||isInside(x-2,y+2)
//					   ||isInside(x+1,y-1)||isInside(x+2,y-2)||isInside(x-1,y-1)||isInside(x-2,y-2)){
//
//							 int score=0;
//							 score=getNullPointScore(x,y,c);
//	//					 NullPoint p=new NullPoint(x,y,score);
////
////							 int number=0;
////							 for(number=0;number<P.size();number++){
////								 if(p.score>P.get(number).score){
////									 break;
////								 }
////							 }
//	//						 P.add(p);
//
//							 tempNull[x][y]=score;
//				             if(max<score)
//				             {
//				            	 max=score; mark.x=x;mark.y=y;
//				             }
//				             mark.count++;
//
//
//					 }


                    if (isInside(x - 1, y)) {
                        if (trychessList[x - 1][y] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x - 2][y] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x + 1][y] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x + 2][y] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x][y - 1] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x][y - 2] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x][y + 1] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x][y + 2] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x + 1][y + 1] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x + 2][y + 2] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x - 1][y + 1] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x - 2][y + 2] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x + 1][y - 1] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x + 2][y - 2] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x - 1][y - 1] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x - 2][y - 2] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x - 1][y + 2] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x - 2][y + 1] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x + 1][y + 2] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x + 2][y + 1] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x + 1][y - 2] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x + 2][y - 1] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x - 1][y - 2] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
                        if (trychessList[x - 2][y - 1] != 0) {
                            tempNull[x][y] = getSuanShaNullPointScore(x, y, c);
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
            mark.count = 1;
            mark.sheng = 1;
            return true;
        } else if (max == 9) {
            mark.count = 1;
            return true;

        } else if (max == 8) {

            mark.count = 1;

            return true;
        } else if (max >= 4 && max <= 7) {
            int count = 0;
            int u = 7;
            while (u >= 4) {
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
            mark.count = count;
            return true;
        }

        mark.count = 0;
        return false;
    }

}