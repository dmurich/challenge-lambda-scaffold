package model;

import java.util.List;

public class TotalCustomerInfo {
    private List<CustomerScore> customerScores;
    private int numberOfOffices;
    private int customers;

    public TotalCustomerInfo(List<CustomerScore> customerScores, int numberOfOffices, int customers) {
        this.customerScores = customerScores;
        this.numberOfOffices = numberOfOffices;
        this.customers = customers;
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
