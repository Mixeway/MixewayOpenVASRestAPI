package pl.orange.bst.mixer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import pl.orange.bst.mixer.openvas.OpenVasClient;
import pl.orange.bst.mixer.openvas.helper.XmlOperationBuilder;
import pl.orange.bst.mixer.openvas.pojo.DeleteTarget;
import pl.orange.bst.mixer.openvas.pojo.User;

@SpringBootApplication
public class MixerApplication {


	public static void main(String[] args)  {
		SpringApplication.run(MixerApplication.class, args);
		System.out.print("Sarted REST API");

		
	}
	
}

