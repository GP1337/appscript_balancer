package com.neurolabs.appscript_balancer.pool;

public class TranslationService {

    volatile private String url;
    volatile private int requests;

    public TranslationService(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public synchronized int getRequests() {
        return requests;
    }

    public synchronized int incAndGetRequests() {

        this.requests++;

        return this.requests;

    }
}
