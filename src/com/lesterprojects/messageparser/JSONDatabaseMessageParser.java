package com.lesterprojects.messageparser;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.Set;
import java.util.HashMap;
import org.json.simple.JSONObject;

public class JSONDatabaseMessageParser extends DatabaseMessageParser {
	private static final String m_type = "json";
	private static final String m_splitter = ";";
	
	public JSONDatabaseMessageParser(){
		m_data = new HashMap<String, Object>();
	}
	
	@Override
	public void parse(String message) {
		
		String[] messageSplit = message.split(m_splitter);
		String messageType = messageSplit[0].split(":")[1];
		
		if(m_type.compareTo(messageType) != 0){
			if(m_next != null){
				m_next.parse(message);
			}
			
			return;
		}
		
		extractDatabaseInfo(messageSplit[1]);
		
		JSONParser parser = new JSONParser();
		
		try{
			JSONObject obj = (JSONObject) parser.parse(messageSplit[1]);
			extractData(obj.get(DatabaseInfoKey.DATA).toString());
			m_isDirty = true;
			
		}catch(ParseException pe){
			System.out.println("Parse Exception Error: Position: " + pe.getPosition());
		}

	}
	
	private void extractDatabaseInfo(String message){
		JSONParser parser = new JSONParser();
		try {
			JSONObject obj = (JSONObject) parser.parse(message);
			m_databaseInfo.put(DatabaseInfoKey.HOST, obj.get(DatabaseInfoKey.HOST));
			m_databaseInfo.put(DatabaseInfoKey.PORT, obj.get(DatabaseInfoKey.PORT));
			m_databaseInfo.put(DatabaseInfoKey.DOCUMENT, obj.get(DatabaseInfoKey.DOCUMENT));
			m_databaseInfo.put(DatabaseInfoKey.DATABASE, obj.get(DatabaseInfoKey.DATABASE));
			m_databaseInfo.put(DatabaseInfoKey.COMMAND, obj.get(DatabaseInfoKey.COMMAND));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void extractData(String dataMessage){
		JSONParser parser = new JSONParser();
		
		try {
			JSONObject obj = (JSONObject) parser.parse(dataMessage);
			
			@SuppressWarnings("unchecked")
			Set<Object> keys = obj.keySet();
			
			for(Object key: keys){
				System.out.println("Key:" + key.toString() + " Value: " + obj.get(key).toString());
				m_data.put(key.toString(), obj.get(key));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
