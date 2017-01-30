package com.psk.bank.configuration;

import javax.enterprise.inject.Produces;  
import javax.enterprise.inject.spi.InjectionPoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;  
  

public class LoggerProducer {  

    @Produces  
    public Logger produceLogger(InjectionPoint injectionPoint) {  
        return LogManager.getLogger(injectionPoint.getMember().getDeclaringClass().getName());  
    }  
}