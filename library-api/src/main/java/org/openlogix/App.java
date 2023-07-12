package org.openlogix;

import java.io.IOException;

//import java.util.logging.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {

    private static Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        try {
            Server server = new Server(8090);
            logger.info("connection success");
            logger.debug("connection debugging");
            logger.fatal("connection not passed");
            logger.error("connection failed");
            logger.warn("warn connection");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
