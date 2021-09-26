package model;

import exceptions.ValidationException;
import lambda.Utils;

import java.io.Reader;
import java.util.*;
import java.util.logging.Logger;

import static exceptions.ValidationException.Category.CONSTRAINTS;
import static exceptions.ValidationException.Category.GENERIC;

public class OutputMapping {

    private static final Logger LOGGER = Logger.getLogger(InputMapping.class.getName());


    public static class OutStruct {
        public int positionX;
        public int positionY;
        public String path;

        @Override
        public String toString() {
            return "MapSize{" +
                    "positionX=" + positionX +
                    ", positionY=" + positionY +
                    ", path=" + path +
                    '}';
        }

        public OutStruct(int positionX, int positionY, String path) {
            this.positionX = positionX;
            this.positionY = positionY;
            this.path = path;
        }
    }

    private static List<CustomerScore> customerCosts = new ArrayList<>();
    private static Set<Point> offices = new HashSet<>();
    private static Set<Point> customers = new HashSet<>();

    public static EnvironmentCollector read(InputMapping inputMapping, Reader reader) throws ValidationException {

        Scanner sc = new Scanner(reader);

        OutStruct outputLine;
        while (sc.hasNextLine()) {
            outputLine = getOutput(sc);
            System.out.println(outputLine);
            CustomerScore customerScore = travelThePath(outputLine.path, inputMapping, outputLine.positionX, outputLine.positionY);
            customerCosts.add(customerScore);
            offices.add(new Point(outputLine.positionX, outputLine.positionY));
        }
        return new EnvironmentCollector(customerCosts, offices, customers);
    }

//    TODO retrieve the cost and the customer we reached
    public static CustomerScore travelThePath(String path, InputMapping inputMapping, int x, int y) throws ValidationException {
        Point replyOffice = new Point(x,y);
        checkMountain(x, y, inputMapping.getMatrix());
        long cost = 0;
        long serviceUtils = 0;
        for (int i = 0; i < path.length(); i++) {
            switch (path.charAt(i)) {
                case 'U':
                    y--;
                    break;
                case 'D':
                    y++;
                    break;
                case 'L':
                    x--;
                    break;
                case 'R':
                    x++;
                    break;
                default:
                    throw new ValidationException(GENERIC, "The wrong symbol in the path. It should be 'R', 'L', 'U' or 'D'");
            }
            serviceUtils = serviceUtils + Utils.getServiceUtils(inputMapping.getServices(), new Point(x,y));

            checkMountain(x, y, inputMapping.getMatrix());
            cost += inputMapping.getMatrix()[y][x].value;
        }
        Point customerPoint = new Point (x,y);
        checkIdentity(replyOffice,customerPoint);
        customers.add(customerPoint);
        return new CustomerScore(cost,
                Utils.getCustomerReward(inputMapping.getCustomers(),customerPoint),
                serviceUtils,
                replyOffice,
                customerPoint);
    }

    private static void checkIdentity(Point replyOffice, Point customerPoint) throws ValidationException {
        if (replyOffice.getY() == customerPoint.getY() && replyOffice.getX() == customerPoint.getX()){
            throw new ValidationException(GENERIC, "the starting point is the same of the office");
        }
    }

    public static void checkMountain(int x, int y, Terrain[][] map) throws ValidationException {
        if (map[y][x] == Terrain.Mountains) {
            throw new ValidationException(CONSTRAINTS, "Faced a mountain during the way");
        }
    }
        private static OutStruct getOutput(Scanner sc) throws ValidationException {
        try {
            String [] lines = sc.nextLine().split(" ");

            return new OutStruct(Integer.parseInt(lines[0]),
                    Integer.parseInt(lines[1]),
                    lines[2]);

        } catch (Exception e) {
            throw new ValidationException(GENERIC, e.getMessage());
        }


    }
}
