package hr.crm.studentproject.aop.config;

import org.aspectj.lang.annotation.Pointcut;

public class CommonJoinPointConfig {
	@Pointcut("execution(* hr.crm.studentproject.service.*.*(..))")
	public void serviceLayerExecution(){}
}
