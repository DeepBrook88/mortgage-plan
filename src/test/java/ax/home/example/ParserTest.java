package ax.home.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
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
        // Kalle Anka,12345,5,10
        // "Joakim von Anka","1400",3.45,4
        // "Knatte,Anka",430,3.5,6
        Parser p = new Parser();
        p.parse("/test/testRead.txt");
        assertEquals("Kalle Anka", p.getCustomers().get(0).getName());
        assertEquals("Joakim von Anka", p.getCustomers().get(1).getName());
        assertEquals(1400, p.getCustomers().get(1).getAmount());
        assertEquals("Knatte,Anka", p.getCustomers().get(2).getName());
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