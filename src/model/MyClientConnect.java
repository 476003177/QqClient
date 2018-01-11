/*
 * 客户端连接服务器的后台
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
	//发送第一次请求
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
	
	//发送信息
	public void SendInfoToServer(Message o)
	{
		
		int b=o.getMesType();
		if(b==4||b==5||b==6)//普通报
		{
			try {
//				System.out.println("MyClientConnect收到一个客户端发来4或5类型的报 ");
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
