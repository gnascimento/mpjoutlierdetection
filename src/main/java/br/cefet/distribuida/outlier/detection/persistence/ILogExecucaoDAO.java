package br.cefet.distribuida.outlier.detection.persistence;

import org.springframework.data.repository.CrudRepository;

import br.cefet.distribuida.outlier.detection.model.LogExecucao;

public interface ILogExecucaoDAO extends CrudRepository<LogExecucao, Long>{

}
