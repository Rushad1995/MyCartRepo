package Pojo;

public class AuthPojo {
	String adminId;
	String AdminPass;
	
	public AuthPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthPojo(String adminId, String adminPass) {
		super();
		this.adminId = adminId;
		AdminPass = adminPass;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminPass() {
		return AdminPass;
	}
	public void setAdminPass(String adminPass) {
		AdminPass = adminPass;
	}
	@Override
	public String toString() {
		return "AuthPojo [adminId=" + adminId + ", AdminPass=" + AdminPass + "]";
	}
		
}
