package com.onetech.springbootdemo.config;

import com.onetech.springbootdemo.domain.Address;
import com.onetech.springbootdemo.domain.Candidate;
import com.onetech.springbootdemo.domain.TechStack;
import com.onetech.springbootdemo.repository.CandidateRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataBootstrap implements CommandLineRunner {

    private CandidateRepository candidateRepository;


    public DataBootstrap(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Candidate candidate = new Candidate();
        candidate.setFirstName("Shintiles");
        candidate.setLastName("Baizakov");
        candidate.setCv(null);
        candidate.setPhoneNumber("7073446438");

        Address address = new Address();
        address.setCity("Almaty");
        address.setCountry("KZ");
        candidate.setAddress(address);

        List<TechStack> techStackSet = new ArrayList<>();
        TechStack javaStack = new TechStack("JAVA", "OOP LANGUAGE");
        TechStack phpStack = new TechStack("PHP", "OOP LANGUAGE");
        TechStack jsStack = new TechStack("JAVASCRIPT", "IS IT LANGUAGE?");
        TechStack goStack = new TechStack("GOLANG", "HYPE LANGUAGE");
        techStackSet.add(javaStack);
        techStackSet.add(phpStack);
        techStackSet.add(jsStack);
        techStackSet.add(goStack);
        candidate.setTechStackSet(techStackSet);
        candidateRepository.save(candidate);
    }
}
