package bean;

public class MemberBean {
	private String memId;
	private int addrSeq;
	private String name;
	private String age;
	private String password;
	private String email;
	private int isAdmin; // 관리자 1 일반유저 0, 디폴트 0
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public int getAddrSeq() {
		return addrSeq;
	}
	public void setAddrSeq(int addrSeq) {
		this.addrSeq = addrSeq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	
    
    
}
