package br.org.cremesp.agenda.sala.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "AGENDAMENTO")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Agendamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "ID_SOLICITANTE")
	private Integer idSolicitante;

	@Column(name = "ID_RESPONSAVEL")
	private Integer idResponsavel;

	@Column(name = "TEMA_REUNIAO")
	private String temaReuniao;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idAgendamento")
	@JsonManagedReference
	private List<DataReuniao> datasReuniao;

	@Column(name = "QUANTIDADE_PESSOAS")
	private Integer quantidadePessoas;

	@Column(name = "PUBLICO_INTERNO")
	private Boolean publicoInterno;

	@Column(name = "PUBLICO_EXTERNO")
	private Boolean publicoExterno;

	@Column(name = "QUANTIDADE_EQUIPAMENTOS")
	private Integer quantidadeEquipamentos;

	@Column(name = "QUANTIDADE_IMPRESSORAS")
	private Integer quantidadeImpressoras;

	@Column(name = "QUANTIDADE_AUDIOVISUAIS")
	private Integer quantidadeAudiovisuais;

	@Column(name = "EXTRA_AGUA")
	private Boolean extraAgua;

	@Column(name = "EXTRA_CAFE")
	private Boolean extraCafe;

	@Column(name = "EXTRA_BISCOITO")
	private Boolean extraBiscoito;

}
