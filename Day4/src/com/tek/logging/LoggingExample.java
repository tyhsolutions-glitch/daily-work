package com.tek.logging;

import java.util.logging.Logger;

public class LoggingExample {
      
	private static final Logger logger = Logger.getLogger(LoggingExample.class.getName());
	public static void main(String[] args) {
		logger.info("Application Started");
		
		logger.warning("Low memory");

		logger.severe("System failure");
	}
	
}
