package com.m800.akka.actors.messages;

import java.nio.file.Path;

/**
 * Line class, a Message (event) send to the Aggregator
 *
 **/
public class Line {

	public final Path targetFile;
	public final String read;

	public Line(Path targetFile, String read) {

		this.targetFile = targetFile;
		this.read = read;

	}

	public String getFilePath(){
		return this.targetFile.toString();
	}

	@Override
	public String toString() {

		return "Line{ " + "targetFile = " + this.targetFile + ", read = "
				+ this.read + " }";

	}

}
