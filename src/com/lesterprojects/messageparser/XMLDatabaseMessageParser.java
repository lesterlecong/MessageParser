package com.lesterprojects.messageparser;

public class XMLDatabaseMessageParser extends MessageParser {

	@Override
	public void parse(String message) {
		System.out.println("Welcome to xml with message: " + message);

	}

}
