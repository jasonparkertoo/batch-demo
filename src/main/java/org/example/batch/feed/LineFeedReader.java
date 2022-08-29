package org.example.batch.feed;

import org.example.batch.api.FeedReader;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LineFeedReader implements FeedReader<Map<Integer, String>> {
    private final InputStream inputStream;

    public LineFeedReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public Map<Integer, String> read() {

        Map<Integer, LineData> data = new HashMap<>();
        try (var reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line = reader.readLine();
            while (line != null) {
                data.put()
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
