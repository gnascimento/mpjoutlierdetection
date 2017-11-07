package br.cefet.distribuida.outlier.detection;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import br.cefet.distribuida.outlier.detection.aspects.annotation.Performance;
import br.cefet.distribuida.outlier.detection.model.Despesa;
import br.cefet.distribuida.outlier.detection.model.Sumario;
import br.cefet.distribuida.outlier.detection.service.IDespesaService;
import br.cefet.distribuida.outlier.detection.service.IStatisticService;
import br.cefet.distribuida.outlier.detection.service.ISumarioService;

@Component
public class BeanPrincipal implements IBeanPrincipal {
	
	@Autowired
	private IDespesaService despesaService;
	
	@Autowired
	private IStatisticService statisticService;
	
	@Autowired
	private ISumarioService sumarioService;
	
	@Override
	@Performance(logName = "EXECUCAO_TOTAL", description = "Execucao do Bean Principal")
	public void run(String[] args) {
		Logger logger = Logger.getLogger(this.getClass().getName());
		/*
		 * Maquina 1: 2013 Maquina 2: 2014 Maquina 3: 2015 Maquina 4: 2016
		 */
		String ano;
		switch (OutlierdetectorApplication.getMe()) {
		case 0:
			ano = "2013";
			break;
		case 1:
			ano = "2014";
			break;
		case 2:
			ano = "2015";
			break;
		default:
			ano = "2016";
			break;
		}
		List<Despesa> despesas = despesaService.buscarDespesasPorAno(ano);
		logger.info("Ano : " + ano + " Tamanho do exemplo: " + despesas.size());
		Map<String, List<Despesa>> agrupamento = despesaService.agruparDespesaPorTipoGasto(despesas);
		logger.info("Tamanho do agrupamento por tipo de gasto: " + agrupamento.size());
		agrupamento.forEach((key, x) -> {
			
			statisticService.setDistribution(x);
			
			Sumario sumario = new Sumario(key, ano, Thread.currentThread().getId(), OutlierdetectorApplication.getMe());
			
			sumario.setMinimo(statisticService.min());
			sumario.setMaximo(statisticService.max());
			sumario.setMedia(statisticService.mean());
			sumario.setVariancia(statisticService.variance());
			sumario.setNumeroElementos(statisticService.count());
			sumario.setQuartil1(statisticService.quantile(1));
			sumario.setQuartil2(statisticService.quantile(2));
			sumario.setQuartil3(statisticService.quantile(3));
			
			sumario.setAno(ano);
			statisticService.normalizeZscore();
			
			List<Despesa> despesasOutliers = statisticService.detectOutliers();
			Gson gson = new Gson();
			String json = gson.toJson(despesasOutliers);
			sumario.setOutliers(json);
			sumarioService.add(sumario);
			
		});
		sumarioService.flush();
	}
}
