package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A simple class that uses the SLF4J logging framework.
 *
 * @author javiergs
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Logger logger = LoggerFactory.getLogger(Main.class);
        logger.trace("1. This is for tracing");
        logger.debug("2. This is for debugging");
        logger.info ("3. This is just for information");
        logger.warn ("4. This is warnings");
        logger.error("5. This is for errors");

        Thread.sleep(5000);
    }

}