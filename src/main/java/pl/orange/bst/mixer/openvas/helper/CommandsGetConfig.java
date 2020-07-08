package pl.orange.bst.mixer.openvas.helper;

import javax.xml.bind.annotation.XmlRootElement;

import pl.orange.bst.mixer.openvas.pojo.Authenticate;
import pl.orange.bst.mixer.openvas.pojo.User;

@XmlRootElement(name="commands")
public class CommandsGetConfig {
	
	private Authenticate authenticate;
	private String get_configs;

	public Authenticate getAuthenticate() {
		return authenticate;
	}

	public void setAuthenticate(Authenticate authenticate) {
		this.authenticate = authenticate;
	}
	

	public String getGet_configs() {
		return get_configs;
	}

	public void setGet_configs(String get_configs) {
		this.get_configs = get_configs;
	}

	public CommandsGetConfig(User user) {
		this.setAuthenticate(new Authenticate(user));
		this.setGet_configs("");
	}
	public CommandsGetConfig() {}
}
