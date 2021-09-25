package model;

public class CustomerScore {
    private int cost;
    private int customerReward;

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCustomerReward() {
        return customerReward;
    }

    public void setCustomerReward(int customerReward) {
        this.customerReward = customerReward;
    }

    public void setScore(int cost) {
        this.cost = cost;
    }

    public CustomerScore(int cost, int customerReward) {
        this.cost = cost;
        this.customerReward = customerReward;
    }

    public int getScore() {
        return cost;
    }
}
