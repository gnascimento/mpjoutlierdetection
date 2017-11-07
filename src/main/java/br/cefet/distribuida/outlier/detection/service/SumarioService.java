package br.cefet.distribuida.outlier.detection.service;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.cefet.distribuida.outlier.detection.aspects.annotation.Performance;
import br.cefet.distribuida.outlier.detection.model.Sumario;
import br.cefet.distribuida.outlier.detection.persistence.ISumarioDAO;

@Component
public class SumarioService implements ISumarioService {
	
	@Autowired
	private ISumarioDAO sumarioDAO;
	
	private LinkedList<Sumario> fila = new LinkedList<>();
	
	@Override
	public Sumario add(Sumario sumario) {
		fila.add(sumario);
		return sumario;
	}

	@Override
	@Performance(logName = "SALVAR_SUMARIOS", description = "Salva todos os Sumários")
	public void flush() {
		sumarioDAO.saveAll(fila);
	}

}
