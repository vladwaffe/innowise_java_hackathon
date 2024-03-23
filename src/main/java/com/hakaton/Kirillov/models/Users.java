package com.hakaton.Kirillov.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.NaturalId;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Entity
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String name;
    Long chat_id;

    @NaturalId
    int prosent;
    public Users(String name, int prosent,  Long chat_id) {
        this.name = name;
        this.chat_id = chat_id;
        this.prosent = prosent;
    }

    public Users() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChat_id() {
        return chat_id;
    }

    public void setChat_id(Long chat_id) {
        this.chat_id = chat_id;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getProsent() {
        return prosent;
    }
    public void setProsent(int prosent) {
        this.prosent = prosent;
    }
}
