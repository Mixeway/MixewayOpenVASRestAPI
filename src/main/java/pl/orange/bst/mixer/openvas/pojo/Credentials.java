package pl.orange.bst.mixer.openvas.pojo;


public class Credentials {
	
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Credentials(User user) {
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
	}
	
	
}
