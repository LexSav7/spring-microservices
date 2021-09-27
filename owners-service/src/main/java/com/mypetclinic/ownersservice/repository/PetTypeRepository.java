package com.mypetclinic.ownersservice.repository;

import com.mypetclinic.ownersservice.model.PetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetTypeRepository extends JpaRepository<PetType, Long> {

    List<PetType> findAllByOrderById();
}
