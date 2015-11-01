package com.lesterprojects.messageparser;

import java.util.HashMap;
import java.util.Map;

public abstract class DatabaseMessageParser extends MessageParser {

	protected Map<String, Object> m_databaseInfo = new HashMap<String, Object>();
	
	public Map<String,Object> databaseInfo(){
		return m_databaseInfo;
	}

}
