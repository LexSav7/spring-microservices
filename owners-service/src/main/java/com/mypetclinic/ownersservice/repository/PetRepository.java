package com.mypetclinic.ownersservice.repository;

import com.mypetclinic.ownersservice.model.Owner;
import com.mypetclinic.ownersservice.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
}
