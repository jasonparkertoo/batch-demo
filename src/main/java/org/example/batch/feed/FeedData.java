package org.example.batch;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static java.lang.String.format;

class FeedData {

    private static final Logger log = LoggerFactory.getLogger(FeedData.class.getName());
    private final Map<String, String> map = new HashMap<>();

    public FeedData(String headerCsv, String dataCsv) {
        String[] dataTokens = this.dataTokens(dataCsv);
        String[] headerTokens = this.headerTokens(headerCsv);

        if (headerTokens.length != dataTokens.length)
            log.warning(format("Jagged data detected, header: %s, data: %s", headerCsv, dataCsv));

        for (int i = 0; i < headerTokens.length; i++) {
            map.put(headerTokens[i], dataTokens[i]);
        }
    }

    private String[] headerTokens(String header) {
        return header.split(",");
    }

    private String[] dataTokens(String data) {
        return data.split(",");
    }

    public String sequence() {
        return this.map.get("seq");
    }

    public String firstName() {
        return this.map.get("name/first");
    }

    public String lastName() {
        return this.map.get("name/last");
    }

    public String age() {
        return this.map.get("age");
    }

    public String street() {
        return this.map.get("street");
    }

    public String city() {
        return this.map.get("city");
    }

    public String state() {
        return this.map.get("state");
    }

    public String zipCode() {
        return this.map.get("zip");
    }

    public String amountInDollars() {
        return this.map.get("dollar");
    }

    public String colorChoice() {
        return this.map.get("pick");
    }

    public String date() {
        return this.map.get("date");
    }

    public float dollarAmount() {
        return Float.parseFloat(this.amountInDollars().substring(1));
    }

    @Override
    public String toString() {
        return format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,",
                this.sequence(),
                this.firstName(),
                this.lastName(),
                this.age(),
                this.street(),
                this.city(),
                this.state(),
                this.zipCode(),
                this.amountInDollars(),
                this.colorChoice(),
                this.date());
    }
}
