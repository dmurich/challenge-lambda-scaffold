package model;

import exceptions.ValidationException;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class InputMapping {
	private static final Logger LOGGER = Logger.getLogger(InputMapping.class.getName());

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public MapSize getMapSize() {
		return mapSize;
	}

	public void setMapSize(MapSize mapSize) {
		this.mapSize = mapSize;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public BuildingCost getBuildingCost() {
		return buildingCost;
	}

	public void setBuildingCost(BuildingCost buildingCost) {
		this.buildingCost = buildingCost;
	}

	public static class Customer {
		public int x;
		public int y;
		public int reward;

		@Override
		public String toString() {
			return "Customer{" +
					"x=" + x +
					", y=" + y +
					", reward=" + reward +
					'}';
		}

		Customer(int x, int y, int reward) {
			this.x = x;
			this.y = y;
			this.reward = reward;
		}
	}

	public static class MapSize {
		public int width;
		public int height;
		public int customersNumber;
		public int maximalDistanceFromService;
		public int servicesNumber;

		@Override
		public String toString() {
			return "MapSize{" +
					"width=" + width +
					", height=" + height +
					", customerNumber=" + customersNumber +
					", maximalDistanceFromService=" + maximalDistanceFromService +
					", numberOfServices=" + servicesNumber +
					'}';
		}

		public MapSize(int width, int height, int customerNumber, int maximalDistanceFromService, int numberOfServices) {
			this.width = width;
			this.height = height;
			this.customersNumber = customerNumber;
			this.maximalDistanceFromService = maximalDistanceFromService;
			this.servicesNumber = numberOfServices;
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
		@Override
		public String toString() {
			return "Service{" +
					"x=" + x +
					", y=" + y +
					", utilityValue=" + utilityValue +
					'}';
		}

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
	private List<Service> services;
	private BuildingCost buildingCost;

	public InputMapping(List<Customer> customers, MapSize mapSize, List<Service> services, BuildingCost buildingCost) {
		this.customers = customers;
		this.mapSize = mapSize;
		this.services = services;
		this.buildingCost = buildingCost;
	}

	public static InputMapping read(Reader reader) throws ValidationException{
		Scanner sc = new Scanner(reader);

		System.out.println("parsing map info:");
		MapSize mapSize = getMapInfo(sc);
		System.out.println(mapSize.toString());

		System.out.println("parsing building costs:");
		BuildingCost buildingCost = getBuildingCost(sc);
		System.out.println(buildingCost.toString());


		System.out.println("parsing customer offices:");
		List<Customer> customerOffices = getCustomerOffices(sc, mapSize.customersNumber);

		System.out.println("parsing services:");
		List<Service> services = getServices(sc, mapSize.servicesNumber);


		sc.close();
		return new InputMapping(customerOffices, mapSize, services, buildingCost);
	}

	private static List<Service> getServices(Scanner sc, int servicesNumber) throws ValidationException {
		List<Service> services = new ArrayList<Service>(servicesNumber);

		try {


			for (int i = 0; i < servicesNumber; i++) {
				String [] servicesInfo = sc.nextLine().split(" ");

				if (servicesInfo.length != 3) {
					throw  new ValidationException(ValidationException.Category.GENERIC, "customer info dimension not equal to 3 at row number: " +String.valueOf(i) );
				}
				Service service = new Service(Integer.parseInt(servicesInfo[0]),
						Integer.parseInt(servicesInfo[1]),
						Integer.parseInt(servicesInfo[2]));
				System.out.println("new service: "+ service.toString());
				services.add(service);

			}
			return services;

		} catch (Exception e) {
			throw new ValidationException(ValidationException.Category.GENERIC, e.getMessage());
		}
	}

	private static List<Customer> getCustomerOffices(Scanner sc, int customerNumber) throws ValidationException {
		List<Customer> customerOffices = new ArrayList<Customer>(customerNumber);

		try {


			for (int i = 0; i < customerNumber; i++) {
				String [] customerInfos = sc.nextLine().split(" ");

				if (customerInfos.length != 3) {
					throw  new ValidationException(ValidationException.Category.GENERIC, "customer info dimension not equal to 3 at row number: " +String.valueOf(i) );
				}
				Customer customer = new Customer(Integer.parseInt(customerInfos[0]),
						Integer.parseInt(customerInfos[1]),
						Integer.parseInt(customerInfos[2]));
				System.out.println("new customer: "+ customer.toString());
				customerOffices.add(customer);


			}
			return customerOffices;

		} catch (Exception e) {
			throw new ValidationException(ValidationException.Category.GENERIC, e.getMessage());
		}
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
