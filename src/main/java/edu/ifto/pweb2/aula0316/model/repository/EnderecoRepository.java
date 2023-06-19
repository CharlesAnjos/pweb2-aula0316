package edu.ifto.pweb2.aula0316.model.repository;

import edu.ifto.pweb2.aula0316.model.entity.Endereco;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public class EnderecoRepository {
  @PersistenceContext
  private EntityManager em;

  public void save(Endereco endereco) {
    em.persist(endereco);
  }
  public Endereco saveAndReturn(Endereco endereco) {
    em.persist(endereco);
    return endereco;
  }

  public Endereco endereco(Long id) {
    return em.find(Endereco.class, id);
  }

  public List<Endereco> enderecos() {
    Query query = em.createQuery("from Endereco");
    return query.getResultList();
  }

  public void remove(Long id) {
    Endereco v = em.find(Endereco.class, id);
    em.remove(v);
  }

  public void update(Endereco endereco) {
    em.merge(endereco);
  }
}
