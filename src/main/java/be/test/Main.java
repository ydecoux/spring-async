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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * TODO.
 */
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
        AwesomeService bean = ctx.getBean(AwesomeService.class);
        List<Future<Result>> results = new ArrayList<Future<Result>>();
        for (int i = 0; i < 5; i++) {
            int a = new Random().nextInt(100);
            int b = new Random().nextInt(100);
            results.add(bean.doSomethingAsync(a, b));
        }
        int done = 0;
        LOGGER.info("Checking responses");
        do {
            Iterables.removeIf(results, new Predicate<Future<Result>>() {
                public boolean apply(Future<Result> input) {
                    if (input.isDone()) {
                        try {
                            Result result;
                            result = input.get();
                            LOGGER.info("Got response : {} + {} = {}", new Object[] {
                                result.getA(), result.getB(), result.getSum() });
                        } catch (Exception e) {
                            throw new UnsupportedOperationException(
                                "Catch an unexpected 'InterruptedException' exception in '.apply'. Exception message:'"
                                    + e.getMessage() + "'", e);
                        }
                        return true;
                    }
                    return false;
                }
            });
            //LOGGER.info("Waiting 100 ms for next responses check");
            Thread.sleep(100);
        } while (!results.isEmpty());
    }
}
