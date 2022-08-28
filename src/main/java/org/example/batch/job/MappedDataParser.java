package org.example.batch.job;

import java.util.Map;

public interface DataReader<S,T> {
    Map<String, DataLengthReader.Length> getDataMap();
}
