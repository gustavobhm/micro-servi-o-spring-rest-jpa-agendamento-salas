package br.org.cremesp.agenda.sala.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
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
		name = "DATA_SALA", //
		uniqueConstraints = @UniqueConstraint(columnNames = { "DATA", "ID_SALA" }) //
)
public class ReservaDataSala {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "DATA")
	@NotNull
	private Date data;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_SALA", referencedColumnName = "ID")
	@JsonManagedReference
	private Sala sala;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idDataSala")
	@JsonManagedReference
	@JsonProperty("horariosReservados")
	private List<HorarioReserva> horariosReserva;

}
