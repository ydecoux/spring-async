/*
 * $HeadURL$
 * $Revision$
 * $Date$
 * $Author$
 * 
 * Application: spring-async
 * Contractor: ARHS
 */
package be.test;

import java.util.Random;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class AwesomeService {
    private static Logger LOGGER = LoggerFactory.getLogger(AwesomeService.class);

    @Async
    public Future<Result> doSomethingAsync(int a, int b) throws InterruptedException {
        LOGGER.info("Computing {} & {}", a, b);
        Thread.sleep(new Random().nextInt(5000));
        Result result = new Result(a, b);
        LOGGER.info("Done computing {} & {}", a, b);
        return new AsyncResult<Result>(result);
    }
}
