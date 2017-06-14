package br.com.atividade4.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PACIENTES", catalog = "atividade4")
public class Pacientes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CPF", unique = true, nullable = true, length = 11)
	private String cpf;

	@Column(name = "NOME", length = 45)
	private String nome;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "DATANASC")
	private Date dataNasc;

	@Column(name = "TELEFONE", length = 20)
	private String telefone;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "pacientes")
	private Set<Agendas> agendas = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "paciente")
	private Set<Procedimentos> procedimentos = new LinkedHashSet<Procedimentos>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "paciente")
	private Set<MatMeds> matMeds = new LinkedHashSet<MatMeds>();

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Set<Agendas> getAgendas() {
		return agendas;
	}

	public void setAgendas(Set<Agendas> agendas) {
		this.agendas = agendas;
	}

	public Set<Procedimentos> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(Set<Procedimentos> procedimentos) {
		this.procedimentos = procedimentos;
	}

	public Set<MatMeds> getMatMeds() {
		return matMeds;
	}

	public void setMatMeds(Set<MatMeds> matMeds) {
		this.matMeds = matMeds;
	}

}
