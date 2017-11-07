package br.cefet.distribuida.outlier.detection.log;

import br.cefet.distribuida.outlier.detection.model.LogExecucao;

public interface IPerformanceLog {
	public void addToStack(LogExecucao logExecucao);
	public void flush();
}
