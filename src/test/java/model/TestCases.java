package model;

import org.junit.Test;
import org.junit.Assert;

import java.io.*;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.input.BOMInputStream;

import lambda.SolutionChecker;

public class TestCases {

	@Test
	public void scoringTest() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		try (
				Reader inputReader = new BufferedReader(new InputStreamReader(new BOMInputStream(classLoader.getResourceAsStream("input-00.txt")), StandardCharsets.UTF_8));
				Reader solutionReader = new BufferedReader(new InputStreamReader(new BOMInputStream(classLoader.getResourceAsStream("solution-00.txt")), StandardCharsets.UTF_8));
		) {
			PairContainer pairContainer = PairContainer.read(inputReader);

			long score = SolutionChecker.score(pairContainer, solutionReader);
			Assert.assertEquals(86833L, score);
		}
	}

	@Test
	public void validationTest() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		try (
				Reader inputReader = new BufferedReader(new InputStreamReader(new BOMInputStream(classLoader.getResourceAsStream("input-01.txt")), StandardCharsets.UTF_8));
				Reader solutionReader = new BufferedReader(new InputStreamReader(new BOMInputStream(classLoader.getResourceAsStream("solution-00.txt")), StandardCharsets.UTF_8));
		) {
//			PairContainer pairContainer = InputMapping.read(inputReader);
			InputMapping.read(inputReader);

//			long score = SolutionChecker.score(pairContainer, solutionReader);
//			Assert.assertEquals(86833L, score);
		}
	}
}
