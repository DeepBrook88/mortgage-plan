package ax.home.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer("Bjarne",10000, 5, 5.5);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCostPerMonth() {
        double result = customer.getCostPerMonth();
        assertEquals(191.01162, result, 0.001);
    }

    @Test
    void power() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        double expectedResult = 10000;
        Method pow = customer.getClass().getDeclaredMethod("power", double.class, double.class);
        pow.setAccessible(true);
        double res = (double)pow.invoke(customer, 10,4);
        assertEquals(expectedResult, res);
        res = (double)pow.invoke(customer, -5,2);
        assertEquals(25, res);
    }
}