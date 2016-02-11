package sr.api.presentation.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import sr.api.presentation.vo.Response;

/**
 * Created by byzas on 11/02/16.
 */

@Aspect
@Component(value = "responseAspect")
public class ResponseAspect {

    private static Logger logger = Logger.getLogger(ResponseAspect.class);

    @Around("anyPublicMethod() && @annotation(prepareResponse)")
    public Object prepareResponse(ProceedingJoinPoint proceedingJoinPoint, PrepareResponse prepareResponse){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Response response = null;

        try {
            logger.info(new StringBuilder("Request is finished..\n ")
                    .append(proceedingJoinPoint.getTarget().getClass()
                            .getSimpleName()).toString());

            Object resp = proceedingJoinPoint.proceed();
            if(resp instanceof Response){
                response = (Response) resp;
                stopWatch.stop();
                response.setDuration(stopWatch.getTotalTimeMillis());
            }


        } catch (Throwable e) {

            logger.error(
                    new StringBuilder("Exception caught on ")
                            .append(proceedingJoinPoint.getTarget().getClass()
                                    .getSimpleName()).append(e.getMessage()).toString(), e);
            try {
                if(proceedingJoinPoint.proceed() instanceof Response){
                    stopWatch.stop();
                    response.setDuration(stopWatch.getTotalTimeMillis());
                }
            } catch (Throwable throwable) {

            }

        }
        return response;
    }



    @Pointcut(value="execution(public * *(..))")
    public void anyPublicMethod() { }





}
