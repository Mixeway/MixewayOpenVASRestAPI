package pl.orange.bst.mixer;

public class ConstantStrings {
	public static final String HOSTS = "hosts";
	public static final String TARGET_NAME = "name";
	public static final String TARGET_ID = "target_id";
	public static final String CONFIG_ID = "config_id";
	public static final String CONFIG_NAME = "Full and fast";
	public static final String SCANNER_ID = "scanner_id";
	public static final String SCANNER_NAME = "OpenVAS Default";
	public static final String TASK_ID = "task_id";
	public static final String REPORT_ID = "report_id";
	public static final String STATUS = "status";
	public static final String STATUS_OK = "ok";
	
	public static String escape(String stringToEscape) {
		return stringToEscape.replace("\"", "").replace("'", "").replace(";", "");
	}
	

}
