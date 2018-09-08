package com.zylear.publish.web.gobang;

import com.zylear.publish.web.gobang.core.GobangCore;
import com.zylear.publish.web.gobang.core.GobangJudge;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class GobangPanel extends JPanel implements MouseListener {


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
    public int SuanShaDeepth = 13;
    public boolean gamestart = false;
    boolean isVisual = false;
    private int Deadline = 60000;
    private double currentime;

    private ArrayList<BestPoint> bestPoints = new ArrayList<>();
    private int highScore;

    int begin = 1;
    Point[] chessList = new Point[(ROWS + 1) * (COLS + 1)];//初始每个数组元素为null


    int[][] tryChess = new int[15][15];
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

    public GobangPanel() {
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

                GobangPanel.this.myPress();

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
        score = score + getScore(continueCount, chance);


///////2

        continueCount = 1;
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
        score = score + getScore(continueCount, chance);


/////////3


        continueCount = 1;
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
        score = score + getScore(continueCount, chance);


        //////////4

        continueCount = 1;
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
        score = score + getScore(continueCount, chance);

        return score;
    }


    private int getValue() {

        int Ascore = 0;
        int Bscore = 0;
        int score = 0;


        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {

                if (tryChess[i][j] == 1)
                    Ascore = Ascore + getPointValue(i, j, 1);
                if (tryChess[i][j] == -1) Bscore = Bscore + getPointValue(i, j, -1);


            }
        }


        score = (int) (Ascore - Bscore);

        return score;


    }


    public void myPress() {

        pressMark = true;
        String info;
        Point ch;
        String colorName = null;
        for (int x = 0; x < fifteen; x++) {
            for (int y = 0; y < fifteen; y++) {
                tryChess[x][y] = 0;

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
                    tryChess[chessList[i].getX()][chessList[i].getY()] = 1;
                else tryChess[chessList[i].getX()][chessList[i].getY()] = -1;
            }

            BestPoint bestPoint = new BestPoint();
            long currentTime = System.currentTimeMillis();
            System.out.println();
            System.out.println("电脑正在思考.....");
            GobangCore.calculate(tryChess, GameDeepth, SuanShaDeepth, computerColor, bestPoint);
            time = (System.currentTimeMillis() - currentTime);
            System.out.println("用时：" + time / 1000.0 + "秒");


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


}