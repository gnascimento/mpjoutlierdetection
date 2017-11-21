package br.cefet.distribuida.outlier.detection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
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
		 * Lê os parâmetros
		 */
		List<String> params = Arrays.asList(args);
		int anos = params.indexOf("-anos");
		if (anos == -1) {
			logger.log(Level.SEVERE, "Parametros Insuficientes. Informe os anos");
			return;
		}

		List<String> anosExec = new ArrayList<>();
		for (int i = anos + 1; i < params.size(); i++) {
			try {
				String[] anoNode = params.get(i).split("-");
				if (anoNode[1].equals(String.valueOf(OutlierdetectorApplication.getMe()))) {
					anosExec.add(anoNode[0]);
				}
			} catch (Exception ex) {
				break;
			}
		}

		if (anosExec.size() == 0) {
			logger.log(Level.SEVERE, "Parametros Insuficientes. Informe os anos");
			return;
		}

		List<Despesa> despesas = despesaService.buscarDespesasPorAno(anosExec);
		logger.info("Anos : " + anosExec + " Tamanho do exemplo: " + despesas.size());
		Map<String, List<Despesa>> agrupamento = despesaService.agruparDespesaPorTipoGasto(despesas);
		logger.info("Tamanho do agrupamento por tipo de gasto e ano: " + agrupamento.size());
		agrupamento.forEach((key, x) -> {
			String ano = key.split("-")[0];
			String tipoGasto = key.split("-")[1];
			
			statisticService.setDistribution(x);
			
			Sumario sumario = new Sumario(tipoGasto, ano, Thread.currentThread().getId(), OutlierdetectorApplication.getMe());

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
