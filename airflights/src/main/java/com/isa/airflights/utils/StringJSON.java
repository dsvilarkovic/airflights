package com.isa.airflights.utils;

import java.io.Serializable;

public class StringJSON implements Serializable{
	private static final long serialVersionUID = 8632097138967770020L;
	private String text;
	
	public StringJSON(String text) {
		this.setText(text);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
