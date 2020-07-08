package pl.orange.bst.mixer.openvas.helper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pl.orange.bst.mixer.openvas.pojo.Authenticate;
import pl.orange.bst.mixer.openvas.pojo.GetTask;
import pl.orange.bst.mixer.openvas.pojo.User;

@XmlRootElement(name="commands")
@XmlAccessorType(XmlAccessType.FIELD)
public class CommandsGetTasks {
	
	private Authenticate authenticate;
	@XmlElement(name="get_tasks")
	private GetTask getTask;
	public Authenticate getAuthenticate() {
		return authenticate;
	}
	public void setAuthenticate(Authenticate authenticate) {
		this.authenticate = authenticate;
	}
	public GetTask getGetTask() {
		return getTask;
	}
	public void setGetTask(GetTask getTask) {
		this.getTask = getTask;
	}
	
	public CommandsGetTasks() {}
	public CommandsGetTasks(User user, GetTask getTask) {
		this.setAuthenticate(new Authenticate(user));
		this.setGetTask(getTask);
	}

}
