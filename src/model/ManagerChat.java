package model;

import java.net.*;
import java.util.*;

import common.*;
import view.Chat;

public class ManagerChat {
	
	Message ma;
	HashMap wfmhm=new HashMap<String, Vector>();//每个通讯人一个HashMap项，存放Vector，Vector是对应通讯人未读信息的集合
	Vector wfmavt;
	HashMap chathm=new HashMap<String, Chat>(); //将线程存放在hashmap里，并用String标识，且可得知谁在线
	//添加线程
	public void addChat(String myid,String id,Socket s)
	{
		Chat chat=new Chat(myid,id,s,this);
		chathm.put(id, chat);
		if(wfmhm.get(id)!=null)
		{
			Vector vt1=(Vector) wfmhm.get(id);
			for(int i=0;i<vt1.size();i++)
			{
//				System.out.println("有X条未读："+vt1.size());
				this.receive((Message)vt1.get(i));
			}
			this.removemahm(id);
		}
	}
	public Chat getChat(String id)
	{
		return (Chat)chathm.get(id);//若不存在则返回NULL
	}
	public void removeChat(String id)
	{
//		System.out.println("聊天框HashMap移除");
		this.chathm.remove(id);
	}
	public void addma(String id,Message me)
	{
		this.wfmavt=this.getmavt(id);
		if(this.wfmavt==null)
		{
			wfmavt=new Vector<Message>();
			this.wfmhm.put(id, wfmavt);
		}
		this.wfmavt.add(me);
		
	}
	public Vector getmavt(String id)
	{
		return (Vector)this.wfmhm.get(id);//若不存在则返回NULL
	}
	public void removemahm(String id)
	{
		this.wfmhm.remove(id);
	}
	public void removemavt(int i)
	{
		this.wfmavt.remove(i);
	}
	public void receive(Message me)
	{
		this.ma=me;
		Chat chat1=this.getChat(this.ma.getSender());
		if(chat1==null)
		{
			this.addma(this.ma.getSender(), this.ma);
			System.out.println("存起来");
		}else{
			String iofo=this.ma.getSender()+" 对你说： "+this.ma.getCon()+" ("+this.ma.getTime()+")\r\n";
			chat1.receive(iofo);
		}
	}
}
