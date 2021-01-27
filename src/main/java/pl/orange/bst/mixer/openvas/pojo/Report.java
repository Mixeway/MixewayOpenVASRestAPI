package pl.orange.bst.mixer.openvas.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "get_reports")
public class Report {

	@XmlAttribute(name="report_id")
	private String reportId;
	@XmlAttribute(name="ignore_pagination")
	private Boolean ignorePagination = true;

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	
	public Report(String id) {
		this.setReportId(id);
	}
	public Report(){}

	public Boolean getIgnorePagination() {
		return ignorePagination;
	}

	public void setIgnorePagination(Boolean ignorePagination) {
		this.ignorePagination = ignorePagination;
	}
	
}
