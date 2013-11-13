package co.yshmael.compliance.pars;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.velocity.VelocityEngineUtils;

import co.yshmael.TestBase;

public class RefreshParsCommonTable extends TestBase {
	
	@Autowired
	private VelocityEngine velocityEngine;

	
	@Test
	public void helloWorld() throws FileNotFoundException, UnsupportedEncodingException {
		
		List<String> tables = new ArrayList<String>();
		
		tables.add("common_company");
		tables.add("common_company_info");
		tables.add("common_country");
		tables.add("common_industry");
		tables.add("common_exchange");
		tables.add("common_company_issued");
		tables.add("common_company_delta");
		tables.add("common_company_delta_audit");
		tables.add("common_region");


		
		
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("tables", tables);
        model.put("targetDB", "pars");
        model.put("sourceDB", "compliance_prd");
        
        
        System.out.println("tables size: " + tables.size());
        logger.info("tables size: " + tables.size());
        
		String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "pars-common-tables-refresh.vm", model);
		
		PrintStream out = null;
		try {
		    out = new PrintStream(new FileOutputStream(OUTPUT_FILE), true,"UTF8");
		    out.print(text);
		}
		finally {
		    if (out != null) out.close();
		}
		
		
//        // Prepare context data
//        VelocityContext context = new VelocityContext(model);
//
//        StringWriter swOut = new StringWriter();
//        String templateStr = "$tutorial_name tutorials by $site";
//        
//        // Merge data and template
//        velocityEngine.evaluate( context, swOut, "log tag name", templateStr);
//        System.out.println(swOut);
	}
	
}
