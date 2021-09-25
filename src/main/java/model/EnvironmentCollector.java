package model;

import java.util.List;
import java.util.Set;

public class EnvironmentCollector {
    private List<CustomerScore> customerScores;
    private Set<Point> replyOffices;
    private Set<Point> customerLocation;

    public List<CustomerScore> getCustomerScores() {
        return customerScores;
    }

    public void setCustomerScores(List<CustomerScore> customerScores) {
        this.customerScores = customerScores;
    }

    public Set<Point> getReplyOffices() {
        return replyOffices;
    }

    public void setReplyOffices(Set<Point> replyOffices) {
        this.replyOffices = replyOffices;
    }

    public Set<Point> getCustomerLocation() {
        return customerLocation;
    }

    public void setCustomerLocation(Set<Point> customerLocation) {
        this.customerLocation = customerLocation;
    }

    public EnvironmentCollector(List<CustomerScore> customerScores, Set<Point> replyOffices, Set<Point> customerLocation) {
        this.customerScores = customerScores;
        this.replyOffices = replyOffices;
        this.customerLocation = customerLocation;
    }
}
