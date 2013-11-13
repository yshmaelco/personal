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

public class SybaseGenerateGenericScripts extends TestBase {
	
	@Autowired
	private VelocityEngine velocityEngine;

	
	@Test
	public void helloWorld() throws FileNotFoundException, UnsupportedEncodingException {
		
		List<String> tables = new ArrayList<String>();
		
		tables.add("spr_source_system_audit");
		tables.add("pars_position_arch");
		tables.add("pars_rule_calc_arch");
		tables.add("pars_company_filter");
		tables.add("pars_position_to_aggr_pos");
		tables.add("spr_source_system");
		//tables.add("rs_threads");
		tables.add("common_group_audit");
		tables.add("pars_rule_calc_delta_import");
		tables.add("common_message_comment_hist");
		tables.add("pars_output_flag_delta_recalc");
		tables.add("pars_rule_calc_changes");
		tables.add("spr_legal_entity_audit");
		tables.add("pars_identity_filter");
		//tables.add("rs_ticket_history");
		tables.add("pars_company_filter_audit");
		tables.add("pars_aggr_position_arch");
		tables.add("spr_submitting_entity");
		tables.add("common_division_audit");
		tables.add("pars_output_flag_cutoff");
		tables.add("pars_system_value");
		tables.add("pars_business_date");
		//tables.add("rs_lastcommit");
		tables.add("pars_list_value");
		tables.add("pars_arch_date");
		tables.add("pars_rule_limit_approval");
		tables.add("pars_legislation");
		tables.add("spr_legal_entity");
		tables.add("pars_system_properties");
		tables.add("pars_rule_limit_approval_a");
		tables.add("spr_hk_legal_entity");
		tables.add("spr_hk_position_exclusion");
		tables.add("spr_hk_legal_entity_audit");
		tables.add("spr_position_exclusion");
		tables.add("common_system");
		tables.add("common_region");
		tables.add("pars_region_order");
		tables.add("common_message_type");
		tables.add("pars_output_flag_breach_type");
		tables.add("pars_output_flag_issue_type");
		tables.add("pars_rule_limit");
		tables.add("pars_output_flag_issue");
		tables.add("pars_output_flag_issue_audit");
		tables.add("pars_region_user");
		//tables.add("spr_report_data_suffix_mapping");
		tables.add("pars_rule_limit_audit");
		tables.add("pars_source_regions");
		tables.add("pars_rule_industries");
		tables.add("spr_hk_reportable_stock");
		tables.add("pars_source_system");
		tables.add("spr_hk_uploaded_file");
		tables.add("pars_rule_threshold");
		tables.add("pars_mppm_raw");
		tables.add("common_country");
		tables.add("common_industry");
		tables.add("pars_rule");
		tables.add("pars_product_exclusion_audit");
		tables.add("pars_product_exclusion");
		tables.add("pars_rule_calc_snapshot");
		tables.add("pars_rule_threshold_subs");
		tables.add("pars_rule_threshold_subs_a");
		tables.add("pars_rule_threshold_audit");
		tables.add("pars_source_system_audit");
		tables.add("pars_rule_audit");
		tables.add("spr_hk_uploaded_file_audit");
		tables.add("common_company_delta");
		tables.add("spr_asic_submission_audit");
		tables.add("common_exchange");
		tables.add("pars_source_bypass");
		tables.add("common_company_delta_audit");
		tables.add("spr_hk_report_data");
		tables.add("pars_system_properties_audit");
		tables.add("pars_aggr_pos_import");
		tables.add("spr_uploaded_file");
		tables.add("pars_position_inc_exc_audit");
		tables.add("pars_output_flag_delete_audit");
		tables.add("pars_output_flag_complete");
		tables.add("common_department_audit");
		tables.add("pars_output_flag_divisions");
		tables.add("pars_output_flag_user_details");
		tables.add("pars_of_user_details_audit");
		tables.add("pars_activity_run");
		tables.add("pars_output_flag");
		tables.add("spr_uploaded_file_audit");
		tables.add("pars_output_flag_audit");
		tables.add("pars_rule_calc_import");
		tables.add("pars_output_flag_recalc");
		tables.add("pars_activity");
		tables.add("spr_hk_position");
		tables.add("pars_source_log");
		tables.add("pars_activity_audit");
		tables.add("pars_rule_companies");
		tables.add("spr_report_data");
		tables.add("pars_rule_calc_delete_audit");
		tables.add("pars_position_audit");
		tables.add("common_company_info");
		tables.add("common_company");
		tables.add("pars_manual_position");
		tables.add("pars_aggr_position");
		tables.add("common_company_issued");
		tables.add("pars_manual_position_audit");
		tables.add("spr_position");
		
		
		
		tables.add("pars_position");
		tables.add("common_message_console");
		tables.add("pars_rule_calc");

		
		
        Map<String, List<String>> model = new HashMap<String, List<String>>();
        model.put("tables", tables);
        
        System.out.println("tables size: " + tables.size());
        logger.info("tables size: " + tables.size());
        
		String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "sql-generic.vm", model);
		
		PrintStream out = null;
		try {
		    out = new PrintStream(new FileOutputStream("database-scripts.sql"), true,"UTF8");
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
