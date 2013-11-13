package co.yshmael.compliance.pars;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.transform.stream.StreamSource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.xstream.XStreamMarshaller;

import co.yshmael.TestBase;
import co.yshmael.xml.jmeter.Sample;
import co.yshmael.xml.jmeter.TestResults;

public class Sp_spaceusedParseTest extends TestBase {
	
	@Autowired
	private XStreamMarshaller xstreamMarshaller;
	
	private static final String XML_FILE_NAME = "input.xml";
	private static final String OUTPUT_FILE_NAME = "result.csv";
	
	
	@Test
	public void xml() throws Exception  {
		
		
		
		FileInputStream is = new FileInputStream(XML_FILE_NAME);
		TestResults result = (TestResults) xstreamMarshaller.unmarshal(new StreamSource(is));
		
		
		File file = new File(OUTPUT_FILE_NAME);
		
		if(file.exists())
			System.out.println("Deleting file successfule: " + file.delete());
		
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		
		//
		// Header
		//
		String header = "Date|reserved|data|index_size|unused";
		bw.write(header + "\n");
		
		
		for(Sample s : result.getSample()) {
			
			// split to NEW LINE
			String[] data = s.getResponseData().split("\n");
			
			
			String msg = new Date(s.getTs()) + "|";	
			
			
			String[] spaceData = data[5].split("\t");
			for(String space : spaceData) {
				msg += space.replace("KB", "").replace(" ", "") + "|";
			}
			
			System.out.println(msg);
			bw.write(msg + "\n");

		}
		
		bw.close();
		
	}
}
