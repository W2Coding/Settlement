package com.w2coding.settlementserver.common.exception;

import io.grpc.Metadata;
import io.grpc.stub.StreamObserver;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class GrpcExceptionAop {

    @Pointcut("execution(* com.w2coding.settlementserver.*.grpc.*.*(..))")
    private void cut() {}

    @AfterThrowing(value = "cut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        Object[] args = joinPoint.getArgs();
        StreamObserver<?> observer = (StreamObserver<?>) args[1];

        GlobalException exception = ex instanceof GlobalException? (GlobalException) ex : new GlobalError();

        Metadata trailers = new Metadata();
        trailers.put(Metadata.Key.of("code", Metadata.ASCII_STRING_MARSHALLER), exception.getCode());
        trailers.put(Metadata.Key.of("message", Metadata.ASCII_STRING_MARSHALLER), exception.getMessage());

        observer.onError(exception.getStatus().asRuntimeException(trailers));
    }

}
