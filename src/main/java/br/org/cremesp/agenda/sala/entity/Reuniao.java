package br.org.cremesp.agenda.sala.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Table(//
		name = "REUNIAO", //
		uniqueConstraints = @UniqueConstraint(columnNames = { "ID_SOLICITANTE", "TEMA" }) //
)
@JsonIdentityInfo(generator = ObjectIdGenerators.None.class)
public class Reuniao implements Serializable {

	private static final long serialVersionUID = 1L;

	public Reuniao(Integer id, Integer idSolicitante, Integer idResponsavel, String tema, @NotNull Integer qtdPessoas,
			@NotNull Boolean publicoInterno, Boolean publicoExterno, Boolean projetor, Boolean impressora,
			Boolean extraAgua, Boolean extraCafe, Boolean extraBiscoito, Integer qtdNotebooks) {
		super();
		this.id = id;
		this.idSolicitante = idSolicitante;
		this.idResponsavel = idResponsavel;
		this.tema = tema;
		this.qtdPessoas = qtdPessoas;
		this.publicoInterno = publicoInterno;
		this.publicoExterno = publicoExterno;
		this.projetor = projetor;
		this.impressora = impressora;
		this.extraAgua = extraAgua;
		this.extraCafe = extraCafe;
		this.extraBiscoito = extraBiscoito;
		this.qtdNotebooks = qtdNotebooks;
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "ID_SOLICITANTE", nullable = false)
	private Integer idSolicitante;

	@Column(name = "ID_RESPONSAVEL", nullable = false)
	private Integer idResponsavel;

	@Column(name = "TEMA", nullable = false)
	private String tema;

	@Column(name = "QUANTIDADE_PESSOAS", nullable = false)
	@NotNull
	private Integer qtdPessoas;

	@Column(name = "PUBLICO_INTERNO", nullable = false)
	@NotNull
	private Boolean publicoInterno;

	@Column(name = "PUBLICO_EXTERNO", nullable = false)
	private Boolean publicoExterno;

	@Column(name = "PROJETOR", nullable = false)
	private Boolean projetor;

	@Column(name = "IMPRESSORA", nullable = false)
	private Boolean impressora;

	@Column(name = "EXTRA_AGUA", nullable = false)
	private Boolean extraAgua;

	@Column(name = "EXTRA_CAFE", nullable = false)
	private Boolean extraCafe;

	@Column(name = "EXTRA_BISCOITO", nullable = false)
	private Boolean extraBiscoito;

	@Column(name = "QUANTIDADE_NOTEBOOKS", nullable = false)
	private Integer qtdNotebooks;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "reuniao")
	@JsonIgnore
	private List<Reserva> reservas = new ArrayList<>();

}
