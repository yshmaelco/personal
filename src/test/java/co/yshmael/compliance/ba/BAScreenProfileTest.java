package co.yshmael.compliance.ba;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import co.yshmael.TestBase;
import co.yshmael.compliance.ba.screeningprofile.CharacterMappingFileSPComparator;
import co.yshmael.compliance.ba.screeningprofile.DictionarySPComparator;
import co.yshmael.compliance.ba.screeningprofile.FileSpecificationSPComparator;
import co.yshmael.compliance.ba.screeningprofile.FilterVersionsSPComparator;
import co.yshmael.compliance.ba.screeningprofile.LinkOptionSPComparator;
import co.yshmael.compliance.ba.screeningprofile.RiskScorecardSPComparator;
import co.yshmael.compliance.ba.screeningprofile.ScreenAlgorithmSPComparator;
import co.yshmael.compliance.ba.screeningprofile.ScreeningOptionsSPComparator;

public class BAScreenProfileTest extends TestBase {

//	@Resource(name = "ba_sp_actimize_bcp")
//	@Resource(name = "ba_sp_vessels")
//	@Resource(name = "ba_sp_customers_sanctions_only")
//	@Resource(name = "ba_sp_customers_pep_only")
//	@Resource(name = "ba_sp_customers_bfs_sanctions_only")
//	@Resource(name = "ba_sp_customers_bfs_pep_only")
//	@Resource(name = "ba_sp_customers_edd_only")
//	@Resource(name = "ba_sp_vendors_sanctions_only") -- configuration not yet complete		
	private Map<String,List<String>> screenProfile;
	
	
	@Resource(name = "feedFilesMapping")
	private Map<String, Map<String,List<String>>> feedFilesMapping;

	private static final String INPUT_FILE_NAME = "src/test/resources/input-files/ba-input.txt";

	@SuppressWarnings("unchecked")
	@Test
	public void xml() throws Exception {
		

//		String testFile = "GlobeClientCRE_Customer_PEP_Only";
//		String testFile = "GlobeClientCRE_Customer_Sanctions_Only";
//		String testFile = "TCG_CAS_GT_Customer_PEP_Only";
//		String testFile = "TCG_CAS_GT_Customer_Sanctions_Only";
//		String testFile = "MCAF_MacLease_Daily_Customer_PEP_Only";
//		String testFile = "MCAF_MacLease_Daily_Customer_Sanctions_Only";
		
		
		//  BFS_FSG
//		String testFile = "FSG_Daily_Customers_BFS_PEP_Only";
//		String testFile = "FSG_Daily_Customers_BFS_Sanctions_Only";
		
		String testFile = "Rewash_Extract_Customers_BFS_PEP_Only";
//		String testFile = "Rewash_Extract_Customers_BFS_Sanctions_Only";
		
		
		//  MacLeasing2
//		String testFile = "MCAF_InfoLease_Monthly_AU_Customer_PEP_Only";
//		String testFile = "MCAF_InfoLease_Monthly_AU_Customer_Sanctions_Only";
//		String testFile = "MCAF_InfoLease_Monthly_UK_Customer_PEP_Only";
//		String testFile = "MCAF_InfoLease_Monthly_UK_Customer_Sanctions_Only";
		
		
		
		
		
		// Get screening profile for testing
		screenProfile = (Map<String, List<String>>) feedFilesMapping.get(testFile).get("Screening Profile");
		
		
		// Adding file specification		
		List<String> fileSpecList = feedFilesMapping.get(testFile).get("File Specification");
		screenProfile.put("File Specification", fileSpecList);
		
		

		Map<String,List<String>> resultMap = new HashMap<String, List<String>>();

		try {
			FileInputStream in = new FileInputStream(INPUT_FILE_NAME);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			while ((strLine = br.readLine()) != null) {
				String[] line = strLine.split("\t");
				
				String key = line[0].replace(":", "");
				String value = "";
				if(line.length > 1 )
					value = line[1];
				
				//Special Handling
				if(key.equals("Filter Versions")) {
					value = value.split(":")[0];
				}
				
				if(!resultMap.containsKey(key)) {
					List<String> values = new ArrayList<String>();
					values.add(value);
					resultMap.put(key, values);
				} else {
					List<String> values = (List<String>) resultMap.get(key);
					values.add(value);
				}
				
					
			}

			System.out.println("--------------------------------------------------------------");
			System.out.println("Batch ID: " + resultMap.get("Batch ID").get(0));
			System.out.println("File Specification: " + resultMap.get("File Specification").get(0));
			System.out.println("Original Filename: " + resultMap.get("Original Filename").get(0));
			System.out.println("User ID: " + resultMap.get("User ID").get(0));
			System.out.println("--------------------------------------------------------------");
			
			
			new FileSpecificationSPComparator().compare(screenProfile,resultMap);
			new ScreenAlgorithmSPComparator().compare(screenProfile,resultMap);
			new RiskScorecardSPComparator().compare(screenProfile,resultMap);
			new LinkOptionSPComparator().compare(screenProfile,resultMap);
			new ScreeningOptionsSPComparator().compare(screenProfile,resultMap);
			new DictionarySPComparator().compare(screenProfile,resultMap);
			new CharacterMappingFileSPComparator().compare(screenProfile,resultMap);
			new FilterVersionsSPComparator().compare(screenProfile,resultMap);
			
			
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}

	}
}
