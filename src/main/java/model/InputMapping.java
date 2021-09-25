package model;

import exceptions.ValidationException;

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

		@Override
		public String toString() {
			return "MapSize{" +
					"width=" + width +
					", height=" + height +
					", customerNumber=" + customerNumber +
					", maximalDistanceFromService=" + maximalDistanceFromService +
					", numberOfServices=" + numberOfServices +
					'}';
		}

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

		@Override
		public String toString() {
			return "BuildingCost{" +
					"u=" + u +
					", v=" + v +
					", w=" + w +
					'}';
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
	private MapSize mapSize;

	public InputMapping(MapSize mapSize) {
		this.mapSize = mapSize;
	}

	public static void read(Reader reader) throws ValidationException {
//		public static InputMapping read(Reader reader) {
		Scanner sc = new Scanner(reader);

		MapSize mapSize = getMapInfo(sc);
		System.out.println(mapSize);

		BuildingCost buildingCost = getBuildingCost(sc);
		System.out.println(buildingCost);

//		List<Customer> customerOffices = getCustomerOffices(sc, mapSize.customerNumber);


//		List<MapSize> mapSize = new ArrayList<MapSize>(length);
//		List<Pair> pairs = new ArrayList<Pair>(length);


//		for (int i = 0; i < 5 ; i++) {
//
//		}

//		// Next N lines: pairs
//		for(int i = 0; i < length; i++)
//		{
//			String[] pair = sc.nextLine().split(" ");
//
//			int op1 = Integer.parseInt(pair[0]);
//			int op2 = Integer.parseInt(pair[1]);
//
//			pairs.add(new Pair(op1, op2));
//		}

		sc.close();

//		return new InputMapping(mapSize);
	}

//	private static List<Customer> getCustomerOffices(Scanner sc, int customerNumber) {
		private static void getCustomerOffices(Scanner sc, int customerNumber) {

//		try {
//
//			for (int i = 0; i < customerNumber; i++) {
//				String [] customerInfos = sc.nextLine().split(" ");
//
//			}
//			if (buildingInfos.length != 3) {
//				throw  new ValidationException(ValidationException.Category.GENERIC, "building cost not equal to 3");
//			}
//			return new BuildingCost(Integer.parseInt(buildingInfos[0]),
//					Integer.parseInt(buildingInfos[1]),
//					Integer.parseInt(buildingInfos[2]));
//
//		} catch (Exception e) {
//			throw new ValidationException(ValidationException.Category.GENERIC, e.getMessage());
//		}
	}

	private static BuildingCost getBuildingCost(Scanner sc) throws ValidationException {
		try {
			String [] buildingInfos = sc.nextLine().split(" ");

			if (buildingInfos.length != 3) {
				throw  new ValidationException(ValidationException.Category.GENERIC, "building cost not equal to 3");
			}
			return new BuildingCost(Integer.parseInt(buildingInfos[0]),
					Integer.parseInt(buildingInfos[1]),
					Integer.parseInt(buildingInfos[2]));

		} catch (Exception e) {
			throw new ValidationException(ValidationException.Category.GENERIC, e.getMessage());
		}
	}

	private static MapSize getMapInfo(Scanner sc) throws ValidationException {
		try {
			String [] mapInfos = sc.nextLine().split(" ");

			if (mapInfos.length != 5) {
				throw  new ValidationException(ValidationException.Category.GENERIC, "map size not equal to 5");
			}
			return new MapSize(Integer.parseInt(mapInfos[0]),
					Integer.parseInt(mapInfos[1]),
					Integer.parseInt(mapInfos[2]),
					Integer.parseInt(mapInfos[3]),
					Integer.parseInt(mapInfos[4]));

		} catch (Exception e) {
			throw new ValidationException(ValidationException.Category.GENERIC, e.getMessage());
		}


	}

//	public List<Pair> getPairs() {
//		return pairs;
//	}
}
