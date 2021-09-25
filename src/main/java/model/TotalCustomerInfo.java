package model;

import java.util.List;

public class TotalCustomerInfo {
    private List<CustomerScore> customerScores;
    private int numberOfOffices;

    public TotalCustomerInfo(List<CustomerScore> customerScores, int numberOfOffices) {
        this.customerScores = customerScores;
        this.numberOfOffices = numberOfOffices;
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
