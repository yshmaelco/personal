package co.yshmael.compliance.ba.screeningprofile;

import java.util.List;
import java.util.Map;

public abstract class GenericScreenProfileComparator {
	
	protected String useKey = "";
	
	public String getUseKey() {
		return useKey;
	}

	public void setUseKey(String useKey) {
		this.useKey = useKey;
	}

	public void compare(Map<String,List<String>> orig, Map<String,List<String>> result) {
		System.out.println("Start Checking [" + getUseKey() + "]");
		
		List<String> origList = (List<String>) orig.get(getUseKey());
		List<String> resultList = (List<String>) result.get(getUseKey());
		
		if(origList.size() != resultList.size()) {
			System.err.println(String.format("%s - ERROR!  List size doesn't match.  Expected: %s, Actual: %s" 
					,getUseKey()
					,origList.size()
					,resultList.size()
					));
		}
		
		// Compare Left to Right
		this.compareList("Expected", origList, resultList);
		
		
		// Compare Right to Left
		this.compareList("Actual",resultList, origList);
		
		
		
	}
	
	private void compareList(String message, List<String> left, List<String> right) {
		for(String compareValue : left) {
			
			boolean match = false;
			for(String r : right) {
				
				// It appears value contains no-breaking space (160). trim() does not handle it
				// String.replace(String.valueOf((char) 160), " ") is needed.
				if(r.replace(String.valueOf((char) 160), " ").trim().equals(compareValue.replace(String.valueOf((char) 160), " ").trim())) {
					match = true;
				}
			}
			if(!match)
				System.err.println(String.format("%s - [%s] No Matching found for this = [%s]" ,getUseKey(), message, compareValue));
		}
		
	}
	

}
