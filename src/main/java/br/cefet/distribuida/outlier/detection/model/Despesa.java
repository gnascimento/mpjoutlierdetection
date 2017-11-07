package br.cefet.distribuida.outlier.detection.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "\"despesa\"")
public class Despesa implements IDespesa {
    private long id;
    private String txNomeParlamentar;
    private String idecadastro;
    private String nuCarteiraParlamentar;
    private String nuLegislatura;
    private String sgUf;
    private String sgPartido;
    private Integer codLegislatura;
    private Integer numSubCota;
    private String txtDescricao;
    private Integer numEspecificacaoSubCota;
    private String txtDescricaoEspecificacao;
    private String txtFornecedor;
    private String txtCnpjcpf;
    private String txtNumero;
    private Integer indTipoDocumento;
    private Date datEmissao;
    private BigDecimal vlrDocumento;
    private BigDecimal vlrGlosa;
    private BigDecimal vlrLiquido;
    private String numMes;
    private String numAno;
    private Integer numParcela;
    private String txtPassageiro;
    private String txtTrecho;
    private Integer numLote;
    private Integer numRessarcimento;
    private BigDecimal vlrRestituicao;
    private String nuDeputadoId;
    private String ideDocumento;
    
    public Despesa() {
    	
    }
    
    public Despesa(long id, String txNomeParlamentar, String idecadastro, String nuCarteiraParlamentar, 
    		String sgUf, String sgPartido, String txtDescricao, BigDecimal vlrDocumento, BigDecimal vlrGlosa,
    		String numMes, String numAno) {
    	this.id = id;
    	this.txNomeParlamentar = txNomeParlamentar;
    	this.idecadastro = idecadastro;
    	this.nuCarteiraParlamentar = nuCarteiraParlamentar;
    	this.sgUf = sgUf;
    	this.sgPartido = sgPartido;
    	this.txtDescricao = txtDescricao;
    	this.vlrDocumento = vlrDocumento;
    	this.vlrGlosa = vlrGlosa;
    	this.numMes = numMes;
    	this.numAno = numAno;
    }

