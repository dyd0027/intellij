import org.example.Calculator;
import org.example.DollarCalculator;
import org.example.KrwCalculator;
import org.example.MarketApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DollarCalculatorTest {
    @Mock
    public MarketApi marketApi;
    @BeforeEach
    public void init(){
        Mockito.lenient().when(marketApi.connect()).thenReturn(3000);
    }

    @Test
    public void testHello(){
        System.out.println("test HI!");
    }

    @Test
    public void testJUnit(){
        DollarCalculator dollarCalculator = new DollarCalculator(new MarketApi());
        dollarCalculator.init();
        Calculator calculator = new Calculator(dollarCalculator);

        //이제는 sout으로 찍는게 아니라
        //System.out.println(calculator.sum(10,10));
        Assertions.assertEquals(28000,calculator.sum(10,10));
        Assertions.assertEquals(28000,calculator.minus(10,10));
    }@Test
    public void MockTest(){
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();
        Calculator calculator = new Calculator(dollarCalculator);

        //이제는 sout으로 찍는게 아니라
        //System.out.println(calculator.sum(10,10));
        Assertions.assertEquals(30000,calculator.sum(10,10));
        Assertions.assertEquals(0       ,calculator.minus(10,10));
    }
}
