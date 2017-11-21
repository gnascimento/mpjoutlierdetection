package br.cefet.distribuida.outlier.detection.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.cefet.distribuida.outlier.detection.aspects.annotation.Performance;
import br.cefet.distribuida.outlier.detection.model.Despesa;
import br.cefet.distribuida.outlier.detection.persistence.IDespesaDAO;

@Component
public class DespesaService implements IDespesaService {
	
	@Autowired
	private IDespesaDAO dao;
	
	@Performance(logName = "BUSCAR_DESPESAS_ANO_DB", description = "Buscar despesas do banco de dados")
	public List<Despesa> buscarDespesasPorAno(List<String> ano) {
		List<Despesa> despesas = dao.buscarDespesasPorAno(ano);
		return despesas;
	}
	
	@Performance(logName = "AGRUPA_DESPESAS_TIPO_GASTO", description = "Agrupa Despesas por Tipo de Gasto")
	public Map<String, List<Despesa>> agruparDespesaPorTipoGasto(List<Despesa> despesas) {
		Map<String, List<Despesa>> agrupamento = despesas.parallelStream()
				.collect(Collectors.groupingBy((x) -> 
					 (x.getNumAno() + "-" + x.getTxtDescricao())
				));
		return agrupamento;
	}
	
	@Performance(logName = "MAPEIA_POR_GASTO", description = "Agrupa Despesas por Tipo de Gasto")
	public List<BigDecimal> mapeiaPorValor(List<Despesa> despesas) {
		List<BigDecimal> agrupamento = despesas.parallelStream()
				.map(x -> x.getDespesa())
				.collect(Collectors.toList());
		return agrupamento;
	}
}
