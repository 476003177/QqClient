package common;

import java.util.*;

public class Message implements java.io.Serializable{
	
	private int mesType;//1成功，2失败，3异常，4普通报，5为登录报，6为下线报
	private String Sender;//发送者
	private String getter;//接受者
	private Vector con=new Vector<String>();//信息内容本身
	private String time;//发送时间
	
	public int getMesType() {
		return mesType;
	}
	public void setMesType(int mesType) {
		this.mesType = mesType;
	}
	public String getSender() {
		return Sender;
	}
	public void setSender(String sender) {
		Sender = sender;
	}
	public String getGetter() {
		return getter;
	}
	public void setGetter(String getter) {
		this.getter = getter;
	}
	public String getCon() {
		return (String) this.con.get(0);
	}
	public Vector getConVt() {
		return (Vector) this.con;
	}
	public void setCon(String con) {
		this.con.add(con);
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
