package pl.orange.bst.mixer.rest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.google.gson.Gson;

import pl.orange.bst.mixer.ConstantStrings;
import pl.orange.bst.mixer.openvas.OpenVasClient;
import pl.orange.bst.mixer.openvas.pojo.ReportXml;
import pl.orange.bst.mixer.openvas.pojo.RestRequestBody;
import pl.orange.bst.mixer.openvas.pojo.User;
import pl.orange.bst.mixer.openvas.pojo.Vuln;

@RestController
public class MixerRestController {

	private final OpenVasClient openVasClient;

	@Autowired
	MixerRestController(OpenVasClient openVasClient){
		this.openVasClient = openVasClient;
	}

    @RequestMapping(value="/initialize", method = RequestMethod.POST)
    public ResponseEntity<HashMap<String, String>> initialize(@RequestBody User user) throws JAXBException, IOException, ParserConfigurationException {
    	try {
    		user = user.prepare();
    		HashMap<String, String> responseMap = openVasClient.initializeOpenVas(user);
    	
    		return new ResponseEntity<>(responseMap, HttpStatus.OK);
    	} catch (SAXException s) {
    		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    	}
    }
    @RequestMapping(value="/createtarget", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, String>> createTarget(@RequestBody RestRequestBody body) throws JAXBException, IOException, ParserConfigurationException {
    	try {
    		HashMap<String, String> responseMap = new HashMap<>();
    		body = body.prepare();
    		String target_id = openVasClient.createTarget(body);
    		if(target_id != null && !target_id.equals("")) {
    			responseMap.put(ConstantStrings.TARGET_ID, target_id);
    			return new ResponseEntity<>(responseMap, HttpStatus.OK);
    		}
    		else 
    			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    	} catch (SAXException s) {
    		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    	}
    }
    @RequestMapping(value="/createtask", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, String>> createTask(@RequestBody RestRequestBody body) throws JAXBException, IOException, ParserConfigurationException {
    	try {
    		HashMap<String, String> responseMap = new HashMap<>();
    		body = body.prepare();
    		String task_id = openVasClient.createTask(body);
    		if(task_id != null && !task_id.equals("")) {
    			responseMap.put(ConstantStrings.TASK_ID, task_id);
    			return new ResponseEntity<>(responseMap, HttpStatus.OK);
    		}
    		else 
    			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    	} catch (SAXException s) {
    		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    	}
    }
    @RequestMapping(value="/modifytask/{taskId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, String>> modifyTask(@RequestBody RestRequestBody body, @PathVariable(name="taskId") String taskId) throws JAXBException, IOException, ParserConfigurationException {
    	try {
    		HashMap<String, String> responseMap = new HashMap<>();
    		body = body.prepare();
    		taskId = ConstantStrings.escape(taskId);
    		String status = openVasClient.modifyTask(body,taskId);
    		if(status.equals(ConstantStrings.STATUS_OK)) {
    			responseMap.put(ConstantStrings.STATUS, ConstantStrings.STATUS_OK);
    			return new ResponseEntity<>(responseMap, HttpStatus.OK);
    		}
    		else 
    			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    	} catch (SAXException s) {
    		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    	}
    }
    @RequestMapping(value="/starttask", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, String>> startTask(@RequestBody RestRequestBody body) throws JAXBException, IOException, ParserConfigurationException {
    	try {
    		HashMap<String, String> responseMap = new HashMap<>();
    		body = body.prepare();
    		String report_id = openVasClient.runTask(body);
    		if(report_id!= null && !report_id.equals("")) {
    			responseMap.put(ConstantStrings.REPORT_ID, report_id);
    			return new ResponseEntity<>(responseMap, HttpStatus.OK);
    		}
    		else 
    			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    	} catch (SAXException s) {
    		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    	}
    }
    @RequestMapping(value="/checktask", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, String>> checkTask(@RequestBody RestRequestBody body) throws JAXBException, IOException, ParserConfigurationException {
    	try {
    		HashMap<String, String> responseMap = new HashMap<>();
    		body = body.prepare();
    		String status = openVasClient.taskStatus(body);
    		if(status!= null && !status.equals("")) {
    			responseMap.put(ConstantStrings.STATUS, status);
    			return new ResponseEntity<>(responseMap, HttpStatus.OK);
    		}
    		else 
    			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    	} catch (SAXException s) {
    		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    	}
    }
    @RequestMapping(value="/getreport", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReportXml> getReport(@RequestBody RestRequestBody body) throws JAXBException, IOException, ParserConfigurationException, XPathExpressionException {
    	try {
    		body = body.prepare();
    		ReportXml status = openVasClient.getReport(body);
    		return new ResponseEntity<>(status, HttpStatus.OK);
    	} catch (SAXException s) {
    		return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    	}
    }
}