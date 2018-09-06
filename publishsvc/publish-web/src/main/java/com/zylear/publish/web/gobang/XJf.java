package com.zylear.publish.web.gobang;

import java.net.URL;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class XJf extends JFrame{


	public Learn5 start = new Learn5();


	public XJf(String str){
		super(str);
		Container con=new Container();

		this.setContentPane(con);

		con.setLayout(new BorderLayout());

		JPanel cpanel=new JPanel();
		cpanel.setLayout(new FlowLayout());
		con.add(cpanel, BorderLayout.SOUTH);

		con.add(start);
		setBounds(700,150,555,680);
		this.setResizable(false);

		JButton btn1=new JButton("重新开始");
		JButton btn2=new JButton("悔        棋");
		JButton btn3=new JButton("退出游戏");

		cpanel.add(btn1);
		cpanel.add(btn2);
		cpanel.add(btn3);
		//pack();

		JMenu menu=new JMenu("游戏");
		JMenu menu2=new JMenu("设置");
		JMenuItem item1=new JMenuItem("重新开始");
		JMenuItem item2=new JMenuItem("悔        棋");
		JMenuItem item3=new JMenuItem("退出游戏");
		JMenuItem item4=new JMenuItem("设置背景");
		JMenuItem item5=new JMenuItem("关闭背景");
		JMenuItem item6=new JMenuItem("设        置");


		menu.add(item1);
		menu.add(item2);
		menu.add(item3);
		//menu.add(item6);




		menu2.add(item4);
		menu2.add(item5);

		JMenuBar menubar=new JMenuBar();

		menubar.add(menu);
		menubar.add(menu2);

		setJMenuBar(menubar);
		menubar.setVisible(true);




		class MyEvent implements ActionListener{
			public void actionPerformed(ActionEvent e){
				Object obj=e.getSource();//获得事件源
				if(obj==btn1||obj==item1){
					//重新开始
					//JFiveFrame.this内部类引用外部类
					//System.out.println("重新开始");
					start.restart();
					start.repaint();
				}

				else if (obj==btn2||obj==item2){
					// System.out.println("悔棋...");
					JOptionPane.showMessageDialog(start, "不想给你悔棋");
					// start.goback();


				}

				else if (obj==btn3||obj==item3)
					System.exit(0);
				else if (obj==item4)
				{
					start.paint=true;
					start.repaint();
				}
				else if (obj==item5)
				{
					start.paint=false;
					start.repaint();
				}



			}

		}
		MyEvent my =new MyEvent();

		item1.addActionListener(my);
		item2.addActionListener(my);
		item3.addActionListener(my);
		item4.addActionListener(my);
		item5.addActionListener(my);


		btn1.addActionListener(my);
		btn2.addActionListener(my);
		btn3.addActionListener(my);



	}
	// java.net.URL file1 = getClass().getResource("d:java\\woke\\音乐\\Ring03.wav");




	public static void main(String[] args) {
		// TODO Auto-generated method stub



		//   XThread xx;
		//   xx=new XThread();
		//  xx.start();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					XJf frm=new XJf("五子棋游戏");
					frm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});





	}

	// public void aa(){


	//   XThread xx;
	//   xx=new XThread();
	//  xx.start();

	//}

}


class XThread extends Thread{

	public void run(){

		for(int a=0;a<3;a++)
		{
			JDialog j=new JDialog();
			j.setBounds(200,200,400,300);
			j.setVisible(true);
			// System.out.println("df");
		}
	}

}