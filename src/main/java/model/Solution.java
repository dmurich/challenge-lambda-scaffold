package model;

import java.io.Reader;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.NoSuchElementException;
import java.lang.NumberFormatException;

import exceptions.ValidationException;

public class Solution {

    private static final Logger LOGGER = Logger.getLogger(Solution.class.getName());

    private EnvironmentCollector environmentCollector;

    public Solution(EnvironmentCollector environmentCollector) {
        this.environmentCollector = environmentCollector;
    }

    public static Solution read(InputMapping inputMapping, Reader reader) throws ValidationException {
        Scanner sc = new Scanner(reader);
        try {
            EnvironmentCollector environmentCollector = OutputMapping.read(inputMapping,reader);
            sc.close();
            return new Solution(environmentCollector);
        } catch(NoSuchElementException | NumberFormatException e) {
            throw new ValidationException(ValidationException.Category.SOLUTION_FORMAT, e.getMessage());
        }
    }

    public EnvironmentCollector getEnvironmentCollector() {
        return environmentCollector;
    }

    public void setTotalCustomerInfo(EnvironmentCollector environmentCollector) {
        this.environmentCollector = environmentCollector;
    }
}
