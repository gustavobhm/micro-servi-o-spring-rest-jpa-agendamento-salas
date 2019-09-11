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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table( //
		name = "HORARIO", //
		uniqueConstraints = @UniqueConstraint(columnNames = { "HORA" }) //
)
public class Horario implements Serializable {

	private static final long serialVersionUID = 1L;

	public Horario(Integer id, String hora) {
		super();
		this.id = id;
		this.hora = hora;
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "HORA", nullable = false)
	private String hora;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "horario")
	@JsonIgnore
	private List<Reserva> reservas = new ArrayList<Reserva>();

}
