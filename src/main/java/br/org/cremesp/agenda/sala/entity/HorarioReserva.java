package br.org.cremesp.agenda.sala.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table( //
		name = "HORARIO_RESERVA", //
		uniqueConstraints = @UniqueConstraint(columnNames = { "HORA", "ID_DATA_SALA" }) //
)
public class HorarioReserva {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "HORA", nullable = false)
	@JsonValue
	private String hora;

	@ManyToOne
	@JoinColumn(name = "ID_DATA_SALA", referencedColumnName = "ID", insertable = true, updatable = true, nullable = false)
	@JsonBackReference
	private ReservaDataSala idDataSala;

}
