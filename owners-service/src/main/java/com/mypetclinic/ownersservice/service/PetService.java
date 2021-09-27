package com.mypetclinic.ownersservice.service;

import com.mypetclinic.ownersservice.model.Owner;
import com.mypetclinic.ownersservice.model.Pet;
import com.mypetclinic.ownersservice.model.dto.PetDetails;
import com.mypetclinic.ownersservice.model.dto.PetRequest;

public interface PetService {

    PetDetails getPetDetailsDto(long id);
    Pet getPet(long id);
    Pet addPet(PetRequest petRequest, Owner owner);
    Pet updatePet(PetRequest petRequest);
}
