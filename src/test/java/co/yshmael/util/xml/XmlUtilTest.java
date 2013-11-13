package co.yshmael.util.xml;

import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import co.yshmael.TestBase;
import co.yshmael.xml.Customer;
import co.yshmael.xml.castor.XMLConverter;

public class XmlUtilTest extends TestBase {
	
	@Autowired
	private XMLConverter xmlConverter;
	
	private static final String XML_FILE_NAME = "customer.xml";
	
	@Test
	public void xml() throws Exception  {
		
		System.out.println("xmlConverter: " + xmlConverter);
		
		
		Customer customer = new Customer();
		customer.setName("mkyong");
		customer.setAge(30);
		customer.setFlag(true);
		customer.setAddress("This is address");
 
		System.out.println("Convert Object to XML!");
		//from object to XML file
		xmlConverter.convertFromObjectToXML(customer, XML_FILE_NAME);
		System.out.println("Done \n");
 
		System.out.println("Convert XML back to Object!");
		//from XML to object
		Customer customer2 = (Customer)xmlConverter.convertFromXMLToObject(XML_FILE_NAME);
		System.out.println(customer2);
		System.out.println("Done");
		
	}

}
