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

public class Prp5ResultParseTest extends TestBase {
	
	@Autowired
	private XStreamMarshaller xstreamMarshaller;
	
	private static final String XML_FILE_NAME = "pars.xml";
	private static final String OUTPUT_FILE_NAME = "result.csv";
	
	private static final String PROFILE_PROD = "PROD";
	private static final String PROFILE_DEV = "DEV";
	
	@Test
	public void xml() throws Exception  {
		
		
//		TestResults out = new TestResults();
//		out.setVersion("testversion");
//		StringWriter stringWriter = new StringWriter();
//		xstreamMarshaller.marshal(out, new StreamResult(stringWriter));
//		System.out.println("--" + stringWriter.toString());
//		System.out.println(xstreamMarshaller.getXStream().toXML(out));
		
		FileInputStream is = new FileInputStream(XML_FILE_NAME);
		TestResults result = (TestResults) xstreamMarshaller.unmarshal(new StreamSource(is));
		
		
		File file = new File(OUTPUT_FILE_NAME);
		
		if(file.exists())
			System.out.println("Deleting file successfule: " + file.delete());
		
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		Map<String,Long> dbCount = new TreeMap<String,Long>();
		
		String profile = PROFILE_PROD;
//		String profile = PROFILE_DEV;
		
		if(profile.equals(PROFILE_PROD)) {
			dbCount.put("REPSRMGPRD04_RSSD", 0l);
			dbCount.put("REPSRMGPRD04_RS", 0l);
			
		} else if (profile.equals(PROFILE_DEV)) {
			dbCount.put("conflict", 0l);
			dbCount.put("conflict_tst", 0l);
			dbCount.put("compliance_prd_refresh", 0l);
			dbCount.put("compliance_tst", 0l);
			dbCount.put("macts_prd_refresh", 0l);
			dbCount.put("macts_tst", 0l);
			dbCount.put("pars_prd_refresh", 0l);
			dbCount.put("pars_tst", 0l);
			dbCount.put("pars_ui_tst", 0l);
			dbCount.put("REPSRMGDEV04_RSSD", 0l);
			
		}  
		
		dbCount.put("compliance_prd", 0l);
		dbCount.put("macts", 0l);
		
		dbCount.put("bcl_prd", 0l);
		dbCount.put("ecm", 0l);
		dbCount.put("ecm_load", 0l);
		dbCount.put("rmd_prd", 0l);
		dbCount.put("restrictions", 0l);
		dbCount.put("opr_reporting", 0l);	
		dbCount.put("orr_prd", 0l);
		dbCount.put("pars", 0l);		
		dbCount.put("pars_ui", 0l);
		dbCount.put("qmrs", 0l);
		
		
		dbCount.put("Release_Central", 0l);
		dbCount.put("Release_Central_ORR", 0l);
		dbCount.put("Release_Complia", 0l);
		dbCount.put("Release_Compliance", 0l);
		dbCount.put("Release_PCM", 0l);
		
		
		dbCount.put("sybsecurity", 0l);
		dbCount.put("sybsystemprocs", 0l);		
		dbCount.put("tempdb", 0l);		
		dbCount.put("master", 0l);
		dbCount.put("mondb", 0l);
		dbCount.put("proxy", 0l);
		dbCount.put("SA_tempdb", 0l);
		dbCount.put("dbccdb", 0l);
		
		
		
		String header = "Date|";
		for (Map.Entry<String, Long> entry : dbCount.entrySet()) {
			header += entry.getKey()+ "|";
		}
		bw.write(header + "\n");
		
		for(Sample s : result.getSample()) {
			
			// split to NEW LINE
			String[] data = s.getResponseData().split("\n");
			
			for (Map.Entry<String, Long> entry : dbCount.entrySet()) {
				dbCount.put(entry.getKey(), 0l);
			}
			
			for(String line : data) {
				
				// split to COLUMNS
				String[] columns = line.split("\t");
				
				String dbname = columns[7];
				
				
				if( dbname.equals("dbname")) {
					// ignore
				} else if( dbname.equals("pars_prd_refres")) {
					long cnt = dbCount.get("pars_prd_refresh");
					cnt++;
					dbCount.put(dbname, cnt);					
				} else if(!dbCount.containsKey(dbname)) {
					System.out.println("unknown db=" + dbname);
					bw.write("unknown db=" + dbname + "\n");
//					dbname = "UNKNOWN";
//					long cnt = dbCount.get(dbname);
//					cnt++;
//					dbCount.put(dbname, cnt);
				} else {
					long cnt = dbCount.get(dbname);
					cnt++;
					dbCount.put(dbname, cnt);
				}
				
			}
			
			String msg = new Date(s.getTs()) + "|";
			
			for (Map.Entry<String, Long> entry : dbCount.entrySet()) {
				msg += entry.getValue() + "|";
			}
			
			msg += s.getTs();
			bw.write(msg + "\n");

		}
		
		bw.close();
		System.out.println("Done.");
		
	}
}
