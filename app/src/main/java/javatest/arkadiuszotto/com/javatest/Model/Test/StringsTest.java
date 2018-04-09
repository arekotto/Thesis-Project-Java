package javatest.arkadiuszotto.com.javatest.Model.Test;


/**
 * @author arekotto
 */

public class StringsTest implements PerformanceTest {
    @Override
    public double run(long iterations) {
        long i = 0;
        long startTime = System.nanoTime();
        while (i < iterations) {
            i++;
            String string = "Example Number: " + i;
        }
        long duration = System.nanoTime() - startTime;
        return duration / 1000000000d;
    }
}
