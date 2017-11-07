package br.cefet.distribuida.outlier.detection.persistence;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import br.cefet.distribuida.outlier.detection.model.Despesa;

public interface IDespesaDAO extends Repository<Despesa, Long> {
	
	@Query("select new br.cefet.distribuida.outlier.detection.model.Despesa("
			+ "d.id, d.txNomeParlamentar, d.idecadastro, d.nuCarteiraParlamentar," + 
			"    		d.sgUf, d.sgPartido, d.txtDescricao, d.vlrDocumento, d.vlrGlosa," + 
			"    		d.numMes, d.numAno)"
			+ " from Despesa d where d.numAno = :ano")
    public List<Despesa> buscarDespesasPorAno(@Param("ano") String ano);
}
