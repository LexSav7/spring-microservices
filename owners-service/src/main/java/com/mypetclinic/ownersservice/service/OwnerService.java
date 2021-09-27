package com.mypetclinic.ownersservice.service;

import com.mypetclinic.ownersservice.model.Owner;

import java.util.List;

public interface OwnerService {
    List<Owner> getAllOwners();
    Owner getOwner(long id);
    Owner createOwner(Owner owner);
    Owner updateOwner(Owner owner);
}
