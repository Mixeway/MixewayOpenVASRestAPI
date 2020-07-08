package pl.orange.bst.mixer.openvas.pojo;

public class CreateTask {
	private String name;
	private Config config;
	private Scanner scanner;
	private Target target;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Config getConfig() {
		return config;
	}
	public void setConfig(Config config) {
		this.config = config;
	}
	public Scanner getScanner() {
		return scanner;
	}
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
	public Target getTarget() {
		return target;
	}
	public void setTarget(Target target) {
		this.target = target;
	}

	
	

}
