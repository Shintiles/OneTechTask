package com.onetech.springbootdemo.controller;

import com.onetech.springbootdemo.domain.Candidate;
import com.onetech.springbootdemo.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class CandidateController {

    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping("/candidates")
    public String findAll(Model model) {
        List<Candidate> candidates = candidateService.getAllCandidates();
        model.addAttribute("candidates", candidates);
        return "candidate-list";
    }

    @GetMapping("/candidate-create")
    public String createCandidateForm(Candidate candidate) {
        return "candidate-create";
    }

    @PostMapping("/candidate-create")
    public String createCandidate(Candidate candidate) {
        candidateService.saveOrEditCandidate(candidate);
        return "redirect:/candidates";
    }

    @GetMapping("candidate-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        candidateService.deleteById(id);
        return "redirect:/candidates";
    }

    @GetMapping("/candidate-update/{id}")
    public String updateCandidateForm(@PathVariable("id") Long id, Model model) {
        Candidate candidate = candidateService.getById(id);
        model.addAttribute("candidate",candidate);
        return "/candidate-update";
    }

    @PostMapping("/candidate-update")
    public String updateCandidate(Candidate candidate) {
        candidateService.saveOrEditCandidate(candidate);
        return "redirect:/candidates";
    }

    @PostMapping("/upload-cv/{id}")
    public void uploadCv(@RequestParam("file")MultipartFile multipartFile, @PathVariable("id") Long id){
       candidateService.saveCv(multipartFile,id);
    }

    @GetMapping("/download-cv/{id}")
    public ResponseEntity<Resource> downloadCv(@PathVariable("id") Long id){
        ByteArrayResource byteArrayResource = candidateService.downloadCv(id);
        MediaType mediaType = MediaType.parseMediaType("application/pdf");
       return
                ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=cv.pdf")
                        .contentLength(byteArrayResource.contentLength())
                        .contentType(mediaType)
                        .body(byteArrayResource);
    }


}
