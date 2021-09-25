package model;

import java.util.List;

public class TotalCustomerInfo {
    private List<CustomerScore> customerScores;
    private int numberOfOffices;
    private int numberOfCustomers;

    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }

    public void setNumberOfCustomers(int numberOfCustomers) {
        this.numberOfCustomers = numberOfCustomers;
    }

    public TotalCustomerInfo(List<CustomerScore> customerScores, int numberOfOffices, int numberOfCustomers) {
        this.customerScores = customerScores;
        this.numberOfOffices = numberOfOffices;
        this.numberOfCustomers = numberOfCustomers;
    }

    public List<CustomerScore> getCustomerScores() {
        return customerScores;
    }

    public void setCustomerScores(List<CustomerScore> customerScores) {
        this.customerScores = customerScores;
    }

    public int getNumberOfOffices() {
        return numberOfOffices;
    }

    public void setNumberOfOffices(int numberOfOffices) {
        this.numberOfOffices = numberOfOffices;
    }
}
