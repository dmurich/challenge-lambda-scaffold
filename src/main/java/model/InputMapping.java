package model;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class InputMapping {

	private static final Logger LOGGER = Logger.getLogger(InputMapping.class.getName());

	public static class Customer {
		public int x;
		public int y;
		public int reward;

		Customer(int x, int y, int reward) {
			this.x = x;
			this.y = y;
			this.reward = reward;
		}
	}

	public static class MapSize {
		public int width;
		public int height;
		public int customerNumber;
		public int maximalDistanceFromService;
		public int numberOfServices;

		public MapSize(int width, int height, int customerNumber, int maximalDistanceFromService, int numberOfServices) {
			this.width = width;
			this.height = height;
			this.customerNumber = customerNumber;
			this.maximalDistanceFromService = maximalDistanceFromService;
			this.numberOfServices = numberOfServices;
		}
	}

	public static class BuildingCost{
		public int u;
		public int v;
		public int w;

		public BuildingCost(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}

	public static class Service {
		public int x;
		public int y;
		public int utilityValue;

		public Service(int x, int y, int utilityValue) {
			this.x = x;
			this.y = y;
			this.utilityValue = utilityValue;
		}
	}

	private List<Customer> customers;

	public InputMapping(List<Customer> customers) {
		this.customers = customers;
	}

	public static InputMapping read(Reader reader) {
		Scanner sc = new Scanner(reader);

		// First line: array lenght
		int length = Integer.parseInt(sc.nextLine());

		List<Pair> pairs = new ArrayList<Pair>(length);

		// Next N lines: pairs
		for(int i = 0; i < length; i++)
		{
			String[] pair = sc.nextLine().split(" ");

			int op1 = Integer.parseInt(pair[0]);
			int op2 = Integer.parseInt(pair[1]);

			pairs.add(new Pair(op1, op2));
		}

		sc.close();

		return new InputMapping(pairs);
	}

	public List<Pair> getPairs() {
		return pairs;
	}
}
