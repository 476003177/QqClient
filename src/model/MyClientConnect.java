/*
 * �ͻ������ӷ������ĺ�̨
 */
package model;

import java.io.*;
import java.net.*;
import java.util.*;
import common.*;

public class MyClientConnect {
	
	Socket s;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	Message ms;
	
	public Socket getSocket()
	{
		return this.s;
	}
	public void setSocket(Socket s)
	{
		this.s=s;
	}
	
	public void link()
	{
		try {
			s=new Socket("127.0.0.1",9999);
			oos=new ObjectOutputStream(s.getOutputStream());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close()
	{
		try {
			if(this.s.isClosed()==false)this.s.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	//���͵�һ������
	public int SendLoginInfoToServer(Object o)
	{
		int b=0;
		try {
			this.link();
			oos.writeObject(o);
			ois=new ObjectInputStream(this.s.getInputStream());
			ms=(Message)ois.readObject();
			b=ms.getMesType();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
//			this.close();
			return b;
		}
	}
	
	//������Ϣ
	public void SendInfoToServer(Message o)
	{
		
		int b=o.getMesType();
		if(b==4||b==5||b==6)//��ͨ��
		{
			try {
//				System.out.println("MyClientConnect�յ�һ���ͻ��˷���4��5���͵ı� ");
				this.oos=new ObjectOutputStream(this.s.getOutputStream());
				this.oos.writeObject(o);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
//				this.close();
			}
		}
	}
}
