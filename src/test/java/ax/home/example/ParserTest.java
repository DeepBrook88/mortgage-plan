package ax.home.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    private List<Customer> customers;

    @BeforeEach
    void setUp() {
        customers = new ArrayList<>();
        Customer customer = new Customer("Bjarne",10000, 5, 5.5);
        customers.add(customer);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCustomers() {
        assertEquals("Bjarne",customers.get(0).getName());
    }

    @Test
    void getPrintString() {
    }

    @Test
    void addCustomer() {
        Customer customer1 = new Customer("Bjarne",10000, 5, 5.5);
        Customer customer2 = new Customer("Bjarne",10000, 5, 5.5);
        customers.add(customer1);
        customers.add(customer2);
        assertEquals(3, customers.size());
    }
}