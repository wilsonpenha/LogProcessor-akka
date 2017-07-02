package com.m800.akka.actors;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.m800.akka.actors.messages.End;
import com.m800.akka.actors.messages.Line;
import com.m800.akka.actors.messages.Start;

import akka.actor.UntypedAbstractActor;

/**
 * The Aggregator program counts the number of lines for each file.
 * 
 * @author 
 * @version 1.0
 */
public class Aggregator extends UntypedAbstractActor {

	private static final Logger log = Logger.getLogger(Aggregator.class);
	
	private int numberOfFiles;
    private HashMap<String, Integer> wordCount;
    private int count;

    public Aggregator(int numberOfFiles) {
        this.numberOfFiles = numberOfFiles;
        this.wordCount = new HashMap<>();
    }

    /**
	 * Invoked by the Aggregator actor to handle the Start, Line and End events
	 * 
	 * @param The message to process
	 */
    public void onReceive(Object message) {
        if (message instanceof End) {
            End end = (End) message;
            
            log.debug("Message End of file for : "+end.getFilePath().toString()+" received!");
            
            count++;
            
            String path = end.getFilePath().toString();
            
            System.out.println("Words count of file : " + path + " is " + wordCount.get(path) + "words");
            
            if (count >= numberOfFiles) {
                getContext().system().terminate();
            }
        } else if (message instanceof Line) {
        	
            Line line = (Line) message;
            
            log.debug("Message Line of file for : "+line.getFilePath().toString()+" received!");

            int lineWordsCounts = line.read.split(" ").length;
            
            String path = line.getFilePath();
            
            if (wordCount.containsKey(path)) {
            	wordCount.put(path, wordCount.get(path) + lineWordsCounts);
            } else {
            	wordCount.put(path, lineWordsCounts);
            }
            
        } else if (message instanceof Start) {
            Start start = (Start) message;
            
            log.debug("Message Start of file for : "+start.getFilePath().toString()+" received!");

        } else {
			log.error("Unexpected object message received: "+message);
            unhandled(message);
        }
    }

}
