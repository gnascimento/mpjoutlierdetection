package br.cefet.distribuida.outlier.detection.log;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.cefet.distribuida.outlier.detection.model.LogExecucao;
import br.cefet.distribuida.outlier.detection.persistence.ILogExecucaoDAO;

@Component
@Scope(value = "singleton")
public class PerformanceLog implements IPerformanceLog {

	volatile Map<Long, Stack<LogExecucao>> execucao = new HashMap<>();

	@Autowired
	private ILogExecucaoDAO logExecucaoDAO;

	public void addToStack(LogExecucao logExecucao) {
		logExecucao.setThreadId(Thread.currentThread().getId());
		System.out.println("Thread " + logExecucao.getThreadId());
		if(!execucao.containsKey(logExecucao.getThreadId())) {
			execucao.put(logExecucao.getThreadId(), new Stack<>());
		}
		execucao.get(logExecucao.getThreadId()).push(logExecucao);
	}

	public void flush() {
		for(Long i : execucao.keySet()) {
			Stack<LogExecucao> pilha = execucao.get(i); 
			System.out.println("Tamanho da pilha " + execucao.size());
			logExecucaoDAO.saveAll(pilha);
		}
	}
}
