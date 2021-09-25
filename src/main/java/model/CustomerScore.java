package model;

public class CustomerScore {
    private long cost;
    private long customerReward;
    private long serviceUtils;
    private Point replyOffice;
    private Point customerLocation;

    public CustomerScore(long cost, long customerReward, long serviceUtils, Point replyOffice, Point customerLocation) {
        this.cost = cost;
        this.customerReward = customerReward;
        this.serviceUtils = serviceUtils;
        this.replyOffice = replyOffice;
        this.customerLocation = customerLocation;
    }

    public Point getCustomerLocation() {
        return customerLocation;
    }

    public void setCustomerLocation(Point customerLocation) {
        this.customerLocation = customerLocation;
    }

    public Point getReplyOffice() {
        return replyOffice;
    }

    public void setReplyOffice(Point replyOffice) {
        this.replyOffice = replyOffice;
    }

    public long getServiceUtils() {
        return serviceUtils;
    }

    public void setServiceUtils(long serviceUtils) {
        this.serviceUtils = serviceUtils;
    }

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


    public long getScore() {
        return cost;
    }
}
