package javatest.arkadiuszotto.com.javatest.Model.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author arekotto
 */

public class ListSortTest implements PerformanceTest {
    @Override
    public double run(long iterations) {
        List<Integer> list = new ArrayList<>();
        long i = 0;
        Random random = new Random();
        while (i < iterations) {
            i++;
            list.add(random.nextInt(100));
        }
        long startTime = System.nanoTime();
        Collections.sort(list);
        long duration = System.nanoTime() - startTime;
        return duration / 1000000000d;
    }
}
