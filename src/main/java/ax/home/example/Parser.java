package ax.home.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    @JsonProperty
    private List<Customer> customers;

    public Parser() {
        customers = new ArrayList<>();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
    public void addCustomer(Customer c) {
        this.customers.add(c);
    }

    public void parse(String path) {
        try (InputStream configStream = Parser.class.getResourceAsStream(path)){
            BufferedReader configReader = new BufferedReader(new InputStreamReader(configStream, StandardCharsets.UTF_8));
            CsvSchema schema = CsvSchema.emptySchema().withHeader();
            ObjectMapper objectMapper = new CsvMapper();
            MappingIterator<Customer> mI = objectMapper.readerFor(Customer.class).with(schema).readValues(configReader);
            customers = mI.readAll();
            cleanList(customers);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /* Removes invalid customers from the list */
    private void cleanList(List<Customer> list){
        for (int i = 0; i < list.size(); i++) {
            Customer c = list.get(i);
            if (c.getName().equals("") || c.getAmount() == 0 || c.getYears() == 0 || c.getInterest() == 0) {
                list.remove(i);
                i--;
            }
        }
    }

    public String getPrintString() {
        if (customers.isEmpty()) {
            return "";
        }
        String separator = new String(new char[100]).replace('\0', '*');
        StringBuilder result = new StringBuilder(separator);
        for (int i = 0; i < customers.size(); i++) {
            Customer c = customers.get(i);
            result.append(String.format(
                    "\n\nProspect %d: %s wants to borrow %.2f € for a period of %d years and pay %.2f € each month\n\n",
                    i + 1, c.getName(), c.getAmount(), c.getYears(), c.getCostPerMonth()));
            result.append(separator);
        }
        return result.toString();
    }

    /* Prints out to console */
    public void printCustomers() {
        String separator = new String(new char[100]).replace('\0', '*');
        System.out.println(separator);
        for (int i = 0; i < customers.size(); i++) {
            Customer c = customers.get(i);
            System.out.printf(
                    "\nProspect %d: %s wants to borrow %.2f € for a period of %d years and pay %.2f € each month\n\n",
                    i+1, c.getName(), c.getAmount(), c.getYears(), c.getCostPerMonth());
            System.out.println(separator);
        }
    }
}
