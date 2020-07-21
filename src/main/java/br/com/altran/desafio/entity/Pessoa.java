package br.com.altran.desafio.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * The Class Pessoa.
 */
@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 590593393702054087L;

	/** The id. */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/** The nome. */
	@NotNull(message = "O campo NOME é obrigatório.")
	@Column(name = "nome")
	private String nome;

	/** The cpf. */
	@NotNull(message = "O campo CPF é obrigatório.")
	@Column(name = "cpf")
	private String cpf;

	/** The dt nascimento. */
	@NotNull(message = "O campo DATA DE NASCIMENTO é obrigatório.")
	@Column(name = "data_nascimento")
	private LocalDate dtNascimento;

	/**
	 * Instantiates a new pessoa.
	 */
	public Pessoa() {
		super();
	}

	/**
	 * Instantiates a new pessoa.
	 *
	 * @param id the id
	 * @param nome the nome
	 * @param cpf the cpf
	 */
	public Pessoa(Long id, String nome, String cpf) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
	}

	/**
	 * Instantiates a new pessoa.
	 *
	 * @param id the id
	 * @param nome the nome
	 * @param cpf the cpf
	 * @param dtNascimento the dt nascimento
	 */
	public Pessoa(Long id, String nome, String cpf, LocalDate dtNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dtNascimento = dtNascimento;
	}
	
	/**
	 * Instantiates a new pessoa.
	 *
	 * @param nome the nome
	 * @param cpf the cpf
	 * @param dtNascimento the dt nascimento
	 */
	public Pessoa(String nome, String cpf, LocalDate dtNascimento) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.dtNascimento = dtNascimento;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Gets the cpf.
	 *
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * Sets the cpf.
	 *
	 * @param cpf the new cpf
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * Gets the dt nascimento.
	 *
	 * @return the dt nascimento
	 */
	public LocalDate getDtNascimento() {
		return dtNascimento;
	}

	/**
	 * Sets the dt nascimento.
	 *
	 * @param dtNascimento the new dt nascimento
	 */
	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

}
