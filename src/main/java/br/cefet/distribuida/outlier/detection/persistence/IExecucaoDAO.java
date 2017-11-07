package br.cefet.distribuida.outlier.detection.persistence;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.Repository;

import br.cefet.distribuida.outlier.detection.model.Execucao;

public interface IExecucaoDAO extends Repository<Execucao, Long> {
	
	/*
	 * Avisa ao servidor de banco de dados que sera feita uma nova execucao
	 */
	@Procedure(procedureName = "p_criar_execucao")
	public Long iniciarExecucao();
}
