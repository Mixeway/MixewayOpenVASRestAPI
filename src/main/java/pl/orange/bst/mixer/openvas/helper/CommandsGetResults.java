/*
 * @created  2020-09-28 : 16:14
 * @project  MixewayScanner
 * @author   siewer
 */
package pl.orange.bst.mixer.openvas.helper;

import pl.orange.bst.mixer.openvas.pojo.Authenticate;
import pl.orange.bst.mixer.openvas.pojo.GetResults;
import pl.orange.bst.mixer.openvas.pojo.Report;
import pl.orange.bst.mixer.openvas.pojo.User;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="commands")
@XmlAccessorType(XmlAccessType.FIELD)
public class CommandsGetResults {

    private Authenticate authenticate;
    @XmlElement(name="get_results")
    private GetResults getResults;
    public Authenticate getAuthenticate() {
        return authenticate;
    }
    public void setAuthenticate(Authenticate authenticate) {
        this.authenticate = authenticate;
    }

    public CommandsGetResults(){}

    public CommandsGetResults(User user, int start, String report_id){
        this.authenticate = new Authenticate(user);
        this.getResults = new GetResults(report_id, start);
    }

}