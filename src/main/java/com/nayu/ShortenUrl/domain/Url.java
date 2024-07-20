package com.nayu.ShortenUrl.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String oriUrl;
    private String shtUrl;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getOriUrl() {
        return oriUrl;
    }
    public void setOriUrl(String oriUrl) {
        this.oriUrl = oriUrl;
    }
    public String getShtUrl() {
        return shtUrl;
    }
    public void setShtUrl(String shtUrl) {
        this.shtUrl = shtUrl;
    }
}
