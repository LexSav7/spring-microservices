package com.mypetclinic.vetsservice.controller;

import com.mypetclinic.vetsservice.model.Vet;
import com.mypetclinic.vetsservice.service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vets")
public class VetController {

    private final VetService vetService;

    @Autowired
    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping
    @ResponseBody
    public List<Vet> getVets() {
        return vetService.getVets();
    }
}
