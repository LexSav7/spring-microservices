package org.mypetclinic.visitsservice.controller;

import org.mypetclinic.visitsservice.model.Visit;
import org.mypetclinic.visitsservice.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/visits")
public class VisitController {

    private final VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping
    @ResponseBody
    public List<Visit> getAllVisits() {
        return visitService.getAllVisits();
    }

    @GetMapping(value = "/{petId}")
    @ResponseBody
    public List<Visit> getVisitsForPet(@PathVariable("petId") long petId) {
        return visitService.getVisitsForPet(petId);
    }
}
