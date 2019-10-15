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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Table( //
		name = "SALA", //
		uniqueConstraints = @UniqueConstraint(columnNames = { "NOME" }) //
)
@JsonIdentityInfo(generator = ObjectIdGenerators.None.class)
public class Sala implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NOME", nullable = false)
	@NotNull
	private String nome;

	@Column(name = "ANDAR", nullable = false)
	@NotNull
	private String andar;

	@Column(name = "QUANTIDADE_PESSOAS", nullable = false)
	@NotNull
	private Integer qtdPessoas;

	@Column(name = "IMPRESSORA", nullable = false)
	@NotNull
	private Boolean impressora;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sala")
	@JsonIgnore
	private final List<Reserva> reservas = new ArrayList<>();

}
