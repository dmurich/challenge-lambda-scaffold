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

		int totalBuildingCosts = Utils.getTotalBuildingCosts(inputMapping,solution.getEnvironmentCollector().getReplyOffices().size());
		LOGGER.info("Building costs: "+ String.valueOf(totalBuildingCosts));

		long costs = Utils.getTotalPathCosts(solution.getEnvironmentCollector());
		LOGGER.info("Total costs for the paths: "+ String.valueOf(costs));

		long servicesScore = Utils.getServicesScore(solution.getEnvironmentCollector(), inputMapping);
		LOGGER.info("Total service score: "+ String.valueOf(servicesScore));

		long bonus = Utils.evaluateTheBonus(solution.getEnvironmentCollector().getCustomerLocation(),inputMapping);
		LOGGER.info("Bonus "+ String.valueOf(bonus));

		return Math.max(costs - totalBuildingCosts + bonus + servicesScore, 0);
	}




}
