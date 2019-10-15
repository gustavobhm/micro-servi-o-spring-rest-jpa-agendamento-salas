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
@Table( //
		name = "HORARIO", //
		uniqueConstraints = @UniqueConstraint(columnNames = { "HORA" }) //
)
@JsonIdentityInfo(generator = ObjectIdGenerators.None.class)
public class Horario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "HORA", nullable = false)
	@NotNull
	private String hora;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "horario")
	@JsonIgnore
	private final List<Reserva> reservas = new ArrayList<>();

}
