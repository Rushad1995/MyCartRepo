package Pojo;

public class AuthPojo2 {
	
	String userId;
	String Password;
	public AuthPojo2() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthPojo2(String userId, String password) {
		super();
		this.userId = userId;
		Password = password;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	@Override
	public String toString() {
		return "AuthPojo2 [userId=" + userId + ", Password=" + Password + "]";
	}
	
}
