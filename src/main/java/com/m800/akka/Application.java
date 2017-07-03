package com.m800.akka;

import org.apache.log4j.Logger;

import com.m800.akka.actors.FileScanner;
import com.m800.akka.messages.Scan;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * The Application program to run the ActorSystem for parsing files in a given directory.
 * 
 * @author 
 * @version 1.0
 */
public class Application {
	private static final Logger log = Logger.getLogger(FileScanner.class);
	
	public static void main(String[] args) {

		if (args.length < 1){ 
			System.out.println("Usage: java -jar log-process.jar <directorypath>");
			System.exit(0);
		}
		
		String path=args[0];
			
		log.info("Initiating the ActorSystem 'logProcessor'");

		ActorSystem actorSystem = ActorSystem.create("logProcessor");
		
		log.info("Sending the path : "+path+" to be scan by FileScanner actor!");

		ActorRef fileScannerRef = actorSystem.actorOf(Props.create(FileScanner.class), "fileScanner");
		
		fileScannerRef.tell(new Scan(path), ActorRef.noSender());
	}
}
