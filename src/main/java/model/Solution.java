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

    private TotalCustomerInfo totalCustomerInfo;

    public Solution(TotalCustomerInfo totalCustomerInfo) {
        this.totalCustomerInfo = totalCustomerInfo;
    }

    public static Solution read(InputMapping inputMapping, Reader reader) throws ValidationException {
        Scanner sc = new Scanner(reader);
        try {
            TotalCustomerInfo totalCustomerInfo = OutputMapping.read(inputMapping,reader);
            sc.close();
            return new Solution(totalCustomerInfo);
        } catch(NoSuchElementException | NumberFormatException e) {
            throw new ValidationException(ValidationException.Category.SOLUTION_FORMAT, e.getMessage());
        }
    }

    public TotalCustomerInfo getTotalCustomerInfo() {
        return totalCustomerInfo;
    }

    public void setTotalCustomerInfo(TotalCustomerInfo totalCustomerInfo) {
        this.totalCustomerInfo = totalCustomerInfo;
    }
}
