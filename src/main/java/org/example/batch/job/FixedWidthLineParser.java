package org.example.batch.job;

import lombok.Data;

import java.util.Map;

public class DataLengthReader implements DataReader<String, String> {

    private String[] fields;
    private String[] lengths;

    public DataLengthReader(String fields, String lengths) {
        this.fields = fields.split(",");
        this.lengths = lengths.split(",");
    }

    @Override
    public Map<String, Length> getDataMap() {
        for (int i = 0; i < ; i++) {
            
        }
        return null;
    }

    @Data
    private static class Length {
        private int start;
        private int length;
    }
}
