package com.sybase.automation.framework.common;

import java.util.ArrayList;
import java.util.List;

public class QueryCriteria {
	
	List<String> clauses = new ArrayList<String>();
	
	public QueryCriteria clause(String str){
		this.clauses.add(str);
		return this;
	}

	public List<String> getAllClauses(){
		return this.clauses;
	}

}
