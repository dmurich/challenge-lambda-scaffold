package model;

import exceptions.ValidationException;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class OutputMapping {

    private static final Logger LOGGER = Logger.getLogger(InputMapping.class.getName());


    public static class OutStruct {
        public int positionX;
        public int positionY;
        public String steps;

        @Override
        public String toString() {
            return "MapSize{" +
                    "positionX=" + positionX +
                    ", positionY=" + positionY +
                    ", steps=" + steps +
                    '}';
        }

        public OutStruct(int positionX, int positionY, String steps) {
            this.positionX = positionX;
            this.positionY = positionY;
            this.steps = steps;
        }
    }

    private static List<OutStruct> output = new ArrayList<>();

    public static void read(Reader reader) throws ValidationException {
        Scanner sc = new Scanner(reader);

        OutStruct outputLine;
        while (sc.hasNextLine()) {
            outputLine = getOutput(sc);
            System.out.println(outputLine);
            output.add(outputLine);
        }
    }
    private static OutStruct getOutput(Scanner sc) throws ValidationException {
        try {
            String [] lines = sc.nextLine().split(" ");

            return new OutStruct(Integer.parseInt(lines[0]),
                    Integer.parseInt(lines[1]),
                    lines[2]);

        } catch (Exception e) {
            throw new ValidationException(ValidationException.Category.GENERIC, e.getMessage());
        }


    }
}
