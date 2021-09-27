package org.mypetclinic.visitsservice.service;

import org.mypetclinic.visitsservice.model.Visit;

import java.util.List;

public interface VisitService {

    List<Visit> getAllVisits();

    List<Visit> getVisitsForPet(long petId);
}
