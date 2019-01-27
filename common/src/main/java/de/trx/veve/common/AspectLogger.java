package de.trx.veve.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Logger der sich zwischen die Methondenaufrufe der Facade und der Businessmethoden einklingt.
 *
 * @author [MLA] Marcus Lanvers | Marcus.Lanvers@LMIS.de
 */
@Aspect
@Component
public class AspectLogger {

    @Before("execution(* de.trx.veve.facade..*(..)) || execution(* de.trx.veve.business..*(..))")
    public static void monitorMethodEntry(JoinPoint joinPoint) {
        Logger LOGGER = getLogger(joinPoint);

        LOGGER.info("ENTRY - {}", joinPoint.getSignature().toLongString());
        try {
            if (joinPoint.getArgs().length > 0) {
                LOGGER.info("METHOD PARAMETERS - {}" + Arrays.stream(joinPoint.getArgs()).map((object) -> object.toString() + ","));
            } else {
                LOGGER.info("PARAMETERS - none");
            }
        } catch (Exception e) {
            LOGGER.warn("Could not log functioncall: " + joinPoint.getSignature().toLongString(), e);
        }
    }

    @AfterReturning("execution(* de.trx.veve..facade..*(..)) || execution(* de.trx.veve..business..*(..))")
    public static void monitorMethodExit(JoinPoint joinPoint) {
        Logger LOGGER = getLogger(joinPoint);
        LOGGER.info("LEFT METHOD: " + joinPoint.getSignature().toLongString());
    }

    private static Logger getLogger(JoinPoint joinPoint) {
        return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
    }

}
