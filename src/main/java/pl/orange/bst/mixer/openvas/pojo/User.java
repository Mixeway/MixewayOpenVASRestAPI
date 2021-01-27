package pl.orange.bst.mixer.openvas.pojo;

import pl.orange.bst.mixer.ConstantStrings;

public class User {
	
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
	public User prepare() {		
		this.setPassword(ConstantStrings.escape(this.getPassword()));
		this.setUsername(ConstantStrings.escape(this.getUsername()));
		return this;
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
}
