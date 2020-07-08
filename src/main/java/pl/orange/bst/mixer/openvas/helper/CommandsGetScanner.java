package pl.orange.bst.mixer.openvas.helper;

import javax.xml.bind.annotation.XmlRootElement;

import pl.orange.bst.mixer.openvas.pojo.Authenticate;
import pl.orange.bst.mixer.openvas.pojo.User;

@XmlRootElement(name="commands")
public class CommandsGetScanner {
	private Authenticate authenticate;
	private String get_scanners;
	public Authenticate getAuthenticate() {
		return authenticate;
	}
	public void setAuthenticate(Authenticate authenticate) {
		this.authenticate = authenticate;
	}
	public String getGet_scanners() {
		return get_scanners;
	}
	public void setGet_scanners(String get_scanners) {
		this.get_scanners = get_scanners;
	}
	
	public CommandsGetScanner() {}
	public CommandsGetScanner(User user) {
		this.setAuthenticate(new Authenticate(user));
		this.setGet_scanners("");
	}
}
