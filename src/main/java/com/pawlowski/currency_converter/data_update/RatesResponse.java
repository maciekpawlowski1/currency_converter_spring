package com.pawlowski.currency_converter.data_update;

import java.util.Map;

public class RatesResponse {
    long timestamp;
    String base;
    boolean success;
    String date;
    Map<String, Double> rates;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public RatesResponse() {
    }

    public RatesResponse(long timestamp, String base, boolean success, String date, Map<String, Double> rates) {
        this.timestamp = timestamp;
        this.base = base;
        this.success = success;
        this.date = date;
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "RatesResponse{" +
                "timestamp=" + timestamp +
                ", base='" + base + '\'' +
                ", success=" + success +
                ", date='" + date + '\'' +
                ", rates=" + rates +
                '}';
    }
}
