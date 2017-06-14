package br.com.atividade4.helper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.atividade4.entity.Agendas;
import br.com.atividade4.entity.Pacientes;

public class Helper {
	private EntityManager em;

	public Helper(EntityManager em) {
		this.em = em;
	}

	public void salvar(Agendas agenda) throws Exception {
		try {
			em.getTransaction().begin();
			em.persist(agenda);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		}
	}

	// JPQL: Usando Query
	@SuppressWarnings("unchecked")
	public List<Agendas> listarAgendas() {
		Query query = em.createQuery("select a from Agendas a");
		return query.getResultList();
	}

	public Agendas buscarAgenda(Integer id) {
		Query query = em.createQuery("select a from Agendas a where id = :id");
		query.setParameter("id", id);
		Agendas a = (Agendas) query.getSingleResult();
		return a;
	}

	@SuppressWarnings("unchecked")
	public List<Pacientes> listarPacientes() {
		Query query = em.createQuery("select p from Pacientes p");
		return query.getResultList();
	}
	
	public Pacientes buscarPaciente(String cpf) {
		Query query = em.createQuery("select p from Pacientes p where cpf = :cpf");
		query.setParameter("cpf", cpf);
		Pacientes p = (Pacientes) query.getSingleResult();
		return p;
	}
	
	// método retornar lista objetos
	@SuppressWarnings("unchecked")
	public List<Pacientes> listarPacientesAgenda(int idAgenda){
		TypedQuery<Pacientes> typedQuery = em
				.createQuery("SELECT p " + 
							"FROM Pacientes p " + 
							"WHERE p.agenda.id=:idAgenda", Pacientes.class);
		typedQuery.setParameter("idAgenda", idAgenda);
		return typedQuery.getResultList();
	}
}