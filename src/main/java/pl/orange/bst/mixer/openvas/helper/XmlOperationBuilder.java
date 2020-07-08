package pl.orange.bst.mixer.openvas.helper;

import java.io.StringWriter;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.stereotype.Component;

import pl.orange.bst.mixer.ConstantStrings;
import pl.orange.bst.mixer.openvas.pojo.Config;
import pl.orange.bst.mixer.openvas.pojo.CreateTarget;
import pl.orange.bst.mixer.openvas.pojo.CreateTask;
import pl.orange.bst.mixer.openvas.pojo.DeleteTarget;
import pl.orange.bst.mixer.openvas.pojo.GetTask;
import pl.orange.bst.mixer.openvas.pojo.ModifyTask;
import pl.orange.bst.mixer.openvas.pojo.Report;
import pl.orange.bst.mixer.openvas.pojo.Scanner;
import pl.orange.bst.mixer.openvas.pojo.StartTask;
import pl.orange.bst.mixer.openvas.pojo.Target;
import pl.orange.bst.mixer.openvas.pojo.User;

@Component
public class XmlOperationBuilder {

	public String buildGetConfig(User user) throws JAXBException {
		CommandsGetConfig cgc = new CommandsGetConfig(user);
		JAXBContext jaxbContext = JAXBContext.newInstance(CommandsGetConfig.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        StringWriter sw = new StringWriter();
        marshaller.marshal(cgc, sw);
		return sw.toString();
	}
	public String buildGetScanners(User user) throws JAXBException {
		CommandsGetScanner cgs = new CommandsGetScanner(user);
		JAXBContext jaxbContext = JAXBContext.newInstance(CommandsGetScanner.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        StringWriter sw = new StringWriter();
        marshaller.marshal(cgs, sw);
		return sw.toString();
	}
	public String buildCreateTarget(User user, HashMap<String, String> target) throws JAXBException {
		CommandsCreateTarget cct = new CommandsCreateTarget(user);
		CreateTarget ct = new CreateTarget();
		ct.setHosts(target.get(ConstantStrings.HOSTS));
		ct.setName(target.get(ConstantStrings.TARGET_NAME));
		cct.setCreateTarget(ct);
		JAXBContext jaxbContext = JAXBContext.newInstance(CommandsCreateTarget.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        StringWriter sw = new StringWriter();
        marshaller.marshal(cct, sw);
		return sw.toString();
		
	}
	public String buildDeleteTarget(User user, HashMap<String, String> target) throws JAXBException {
		CommandsDeleteTarget cdt = new CommandsDeleteTarget(user);
		DeleteTarget dt = new DeleteTarget();
		dt.setTargetId(target.get(ConstantStrings.TARGET_ID));
		cdt.setDeleteTarget(dt);
		JAXBContext jaxbContext = JAXBContext.newInstance(CommandsDeleteTarget.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        StringWriter sw = new StringWriter();
        marshaller.marshal(cdt, sw);
		return sw.toString();
		
	}
	public String buildCreateTask(User user, HashMap<String, String> target) throws JAXBException {
		CommandsCreateTask cct = new CommandsCreateTask(user);
		CreateTask ct = new CreateTask();
		ct.setConfig(new Config(target.get(ConstantStrings.CONFIG_ID)));
		ct.setScanner(new Scanner(target.get(ConstantStrings.SCANNER_ID)));
		ct.setTarget(new Target(target.get(ConstantStrings.TARGET_ID)));
		ct.setName(target.get(ConstantStrings.TARGET_NAME));
		cct.setCreateTask(ct);
		JAXBContext jaxbContext = JAXBContext.newInstance(CommandsCreateTask.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        StringWriter sw = new StringWriter();
        marshaller.marshal(cct, sw);
		return sw.toString();
		
	}
	public String buildModifyTask(User user, HashMap<String, String> target) throws JAXBException {
		CommandsModifyTask cmt = new CommandsModifyTask(user, new ModifyTask(target.get(ConstantStrings.TASK_ID), new Target(target.get(ConstantStrings.TARGET_ID))));
		JAXBContext jaxbContext = JAXBContext.newInstance(CommandsModifyTask.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        StringWriter sw = new StringWriter();
        marshaller.marshal(cmt, sw);
		return sw.toString();
		
	}
	public String buildStartTask(User user, HashMap<String, String> target) throws JAXBException {
		CommandsStartTask cst = new CommandsStartTask(user, new StartTask(target.get(ConstantStrings.TASK_ID)));
		JAXBContext jaxbContext = JAXBContext.newInstance(CommandsStartTask.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        StringWriter sw = new StringWriter();
        marshaller.marshal(cst, sw);
		return sw.toString();
		
	}
	public String buildGetTask(User user, HashMap<String, String> target) throws JAXBException {
		CommandsGetTasks cgt = new CommandsGetTasks(user, new GetTask(target.get(ConstantStrings.TASK_ID)));
		JAXBContext jaxbContext = JAXBContext.newInstance(CommandsGetTasks.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        StringWriter sw = new StringWriter();
        marshaller.marshal(cgt, sw);
		return sw.toString();
		
	}
	public String buildGetReport(User user, HashMap<String, String> target) throws JAXBException {
		CommandsGetReport cgr = new CommandsGetReport(user, new Report(target.get(ConstantStrings.REPORT_ID)));
		JAXBContext jaxbContext = JAXBContext.newInstance(CommandsGetReport.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        StringWriter sw = new StringWriter();
        marshaller.marshal(cgr, sw);
		return sw.toString();
		
	}

}
