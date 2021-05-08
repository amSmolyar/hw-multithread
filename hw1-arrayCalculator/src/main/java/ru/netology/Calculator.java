package ru.netology;

import java.util.concurrent.RecursiveTask;

public class Calculator extends RecursiveTask<Double> {
    public final int THRESHOLD;

    private double[] array;
    private int start;
    private int end;

    public Calculator(double[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.THRESHOLD = array.length / Runtime.getRuntime().availableProcessors();
    }

    @Override
    protected Double compute() {
        if (end - start < THRESHOLD) {
            double sum = 0;
            for (int ii = start; ii < end; ii++) {
                sum += array[ii];
            }
            return sum;
        } else {
            int middle = Math.round((end - start) / 2) + start;
            Calculator first = new Calculator(array, start, middle);
            Calculator second = new Calculator(array, middle, end);
            invokeAll(first, second);
            return first.join() + second.join();
        }
    }
}
