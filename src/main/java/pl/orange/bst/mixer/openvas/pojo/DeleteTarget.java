package pl.orange.bst.mixer.openvas.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "delete_target")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeleteTarget {
	
	@XmlAttribute(name="target_id")
	private String targetId;

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	
	

}
