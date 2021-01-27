package pl.orange.bst.mixer.openvas.helper;

import javax.xml.bind.annotation.XmlRootElement;

import pl.orange.bst.mixer.openvas.pojo.Authenticate;
import pl.orange.bst.mixer.openvas.pojo.User;

@XmlRootElement(name="get_configs")
public class CommandsGetConfig {
	

	public CommandsGetConfig(User user) {
	}
	public CommandsGetConfig() {}
}
