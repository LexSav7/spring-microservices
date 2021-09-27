package com.mypetclinic.ownersservice.service.impl;

import com.mypetclinic.ownersservice.model.Owner;
import com.mypetclinic.ownersservice.repository.OwnerRepository;
import com.mypetclinic.ownersservice.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Owner getOwner(long id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Owner with the ID of %d is not found!", id)));
    }

    @Override
    @Transactional
    public Owner createOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    @Transactional
    public Owner updateOwner(Owner ownerRequest) {

        Owner ownerToUpdate = getOwner(ownerRequest.getId());

        ownerToUpdate.setFirstName(ownerRequest.getFirstName());
        ownerToUpdate.setLastName(ownerRequest.getLastName());
        ownerToUpdate.setCity(ownerRequest.getCity());
        ownerToUpdate.setAddress(ownerRequest.getAddress());
        ownerToUpdate.setTelephone(ownerRequest.getTelephone());

        return ownerRepository.save(ownerToUpdate);
    }

}
