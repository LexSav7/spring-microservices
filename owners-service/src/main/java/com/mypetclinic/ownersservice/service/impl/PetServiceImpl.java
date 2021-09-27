package com.mypetclinic.ownersservice.service.impl;

import com.mypetclinic.ownersservice.model.Owner;
import com.mypetclinic.ownersservice.model.Pet;
import com.mypetclinic.ownersservice.model.dto.PetDetails;
import com.mypetclinic.ownersservice.model.dto.PetRequest;
import com.mypetclinic.ownersservice.repository.PetRepository;
import com.mypetclinic.ownersservice.repository.PetTypeRepository;
import com.mypetclinic.ownersservice.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;

@Service
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    @Autowired
    public PetServiceImpl(PetRepository petRepository, PetTypeRepository petTypeRepository) {
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public PetDetails getPetDetailsDto(long id) {
        Pet pet = getPet(id);
        return new PetDetails(pet);
    }

    @Override
    @Transactional(readOnly = true)
    public Pet getPet(long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Pet with the ID of %d is not found!", id)));
    }

    @Override
    @Transactional
    public Pet addPet(PetRequest petRequest, Owner owner) {
        Pet newPet = convertToPetEntity(new Pet(), petRequest);
        owner.addPet(newPet);
        return petRepository.save(newPet);
    }

    @Override
    @Transactional
    public Pet updatePet(PetRequest petRequest) {

        if ( petRequest.getId() == null || petRequest.getId() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pet ID is not provided, cannot update.");
        }

        Pet petToUpdate = getPet(petRequest.getId());
        Pet updatedPet = petRepository.save(convertToPetEntity(petToUpdate, petRequest));

        return updatedPet;
    }

    private Pet convertToPetEntity(Pet pet, PetRequest petRequest) {
        pet.setName(petRequest.getName());
        pet.setBirthDate(petRequest.getBirthDate());

        petTypeRepository.findById(petRequest.getTypeId())
                .ifPresent(pet::setPetType);
        return pet;
    }
}
