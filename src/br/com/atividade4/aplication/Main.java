package br.com.atividade4.aplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.atividade4.entity.Agendas;
import br.com.atividade4.entity.MatMeds;
import br.com.atividade4.entity.Pacientes;
import br.com.atividade4.entity.Procedimentos;
import br.com.atividade4.helper.Helper;

public class Main {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Atividade4_JDBC_JPA");
		EntityManager em = emf.createEntityManager();

		incluirAgendaCompleta(em);
		listarPacientes(em);
		buscarPaciente(em, "1234567890");
	}

	public static Date transformaData(String data) {
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return formatador.parse(data);
		} catch (ParseException ex) {
			throw new RuntimeException(ex);
		}
	}

	private static void incluirAgendaCompleta(EntityManager em) {
		Date date = new Date();
		Helper dao = new Helper(em);
		// agenda
		Agendas agenda = new Agendas();
		agenda.setDescricao("Agenda 1");
		agenda.setData(date);
		agenda.setHora(date);
		// paciente
		Pacientes paciente = new Pacientes();
		paciente.setCpf("1234567890");
		paciente.setNome("Paciente 1");
		paciente.setDataNasc(transformaData("01/01/1995"));
		paciente.setTelefone("(11) 99999-1234");
		// procedimento
		Procedimentos pro = new Procedimentos();
		pro.setDescricao("Procedimento");
		pro.setPreco(1000.0);
		// MatMeds
		MatMeds m = new MatMeds();
		m.setDescricao("Material Medico");
		m.setPreco(200.0);
		m.setFabricante("fabricante");


		pro.setPaciente(paciente);
		m.setPaciente(paciente);
		paciente.getProcedimentos().add(pro);
		paciente.getMatMeds().add(m);
		paciente.getAgendas().add(agenda);
		agenda.getPacientes().add(paciente);

		// criando outros pacientes
		for (int i = 1; i <= 3; i++) {
			Pacientes p = new Pacientes();
			p.setCpf("1234567890" + i);
			p.setNome("Paciente" + i);
			p.setDataNasc(transformaData("01/01/199" + i));
			p.setTelefone("(11) 98626-525" + i);

			// associando
			p.getAgendas().add(agenda);
			agenda.getPacientes().add(p);
		}
		// salvar
		try {
			dao.salvar(agenda);
			System.out.println("Agenda inserida");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void listarPacientes(EntityManager em) {
		Helper dao = new Helper(em);
		List<Pacientes> pacientes = dao.listarPacientes();
		System.out.println("exibindo os Pacientes:");
		for (Pacientes paciente : pacientes) {
			System.out.println(paciente.getCpf() + ": " + paciente.getNome());
		}
	}

	private static void buscarPaciente(EntityManager em, String cpf) {
		Helper dao = new Helper(em);
		Pacientes p = dao.buscarPaciente(cpf);
		System.out.println(p.getCpf() + " - " + p.getNome() + " - " + p.getDataNasc());
		// listando os pedidos
		Set<Procedimentos> procedimentoList = p.getProcedimentos();
		System.out.println("Exibe de Procedimentos do Paciente:");
		for (Procedimentos procedimento : procedimentoList) {
			System.out.println(" => " + procedimento.getId() + " " 
				+ procedimento.getDescricao() + " R$ "
				+ procedimento.getPreco());
		}
		// listando os pedidos
		Set<MatMeds> matMedList = p.getMatMeds();
		System.out.println("Exibe Materias do Paciente:");
		for (MatMeds matMed : matMedList) {
			System.out.println(matMed.getId() + " " 
				+ matMed.getDescricao() + " R$ " 
				+ matMed.getPreco() + " "
				+ matMed.getFabricante());
		}
	}
}
