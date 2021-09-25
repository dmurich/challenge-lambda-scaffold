package lambda;

import java.io.Reader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

import exceptions.ValidationException;
import model.*;

/*
 * This class implements validation and scoring procedures for an example problem.
 * The input file is an ASCII file. The first line contains the number of elements (N),
 * and each of the following N lines contains two space-separated integers.
 * There is only one constraint: all the integers are positive numbers.
 * The solution file should be an ASCII file of N lines. Each line contains the
 * result of the multiplication of the two corresponding integers in the input
 * file. The score is the total sum of these lines.
 */
public class SolutionChecker {

	private static final Logger LOGGER = Logger.getLogger(SolutionChecker.class.getName());

	public static void validate(InputMapping inputMapping, Reader solutionReader) throws ValidationException {
		LOGGER.info("Validation: Begin");

		// Parse the solution file
		LOGGER.info("Validation: Read solution");
		checkConstraints(inputMapping);
//		Solution solution = Solution.read(pairContainer.getPairs().size(), solutionReader);

		LOGGER.info("Validation: End");
	}

	private static void checkConstraints(InputMapping inputMapping) throws ValidationException {
		if (inputMapping.getCustomers().size() > 500 || inputMapping.getCustomers().size() < 0 ) {
			throw  new ValidationException(ValidationException.Category.CONSTRAINTS, " Customer number not regular");
		}
		if (inputMapping.getMapSize().width > 2000 || inputMapping.getMapSize().width < 0 ) {
			throw  new ValidationException(ValidationException.Category.CONSTRAINTS, " width of the matrix not regular");
		}
		if (inputMapping.getMapSize().height > 2000 || inputMapping.getMapSize().height < 0 ) {
			throw  new ValidationException(ValidationException.Category.CONSTRAINTS, " height of the matrix not regular");
		}
		if (inputMapping.getMapSize().maximalDistanceFromService > 2000 || inputMapping.getMapSize().maximalDistanceFromService < 0 ) {
			throw  new ValidationException(ValidationException.Category.CONSTRAINTS, " distance to service not regular");
		}
	}

	public static long score(InputMapping inputMapping, Reader solutionReader) throws Exception {
		LOGGER.info("Scoring: Begin");

		// Parse the solution file
		LOGGER.info("Scoring: Read solution");
		Solution solution = Solution.read(inputMapping, solutionReader);

		int totalBuildingCosts = Utils.getTotalBuildingCosts(inputMapping,solution.getTotalCustomerInfo().getNumberOfOffices());
		System.out.println("Building cost"+ String.valueOf(totalBuildingCosts));
		long finalScore = 0;
		Set<Point> replyOffices = new HashSet<>();
		Set<Point> customerLocation = new HashSet<>();

		for (CustomerScore customerScore : solution.getTotalCustomerInfo().getCustomerScores()) {
			long customerLocalScore = customerScore.getCustomerReward() - customerScore.getCost();
			replyOffices.add(customerScore.getReplyOffice());
			customerLocation.add(customerScore.getCustomerLocation());
			finalScore = finalScore + customerLocalScore;
		}


		int servicesScore = 0;
		System.out.println(replyOffices);
		Iterator<Point> it = replyOffices.iterator();
		while(it.hasNext()){
			Point replyOffice = it.next();

			for (InputMapping.Service service : inputMapping.getServices()) {
				System.out.println(replyOffice);
				System.out.println(service);
				System.out.println("|"+replyOffice.getX()+"-"+service.x+"|,|"+replyOffice.getY()+"-"+service.y+"|");
				System.out.println("distance evaluation: "+ String.valueOf( Utils.getDistance(replyOffice, new Point(service.x, service.y))));
				System.out.println("service Utility: "+ service.utilityValue);
				int distance = Utils.getDistance(replyOffice, new Point(service.x, service.y));
				int localServiceScore = service.utilityValue - distance;
				if (distance > inputMapping.getMapSize().maximalDistanceFromService) {
					localServiceScore = 0;
				}
				System.out.println("local service score: "+ localServiceScore);
				servicesScore = servicesScore + localServiceScore;

			}
		}



		long bonus = evaluateTheBonus(customerLocation,inputMapping);
		System.out.println("Bonus "+ String.valueOf(bonus));
		System.out.println("Final Score "+ finalScore);
		return Math.max(finalScore - totalBuildingCosts + bonus + servicesScore, 0);
	}

	private static long evaluateTheBonus(Set<Point> customerLocation, InputMapping inputMapping) throws ValidationException {
		long bonus = 0;
		if (customerLocation.size() == inputMapping.getMapSize().customersNumber) {
			Iterator<Point> it = customerLocation.iterator();
			while(it.hasNext()) {
				Point customer = it.next();
				bonus = bonus + Utils.getCustomerReward(inputMapping.getCustomers(),customer);
			}
		}
		return bonus;
	}


}
