package com.java.rest.service;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CustomerService {
	public String DATA_FILE;
		
	public long persist(Customer customer, long customerId) throws IOException {
	    Properties properties = new Properties();
	    DATA_FILE="C:\\TEMP\\customer-data.txt";
		properties.load(new FileInputStream(DATA_FILE));

	    if (customerId == 0) {
	        customerId = System.currentTimeMillis();
	    }

	    properties.setProperty(String.valueOf(customerId),
	        customer.getFirstName() + "," + customer.getLastName() +
	        "," + customer.getZipcode());
	    properties.store(new FileOutputStream(DATA_FILE), null);
	    return customerId;
	}

	public Customer buildCustomer(Customer customer, InputStream customerData) throws Exception {
	    if (customer == null) {
	        customer = new Customer();
	    }
	    DocumentBuilder documentBuilder =  DocumentBuilderFactory.newInstance().newDocumentBuilder();
	    Document document = documentBuilder.parse(customerData);
	    document.getDocumentElement().normalize();
        NodeList nodeList = document.getElementsByTagName("customer");
        Node customerRoot = nodeList.item(0);
	        if (customerRoot.getNodeType() == Node.ELEMENT_NODE) {
	            Element element = (Element) customerRoot;
	            NodeList childNodes = element.getChildNodes();
	            for (int i = 0; i < childNodes.getLength(); i++) {
	                Element childElement = (Element)childNodes.item(i);
	                String tagName = childElement.getTagName();
	                String textContent = childElement.getTextContent();
	                if (tagName.equals("firstName")) {
	                    customer.setFirstName(textContent);
	                } else if (tagName.equals("lastName")) {
	                    customer.setLastName(textContent);
	                } else if (tagName.equals("zipcode")) {
	                    customer.setZipcode(textContent);
	                }
	            }
	        } else {
	        	throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);  
	        }
	        
	        return customer;
	}


}
