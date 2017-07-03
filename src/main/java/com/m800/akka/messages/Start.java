package com.m800.akka.messages;

import java.nio.file.Path;

/**
 * Start class, a Message (event) send to the Aggregator
 *
 **/
public class Start {

	public final Path targetFile;

	public Start(Path targetFile) {

		this.targetFile = targetFile;

	}

	public String getFilePath() {
		return this.targetFile.toString();
	}

	@Override
	public String toString() {

		return "Start-Of-File{ targetFile = " + this.targetFile + " }";

	}

}
