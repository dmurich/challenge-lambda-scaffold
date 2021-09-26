package model;

import java.util.Objects;

public class CustomerVSReply {
    private Point customerPoint;
    private Point replyPoint;
    private String path;

    public CustomerVSReply(Point customerPoint, Point replyPoint, String path) {
        this.customerPoint = customerPoint;
        this.replyPoint = replyPoint;
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerVSReply that = (CustomerVSReply) o;
        return Objects.equals(customerPoint, that.customerPoint) && Objects.equals(replyPoint, that.replyPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerPoint, replyPoint);
    }

    public Point getCustomerPoint() {
        return customerPoint;
    }

    public void setCustomerPoint(Point customerPoint) {
        this.customerPoint = customerPoint;
    }

    public Point getReplyPoint() {
        return replyPoint;
    }

    public void setReplyPoint(Point replyPoint) {
        this.replyPoint = replyPoint;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
