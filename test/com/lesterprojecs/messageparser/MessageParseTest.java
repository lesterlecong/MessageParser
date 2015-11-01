package com.lesterprojecs.messageparser;

import com.lesterprojects.messageparser.JSONDatabaseMessageParser;
import com.lesterprojects.messageparser.MessageParser;
import com.lesterprojects.messageparser.XMLDatabaseMessageParser;

public class MessageParseTest {

	public static void main(String[] args) {
		MessageParser messageParser = new JSONDatabaseMessageParser();
		messageParser.add(new XMLDatabaseMessageParser());
		
		String message = "type:json;{\"name\":\"Lester\",\"age\":26,\"occupation\":\"Software Engineer\"}";
		messageParser.parse(message);
		
		while(!messageParser.isDirty());
		
		System.out.println(messageParser.data().toString());
	}

}
