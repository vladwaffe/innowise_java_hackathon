package com.hakaton.Kirillov.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.NaturalId;

import java.io.Serializable;

@Entity
public class Cript implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NaturalId
    private String name;
    private float cost;

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public Cript(String name, float cost) {
        this.name = name;
        this.cost = cost;
    }

    public Cript() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
