package javatest.arkadiuszotto.com.javatest.Model.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javatest.arkadiuszotto.com.javatest.Model.User;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * @author arekotto
 */

public class ListAddTest implements PerformanceTest {
    @Override
    public double run(long iterations) {

        List<Long> list = new ArrayList<>();
        long i = 0;
        long startTime = System.nanoTime();
        while (i < iterations) {
            i++;
            list.add(i);
            if (list.size() > 10000) {
                list.clear();
            }
        }
        long duration = System.nanoTime() - startTime;
        return duration / 1000000000d;
    }
}
