package org.example.batch.feed;

import lombok.Data;

@Data
public class LineData {
    private String data;
    private Status status;
    private int lineNumber;

    public LineData(String data, Status status, int lineNumber) {
        this.data = data;
        this.status = status;
        this.lineNumber = lineNumber;
    }

    public enum Status {
        PROCESSED, PENDING, FAILED, INFLIGHT
    }
}
