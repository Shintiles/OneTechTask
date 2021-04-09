package com.onetech.springbootdemo.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.*;

@Data
@Cacheable
@JsonIgnoreProperties("candidateSet")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "onetech")
@Entity
@Table(name = "CANDIDATE")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Lob
    private byte[] cv;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    private String phoneNumber;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "candidate_techstack",
            joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "techstack_id"))
    private List<TechStack> techStackSet = new ArrayList<>();

}
