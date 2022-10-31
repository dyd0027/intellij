package org.example;

public class Main {
    //원래는 이런식으로 테스트 하지만 junit 쓰면 다르다잉
    public static void main(String[] args) {

        System.out.println("Hello world!");

        Calculator calculator = new Calculator(new KrwCalculator());
        System.out.println(calculator.sum(10,10));

        DollarCalculator dollarCalculator = new DollarCalculator(new MarketApi());
        dollarCalculator.init();
        calculator = new Calculator(dollarCalculator);
        System.out.println(calculator.sum(10,10));
    }
}