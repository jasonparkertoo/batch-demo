package org.example.batch;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.logging.Logger;

public class Job {

    private static final Logger log = Logger.getLogger(Job.class.getName());

    public static void main(String[] args) {

        Job job = new Job();

        final String objKey = "data.csv";
        final String bucketName = "jparker-demo-bucket";

        S3Job processor = new S3Job(objKey, bucketName);
    }

    Reader getReader(byte[] bytes) {
        return new InputStreamReader(new ByteArrayInputStream(bytes));
    }


}
