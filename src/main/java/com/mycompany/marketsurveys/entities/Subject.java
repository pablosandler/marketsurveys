package com.mycompany.marketsurveys.entities;

import javax.persistence.*;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable=false)
    private Long id;

    @Column(nullable=false)
    private String code;

    @Column(nullable=false)
    private String name;

    public Subject(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    private Subject(){
        this.id = null;
        this.code = null;
        this.name = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
