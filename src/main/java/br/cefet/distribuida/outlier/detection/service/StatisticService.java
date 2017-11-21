package br.cefet.distribuida.outlier.detection.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.springframework.stereotype.Component;

import br.cefet.distribuida.outlier.detection.aspects.annotation.Performance;
import br.cefet.distribuida.outlier.detection.model.Despesa;
import br.cefet.distribuida.outlier.detection.model.IDespesa;

@Component
public class StatisticService implements IStatisticService {

    private List<BigDecimal> distribution;
    private List<Despesa> input;

    private Long count = null;
    private BigDecimal mean = null;
    private BigDecimal variance = null;
    private boolean sorted = false;
    private boolean normalized = false;
    
    public StatisticService() {
    	
    }
    
    @Performance(logName = "CONFIGURAR_DISTRIBUICAO", description = "Configurar Distribuicao")
    public void setDistribution(List<Despesa> distribution) {
    	this.input = distribution;
    	this.distribution = distribution.parallelStream()
        		.map(x -> x.getDespesa())
        		.collect(Collectors.toList());
    	this.sorted = false;
    	this.count = null;
    	this.variance = null;
    	this.mean = null;
    	normalized = false;
    }
    
    public StatisticService(List<IDespesa> distribution) {
        this.distribution = distribution.parallelStream()
        		.map(x -> x.getDespesa())
        		.collect(Collectors.toList());
    }
    
    @Performance(logName = "CALCULAR_MEDIA", description = "Calcular Média")
    public BigDecimal mean() {
        if(this.mean == null) {
            double m  = distribution.parallelStream()
                    .mapToDouble(BigDecimal::doubleValue).average().getAsDouble();
            this.mean = new BigDecimal(m);
        }
        return this.mean;
    }
    
    @Performance(logName = "CONTAR_NUM_ELEMENTOS", description = "Contar Número de Elementos")
    public long count() {
        if(count == null) {
            this.count = distribution.parallelStream().count();
        }
        return count;
    }
    
    @Performance(logName = "CALCULAR_VARIANCIA", description = "Calcular Variância")
    public BigDecimal variance() {
        if(this.variance == null)  {
            double mean = this.mean().doubleValue();
            double v = this.distribution.parallelStream()
                    .mapToDouble(BigDecimal::doubleValue)
                    .map((a) -> Math.pow(a - mean, 2))
                    .sum();
            if(this.count == null) {
                this.count = this.count();
            }
            v = v / (this.count - 1);
            this.variance = new BigDecimal(v);
        }
        return this.variance;
    }
    
  
    @Performance(logName = "CALCULAR_DESVIO_PADRAO", description = "Calcular Desvio Padrao")
    public BigDecimal sd() {
        if(this.variance == null) {
            this.variance = this.variance();
        }
        double standardDeviation= Math.sqrt(variance.doubleValue());
        return new BigDecimal(standardDeviation);
    }
    
    @Performance(logName = "CALCULAR_PERCENTIL", description = "Calcular Percentil")
    public BigDecimal percentile(double percentile) {
        if(percentile >= 1 && percentile <= 100) {
            if(this.count == null) {
                this.count = this.count();
            }
            percentile = (percentile / 100.00);
            //Nao precisa somar 1 como na formula original, pois o indice comeca com zero
            long pos = Math.round(percentile * (this.count -1));
            this.sort();
            System.out.println("Posicao percentil: " + pos);
            return this.distribution.get((int) pos);
        } else {
            throw new IllegalArgumentException("Percentil invalido");
        }
    }
    
    @Performance(logName = "ORDENAR_ELEMENTOS", description = "Ordenar Elementos")
    public List<BigDecimal> sort() {
        //Ordena a amostra apenas se nunca ordenou antes. Evita computacao desnecessaria
        if(!this.sorted) {
            this.distribution = this.distribution.parallelStream().sorted().collect(Collectors.toList());
            this.sorted = true;
        }
        return this.distribution;
    }
    
    @Performance(logName = "CALCULAR_QUARTIL", description = "Calcular Quartil")
    public BigDecimal quantile(int quantile) {
        if(quantile >= 1 && quantile <= 4) {
            if(this.count == null) {
                this.count = this.count();
            }
            //Nao precisa somar 1 como na formula original, pois o indice comeca com zero
            int pos = (int)Math.round((this.count - 1) * 0.25 * quantile);
            this.sort();
            System.out.println("Posicao quartil: " + pos);
            return this.distribution.get(pos);
        } else {
            throw new IllegalArgumentException("Quantil invalido");
        }
    }
    
