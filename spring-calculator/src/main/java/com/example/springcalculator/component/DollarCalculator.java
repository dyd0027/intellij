package com.example.springcalculator.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

//Spring에서 관리하게 할꺼임
@Component
@RequiredArgsConstructor
public class DollarCalculator implements ICalculator{

    private int price = 1;
    private final MarketApi marketApi;
    @Override
    public void init(){
        //어떤 사이트에서 환율을 가져온다는 가정
        this.price = marketApi.connect();
    }
    @Override
    public int sum(int x, int y) {
        return (x+y)*price;
    }

    @Override
    public int minus(int x, int y) {
        return (x-y)*price;
    }
}
