/*
 * �ҵĺ����б�����İ���ˡ�������
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

	int friendcount = 50;// �ҵĺ��Ѹ���
	int strangercount = 20;// İ���˸���
	int blackcount = 20;// ����������
	ManagerChat mchat;
	String myid;
	public Socket s;
	ObjectInputStream ois;
	MyClientConnect mc=new MyClientConnect();
	
	CardLayout cl;
	// ��һ�ſ�Ƭ���ҵĺ��ѣ�
	JPanel jphy1, jphy2, jphy3;
	JButton jphy_jb1, jphy_jb2, jphy_jb3;
	JScrollPane jsphy;
	JLabel[] jlbhys;
	// �ڶ��ſ�Ƭ��İ���ˣ�
	JPanel jpms1, jpms2, jpms3;
	JButton jpms_jb1, jpms_jb2, jpms_jb3;
	JScrollPane jspms;
	JLabel[] jlbmss;
	// �ڶ��ſ�Ƭ����������
	JPanel jphm1, jphm2, jphm3;
	JButton jphm_jb1, jphm_jb2, jphm_jb3;
	JScrollPane jsphm;
	JLabel[] jlbhms;

	public void ThirdCard()// ��������ſ�Ƭ
	{
		// �����ſ�Ƭ����ʾ�����б�
		jphm1 = new JPanel(new BorderLayout());
		jphm2 = new JPanel(new GridLayout(blackcount, 1, 4, 4));// friendcount��1�У��м���м���Ϊ4
		jsphm = new JScrollPane(jphm2);// ����ͬʱ��jphy2����
		jphm3 = new JPanel(new GridLayout(3, 1));
		jphm_jb1 = new JButton("�ҵĺ���");
		jphm_jb1.addActionListener(this);
		jphm_jb2 = new JButton("İ����");
		jphm_jb2.addActionListener(this);
		jphm_jb3 = new JButton("������");
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
	
	public void SecondCard()// ����ڶ��ſ�Ƭ
	{
		// �ڶ��ſ�Ƭ����ʾ�����б�
		jpms1 = new JPanel(new BorderLayout());
		jpms2 = new JPanel(new GridLayout(strangercount, 1, 4, 4));// friendcount��1�У��м���м���Ϊ4
		jspms = new JScrollPane(jpms2);// ����ͬʱ��jphy2����
		jpms3 = new JPanel(new GridLayout(2, 1));
		jpms_jb1 = new JButton("�ҵĺ���");
		jpms_jb1.addActionListener(this);
		jpms_jb2 = new JButton("İ����");
		jpms_jb3 = new JButton("������");
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

	public void FirstCard()// �����һ�ſ�Ƭ
	{
		// ��һ�ſ�Ƭ����ʾİ����
		jphy1 = new JPanel(new BorderLayout());
		jphy2 = new JPanel(new GridLayout(friendcount, 1, 4, 4));// friendcount��1�У��м���м���Ϊ4
		jsphy = new JScrollPane(jphy2);// ����ͬʱ��jphy2����
		jphy3 = new JPanel(new GridLayout(2, 1));
		jphy_jb1 = new JButton("�ҵĺ���");
		jphy_jb2 = new JButton("İ����");
		jphy_jb2.addActionListener(this);
		jphy_jb3 = new JButton("������");
		jphy_jb3.addActionListener(this);
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		jlbhys = new JLabel[friendcount];
		for (int i = 0; i < jlbhys.length; i++) {
			jlbhys[i] = new JLabel(i + 1 + "", new ImageIcon("images/mm.jpg"), JLabel.LEFT);
			jlbhys[i].addMouseListener(this);
			jlbhys[i].setEnabled(false);//Ĭ��δ��¼�������ã����
			if(jlbhys[i].getText().equals(this.myid))jlbhys[i].setEnabled(true);//Ĭ��ֻ���Լ���¼����
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
		this.setLayout(cl);//����JFrame���ɿ�Ƭ����
		this.FirstCard();
		this.SecondCard();
		this.ThirdCard();
		this.add(jphy1,"1");
		this.add(jpms1,"2");
		this.add(jphm1,"3");

		this.setTitle(this.myid); // ���ô������
		this.setIconImage((new ImageIcon("images/qq.gif")).getImage());// ����ͼ��
		this.setSize(100, 400); // ������Ϊ��λ���ô��峤��
		this.setLocation(200, 200); // ���ó�ʼ����λ��
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���ùرմ��ڼ��˳�
		this.setResizable(true); // �����û��ı䴰���С
		this.setVisible(true); // ��ʾ
		this.addWindowListener(this);

//		try {
//			Message mdl=new Message();
//			mdl.setMesType(5);
//			ObjectOutputStream oos=new ObjectOutputStream(this.s.getOutputStream());
//			oos.writeObject(mdl);
//			System.out.println("���ͳɹ�");
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
			cl.show(this.getContentPane(), "2");//������ֱ�Ӷ�this����
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
		//��Ӧ�û�˫���¼������õ����ѱ��
		if(arg0.getClickCount()==2)//˫��
		{
			JLabel jl=(JLabel)arg0.getSource();
			String friendNo=jl.getText();
//			System.out.println("ϣ����  "+friendNo+"  ����");
//			Chat chat=new Chat(this.myid,friendNo,this.s);
			mchat.addChat(this.myid,friendNo, this.s);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JLabel jl=(JLabel)arg0.getSource();
		jl.setForeground(Color.red);//�������Label���
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JLabel jl=(JLabel)arg0.getSource();
		jl.setForeground(Color.black);//����˳���Label�ָ�Ϊ��
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
		boolean first=true;//�յ�¼�ͻ��˵�һ�β�ѯ��¼������Ϣ����Ҫ��ʾ��������
		while(true)
		{
			Message mdl=new Message();
			mdl.setMesType(5);
			mdl.setSender(this.myid);
			this.mc.SendInfoToServer(mdl);
			try {
//				ObjectOutputStream oos=new ObjectOutputStream(this.s.getOutputStream());
//				oos.writeObject(mdl);
				this.ois=new ObjectInputStream(this.s.getInputStream());//�������͵ȴ�
				Message m=(Message)ois.readObject();
				if(m.getMesType()==4)
				{
					this.mchat.receive(m);
				}else if(m.getMesType()==5)//�޸ĺ��ѵ�¼״̬
				{
					Vector hy=m.getConVt();
					int size=hy.size();
					int b=0;
					int a=Integer.parseInt((String)hy.get(b));
					for(int i=0;i<this.jlbhys.length;i++)//�޸ĺ��ѵ�¼״̬������ʾ
					{
						if(i+1<a||a==-1)
						{
							if(this.jlbhys[i].isEnabled()==true)
							{
								System.out.println(i+1+"������");
								this.jlbhys[i].setEnabled(false);
							}
						}else if(i+1==a)
						{
							if(this.jlbhys[i].isEnabled()==false)
							{
								if(first==false)System.out.println(i+1+"������");
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
//						this.jlbhys[i].setEnabled(false);//ͷ�����Ĭ��
//					}
//					for(int i=0;i<hy.size();i++)
//					{
////						System.out.println(hy.get(i)+"����");
//						int a = Integer.parseInt((String)hy.get(i));
//						this.jlbhys[a-1].setEnabled(true);//ͷ����Ϊ��¼״̬
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
		System.out.println("�������߱�");
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
