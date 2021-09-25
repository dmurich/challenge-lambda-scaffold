package lambda;

import exceptions.ValidationException;
import model.InputMapping;
import model.Point;

import java.util.List;

public class Utils {
    public static int getCustomerReward(List<InputMapping.Customer> customers, int x, int y) throws ValidationException {
        for (InputMapping.Customer customer : customers) {
            if (customer.x == x && customer.y == y) {
                return customer.reward;
            }
        }
        throw new ValidationException(ValidationException.Category.GENERIC, "NO CUSTOMER FOUND IN THIS POSITION");
    }

    public static int getDistance(Point p1, Point p2) {
        int v1 = Math.abs(p1.getX()-p2.getX());
        int v2 = Math.abs(p1.getY()-p2.getY());
        return Math.max(v1,v2);
    }

}
