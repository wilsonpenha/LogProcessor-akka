package com.m800.akka.actors;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.log4j.Logger;

<<<<<<< HEAD
import com.m800.akka.messages.End;
import com.m800.akka.messages.Line;
import com.m800.akka.messages.Parse;
import com.m800.akka.messages.Start;
=======
import com.m800.akka.actors.messages.End;
import com.m800.akka.actors.messages.Line;
import com.m800.akka.actors.messages.Parse;
import com.m800.akka.actors.messages.Start;
>>>>>>> 1ef49ac685c8113b4012390dbba91d16c237d2b3

import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;

/**
 * The FileParser actor, will parse files from the given directory
 * 
 * @author 
 * @version 1.0
 */
public class FileParser extends UntypedAbstractActor {

	private static final Logger log = Logger.getLogger(FileParser.class);

    private ActorRef aggregator;

    public FileParser(ActorRef aggregator) {
        this.aggregator = aggregator;
    }

	/**
	 * Invoked by the FileScanner actor to scan a given file/directory
	 * 
	 * @param The message to process
	 */
    public void onReceive(Object message) {
        if (message instanceof Parse) {
            Parse parseMessage = (Parse) message;

			log.info("Parsing file from directory :" + parseMessage.getFilePath());

            Path file = Paths.get(parseMessage.getFilePath());
            try {
                BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8);
                
                String line = null;
                
                // send a Start message to the aggregator actor, 
                aggregator.tell(new Start(parseMessage.fileToParse), getSelf());
                
                // send each line as Line message to the aggregator actor, 
                while ((line = reader.readLine()) != null) {
                    aggregator.tell( new Line(parseMessage.fileToParse,line), getSelf());
                }
                
                // send a End message to the aggregator actor, 
                aggregator.tell(new End(parseMessage.fileToParse), getSelf());
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
			log.error("Unexpected object message received: "+message);
            unhandled(message);
        }
    }

}
