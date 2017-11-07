package br.cefet.distribuida.outlier.detection.service;

import br.cefet.distribuida.outlier.detection.model.Sumario;

public interface ISumarioService {
	public Sumario add(Sumario sumario);
	public void flush();
}
