package pl.orange.bst.mixer.openvas.pojo;

import java.util.HashMap;
import java.util.Map;

import pl.orange.bst.mixer.ConstantStrings;

public class RestRequestBody {
	private User user;
	private HashMap<String, String> params;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public HashMap<String, String> getParams() {
		return params;
	}
	public void setParams(HashMap<String, String> params) {
		this.params = params;
	}
	public RestRequestBody prepare() {
		this.setUser(user.prepare());
		HashMap<String, String> newMap = new HashMap<>();
		for (Map.Entry<String, String> item : params.entrySet()) {
			newMap.put(ConstantStrings.escape(item.getKey()), ConstantStrings.escape(item.getValue()));
		}
		this.setParams(newMap);
		return this;
	}
	
	

}
