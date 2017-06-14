package br.com.atividade4.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "AGENDAS", catalog = "atividade4")
public class Agendas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = true, length = 11)
	private Integer id;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "DATA")
	private Date data;

	@Temporal(value = TemporalType.TIME)
	@Column(name = "HORA")
	private Date hora;

	@Column(name = "DESCRICAO", length = 45)
	private String descricao;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "AGENDAS_PACIENTES", catalog = "atividade4", joinColumns = {
			@JoinColumn(name = "AGENDA_ID", nullable = true, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "PACIENTE_CPF", nullable = true, updatable = false) })
	private Set<Pacientes> pacientes = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<Pacientes> getPacientes() {
		return pacientes;
	}

	public void setPacientes(Set<Pacientes> pacientes) {
		this.pacientes = pacientes;
	}

}
