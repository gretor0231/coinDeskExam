package com.bezkoder.spring.jpa.h2.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "currencys")

public class MyCurrency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "description")
    private String description;

    @Column(name = "rate")
    private String rate;

    @Column(name="updateTime")
    private LocalDateTime updateTime;// we use local time now

    public MyCurrency() {
    }

    public MyCurrency(String symbol, String description, String rate, LocalDateTime updateTime) {
        this.symbol = symbol;
        this.description = description;
        this.rate = rate;
        this.updateTime = updateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "MyCurrency{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", description='" + description + '\'' +
                ", rate=" + rate +
                ", updateTime=" + updateTime +
                '}';
    }
}
