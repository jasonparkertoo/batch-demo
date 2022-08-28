package org.example.batch;

import java.util.logging.Logger;

public class LoggerFactory {

    private LoggerFactory() {
    }

    public static Logger getLogger(String name) {
        return Logger.getLogger(name);
    }
}
