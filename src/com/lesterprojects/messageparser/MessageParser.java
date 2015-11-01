package com.lesterprojects.messageparser;

import java.util.Map;

public abstract class MessageParser {
	
	protected MessageParser m_next = null;
	protected Map<String, Object> m_data;
	protected boolean m_isDirty = false;
	
	public abstract void parse(String message);
	
	public void setNext(MessageParser next){
		m_next = next;
	}
	
	public void add(MessageParser next){
		if(m_next != null){
			m_next.add(next);
		}else{
			m_next = next;
		}
	}
	
	public boolean isDirty(){
		return m_isDirty;
	}
	public Map<String, Object> data(){
		m_isDirty = false;
		return m_data;
	}
	
	
}
