package model;

public class CustomerScore {
    private long cost;
    private long customerReward;

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public long getCustomerReward() {
        return customerReward;
    }

    public void setCustomerReward(long customerReward) {
        this.customerReward = customerReward;
    }

    public void setScore(long cost) {
        this.cost = cost;
    }

    public CustomerScore(long cost, long customerReward) {
        this.cost = cost;
        this.customerReward = customerReward;
    }

    public long getScore() {
        return cost;
    }
}
