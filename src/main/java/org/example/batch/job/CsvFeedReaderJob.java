package org.example.batch;

import org.example.batch.api.Job;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CsvFeedReaderJob implements Job {

    private static final Logger log = LoggerFactory.getLogger(CsvFeedReaderJob.class.getName());
    private final Feed feed;

    public CsvFeedReaderJob(String feed, String bucket) {
        this.feed = new Feed(feed, bucket);
    }

    public static CsvFeedReaderJob from(String feed, String bucket) {
        return new CsvFeedReaderJob(feed, bucket);
    }

    @Override
    public void initialize() {
        try {
            this.fetchLocal();
            this.parse();
            this.process();
        } catch (Exception ex) {
            log.log(Level.SEVERE, "{0} : {1}", new String[] {feed.fileName(), ex.getMessage()});
        }
    }

    private void process() throws InterruptedException {
        TaskRunner runner = new TaskRunner();
        this.feed.getData().forEach(data ->
                CompletableFuture.runAsync(Task.from(data), runner.getExecutorService()));

        boolean isRunning = runner.isProcessingThreads();
        while (isRunning) {
            Thread.sleep(1000);
            isRunning = runner.isProcessingThreads();
        }

        runner.getExecutorService().shutdown();
    }

    private void parse() throws IOException {
        try (var reader = new InputStreamReader(new ByteArrayInputStream(feed.fileBytes()));
             var bufferedReader = new BufferedReader(reader)) {

            String header = bufferedReader.readLine();
            bufferedReader.lines().forEach(data -> feed.addData(new FeedData(header, data)));
        }
    }

    private void fetch() {
        try (S3Client s3 = Aws.getClient(Region.US_EAST_2)) {
            var req = GetObjectRequest.builder()
                    .key(this.feed.fileName())
                    .bucket(this.feed.bucketName())
                    .build();
            this.feed.setFileBytes(s3.getObjectAsBytes(req).asByteArray());
        } catch (S3Exception s3Ex) {
            throw new FetchFeedFileException(s3Ex.getMessage(), s3Ex);
        }
    }

    private void fetchLocal() throws IOException {
        feed.setFileBytes(Files.readAllBytes(Path.of("src/main/resources/data-1.csv")));
    }
}
