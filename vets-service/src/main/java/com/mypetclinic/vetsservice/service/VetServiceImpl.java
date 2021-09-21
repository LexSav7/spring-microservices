package com.mypetclinic.vetsservice.service;

import com.mypetclinic.vetsservice.model.Vet;
import com.mypetclinic.vetsservice.repository.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VetServiceImpl implements VetService {

    private final VetRepository vetRepository;

    @Autowired
    public VetServiceImpl(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public List<Vet> getVets() {
        return vetRepository.findAll();
    }
}
