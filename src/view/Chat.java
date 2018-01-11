/*
 * 聊天界面，要处于一直读取信息的状态，是线程
 */
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.sql.*;
import java.text.*;
import common.*;
import model.*;

public class Chat extends JFrame implements ActionListener,WindowListener{
	
	ManagerChat mchat;
	JTextArea jta;
	JTextField jtf;
	JButton jb;
	JPanel jp;
	Message m;
	String myid;
	String friend;
	MyClientConnect mc;
	Socket s;
	ObjectInputStream ois;
	
	public Chat(String myid,String friend,Socket s,ManagerChat mchat)
	{
		this.mchat=mchat;
		this.s=s;
		this.myid=myid;
		this.friend=friend;
		jp=new JPanel();
		jta=new JTextArea();
		jta.setEditable(false);//不可编辑
		jtf=new JTextField(15);
		jb=new JButton("发送");
		jb.addActionListener(this);
		jp.add(jtf);
		jp.add(jb);
		this.add(jta,"Center");
		this.add(jp,"South");
		
		this.setTitle(this.myid+" 正在和  "+this.friend+"  聊天"); // 设置窗体标题
		this.setIconImage((new ImageIcon("images/qq.gif")).getImage());// 设置图标
		this.setSize(300, 200); // 以像素为单位设置窗体长宽
		this.setLocation(200, 200); // 设置初始横纵位置
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置关闭窗口即退出
		this.setResizable(false); // 允许(true)/禁止(false)用户改变窗体大小
		this.setVisible(true); // 显示
		this.addWindowListener(this);
	}

	public void receive(String s)
	{
		this.jta.append(s);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb)
		{
			this.m=new Message();
			this.m.setMesType(4);
			this.m.setSender(this.myid);
			this.m.setGetter(this.friend);
			this.m.setCon(this.jtf.getText());
			java.util.Date nowdate = new java.util.Date();//获取当前时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化
			this.m.setTime(df.format(nowdate));//传格式化的时间，String格式
//			this.m.setTime(new java.util.Date().toString());
			this.mc=new MyClientConnect();
			this.mc.setSocket(this.s);
			this.mc.SendInfoToServer(this.m);
			String iofo="我对： "+m.getGetter()+" 说："+m.getCon()+"("+m.getTime()+")\r\n";
			this.jta.append(iofo);
			jtf.setText("");
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
//		System.out.println("窗口激活");
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
//		System.out.println("窗口关闭");
//		this.mchat.removeChat(this.friend);
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
//		System.out.println("窗口正在关闭");
		this.mchat.removeChat(this.friend);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
//		System.out.println("窗口取消激活");
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub


	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
//		System.out.println("窗口打开");
		
	}
}