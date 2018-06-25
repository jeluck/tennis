package com.project.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UseHowTime {
	private Logger logger = Logger.getLogger(this.getClass());

	private long currentTime;

	//第一种方法:用前置增强和后置增强配合
	//定义在controller包或者子包里的任意方法的执行
//	@Before("execution(* com.project.controller..*.*(..))")
//	public void before() {
//		currentTime = System.currentTimeMillis();
//		logger.info("进入方法开始计时...当前时间戳:" + currentTime);
//	}
//
//	@AfterReturning("execution(* com.project.controller..*.*(..)) ")
//	public void after() {
//		logger.info("运行方法结束时长..." + ((System.currentTimeMillis() - currentTime))+"毫秒");
//	}

	
//	第二种方法用环绕增强
//	定义在controller包或者子包里的任意方法的执行 
    @Around("execution(* com.project.controller..*.*(..)) ")  
    public Object arountAction(ProceedingJoinPoint pjp){  
    	long startTime=System.currentTimeMillis();
        Object result = null;  
        try {
        	System.err.println("开始时间戳"+startTime+",类名称"+pjp.getSignature().getDeclaringTypeName()+",方法名称:"+pjp.getSignature().getName());
        	//执行目标方法  
            result = pjp.proceed();
            long endTime=System.currentTimeMillis();
            float excTime=(float)(endTime-startTime)/1000;
        	System.err.println("时间戳为"+startTime+",执行时间："+excTime+"秒");
        } catch (Throwable e) {  
        	e.printStackTrace();
        	logger.error("执行AOP环绕增强出错.....");  
        }  
        return result;  
    }  
}
