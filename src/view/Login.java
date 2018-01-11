/*
 * QQ�ͻ��˵�¼����
 */
package view;

import javax.swing.*;

import common.*;
import model.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener{

	Friend friend;
	//�������
	JLabel jlb1;
	//�в����
	JTabbedPane jtp;
	JPanel jp2,jp3,jp4;
	JLabel jp2_jlb1,jp2_jlb2,jp2_jlb3,jp2_jlb4;
	JButton jp2_jb1;
	JTextField jp2_jtf;//�ı���
	JPasswordField jp2_jpf;//�����
	JCheckBox jp2_jcb1,jp2_jcb2;//��ѡ��
	//�ϲ����
	JPanel jp1;
	JButton jp1_jb1,jp1_jb2,jp1_jb3;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Login login=new Login();
		
	}

	public Login()
	{
		//����
		jlb1=new JLabel(new ImageIcon("images/tou.gif"));
		//�ϲ�
		jp1=new JPanel();//Ĭ�������־���
		jp1_jb1=new JButton(new ImageIcon("images/denglu.gif"));
		jp1_jb1.addActionListener(this);//��Ӧ��¼
		jp1_jb2=new JButton(new ImageIcon("images/quxiao.gif"));
		jp1_jb2.addActionListener(this);//��Ӧȡ��
		jp1_jb3=new JButton(new ImageIcon("images/xiangdao.gif"));
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);
		//�в�������JPanel����һ��ѡ����ڹ���
		jtp=new JTabbedPane();//ѡ�����
		jp2=new JPanel(new GridLayout(3,3));
		jp2_jlb1=new JLabel("QQ����",JLabel.CENTER);//����
		jp2_jlb2=new JLabel("QQ����",JLabel.CENTER);
		jp2_jlb3=new JLabel("��������",JLabel.CENTER);
		jp2_jlb3.setFont(new Font("����",Font.PLAIN,16)); //����������ʽ
		jp2_jlb3.setForeground(Color.BLUE);//����ǰ��ɫ��������ɫ��
		jp2_jlb3.setCursor(new Cursor(Cursor.HAND_CURSOR));//��������
		jp2_jlb4=new JLabel("<HTML><U>�������뱣��</U></HTML>",JLabel.CENTER);//�»��߾���
		jp2_jlb4.setForeground(Color.BLUE);//����ǰ��ɫ��������ɫ��
		jp2_jlb4.setCursor(new Cursor(Cursor.HAND_CURSOR));//��������
		jp2_jb1=new JButton(new ImageIcon("images/clear.gif"));
		jp2_jtf=new JTextField();
		jp2_jpf=new JPasswordField();
		jp2_jcb1=new JCheckBox("�����¼");
		jp2_jcb2=new JCheckBox("��ס����");
		jp2.add(jp2_jlb1);
		jp2.add(jp2_jtf);
		jp2.add(jp2_jb1);
		jp2.add(jp2_jlb2);
		jp2.add(jp2_jpf);
		jp2.add(jp2_jlb3);
		jp2.add(jp2_jcb1);
		jp2.add(jp2_jcb2);
		jp2.add(jp2_jlb4);
		jp3=new JPanel();
		jp4=new JPanel();
		jtp.add("QQ����",jp2);
		jtp.add("�ֻ�����",jp3);
		jtp.add("�����ʼ�",jp4);
		
		this.add(jlb1,"North");
		this.add(jtp,"Center");
		this.add(jp1,"South");
		
		this.setTitle("��ѶQQ");                             //���ô������
		this.setIconImage((new ImageIcon("images/qq.gif")).getImage());//����ͼ��
		this.setSize(350, 240);                         //������Ϊ��λ���ô��峤��	
		this.setLocation(200,200);                      //���ó�ʼ����λ��
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ùرմ��ڼ��˳�
		this.setResizable(false);                       //��ֹ�û��ı䴰���С
		this.setVisible(true);                          //��ʾ
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jp1_jb1)
		{
			MyClientUser myclientuser=new MyClientUser();
			User u=new User();
			u.setid(jp2_jtf.getText().trim());
			u.setPass(new String(jp2_jpf.getPassword()));
			int a=myclientuser.checkUser(u);
//			System.out.println("�յ���¼������Ϣ����"+a);
			if(a==1)//1������룬2�˺�/�������3�˺��쳣
			{
				friend=new Friend(u.getid(),myclientuser.getSocket());
				Thread t=new Thread(friend);
				t.start();
				this.dispose();
			}else if(a==2){
				JOptionPane.showMessageDialog(this, "�û���/�������");
			}else if(a==3){
				JOptionPane.showMessageDialog(this, "�˺��쳣");
			}else{
				JOptionPane.showMessageDialog(this, "�쳣");
			}
		}else if(e.getSource()==jp1_jb2)
		{
			jp2_jtf.setText("");
			jp2_jpf.setText("");
		}
	}
	
}
