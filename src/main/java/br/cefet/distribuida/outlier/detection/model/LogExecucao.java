package br.cefet.distribuida.outlier.detection.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "log_execucao")
@SequenceGenerator(name = "seq_log_execucao", sequenceName = "seq_log_execucao", allocationSize = 1, initialValue = 1)
public class LogExecucao {
	
	private Long id;
	private String nomeLog;
	private String descricao;
	private Long inicio;
	private Long fim;
	private Date dataHoraCriacao;
	private int traceId;
	private Execucao execucao;
	private Long threadId;
	private Long execucaoId;
	

	@Id
	@GeneratedValue(generator = "seq_log_execucao", strategy = GenerationType.SEQUENCE)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	 
	@Column(name = "\"nomeLog\"", nullable = false)
	public String getNomeLog() {
		return nomeLog;
	}
	
	public void setNomeLog(String nomeLog) {
		this.nomeLog = nomeLog;
	}
	
	@Column(name = "\"descricao\"")
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Column(name = "\"inicio\"", nullable = false)
	public Long getInicio() {
		return inicio;
	}
	
	public void setInicio(Long inicio) {
		this.inicio = inicio;
	}
	
	@Column(name = "\"fim\"", nullable = false)
	public Long getFim() {
		return fim;
	}
	
	public void setFim(Long fim) {
		this.fim = fim;
	}
	
	@Column(name = "\"dataHoraCriacao\"", nullable = false)
	public Date getDataHoraCriacao() {
		return dataHoraCriacao;
	}

	public void setDataHoraCriacao(Date dataHoraCriacao) {
		this.dataHoraCriacao = dataHoraCriacao;
	}
	
	@Column(name = "\"traceId\"", nullable = false)
	public int getTraceId() {
		return traceId;
	}

	public void setTraceId(int traceId) {
		this.traceId = traceId;
	}
	

	@ManyToOne
	@JoinColumn(name = "\"execucaoId\"", insertable = false, updatable = false)
	public Execucao getExecucao() {
		return execucao;
	}
	
	public void setExecucao(Execucao execucao) {
		this.execucao = execucao;
	}
	
	@Column(name = "\"threadId\"", nullable = false)
	public Long getThreadId() {
		return threadId;
	}

	public void setThreadId(Long threadId) {
		this.threadId = threadId;
	}
	
	@Column(name = "\"execucaoId\"", columnDefinition = "BIGINT DEFAULT p_criar_execucao()", insertable = false, updatable = false)
	public Long getExecucaoId() {
		return execucaoId;
	}

	public void setExecucaoId(Long execucaoId) {
		this.execucaoId = execucaoId;
	}
	
}
