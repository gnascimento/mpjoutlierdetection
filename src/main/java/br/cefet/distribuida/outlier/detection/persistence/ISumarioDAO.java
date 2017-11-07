package br.cefet.distribuida.outlier.detection.persistence;

import org.springframework.data.repository.CrudRepository;

import br.cefet.distribuida.outlier.detection.model.Sumario;

public interface ISumarioDAO extends CrudRepository<Sumario, Long>{

}
