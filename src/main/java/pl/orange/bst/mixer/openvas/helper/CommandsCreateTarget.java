package pl.orange.bst.mixer.openvas.helper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pl.orange.bst.mixer.openvas.pojo.Authenticate;
import pl.orange.bst.mixer.openvas.pojo.CreateTarget;
import pl.orange.bst.mixer.openvas.pojo.User;

@XmlRootElement(name="commands")
@XmlAccessorType(XmlAccessType.FIELD)
public class CommandsCreateTarget {
	private Authenticate authenticate;
	@XmlElement(name="create_target")
	private CreateTarget createTarget;
	public Authenticate getAuthenticate() {
		return authenticate;
	}
	public void setAuthenticate(Authenticate authenticate) {
		this.authenticate = authenticate;
	}
	public CreateTarget getCreateTarget() {
		return createTarget;
	}
	public void setCreateTarget(CreateTarget createTarget) {
		this.createTarget = createTarget;
	}
	
	public CommandsCreateTarget() {}
	public CommandsCreateTarget(User user) {
		this.setAuthenticate(new Authenticate(user));
	}

}
