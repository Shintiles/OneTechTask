package com.onetech.springbootdemo.service;

import com.onetech.springbootdemo.domain.Candidate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.ByteArrayResource;

import java.util.List;

public interface ICandidateService {

    List<Candidate> getAllCandidates();
    void saveOrEditCandidate(Candidate candidate);
    Candidate getById(Long id);
    void deleteById(Long id);
    void saveCv(MultipartFile multipartFile, Long id);

    ByteArrayResource downloadCv(Long id);
}
