package board.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Around("execution(* board..controller.*Controller.*(..)) or execution(* board..service.*Impl.*(..)) or execution(* board..mapper.*Mapper.*(..))")
	public Object logPrint(ProceedingJoinPoint joinpoint) throws Throwable{
		String type = "";
		String name = joinpoint.getSignature().getDeclaringTypeName();
		if(name.indexOf("Controller") > -1) {
			type = "Controller \t:  ";
		}
		else if(name.indexOf("Service") > -1) {
			type = "Service \t:  ";
		}
		else if(name.indexOf("Mapper") > -1) {
			type = "Mapper \t\t:  ";
		}
		log.debug(type + name + "." + joinpoint.getSignature().getName() + "()" );
		return joinpoint.proceed();
		
	}
			
}
