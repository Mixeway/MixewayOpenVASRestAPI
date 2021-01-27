package pl.orange.bst.mixer.openvas.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "modify_task")
public class ModifyTask {
	@XmlAttribute(name="task_id")
	private String taskId;
	
	private Target target;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Target getTarget() {
		return target;
	}

	public void setTarget(Target target) {
		this.target = target;
	}
	
	public ModifyTask(String taskId, Target target) {
		this.setTaskId(taskId);
		this.setTarget(target);
	}

}
