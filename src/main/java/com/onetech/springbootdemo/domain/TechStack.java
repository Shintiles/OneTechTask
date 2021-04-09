package com.onetech.springbootdemo.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Cacheable
@JsonIgnoreProperties("techStack")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "onetech")
@Entity
@Table(name="TECHSTACK")
public class TechStack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToMany(mappedBy = "techStackSet")
    private List<Candidate> candidateSet = new ArrayList<>();

    public TechStack(){

    }

    public TechStack(String name, String description){
        this.name = name;
        this.description = description;
    }

}
