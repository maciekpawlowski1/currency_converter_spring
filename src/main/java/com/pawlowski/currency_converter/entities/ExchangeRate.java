package com.pawlowski.currency_converter.entities;

import javax.persistence.*;

@Entity
@Table(name = "exchange_rates")
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "valueFrom")
    private String from;

    @Column(name = "valueTo")
    String to;

    @Column(name = "exchangeRate")
    double exchangeRate;

    @Column(name = "timestamp")
    String timestamp;



    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public ExchangeRate(String from, String to, String timestamp, double exchangeRate) {
        this.from = from;
        this.to = to;
        this.timestamp = timestamp;
        this.exchangeRate = exchangeRate;
    }

    public ExchangeRate() {
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", date=" + timestamp +
                '}';
    }
}
