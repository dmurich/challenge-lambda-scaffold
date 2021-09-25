package model;

import exceptions.ValidationException;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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

    private static List<Score> scores = new ArrayList<>();
//TODO retrieve also the numberofOffices and refactor actual score into cost
    public static List<Score> read(InputMapping inputMapping, Reader reader) throws ValidationException {
        Scanner sc = new Scanner(reader);

        OutStruct outputLine;
        while (sc.hasNextLine()) {
            outputLine = getOutput(sc);
            System.out.println(outputLine);
            Score score = travelThePath(outputLine.path, inputMapping.getMatrix(), outputLine.positionX, outputLine.positionY);
            scores.add(score);
        }
        return scores;
    }

//    TODO retrieve the cost and the customer we reached
    public static Score travelThePath(String path, Terrain[][] map, int x, int y) throws ValidationException {

        checkMountain(x, y, map);
        int score = 0;
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
            checkMountain(x, y, map);
            score += map[y][x].value;
        }
        return new Score(score);
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
