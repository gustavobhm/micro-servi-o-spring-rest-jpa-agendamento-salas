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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "DATA_REUNIAO")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class DataReuniao {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ID_AGENDAMENTO", referencedColumnName = "ID", insertable = true, updatable = true)
	@JsonBackReference
	private Agendamento idAgendamento;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "DATA")
	private Date data;

	@Column(name = "ID_SALA")
	private Integer idSala;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idDataReuniao")
	@JsonManagedReference
	private List<HoraReuniao> horariosReuniao;

}
