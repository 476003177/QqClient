/*
 * 用户动作类
 */
package model;
import java.net.*;
import common.*;
public class MyClientUser {
	MyClientConnect mcc;
	public MyClientUser()
	{
		this.mcc=new MyClientConnect();
	}
	public int checkUser(User u)
	{
		return mcc.SendLoginInfoToServer(u);
	}
	public Socket getSocket()
	{
		return mcc.getSocket();
	}
	
}
