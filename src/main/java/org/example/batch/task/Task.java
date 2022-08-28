package org.example.batch;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Task implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(Task.class.getName());

    private final Random rnd = new Random();
    private final FeedData data;
    private final int sleepTime;

    public Task(FeedData data) {
        this.data = data;
        this.sleepTime = this.getSleepTimeInMillis();
    }

    public static Task from(FeedData data) {
        return new Task(data);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(sleepTime);
            log.log(Level.INFO, "Value: {0}, Seconds: {1}",
                    new String[] {data.sequence(), String.valueOf(sleepTime/1000)});
        } catch (InterruptedException e) {
            throw new IllegalAccessError();
        }
    }

    private int getSleepTimeInMillis() {
        return rnd.nextInt(5000 - 1000) + 1000;
    }
}
