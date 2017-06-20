package com.mycompany.marketsurveys.entities;

import javax.persistence.*;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable=false)
    private final Long id;

    @Column(nullable=false)
    private final String name;

    public Client(String name) {
        this.name = name;
        this.id = null;
    }

    private Client() {
        this.name = null;
        this.id = null;
    }
}
