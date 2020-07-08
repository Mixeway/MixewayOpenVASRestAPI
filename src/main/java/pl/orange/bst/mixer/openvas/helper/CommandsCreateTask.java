package pl.orange.bst.mixer.openvas.helper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pl.orange.bst.mixer.openvas.pojo.Authenticate;
import pl.orange.bst.mixer.openvas.pojo.CreateTask;
import pl.orange.bst.mixer.openvas.pojo.User;

@XmlRootElement(name="commands")
@XmlAccessorType(XmlAccessType.FIELD)
public class CommandsCreateTask {
	private Authenticate authenticate;
	@XmlElement(name="create_task")
	private CreateTask createTask;
	public Authenticate getAuthenticate() {
		return authenticate;
	}
	public void setAuthenticate(Authenticate authenticate) {
		this.authenticate = authenticate;
	}
	public CreateTask getCreateTask() {
		return createTask;
	}
	public void setCreateTask(CreateTask createTask) {
		this.createTask = createTask;
	}
	
	public CommandsCreateTask() {}
	public CommandsCreateTask(User user) {
		this.setAuthenticate(new Authenticate(user));
	}

}
