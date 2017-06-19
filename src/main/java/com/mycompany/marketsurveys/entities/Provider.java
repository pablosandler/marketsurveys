package com.mycompany.marketsurveys.entities;

import javax.persistence.*;

@Entity
public class Provider {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable=false)
    private final Long id;

    @Column(nullable=false)
    private final String name;

    public Provider(String name) {
        id=null;
        this.name = name;
    }

    private Provider() {
        id=null;
        name=null;
    }
}
