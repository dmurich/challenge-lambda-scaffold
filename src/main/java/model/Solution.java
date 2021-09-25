package model;

import java.io.Reader;
import java.util.Scanner;
import java.util.List;
import java.util.logging.Logger;
import java.util.NoSuchElementException;
import java.lang.NumberFormatException;

import exceptions.ValidationException;

public class Solution {

    private static final Logger LOGGER = Logger.getLogger(Solution.class.getName());

    private List<CustomerScore> scores;
    private int numberOfoffices;

    public int getNumberOfoffices() {
        return numberOfoffices;
    }

    public Solution(List<CustomerScore> scores, int numberOfoffices) {
        this.scores = scores;
        this.numberOfoffices = numberOfoffices;
    }

    public List<CustomerScore> getScores() {
        return scores;
    }


    public static Solution read(InputMapping inputMapping, Reader reader) throws ValidationException {
        Scanner sc = new Scanner(reader);
        try {
            List<CustomerScore> scores = OutputMapping.read(inputMapping,reader);
            sc.close();
            return new Solution(scores,1);
        } catch(NoSuchElementException | NumberFormatException e) {
            throw new ValidationException(ValidationException.Category.SOLUTION_FORMAT, e.getMessage());
        }
    }

}
