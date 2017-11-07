package br.cefet.distribuida.outlier.detection.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "execucao")
@SequenceGenerator(name = "seq_execucao", sequenceName = "seq_execucao", allocationSize = 1, initialValue = 1)
public class Execucao {
	
	private Long id;
	private Date dataHoraCriacao;
	private Date dataHoraTermino;
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "\"dataHoraCriacao\"")
	public Date getDataHoraCriacao() {
		return dataHoraCriacao;
	}
	public void setDataHoraCriacao(Date dataHoraCriacao) {
		this.dataHoraCriacao = dataHoraCriacao;
	}
	
	@Column(name = "\"dataHoraTermino\"")
	public Date getDataHoraTermino() {
		return dataHoraTermino;
	}
	public void setDataHoraTermino(Date dataHoraTermino) {
		this.dataHoraTermino = dataHoraTermino;
	}
}
