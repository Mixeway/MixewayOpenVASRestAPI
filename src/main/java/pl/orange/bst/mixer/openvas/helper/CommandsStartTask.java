package pl.orange.bst.mixer.openvas.helper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pl.orange.bst.mixer.openvas.pojo.Authenticate;
import pl.orange.bst.mixer.openvas.pojo.StartTask;
import pl.orange.bst.mixer.openvas.pojo.User;

@XmlRootElement(name="commands")
@XmlAccessorType(XmlAccessType.FIELD)
public class CommandsStartTask {
	
	private Authenticate authenticate;
	@XmlElement(name="start_task")
	private StartTask startTask;
	public Authenticate getAuthenticate() {
		return authenticate;
	}
	public void setAuthenticate(Authenticate authenticate) {
		this.authenticate = authenticate;
	}
	public StartTask getStartTask() {
		return startTask;
	}
	public void setStartTask(StartTask startTask) {
		this.startTask = startTask;
	}
	
	public CommandsStartTask() {}
	public CommandsStartTask(User user, StartTask startTask) {
		this.setAuthenticate(new Authenticate(user));
		this.setStartTask(startTask);
	}

}
