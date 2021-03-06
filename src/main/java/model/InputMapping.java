package model;

import exceptions.ValidationException;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class InputMapping {
	private static final Logger LOGGER = Logger.getLogger(InputMapping.class.getName());
	private static final String LogPrefix = "InputManager | ";
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

	public Terrain[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(Terrain[][] matrix) {
		this.matrix = matrix;
	}

	public static class Customer {
		public long x;
		public long y;
		public int reward;

		@Override
		public String toString() {
			return "Customer{" +
					"x=" + x +
					", y=" + y +
					", reward=" + reward +
					'}';
		}

		Customer(long x, long y, int reward) {
			this.x = x;
			this.y = y;
			this.reward = reward;
		}
	}

	public static class MapSize {
		public int width;
		public int height;
		public int customersNumber;
		public long maximalDistanceFromService;
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
	private Terrain[][] matrix;



	public InputMapping(Terrain[][] matrix, List<Customer> customers, MapSize mapSize, List<Service> services, BuildingCost buildingCost) {
		this.customers = customers;
		this.mapSize = mapSize;
		this.services = services;
		this.buildingCost = buildingCost;
		this.matrix = matrix;
	}

	public static InputMapping read(Reader reader) throws ValidationException{
		Scanner sc = new Scanner(reader);

		LOGGER.info(LogPrefix+"parsing map info:");
		MapSize mapSize = getMapInfo(sc);
		LOGGER.info(LogPrefix+ mapSize.toString());

		LOGGER.info(LogPrefix+"parsing building costs:");
		BuildingCost buildingCost = getBuildingCost(sc);
		LOGGER.info(LogPrefix+ buildingCost.toString());


		LOGGER.info(LogPrefix+"parsing customer offices:");
		List<Customer> customerOffices = getCustomerOffices(sc, mapSize.customersNumber);

		LOGGER.info(LogPrefix+"parsing services:");
		List<Service> services = getServices(sc, mapSize.servicesNumber);
		System.out.println(services);

		LOGGER.info(LogPrefix+ "Evaluating map:");
		Terrain[][] matrix = getMatrixFromInput(sc, mapSize.width, mapSize.height);

		sc.close();
		return new InputMapping(matrix,customerOffices, mapSize, services, buildingCost);
	}

	private static Terrain[][] getMatrixFromInput(Scanner sc, int width, int height) throws ValidationException {
		try {
			Terrain[][] matrix = new Terrain[height][width];
			int heightCounter = 0;
			while (sc.hasNextLine()) {

				String line = sc.nextLine();
				char[] chars = line.toCharArray();
				int widthCounter = 0;
				LOGGER.info(LogPrefix+"Scanning line number "+ String.valueOf(heightCounter));
				for (char ch: chars) {
					matrix[heightCounter][widthCounter] = Terrain.valueOf(ch);
					widthCounter = widthCounter + 1;
				}
				heightCounter = heightCounter + 1;
			}
			return matrix;

		} catch (Exception e) {
			throw new ValidationException(ValidationException.Category.GENERIC, e.getMessage());
		}
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
				LOGGER.info(LogPrefix+"new service: "+ service.toString());
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
				LOGGER.info(LogPrefix+"new customer: "+ customer.toString());
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

}
