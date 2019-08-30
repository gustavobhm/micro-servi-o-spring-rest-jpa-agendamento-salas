package br.org.cremesp.agenda.sala.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "HORA_REUNIAO")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class HoraReuniao {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ID_DATA_REUNIAO", referencedColumnName = "ID", insertable = true, updatable = true)
	@JsonBackReference
	private DataReuniao idDataReuniao;

	@Column(name = "HORA")
	private String hora;

}
