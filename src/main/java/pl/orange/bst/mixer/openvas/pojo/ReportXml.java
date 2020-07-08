package pl.orange.bst.mixer.openvas.pojo;

import java.util.List;

public class ReportXml {
	private List<Vuln> vulns;
	public List<Vuln> getVulns() {
		return vulns;
	}
	public void setVulns(List<Vuln> vulns) {
		this.vulns = vulns;
	}

	public ReportXml(List<Vuln> v) {
		this.setVulns(v);
	}
	

}
