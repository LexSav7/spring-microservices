package org.mypetclinic.visitsservice.repository;

import org.mypetclinic.visitsservice.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    List<Visit> findAllByPetId(long petId);

    List<Visit> findAllByPetIdIn(List<Long> petIds);
}
