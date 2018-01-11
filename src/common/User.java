package common;

public class User  extends Message implements java.io.Serializable{//实现接口，序列化，让一个对象可以在网络上传输
	
	private String id;
	private String pass;
	
	public User()
	{
		this.setMesType(5);
	}
	public String getid() {
		return id;
	}
	public void setid(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
}
