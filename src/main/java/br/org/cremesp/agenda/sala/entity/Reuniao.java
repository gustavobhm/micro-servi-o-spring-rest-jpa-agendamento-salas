package br.org.cremesp.agenda.sala.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Table(name = "REUNIAO")
@JsonIdentityInfo(generator = ObjectIdGenerators.None.class)
public class Reuniao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "ID_SOLICITANTE", nullable = false)
	@NotNull
	private Integer idSolicitante;

	@Column(name = "RESPONSAVEL", nullable = false)
	@NotNull
	private String responsavel;

	@Column(name = "TEMA", nullable = false)
	@NotNull
	private String tema;

	@Column(name = "QUANTIDADE_PESSOAS", nullable = false)
	@NotNull
	private Integer qtdPessoas;

	@Column(name = "PUBLICO", nullable = false)
	@NotNull
	private String publico;

	@Column(name = "PROJETOR", nullable = false)
	@NotNull
	private Boolean projetor;

	@Column(name = "IMPRESSORA", nullable = false)
	@NotNull
	private Boolean impressora;

	@Column(name = "EXTRA_AGUA", nullable = false)
	@NotNull
	private Boolean extraAgua;

	@Column(name = "EXTRA_CAFE", nullable = false)
	@NotNull
	private Boolean extraCafe;

	@Column(name = "EXTRA_BISCOITO", nullable = false)
	@NotNull
	private Boolean extraBiscoito;

	@Column(name = "QUANTIDADE_NOTEBOOKS", nullable = false)
	@NotNull
	private Integer qtdNotebooks;

	@Column(name = "DATA_CRIACAO", nullable = true)
	private Date dataCriacao;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "reuniao")
	@JsonIgnore
	private final List<Reserva> reservas = new ArrayList<>();

}
