package com.mypetclinic.ownersservice.controller;

import com.mypetclinic.ownersservice.model.Owner;
import com.mypetclinic.ownersservice.model.Pet;
import com.mypetclinic.ownersservice.model.PetType;
import com.mypetclinic.ownersservice.model.dto.PetDetails;
import com.mypetclinic.ownersservice.model.dto.PetRequest;
import com.mypetclinic.ownersservice.repository.PetTypeRepository;
import com.mypetclinic.ownersservice.service.OwnerService;
import com.mypetclinic.ownersservice.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//TODO учитывай оунер айди, есть ли под ним определенный пет
@RequestMapping("/api/v1/owners/{ownerId}/pets")
public class PetController {

    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeRepository petTypeRepository;

    @Autowired
    public PetController(PetService petService, OwnerService ownerService, PetTypeRepository petTypeRepository) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeRepository = petTypeRepository;
    }

    @GetMapping("/petTypes")
    public List<PetType> getPetTypes() {
        return petTypeRepository.findAllByOrderById();
    }

    @GetMapping(value = "/{petId}")
    public PetDetails getPet(@PathVariable("petId") long petId) {
        return petService.getPetDetailsDto(petId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Pet addPet(@RequestBody PetRequest petRequest, @PathVariable("ownerId") long ownerId) {

        Owner owner = ownerService.getOwner(ownerId);
        Pet pet = petService.addPet(petRequest, owner);
        return pet;
    }

    @PutMapping("/{petId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Pet updatePet(@RequestBody PetRequest petRequest) {
        return petService.updatePet(petRequest);
    }


}
