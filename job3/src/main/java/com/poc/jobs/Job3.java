package com.poc.jobs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom.JDOMException;
import org.jdom.input.DOMBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import com.poc.common.IJob;


/**
 * Reference 1 :
 * http://static.springsource.org/spring-ws/site/reference/html/common.html
 * Handle JDom Node with XPath -- private XPathOperations template = new
 * Jaxp13XPathTemplate();
 * 
 * public void doXPath(Source request) { String name =
 * template.evaluateAsString("/Contacts/Contact/Name", request); // do something
 * with name } Reference 2 :
 * http://static.springsource.org/spring-ws/site/reference/html/tutorial.html
 * 
 * http://www.mongodb.org/display/DOCS/SQL+to+Mongo+Mapping+Chart
 * http://software.dzhuvinov.com/json-rpc-2.0-server.html
 * 
 * @author Kaniska_Mandal
 * 
 */
@Controller
@RequestMapping(value = "/operation/", headers = Job3.acceptHeader)
public class Job3 implements IJob{
	protected static final String acceptHeader = "Accept=application/json, application/xml, text/xml";
	int opportunity = 0;
	int location = 0;
	//private Log log = LogFactory.getLog(getClass());


	private org.jdom.xpath.XPath idExpr;
	{
		try {
			idExpr = org.jdom.xpath.XPath.newInstance("//account[@id]");
		} catch (JDOMException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/fetch/", method = RequestMethod.GET)
	public @ResponseBody
	String findAll() {
		return "<test>5677</test>";
	}

	// Experimental
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<String> addEntity(@RequestBody String request) {
		// Load the XML document
		InputStream ioStream = null;
		try {
			
			ioStream = new ByteArrayInputStream(request.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// configure the document builder factory
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);

		// get a builder to create a DOM document
		DocumentBuilder domBuilder = null;
		try {
			domBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		}

		// parse the damned document
		org.w3c.dom.Document w3cDocument = null;
		//String str = "";
		try {
	        System.out.println("input string yo gaba gaba ");
	        
			w3cDocument = domBuilder.parse(ioStream);
			
	        
		} catch (SAXException e) {
		
	       e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// create a JDOM DOM Builder
		DOMBuilder jdomBuilder = new DOMBuilder();
		// create a JDOM Document from a w3c DOM
		org.jdom.Document jdomDocument = jdomBuilder.build(w3cDocument);

		org.jdom.Element rootElement = jdomDocument.getRootElement();

		String rootElemName = rootElement.getName();
		System.out.println("Root er ami Root er tumi " + rootElemName);
		// CREATE //UPDATE //DELETE
		
		int documentSize = 0;
		
		// iterate through child elements of root
		for (Object obj : rootElement.getChildren()) {
			if (!(obj instanceof org.jdom.Element)) {
				break;
		}

			org.jdom.Element element = (org.jdom.Element) obj;
			if (!element.getChildren().isEmpty()) {
				break; // don't proceed -- this is just a mere parent name -
						// like 'Opportunities' that hold Opportunity elements
			}

			// Create
			if(element.getName().equals("opportunity")){
				++opportunity; 
				System.out.println("I am here element name Element print opportunity: " + element.getName());

			}
			System.out.println(opportunity + "    opportunity   " + location + "    location");
			// finally save the parentDocument
		/*	if (!parentDocument.toMap().isEmpty()) {
				mongoDataAccessService.add(parentDocument, rootElemName);
			}*/

		}
			return new ResponseEntity<String>("Hello Mongo", HttpStatus.ACCEPTED);
		
		}
}