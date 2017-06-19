package com.mycompany.marketsurveys.entities;

import javax.persistence.*;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable=false)
    private final Long id;

    @Column(nullable=false)
    private final String code;

    @Column(nullable=false)
    private final String name;

    public Subject(String code, String name) {
        id = null;
        this.code = code;
        this.name = name;
    }

    private Subject(){
        this.id = null;
        this.code = null;
        this.name = null;
    }
}
