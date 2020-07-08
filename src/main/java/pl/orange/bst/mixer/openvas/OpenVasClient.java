package pl.orange.bst.mixer.openvas;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import pl.orange.bst.mixer.ConstantStrings;
import pl.orange.bst.mixer.openvas.helper.XmlOperationBuilder;
import pl.orange.bst.mixer.openvas.pojo.ReportXml;
import pl.orange.bst.mixer.openvas.pojo.RestRequestBody;
import pl.orange.bst.mixer.openvas.pojo.User;
import pl.orange.bst.mixer.openvas.pojo.Vuln;

@Component
public class OpenVasClient {
	@Value("${openvasmd.socket}")
	private String socket;

	XmlOperationBuilder xob = new XmlOperationBuilder();
	private static final Logger log = LoggerFactory.getLogger(OpenVasClient.class);
	
	public HashMap<String, String> initializeOpenVas(User user) throws JAXBException, IOException, SAXException, ParserConfigurationException{
		HashMap<String, String> responseMap = new HashMap<>();
		String configId = getConfigResponse(user);
		String scannerId = getScannerResponse(user);
		responseMap.put(ConstantStrings.SCANNER_ID, scannerId);
		responseMap.put(ConstantStrings.CONFIG_ID, configId);
		return responseMap;
		
	}
	public String createTarget(RestRequestBody body) throws JAXBException, SAXException, IOException, ParserConfigurationException {
		return getCreateTargetRespnse(body.getUser(), body.getParams());
		
	}
	public String createTask(RestRequestBody body) throws JAXBException, SAXException, IOException, ParserConfigurationException {
		return getCreateTaskResponse(body.getUser(), body.getParams());
	}
	public String modifyTask(RestRequestBody body, String taskId) throws JAXBException, SAXException, IOException, ParserConfigurationException {
		body.getParams().put(ConstantStrings.TASK_ID, taskId);
		return getModifyTaskResponse(body.getUser(), body.getParams());
	}
	public String runTask(RestRequestBody body) throws JAXBException, SAXException, IOException, ParserConfigurationException {
		return getRunTask(body.getUser(),body.getParams());
	}
	public String taskStatus(RestRequestBody body) throws JAXBException, SAXException, IOException, ParserConfigurationException {
		return getTaskStatusResponse(body.getUser(), body.getParams());
	}
	public ReportXml getReport(RestRequestBody body) throws JAXBException, SAXException, IOException, ParserConfigurationException {
		return getReportResponse(body.getUser(), body.getParams());
	}
	

