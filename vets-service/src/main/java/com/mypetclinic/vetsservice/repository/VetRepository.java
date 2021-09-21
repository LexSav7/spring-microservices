package com.mypetclinic.vetsservice.repository;

import com.mypetclinic.vetsservice.model.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VetRepository extends JpaRepository<Vet, Long> {
}
