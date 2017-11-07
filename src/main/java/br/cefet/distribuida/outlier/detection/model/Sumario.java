package br.cefet.distribuida.outlier.detection.model;

import java.math.BigDecimal;

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
@Table(name = "\"sumario\"")
@SequenceGenerator(name = "seq_sumario", sequenceName = "seq_sumario", allocationSize = 1, initialValue = 1)
public class Sumario {
	
	private Long id;
	private String tipoGasto;
	private BigDecimal media;
	private BigDecimal variancia;
	private BigDecimal quartil1;
	private BigDecimal quartil2;
	private BigDecimal quartil3;
	private BigDecimal minimo;
	private BigDecimal maximo;
	private Long numeroElementos;
	private String ano;
	private Long threadId;
	private Integer traceId;
	private Long executionId;
	private Execucao execucao;
	private String outliers;
	
	public Sumario(String tipoGasto, String ano, Long threadId, Integer traceId) {
		this.tipoGasto = tipoGasto;
		this.ano = ano;
		this.threadId = threadId;
		this.traceId = traceId;
	}
	
	public Sumario() {
		
	}
	
	@Id
	@Column(name = "\"id\"")
	@GeneratedValue(generator = "seq_sumario", strategy = GenerationType.SEQUENCE)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "\"tipoGasto\"", nullable = false)
	public String getTipoGasto() {
		return tipoGasto;
	}
	
	public void setTipoGasto(String tipoGasto) {
		this.tipoGasto = tipoGasto;
	}
	@Column(name = "\"variancia\"")
	public BigDecimal getVariancia() {
		return variancia;
	}
	
	public void setVariancia(BigDecimal variancia) {
		this.variancia = variancia;
	}
	@Column(name = "\"quartil1\"")
	public BigDecimal getQuartil1() {
		return quartil1;
	}
	
	public void setQuartil1(BigDecimal quartil1) {
		this.quartil1 = quartil1;
	}
	
	@Column(name = "\"quartil2\"")
	public BigDecimal getQuartil2() {
		return quartil2;
	}
	
	public void setQuartil2(BigDecimal quartil2) {
		this.quartil2 = quartil2;
	}
	
	@Column(name = "\"quartil3\"")
	public BigDecimal getQuartil3() {
		return quartil3;
	}
	public void setQuartil3(BigDecimal quartil3) {
		this.quartil3 = quartil3;
	}
	
	@Column(name = "\"minimo\"")
	public BigDecimal getMinimo() {
		return minimo;
	}
	
	public void setMinimo(BigDecimal minimo) {
		this.minimo = minimo;
	}
	
	@Column(name = "\"maximo\"")
	public BigDecimal getMaximo() {
		return maximo;
	}
	
	public void setMaximo(BigDecimal maximo) {
		this.maximo = maximo;
	}
	
	@Column(name = "\"numeroElementos\"")
	public Long getNumeroElementos() {
		return numeroElementos;
	}
	
	public void setNumeroElementos(Long numeroElementos) {
		this.numeroElementos = numeroElementos;
	}
	
	@Column(name = "\"ano\"", nullable = false)
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	
	@Column(name = "\"threadId\"", nullable = false)
	public Long getThreadId() {
		return threadId;
	}
	
	public void setThreadId(Long threadId) {
		this.threadId = threadId;
	}
	
	@Column(name = "\"traceId\"", nullable = false)
	public Integer getTraceId() {
		return traceId;
	}
	
	public void setTraceId(Integer traceId) {
		this.traceId = traceId;
	}
	
	@Column(name = "\"execucaoId\"", columnDefinition = "BIGINT DEFAULT p_criar_execucao()", insertable = false, updatable = false)
	public Long getExecutionId() {
		return executionId;
	}
	
	public void setExecutionId(Long executionId) {
		this.executionId = executionId;
	}
	
	@ManyToOne
	@JoinColumn(name = "\"execucaoId\"", insertable = false, updatable = false)
	public Execucao getExecucao() {
		return execucao;
	}
	
	public void setExecucao(Execucao execucao) {
		this.execucao = execucao;
	}
	
	@Column(name = "\"media\"")
	public BigDecimal getMedia() {
		return media;
	}

	public void setMedia(BigDecimal media) {
		this.media = media;
	}
	
	@Column(name = "\"outliers\"",columnDefinition = "TEXT")
	public String getOutliers() {
		return outliers;
	}

	public void setOutliers(String outliers) {
		this.outliers = outliers;
	}
	
}
