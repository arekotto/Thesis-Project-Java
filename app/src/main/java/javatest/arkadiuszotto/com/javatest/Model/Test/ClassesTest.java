package javatest.arkadiuszotto.com.javatest.Model.Test;

import javatest.arkadiuszotto.com.javatest.Model.Test.PerformanceTest;
import javatest.arkadiuszotto.com.javatest.Model.User;

/**
 * @author arekotto
 */

public class ClassesTest implements PerformanceTest {

    @Override
    public double run(long iterations) {
        long i = 0;
        long startTime = System.nanoTime();
        while (i < iterations) {
            i++;
            User user = new User("John Smith", (int) i);
        }
        long duration = System.nanoTime() - startTime;
        return duration / 1000000000d;
    }
}
