package com.mypetclinic.ownersservice.controller;

import com.mypetclinic.ownersservice.model.Owner;
import com.mypetclinic.ownersservice.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/owners")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/{ownerId}")
    public Owner getOwner(@PathVariable("ownerId") long ownerId) {
        return ownerService.getOwner(ownerId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Owner createOwner(@Valid @RequestBody Owner owner) {
        return ownerService.createOwner(owner);
    }

    @GetMapping
    public List<Owner> findAll() {
        return ownerService.getAllOwners();
    }

    @PutMapping(value = "/{ownerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Owner updateOwner(@PathVariable("ownerId") long ownerId, @Valid @RequestBody Owner owner) {

        if ((owner.getId() == null) || (owner.getId() == 0L) || (owner.getId() != ownerId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot update owner. ID is not provided or does not match.");
        }
        return ownerService.updateOwner(owner);
    }
}
