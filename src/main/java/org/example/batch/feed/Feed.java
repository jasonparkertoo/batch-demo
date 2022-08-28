package org.example.batch;

import java.util.ArrayList;
import java.util.List;

class Feed {

    private final String fileName;
    private final String bucket;
    private final List<FeedData> data = new ArrayList<>();
    private byte[] fileBytes;


    public Feed(String fileName, String bucket) {
        this.fileName = fileName;
        this.bucket = bucket;
    }

    public String fileName() {
        return fileName;
    }

    public String bucketName() {
        return bucket;
    }

    public byte[] fileBytes() {
        return fileBytes;
    }

    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    public void addData(FeedData data) {
        this.data.add(data);
    }

    public List<FeedData> getData() {
        return data;
    }

    public void freeMemory() {
        this.fileBytes = null;
    }
}
