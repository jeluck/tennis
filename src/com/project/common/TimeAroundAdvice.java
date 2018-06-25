package com.project.common;



import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;



//@Aspect
//@Component
public class TimeAroundAdvice {
	
//	@Pointcut("execution(* com.project.service.*.*(..))")
//    public void perform(){}
	
//	@Around("perform()")
//	@Override
//	public Object invoke(MethodInvocation invocation) throws Throwable {
//		  System.out.println("睡觉前要脱衣服!");
//		Object result = invocation.proceed();  
//		 System.out.println("睡醒了要穿衣服！");
//		return result;  
//	}
	
//	@Around("perform()")  
//    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable{  
//        System.out.println("进入环绕通知");  
//        Object object = pjp.proceed();//执行该方法  
//        System.out.println("退出方法");  
//        return object;  
//    }  

//	public void watchPerformance(ProceedingJoinPoint joinpoint){
//        try{
//            System.out.println("begin!");
//            long start = System.currentTimeMillis();
//            
//            joinpoint.proceed();
//            
//            long end = System.currentTimeMillis();
//            System.out.println("end!        performance took "+(end-start)+" milliseconds");
//        }catch(Throwable e){
//            System.out.println("eee!We want our money back!");
//        }
//    }
}
