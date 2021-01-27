package pl.orange.bst.mixer.openvas.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "create_target")
public class CreateTarget {

	private String name;
	private String hosts;
	private String alive_tests;
	private PortList port_list;

	public PortList getPort_list() {
		return port_list;
	}

	public void setPort_list(PortList port_list) {
		this.port_list = port_list;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHosts() {
		return hosts;
	}
	public void setHosts(String hosts) {
		this.hosts = hosts;
	}
	public String getAlive_tests() {
		return alive_tests;
	}
	public void setAlive_tests(String alive_tests) {
		this.alive_tests = alive_tests;
	}
	public CreateTarget() {
		this.port_list = new PortList();
		this.setAlive_tests("Consider Alive");
	}
	
}
