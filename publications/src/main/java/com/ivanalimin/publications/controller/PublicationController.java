package com.ivanalimin.publications.controller;

import com.ivanalimin.publications.model.Publication;
import com.ivanalimin.publications.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/publications")
@RequiredArgsConstructor
public class PublicationController {

    private final PublicationService publicationService;

    @PostMapping
    public String create(@RequestBody Publication publication) {
        publicationService.send(publication);
        return "Publication send to queue";
    }
}
