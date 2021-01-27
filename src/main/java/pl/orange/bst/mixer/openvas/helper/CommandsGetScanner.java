package pl.orange.bst.mixer.openvas.helper;

import javax.xml.bind.annotation.XmlRootElement;

import pl.orange.bst.mixer.openvas.pojo.Authenticate;
import pl.orange.bst.mixer.openvas.pojo.User;

@XmlRootElement(name="get_scanners")
public class CommandsGetScanner {

	public CommandsGetScanner() {}
	public CommandsGetScanner(User user) {
	}
}
