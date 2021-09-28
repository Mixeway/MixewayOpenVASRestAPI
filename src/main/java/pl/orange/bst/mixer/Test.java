/*
 * @created  2021-01-27 : 09:38
 * @project  MixewayScanner
 * @author   siewer
 */
package pl.orange.bst.mixer;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import pl.orange.bst.mixer.openvas.helper.XmlOperationBuilder;
import pl.orange.bst.mixer.openvas.pojo.User;

import javax.xml.bind.JAXBException;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= AnnotationConfigContextLoader.class)
public class Test {


    @org.junit.Test
    public void createTarget() throws JAXBException {
        XmlOperationBuilder xob = new XmlOperationBuilder();
        User user = new User("gvmadmin","1qaz@WSX");
        HashMap<String, String> params =new HashMap<>();
        params.put("hosts","192.168.1.1,192.168.1.2");
        params.put("name","random_rtarget");
        System.out.println("Create TargeT:");
        System.out.println(buildCommandPrefix(user) + "\""+xob.buildCreateTarget(user, params)+"\"");
    }
    @org.junit.Test
    public void createTask() throws JAXBException {
        XmlOperationBuilder xob = new XmlOperationBuilder();
        User user = new User("gvmadmin","1qaz@WSX");
        HashMap<String, String> params =new HashMap<>();
        params.put("report_id","c9299f55-c17f-4e33-a3b0-48809213ee6d");
        System.out.println("Create task:");
        System.out.println(buildCommandPrefix(user) + "'"+xob.buildGetReport(user, params)+"'");
    }
    @org.junit.Test
    public void getReport() throws JAXBException {
        XmlOperationBuilder xob = new XmlOperationBuilder();
        User user = new User("gvmadmin","1qaz@WSX");
        HashMap<String, String> params =new HashMap<>();
        params.put("report_id","c9299f55-c17f-4e33-a3b0-48809213ee6d");
        System.out.println("Create task:");
        System.out.println(buildCommandPrefix(user) + "'"+xob.buildGetReport(user, params)+"'");
    }



    public String buildCommandPrefix(User user){
        return String.format("gvm-cli --gmp-username=%s --gmp-password=%s socket --socketpath %s --xml ",user.getUsername(), user.getPassword(), "/opt/gvm/var/run/gvmd.sock");
    }
}
