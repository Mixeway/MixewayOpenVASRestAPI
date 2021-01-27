/*
 * @created  2020-09-28 : 16:15
 * @project  MixewayScanner
 * @author   siewer
 */
package pl.orange.bst.mixer.openvas.pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "get_results")
public class GetResults {
    @XmlAttribute(name="filter")
    String filter;

    public GetResults(String reportId, int start){
        this.filter ="report_id="+reportId+" first="+(start * 1000 + 1)+" rows=1000";
    }

    public GetResults() {
    }
}
