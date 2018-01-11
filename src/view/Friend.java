/*
 * 我的好友列表，包括陌生人、黑名单
 */
package view;

import javax.swing.*;

import common.*;
import model.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Friend extends JFrame implements ActionListener,MouseListener,Runnable,WindowListener{

	int friendcount = 50;// 我的好友个数
	int strangercount = 20;// 陌生人个数
	int blackcount = 20;// 黑名单个数
	ManagerChat mchat;
	String myid;
	public Socket s;
	ObjectInputStream ois;
	MyClientConnect mc=new MyClientConnect();
	
	CardLayout cl;
	// 第一张卡片（我的好友）
	JPanel jphy1, jphy2, jphy3;
	JButton jphy_jb1, jphy_jb2, jphy_jb3;
	JScrollPane jsphy;
	JLabel[] jlbhys;
	// 第二张卡片（陌生人）
	JPanel jpms1, jpms2, jpms3;
	JButton jpms_jb1, jpms_jb2, jpms_jb3;
	JScrollPane jspms;
	JLabel[] jlbmss;
	// 第二张卡片（黑名单）
	JPanel jphm1, jphm2, jphm3;
	JButton jphm_jb1, jphm_jb2, jphm_jb3;
	JScrollPane jsphm;
	JLabel[] jlbhms;

	public void ThirdCard()// 处理第三张卡片
	{
		// 第三张卡片，显示好友列表
		jphm1 = new JPanel(new BorderLayout());
		jphm2 = new JPanel(new GridLayout(blackcount, 1, 4, 4));// friendcount行1列，行间距列间距均为4
		jsphm = new JScrollPane(jphm2);// 创建同时把jphy2放入
		jphm3 = new JPanel(new GridLayout(3, 1));
		jphm_jb1 = new JButton("我的好友");
		jphm_jb1.addActionListener(this);
		jphm_jb2 = new JButton("陌生人");
		jphm_jb2.addActionListener(this);
		jphm_jb3 = new JButton("黑名单");
		jphm3.add(jphm_jb1);
		jphm3.add(jphm_jb2);
		jphm3.add(jphm_jb3);
		jlbhms = new JLabel[blackcount];
		for (int i = 0; i < jlbhms.length; i++) {
			jlbhms[i] = new JLabel(i + 1 + "", new ImageIcon("images/mm.jpg"), JLabel.LEFT);
			jphm2.add(jlbhms[i]);
		}

		jphm1.add(jphm3, "North");
		jphm1.add(jsphm, "Center");
	}
	
	public void SecondCard()// 处理第二张卡片
	{
		// 第二张卡片，显示好友列表
		jpms1 = new JPanel(new BorderLayout());
		jpms2 = new JPanel(new GridLayout(strangercount, 1, 4, 4));// friendcount行1列，行间距列间距均为4
		jspms = new JScrollPane(jpms2);// 创建同时把jphy2放入
		jpms3 = new JPanel(new GridLayout(2, 1));
		jpms_jb1 = new JButton("我的好友");
		jpms_jb1.addActionListener(this);
		jpms_jb2 = new JButton("陌生人");
		jpms_jb3 = new JButton("黑名单");
		jpms_jb3.addActionListener(this);
		jpms3.add(jpms_jb1);
		jpms3.add(jpms_jb2);
		jlbmss = new JLabel[strangercount];
		for (int i = 0; i < jlbmss.length; i++) {
			jlbmss[i] = new JLabel(i + 1 + "", new ImageIcon("images/mm.jpg"), JLabel.LEFT);
		}

		jpms1.add(jpms3, "North");
		jpms1.add(jspms, "Center");
		jpms1.add(jpms_jb3, "South");
	}

	public void FirstCard()// 处理第一张卡片
	{
		// 第一张卡片，显示陌生人
		jphy1 = new JPanel(new BorderLayout());
		jphy2 = new JPanel(new GridLayout(friendcount, 1, 4, 4));// friendcount行1列，行间距列间距均为4
		jsphy = new JScrollPane(jphy2);// 创建同时把jphy2放入
		jphy3 = new JPanel(new GridLayout(2, 1));
		jphy_jb1 = new JButton("我的好友");
		jphy_jb2 = new JButton("陌生人");
		jphy_jb2.addActionListener(this);
		jphy_jb3 = new JButton("黑名单");
		jphy_jb3.addActionListener(this);
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		jlbhys = new JLabel[friendcount];
		for (int i = 0; i < jlbhys.length; i++) {
			jlbhys[i] = new JLabel(i + 1 + "", new ImageIcon("images/mm.jpg"), JLabel.LEFT);
			jlbhys[i].addMouseListener(this);
			jlbhys[i].setEnabled(false);//默认未登录，不可用，变灰
			if(jlbhys[i].getText().equals(this.myid))jlbhys[i].setEnabled(true);//默认只有自己登录在线
			jphy2.add(jlbhys[i]);
			
		}

		jphy1.add(jphy_jb1, "North");
		jphy1.add(jsphy, "Center");
		jphy1.add(jphy3, "South");
	}

	public Friend(String myid,Socket s) {
		this.mc.setSocket(s);
		this.mchat=new ManagerChat();
		this.myid=myid;
		this.s=s;
		cl=new CardLayout();
		this.setLayout(cl);//整个JFrame做成卡片布局
		this.FirstCard();
		this.SecondCard();
		this.ThirdCard();
		this.add(jphy1,"1");
		this.add(jpms1,"2");
		this.add(jphm1,"3");

		this.setTitle(this.myid); // 设置窗体标题
		this.setIconImage((new ImageIcon("images/qq.gif")).getImage());// 设置图标
		this.setSize(100, 400); // 以像素为单位设置窗体长宽
		this.setLocation(200, 200); // 设置初始横纵位置
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置关闭窗口即退出
		this.setResizable(true); // 允许用户改变窗体大小
		this.setVisible(true); // 显示
		this.addWindowListener(this);

//		try {
//			Message mdl=new Message();
//			mdl.setMesType(5);
//			ObjectOutputStream oos=new ObjectOutputStream(this.s.getOutputStream());
//			oos.writeObject(mdl);
//			System.out.println("发送成功");
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jphy_jb2||e.getSource()==jphm_jb2)
		{
			cl.show(this.getContentPane(), "2");//不允许直接对this操作
		}else if(e.getSource()==jpms_jb1||e.getSource()==jphm_jb1)
		{
			cl.show(this.getContentPane(), "1");
		}else if(e.getSource()==jphy_jb3||e.getSource()==jpms_jb3)
		{
			cl.show(this.getContentPane(), "3");
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//响应用户双击事件，并得到好友编号
		if(arg0.getClickCount()==2)//双击
		{
			JLabel jl=(JLabel)arg0.getSource();
			String friendNo=jl.getText();
//			System.out.println("希望和  "+friendNo+"  聊天");
//			Chat chat=new Chat(this.myid,friendNo,this.s);
			mchat.addChat(this.myid,friendNo, this.s);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JLabel jl=(JLabel)arg0.getSource();
		jl.setForeground(Color.red);//鼠标进入该Label变红
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JLabel jl=(JLabel)arg0.getSource();
		jl.setForeground(Color.black);//鼠标退出该Label恢复为黑
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean first=true;//刚登录客户端第一次查询登录好友信息不需要提示好友上线
		while(true)
		{
			Message mdl=new Message();
			mdl.setMesType(5);
			mdl.setSender(this.myid);
			this.mc.SendInfoToServer(mdl);
			try {
//				ObjectOutputStream oos=new ObjectOutputStream(this.s.getOutputStream());
//				oos.writeObject(mdl);
				this.ois=new ObjectInputStream(this.s.getInputStream());//读不到就等待
				Message m=(Message)ois.readObject();
				if(m.getMesType()==4)
				{
					this.mchat.receive(m);
				}else if(m.getMesType()==5)//修改好友登录状态
				{
					Vector hy=m.getConVt();
					int size=hy.size();
					int b=0;
					int a=Integer.parseInt((String)hy.get(b));
					for(int i=0;i<this.jlbhys.length;i++)//修改好友登录状态，并提示
					{
						if(i+1<a||a==-1)
						{
							if(this.jlbhys[i].isEnabled()==true)
							{
								System.out.println(i+1+"下线了");
								this.jlbhys[i].setEnabled(false);
							}
						}else if(i+1==a)
						{
							if(this.jlbhys[i].isEnabled()==false)
							{
								if(first==false)System.out.println(i+1+"上线了");
								this.jlbhys[i].setEnabled(true);
							}
							if(b+1<hy.size())
							{
								b++;
								a=Integer.parseInt((String)hy.get(b));
							}else{
								a=-1;
							}
						}
					}
					first=false;
//					for(int i=0;i<this.jlbhys.length;i++)
//					{
//						this.jlbhys[i].setEnabled(false);//头像设回默认
//					}
//					for(int i=0;i<hy.size();i++)
//					{
////						System.out.println(hy.get(i)+"在线");
//						int a = Integer.parseInt((String)hy.get(i));
//						this.jlbhys[a-1].setEnabled(true);//头像变回为登录状态
//					}
				}
				Thread.sleep(500);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		Message mxx=new Message();
		mxx.setMesType(6);
		mxx.setSender(this.myid);
		this.mc.SendInfoToServer(mxx);
		System.out.println("发送下线报");
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
