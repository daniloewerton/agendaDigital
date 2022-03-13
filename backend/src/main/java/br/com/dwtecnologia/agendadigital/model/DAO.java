package br.com.dwtecnologia.agendadigital.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class DAO<E> {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Class<E> classe;
	
	static {
		emf = Persistence.createEntityManagerFactory("agendaDigital");
	}
	
	public DAO() {
		this(null);
	}
	
	public DAO(Class<E> classe) {
		this.classe = classe;
		em = emf.createEntityManager();
	}
	
	public DAO<E> abrirTransacao() {
		em.getTransaction().begin();
		return this;
	}
	
	public DAO<E> fecharTransacao() {
		em.getTransaction().commit();
		return this;
	}
	
	public DAO<E> incluir(E entidade) {
		em.persist(entidade);
		return this;
	}
	
	public DAO<E> incluirAtomico(E entidade) {
		return this.abrirTransacao().incluir(entidade).fecharTransacao();
	}
	
	public List<E> obterTodos(int qtde, int desloc) {
		if(classe == null ) {
			throw new UnsupportedOperationException("Classe nula");
		}
		
		String jpql = "SELECT E FROM " + classe.getName() + " E";
		TypedQuery<E> query = em.createQuery(jpql, classe);
		query.setMaxResults(qtde);
		query.setFirstResult(desloc);
		return query.getResultList();
	}
	
	public List<E> obterTodos() {
		return this.obterTodos(10, 0);
	}
	
	public void fechar() {
		em.close();
	}
}
