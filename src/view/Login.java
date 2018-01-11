/*
 * QQ客户端登录界面
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
	//北部组件
	JLabel jlb1;
	//中部组件
	JTabbedPane jtp;
	JPanel jp2,jp3,jp4;
	JLabel jp2_jlb1,jp2_jlb2,jp2_jlb3,jp2_jlb4;
	JButton jp2_jb1;
	JTextField jp2_jtf;//文本框
	JPasswordField jp2_jpf;//密码框
	JCheckBox jp2_jcb1,jp2_jcb2;//复选框
	//南部组件
	JPanel jp1;
	JButton jp1_jb1,jp1_jb2,jp1_jb3;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Login login=new Login();
		
	}

	public Login()
	{
		//北部
		jlb1=new JLabel(new ImageIcon("images/tou.gif"));
		//南部
		jp1=new JPanel();//默认流布局居中
		jp1_jb1=new JButton(new ImageIcon("images/denglu.gif"));
		jp1_jb1.addActionListener(this);//响应登录
		jp1_jb2=new JButton(new ImageIcon("images/quxiao.gif"));
		jp1_jb2.addActionListener(this);//响应取消
		jp1_jb3=new JButton(new ImageIcon("images/xiangdao.gif"));
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);
		//中部，三个JPanel，由一个选项卡窗口管理
		jtp=new JTabbedPane();//选项卡窗口
		jp2=new JPanel(new GridLayout(3,3));
		jp2_jlb1=new JLabel("QQ号码",JLabel.CENTER);//居中
		jp2_jlb2=new JLabel("QQ密码",JLabel.CENTER);
		jp2_jlb3=new JLabel("忘记密码",JLabel.CENTER);
		jp2_jlb3.setFont(new Font("宋体",Font.PLAIN,16)); //设置字体样式
		jp2_jlb3.setForeground(Color.BLUE);//设置前景色（字体颜色）
		jp2_jlb3.setCursor(new Cursor(Cursor.HAND_CURSOR));//设置手型
		jp2_jlb4=new JLabel("<HTML><U>申请密码保护</U></HTML>",JLabel.CENTER);//下划线居中
		jp2_jlb4.setForeground(Color.BLUE);//设置前景色（字体颜色）
		jp2_jlb4.setCursor(new Cursor(Cursor.HAND_CURSOR));//设置手型
		jp2_jb1=new JButton(new ImageIcon("images/clear.gif"));
		jp2_jtf=new JTextField();
		jp2_jpf=new JPasswordField();
		jp2_jcb1=new JCheckBox("隐身登录");
		jp2_jcb2=new JCheckBox("记住密码");
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
		jtp.add("QQ号码",jp2);
		jtp.add("手机号码",jp3);
		jtp.add("电子邮件",jp4);
		
		this.add(jlb1,"North");
		this.add(jtp,"Center");
		this.add(jp1,"South");
		
		this.setTitle("腾讯QQ");                             //设置窗体标题
		this.setIconImage((new ImageIcon("images/qq.gif")).getImage());//设置图标
		this.setSize(350, 240);                         //以像素为单位设置窗体长宽	
		this.setLocation(200,200);                      //设置初始横纵位置
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭窗口即退出
		this.setResizable(false);                       //禁止用户改变窗体大小
		this.setVisible(true);                          //显示
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
//			System.out.println("收到登录反馈信息报："+a);
			if(a==1)//1允许进入，2账号/密码错误，3账号异常
			{
				friend=new Friend(u.getid(),myclientuser.getSocket());
				Thread t=new Thread(friend);
				t.start();
				this.dispose();
			}else if(a==2){
				JOptionPane.showMessageDialog(this, "用户名/密码错误");
			}else if(a==3){
				JOptionPane.showMessageDialog(this, "账号异常");
			}else{
				JOptionPane.showMessageDialog(this, "异常");
			}
		}else if(e.getSource()==jp1_jb2)
		{
			jp2_jtf.setText("");
			jp2_jpf.setText("");
		}
	}
	
}
