package org.example;

public class KrwCalculator implements ICalculator{
    @Override
    public int sum(int x, int y) {
        return x+y;
    }

    @Override
    public int minus(int x, int y) {
        return x-y;
    }
}
