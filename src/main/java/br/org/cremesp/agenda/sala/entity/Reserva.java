package br.org.cremesp.agenda.sala.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Table( //
		name = "RESERVA", //
		uniqueConstraints = @UniqueConstraint(columnNames = { "DATA", "ID_SALA", "ID_HORARIO" }) //
)
public class Reserva implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ID_REUNIAO", referencedColumnName = "ID", insertable = true, updatable = true)
	@NotNull
	private Reuniao reuniao;

	@Column(name = "DATA")
	@NotNull
	private Date data;

	@ManyToOne
	@JoinColumn(name = "ID_SALA", referencedColumnName = "ID", insertable = true, updatable = true)
	@NotNull
	private Sala sala;

	@ManyToOne
	@JoinColumn(name = "ID_HORARIO", referencedColumnName = "ID", insertable = true, updatable = true)
	@NotNull
	private Horario horario;

}
