package model;

import java.io.Reader;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.NoSuchElementException;
import java.lang.NumberFormatException;

import exceptions.ValidationException;

public class Solution {

    private static final Logger LOGGER = Logger.getLogger(Solution.class.getName());

    private List<Long> values;

    public Solution(List<Long> values) {
        this.values = values;
    }

    public static Solution read(int length, Reader reader) throws ValidationException {
        Scanner sc = new Scanner(reader);

        List<Long> values = new ArrayList<Long>(length);

        try {
            for(int i = 0; i < length; i++) {
                String valueString = sc.nextLine();
                long value = Long.parseLong(valueString);

                if (value < 0) {
                    throw new ValidationException(ValidationException.Category.CONSTRAINTS, "Negative numbers not permitted");
                }

                values.add(value);

            }
        } catch(NoSuchElementException | NumberFormatException e) {
            throw new ValidationException(ValidationException.Category.SOLUTION_FORMAT, e.getMessage());
        }

        sc.close();

        return new Solution(values);
    }

    public List<Long> getValues() {
        return values;
    }
}
