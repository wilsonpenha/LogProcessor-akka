package com.m800.akka.actors.messages;

import java.nio.file.Path;

/**
 * End class, a Message (event) send to the Aggregator
 *
 **/
public class End {

	public final Path targetFile;
	public final long finalLineNumber;

	public End(Path targetFile, long finalLineNumber) {

		this.targetFile = targetFile;
		this.finalLineNumber = finalLineNumber;

	}

	public End(Path targetFile) {

		this.targetFile = targetFile;
		this.finalLineNumber = 0;

	}

	public Path getFilePath(){
		return this.targetFile;
	}
	
	@Override
	public String toString() {

		return "End-Of-File{ targetFile = " + this.targetFile + ", finalLineNumber = " + this.finalLineNumber + " }";

	}

}
