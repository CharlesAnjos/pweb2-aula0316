package edu.ifto.pweb2.aula0316.model.repository;

import edu.ifto.pweb2.aula0316.model.entity.Pessoa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PessoaRepository {

  @PersistenceContext
  private EntityManager em;

  public void save(Pessoa pessoa){
    em.persist(pessoa);
  }

  public Pessoa pessoa(Long id){
    return em.find(Pessoa.class, id);
  }

  public List<Pessoa> pessoas(){
    Query query = em.createQuery("from Pessoa");
    return query.getResultList();
  }

  public void remove(Long id){
    Pessoa p = em.find(Pessoa.class, id);
    em.remove(p);
  }

  public void update(Pessoa pessoa){
    em.merge(pessoa);
  }
}