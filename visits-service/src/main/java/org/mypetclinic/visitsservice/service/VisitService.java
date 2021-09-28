package org.mypetclinic.visitsservice.service;

import org.mypetclinic.visitsservice.model.Visit;

import java.util.List;

public interface VisitService {

    List<Visit> getVisitsForPet(long petId);

    List<Visit> getVisitsForPets(List<Long> petIds);

    Visit addVisit(Visit visit, long petId);
}
