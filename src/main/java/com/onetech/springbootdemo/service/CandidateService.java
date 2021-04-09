package com.onetech.springbootdemo.service;

import com.onetech.springbootdemo.domain.Candidate;
import com.onetech.springbootdemo.repository.CandidateRepository;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class CandidateService implements ICandidateService {

    private CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    @Override
    public void saveOrEditCandidate(Candidate candidate) {
        candidateRepository.save(candidate);
    }

    @Override
    public Candidate getById(Long id) {
        return candidateRepository.getOne(id);
    }

    @Override
    public void deleteById(Long id) {
        candidateRepository.deleteById(id);
    }

    @Override
    public void saveCv(MultipartFile multipartFile, Long id) {
        Candidate candidate = getById(id);
        try {
            candidate.setCv(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveOrEditCandidate(candidate);
    }

    @Override
    public ByteArrayResource downloadCv(Long id) {
        Candidate candidate = getById(id);
        return new ByteArrayResource(candidate.getCv());
    }
}
