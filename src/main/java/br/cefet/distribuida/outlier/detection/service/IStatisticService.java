package br.cefet.distribuida.outlier.detection.service;

import java.math.BigDecimal;
import java.util.List;

import br.cefet.distribuida.outlier.detection.model.Despesa;

public interface IStatisticService {
	BigDecimal mean();
	long count();
	BigDecimal variance();
	BigDecimal sd();
	BigDecimal percentile(double percentile);
	List<BigDecimal> sort();
	BigDecimal quantile(int quantile);
	List<BigDecimal> generateRandomNormalDistribution(double mean, double sd, int size);
	void normalizeZscore();
	void setDistribution(List<Despesa> distribution);
	BigDecimal max();
	BigDecimal min();
	public List<Despesa> detectOutliers();
}
