package br.org.cremesp.agenda.sala.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

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
		uniqueConstraints = @UniqueConstraint(columnNames = { "ID_DATA_SALA", "ID_REUNIAO" }) //
)
public class Reserva {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @JsonIgnore
	private Integer id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_DATA_SALA", referencedColumnName = "ID")
	@JsonManagedReference
	@JsonProperty("reserva")
	private ReservaDataSala dataSala;

	@ManyToOne
	@JoinColumn(name = "ID_REUNIAO", referencedColumnName = "ID", insertable = true, updatable = true)
	@JsonBackReference
	@NotNull
	private Reuniao idReuniao;

}
