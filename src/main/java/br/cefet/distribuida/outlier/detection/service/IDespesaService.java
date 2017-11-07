package br.cefet.distribuida.outlier.detection.service;

import java.util.List;
import java.util.Map;

import br.cefet.distribuida.outlier.detection.model.Despesa;

public interface IDespesaService {
	public List<Despesa> buscarDespesasPorAno(String ano);
	public Map<String, List<Despesa>> agruparDespesaPorTipoGasto(List<Despesa> despesas);
}
