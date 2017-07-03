package com.m800.akka.messages;

import java.nio.file.Path;

/**
 * Parse class, A Message passed to FileParser from FileScanner 
 *
 **/
public class Parse {

	public final String name;
	public final Path fileToParse;

	public Parse(Path fileToParse) {

		this.name = "parse";
		this.fileToParse = fileToParse;

	}

	public String getFilePath(){
		return this.fileToParse.toString();
	}
	
	@Override
	public String toString() {

		return "Parse{ fileToParse : " + this.fileToParse + "}";

	}

}