    @Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "\"txNomeParlamentar\"")
    public String getTxNomeParlamentar() {
        return txNomeParlamentar;
    }

    public void setTxNomeParlamentar(String txNomeParlamentar) {
        this.txNomeParlamentar = txNomeParlamentar;
    }

    @Basic
    @Column(name = "\"idecadastro\"")
    public String getIdecadastro() {
        return idecadastro;
    }

    public void setIdecadastro(String idecadastro) {
        this.idecadastro = idecadastro;
    }

    @Basic
    @Column(name = "\"nuCarteiraParlamentar\"")
    public String getNuCarteiraParlamentar() {
        return nuCarteiraParlamentar;
    }

    public void setNuCarteiraParlamentar(String nuCarteiraParlamentar) {
        this.nuCarteiraParlamentar = nuCarteiraParlamentar;
    }

    @Basic
    @Column(name = "\"nuLegislatura\"")
    public String getNuLegislatura() {
        return nuLegislatura;
    }

    public void setNuLegislatura(String nuLegislatura) {
        this.nuLegislatura = nuLegislatura;
    }

    @Basic
    @Column(name = "\"sgUF\"")
    public String getSgUf() {
        return sgUf;
    }

    public void setSgUf(String sgUf) {
        this.sgUf = sgUf;
    }

    @Basic
    @Column(name = "\"sgPartido\"")
    public String getSgPartido() {
        return sgPartido;
    }

    public void setSgPartido(String sgPartido) {
        this.sgPartido = sgPartido;
    }

    @Basic
    @Column(name = "\"codLegislatura\"")
    public Integer getCodLegislatura() {
        return codLegislatura;
    }

    public void setCodLegislatura(Integer codLegislatura) {
        this.codLegislatura = codLegislatura;
    }

    @Basic
    @Column(name = "\"numSubCota\"")
    public Integer getNumSubCota() {
        return numSubCota;
    }

    public void setNumSubCota(Integer numSubCota) {
        this.numSubCota = numSubCota;
    }

    @Basic
    @Column(name = "\"txtDescricao\"")
    public String getTxtDescricao() {
        return txtDescricao;
    }

    public void setTxtDescricao(String txtDescricao) {
        this.txtDescricao = txtDescricao;
    }

    @Basic
    @Column(name = "\"numEspecificacaoSubCota\"")
    public Integer getNumEspecificacaoSubCota() {
        return numEspecificacaoSubCota;
    }

    public void setNumEspecificacaoSubCota(Integer numEspecificacaoSubCota) {
        this.numEspecificacaoSubCota = numEspecificacaoSubCota;
    }

    @Basic
    @Column(name = "\"txtDescricaoEspecificacao\"")
    public String getTxtDescricaoEspecificacao() {
        return txtDescricaoEspecificacao;
    }

    public void setTxtDescricaoEspecificacao(String txtDescricaoEspecificacao) {
        this.txtDescricaoEspecificacao = txtDescricaoEspecificacao;
    }

    @Basic
    @Column(name = "\"txtFornecedor\"")
    public String getTxtFornecedor() {
        return txtFornecedor;
    }

    public void setTxtFornecedor(String txtFornecedor) {
        this.txtFornecedor = txtFornecedor;
    }

    @Basic
    @Column(name = "\"txtCNPJCPF\"")
    public String getTxtCnpjcpf() {
        return txtCnpjcpf;
    }

    public void setTxtCnpjcpf(String txtCnpjcpf) {
        this.txtCnpjcpf = txtCnpjcpf;
    }

    @Basic
    @Column(name = "\"txtNumero\"")
    public String getTxtNumero() {
        return txtNumero;
    }

    public void setTxtNumero(String txtNumero) {
        this.txtNumero = txtNumero;
    }

    @Basic
    @Column(name = "\"indTipoDocumento\"")
    public Integer getIndTipoDocumento() {
        return indTipoDocumento;
    }

    public void setIndTipoDocumento(Integer indTipoDocumento) {
        this.indTipoDocumento = indTipoDocumento;
    }

    @Column(name = "\"datEmissao\"")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDatEmissao() {
        return datEmissao;
    }

    public void setDatEmissao(Date datEmissao) {
        this.datEmissao = datEmissao;
    }

    @Basic
    @Column(name = "\"vlrDocumento\"")
    public BigDecimal getVlrDocumento() {
        return vlrDocumento;
    }

    public void setVlrDocumento(BigDecimal vlrDocumento) {
        this.vlrDocumento = vlrDocumento;
    }

    @Basic
    @Column(name = "\"vlrGlosa\"")
    public BigDecimal getVlrGlosa() {
        return vlrGlosa;
    }

    public void setVlrGlosa(BigDecimal vlrGlosa) {
        this.vlrGlosa = vlrGlosa;
    }

    @Basic
    @Column(name = "\"vlrLiquido\"")
    public BigDecimal getVlrLiquido() {
        return vlrLiquido;
    }

    public void setVlrLiquido(BigDecimal vlrLiquido) {
        this.vlrLiquido = vlrLiquido;
    }

    @Basic
    @Column(name = "\"numMes\"")
    public String getNumMes() {
        return numMes;
    }

    public void setNumMes(String numMes) {
        this.numMes = numMes;
    }

    @Basic
    @Column(name = "\"numAno\"")
    public String getNumAno() {
        return numAno;
    }

    public void setNumAno(String numAno) {
        this.numAno = numAno;
    }

    @Basic
    @Column(name = "\"numParcela\"")
    public Integer getNumParcela() {
        return numParcela;
    }

    public void setNumParcela(Integer numParcela) {
        this.numParcela = numParcela;
    }

    @Basic
    @Column(name = "\"txtPassageiro\"")
    public String getTxtPassageiro() {
        return txtPassageiro;
    }

    public void setTxtPassageiro(String txtPassageiro) {
        this.txtPassageiro = txtPassageiro;
    }

    @Basic
    @Column(name = "\"txtTrecho\"")
    public String getTxtTrecho() {
        return txtTrecho;
    }

    public void setTxtTrecho(String txtTrecho) {
        this.txtTrecho = txtTrecho;
    }

    @Basic
    @Column(name = "\"numLote\"")
    public Integer getNumLote() {
        return numLote;
    }

    public void setNumLote(Integer numLote) {
        this.numLote = numLote;
    }

    @Basic
    @Column(name = "\"numRessarcimento\"")
    public Integer getNumRessarcimento() {
        return numRessarcimento;
    }

    public void setNumRessarcimento(Integer numRessarcimento) {
        this.numRessarcimento = numRessarcimento;
    }

    @Basic
    @Column(name = "\"vlrRestituicao\"")
    public BigDecimal getVlrRestituicao() {
        return vlrRestituicao;
    }

    public void setVlrRestituicao(BigDecimal vlrRestituicao) {
        this.vlrRestituicao = vlrRestituicao;
    }

    @Basic
    @Column(name = "\"nuDeputadoId\"")
    public String getNuDeputadoId() {
        return nuDeputadoId;
    }

    public void setNuDeputadoId(String nuDeputadoId) {
        this.nuDeputadoId = nuDeputadoId;
    }

    @Basic
    @Column(name = "\"ideDocumento\"")
    public String getIdeDocumento() {
        return ideDocumento;
    }

    public void setIdeDocumento(String ideDocumento) {
        this.ideDocumento = ideDocumento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Despesa despesa = (Despesa) o;

        if (txNomeParlamentar != null ? !txNomeParlamentar.equals(despesa.txNomeParlamentar) : despesa.txNomeParlamentar != null)
            return false;
        if (idecadastro != null ? !idecadastro.equals(despesa.idecadastro) : despesa.idecadastro != null) return false;
        if (nuCarteiraParlamentar != null ? !nuCarteiraParlamentar.equals(despesa.nuCarteiraParlamentar) : despesa.nuCarteiraParlamentar != null)
            return false;
        if (nuLegislatura != null ? !nuLegislatura.equals(despesa.nuLegislatura) : despesa.nuLegislatura != null)
            return false;
        if (sgUf != null ? !sgUf.equals(despesa.sgUf) : despesa.sgUf != null) return false;
        if (sgPartido != null ? !sgPartido.equals(despesa.sgPartido) : despesa.sgPartido != null) return false;
        if (codLegislatura != null ? !codLegislatura.equals(despesa.codLegislatura) : despesa.codLegislatura != null)
            return false;
        if (numSubCota != null ? !numSubCota.equals(despesa.numSubCota) : despesa.numSubCota != null) return false;
        if (txtDescricao != null ? !txtDescricao.equals(despesa.txtDescricao) : despesa.txtDescricao != null)
            return false;
        if (numEspecificacaoSubCota != null ? !numEspecificacaoSubCota.equals(despesa.numEspecificacaoSubCota) : despesa.numEspecificacaoSubCota != null)
            return false;
        if (txtDescricaoEspecificacao != null ? !txtDescricaoEspecificacao.equals(despesa.txtDescricaoEspecificacao) : despesa.txtDescricaoEspecificacao != null)
            return false;
        if (txtFornecedor != null ? !txtFornecedor.equals(despesa.txtFornecedor) : despesa.txtFornecedor != null)
            return false;
        if (txtCnpjcpf != null ? !txtCnpjcpf.equals(despesa.txtCnpjcpf) : despesa.txtCnpjcpf != null) return false;
        if (txtNumero != null ? !txtNumero.equals(despesa.txtNumero) : despesa.txtNumero != null) return false;
        if (indTipoDocumento != null ? !indTipoDocumento.equals(despesa.indTipoDocumento) : despesa.indTipoDocumento != null)
            return false;
        if (datEmissao != null ? !datEmissao.equals(despesa.datEmissao) : despesa.datEmissao != null) return false;
        if (vlrDocumento != null ? !vlrDocumento.equals(despesa.vlrDocumento) : despesa.vlrDocumento != null)
            return false;
        if (vlrGlosa != null ? !vlrGlosa.equals(despesa.vlrGlosa) : despesa.vlrGlosa != null) return false;
        if (vlrLiquido != null ? !vlrLiquido.equals(despesa.vlrLiquido) : despesa.vlrLiquido != null) return false;
        if (numMes != null ? !numMes.equals(despesa.numMes) : despesa.numMes != null) return false;
        if (numAno != null ? !numAno.equals(despesa.numAno) : despesa.numAno != null) return false;
        if (numParcela != null ? !numParcela.equals(despesa.numParcela) : despesa.numParcela != null) return false;
        if (txtPassageiro != null ? !txtPassageiro.equals(despesa.txtPassageiro) : despesa.txtPassageiro != null)
            return false;
        if (txtTrecho != null ? !txtTrecho.equals(despesa.txtTrecho) : despesa.txtTrecho != null) return false;
        if (numLote != null ? !numLote.equals(despesa.numLote) : despesa.numLote != null) return false;
        if (numRessarcimento != null ? !numRessarcimento.equals(despesa.numRessarcimento) : despesa.numRessarcimento != null)
            return false;
        if (vlrRestituicao != null ? !vlrRestituicao.equals(despesa.vlrRestituicao) : despesa.vlrRestituicao != null)
            return false;
        if (nuDeputadoId != null ? !nuDeputadoId.equals(despesa.nuDeputadoId) : despesa.nuDeputadoId != null)
            return false;
        if (ideDocumento != null ? !ideDocumento.equals(despesa.ideDocumento) : despesa.ideDocumento != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = txNomeParlamentar != null ? txNomeParlamentar.hashCode() : 0;
        result = 31 * result + (idecadastro != null ? idecadastro.hashCode() : 0);
        result = 31 * result + (nuCarteiraParlamentar != null ? nuCarteiraParlamentar.hashCode() : 0);
        result = 31 * result + (nuLegislatura != null ? nuLegislatura.hashCode() : 0);
        result = 31 * result + (sgUf != null ? sgUf.hashCode() : 0);
        result = 31 * result + (sgPartido != null ? sgPartido.hashCode() : 0);
        result = 31 * result + (codLegislatura != null ? codLegislatura.hashCode() : 0);
        result = 31 * result + (numSubCota != null ? numSubCota.hashCode() : 0);
        result = 31 * result + (txtDescricao != null ? txtDescricao.hashCode() : 0);
        result = 31 * result + (numEspecificacaoSubCota != null ? numEspecificacaoSubCota.hashCode() : 0);
        result = 31 * result + (txtDescricaoEspecificacao != null ? txtDescricaoEspecificacao.hashCode() : 0);
        result = 31 * result + (txtFornecedor != null ? txtFornecedor.hashCode() : 0);
        result = 31 * result + (txtCnpjcpf != null ? txtCnpjcpf.hashCode() : 0);
        result = 31 * result + (txtNumero != null ? txtNumero.hashCode() : 0);
        result = 31 * result + (indTipoDocumento != null ? indTipoDocumento.hashCode() : 0);
        result = 31 * result + (datEmissao != null ? datEmissao.hashCode() : 0);
        result = 31 * result + (vlrDocumento != null ? vlrDocumento.hashCode() : 0);
        result = 31 * result + (vlrGlosa != null ? vlrGlosa.hashCode() : 0);
        result = 31 * result + (vlrLiquido != null ? vlrLiquido.hashCode() : 0);
        result = 31 * result + (numMes != null ? numMes.hashCode() : 0);
        result = 31 * result + (numAno != null ? numAno.hashCode() : 0);
        result = 31 * result + (numParcela != null ? numParcela.hashCode() : 0);
        result = 31 * result + (txtPassageiro != null ? txtPassageiro.hashCode() : 0);
        result = 31 * result + (txtTrecho != null ? txtTrecho.hashCode() : 0);
        result = 31 * result + (numLote != null ? numLote.hashCode() : 0);
        result = 31 * result + (numRessarcimento != null ? numRessarcimento.hashCode() : 0);
        result = 31 * result + (vlrRestituicao != null ? vlrRestituicao.hashCode() : 0);
        result = 31 * result + (nuDeputadoId != null ? nuDeputadoId.hashCode() : 0);
        result = 31 * result + (ideDocumento != null ? ideDocumento.hashCode() : 0);
        return result;
    }
    
    @Override
    @Transient
    public BigDecimal getDespesa() {
    	return this.vlrDocumento.subtract(this.vlrGlosa);
    }
}
