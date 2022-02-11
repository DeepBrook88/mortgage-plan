package ax.home.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer {
    @JsonProperty("Customer")
    private String name;
    @JsonProperty("Total loan")
    private double amount;
    @JsonProperty("Years")
    private int years;
    @JsonProperty("Interest")
    private double interest;

    public Customer() {
    }

    public Customer(String name, double amount, int years, double interest) {
        this.name = name;
        this.amount = amount;
        this.years = years;
        this.interest = interest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getCostPerMonth() {
        int p = years * 12;
        double b = (interest/100) / 12;
        return amount * ((b * power(1+b, p)) / (power(1 + b, p) - 1));
    }

    private double power(double val, double pow) {
        double sum = 1;
        if(pow!=0){
            for (int i =1;i<=pow;i++){
                sum *= val;
            }
        }
        return sum;
    }
}
