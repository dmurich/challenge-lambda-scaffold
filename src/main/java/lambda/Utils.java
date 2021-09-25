package lambda;

import exceptions.ValidationException;
import model.InputMapping;
import model.Point;

import java.util.List;

public class Utils {
    public static int getCustomerReward(List<InputMapping.Customer> customers, Point point) throws ValidationException {
        for (InputMapping.Customer customer : customers) {
            if (customer.x == point.getX() && customer.y == point.getY()) {
                return customer.reward;
            }
        }
        throw new ValidationException(ValidationException.Category.GENERIC, "NO CUSTOMER FOUND IN THIS POSITION");
    }

    public static int getDistance(Point p1, Point p2, long maximalDistance) throws ValidationException {
        int v1 = Math.abs(p1.getX()-p2.getX());
        int v2 = Math.abs(p1.getY()-p2.getY());
        int distance = Math.max(v1,v2);
//        if (distance < maximalDistance) {
//            throw new ValidationException(ValidationException.Category.CONSTRAINTS, "Distance too long");
//        }
        return distance;
    }

    public static int getServiceUtils(List<InputMapping.Service> services, Point point) throws ValidationException {
        for (InputMapping.Service service : services) {
            if (service.x == point.getX() && service.y == point.getY()) {
                return service.utilityValue;
            }
        }
        return 0;
//        throw  new ValidationException(ValidationException.Category.GENERIC,"NO SERVICES FOUND IN THIS POSITION");
    }

    public static int getTotalBuildingCosts(InputMapping inputMapping, int numberOfoffices) {
        int totalU = inputMapping.getBuildingCost().u * numberOfoffices * numberOfoffices;
        int totalV = inputMapping.getBuildingCost().v * numberOfoffices;
        return totalU + totalV + inputMapping.getBuildingCost().w;
    }

}
