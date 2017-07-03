package com.m800.akka.messages;

import java.io.File;

/**
 * Scan class to pass as message to the FileScanner Actor 
 *
 **/
public class Scan {

	private File file;

	public Scan(String path) {

		this.file = new File(path);

	}

	public String getDirectory(){
		return this.file.getPath();
	}
}
