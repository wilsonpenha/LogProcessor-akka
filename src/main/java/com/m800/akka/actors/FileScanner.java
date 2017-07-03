package com.m800.akka.actors;

import java.io.File;

import org.apache.log4j.Logger;

<<<<<<< HEAD
import com.m800.akka.messages.Parse;
import com.m800.akka.messages.Scan;
=======
import com.m800.akka.actors.messages.Parse;
import com.m800.akka.actors.messages.Scan;
>>>>>>> 1ef49ac685c8113b4012390dbba91d16c237d2b3

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;

/**
 * The FileScanner Actor, to scans for files from a given directory.
 * 
 * @author
 * @version 1.0
 */
public class FileScanner extends UntypedAbstractActor {

	private static final Logger log = Logger.getLogger(FileScanner.class);
	
	/**
	 * Invoked by the ActorSystem to scan a given directory
	 * 
	 * @param The message to process
	 */
	public void onReceive(Object message) {
		ActorRef parserRef;
		if (message instanceof Scan) {
			Scan scan = (Scan) message;

			File directory = new File(scan.getDirectory());
			
			log.info("Scanning files into directory :" + scan.getDirectory());
			
			File[] files = directory.listFiles();

			int numberOfFiles = 0;

			for (File file : files) {
				if (file.isFile())
					numberOfFiles++;
			}
			ActorRef aggregatorRef = getContext().actorOf(Props.create(Aggregator.class, numberOfFiles), "aggregator");
			File file;
			for (int i = 0; i < files.length; i++) {
				file = files[i];
				// only process files into the given directory, do not handle sub-dirs
				if (!file.isFile())
					continue;

				// call the FileParser actor to process each file
				parserRef = getContext().actorOf(Props.create(FileParser.class, aggregatorRef), "parser-" + i);
				parserRef.tell(new Parse(file.toPath()), getSelf());
			}
		} else {
			log.error("Unexpected object message received: "+message);
			unhandled(message);
		}
	}
}
