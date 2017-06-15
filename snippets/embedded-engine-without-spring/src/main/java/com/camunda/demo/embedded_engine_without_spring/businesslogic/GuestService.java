package com.camunda.demo.embedded_engine_without_spring.businesslogic;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.camunda.demo.embedded_engine_without_spring.businesslogic.persistence.GuestEntry;

@Stateless
public class GuestService {
  
  @PersistenceContext
  EntityManager em;
  
  public GuestEntry save(GuestEntry entry) {
    return this.em.merge(entry);
  }
  
  public List<GuestEntry> all() {
    return this.em.createNamedQuery(GuestEntry.findAll, GuestEntry.class).getResultList();
  }
  
  public GuestEntry find(long id) {
    return this.em.find(GuestEntry.class, id);
  }

}
