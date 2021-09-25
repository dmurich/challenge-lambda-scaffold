package lambda;

import exceptions.ValidationException;
import model.CustomerScore;
import model.EnvironmentCollector;
import model.InputMapping;
import model.Point;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Utils {
    public static int getCustomerReward(List<InputMapping.Customer> customers, Point point) throws ValidationException {
        for (InputMapping.Customer customer : customers) {
            if (customer.x == point.getX() && customer.y == point.getY()) {
                return customer.reward;
            }
        }
        throw new ValidationException(ValidationException.Category.GENERIC, "NO CUSTOMER FOUND IN THIS POSITION");
    }

    public static int getDistance(Point p1, Point p2) {

        int v1 = Math.abs(p1.getX()-p2.getX());
        int v2 = Math.abs(p1.getY()-p2.getY());
        int distance = Math.max(v1,v2);


        return distance;
    }

    public static int getServiceUtils(List<InputMapping.Service> services, Point point) throws ValidationException {
        for (InputMapping.Service service : services) {
            if (service.x == point.getX() && service.y == point.getY()) {

                return service.utilityValue;
            }
        }
        return 0;
    }

    public static int getTotalBuildingCosts(InputMapping inputMapping, int numberOfoffices) {
        int totalU = inputMapping.getBuildingCost().u * numberOfoffices * numberOfoffices;
        int totalV = inputMapping.getBuildingCost().v * numberOfoffices;
        return totalU + totalV + inputMapping.getBuildingCost().w;
    }

    public static long getTotalPathCosts(EnvironmentCollector environmentCollector) {
        long totalCosts = 0;

        for (CustomerScore customerScore : environmentCollector.getCustomerScores()) {
            long localCost = customerScore.getCustomerReward() - customerScore.getCost();
            environmentCollector.getReplyOffices().add(customerScore.getReplyOffice());
            environmentCollector.getCustomerLocation().add(customerScore.getCustomerLocation());
            totalCosts = totalCosts + localCost;
        }
        return totalCosts;
    }

    public static int getServicesScore(EnvironmentCollector environmentCollector, InputMapping inputMapping) {
        int servicesScore = 0;
        Iterator<Point> it = environmentCollector.getReplyOffices().iterator();
        while(it.hasNext()){
            Point replyOffice = it.next();

            for (InputMapping.Service service : inputMapping.getServices()) {
                System.out.println(replyOffice);
                System.out.println(service);
                System.out.println("|"+replyOffice.getX()+"-"+service.x+"|,|"+replyOffice.getY()+"-"+service.y+"|");
                System.out.println("distance evaluation: "+ String.valueOf( Utils.getDistance(replyOffice, new Point(service.x, service.y))));
                System.out.println("service Utility: "+ service.utilityValue);
                int distance = Utils.getDistance(replyOffice, new Point(service.x, service.y));
                int localServiceScore = service.utilityValue - distance;
                if (distance > inputMapping.getMapSize().maximalDistanceFromService) {
                    localServiceScore = 0;
                }
                System.out.println("local service score: "+ localServiceScore);
                servicesScore = servicesScore + localServiceScore;

            }
        }
        return servicesScore;
    }

    public static long evaluateTheBonus(Set<Point> customerLocation, InputMapping inputMapping) throws ValidationException {
            long bonus = 0;
            if (customerLocation.size() == inputMapping.getMapSize().customersNumber) {
                Iterator<Point> it = customerLocation.iterator();
                while(it.hasNext()) {
                    Point customer = it.next();
                    bonus = bonus + Utils.getCustomerReward(inputMapping.getCustomers(),customer);
                }
            }
            return bonus;
    }
}