	private ReportXml getReportResponse(User user, HashMap<String, String> params) throws JAXBException, SAXException, IOException, ParserConfigurationException {
		ProcessBuilder pb = new ProcessBuilder("bash", "-c", "gvm-cli socket --socketpath "+socket+" --xml '"+xob.buildGetReport(user, params)+"'");
		String output = IOUtils.toString(pb.start().getInputStream());
		Document doc = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(new InputSource(new StringReader(output)));
		Element reportNodeResponse = (Element) doc.getElementsByTagName("get_reports_response").item(0);
		Element reportNode = (Element) reportNodeResponse.getElementsByTagName("report").item(0);
		Element reportNode2 = (Element) reportNode.getElementsByTagName("report").item(0);
		Element results = (Element) reportNode2.getElementsByTagName("results").item(0);
		NodeList vulnNode = results.getElementsByTagName("result");
		List<Vuln> vulns = new ArrayList<>();
		
		if (vulnNode != null) {
	        int length = vulnNode.getLength();
	        for (int i = 0; i < length; i++) {
	            if (vulnNode.item(i).getNodeType() == Node.ELEMENT_NODE) {
	                Element el = (Element) vulnNode.item(i);   
	                try {
		                Vuln v = new Vuln();
		                v.setName(el.getElementsByTagName("name").item(0).getTextContent());
		                v.setHost(el.getElementsByTagName("host").item(0).getTextContent());
		                v.setDesc(el.getElementsByTagName("description").item(0).getTextContent());
		                v.setPort(el.getElementsByTagName("port").item(0).getTextContent());
		                v.setThreat(el.getElementsByTagName("threat").item(0).getTextContent());
		                vulns.add(v);
	                } catch (NullPointerException n) {
	                	log.warn("Something is wrong with one of the results, nullpoitner returned");
	                }
	            }
	        }
	    }
		
		return new ReportXml(vulns);
	}
	private String getTaskStatusResponse(User user, HashMap<String, String> params) throws JAXBException, SAXException, IOException, ParserConfigurationException {
		ProcessBuilder pb = new ProcessBuilder("bash", "-c", "gvm-cli socket --socketpath "+socket+" --xml '"+xob.buildGetTask(user, params)+"'");
		String output = IOUtils.toString(pb.start().getInputStream());
		Document doc = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(new InputSource(new StringReader(output)));
		NodeList ctResponseNode = doc.getElementsByTagName("get_tasks_response");
		Element ctResponse = (Element) ctResponseNode.item(0);
		Element ctResponseTask = (Element) ctResponse.getElementsByTagName("task").item(0);
		if (ctResponse.getAttribute("status").equals("200"))
			return ctResponseTask.getElementsByTagName("status").item(0).getTextContent();
		else
			return null;
	}
	private String getRunTask(User user, HashMap<String, String> params) throws JAXBException, SAXException, IOException, ParserConfigurationException {
		ProcessBuilder pb = new ProcessBuilder("bash", "-c", "gvm-cli socket --socketpath "+socket+" --xml '"+xob.buildStartTask(user, params)+"'");
		String output = IOUtils.toString(pb.start().getInputStream());
		Document doc = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(new InputSource(new StringReader(output)));
		NodeList ctResponseNode = doc.getElementsByTagName("start_task_response");
		Element ctResponse = (Element) ctResponseNode.item(0);
		if (ctResponse.getAttribute("status").equals("202"))
			return ctResponse.getElementsByTagName("report_id").item(0).getTextContent();
		else
			return null;
	}
	private String getModifyTaskResponse(User user, HashMap<String, String> params) throws JAXBException, SAXException, IOException, ParserConfigurationException {
		ProcessBuilder pb = new ProcessBuilder("bash", "-c", "gvm-cli socket --socketpath "+socket+" --xml '"+xob.buildModifyTask(user, params)+"'");
		String output = IOUtils.toString(pb.start().getInputStream());
		Document doc = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(new InputSource(new StringReader(output)));
		NodeList ctResponseNode = doc.getElementsByTagName("modify_task_response");
		Element ctResponse = (Element) ctResponseNode.item(0);
		if (ctResponse.getAttribute("status").equals("200"))
			return ConstantStrings.STATUS_OK;
		else
			return null;
	}
	private String getCreateTaskResponse(User user, HashMap<String, String> params) throws JAXBException, SAXException, IOException, ParserConfigurationException {
		ProcessBuilder pb = new ProcessBuilder("bash", "-c", "gvm-cli socket --socketpath "+socket+" --xml '"+xob.buildCreateTask(user, params)+"'");
		String output = IOUtils.toString(pb.start().getInputStream());
		Document doc = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(new InputSource(new StringReader(output)));
		NodeList ctResponseNode = doc.getElementsByTagName("create_task_response");
		Element ctResponse = (Element) ctResponseNode.item(0);
		if (ctResponse.getAttribute("status").equals("201"))
			return ctResponse.getAttribute("id");
		else
			return null;
	}
	private String getConfigResponse(User user) throws SAXException, IOException, ParserConfigurationException, JAXBException {
		ProcessBuilder pb = new ProcessBuilder("bash", "-c", "gvm-cli socket --socketpath "+socket+" --xml \""+xob.buildGetConfig(user)+"\"");
		System.out.println("gvm-cli socket --socketpath "+socket+" --xml \""+xob.buildGetConfig(user)+"\"");
		String output = IOUtils.toString(pb.start().getInputStream());
		Document doc = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(new InputSource(new StringReader(output)));
		NodeList configNode = doc.getElementsByTagName("config");
		if (configNode != null) {
	        int length = configNode.getLength();
	        for (int i = 0; i < length; i++) {
	            if (configNode.item(i).getNodeType() == Node.ELEMENT_NODE) {
	                Element el = (Element) configNode.item(i);
	                if(el.getElementsByTagName("name").item(1).getTextContent().equals(ConstantStrings.CONFIG_NAME)) {
	                	return el.getAttribute("id");
	                }
	            }
	        }
	    }
		return null;
	}

	private String getScannerResponse(User user) throws SAXException, IOException, ParserConfigurationException, JAXBException {
		ProcessBuilder pb = new ProcessBuilder("bash", "-c", "gvm-cli socket --socketpath "+socket+" --xml \""+xob.buildGetScanners(user)+"\"");
		String output = IOUtils.toString(pb.start().getInputStream());
		Document doc = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(new InputSource(new StringReader(output)));
		NodeList configNode = doc.getElementsByTagName("scanner");
		if (configNode != null) {
	        int length = configNode.getLength();
	        for (int i = 0; i < length; i++) {
	            if (configNode.item(i).getNodeType() == Node.ELEMENT_NODE) {
	                Element el = (Element) configNode.item(i);
	                if(el.getElementsByTagName("name").item(1).getTextContent().equals(ConstantStrings.SCANNER_NAME)) {
	                	return el.getAttribute("id");
	                }
	            }
	        }
	    }
		return null;
	}
	private String getCreateTargetRespnse(User user, HashMap<String, String> params) throws JAXBException, SAXException, IOException, ParserConfigurationException {
		
		ProcessBuilder pb = new ProcessBuilder("bash", "-c", "gvm-cli socket --socketpath "+socket+" --xml \""+xob.buildCreateTarget(user, params)+"\"");
		String output = IOUtils.toString(pb.start().getInputStream());
		Document doc = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(new InputSource(new StringReader(output)));
		NodeList ctResponseNode = doc.getElementsByTagName("create_target_response");
		Element ctResponse = (Element) ctResponseNode.item(0);
		if (ctResponse.getAttribute("status").equals("201"))
			return ctResponse.getAttribute("id");
		else
			return null;
	}

}