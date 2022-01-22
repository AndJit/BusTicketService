package com.aj.bts.TicketManagementSystem.db.races;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "races")
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private int id;
    private String fromCity;
    private String toCity;
    private long timestamp;
    private int price;
    private int amount;

    public Race(){

    }

    public Race(String fromCity, String toCity, long timestamp, int price, int amount) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.timestamp = timestamp;
        this.price = price;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }
}