    @Performance(logName = "NORMALIZACAO_Z_SCORE", description = "Normalização Z-Score")
    public void normalizeZscore() {
    	if(!this.normalized) {
        	BigDecimal mean = mean();
        	BigDecimal sd = sd();
        	this.distribution = this.distribution.parallelStream()
        			.map(x -> (x.subtract(mean)).divide(sd, 2, RoundingMode.HALF_UP))
        			.collect(Collectors.toList());
        	this.normalized = true;
    	}
    }

    public List<BigDecimal> generateRandomNormalDistribution(double mean, double sd, int size) {
        NormalDistribution dist = new NormalDistribution(mean, sd);
        double[] tmp = dist.sample(size);
        List<BigDecimal> smp = Arrays.stream(tmp).boxed()
                .map(BigDecimal::valueOf)
                .collect(Collectors.toList());
        return smp;
    }

	@Override
	@Performance(logName = "CALCULAR_MAXIMO", description = "Calcular máximo")
	public BigDecimal max() {
		BigDecimal max = null;
		if(!this.distribution.isEmpty()) {
			if(!sorted) {
				this.sort();
			}
			max = this.distribution.get(this.distribution.size() - 1);
		}
		return max;
	}

	@Override
	@Performance(logName = "CALCULAR_MINIMO", description = "Calcular mínimo")
	public BigDecimal min() {
		BigDecimal min = null;
		if(!this.distribution.isEmpty()) {
			if(!sorted) {
				this.sort();
			}
			min = this.distribution.get(0);
		}
		return min;
	}
	
	@Override
	@Performance(logName = "DETECTAR_OUTLIERS", description = "Detectar Outliers")
	public List<Despesa> detectOutliers() {
		//OC = (P87,5 − 2 * Q2 + P12,5)/(P87,5 − P12, 5)
		BigDecimal p875 = this.percentile(87.5);
		BigDecimal p125 = this.percentile(12.5);
		//mediana
		BigDecimal q2 = this.quantile(2);
		// 2 * q2
		BigDecimal middle = q2.multiply(new BigDecimal(2));
		
		BigDecimal oc = p875.subtract(middle).add(p125);
		oc = oc.divide(p875.subtract(p125), 2, RoundingMode.HALF_UP);
		System.out.println("OC: " + oc.setScale(2, RoundingMode.HALF_UP));
		//OutliersSuperior = Q3 + 1,5*(Q3-Q1) ∗ e^0,5*(OC)
		BigDecimal q3 = this.quantile(3);
		BigDecimal q1 = this.quantile(1);
		BigDecimal q3Minusq1 = q3.subtract(q1);
		BigDecimal eParticle = new BigDecimal(Math.pow(Math.E, 0.5 * oc.doubleValue()));
		BigDecimal outliersSuperiores = q3.add(new BigDecimal("1.5").multiply(q3Minusq1).multiply(eParticle));
		//BigDecimal outliersSuperiores = q3.add(new BigDecimal("1.5").multiply(q3Minusq1).multiply(eParticle));
		/*System.out.println("Outliers Superiores: " + outliersSuperiores.setScale(2, RoundingMode.HALF_UP));
		System.out.println("Min: " + this.min());
		System.out.println("Max: " + this.max());
		System.out.println("Media: " + this.mean());
		System.out.println("Q1: " + q1);
		System.out.println("Q2: " + q2);
		System.out.println("Q3: " + q3);*/
		//Desnormaliza os dados
		List<Despesa> despesasOutliers = null;
		if(this.normalized && mean != null && variance != null) {
			BigDecimal sd = new BigDecimal(Math.sqrt(variance.doubleValue()));
    		//Desnormaliza e filtra
    		BigDecimal outliersSuperioresDesnorm = outliersSuperiores.multiply(sd).add(mean);
    		
    		despesasOutliers = this.input.parallelStream()
    				.filter(x -> x.getDespesa().compareTo(outliersSuperioresDesnorm) >= 0)
    				.collect(Collectors.toList());
    	} else {
    		despesasOutliers = this.input.parallelStream()
    				.filter(x -> x.getDespesa().compareTo(outliersSuperiores) >= 0)
    				.collect(Collectors.toList());
    	}
		return despesasOutliers;
	}
}
