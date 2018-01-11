/*
 * ������棬Ҫ����һֱ��ȡ��Ϣ��״̬�����߳�
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
		jta.setEditable(false);//���ɱ༭
		jtf=new JTextField(15);
		jb=new JButton("����");
		jb.addActionListener(this);
		jp.add(jtf);
		jp.add(jb);
		this.add(jta,"Center");
		this.add(jp,"South");
		
		this.setTitle(this.myid+" ���ں�  "+this.friend+"  ����"); // ���ô������
		this.setIconImage((new ImageIcon("images/qq.gif")).getImage());// ����ͼ��
		this.setSize(300, 200); // ������Ϊ��λ���ô��峤��
		this.setLocation(200, 200); // ���ó�ʼ����λ��
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���ùرմ��ڼ��˳�
		this.setResizable(false); // ����(true)/��ֹ(false)�û��ı䴰���С
		this.setVisible(true); // ��ʾ
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
			java.util.Date nowdate = new java.util.Date();//��ȡ��ǰʱ��
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//��ʽ��
			this.m.setTime(df.format(nowdate));//����ʽ����ʱ�䣬String��ʽ
//			this.m.setTime(new java.util.Date().toString());
			this.mc=new MyClientConnect();
			this.mc.setSocket(this.s);
			this.mc.SendInfoToServer(this.m);
			String iofo="�Ҷԣ� "+m.getGetter()+" ˵��"+m.getCon()+"("+m.getTime()+")\r\n";
			this.jta.append(iofo);
			jtf.setText("");
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
//		System.out.println("���ڼ���");
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
//		System.out.println("���ڹر�");
//		this.mchat.removeChat(this.friend);
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
//		System.out.println("�������ڹر�");
		this.mchat.removeChat(this.friend);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
//		System.out.println("����ȡ������");
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
//		System.out.println("���ڴ�");
		
	}
}