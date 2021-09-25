package lambda;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import model.InputMapping;
import org.apache.commons.io.input.BOMInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.reply.codemasters.judgelambda.GenericValidationLambda;
import it.reply.codemasters.judgelambda.model.OutputFile;
import model.PairContainer;
import model.Solution;
import exceptions.ValidationException;

public class ValidatorLambda extends GenericValidationLambda {

	private static final Logger LOGGER = LoggerFactory.getLogger(lambda.ValidatorLambda.class);

	public ValidatorLambda() {
		super(ValidatorLambda::validationFunction);
	}

	private static String validationFunction(OutputFile file) {
		try {

			try (
				Reader inputReader = new BufferedReader(new InputStreamReader(new BOMInputStream(file.decodedInputStream()), StandardCharsets.UTF_8));
				Reader solutionReader = new BufferedReader(new InputStreamReader(new BOMInputStream(file.decodedStream()), StandardCharsets.UTF_8));
			) {
				// ---------------- THIS IS A SAMPLE PROBLEM -------------------
				// ---- REPLACE THE FOLLOWING CODE WITH YOUR IMPLEMENTATION ----

				// Parse problem input
				InputMapping inputMapping = InputMapping.read(inputReader);

				// Perform validation. The validation routine is successful
				// if it doesn't throw exceptions
				SolutionChecker.validate(inputMapping, solutionReader);

				// If no exception is raised, validation is successful
				return null;

				// -------------------------------------------------------------

			}
		} catch(ValidationException e) {
			LOGGER.error(String.format("Exception during validation [%s]", e.getCategory().toString()), e.getMessage());
			return e.getCategory().getMessage();
		} catch(Exception e) {
			LOGGER.error("Generic error during validation", e);
			return "Generic error";
		}
	}
}
