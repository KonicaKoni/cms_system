package hr.crm.studentproject.aop.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggerAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${logger.aspect.enabled}")
	private boolean loggerAspectEnabled;

	@Before("hr.crm.studentproject.aop.config.CommonJoinPointConfig.serviceLayerExecution()")
	public void before(JoinPoint joinPoint) {
		logger.info(" ----------------------- Before --------------------- ");
		logger.info(joinPoint.getSignature().getName() + " called with " + Arrays.toString(joinPoint.getArgs()));
		logger.info(" ----------------------------------------------------- ");
	}
	
	@Around("@annotation(hr.crm.studentproject.aop.annotation.LogExecutionTime)")
	public void logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		if (loggerAspectEnabled) {
			logger.info(" ---------------------- LogExecutionTime ---------------------- ");
			long start = System.currentTimeMillis();

			long executionTime = System.currentTimeMillis() - start;

			logger.info("{} executed in {} ms", joinPoint.getSignature(), executionTime);
			logger.info(" ----------------------------------------------------- ");
		}
	}

	@After(value = "hr.crm.studentproject.aop.config.CommonJoinPointConfig.serviceLayerExecution()")
	public void after(JoinPoint joinPoint) {
		if(loggerAspectEnabled) {
			logger.info(" ----------------------- After --------------------- ");
			logger.info("execution of {}", joinPoint);
			logger.info(" -----------------------------------------------------");
		}
	}

}
