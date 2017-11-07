package br.cefet.distribuida.outlier.detection.aspects;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import br.cefet.distribuida.outlier.detection.OutlierdetectorApplication;
import br.cefet.distribuida.outlier.detection.aspects.annotation.Performance;
import br.cefet.distribuida.outlier.detection.log.IPerformanceLog;
import br.cefet.distribuida.outlier.detection.model.LogExecucao;

@Aspect
public class PerformanceAspect {
	
	@Autowired
	private IPerformanceLog performanceLog;
	
	@Around("@annotation(br.cefet.distribuida.outlier.detection.aspects.annotation.Performance) && execution(* *(..))")
	public Object logPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
		long inicio = System.currentTimeMillis();
		// Pega o nome do log na anotacao
		Performance p = null;
		if (joinPoint.getSignature() instanceof MethodSignature) {
			MethodSignature signature = (MethodSignature) joinPoint.getSignature();
			p = signature.getMethod().getAnnotation(Performance.class);
		}
		Object ret = joinPoint.proceed();
		long fim = System.currentTimeMillis();
		LogExecucao log = new LogExecucao();
		log.setDataHoraCriacao(new Date());
		log.setInicio(inicio);
		log.setFim(fim);
		if(p != null) {
			log.setDescricao(p.description());
			log.setNomeLog(p.logName());
			log.setTraceId(OutlierdetectorApplication.getMe());
		}
		performanceLog.addToStack(log);
		return ret;
	}
}
