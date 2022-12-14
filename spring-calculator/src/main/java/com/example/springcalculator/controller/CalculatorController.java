package com.example.springcalculator.controller;

import com.example.springcalculator.component.Calculator;
import com.example.springcalculator.ddto.Req;
import com.example.springcalculator.ddto.Res;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CalculatorController {
    private final Calculator calculator;

    @GetMapping("/sum")
    public int sum(@RequestParam int x, @RequestParam int y){
        return calculator.sum(x,y);
    }
    @PostMapping("/minus")
    public Res minus(@RequestBody Req req){
        int result = calculator.minus(req.getX(),req.getY());
        Res res = new Res();
        res.setResult(result);
        res.setResponse(new Res.Body());
        return res;
    }

}
