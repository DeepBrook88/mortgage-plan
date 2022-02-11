package ax.home.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ManualParser {
    private final String path;

    public ManualParser(String path) {
        this.path = path;
    }

    public List<Customer> parseFile(boolean hasHeader) {
        List<Customer> list = new ArrayList<>();
        try (InputStream configStream = Parser.class.getResourceAsStream(path)) {
            BufferedReader configReader = new BufferedReader(new InputStreamReader(configStream, StandardCharsets.UTF_8));
            Scanner sc = new Scanner(configReader);
            if(hasHeader) sc.nextLine();
            while (sc.hasNextLine()) {
                String string = sc.nextLine();
                String[] tokens = string.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                Customer c = new Customer();
                try {
                    c.setName(tokens[0]);
                    c.setAmount(Double.parseDouble(tokens[1]));
                    c.setInterest(Double.parseDouble(tokens[2]));
                    c.setYears(Integer.parseInt(tokens[3]));
                    list.add(c);
                } catch (Exception ignored) {}
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
