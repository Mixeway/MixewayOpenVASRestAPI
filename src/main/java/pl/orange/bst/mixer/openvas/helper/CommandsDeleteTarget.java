package pl.orange.bst.mixer.openvas.helper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pl.orange.bst.mixer.openvas.pojo.Authenticate;
import pl.orange.bst.mixer.openvas.pojo.DeleteTarget;
import pl.orange.bst.mixer.openvas.pojo.User;

@XmlRootElement(name="commands")
@XmlAccessorType(XmlAccessType.FIELD)
public class CommandsDeleteTarget {
	
	
	private Authenticate authenticate;
	@XmlElement(name="delete_target")
	private DeleteTarget deleteTarget;
	public Authenticate getAuthenticate() {
		return authenticate;
	}
	public void setAuthenticate(Authenticate authenticate) {
		this.authenticate = authenticate;
	}
	public DeleteTarget getDeleteTarget() {
		return deleteTarget;
	}
	public void setDeleteTarget(DeleteTarget deleteTarget) {
		this.deleteTarget = deleteTarget;
	}
	
	public CommandsDeleteTarget() {}
	public CommandsDeleteTarget(User user) {
		this.setAuthenticate(new Authenticate(user));
	}

}
