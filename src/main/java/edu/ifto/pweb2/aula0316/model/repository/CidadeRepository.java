package edu.ifto.pweb2.aula0316.model.repository;

import edu.ifto.pweb2.aula0316.model.entity.Cidade;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CidadeRepository {
  @PersistenceContext
  private EntityManager em;

  public void save(Cidade cidade) {
    em.persist(cidade);
  }

  public Cidade cidade(Long id) {
    return em.find(Cidade.class, id);
  }

  public List<Cidade> cidades() {
    Query query = em.createQuery("from Cidade");
    return query.getResultList();
  }

  public void remove(Long id) {
    Cidade v = em.find(Cidade.class, id);
    em.remove(v);
  }

  public void update(Cidade cidade) {
    em.merge(cidade);
  }
}
