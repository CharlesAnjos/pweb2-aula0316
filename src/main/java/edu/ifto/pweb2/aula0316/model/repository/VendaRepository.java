package edu.ifto.pweb2.aula0316.model.repository;

import edu.ifto.pweb2.aula0316.model.entity.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VendaRepository {
  @PersistenceContext
  private EntityManager em;

  public void save(Item item) {
    em.persist(item);
  }

  public Item item(Long id) {
    return em.find(Item.class, id);
  }

  public List<Item> items() {
    Query query = em.createQuery("from Item");
    return query.getResultList();
  }

  public void remove(Long id) {
    Item p = em.find(Item.class, id);
    em.remove(p);
  }

  public void update(Item item) {
    em.merge(item);
  }
}
