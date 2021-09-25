package lambda;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.input.BOMInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.reply.codemasters.judgelambda.GenericScoringLambda;
import it.reply.codemasters.judgelambda.model.OutputFile;
import model.PairContainer;
import model.Solution;

public class ScoringLambda extends GenericScoringLambda {

	private static final Logger LOGGER = LoggerFactory.getLogger(lambda.ScoringLambda.class);

	public ScoringLambda() {
		super(ScoringLambda::scoringFunction);
	}

	private static long scoringFunction(OutputFile file) {
		try {
			try (
				Reader inputReader = new BufferedReader(new InputStreamReader(new BOMInputStream(file.decodedInputStream()), StandardCharsets.UTF_8));
				Reader solutionReader = new BufferedReader(new InputStreamReader(new BOMInputStream(file.decodedStream()), StandardCharsets.UTF_8));
			) {
				// ---------------- THIS IS A SAMPLE PROBLEM -------------------
				// ---- REPLACE THE FOLLOWING CODE WITH YOUR IMPLEMENTATION ----

				// Parse problem input
				PairContainer pairContainer = PairContainer.read(inputReader);

				// Perform scoring
				return SolutionChecker.score(pairContainer, solutionReader);

				// -------------------------------------------------------------
			}
		} catch(Exception e) {
			LOGGER.error(e.getMessage());
			return 0L;
		}
	}
}
