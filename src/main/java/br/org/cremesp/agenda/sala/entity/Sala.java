package br.org.cremesp.agenda.sala.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Table( //
		name = "SALA", //
		uniqueConstraints = @UniqueConstraint(columnNames = { "NOME" }) //
)
public class Sala implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NOME", nullable = false)
	private String nome;

	@Column(name = "ANDAR", nullable = false)
	private String andar;

	@Column(name = "QUANTIDADE_PESSOAS", nullable = false)
	private Integer qtdPessoas;

	@Column(name = "IMPRESSORA", nullable = false)
	private Boolean impressora;

	@OneToOne(mappedBy = "sala")
	@JsonBackReference
	private ReservaDataSala dataSala; 

}
