package model;

import lambda.SolutionChecker;
import lambda.Utils;
import org.apache.commons.io.input.BOMInputStream;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class UtilsTestCases {

	@Test
	public void getCustomerReward() throws Exception {
		List<InputMapping.Customer> customers = new ArrayList<>();
		InputMapping.Customer customer1 = new InputMapping.Customer(1,2,10);
		InputMapping.Customer customer2 = new InputMapping.Customer(3,2,10);
		InputMapping.Customer customer3 = new InputMapping.Customer(7,2,10);
		customers.add(customer1);
		customers.add(customer2);
		customers.add(customer3);

		System.out.println(Utils.getCustomerReward(customers,1,2));
//		System.out.println(Utils.getCustomerReward(customers,1,4));
		System.out.println(Utils.getCustomerReward(customers,7,2));

	}

	@Test
	public void getDistance() {
		Point p1 = new Point(2,3);
		Point p2 = new Point(7,9);
		System.out.println(Utils.getDistance(p1,p2));

	}
}
