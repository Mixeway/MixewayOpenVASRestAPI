package pl.orange.bst.mixer.openvas.pojo;

public class CreateTarget {

	private String name;
	private String hosts;
	private String alive_tests;
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
		this.setAlive_tests("Consider Alive");
	}
	
}
