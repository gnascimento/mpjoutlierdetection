package br.cefet.distribuida.outlier.detection;

import java.util.Properties;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import br.cefet.distribuida.outlier.detection.aspects.PerformanceAspect;
import br.cefet.distribuida.outlier.detection.log.IPerformanceLog;
import br.cefet.distribuida.outlier.detection.log.PerformanceLog;
import br.cefet.distribuida.outlier.detection.service.IStatisticService;
import br.cefet.distribuida.outlier.detection.service.ISumarioService;
import br.cefet.distribuida.outlier.detection.service.StatisticService;
import br.cefet.distribuida.outlier.detection.service.SumarioService;
import mpi.MPI;

@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"br.cefet.distribuida.outlier.detection"})
public class OutlierdetectorApplication {
	private static int me;
	private static Long executionId;
	
	private IPerformanceLog performanceLog;
	
	public static int getMe() {
		return me;
	}
	
	public static Long getExecutionId() {
		return executionId;
	}
	
	private static int clusterSize;
	
	public static int getClusterSize() {
		return clusterSize;
	}
	
	public static void main(String[] args) {
		Logger logger = Logger.getLogger(OutlierdetectorApplication.class.getName());
		System.out.println("Iniciando execucao...");
		MPI.Init(args);
		me = MPI.COMM_WORLD.Rank();
		clusterSize = MPI.COMM_WORLD.Size();
		System.out.println("Maquina <" + getMe() + "> Num Maquinas <" + getClusterSize() + ">");
		logger.info("Dividir o trabalho por ano de acordo com a maquina");
		
		//ApplicationContext ctx = SpringApplication.run(OutlierdetectorApplication.class, args);
		SpringApplication application = new SpringApplication(OutlierdetectorApplication.class);
		
		//Configuracao do nome do Pool JMX diferente para cada cluster
		Properties prop = new Properties();
		prop.setProperty("spring.datasource.hikari.pool-name", "HikariPool" + getMe());
		application.setDefaultProperties(prop);
		//Inicia o Spring
		ApplicationContext ctx = application.run(args);
		if(ctx.containsBean("beanPrincipal")) {
			IBeanPrincipal principal = (IBeanPrincipal)ctx.getBean("beanPrincipal");
			principal.run(args);
			IPerformanceLog performanceLog = (IPerformanceLog)ctx.getBean("performanceLog");
			performanceLog.flush();
		}
		MPI.Finalize();
		logger.info("Terminado...");
	}
	
	@Bean(name = "beanPrincipal")
	public IBeanPrincipal getBeanPrincipal() {
		return new BeanPrincipal();
	}
	
	@Bean
	public PerformanceAspect getPerformanceAspect() {
		return new PerformanceAspect();
	}
	
	@Bean
	public IPerformanceLog getPerformanceLog() {
		if(performanceLog == null) {
			performanceLog = new PerformanceLog();
		}
		return performanceLog;
	}
	
	@Bean
	public IStatisticService getStatisticService() {
		return new StatisticService();
	}
	
	@Bean
	public ISumarioService sumarioService() {
		return new SumarioService();
	}
}
