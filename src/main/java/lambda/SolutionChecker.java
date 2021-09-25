package lambda;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.*;
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
//
//
//		// Check if the result is equal to the multiplication of the pair operands
//		for(int i = 0; i < pairContainer.getPairs().size(); i++) {
//			PairContainer.Pair pair = pairContainer.getPairs().get(i);
//			long result = solution.getValues().get(i);
//			if(result != pair.op1 * pair.op2) {
//				throw new ValidationException(ValidationException.Category.CONSTRAINTS, "Result is wrong");
//			}
//		}
		LOGGER.info("Validation: End");
	}

	private static void checkConstraints(InputMapping inputMapping) throws ValidationException {
		System.out.println(inputMapping.getCustomers().size());
		if (inputMapping.getCustomers().size() > 500 || inputMapping.getCustomers().size() < 0 ) {
			throw  new ValidationException(ValidationException.Category.CONSTRAINTS, " Customer number not regular");
		}
	}

	public static long score(PairContainer pairContainer, Reader solutionReader) throws Exception {
		LOGGER.info("Scoring: Begin");

		// Parse the solution file
		LOGGER.info("Scoring: Read solution");
		Solution solution = Solution.read(pairContainer.getPairs().size(), solutionReader);

		// The score is the sum of the solution values
		LOGGER.info("Scoring: compute score");
		long score = 0;
		for(int i = 0; i < solution.getValues().size(); i++) {
			long result = solution.getValues().get(i);
			score += result;
		}

		LOGGER.info("Scoring: End");
		LOGGER.info("Score: " + score);
		return score;
	}
}
